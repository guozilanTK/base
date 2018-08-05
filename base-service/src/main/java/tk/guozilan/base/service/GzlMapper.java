package tk.guozilan.base.service;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author guozilan
 */
@RegisterMapper
public interface GzlMapper<T> extends Mapper<T> {

}
