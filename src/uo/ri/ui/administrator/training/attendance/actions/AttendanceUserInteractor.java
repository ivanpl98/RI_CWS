package uo.ri.ui.administrator.training.attendance.actions;

import alb.util.console.Console;
import uo.ri.business.CourseAttendanceService;
import uo.ri.business.dto.*;
import uo.ri.business.exception.BusinessException;
import uo.ri.ui.administrator.Printer;
import uo.ri.ui.conf.Factory;

import java.util.List;

public class AttendanceUserInteractor {

    public void fill(EnrollmentDto att) throws BusinessException {
        fillCourseId(att);
        fillMechanicId(att);
        fillAttendance(att);
        fillPassed(att);
    }

    private void fillAttendance(EnrollmentDto att) {
        att.attendance = Console.readInt("% of attendance");
    }

    private void fillMechanicId(EnrollmentDto att) throws BusinessException {
        showMechanics();
        att.mechanicId = Console.readString("Mechanic id");
    }

    private void fillCourseId(EnrollmentDto att) throws BusinessException {
        showCourses();
        att.courseId = Console.readString("Course id");
    }

    private void fillPassed(EnrollmentDto att) {
        att.passed = Console.readString("Passed (y/n)?").equalsIgnoreCase("y");
    }

    private void showMechanics() throws BusinessException {
        CourseAttendanceService cs = Factory.service.forCourseAttendanceService();
        List<MechanicDto> mechanics = cs.findAllActiveMechanics();
        Console.println("List of mechanics");
        mechanics.forEach((m) -> Printer.printMechanic(m));
    }

    public void showCourses() throws BusinessException {
        CourseAttendanceService cs = Factory.service.forCourseAttendanceService();
        List<CourseDto> mechanics = cs.findAllActiveCourses();
        Console.println("List of courses");
        mechanics.forEach((c) -> Printer.printCourse(c));
    }

    public Long askForCourseId() throws BusinessException {
        showCourses();
        return Console.readLong("Course id");
    }

}
