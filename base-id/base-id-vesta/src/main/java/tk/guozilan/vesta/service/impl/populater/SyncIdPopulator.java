package tk.guozilan.vesta.service.impl.populater;

import tk.guozilan.vesta.service.bean.Id;
import tk.guozilan.vesta.service.impl.bean.IdMeta;
import tk.guozilan.vesta.service.impl.timer.Timer;

public class SyncIdPopulator extends BasePopulator {

    public SyncIdPopulator() {
        super();
    }

    @Override
    public synchronized void populateId(Timer timer, Id id, IdMeta idMeta) {
        super.populateId(timer, id, idMeta);
    }

}
