package eu.lightest.demo;

import at.tugraz.lightest.ASICCreator;
import org.apache.log4j.Logger;
import org.digidoc4j.Container;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.cert.CertificateException;

public class CreateContainer {
    
    public static String orders = "../orders/";
    private static Logger logger = Logger.getLogger(CreateContainer.class);
    
    private static ASICCreator creator;
    private static Container.DocumentType containerType = Container.DocumentType.ASICE;
    
    private static String eidas_cert = "../eidas/correos-certs_20190503.p12";
    private static String eidas_password = "7z6pwscEKeeBZnZS";
    private static String eidas_alias = null; // only has one privatekey
    
    private static String tr_cert = "../tr/tr_20191205.pfx";
    private static String tr_password = "123456";
    private static String tr_alias = "eSignature-SIGNER";
    
    private static String pof_cert = "../pof/pof__3_.pfx";
    private static String pof_password = "123456";
    private static String pof_alias = "supermarket_1";
    
    public static void main(String[] args) throws CertificateException, FileNotFoundException {
        creator = new ASICCreator();
        
        create("order_eidas1", eidas_cert, eidas_password, eidas_alias);
        create("order_eidas2_failing", eidas_cert, eidas_password, eidas_alias);
        create("order_tr1", tr_cert, tr_password, tr_alias);
        create("order_pof1", pof_cert, pof_password, pof_alias);
        
        logger.info("DONE!");
    }
    
    private static void create(String orderName, String pathToCert, String certPassword, String certAlias) {
        String orderPath = orders + orderName + ".xml";
        String containerPath = orders + orderName + ".asice";
        if(!checkFileExists(orderPath)) {
            return;
        }
        
        logger.info("Creating container for " + orderPath);
        final Container container = creator.createASic(orderPath, containerType, pathToCert, certPassword, certAlias);
        
        container.saveAsFile(containerPath);
        logger.info("Stored container at " + containerPath);
    }
    
    private static boolean checkFileExists(String orderPath) {
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
