package tk.guozilan.base.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import tk.guozilan.base.controller.propertyeditors.CustomDateEditor;

import java.util.Date;

/**
 * @author guozilan
 */
public abstract class AbstractController {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(false));
    }

}
