# LIGHTest Demo

🇪🇺 LIGHTest demo for the EC review. 🇪🇺


Plan: WP 3,4,5 and 6 Cookbook demo


#### Step 1-2: (Actor: Pumpkin Oil Federation, POF):

POF setup TSPA (including Trust List) (TUG: infrastructure, FHG: Trust List)

* [x]  Setup TSPA (Georg): https://tspa.tug.do.nlnetlabs.nl/tspa/api/v1/
* [ ]  ~~Setup special POF TSPA? (Georg, Stefan)~~
* [ ]  Prepare script to verify TSPA is working & reachable (Georg)
* [ ]  Create & publish POF TrustList (FHG)


POF setup TTA (including rules) (ATOS: infrastructure and translations)

* [ ]  Setup TTA for POF (ATOS)
* [ ]  Prepare script to verify TTA is working & reachable (ATOS)
* [ ]  Publish translation agreement for POF, e.g. from eIDAS & TR (ATOS)


#### Step 3-4: (Actor: Alice the Verifier):

Create Trust Policies (using graphical editor, NL editor and TPL)  (Lukas, Sven and DTU)

* [ ]  Prepate TPAT (Lukas, DTU)
* [ ]  Prepare demo policy, trusted = POF scheme (Sven, Lukas, DTU)


Verify Transactions received via Mail (3 different transactions showing different certificates (POF, eIDAs, Turkish)


* [x]  Prepare ATV (Stefan)
* [x]  Add sample policy for checking (Stefan)
* [ ]  Verify prepared demo policy (Lukas, Stefan)
 

#### Step 3.5 (Actor: 3 different buyers):

Create transactions for Alice to verify (certificates needed POF: sven, eidas: TUG, Turkish: Tubitak)

 * [ ]  Provide POF certs (Sven)
 * [x]  Provide eIDAS certs (TUG, using CORREOS test cert): [./certs/eidas_qualified](./certs/eidas_qualified)
 * [ ]  Provide TR certs (TUBITAK)
 * [ ]  Create different transactions for different schemes (?)
 * [ ]  Test transactions with prepared demo policy (?)




