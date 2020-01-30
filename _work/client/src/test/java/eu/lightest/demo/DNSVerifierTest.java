package eu.lightest.demo;

import eu.lightest.verifier.exceptions.DNSException;
import eu.lightest.verifier.wrapper.DNSHelper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class DNSVerifierTest {
    
    public static final String TYPE_SMIMEA = "SMIMEA";
    public static final String TYPE_URI = "URI";
    public static final String TYPE_PTR = "PTR";
    
    private static DNSHelper dns;
    
    @Parameterized.Parameter(0)
    public String type;
    
    @Parameterized.Parameter(1)
    public String hostname;
    
    @BeforeClass
    public static void init() throws IOException {
        dns = new DNSHelper(DNSHelper.DNS_CLOUDFLARE1);
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
    public void verify() throws IOException, DNSException {
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
}
