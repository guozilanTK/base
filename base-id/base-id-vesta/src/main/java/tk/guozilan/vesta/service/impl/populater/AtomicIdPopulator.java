package tk.guozilan.vesta.service.impl.populater;

import tk.guozilan.vesta.service.bean.Id;
import tk.guozilan.vesta.service.impl.bean.IdMeta;
import tk.guozilan.vesta.service.impl.timer.Timer;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicIdPopulator implements IdPopulator, ResetPopulator {

    private AtomicReference<Variant> variant = new AtomicReference<Variant>(new Variant());

    public AtomicIdPopulator() {
        super();
    }

    @Override
    public void populateId(Timer timer, Id id, IdMeta idMeta) {
        Variant varOld, varNew;
        long timestamp, sequence;

        while (true) {

            // Save the old variant
            varOld = variant.get();

            // populate the current variant
            timestamp = timer.genTime();
            timer.validateTimestamp(varOld.lastTimestamp, timestamp);

            sequence = varOld.sequence;

            if (timestamp == varOld.lastTimestamp) {
                sequence++;
                sequence &= idMeta.getSeqBitsMask();
                if (sequence == 0) {
                    timestamp = timer.tillNextTimeUnit(varOld.lastTimestamp);
                }
            } else {
                sequence = 0;
            }

            // Assign the current variant by the atomic tools
            varNew = new Variant();
            varNew.sequence = sequence;
            varNew.lastTimestamp = timestamp;

            if (variant.compareAndSet(varOld, varNew)) {
                id.setSeq(sequence);
                id.setTime(timestamp);

                break;
            }

        }
    }

    @Override
    public void reset() {
        variant = new AtomicReference<Variant>(new Variant());
    }

    class Variant {

        private long sequence = 0;
        private long lastTimestamp = -1;

    }

}
