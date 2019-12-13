package eu.lightest.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class VerifyContainerTest {
    
    private static Object pathPolicy = "../policies/policy_pof_withTranslation.tpl";
    
    @Parameterized.Parameter(0)
    public String container;
    
    @Parameterized.Parameter(1)
    public String policy;
    
    @Parameterized.Parameters(name = "{0}")
    public static List<Object> data() {
        List<Object> data = new ArrayList<>();
        
        final FilenameFilter filter = (File dir, String name) ->
                name.endsWith(".asic") || name.endsWith(".asice") || name.endsWith(".asics");
        
        File container = new File(CreateContainer.orders);
        for(File file : container.listFiles(filter)) {
            data.add(new Object[]{file.getPath(), pathPolicy});
        }
        
        return data;
    }
    
    @Test
    public void verifyContainer() {
        boolean status = VerifyContainer.verify(container, policy);
        
        if(this.container.contains("failing")) {
            assertFalse(status);
        } else {
            assertTrue(status);
        }
    }
    
}