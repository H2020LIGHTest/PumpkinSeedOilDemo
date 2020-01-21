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
    private static final Function<String, Boolean> allowedToFail = (String containerFilename) -> containerFilename.contains("failing");
    private static final Function<String, Boolean> worksWithoutTransl = (String containerFilename) -> containerFilename.toLowerCase().contains("pof");
    
    private static Object pathPolicy0 = "../policies/policy0_pof.tpl";
    private static Object pathPolicy1 = "../policies/policy1_pof.tpl";
    private static Object pathPolicy2 = "../policies/policy2_pof_withTranslation.tpl";
    private static Object pathPolicy3 = "../policies/policy3_pof_withTranslation_withPades.tpl";
    @Parameterized.Parameter(0)
    public String container;
    
    @Parameterized.Parameter(1)
    public String policy;
    
    @Parameterized.Parameter(2)
    public String testName;
    
    @Parameterized.Parameter(3)
    public boolean wrongPolicyUsed;
    
    @Parameterized.Parameters(name = "{2}")
    public static List<Object> data() {
        List<Object> data = new ArrayList<>();
        
        
        File container = new File(ContainerCreator.orders);
        for(File file : container.listFiles(filterAsic)) {
            data.add(new Object[]{file.getPath(), pathPolicy0, "policy0:clear,   " + file.getName(), !worksWithoutTransl.apply(file.getPath())});
            data.add(new Object[]{file.getPath(), pathPolicy1, "policy1:simple,   " + file.getName(), !worksWithoutTransl.apply(file.getPath())});
            data.add(new Object[]{file.getPath(), pathPolicy2, "policy2:transl,   " + file.getName(), false});
            data.add(new Object[]{file.getPath(), pathPolicy3, "policy3:PADES, " + file.getName(), false});
        }
        for(File file : container.listFiles(filterPdf)) {
            data.add(new Object[]{file.getPath(), pathPolicy0, "policy0:clear,   " + file.getName(), true});
            data.add(new Object[]{file.getPath(), pathPolicy1, "policy1:simple,   " + file.getName(), true});
            data.add(new Object[]{file.getPath(), pathPolicy2, "policy2:transl,   " + file.getName(), true});
            data.add(new Object[]{file.getPath(), pathPolicy3, "policy3:PADES, " + file.getName(), false});
        }
        
        return data;
    }
    
    @Test
    public void verify() {
        boolean status = ContainerVerifier.verify(container, policy);
        
        assertTrue("PreChecks FAILED, but expected to PASS", ContainerVerifier.prechecksPassed());
        
        if(allowedToFail.apply(container) || wrongPolicyUsed) {
            assertFalse("Container Validation SUCCEEDED, but expected to FAIL", status);
        } else {
            assertTrue("Container Validation FAILED, but expected to SUCCEEDED", status);
        }
    }
    
}