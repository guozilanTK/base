package tk.guozilan.vesta.service.impl;

import tk.guozilan.vesta.service.bean.Id;
import tk.guozilan.vesta.service.impl.bean.IdType;
import tk.guozilan.vesta.service.impl.populater.IdPopulator;
import tk.guozilan.vesta.service.impl.populater.LockIdPopulator;

public class IdServiceImpl extends AbstractIdServiceImpl {

    protected IdPopulator idPopulator;

    public IdServiceImpl() {
        super();
    }

    public IdServiceImpl(String type) {
        super(type);
    }

    public IdServiceImpl(long type) {
        super(type);
    }

    public IdServiceImpl(IdType type) {
        super(type);
    }

    @Override
    public void init() {
        super.init();
        initPopulator();
    }

    public void initPopulator() {
        if (idPopulator != null) {
            log.info("The " + idPopulator.getClass().getCanonicalName() + " is used.");
        } else {
            log.info("The default LockIdPopulator is used.");
            idPopulator = new LockIdPopulator();
        }
    }

    @Override
    protected void populateId(Id id) {
        idPopulator.populateId(timer, id, idMeta);
    }

    public void setIdPopulator(IdPopulator idPopulator) {
        this.idPopulator = idPopulator;
    }
}
