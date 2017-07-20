package cn.loan51.www.a51loan.bean;

/**
 * Created by apple on 2017/5/9.
 */

public class User {

    /*
     "id": 163,
      "name": "596ecd006d4ee",
      "telephone": null,
      "email": null,
      "created_at": "2017-07-19 03:07:44",
      "updated_at": "2017-07-19 03:07:44",
      "mac_uuid": "a11c3d84-57d5-302d-a0ed-82a47878c92a",
      "unionid": null,
      "nickname": null,
      "sex": null,
      "language": null,
      "city": null,
      "province": null,
      "country": null,
      "avatar": null,
      "remark": null,
      "subscribed": null,
      "openid": null,
      "role_id": "0",
      "access_token": "eyJ0eX
     */
    private int id;
    private String name;
    private String telephone;
    private String email;
    private String created_at;
    private String updated_at;
    private String mac_uuid;
//    private String unionid;
//    private String nickname;
//    private String sex;
////    private String language;
//    private String province;
//    private String country;
//    private String avatar;
//    private String remark;
//    private String subscribed;
//    private String openid;
//    private String role_id;
    private String access_token;

    public User(String telephone) {
        this.telephone = telephone;
    }
    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getMac_uuid() {
        return mac_uuid;
    }

    public void setMac_uuid(String mac_uuid) {
        this.mac_uuid = mac_uuid;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;

        User user = (User) obj;
        if (!telephone.equals(user.getTelephone())) {
            return false;
        }
        return getUpdated_at().equals(user.getUpdated_at());
    }

    @Override
    public String toString() {
//        return super.toString();
        return "User{"+
                "id = \'"+ id + "\'"+
                " ,name = \'"+ name + "\'"+
                " ,telephone = \'" + telephone + "\'" +
                " ,created_at = \'" + created_at + "\'" +
                "mac_uuid = \'" + mac_uuid + '\'' +
                " ,access_token = \'" + access_token + "\'" +
                "}";
    }
}
