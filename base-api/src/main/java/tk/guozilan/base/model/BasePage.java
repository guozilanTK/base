package tk.guozilan.base.model;

import javax.persistence.Transient;

/**
 * @author guozilan
 */
public class BasePage extends BaseId {
    @Transient
    private Integer pageNum;

    @Transient
    private Integer pageSize;

    public boolean needPage(){
        return pageNum != null && pageSize != null;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
