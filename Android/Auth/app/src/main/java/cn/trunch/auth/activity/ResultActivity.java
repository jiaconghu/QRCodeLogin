package cn.trunch.auth.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.dou361.dialogui.DialogUIUtils;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.HashMap;

import cn.trunch.auth.R;
import cn.trunch.auth.entity.AuthEntity;
import cn.trunch.auth.http.Api;
import cn.trunch.auth.http.ApiListener;
import cn.trunch.auth.http.ApiUtil;
import cn.trunch.auth.http.UniteApi;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class ResultActivity extends AppCompatActivity {

    private AuthEntity auth;

    private ImageView resultBackBtn;
    private ImageView resultUAvatar;
    private TextView resultUName;
    private TextView resultTime;
    private TextView resultAddress;
    private TextView resultInfo;
    private Button resultConfirmBtn;
    private Button resultCancelBtn;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        auth = (AuthEntity) getIntent().getSerializableExtra("auth");

        init();
        initListener();
    }

    private void init() {
        resultBackBtn = findViewById(R.id.resultBackBtn);
        resultUAvatar = findViewById(R.id.resultUAvatar);
        resultUName = findViewById(R.id.resultUName);
        resultTime = findViewById(R.id.resultTime);
        resultAddress = findViewById(R.id.resultAddress);
        resultInfo = findViewById(R.id.resultInfo);
        resultConfirmBtn = findViewById(R.id.resultConfirmBtn);
        resultCancelBtn = findViewById(R.id.resultCancelBtn);


        Glide.with(this)
                .load(ApiUtil.USER_AVATAR)
                .apply(bitmapTransform(new CircleCrop()))
                .into(resultUAvatar);
        resultUName.setText(ApiUtil.USER_NAME);
        resultTime.setText(auth.getAuthTime().toString());
        resultAddress.setText(auth.getAuthAddress());
    }

    private void initListener() {
        resultConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = DialogUIUtils.showLoading(ResultActivity.this,
                        "登录中...", false, false,
                        false, true)
                        .show();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("userId", ApiUtil.USER_ID);
                new UniteApi(ApiUtil.TOKEN_USE + auth.getAuthToken(), hashMap).post(new ApiListener() {
                    @Override
                    public void success(Api api) {
                        dialog.dismiss();
                        UniteApi uniteApi = (UniteApi) api;
                        try {
                            if (uniteApi.getJsonData().getInt("state") == 1) {
                                DialogUIUtils.showToastCenter("登录成功");
                                finish();
                            } else {
                                DialogUIUtils.showToastCenter("登录码已过期，请重试");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            DialogUIUtils.showToastCenter("登录失败，请重试");
                        }
                    }

                    @Override
                    public void failure(Api api) {
                        dialog.dismiss();
                        DialogUIUtils.showToastCenter("登录失败，请重试");
                    }
                });
            }
        });

        resultBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        resultCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
