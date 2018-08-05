package tk.guozilan.base.model;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author guozilan
 */
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> rows;
    private Long total;

    public static <T> PageInfo<T> of(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.rows = list;
        if (list instanceof Page) {
            pageInfo.total = ((Page<T>) list).getTotal();
        } else {
            pageInfo.total = Long.valueOf(list.size());
        }
        return pageInfo;
    }

    public List<T> getRows() {
        return rows;
    }

    public Long getTotal() {
        return total;
    }

}
