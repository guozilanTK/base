package tk.guozilan.base.model;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author guozilan
 */
public class BaseId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(order = ORDER.BEFORE, genSql = OracleGenSql.class)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
