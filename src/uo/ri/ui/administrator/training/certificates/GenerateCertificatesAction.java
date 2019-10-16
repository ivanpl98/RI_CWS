package uo.ri.ui.administrator.training.certificates;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.CertificateService;


public class GenerateCertificatesAction implements Action {

    @Override
    public void execute() throws Exception {

        Console.println("Generating certificates...");

        CertificateService cs = Factory.service.forCertificateService();
        int qty = cs.generateCertificates();

        Console.println(qty + " certificates generated");
    }

}
