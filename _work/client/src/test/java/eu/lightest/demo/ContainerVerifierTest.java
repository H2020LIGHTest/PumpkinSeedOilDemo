package eu.lightest.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ContainerVerifierTest {
    
    private static final FilenameFilter filterAsic = (File dir, String name) -> name.endsWith(".asic") || name.endsWith(".asice") || name.endsWith(".asics");
    private static final FilenameFilter filterPdf = (File dir, String name) -> name.endsWith(".pdf");
    private static final Function<String, Boolean> allowedToFail = (String filename) -> filename.contains("failing");
    
    private static Object pathPolicy = "../policies/policy_pof_withTranslation.tpl";
    private static Object pathPolicyPades = "../policies/policy_pof_withTranslation_pades.tpl";
    @Parameterized.Parameter(0)
    public String container;
    
    @Parameterized.Parameter(1)
    public String policy;
    
    @Parameterized.Parameter(2)
    public String testName;
    
    @Parameterized.Parameters(name = "{2}")
    public static List<Object> data() {
        List<Object> data = new ArrayList<>();
        
        
        File container = new File(ContainerCreator.orders);
        for(File file : container.listFiles(filterAsic)) {
            data.add(new Object[]{file.getPath(), pathPolicy, "policy:XML   " + file.getName()});
            data.add(new Object[]{file.getPath(), pathPolicyPades, "policy:PADES " + file.getName()});
        }
        for(File file : container.listFiles(filterPdf)) {
            data.add(new Object[]{file.getPath(), pathPolicyPades, "policy:PADES " + file.getName()});
        }
        
        return data;
    }
    
    @Test
    public void verify() {
        boolean status = ContainerVerifier.verify(container, policy);
        
        assertTrue("PreChecks FAILED, but expected to PASS", ContainerVerifier.prechecksPassed());
        
        if(allowedToFail.apply(container)) {
            assertFalse("Container Validation SUCCEEDED, but expected to FAIL", status);
        } else {
            assertTrue("Container Validation FAILED, but expected to SUCCEEDED", status);
        }
    }
    
}