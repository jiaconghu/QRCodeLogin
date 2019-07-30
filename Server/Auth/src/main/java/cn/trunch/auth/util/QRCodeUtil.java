package cn.trunch.auth.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

/**
 * 二维码工具类
 *
 */
public class QRCodeUtil {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 400;
    // LOGO宽度
    private static final int WIDTH = 80;
    // LOGO高度
    private static final int HEIGHT = 80;

    private static BufferedImage createImage(String content, String imgPath,
                                             boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        // 去掉白边
        int[] rec = bitMatrix.getEnclosingRectangle();
        if(rec != null){
            int resWidth = rec[2] + 1;
            int resHeight = rec[3] + 1;
            BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
            resMatrix.clear();
            for (int i = 0; i < resWidth; i++) {
                for (int j = 0; j < resHeight; j++) {
                    if (bitMatrix.get(i + rec[0], j + rec[1])) {
                        resMatrix.set(i, j);
                    }
                }
            }
        }

        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
//            Log.info("no logo success：");
            return image;
        }

        // 配置了logo路径时插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress);
//        Log.info("have logo success");
        return image;
    }

    /**
     * 插入LOGO
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        //new一个URL对象
        URL url = new URL(imgPath);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        //创建文件用于暂存公司LOGO
        File tmpFile = createTmpFile();
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        //创建输出流
        FileOutputStream outStream = new FileOutputStream(tmpFile);
        //写入数据
        outStream.write(data);
        //关闭输出流
        outStream.close();
        Image src = ImageIO.read(tmpFile);
        if(src != null){
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            if (needCompress) { // 压缩LOGO
                if (width > WIDTH) {
                    width = WIDTH;
                }
                if (height > HEIGHT) {
                    height = HEIGHT;
                }
                Image image = src.getScaledInstance(width, height,
                        Image.SCALE_SMOOTH);
                BufferedImage tag = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(image, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                src = image;
            }
            // 插入LOGO
            Graphics2D graph = source.createGraphics();
            int x = (QRCODE_SIZE - width) / 2;
            int y = (QRCODE_SIZE - height) / 2;
            graph.drawImage(src, x, y, width, height, null);
            Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
            graph.setStroke(new BasicStroke(3f));
            graph.draw(shape);
            graph.dispose();
        }
    }

    private static File createTmpFile(){
        String path = "/tmpLogo";
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        // fileName表示你创建的文件名；为txt类型；
        String fileName="zxing_tmp.png";
        File tmpFile = new File(f,fileName);
        if(!tmpFile.exists()){
            try {
                tmpFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tmpFile;
    }


    /**
     * 把文件读出来
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /**
     * 生成二维码(内嵌LOGO)
     */
    public static void encode(String content, String imgPath, HttpServletResponse resp, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, resp.getOutputStream());
    }

    /**
     * 生成二维码(内嵌LOGO)
     */
    public static void encode(String content, String imgPath, HttpServletResponse resp)
            throws Exception {
        QRCodeUtil.encode(content, imgPath, resp, false);
    }

    /**
     * 生成二维码
     */
    public static void encode(String content, HttpServletResponse resp,
                              boolean needCompress) throws Exception {
        QRCodeUtil.encode(content, null, resp, needCompress);
    }

    /**
     * 生成二维码
     */
    public static void encode(String content, HttpServletResponse resp) throws Exception {
        QRCodeUtil.encode(content, null, resp, false);
    }

    /**
     * 生成二维码(内嵌LOGO)
     */
    public static void encode(String content, String imgPath,
                              OutputStream output, boolean needCompress, String realPath) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath,
                needCompress);

        File file =new File(realPath + "res/qrcodeTmp");
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        ImageIO.setCacheDirectory(file);

        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * 生成二维码
     */
    public static void encode(String content, OutputStream output)
            throws Exception {
        QRCodeUtil.encode(content, null, output, false, "/");
    }

    /**
     * 解析二维码
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
                image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * 解析二维码
     */
    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }
}