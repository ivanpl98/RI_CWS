package uo.ri.ui.administrator.Mechanic.actions;

import uo.ri.business.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.common.ServiceFactory;
import uo.ri.ui.Action;
import uo.ri.util.Console;

public class UpdateMechanicAction implements Action {

    @Override
    public void execute() throws BusinessException {
        MechanicDto mecanico = new MechanicDto();
        // Ask for mechanic data
        mecanico.id = Console.readLong("Mechanic id");
        mecanico.dni = Console.readString("Dni");
        mecanico.name = Console.readString("Name");
        mecanico.surname = Console.readString("Surname");

        MechanicCrudService mcs = ServiceFactory.getMechanicCrudService();
        mcs.updateMechanic(mecanico);

        // Mostrar resultado
        Console.println("Mecánico actualizado");
    }

}