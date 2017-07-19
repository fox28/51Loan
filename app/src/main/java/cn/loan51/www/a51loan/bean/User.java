package cn.loan51.www.a51loan.bean;

/**
 * Created by apple on 2017/5/9.
 */

public class User {

    /*
    "user": {
            "id": 14,
            "name": "590eee7833581",
            "telephone": null,
            "email": null,
            "created_at": "2017-05-07 09:52:50",
            "updated_at": "2017-05-07 09:52:56",
            "mac_uuid": "829382988943",
            "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjY4ZWFmMjI3YzY0YzY1OWU5MTllY2UxYTZkZWU1NjI0MDQwZjkwODFlNTg2MDFjNjIzOGY5MzIyMzA2NTUzN2Y5ZGY1NDhjYWE0NDM1YzkzIn0.eyJhdWQiOiIxIiwianRpIjoiNjhlYWYyMjdjNjRjNjU5ZTkxOWVjZTFhNmRlZTU2MjQwNDBmOTA4MWU1ODYwMWM2MjM4ZjkzMjIzMDY1NTM3ZjlkZjU0OGNhYTQ0MzVjOTMiLCJpYXQiOjE0OTQxNTA3NzYsIm5iZiI6MTQ5NDE1MDc3NiwiZXhwIjoxNTI1Njg2Nzc2LCJzdWIiOiIxNCIsInNjb3BlcyI6W119.hIwqbS2rvI1ZJTR_nfFu5l7Dky0EO1WzOZQCi-xkXyJK4WAN8rZ5g9HGq-S-w6nWKCn1yAKwMTrUAHJIxoIa20bmR7wwk55_BL7R-2buldypubVCN_z6PJTa6MvOMj-8ZOEJIniPBWPS1GvFALAmxXo4M_9WqcGvTbNhrRT7X12VsvRmxEgPJxsN4rgBk24RtnserFBM8V-tpaYnQmFJvqWvB-VRS8ILuTPwk5NWZOsTs3awhZ_mhio08F0brqiZK_Ik5jqpaDhnxR_qkdnJo6pbyhboZWKUDRHPkXw8_OOwwxFBfoVMVS1ZxqCJ8AyXwBf_h2aRZjpYIp0wLb0MFjLjCfLtiT0k0-LoIC-F3EtGDmtkRjghrA1zT2b1MO_ORzsaZFyg8eFPJDdZyuZbVNLeh5ic-e34vLCbHJT-twnfb-CYqw3a28ygCURBELzeqoEBgo3l20gSmTvBWKM13xeB7ihRBboOLA5hju7iuk7mmiPHxQyssxFC7-P-kAt6vGpnxQiF4dnFyd-qh_x3WfdVBcvYXBA0BRwcT6sOKBAx_Xe7K-7mebvxSKxIV5sQvfem-XVtamoq-pC6yt11FhQEvs6MGOOBT0DXcxrHNLK8HdCDgYI6Z7R0zlP2zah88wG-Rg0lgDc4V7t41DffuU8xjbLF2tVFoQ_LgJn-JEo"
        }
     */
    private int id;
    private String name;
    private String telephone;
    private String email;
    private String created_at;
    private String updated_at;
    private String mac_uuid;
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
        return "Uer{"+
                "id = \'"+ id + "\'"+
                " ,name = \'"+ name + "\'"+
                " ,telephone = \'" + telephone + "\'" +
                " ,created_at = \'" + created_at + "\'" +
                "mac_uuid = \'" + mac_uuid + '\'' +
                " ,access_token = \'" + access_token + "\'" +
                "}";
    }
}
