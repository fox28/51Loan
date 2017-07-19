package cn.loan51.www.a51loan.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.apple.a51loan.R;

import java.io.IOException;
import java.util.UUID;

import cn.loan51.www.a51loan.application.I;
import cn.loan51.www.a51loan.bean.User;
import cn.loan51.www.a51loan.utils.DeviceUuidFactory;
import cn.loan51.www.a51loan.utils.L;
import cn.loan51.www.a51loan.utils.MFGT;
import cn.loan51.www.a51loan.utils.Result;
import cn.loan51.www.a51loan.utils.ResultUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 获得设备识别码
 * 闪屏持续时间2秒钟
 * 右上角可跳过（功能实现）
 * Created by apple on 2017/7/18.
 */

public class SlashActivity extends Activity{
    private static final String TAG = "SlashActivity";

    private static UUID mac_uuid;
    private static final int slash_last_time = 3000;
    private User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MFGT.gotoMainActivity(SlashActivity.this);
                MFGT.finish(SlashActivity.this);

            }
        }, slash_last_time);

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mac_uuid == null) {
                    getDeviceId();
                }
                if (mac_uuid!= null) {
                    // 临时登录
                    // 计算时间，闪屏保持3秒
                    // https://modelx.yuzhidushu.com/api/v1/user/temp/login
                    // okhttp下载，最终封装框架
                    RequestBody requestBody = new FormBody.Builder()
                            .add(I.User.MAC_UUID, mac_uuid+"")
                            .build();
                    final Request request = new Request.Builder()
                            .url(I.REQUEST_USER_TEMPORARY_LOGIN)
                            .post(requestBody)
                            .build();
                    Call call = new OkHttpClient().newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            String json = response.body().string();
                            L.e(TAG, "onStart(), 临时登录，onResponse(), json = "+json);
                            Result result = ResultUtils.getResultFromJsonWithUser(json, User.class);
                            L.e(TAG, "result = "+ result);

                            // 解析json数据， 最终封装框架
//                            try {
//                                JSONObject jsonObject = new JSONObject(json);
//                                L.e(TAG, "onStart(), 临时登录，onResponse(), jsonObject = "+jsonObject);
//                                L.e(TAG, "flag = "+jsonObject.getString("errmsg").equals("success"));
//                                if (jsonObject.getString("errmsg").equals("success")) {
//
//                                    JSONObject userJson = jsonObject.getJSONObject("data").getJSONObject("user");
//                                    L.e(TAG, "userJson = "+userJson);
//                                    mUser.setId(userJson.getInt("id"));
//                                    mUser.setMac_uuid(userJson.getString("mac_uuid"));
//                                    mUser.setName(userJson.getString("name"));
//                                    mUser.setUpdated_at(userJson.getString("updated_at"));
//                                    mUser.setCreated_at(userJson.getString("created_at"));
//                                    mUser.setAccess_token(userJson.getString("access_token"));
//                                    mUser.setTelephone(userJson.getString("telephone"));
//                                    L.e(TAG, "id, mac_uuid, name, updated_at, created_at, access_token :"+mUser.toString());
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }

                        }
                    });



                }

            }
        }).start();

    }

    private void initData() {
        // 获得设备识别码
        getDeviceId();
        mUser = new User();
    }

    /**
     * 获得mac_uuid, 存储在SharePreference中
     * 打印验证，成功获得mac_uuid, 已存储于SharePreference
     */
    private void getDeviceId() {
        mac_uuid = new DeviceUuidFactory(this).getDeviceUuid();
        L.e(TAG, "mac_uuid = "+mac_uuid);

    }
}
