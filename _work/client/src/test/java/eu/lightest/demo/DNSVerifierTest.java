package eu.lightest.demo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.lightest.verifier.ATVConfiguration;
import eu.lightest.verifier.exceptions.DNSException;
import eu.lightest.verifier.wrapper.DNSHelper;
import eu.lightest.verifier.wrapper.HTTPSHelper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

@RunWith(Parameterized.class)
public class DNSVerifierTest {
    
    public static final boolean TEST_ONLY_DEFAULT = true;
    
    public static final String TYPE_SMIMEA = "SMIMEA";
    public static final String TYPE_URI = "URI";
    public static final String TYPE_PTR = "PTR";
    
    private static DNSHelper dnsCLOUDFLAIR;
    private static DNSHelper dnsGOOGLE;
    private static HTTPSHelper http;
    private static JsonParser jsonParser;
    private static DNSHelper dnsDefault;
    private static String defaultNS;
    
    @Parameterized.Parameter(0)
    public String type;
    
    @Parameterized.Parameter(1)
    public String hostname;
    
    @BeforeClass
    public static void init() throws IOException {
        defaultNS = ATVConfiguration.get().getString("dns_nameserver", "NO DEFAULT FOUND");
        dnsDefault = new DNSHelper();
        dnsCLOUDFLAIR = new DNSHelper(DNSHelper.DNS_CLOUDFLARE1);
        dnsGOOGLE = new DNSHelper(DNSHelper.DNS_GOOGLE1);
        http = new HTTPSHelper();
        jsonParser = new JsonParser();
    }
    
    @Parameterized.Parameters(name = "{0} {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {TYPE_PTR, "_scheme._trust.company-ca.pof-demo.lightest.nlnetlabs.nl"},
                {TYPE_URI, "_scheme._trust.federation.pof-demo.lightest.nlnetlabs.nl"},
                {TYPE_SMIMEA, "_scheme._trust.federation.pof-demo.lightest.nlnetlabs.nl"},
                
                {TYPE_PTR, "_scheme._trust.TR-eSignature.lightest.nlnetlabs.nl"},
                {TYPE_URI, "_scheme._trust.tr-eidas-esignature.lightest.nlnetlabs.nl"},
                {TYPE_SMIMEA, "_scheme._trust.tr-eidas-esignature.lightest.nlnetlabs.nl"},
                
                {TYPE_PTR, "_scheme._trust.test-scheme.lightest.nlnetlabs.nl"},
                {TYPE_URI, "_scheme._trust.eidas.lightest.nlnetlabs.nl"},
                {TYPE_SMIMEA, "_scheme._trust.eidas.lightest.nlnetlabs.nl"},
                
                {TYPE_PTR, "_scheme._trust.eidas-ca.pof-demo.lightest.nlnetlabs.nl"},
                {TYPE_URI, "_scheme._trust.eidas.pof-demo.lightest.nlnetlabs.nl"},
                {TYPE_SMIMEA, "_scheme._trust.eidas.pof-demo.lightest.nlnetlabs.nl"},
                
                {TYPE_URI, "_translation._trust.federation.pof-demo.lightest.nlnetlabs.nl.lightest.nlnetlabs.nl"},
                {TYPE_SMIMEA, "_translation._trust.federation.pof-demo.lightest.nlnetlabs.nl.lightest.nlnetlabs.nl"},
        });
    }
    
    @Test
    public void verifyDefault() throws IOException, DNSException {
        System.out.println("Using nameserver: " + defaultNS);
        
        DNSHelper dns = dnsDefault;
        switch(this.type) {
            case TYPE_PTR:
                dns.queryPTR(this.hostname);
                break;
            case TYPE_URI:
                dns.queryURI(this.hostname);
                break;
            case TYPE_SMIMEA:
                dns.querySMIMEA(this.hostname);
                break;
            default:
                assertTrue("Wrong type: " + type, false);
        }
    }
    
    @Test
    public void verifyCloudflair() throws IOException, DNSException {
        assumeTrue(TEST_ONLY_DEFAULT == false);
        
        DNSHelper dns = dnsCLOUDFLAIR;
        switch(this.type) {
            case TYPE_PTR:
                dns.queryPTR(this.hostname);
                break;
            case TYPE_URI:
                dns.queryURI(this.hostname);
                break;
            case TYPE_SMIMEA:
                dns.querySMIMEA(this.hostname);
                break;
            default:
                assertTrue("Wrong type: " + type, false);
        }
    }
    
    @Test
    public void verifyGoogle() throws IOException, DNSException {
        assumeTrue(TEST_ONLY_DEFAULT == false);
        
        DNSHelper dns = dnsGOOGLE;
        switch(this.type) {
            case TYPE_PTR:
                dns.queryPTR(this.hostname);
                break;
            case TYPE_URI:
                dns.queryURI(this.hostname);
                break;
            case TYPE_SMIMEA:
                dns.querySMIMEA(this.hostname);
                break;
            default:
                assertTrue("Wrong type: " + type, false);
        }
    }
    
    @Test
    public void verifyGoogleWeb() throws IOException {
        assumeTrue(TEST_ONLY_DEFAULT == false);
        assumeTrue(this.type == TYPE_PTR);
        
        String url = "https://dns.google.com/resolve?name=" + this.hostname + "&type=" + this.type;
        String res = http.get(new URL(url));
        final JsonObject data = jsonParser.parseString(res).getAsJsonObject();
        
        assertEquals("Status was not 0.", 0, data.get("Status").getAsInt());
        assertEquals("AD flag not TRUE", true, data.get("AD").getAsBoolean());
    }
    
    @Test
    public void verifyGoogleDrill() throws IOException, InterruptedException {
        assumeTrue(TEST_ONLY_DEFAULT == false);
        verifyDrill(DNSHelper.DNS_GOOGLE1);
    }
    
    @Test
    public void verifyCloudflairDrill() throws IOException, InterruptedException {
        assumeTrue(TEST_ONLY_DEFAULT == false);
        verifyDrill(DNSHelper.DNS_CLOUDFLARE1);
    }
    
    public void verifyDrill(String nameserver) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("drill", "-D", this.type, this.hostname, "@" + nameserver);
        
        Process process = processBuilder.start();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
        String line;
        while((line = reader.readLine()) != null) {
            System.out.println(line);
            if(line.startsWith(";; EDNS:")) {
                assertTrue("DO flag not found.", line.contains("flags: do"));
            } else if(line.startsWith(";; flags: ")) {
                assertTrue("AD flag not found.", line.contains("ad"));
            }
        }
        
        int exitCode = process.waitFor();
        assertEquals("Error during drilling ...", 0, exitCode);
    }
}
