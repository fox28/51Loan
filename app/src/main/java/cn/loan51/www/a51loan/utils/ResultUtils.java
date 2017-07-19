package cn.loan51.www.a51loan.utils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import cn.loan51.www.a51loan.application.I;

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
    public static <T> Result getResultFromJsonWithUser(String jsonStr, Class<T> cls) {
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
                L.e("ResultUtils", "getResultFromJsonWithUser, RetData = "+dataObject);
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
}
