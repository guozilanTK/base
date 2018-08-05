package tk.guozilan.base.model.genid;

import tk.guozilan.service.VestaService;
import tk.mybatis.mapper.genid.GenId;

/**
 * @author guozilan
 */
public class VestaGenId implements GenId<Long> {

    @Override
    public Long genId(String table, String column) {
        return VestaService.genId();
    }

}
