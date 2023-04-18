package cn.yumengfei.response;

public class Result {
    private Integer code; // 响应码， 1 代表成功; 0 代表失败
    private String msg; //响应码 描述字符串
    private Object data; //返回的数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public Result() {

    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        return new Result(1,"success", null);
    }

    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    public static Result fail(String msg) {
        return new Result(0, msg, null);
    }
}
