package tk.guozilan.base.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author guozilan
 */
public class MapResponse extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String THROWABLE = "throwable";
    public static final String SUCCESS = "success";
    public static final String DATA = "data";
    public static final String MSG = "msg";

    private MapResponse() {
    }

    public static MapResponse ok() {
        return ok(null);
    }

    public static MapResponse ok(Object data) {
        return ok(data, null);
    }

    public static MapResponse ok(Object data, String msg) {
        MapResponse response = new MapResponse();
        response.put(SUCCESS, true);
        response.put(DATA, data);
        response.put(MSG, msg);
        return response;
    }

    public static MapResponse error() {
        MapResponse response = new MapResponse();
        response.put(SUCCESS, false);
        return response;
    }

    public static MapResponse error(Throwable throwable) {
        return error(throwable, throwable.getMessage());
    }

    public static MapResponse error(String msg) {
        return error(null, msg);
    }

    public static MapResponse error(Throwable throwable, String msg) {
        MapResponse response = new MapResponse();
        response.put(SUCCESS, false);
        response.put(THROWABLE, throwable);
        response.put(MSG, msg);
        return response;
    }

    public MapResponse add(String key, Object data) {
        this.put(key, data);
        return this;
    }

    public MapResponse data(Object data) {
        this.put(DATA, data);
        return this;
    }

    public MapResponse success(boolean success) {
        this.put(SUCCESS, success);
        return this;
    }

    public MapResponse throwable(Throwable throwable) {
        this.put(THROWABLE, throwable);
        return this;
    }

    public MapResponse msg(String msg) {
        this.put(MSG, msg);
        return this;
    }

}
