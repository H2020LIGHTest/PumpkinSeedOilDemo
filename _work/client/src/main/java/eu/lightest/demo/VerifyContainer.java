package eu.lightest.demo;

import eu.lightest.verifier.client.ATVClient;
import eu.lightest.verifier.client.LocalATVClient;
import eu.lightest.verifier.model.report.BufferedStdOutReportObserver;
import eu.lightest.verifier.model.report.Report;
import eu.lightest.verifier.model.report.StdOutReportObserver;

public class VerifyContainer {
    
    private static LocalATVClient client;
    private static BufferedStdOutReportObserver reportBuffer;
    
    public static boolean verify(String pathToContainer, String pathToPolicy) {
        ATVClient client = getClient();
        
        System.out.println("Container: " + pathToContainer);
        System.out.println("Policy:    " + pathToPolicy);
        
        boolean status = client.verify(pathToPolicy, pathToContainer);
        
        printReport();
        
        return status;
    }
    
    
    private static ATVClient getClient() {
        if(client != null) {
            return client;
        }
        
        Report report = new Report();
        
        StdOutReportObserver reporter1 = new StdOutReportObserver();
        report.addObserver(reporter1);
        
        reportBuffer = new BufferedStdOutReportObserver();
        report.addObserver(reportBuffer);
        
        client = new LocalATVClient(report);
        return client;
    }
    
    private static void printReport() {
        System.out.println("");
        System.out.println(client.getClass().getSimpleName() + " REPORT: ");
        reportBuffer.print();
        reportBuffer.clearBuffer();
    }
}
