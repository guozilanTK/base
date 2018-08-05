package tk.guozilan.base.service;

import cn.hutool.core.lang.Assert;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.guozilan.base.api.BaseService;
import tk.guozilan.base.model.BaseId;
import tk.guozilan.base.model.BasePage;

import java.util.List;

/**
 * @author guozilan
 */
public abstract class AbstractService<Entity extends BaseId, Mapper extends GzlMapper<Entity>>
        implements BaseService<Entity> {

    @Autowired
    protected Mapper mapper;

    @Override
    public Entity selectById(Long id) {
        Assert.notNull(id);
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<? extends Entity> select(Entity entity) {
        if(entity instanceof BasePage && ((BasePage) entity).needPage()){
            PageHelper.startPage(entity);
        }
        return mapper.select(entity);
    }

    @Override
    public List<? extends Entity> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public Entity saveOrUpdate(Entity entity) {
        if (entity.getId() == null) {
            mapper.insert(entity);
        } else {
            mapper.updateByPrimaryKeySelective(entity);
        }
        return entity;
    }

    @Override
    public int deleteById(Long id) {
        Assert.notNull(id);
        return mapper.deleteByPrimaryKey(id);
    }
}
