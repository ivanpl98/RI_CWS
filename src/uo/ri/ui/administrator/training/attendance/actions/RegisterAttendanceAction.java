package uo.ri.ui.administrator.training.attendance.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.CourseAttendanceService;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.exception.BusinessException;

public class RegisterAttendanceAction implements Action {

    private AttendanceUserInteractor user = new AttendanceUserInteractor();

    @Override
    public void execute() throws BusinessException {

        // Ask the user for data
        EnrollmentDto att = new EnrollmentDto();
        user.fill(att);

        // Invoke the service
        CourseAttendanceService cs = Factory.service.forCourseAttendanceService();
        cs.registerNew(att);

        // Show result
        Console.println("Attendance registered:" + att.id);
    }

}
