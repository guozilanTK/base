package ${package};

import org.springframework.stereotype.Service;
import tk.guozilan.base.service.AbstractService;
import ${packageName}.api.${tableClass.shortClassName}Service;
import ${packageName}.mapper.${tableClass.shortClassName}Mapper;
import ${packageName}.model.${tableClass.shortClassName};

/**
 * @author guozilan
 */
@Service
public class ${tableClass.shortClassName}ServiceImpl extends AbstractService<${tableClass.shortClassName}, ${tableClass.shortClassName}Mapper> implements ${tableClass.shortClassName}Service {

}
