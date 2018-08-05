package ${package};

import org.springframework.stereotype.Service;
import tk.guozilan.base.service.AbstractService;
import tk.guozilan.${projectName}.api.${tableClass.shortClassName}Service;
import tk.guozilan.${projectName}.mapper.${tableClass.shortClassName}Mapper;
import tk.guozilan.${projectName}.model.${tableClass.shortClassName};

/**
 * @author guozilan
 */
@Service
public class ${tableClass.shortClassName}ServiceImpl extends AbstractService<${tableClass.shortClassName}, ${tableClass.shortClassName}Mapper> implements ${tableClass.shortClassName}Service {

}
