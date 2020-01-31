#!/bin/bash

driller() {
  RES=$(drill -D $2 $3 @$1 | grep ";; flags: ")
  if [[ $RES == *"ad"* ]]; then
    echo "OK         drill -D $2 $3 @$1"
  else
    echo "FAIL       drill -D $2 $3 @$1"
  fi
}

driller 1.1.1.1 PTR _scheme._trust.company-ca.pof-demo.lightest.nlnetlabs.nl
driller 1.1.1.1 PTR _scheme._trust.company-ca.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 PTR _scheme._trust.company-ca.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 PTR _scheme._trust.company-ca.pof-demo.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 URI _scheme._trust.federation.pof-demo.lightest.nlnetlabs.nl
driller 1.1.1.1 URI _scheme._trust.federation.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 URI _scheme._trust.federation.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 URI _scheme._trust.federation.pof-demo.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 SMIMEA _scheme._trust.federation.pof-demo.lightest.nlnetlabs.nl
driller 1.1.1.1 SMIMEA _scheme._trust.federation.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 SMIMEA _scheme._trust.federation.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 SMIMEA _scheme._trust.federation.pof-demo.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 PTR _scheme._trust.TR-eSignature.lightest.nlnetlabs.nl
driller 1.1.1.1 PTR _scheme._trust.TR-eSignature.lightest.nlnetlabs.nl
driller 8.8.8.8 PTR _scheme._trust.TR-eSignature.lightest.nlnetlabs.nl
driller 8.8.8.8 PTR _scheme._trust.TR-eSignature.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 URI _scheme._trust.tr-eidas-esignature.lightest.nlnetlabs.nl
driller 1.1.1.1 URI _scheme._trust.tr-eidas-esignature.lightest.nlnetlabs.nl
driller 8.8.8.8 URI _scheme._trust.tr-eidas-esignature.lightest.nlnetlabs.nl
driller 8.8.8.8 URI _scheme._trust.tr-eidas-esignature.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 SMIMEA _scheme._trust.tr-eidas-esignature.lightest.nlnetlabs.nl
driller 1.1.1.1 SMIMEA _scheme._trust.tr-eidas-esignature.lightest.nlnetlabs.nl
driller 8.8.8.8 SMIMEA _scheme._trust.tr-eidas-esignature.lightest.nlnetlabs.nl
driller 8.8.8.8 SMIMEA _scheme._trust.tr-eidas-esignature.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 PTR _scheme._trust.test-scheme.lightest.nlnetlabs.nl
driller 1.1.1.1 PTR _scheme._trust.test-scheme.lightest.nlnetlabs.nl
driller 8.8.8.8 PTR _scheme._trust.test-scheme.lightest.nlnetlabs.nl
driller 8.8.8.8 PTR _scheme._trust.test-scheme.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 URI _scheme._trust.eidas.lightest.nlnetlabs.nl
driller 1.1.1.1 URI _scheme._trust.eidas.lightest.nlnetlabs.nl
driller 8.8.8.8 URI _scheme._trust.eidas.lightest.nlnetlabs.nl
driller 8.8.8.8 URI _scheme._trust.eidas.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 SMIMEA _scheme._trust.eidas.lightest.nlnetlabs.nl
driller 1.1.1.1 SMIMEA _scheme._trust.eidas.lightest.nlnetlabs.nl
driller 8.8.8.8 SMIMEA _scheme._trust.eidas.lightest.nlnetlabs.nl
driller 8.8.8.8 SMIMEA _scheme._trust.eidas.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 PTR _scheme._trust.eidas-ca.pof-demo.lightest.nlnetlabs.nl
driller 1.1.1.1 PTR _scheme._trust.eidas-ca.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 PTR _scheme._trust.eidas-ca.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 PTR _scheme._trust.eidas-ca.pof-demo.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 URI _scheme._trust.eidas.pof-demo.lightest.nlnetlabs.nl
driller 1.1.1.1 URI _scheme._trust.eidas.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 URI _scheme._trust.eidas.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 URI _scheme._trust.eidas.pof-demo.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 SMIMEA _scheme._trust.eidas.pof-demo.lightest.nlnetlabs.nl
driller 1.1.1.1 SMIMEA _scheme._trust.eidas.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 SMIMEA _scheme._trust.eidas.pof-demo.lightest.nlnetlabs.nl
driller 8.8.8.8 SMIMEA _scheme._trust.eidas.pof-demo.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 URI _translation._trust.federation.pof-demo.lightest.nlnetlabs.nl.lightest.nlnetlabs.nl
driller 1.1.1.1 URI _translation._trust.federation.pof-demo.lightest.nlnetlabs.nl.lightest.nlnetlabs.nl
driller 8.8.8.8 URI _translation._trust.federation.pof-demo.lightest.nlnetlabs.nl.lightest.nlnetlabs.nl
driller 8.8.8.8 URI _translation._trust.federation.pof-demo.lightest.nlnetlabs.nl.lightest.nlnetlabs.nl
echo ''
driller 1.1.1.1 SMIMEA _translation._trust.federation.pof-demo.lightest.nlnetlabs.nl.lightest.nlnetlabs.nl
driller 1.1.1.1 SMIMEA _translation._trust.federation.pof-demo.lightest.nlnetlabs.nl.lightest.nlnetlabs.nl
driller 8.8.8.8 SMIMEA _translation._trust.federation.pof-demo.lightest.nlnetlabs.nl.lightest.nlnetlabs.nl
driller 8.8.8.8 SMIMEA _translation._trust.federation.pof-demo.lightest.nlnetlabs.nl.lightest.nlnetlabs.nl
echo ''
