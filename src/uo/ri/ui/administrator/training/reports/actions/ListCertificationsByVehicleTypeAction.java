package uo.ri.ui.administrator.training.reports.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.CourseReportService;
import uo.ri.business.dto.CertificateDto;
import uo.ri.ui.administrator.Printer;

import java.util.List;


public class ListCertificationsByVehicleTypeAction implements Action {

    @Override
    public void execute() throws Exception {

        CourseReportService rs = Factory.service.forCourseReportService();
        List<CertificateDto> rows = rs.findCertificatedByVehicleType();

        Console.println("Certificates by vehicle type");
        rows.forEach(r ->
                Printer.printCertificateRow(r)
        );
    }

}
