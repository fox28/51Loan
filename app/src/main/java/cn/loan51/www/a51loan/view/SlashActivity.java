package cn.loan51.www.a51loan.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.apple.a51loan.R;

import java.util.UUID;

import cn.loan51.www.a51loan.application.I;
import cn.loan51.www.a51loan.application.SharePreferenceUtils;
import cn.loan51.www.a51loan.bean.Result;
import cn.loan51.www.a51loan.bean.User;
import cn.loan51.www.a51loan.utils.DeviceUuidFactory;
import cn.loan51.www.a51loan.utils.L;
import cn.loan51.www.a51loan.utils.MFGT;
import cn.loan51.www.a51loan.utils.OkUtils;

/**
 * 获得设备识别码
 * 闪屏持续时间2秒钟
 * 右上角可跳过（功能实现）-- 【待实现】
 * 临时登录、登录不成功、显示"登录异常，稍后重试"---断网测试
 *          登录成功，跳转主页
 * Created by apple on 2017/7/18.
 */

public class SlashActivity extends Activity{
    private static final String TAG = "SlashActivity";

    private static UUID mac_uuid;
    private static final int SLASH_LAST_TIME = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                MFGT.gotoMainActivity(SlashActivity.this);
//                MFGT.finish(SlashActivity.this);
//
//            }
//        }, SLASH_LAST_TIME);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Long start = System.currentTimeMillis();//起始时间点
                if (mac_uuid == null) {
                    getDeviceId();
                }
                if (mac_uuid!= null) {
                    // 临时登录过程：
                    // 计算时间，闪屏保持3秒
                    // https://modelx.yuzhidushu.com/api/v1/user/temp/login
                    // OkHttp下载，最终封装框架
                    // post请求
                    OkUtils<Result> utils = new OkUtils<>(SlashActivity.this);
                    utils.url(I.REQUEST_USER_TEMPORARY_LOGIN)
                            .post()
                            .addParam(I.User.MAC_UUID, mac_uuid+"")
                            .targetClass(User.class)
                            .execute(new OkUtils.OnCompleteListener<Result>() {
                                @Override
                                public void onSuccess(Result result) {
                                    L.e(TAG, result.toString());
                                    boolean flag = false;
                                    if (result!=null && result.getRetMsg().equals("success")) {
                                        User user = (User) result.getRetData();
                                        if (user!=null) {
                                            // user保存到SharePreference
                                            saveUserInfo(user);
                                            flag =true;
                                        }
                                        // 验证：打印输出SharePreference
                                        Log.e(TAG, SharePreferenceUtils.getInstance().getName());

                                    }
                                    Long lastTime = System.currentTimeMillis() - start;
                                    if (SLASH_LAST_TIME - lastTime > 0) {
                                        try {
                                            Thread.sleep(SLASH_LAST_TIME-lastTime);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (flag) {
                                        MFGT.gotoMainActivity(SlashActivity.this);
                                        MFGT.finish(SlashActivity.this);
                                    } else {
                                        //  设置flag，跳转主页或者异常页面
                                    }
                                }

                                @Override
                                public void onError(String error) {
                                    L.e(TAG, "onError, error = "+error);


                                }
                            });



                }
            }
        }).start();
        /*
         // 设置持续时间

         */

    }

    private void initData() {
        // 获得设备识别码
        getDeviceId();
    }

    /**
     * 获得mac_uuid, 存储在SharePreference中
     * 打印验证，成功获得mac_uuid, 已存储于SharePreference
     */
    private void getDeviceId() {
        mac_uuid = new DeviceUuidFactory(this).getDeviceUuid();
        L.e(TAG, "mac_uuid = "+mac_uuid);

    }

    private void saveUserInfo(User user) {
        if (user != null) {
            SharePreferenceUtils.getInstance().setAccessToken(user.getAccess_token());
            SharePreferenceUtils.getInstance().setName(user.getName());
            SharePreferenceUtils.getInstance().setTelephone(user.getTelephone());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkUtils.onRelease();
    }
}
