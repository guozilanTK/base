package ${package};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tk.guozilan.base.controller.AbstractController;
import tk.guozilan.base.model.ListResponse;
import tk.guozilan.base.model.ObjectResponse;
import ${packageName}.api.${tableClass.shortClassName}Service;
import ${packageName}.model.${tableClass.shortClassName};

/**
 * @author guozilan
 */
@Controller
<#if tableClass.lowerCaseName?ends_with("y")>
    <#assign names = tableClass.lowerCaseName[0..tableClass.lowerCaseName?length - 2] + 'ies'>
<#else>
    <#assign names = tableClass.lowerCaseName + 's'>
</#if>
@RequestMapping("${names}")
public class ${tableClass.shortClassName}Controller extends AbstractController {

    @Autowired
    private ${tableClass.shortClassName}Service ${tableClass.lowerCaseName}Service;

    <#assign lastIndex = packageName?last_index_of('.') + 1>
    @RequestMapping("/page")
    public ModelAndView main() {
        return new ModelAndView("${packageName[lastIndex..]}/${tableClass.lowerCaseName}-main");
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ListResponse<${tableClass.shortClassName}> listBy${tableClass.shortClassName}(${tableClass.shortClassName} ${tableClass.lowerCaseName}) {
        try {
            return ListResponse.ok(${tableClass.lowerCaseName}Service.select(${tableClass.lowerCaseName}));
        } catch (Exception e) {
            return ListResponse.error(e);
        }
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public ObjectResponse<${tableClass.shortClassName}> list${tableClass.shortClassName}(@PathVariable("id") Long id) {
        try {
            return ObjectResponse.ok(${tableClass.lowerCaseName}Service.selectById(id));
        } catch (Exception e) {
            return ObjectResponse.error(e);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ObjectResponse<${tableClass.shortClassName}> saveOrUpdate(${tableClass.shortClassName} ${tableClass.lowerCaseName}) {
        try {
            return ObjectResponse.ok(${tableClass.lowerCaseName}Service.saveOrUpdate(${tableClass.lowerCaseName}));
        } catch (Exception e) {
            return ObjectResponse.error(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ObjectResponse<Integer> delete(@PathVariable("id") Long id) {
        try {
            return ObjectResponse.ok(${tableClass.lowerCaseName}Service.deleteById(id));
        } catch (Exception e) {
            return ObjectResponse.error(e);
        }
    }

}
