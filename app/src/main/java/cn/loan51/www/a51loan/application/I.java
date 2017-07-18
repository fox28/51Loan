package cn.loan51.www.a51loan.application;

public interface I {
	// https://modelx.yuzhidushu.com/api/v1/user/temp/login
	// https://modelx.yuzhidushu.com/api/v1/user/tickets
	// https://modelx.yuzhidushu.com/api/v1/user/sms
	// https://modelx.yuzhidushu.com/api/v1/user/login

	/** 临时用户登录：*/
	String SERVER_ROOT = "https://modelx.yuzhidushu.com/api/v1/user/";
	String REQUEST_USER_TEMPORARY_LOGIN				= 		"https://modelx.yuzhidushu.com/api/v1/user/temp/login";
	String REQUEST_USER_TICKETS						= 		"https://modelx.yuzhidushu.com/api/v1/user/tickets";
	String REQUEST_USER_SMS							= 		"https://modelx.yuzhidushu.com/api/v1/user/sms";
	String REQUEST_USER_LOGIN						= 		"https://modelx.yuzhidushu.com/api/v1/user/login";
	String REQUEST_USER_UPDATE						= 		"https://modelx.yuzhidushu.com/api/v1/user";
	String REQUEST_USER_LOGIN_BY_PASSWORD			= 		"https://modelx.yuzhidushu.com/api/v1/user/login/password";
	String REQUEST_HOMEPAGE							= 		"https://modelx.yuzhidushu.com/api/v1/homepage";


	String UTF_8 = "utf-8";




	public static interface User{
		String MAC_UUID					=		"mac_uuid";
		String TOKEN					=		"token";
		String INEEDTICKETS				=		"ineedtickets";
    }
	public static interface Sms{
		String TELEPHONE				=		"telephone";
		String KEY						=		"key";
		String TICKETS					=		"tickets";
    }
	public static interface LoginByCode {
		String TELEPHONE				=		"telephone";
		String CODE						=		"code";
		String USER_ID					=		"user_id";
    }
	public static interface Update {
		String USER_ID					=		"user_id";
		String NAME						=		"name";
		String PASSWORD					=		"password";
    }

    public static interface LoginByPassword{
		String TELEPHONE				=		"telephone";
		String PASSWORD					=		"password";
	}

	/** SharePreference文件名 */
	public static interface SharePreference{
		String SHARE_PREFERENCE_NAME				=		"cn.kkk.usury_save_userInfo";
		String ACCESS_TOKEN 						=		"m_user_access_token";
		String ID									=		"m_user_id";
		String TELEPHONE							=		"m_user_telephone";
		String NAME									=		"m_user_name";
		String MAC_UUID								=		"device_id";

	}

//	public static interface User {
//		String MAC_UUID							=		"t_superwechat_user";
//		String USER_NAME 							= 		"m_user_name";					//用户账号
//		String PASSWORD 							= 		"m_user_password";				//用户密码
//		String NICK 								= 		"m_user_nick";					//用户昵称
//	}
//
//	public static interface Contact {
//		String TABLE_NAME 							= 		"t_superwechat_contact";
//		String CONTACT_ID 							= 		"m_contact_id";					//主键
//		String USER_NAME 							= 		"m_contact_user_name";			//用户账号
//		String CU_NAME 								= 		"m_contact_cname";				//好友账号
//	}
	


//	String SERVER_ROOT = "http://120.26.242.249:8080/SuperWeChatServerV2.0/";
//	String SERVER_ROOT = "http://101.251.196.90:8080/SuperWeChatServerV2.0/";

//	/** 上传头像图片的类型：user_avatar或group_icon */
//	String AVATAR_TYPE 								= 		"avatarType";
//	/** 用户的账号或群组的环信id */
//	String NAME_OR_HXID                             =       "name_or_hxid";
//	/** 客户端发送的获取服务端状态的请求 */
//	String REQUEST_SERVERSTATUS 					= 		"getServerStatus";
//	/** 客户端发送的新用户注册的请求 */
//	String REQUEST_REGISTER		 					= 		"register";
//	/** 客户端发送的取消注册的请求 */
//	String REQUEST_UNREGISTER 						= 		"unregister";
//	/** 客户端发送的用户登录请求 */
//	String REQUEST_LOGIN 							= 		"login";
//	/** 客户端发送的下载用户头像请求 */
//	String REQUEST_DOWNLOAD_AVATAR	 				= 		"downloadAvatar";
//	/** 客户端发送的上传/更新用户头像的请求 */
//	String REQUEST_UPDATE_AVATAR 					= 		"updateAvatar";
//	/** 客户端发送的更新用户昵称的请求 */
//	String REQUEST_UPDATE_USER_NICK 				= 		"updateNick";
//	/** 客户端发送的更新用户密码的请求 */
//	String REQUEST_UPDATE_USER_PASSWORD 			= 		"updatePassword";

}
