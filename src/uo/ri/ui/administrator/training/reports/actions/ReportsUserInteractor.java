package uo.ri.ui.administrator.training.reports.actions;

import alb.util.console.Console;
import uo.ri.business.CourseAttendanceService;
import uo.ri.business.CourseCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.ui.administrator.Printer;

import java.util.List;


public class ReportsUserInteractor {

    public Long askForMechanicId() throws BusinessException {
        showMechanics();
        return Console.readLong("Mechanic id");
    }

    public String askForVehicleTypeId() throws BusinessException {
        showVehicleTypes();
        return Console.readString("Vehicle type id");
    }

    private void showVehicleTypes() throws BusinessException {
        CourseCrudService cs = Factory.service.forCourseCrudService();
        List<VehicleTypeDto> mechanics = cs.findAllVehicleTypes();
        Console.println("List of vehicle types");
        mechanics.forEach((vt) -> Printer.printVehicleType(vt));
    }

    private void showMechanics() throws BusinessException {
        CourseAttendanceService cs = Factory.service.forCourseAttendanceService();
        List<MechanicDto> mechanics = cs.findAllActiveMechanics();
        Console.println("List of mechanics");
        mechanics.forEach((m) -> Printer.printMechanic(m));
    }

}
