package cn.loan51.www.a51loan.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import cn.loan51.www.a51loan.application.I;
import cn.loan51.www.a51loan.bean.Result;

/**
 * Created by apple on 2017/7/19.
 */

public class ResultUtils {
    /*
     "errcode": 0,
  "errmsg": "success",
  "data":
     */

    /**
     * 将jsonStr数据解析成Result，泛型类为User
     * @param jsonStr
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> Result getResultFromJson(String jsonStr, Class<T> cls) {
        Result result = new Result();
        // jsonStr转换成jsonObject类型
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            // jsonObject的value非空情况下，解析成result，setRetCode() setRetMsg()
            if (!jsonObject.isNull("errcode")) {
                result.setRetCode(jsonObject.getInt("errcode"));
            }
            if (!jsonObject.isNull("errmsg")) {
                result.setRetMsg(jsonObject.getString("errmsg"));
            }

            JSONObject dataObject = jsonObject.getJSONObject("data").getJSONObject("user");
            if (!jsonObject.isNull("data") && dataObject!=null) {// 返回数据不为空
                String data;
                L.e("ResultUtils", "getResultFromJson, RetData = "+dataObject);
                // dataObject转换成泛型类
                try {
                    data = URLDecoder.decode(dataObject.toString(), I.UTF_8);
                    // Gson解析为泛型类
                    T t = new Gson().fromJson(data, cls);
                    result.setRetData(t);
                    return result;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            // 若返回json的data为空，则直接返回result
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static <T> Result getResultFromJson(String jsonStr, Class<T> claz) {
//        Result result = new Result();
//        try {
//            String json = URLEncoder.encode(jsonStr, I.UTF_8);
//            // 第一次Gson解析
//            result = new Gson().fromJson(json, Result.class);
//            if (result.getData() != null) {
//                T t =  new Gson().fromJson((JsonElement) Result.getData(), claz);
//                result.setData(t);
//                return  result;
//            }
//            // 若result.getData == null, result
//            return result;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
