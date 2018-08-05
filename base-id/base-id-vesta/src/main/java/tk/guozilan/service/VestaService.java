package tk.guozilan.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import tk.guozilan.vesta.service.impl.MachineIdsIdServiceImpl;
import tk.guozilan.vesta.service.impl.bean.IdMeta;
import tk.guozilan.vesta.service.impl.populater.AtomicIdPopulator;
import tk.guozilan.vesta.service.impl.provider.MachineIdProvider;
import tk.guozilan.vesta.service.impl.provider.PropertyMachineIdsProvider;
import tk.guozilan.vesta.service.intf.IdService;

/**
 * @author guozilan
 */
@Component
public class VestaService {

    private static IdService idService;

    public static Long genId() {
        return idService.genId();
    }

    private IdMeta idMeta(){
        byte machineBits = 6;
        byte seqBits = 14;
        byte timeBits = 30;
        byte genMethodBits = 2;
        byte typeBits = 1;
        byte versionBits = 1;
        //总位数 54，可以
        return new IdMeta(machineBits, seqBits, timeBits, genMethodBits, typeBits, versionBits);
    }

    private MachineIdProvider machineIdProvider(){
        PropertyMachineIdsProvider machineIdProvider = new PropertyMachineIdsProvider();
        long[] machineIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        machineIdProvider.setMachineIds(machineIds);
        return machineIdProvider;
    }

    @Bean
    public IdService idService() {
        MachineIdsIdServiceImpl idService = new MachineIdsIdServiceImpl();
        idService.setIdPopulator(new AtomicIdPopulator());
        idService.setIdMeta(idMeta());
        idService.setMachineIdProvider(machineIdProvider());
        idService.init();

        VestaService.idService = idService;
        return idService;
    }
}
