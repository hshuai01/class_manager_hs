package cn.tohuangshuai.common.util;

public class HSJSONResult {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;


    public static HSJSONResult ok(){
        return new HSJSONResult(null);
    }

    public static HSJSONResult ok(Object data){
        return new HSJSONResult(data);
    }

    public static HSJSONResult error(String msg){
        return new HSJSONResult(502,msg,null);
    }

    public HSJSONResult() {
    }

    public HSJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public HSJSONResult(Object data) {
        this.data = data;
        this.status = 200;
        this.msg = "success";
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
