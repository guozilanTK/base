package tk.guozilan.vesta.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.guozilan.vesta.service.bean.Id;
import tk.guozilan.vesta.service.impl.bean.IdMeta;
import tk.guozilan.vesta.service.impl.bean.IdMetaFactory;
import tk.guozilan.vesta.service.impl.bean.IdType;
import tk.guozilan.vesta.service.impl.converter.IdConverter;
import tk.guozilan.vesta.service.impl.converter.IdConverterImpl;
import tk.guozilan.vesta.service.impl.provider.MachineIdProvider;
import tk.guozilan.vesta.service.impl.timer.SimpleTimer;
import tk.guozilan.vesta.service.impl.timer.Timer;
import tk.guozilan.vesta.service.intf.IdService;

import java.util.Date;

public abstract class AbstractIdServiceImpl implements IdService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected long machineId = -1;
    protected long genMethod = 0;
    protected long version = 0;

    protected IdType idType;
    protected IdMeta idMeta;

    protected IdConverter idConverter;

    protected MachineIdProvider machineIdProvider;

    protected Timer timer;

    public AbstractIdServiceImpl() {
        idType = IdType.SECONDS;
    }

    public AbstractIdServiceImpl(String type) {
        idType = IdType.parse(type);
    }

    public AbstractIdServiceImpl(long type) {
        idType = IdType.parse(type);
    }

    public AbstractIdServiceImpl(IdType type) {
        idType = type;
    }

    public void init() {
        if (this.idMeta == null) {
            setIdMeta(IdMetaFactory.getIdMeta(idType));
        }
        if (this.idConverter == null) {
            setIdConverter(new IdConverterImpl());
        }
        if (this.timer == null) {
            setTimer(new SimpleTimer());
        }
        this.timer.init(idMeta, idType);

        this.machineId = machineIdProvider.getMachineId();
        validateMachineId(this.machineId);
    }

    @Override
    public long genId() {
        Id id = new Id();

        id.setMachine(machineId);
        id.setGenMethod(genMethod);
        id.setType(idType.value());
        id.setVersion(version);

        populateId(id);

        long ret = idConverter.convert(id, this.idMeta);

        // Use trace because it cause low performance
        if (log.isTraceEnabled()) {
            log.trace(String.format("Id: %s => %d", id, ret));
        }

        return ret;
    }

    public void validateMachineId(long machineId) {
        if (machineId < 0) {
            log.error("The machine ID is not configured properly (" + machineId + " < 0) so that Vesta Service refuses to start.");

            throw new IllegalStateException(
                    "The machine ID is not configured properly (" + machineId + " < 0) so that Vesta Service refuses to start.");

        } else if (machineId >= (1 << this.idMeta.getMachineBits())) {
            log.error("The machine ID is not configured properly ("
                    + machineId + " >= " + (1 << this.idMeta.getMachineBits()) + ") so that Vesta Service refuses to start.");

            throw new IllegalStateException("The machine ID is not configured properly ("
                    + machineId + " >= " + (1 << this.idMeta.getMachineBits()) + ") so that Vesta Service refuses to start.");

        }
    }

    protected abstract void populateId(Id id);

    @Override
    public Date transTime(final long time) {
        return timer.transTime(time);
    }


    @Override
    public Id expId(long id) {
        return idConverter.convert(id, this.idMeta);
    }

    @Override
    public long makeId(long time, long seq) {
        return makeId(time, seq, machineId);
    }

    @Override
    public long makeId(long time, long seq, long machine) {
        return makeId(genMethod, time, seq, machine);
    }

    @Override
    public long makeId(long genMethod, long time, long seq, long machine) {
        return makeId(idType.value(), genMethod, time, seq, machine);
    }

    @Override
    public long makeId(long type, long genMethod, long time,
                       long seq, long machine) {
        return makeId(version, type, genMethod, time, seq, machine);
    }

    @Override
    public long makeId(long version, long type, long genMethod,
                       long time, long seq, long machine) {
        Id id = new Id(machine, seq, time, genMethod, type, version);
        return idConverter.convert(id, this.idMeta);
    }

    public void setGenMethod(long genMethod) {
        this.genMethod = genMethod;
    }

    public void setIdConverter(IdConverter idConverter) {
        this.idConverter = idConverter;
    }

    public void setIdMeta(IdMeta idMeta) {
        this.idMeta = idMeta;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public void setMachineIdProvider(MachineIdProvider machineIdProvider) {
        this.machineIdProvider = machineIdProvider;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}