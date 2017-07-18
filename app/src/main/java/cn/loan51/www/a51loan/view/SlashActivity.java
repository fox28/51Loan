package cn.loan51.www.a51loan.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.apple.a51loan.R;

import java.util.UUID;

import cn.loan51.www.a51loan.application.SharePreferenceUtils;
import cn.loan51.www.a51loan.utils.DeviceUuidFactory;
import cn.loan51.www.a51loan.utils.L;
import cn.loan51.www.a51loan.utils.MFGT;

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
}
