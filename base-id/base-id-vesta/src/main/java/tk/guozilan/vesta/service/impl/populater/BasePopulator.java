package tk.guozilan.vesta.service.impl.populater;

import tk.guozilan.vesta.service.bean.Id;
import tk.guozilan.vesta.service.impl.bean.IdMeta;
import tk.guozilan.vesta.service.impl.timer.Timer;

public abstract class BasePopulator implements IdPopulator, ResetPopulator {
    protected long sequence = 0;
    protected long lastTimestamp = -1;

    public BasePopulator() {
        super();
    }

    @Override
    public void populateId(Timer timer, Id id, IdMeta idMeta) {
        long timestamp = timer.genTime();
        timer.validateTimestamp(lastTimestamp, timestamp);

        if (timestamp == lastTimestamp) {
            sequence++;
            sequence &= idMeta.getSeqBitsMask();
            if (sequence == 0) {
                timestamp = timer.tillNextTimeUnit(lastTimestamp);
            }
        } else {
            lastTimestamp = timestamp;
            sequence = 0;
        }

        id.setSeq(sequence);
        id.setTime(timestamp);
    }

    @Override
    public void reset() {
        this.sequence = 0;
        this.lastTimestamp = -1;
    }
}
