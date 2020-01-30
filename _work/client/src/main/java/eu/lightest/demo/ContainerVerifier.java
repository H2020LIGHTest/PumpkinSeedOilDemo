package eu.lightest.demo;

import eu.lightest.verifier.client.ATVClient;
import eu.lightest.verifier.client.LocalATVClient;
import eu.lightest.verifier.client.RemoteATVClient;
import eu.lightest.verifier.model.report.BufferedStdOutReportObserver;
import eu.lightest.verifier.model.report.Report;
import eu.lightest.verifier.model.report.StdOutReportObserver;

public class ContainerVerifier {
    
    private static ATVClient client;
    private static BufferedStdOutReportObserver reportBuffer;
    
    public static boolean verify(String pathToContainer, String pathToPolicy) {
        return verify(pathToContainer, pathToPolicy, null);
    }
    
    public static boolean verify(String pathToContainer, String pathToPolicy, String atvURL) {
        ATVClient client = getClient(atvURL);
        
        System.out.println("Container: " + pathToContainer);
        System.out.println("Policy:    " + pathToPolicy);
        
        boolean status = client.verify(pathToPolicy, pathToContainer);
        
        printReport();
        
        return status;
    }
    
    private static ATVClient getClient(String atvURL) {
        Report report = new Report();
        
        StdOutReportObserver reporter1 = new StdOutReportObserver();
        report.addObserver(reporter1);
        
        reportBuffer = new BufferedStdOutReportObserver();
        report.addObserver(reportBuffer);
        
        if(atvURL == null) {
            client = new LocalATVClient(report);
        } else {
            client = new RemoteATVClient(atvURL, report);
        }
        return client;
    }
    
    private static void printReport() {
        System.out.println();
        System.out.println(client.getClass().getSimpleName() + " REPORT: ");
        reportBuffer.print();
        reportBuffer.clearBuffer();
    }
    
    public static boolean prechecksPassed() {
        if(client != null && client instanceof RemoteATVClient) {
            return true;
        }
        return client != null && ((LocalATVClient) client).prechecksPassed();
    }
}
