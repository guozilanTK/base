package tk.guozilan.base.model;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author guozilan
 */
public class BaseId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(insertable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
