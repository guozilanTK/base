package tk.guozilan.base.model;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author guozilan
 */
public class ListResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<? extends T> rows;
    private Long total;
    private boolean success;
    private Throwable throwable;
    private String msg;

    private ListResponse() {
    }

    public static <T> ListResponse<T> ok() {
        return ok(null, null);
    }

    public static <T> ListResponse<T> ok(PageInfo<T> pageInfo) {
        return ok(pageInfo.getRows(), null).total(pageInfo.getTotal());
    }

    public static <T> ListResponse<T> ok(List<? extends T> rows) {
        return ok(rows, null);
    }

    public static <T> ListResponse<T> ok(List<? extends T> rows, String msg) {
        ListResponse<T> response = new ListResponse<>();
        response.success = true;
        response.rows = rows;
        if(rows instanceof Page){
            response.total = ((Page<? extends T>) rows).getTotal();
        } else {
            response.total = Long.valueOf(rows.size());
        }
        response.msg = msg;
        return response;
    }

    public static <T> ListResponse<T> error() {
        ListResponse<T> response = new ListResponse<>();
        response.success = false;
        return response;
    }

    public static <T> ListResponse<T> error(Throwable throwable) {
        return error(throwable, throwable.getMessage());
    }

    public static <T> ListResponse<T> error(String msg) {
        return error(null, msg);
    }

    public static <T> ListResponse<T> error(Throwable throwable, String msg) {
        ListResponse<T> response = new ListResponse<>();
        response.success = false;
        response.throwable = throwable;
        response.msg = msg;
        return response;
    }

    public ListResponse<T> rows(List<? extends T> rows) {
        this.rows = rows;
        return this;
    }

    public ListResponse<T> success(boolean success) {
        this.success = success;
        return this;
    }

    public ListResponse<T> total(Long total) {
        this.total = total;
        return this;
    }

    public ListResponse<T> throwable(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    public ListResponse<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public List<? extends T> getRows() {
        return rows;
    }

    public void setRows(List<? extends T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
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
