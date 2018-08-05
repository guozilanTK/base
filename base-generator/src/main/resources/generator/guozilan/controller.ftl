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
import tk.guozilan.${projectName}.api.${tableClass.shortClassName}Service;
import tk.guozilan.${projectName}.model.${tableClass.shortClassName};

/**
 * @author guozilan
 */
@Controller
@RequestMapping("${tableClass.lowerCaseName}s")
public class ${tableClass.shortClassName}Controller {

    @Autowired
    private ${tableClass.shortClassName}Service ${tableClass.lowerCaseName}Service;

    @RequestMapping("/page")
    public ModelAndView main() {
        return new ModelAndView("${projectName}/${tableClass.lowerCaseName}-main");
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
