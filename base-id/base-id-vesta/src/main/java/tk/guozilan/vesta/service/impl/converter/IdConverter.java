package tk.guozilan.vesta.service.impl.converter;

import tk.guozilan.vesta.service.bean.Id;
import tk.guozilan.vesta.service.impl.bean.IdMeta;

public interface IdConverter {

    long convert(Id id, IdMeta idMeta);

    Id convert(long id, IdMeta idMeta);

}
