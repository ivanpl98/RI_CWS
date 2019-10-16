package uo.ri.ui.foreman.reception.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.WorkOrderService;
import uo.ri.business.exception.BusinessException;


public class RemoveWorkOrderAction implements Action {

    @Override
    public void execute() throws BusinessException {

        Long woId = Console.readLong("Work order id");

        WorkOrderService as = Factory.service.forWorkOrderService();
        as.deleteWorkOrder(woId);

        Console.println("\nThe work order has been deleted");
    }
}
