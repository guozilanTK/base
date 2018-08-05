package tk.guozilan.base.api;

import tk.guozilan.base.model.BaseId;

import java.util.List;

/**
 * @author guozilan
 */
public interface BaseService<Entity extends BaseId> {

    Entity selectById(Long id);

    List<? extends Entity> select(Entity entity);

    List<? extends Entity> selectAll();

    Entity saveOrUpdate(Entity entity);

    int deleteById(Long id);

}
