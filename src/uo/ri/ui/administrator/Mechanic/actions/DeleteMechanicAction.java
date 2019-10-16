package uo.ri.ui.administrator.Mechanic.actions;

import uo.ri.business.MechanicCrudService;
import uo.ri.business.exception.BusinessException;
import uo.ri.common.ServiceFactory;
import uo.ri.ui.Action;
import uo.ri.util.Console;

public class DeleteMechanicAction implements Action {

    @Override
    public void execute() throws BusinessException {
        Long idMecanico = Console.readLong("Mechanic id");

        MechanicCrudService mcs = ServiceFactory.getMechanicCrudService();
        mcs.deleteMechanic(idMecanico);

        Console.println("The mechanic has been deleted.");
    }

}