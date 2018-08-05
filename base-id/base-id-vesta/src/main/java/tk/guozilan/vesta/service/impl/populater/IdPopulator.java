package tk.guozilan.vesta.service.impl.populater;

import tk.guozilan.vesta.service.bean.Id;
import tk.guozilan.vesta.service.impl.bean.IdMeta;
import tk.guozilan.vesta.service.impl.timer.Timer;

public interface IdPopulator {

    void populateId(Timer timer, Id id, IdMeta idMeta);

}
