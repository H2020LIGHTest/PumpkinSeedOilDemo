package eu.lightest.demo;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoScenarioTest {
    
    @Test
    public void scenario1a() {
        String prefix = "../../scenario1/";
        String policy = "policy0_pof.tpl";
        String container = "order_pof1.asice";
        
        verify(prefix, container, policy);
    }
    
    @Test
    public void scenario1b() {
        String prefix = "../../scenario1/";
        String policy = "policy1_pof.tpl";
        String container = "order_pof1.asice";
        
        verify(prefix, container, policy);
    }
    
    @Test
    public void scenario2() {
        String prefix = "../../scenario2/";
        String policy = "policy2_pof_withTranslation.tpl";
        String container = "order_tr1.asice";
        
        verify(prefix, container, policy);
    }
    
    @Test
    public void scenario3() {
        String prefix = "../../scenario3/";
        String policy = "policy3_pof_withTranslation_withPades.tpl";
        String container = "order_signed_backup.pdf";
        
        verify(prefix, container, policy);
    }
    
    public void verify(String prefix, String pathToContainer, String pathToPolicy) {
        boolean status = ContainerVerifier.verify(prefix + pathToContainer, prefix + pathToPolicy);
        
        assertTrue("PreChecks FAILED, but expected to PASS", ContainerVerifier.prechecksPassed());
        assertTrue("Container Validation FAILED, but expected to SUCCEEDED", status);
    }
    
}