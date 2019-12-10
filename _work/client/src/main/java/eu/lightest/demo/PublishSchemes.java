package eu.lightest.demo;

import eu.lightest.verifier.wrapper.TSPAHelper;
import org.apache.log4j.Logger;

public class PublishSchemes {
    
    private static final String TSPA = "https://tspa.tug.do.nlnetlabs.nl/tspa/api/v1/";
    private static Logger logger = Logger.getLogger(PublishSchemes.class);
    
    public static void main(String[] args) {
        PublishSchemes publisher = new PublishSchemes();
        
        boolean statusEidas = publisher.publish(new SCHEME_EIDAS());
        //boolean statusTrEsig = publisher.publish(new SCHEME_TUBITAK_ESIG());
        //boolean statusPOF = publisher.publish(new SCHEME_POF());
        
    }
    
    public boolean publish(SCHEME scheme) {
        return publish(scheme, false);
    }
    
    public boolean publish(SCHEME scheme, boolean disablePostCheck) {
        logger.info("Publishing Scheme " + scheme.SCHEME + " ...");
        
        TSPAHelper tspaHelper = new TSPAHelper(TSPA, scheme.CLAIM, scheme.SCHEME, scheme.TSL);
        tspaHelper.setDisablePostCheck(disablePostCheck);
        
        boolean status = false;
        try {
            status = tspaHelper.publish();
        } catch(Exception e) {
            logger.error("", e);
        }
        
        if(status) {
            logger.info("Scheme published: " + scheme.SCHEME);
        } else {
            logger.error("Publishing failed for Scheme " + scheme.SCHEME);
        }
        
        return !status;
    }
    
    public static class SCHEME_TUBITAK_ESIG extends SCHEME {
        
        public SCHEME_TUBITAK_ESIG() {
            super("https://lightest.iaik.tugraz.at/testschemes/TR_eIDAS_eSignature_2019-11-27_TUG_signed.xml",
                    "TR-eSignature.pof-demo.lightest.nlnetlabs.nl",
                    "tr-eidas-esignature.pof-demo.lightest.nlnetlabs.nl");
        }
    }
    
    public static class SCHEME_POF extends SCHEME {
        
        public SCHEME_POF() {
            super("https://lightest.iaik.tugraz.at/testschemes/POF_TODO.xml",
                    "company-ca.pof-demo.lightest.nlnetlabs.nl",
                    "federation.pof-demo.lightest.nlnetlabs.nl");
        }
    }
    
    public static class SCHEME_EIDAS extends SCHEME {
        
        public SCHEME_EIDAS() {
            super("https://ec.europa.eu/information_society/policy/esignature/trusted-list/tl-mp.xml",
                    "test-scheme.pof-demo.lightest.nlnetlabs.nl",
                    "eidas.pof-demo.lightest.nlnetlabs.nl");
        }
    }
    
    public static abstract class SCHEME {
        
        /**
         * HTTPS URL to the Trust Status List.
         */
        public final String TSL;
        /**
         * Hostname of the Claim (used for PTR record).
         */
        public final String CLAIM;
        /**
         * Hostname of the Scheme (used for URI & SMIMEA records).
         */
        public final String SCHEME;
        
        public SCHEME(String TSL, String CLAIM, String SCHEME) {
            this.TSL = TSL;
            this.CLAIM = CLAIM;
            this.SCHEME = SCHEME;
        }
    }
}
