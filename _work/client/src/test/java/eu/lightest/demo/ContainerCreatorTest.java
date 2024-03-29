package eu.lightest.demo;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.security.cert.CertificateException;

import static org.junit.Assert.assertTrue;

public class ContainerCreatorTest {
    
    private static String eidas_cert = "../eidas/eidas-testcert.p12";
    private static String eidas_password = "7z6pwscEKeeBZnZS";
    private static String eidas_alias = null; // only has one privatekey
    
    private static String tr_cert = "../tr/tr-testcert.pfx";
    private static String tr_password = "123456";
    private static String tr_alias = "eSignature-SIGNER";
    
    private static String pof_cert = "../pof/pof__3_.pfx";
    private static String pof_password = "123456";
    private static String pof_alias = "supermarket_1";
    private static ContainerCreator creator;
    
    @BeforeClass
    public static void init() throws CertificateException, FileNotFoundException {
        creator = new ContainerCreator();
    }
    
    @Test
    public void create_order_eidas1() {
        
        boolean containerExists = creator.create("order_eidas1", eidas_cert, eidas_password, eidas_alias);
        
        assertTrue("Container not created ...", containerExists);
        
    }
    
    @Test
    public void create_order_eidas2_failing() {
        
        boolean containerExists = creator.create("order_eidas2_failing", eidas_cert, eidas_password, eidas_alias);
        
        
        assertTrue("Container not created ...", containerExists);
        
    }
    
    @Test
    public void create_order_tr1() {
        
        boolean containerExists = creator.create("order_tr1", tr_cert, tr_password, tr_alias);
        
        assertTrue("Container not created ...", containerExists);
        
    }
    
    @Test
    public void create_order_pof1() {
        
        boolean containerExists = creator.create("order_pof1", pof_cert, pof_password, pof_alias);
        
        assertTrue("Container not created ...", containerExists);
        
    }
}