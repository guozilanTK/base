package tk.guozilan.base.model;

import java.io.Serializable;

/**
 * @author guozilan
 */
public class ObjectResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private T data;
    private boolean success;
    private Throwable throwable;
    private String msg;

    private ObjectResponse() {
    }

    public static <T> ObjectResponse<T> ok() {
        return ok(null);
    }

    public static <T> ObjectResponse<T> ok(T data) {
        return ok(data, null);
    }

    public static <T> ObjectResponse<T> ok(T data, String msg) {
        ObjectResponse<T> response = new ObjectResponse<>();
        response.success = true;
        response.data = data;
        response.msg = msg;
        return response;
    }

    public static <T> ObjectResponse<T> error() {
        ObjectResponse<T> response = new ObjectResponse<>();
        response.success = false;
        return response;
    }

    public static <T> ObjectResponse<T> error(Throwable throwable) {
        return error(throwable, throwable.getMessage());
    }

    public static <T> ObjectResponse<T> error(String msg) {
        return error(null, msg);
    }

    public static <T> ObjectResponse<T> error(Throwable throwable, String msg) {
        ObjectResponse<T> response = new ObjectResponse<>();
        response.success = false;
        response.throwable = throwable;
        response.msg = msg;
        return response;
    }

    public ObjectResponse<T> data(T data) {
        this.data = data;
        return this;
    }

    public ObjectResponse<T> success(boolean success) {
        this.success = success;
        return this;
    }

    public ObjectResponse<T> throwable(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    public ObjectResponse<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
