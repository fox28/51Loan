package cn.loan51.www.a51loan.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.apple.a51loan.R;

import cn.loan51.www.a51loan.application.SharePreferenceUtils;
import cn.loan51.www.a51loan.utils.L;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
