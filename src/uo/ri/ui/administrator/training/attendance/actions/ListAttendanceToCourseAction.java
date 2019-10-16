package uo.ri.ui.administrator.training.attendance.actions;

import alb.util.menu.Action;
import uo.ri.business.CourseAttendanceService;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.ui.administrator.Printer;
import uo.ri.ui.conf.Factory;

import java.util.List;

public class ListAttendanceToCourseAction implements Action {

	private AttendanceUserInteractor user = new AttendanceUserInteractor();

	@Override
	public void execute() throws Exception {
		Long cId = user.askForCourseId();

		CourseAttendanceService s = Factory.service.forCourseAttendanceService();
		List<EnrollmentDto> attendance = s.findAttendanceByCourseId( cId );

		attendance.forEach( att -> Printer.printAttendingMechanic(att) );
	}

}
