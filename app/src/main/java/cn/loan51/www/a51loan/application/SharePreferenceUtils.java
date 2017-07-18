package cn.loan51.www.a51loan.application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import cn.loan51.www.a51loan.utils.L;


/**
 * Created by apple on 2017/4/6.
 */

/**
 * 一般在LoginActivity的loginSuccess()中调用
 */
public class SharePreferenceUtils {
    private static final String TAG = "SharePreferenceUtils";
    // sharePreference的文件名, 该文件用于保存username、username可以用于从database读取数据
    private static final String SHARE_PREFERENCE_NAME = "cn.kkk.usury_save_userInfo";
    // 保存数据的字段
    private static final String ID                  = I.SharePreference.ID;
    private static final String TELEPHONE           = I.SharePreference.TELEPHONE;
    private static final String ACCESS_TOKEN        = I.SharePreference.ACCESS_TOKEN;
    private static final String NAME                = I.SharePreference.NAME;
    private static final String MAC_UUID            = I.SharePreference.MAC_UUID;


    static SharePreferenceUtils mSharePreferenceUtils;
    SharedPreferences sharePreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    private SharePreferenceUtils(Context cxt) {
        sharePreferences = cxt.getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharePreferences.edit();
    }

    // 初始化sharePreferences, editor
    public static synchronized void init(Context cxt) {
        if (mSharePreferenceUtils == null) {
            mSharePreferenceUtils = new SharePreferenceUtils(cxt);
        }
    }

    // 获得工具类实例 mSharePreferenceUtils;
    public static synchronized SharePreferenceUtils getInstance() {
        if (mSharePreferenceUtils == null) {
            throw new RuntimeException("please init first!");
        }
        return mSharePreferenceUtils;
    }


    public void setId(int id) {
        editor.putInt(ID, id).commit();
    }

    public int getId() {
        return sharePreferences.getInt(ID, -1);
    }

    // 保存数据方法
    // 切记别忘了接commit()
    public void setTelephone(String telephone) {
        editor.putString(TELEPHONE, telephone).commit();
    }

    // 读取数据方法
    public String getTelephone() {
        return sharePreferences.getString(TELEPHONE, null);
    }



    public void setAccessToken(String access_token) {
        editor.putString(ACCESS_TOKEN, access_token).commit();
    }
    public String getAccessToken() {
        return sharePreferences.getString(ACCESS_TOKEN,null);
    }


    public void setName(String name) {
        editor.putString(NAME, name).commit();
    }
    public String getName() {
        return sharePreferences.getString(NAME,null);
    }

    public  String getMac_uuid() {
        return sharePreferences.getString(MAC_UUID, null);
    }

    public void setMac_uuid(String mac_uuid){
        editor.putString(MAC_UUID,mac_uuid).commit();
    }

    // 用户退出登录后、清空数据
    public void removeUserInfo() {
        editor.remove(TELEPHONE).commit();
        editor.remove(NAME).commit();
        L.e(TAG, "removeUserInfo, 执行结果， telephone = "+getTelephone()+", name = "+getName());
    }
}
