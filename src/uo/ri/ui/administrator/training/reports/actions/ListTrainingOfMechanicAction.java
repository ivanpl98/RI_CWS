package uo.ri.ui.administrator.training.reports.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.CourseReportService;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.ui.administrator.Printer;

import java.util.List;

public class ListTrainingOfMechanicAction implements Action {

    private ReportsUserInteractor user = new ReportsUserInteractor();

    @Override
    public void execute() throws Exception {
        Long mId = user.askForMechanicId();

        CourseReportService rs = Factory.service.forCourseReportService();
        List<TrainingForMechanicRow> rows = rs.findTrainigByMechanicId(mId);

        Console.println("Training for mechanic report");
        printTotals(rows);

        Console.println("\n  - Breakdown by vehicle type -");
        rows.forEach((row) ->
                Printer.printTrainingForMechanic(row)
        );
    }

    private void printTotals(List<TrainingForMechanicRow> rows) {
        int totalEnrolledHours = 0;
        int totalAttendedHours = 0;
        for (TrainingForMechanicRow r : rows) {
            totalEnrolledHours += r.enrolledHours;
            totalAttendedHours += r.attendedHours;
        }

        Console.printf("\tTotal enrolled hours: %d\n", totalEnrolledHours);
        Console.printf("\tTotal attended hours: %d\n", totalAttendedHours);
    }

}
