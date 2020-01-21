# LIGHTest 💚 Pumpkin Seed Oil 💚 Demo

🇪🇺 LIGHTest demo for the EC review. 🇪🇺

Also known as WP3, WP4 and WP6 Cookbook demo.


## Repository Overview

* `atv`: JFX build of [AutomaticTrustVerifier-GUI](https://github.com/H2020LIGHTest/AutomaticTrustVerifier-GUI)
* `tpat`: JFX build of [TrustPolicyAuthoringTool](https://github.com/H2020LIGHTest/TrustPolicyAuthoringTool)
* `scenario1`: Transaction & Policy for [Scenario 1](#scenario-1)
* `scenario2`: Transaction & Policy for [Scenario 2](#scenario-2)
* `scenario3`: Transaction & Policy for [Scenario 3](#scenario-3)
* `usecase_simple`: another (unrelated) scenario
* `_work`: Certificates, keys, translations, other transactions & policies
    * `client`: ATV & ASiC-Creator client in Java
        * `SchemePublisherTest.java`: TSPA Client
        * `ContainerCreatorTest.java`: ASiC-Creator Client
        * `ContainerVerifierTest.java`: ATV Client
        * `DemoScenarioTest.java`: Client to verify files used in demos


## Demo Overview

#### Step 1-2: (Actor: Pumpkin Oil Federation, POF):

POF setup TSPA (including Trust List)

POF setup TTA (including rules)


#### Step 3 (Actor: 3 different buyers):

Create transactions for Alice to verify 


#### Step 4-5: (Actor: Alice the Verifier):

Create Trust Policies (using graphical editor, NL editor and TPL)  

Verify Transactions received via Mail (3 different transactions showing different certificates: POF, eIDAs, Turkish)


---

## Demo Script

* Scenario 1:
    * Buyer from POF Scheme wants to buy Oil
    * Directly trusted by Policy
* Scenario 2:
    * Buyer from eIDAS or TR want to buy Oil
    * Trusted via Trust Translation
* Scenario 3:
    * Policy extended to support Orders in PDF format


### Scenario 1

* Step 1: Briefly show publication of POF Scheme (via Python Script or Java Client)
* Step 3: Show `order_pof1.xml`
* Step 3: Show `order_pof1.asice`
* Step 4: Show `policy0_pof.tpl` (or create using TPAT)
* Step 5: Open ATV with `order_pof1.asice` and `policy0_pof.tpl`

### Scenario 2

* Step 2: Briefly show publication of Trust Translation (via Postman)
* Step 3: Show `order_tr1.xml`
* Step 3: Show `order_tr1.asice`
* Step 4: Show `policy2_pof_withTranslation.tpl`
* Step 5: Open ATV with `order_tr1.asice` and `policy2_pof_withTranslation.tpl`

### Scenario 3

* Step 3: Explain eID / Handysignatur
* Step 3: Show `order.pdf`
* Step 3: Use [PDF-Over](https://webstart.buergerkarte.at/pdf-over/) or [A-Trust](https://www.handy-signatur.at/hs2/#!sign/single) to sign `order.pdf`
* Step 4: Show `policy3_pof_withTranslation_withPades.tpl`
* Step 5: Open ATV with `order_signed.pdf` and `policy3_pof_withTranslation_withPades.tpl`


## Video Recordings

Only the following steps require Internet connection:

* Scenario 1, Step 1: Publish POF Trust Scheme
* Scenario 1, Step 5: Run ATV 
* Scenario 2, Step 2: Publish Trust Translation
* Scenario 2, Step 5: Run ATV
* Scenario 3, Step 3: Run PDF-Over
* Scenario 3, Step 5: Run ATV

