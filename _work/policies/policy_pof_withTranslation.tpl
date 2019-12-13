accept(Form) :-
  extract(Form, format, pumpkinSeedOil),

  extract(Form, purchaser, Buyer),
  extract(Buyer, name, BuyerName),
  print(BuyerName),

  authorize_order(Form),

  extract(Form, certificate, Certificate),
  extract(Certificate, format, x509cert),
  extract(Certificate, pubKey, PK),
  verify_signature(Form, PK),
  check_qualified(Certificate).


authorize_order(Form) :-
  extract(Form, item_id, ID),
   ID == 54678,
  extract(Form, amount, Amount),
   Amount <= 10,
  print(authorized_Order_for_Item_54678).

authorize_order(Form) :-
  extract(Form, item_id, ID),
   ID == 42,
  extract(Form, amount, Amount),
   Amount <= 10,
  print(authorized_Order_for_Item_42).

authorize_order(Form) :-
  extract(Form, item_id, ID),
   ID == 7,
  extract(Form, amount, Amount),
   Amount <= 100,
  print(authorized_Order_for_Item_7).


check_qualified(Certificate) :-
  extract(Certificate, issuer, IssuerCertificate),

  trustschemeX(IssuerCertificate, pumpkin_Oil_Federation, TrustListEntry),
  extract(TrustListEntry, format, trustlist_entry),

  print(checking_Trust_Status),
  verify_service_type(TrustListEntry),

  extract(TrustListEntry, pubKey, PkIss),
  verify_signature(Certificate, PkIss).


trustschemeX(IssuerCert, TrustedScheme, TrustListEntry) :-
  print(trustschemeX_without_Translation_START),
  extract(IssuerCert, trustScheme, Claim),
  trustscheme(Claim, TrustedScheme),
  trustlist(Claim, IssuerCert, TrustListEntry),
  print(trustschemeX_without_Translation_DONE).

trustschemeX(IssuerCert, TrustedScheme, TrustedTrustListEntry) :-
  print(trustschemeX_with_Translation_START),
  extract(IssuerCert, trustScheme, Claim),
  trustlist(Claim, IssuerCert, TrustListEntry),
  encode_translation_domain(Claim, TrustedScheme, TTAdomain),
  lookup(TTAdomain, TranslationEntry),

  translate(TranslationEntry, TrustListEntry, TrustedTrustListEntry),
  print(trustschemeX_with_Translation_DONE).


verify_service_type(TrustListEntry) :-
  extract(TrustListEntry, serviceType, qualified_certificate_authority),
  print(verified_ServiceType_as_Qualified_CA).

verify_service_type(TrustListEntry) :-
  extract(TrustListEntry, serviceType, qualified_pof_buyer),
  print(verified_ServiceType_as_Qualified_Buyer).
