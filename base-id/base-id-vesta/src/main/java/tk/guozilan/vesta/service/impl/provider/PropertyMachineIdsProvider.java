package tk.guozilan.vesta.service.impl.provider;

public class PropertyMachineIdsProvider implements MachineIdsProvider {
    private long[] machineIds;
    private int currentIndex;

    @Override
    public long getMachineId() {
        return machineIds[currentIndex++ % machineIds.length];
    }

    @Override
    public long getNextMachineId() {
        return getMachineId();
    }

    public void setMachineIds(long[] machineIds) {
        this.machineIds = machineIds;
    }
}
