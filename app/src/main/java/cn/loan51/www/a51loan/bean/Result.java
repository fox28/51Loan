package cn.loan51.www.a51loan.bean;

import java.io.Serializable;

/**
 * Created by apple on 2017/7/19.
 */

public class Result implements Serializable{
    /*
     "errcode": 0,
  "errmsg": "success",
  "data":
     */
    private int retCode;
    private String retMsg;
    private Object retData;


    public Result(){

    }
    public Result(int retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public Result(int retCode, String retMsg, Object retData) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.retData = retData;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }

    @Override
    public String toString() {
        return "Result{" +
                "retCode=" + retCode +
                ", retMsg='" + retMsg + '\'' +
                ", retData=" + retData +
                '}';
    }
}
