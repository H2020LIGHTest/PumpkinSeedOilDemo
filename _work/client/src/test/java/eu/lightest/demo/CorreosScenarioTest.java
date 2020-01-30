package eu.lightest.demo;

import eu.lightest.verifier.ATVConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class CorreosScenarioTest {
    
    public static final String ATV = "https://atvapi.tug.do.nlnetlabs.nl/atvapi";
    
    @Parameterized.Parameter(0)
    public boolean runLocal;
    
    @Parameterized.Parameter(1)
    public String testName;
    
    @BeforeClass
    public static void setupClass() throws ConfigurationException {
        ATVConfiguration.init();
    }
    
    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> data() {
        List data = new ArrayList();
        
        data.add(new Object[]{true, "LOCAL"});
        data.add(new Object[]{false, "REMOTE"});
        
        return data;
    }
    
    @Test
    public void CORREOSeSignature() {
        String prefix = "../correosPilot/";
        String policy = "policy_pades.tpl";
        String container = "correos_esignature.pdf";
        
        verify(prefix, container, policy);
    }
    
    @Test
    public void CORREOSeSeal() {
        String prefix = "../correosPilot/";
        String policy = "policy_pades.tpl";
        String container = "correos_eseal.pdf";
        
        verify(prefix, container, policy);
    }
    
    @Test
    public void TUGeSignature() {
        String prefix = "../correosPilot/";
        String policy = "policy_pades.tpl";
        String container = "tug_esignature.pdf";
        
        verify(prefix, container, policy);
    }
    
    @Test
    public void TUGeSeal() {
        String prefix = "../correosPilot/";
        String policy = "policy_pades.tpl";
        String container = "tug_eseal.pdf";
        
        verify(prefix, container, policy);
    }
    
    public void verify(String prefix, String pathToContainer, String pathToPolicy) {
        boolean status = ContainerVerifier.verify(prefix + pathToContainer, prefix + pathToPolicy, runLocal ? null : ATV);
        
        assertTrue("PreChecks FAILED, but expected to PASS", ContainerVerifier.prechecksPassed());
        assertTrue("Container Validation FAILED, but expected to SUCCEED.", status);
        
        System.out.println((runLocal ? "LOCAL" : "REMOTE") + " ATV done!");
    }
}
