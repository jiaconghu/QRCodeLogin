package cn.trunch.auth.http;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 封装原始Callback，对响应事件进行监听，下层判断成功与否
 */
public abstract class OkHttpCallback implements Callback {

    public abstract void onSuccess(Call call, JSONObject jsonObject);
    public abstract void onFailure(Call call);
    private Handler handler = new Handler(Looper.getMainLooper());
    private JSONObject jsonData;

    @Override
    public void onResponse(@NonNull final Call call, @NonNull final Response response) {
        if (response.isSuccessful()) {
            try {
                String string = response.body().string().trim();
                jsonData = (JSONObject) new JSONTokener(string).nextValue();
                System.out.println(jsonData);
            } catch (Exception e) {
                e.printStackTrace();
                onFailure(call, null); // 数据解析错误
            }
            if (jsonData != null) {
                if (isRunOnUiThread()) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            onSuccess(call, jsonData);
                        }
                    });
                } else {
                    onSuccess(call, jsonData);
                }
            } else {
                onFailure(call, null); // 响应数据为空
            }
        } else {
            onFailure(call, null); // 响应不成功
        }
    }

    @Override
    public void onFailure(@NonNull final Call call, @NonNull IOException e) {
        if (isRunOnUiThread()) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(call);
                }
            });
        } else {
            onFailure(call);
        }
    }

    protected boolean isRunOnUiThread() {
        return true;
    }
}
