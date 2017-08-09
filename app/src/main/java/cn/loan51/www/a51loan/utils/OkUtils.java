package cn.loan51.www.a51loan.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp的二次封装
 * 带增加post请求
 * Created by apple on 2017/8/8.
 */

public class OkUtils<T> {
    private static final String TAG = "OkUtils";
    private static final String UTF_8 = "utf-8";
    private static final int RESULT_OK = 0;
    private static final int RESULT_ERROR = 1;



    /**
     * 单例模式，无论多少个网络请求request，都由一个OkHttpClient对象处理
     */
    private static OkHttpClient mOkHttpClient;
    private Handler mHandler;

    // 地址栏的请求
    private StringBuilder mUrl;
    // 泛型，解析目标类
    private Class mClaz;

    /**
     * get请求：
     * 创建请求客户的：mOkHttpClient
     * 创建请求对象: Request request
     * 创建请求任务：Call call
     * 发起异步请求：call.enqueue(new CallBalk(){...});
     *
     *
     * post请求：
     * 创建客户端（已有）mOkHttpClient
     * 创建表单实体对象(拿到body构建器）：mFormBuilder
     * 创建请求实体对象：RequestBody body
     * 创建请求对象 Request request
     * 创建请求的任务 Call call
     * 发起请求：call.enqueue(new CallBalk(){...});
     *
     * 小结：get、post的区别是request之前
     *
     */


    private FormBody.Builder mFormBuilder;


    private OnCompleteListener mListener;
    /**
     * 客户端向服务端发送请求，处理返回结果的接口
     * @param <T>
     */
    public interface OnCompleteListener<T>{
        void onSuccess(T t); // 服务器返回结果的处理方法：包括完整数据，空数据
        void onError(String error);// 服务器没有返回数据的方法：断网、url错误
    }

    // 构造方法，mOkHttpClient单例模式
    public OkUtils(Context context) {
        if (mOkHttpClient == null) {
            synchronized (OkUtils.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient(); 
                }
            }
        }
        initHandler(context);
    }

    private void initHandler(Context context) {
        mHandler = new Handler(context.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case RESULT_ERROR:
                        if (msg.obj != null && mListener != null) {
                            mListener.onError(msg.obj.toString());
                        }
                        break;
                    case RESULT_OK:
                        if (msg.obj != null && mListener != null) {
                            T t = (T) msg.obj;
                            mListener.onSuccess(t);
                        }
                        break;
                }
            }
        };
    }

    public OkUtils<T> post(){
        mFormBuilder = new FormBody.Builder();
        return this;
    }

    public OkUtils<T> url(String url) {
        mUrl = new StringBuilder(url);
        return this;
    }

    public OkUtils<T> addParam(String key, String value) {
        if (mFormBuilder != null) {// post请求
            mFormBuilder.add(key, value );// URLEncoder.encode(value, UTF_8)
        } else {// get请求
            if (mUrl.indexOf("?") == -1) {
                mUrl.append("?");
            } else {
                mUrl.append("&");
            }
            try {
                mUrl.append(key).append("=").append(URLEncoder.encode(value, UTF_8));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return this;
    }
    
    public OkUtils<T> targetClass(Class claz) {
        mClaz = claz;
        return this;
    }

    public void execute(OnCompleteListener listener) {
        mListener = listener;
        if (mUrl == null) {
            Message msg = Message.obtain();
            msg.what = RESULT_ERROR;
            msg.obj = "url不能为空";
            mHandler.sendMessage(msg);
            return;
        }
        if (mUrl.indexOf("?") == -1) {
            Message msg = Message.obtain();
            msg.what = RESULT_ERROR;
            msg.obj = "请设置请求类型";
            mHandler.sendMessage(msg);
        }
        if (mClaz == null) {
            Message msg = Message.obtain();
            msg.what = RESULT_ERROR;
            msg.obj = "目标解析类claz不能为空";
            mHandler.sendMessage(msg);
            return;
        }
        Request.Builder builder = new Request.Builder().url(mUrl.toString());// url() 方法属于链式调用
        // post请求
        if (mFormBuilder != null) {
            FormBody formBody = mFormBuilder.build();
            builder.post(formBody);
        }
        Request request = builder.build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = Message.obtain();
                msg.what = RESULT_ERROR;
                msg.obj = e.toString();
                mHandler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                L.e(TAG, json);
                T t = (T) ResultUtils.getResultFromJson(json, mClaz);
                Message msg = Message.obtain();
                msg.what = RESULT_OK;
                msg.obj = t;
                mHandler.sendMessage(msg);

            }
        });
    }

    public static void onRelease() {
        if (mOkHttpClient != null) {
            mOkHttpClient.dispatcher().cancelAll();
            mOkHttpClient = null;
        }
    }
}
