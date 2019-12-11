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
  extract(Form, item_id, 54678),
  extract(Form, ammount, Ammount),
   Ammount <= 10,
  print(authorized_order_for_item_54678).

authorize_order(Form) :-
  extract(Form, item_id, 42),
  extract(Form, ammount, Ammount),
   Ammount <= 10,
  print(authorized_order_for_item_42).

authorize_order(Form) :-
  extract(Form, item_id, 7),
  extract(Form, ammount, Ammount),
   Ammount <= 100,
  print(authorized_order_for_item_7).


check_qualified(Certificate) :-
  extract(Certificate, issuer, IssuerCertificate),

  %trustschemeX(IssuerCertificate, eidas_qualified, TrustListEntry),
  trustschemeX(IssuerCertificate, pumpkin_Oil_Federation, TrustListEntry),
  extract(TrustListEntry, format, trustlist_entry),

  extract(TrustListEntry, serviceType, qualified_certificate_authority),
  print(verified_serviceType),

  extract(TrustListEntry, serviceAdditionalServiceInfo, for_esignatures),
  print(verified_signatureType),

  extract(TrustListEntry, pubKey, PkIss),
  verify_signature(Certificate, PkIss).


trustschemeX(IssuerCert, TrustedScheme, TrustListEntry) :-
  print(trustschemeX_without_translation_START),
  extract(IssuerCert, trustScheme, Claim),
  trustscheme(Claim, TrustedScheme),
  trustlist(Claim, IssuerCert, TrustListEntry),
  print(trustschemeX_without_translation_DONE).

trustschemeX(IssuerCert, TrustedScheme, TrustedTrustListEntry) :-
  print(trustschemeX_with_translation_START),
  extract(IssuerCert, trustScheme, Claim),
  trustlist(Claim, IssuerCert, TrustListEntry),
  encode_translation_domain(Claim, TrustedScheme, TTAdomain),
  lookup(TTAdomain, TranslationEntry),

  translate(TranslationEntry, TrustListEntry, TrustedTrustListEntry),
  print(trustschemeX_with_translation_DONE).