## Pumpkin Oil Demo

Who is who and what is what? 

* Halloween Pumpkin Oil Company (HPOC): sells pumpkin oil 
	- Alice: managing director of HPOC
		- defines trust policy
		- verifies electronic transactions
* Pumpkin Oil Federation (POF): federation, which provides networking between trustworthy producers and consumers around the world.
	- POF operates a Trust Scheme: TrustSchemePumpkinOilFederation
	- POF maintains trust list of trusted entities
	- POF maintains trust translation to trusted schemes
	    - TR
	    - eIDAS
* Buyers:
	- supermarket_1, member of POF scheme (uses cert signed by our POF CA)
	- Correos User, member of eIDAS scheme (uses cert signed by Correos eIDAS CA)
	- eSignature-SIGNER, member of TR scheme (uses cert signed by TUBITAK TR CA)


### Trust Policy:

Available in [./_work/policies](https://extgit.iaik.tugraz.at/LIGHTest/lightest-demo/tree/master/_work/policies).

TPL Editor (TPAT) available in [here](https://extgit.iaik.tugraz.at/LIGHTest/lightest-demo/tree/master/tpat)([Status](https://extgit.iaik.tugraz.at/LIGHTest/lightest-demo/issues/2)).


### ATV:

Available in [./atv](https://extgit.iaik.tugraz.at/LIGHTest/lightest-demo/tree/master/atv).


### TSPA:

TUG published the following on [tspa.tug.do.nlnetlabs.nl](https://tspa.tug.do.nlnetlabs.nl/tspa/):

| **claim**                                 | **scheme**                                | **TSL**                                                                           |
|-------------------------------------------|-------------------------------------------|-----------------------------------------------------------------------------------|
| eidas-ca.pof-demo.lightest.nlnetlabs.nl   | eidas.pof-demo.lightest.nlnetlabs.nl      | https://ec.europa.eu/information_society/policy/esignature/trusted-list/tl-mp.xml |
| TR-eSignature.lightest.nlnetlabs.nl       | tr-eidas-esignature.lightest.nlnetlabs.nl | https://mindertestbed.org:8443/tta/TR_eIDAS_eSignature_2019-12-05.xml             |
| company-ca.pof-demo.lightest.nlnetlabs.nl | federation.pof-demo.lightest.nlnetlabs.nl | https://lightest.iaik.tugraz.at/testschemes/Pumpkin_Demo_TS_v0.3-signed.xml       |

Published using [client -> PublishSchemes.java](https://extgit.iaik.tugraz.at/LIGHTest/lightest-demo/blob/master/_work/client/src/main/java/eu/lightest/demo/PublishSchemes.java).


### TTA: 

Trust Translation Authority provides the translation of any trust scheme level from eIDAS and TR to POF, based on a previous bilateral agreement. 

Available in [./_work/translations](https://extgit.iaik.tugraz.at/LIGHTest/lightest-demo/tree/master/_work/translations).

Published at _translation._trust.federation.pof-demo.lightest.nlnetlabs.nl.lightest.nlnetlabs.nl.

Hosted at [tta-lightest.eu:8445/ttaFM/mng/TrustTranslationDeclaration/federation.pof-demo.lightest.nlnetlabs.nl](https://tta-lightest.eu:8445/ttaFM/mng/TrustTranslationDeclaration/federation.pof-demo.lightest.nlnetlabs.nl).


### Orders:

Available in [./_work/orders](https://extgit.iaik.tugraz.at/LIGHTest/lightest-demo/tree/master/_work/orders).

Containers signed using [client -> CreateContainer.java](https://extgit.iaik.tugraz.at/LIGHTest/lightest-demo/blob/master/_work/client/src/main/java/eu/lightest/demo/CreateContainer.java).


### Others:

Simple verification client available in [client -> VerifyContainerTest.java](https://extgit.iaik.tugraz.at/LIGHTest/lightest-demo/blob/master/_work/client/src/test/java/eu/lightest/demo/VerifyContainerTest.java).
