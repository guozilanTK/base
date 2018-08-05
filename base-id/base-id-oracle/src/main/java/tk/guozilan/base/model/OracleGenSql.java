package tk.guozilan.base.model;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.gensql.GenSql;

/**
 * 下面是简单的用表名 + _SEQ 作为序列，你可以根据实际情况来修改
 *
 * @author guozilan
 */
public class OracleGenSql implements GenSql {

    public static final String SQL = "SELECT %s_SEQ.NEXTVAL FROM DUAL";

    @Override
    public String genSql(EntityTable entityTable, EntityColumn entityColumn) {
        return String.format(SQL, entityTable.getName().toUpperCase());
    }

}
