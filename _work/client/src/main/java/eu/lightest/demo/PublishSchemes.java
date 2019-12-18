package eu.lightest.demo;

import eu.lightest.verifier.wrapper.TSPAHelper;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PublishSchemes {
    
    public static List<SCHEME> schemes = new ArrayList<>();
    private static Logger logger = Logger.getLogger(PublishSchemes.class);
    private final String TSPA;
    
    public PublishSchemes(String tspa) {
        TSPA = tspa;
    }
    
    public static void printTable() {
        int col1 = 41;
        int col2 = 41;
        int col3 = 81;
        String pattern = "| %-" + col1 + "s | %-" + col2 + "s | %-" + col3 + "s |";
        
        System.out.println(String.format(pattern, "**claim**", "**scheme**", "**TSL**"));
        System.out.println(String.format(pattern, "", "", "").replace(" ", "-"));
        
        for(SCHEME scheme : schemes) {
            System.out.println(String.format(pattern, scheme.CLAIM, scheme.SCHEME, scheme.TSL));
        }
        
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
            logger.info("Claim published:   dig PTR _scheme._trust." + scheme.CLAIM);
            logger.info("Scheme published:  dig URI _scheme._trust." + scheme.SCHEME);
            logger.info(" pointing to list: " + scheme.TSL);
        } else {
            logger.error("Publishing failed for Scheme " + scheme.SCHEME);
        }
        
        return status;
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
            PublishSchemes.schemes.add(this);
        }
    }
    
}
