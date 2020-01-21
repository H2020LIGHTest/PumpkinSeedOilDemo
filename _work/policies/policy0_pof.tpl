accept(Form) :-
  extract(Form, format, pumpkinSeedOil),

  extract(Form, certificate, Certificate),
  extract(Certificate, format, x509cert),

  extract(Certificate, pubKey, PK),
  verify_signature(Form, PK),

  check_qualified(Certificate).


check_qualified(Certificate) :-
  extract(Certificate, issuer, IssuerCert),

  extract(IssuerCert, trustScheme, Claim),
  trustscheme(Claim, pumpkin_Oil_Federation),
  trustlist(Claim, IssuerCert, TrustListEntry),

  extract(TrustListEntry, format, trustlist_entry),

  extract(TrustListEntry, pubKey, PkIss),
  verify_signature(Certificate, PkIss).

