package uo.ri.ui.foreman.reception.actions;

import alb.util.console.Console;
import uo.ri.business.VehicleCrudService;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;

import java.util.Optional;


public class WorkOrderUserInteractor {

    public WorkOrderDto askForWorkOrder(VehicleDto v) {
        WorkOrderDto wo = new WorkOrderDto();
        wo.description = Console.readString("Work description");
        wo.vehicleId = v.id;
        return wo;
    }

    public VehicleDto askForVehicle() throws BusinessException {
        String plate = Console.readString("Plate number");

        VehicleCrudService vs = Factory.service.forVehicleCrudService();
        VehicleDto v = vs.findVehicleByPlate(plate);

        return v.get();
    }

    private void assertPresent(Optional<?> o) throws BusinessException {
        if (o.isPresent()) return;
        throw new BusinessException("There is no such entity");
    }

}
