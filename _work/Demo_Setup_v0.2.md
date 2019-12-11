## Pumpkin Oil Demo

Who is who and what is what? 
* Halloween Pumpkin Oil Company (HPOC): sells pumpkin oil 
	o Alice: managing director of HPOC
		- defines trust policy
		- verifies electronic transactions

* Pumpkin Oil Federation (POF): federation, which provides networking between trustworthy producers and consumers around the world.
	o POF operates a Trust Scheme: TrustSchemePumpkinOilFederation
	o POF maintains trust list of trusted entities
		- Bob: contact person at POF
		- e.g. mailto: bob@TrustSchemePumpkinOilFederation.example.com 
	o POF maintains trust translation to trusted schemes
	    - TR
	    - eIDAS

* Trust Service Providers (buyers)
	o SupermarketA, member of eIDAS scheme (uses cert signed by a eIDAS CA)
	o WholesalerB, member of TR scheme (uses cert signed by a TR CA)


    

### Trust Policy:

*todo*


### ATV:

Available in [./atv](https://extgit.iaik.tugraz.at/LIGHTest/lightest-demo/tree/master/atv).


### TSPA:

TUG published the following on [tspa.tug.do.nlnetlabs.nl](https://tspa.tug.do.nlnetlabs.nl/tspa/):

| **claim**                                 | **scheme**                                | **TSL**                                                                           |
|-------------------------------------------|-------------------------------------------|-----------------------------------------------------------------------------------|
| eidas-ca.pof-demo.lightest.nlnetlabs.nl   | eidas.pof-demo.lightest.nlnetlabs.nl      | https://ec.europa.eu/information_society/policy/esignature/trusted-list/tl-mp.xml |
| TR-eSignature.lightest.nlnetlabs.nl       | tr-eidas-esignature.lightest.nlnetlabs.nl | https://mindertestbed.org:8443/tta/TR_eIDAS_eSignature_2019-12-05.xml             |
| company-ca.pof-demo.lightest.nlnetlabs.nl | federation.pof-demo.lightest.nlnetlabs.nl | https://lightest.iaik.tugraz.at/testschemes/Pumpkin_Demo_TS_v0.2-signed.xml       |


### TTA: 

Trust Translation Authority provides the translation of any trust scheme level from POC to any third party one and/or vice versa, based on a previous bilateral agreement. 

*todo*
 
 
### Others:


