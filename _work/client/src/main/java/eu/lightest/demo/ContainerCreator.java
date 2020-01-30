package eu.lightest.demo;

import at.tugraz.lightest.ASICCreator;
import org.apache.log4j.Logger;
import org.digidoc4j.Container;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.cert.CertificateException;

public class ContainerCreator {
    
    public static String orders = "../orders/";
    private static Logger logger = Logger.getLogger(ContainerCreator.class);
    private static Container.DocumentType containerType = Container.DocumentType.ASICE;
    private ASICCreator creator;
    
    
    public ContainerCreator() throws CertificateException, FileNotFoundException {
        creator = new ASICCreator();
    }
    
    public boolean create(String orderName, String pathToCert, String certPassword, String certAlias) {
        String orderPath = orders + orderName + ".xml";
        String containerPath = orders + orderName + ".asice";
        if(!checkFileExists(orderPath)) {
            logger.error("Does not exist: " + orderPath);
            return false;
        }
        
        if(checkFileExists(containerPath)) {
            logger.info("Container already exists. Deleting it first ...");
            File container = new File(containerPath);
            container.delete();
        }
        
        logger.info("Creating container for " + orderPath);
        final Container container = creator.createASic(orderPath, containerType, pathToCert, certPassword, certAlias);
        
        container.saveAsFile(containerPath);
        logger.info("Stored container at " + containerPath);
        
        return checkFileExists(containerPath);
    }
    
    private boolean checkFileExists(String orderPath) {
        File ordersFolder = new File(orders);
        if(!ordersFolder.exists() || ordersFolder.isFile()) {
            logger.error("Orders folder not found: " + orders);
            return false;
        }
        
        File orderFile = new File(orderPath);
        if(!orderFile.exists() || !orderFile.isFile()) {
            logger.error("Order not found: " + orderPath);
            return false;
        }
        return true;
    }
}
