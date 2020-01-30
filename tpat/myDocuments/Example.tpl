accept(Form):-
    extract(Form,format,'pumpkinSeedOil')
    ,extract(Form,form,Form2)
    ,extract(Form2,certificate,Certificate)
    ,extract(Certificate,pubKey,PK)
    ,extract(Certificate,issuer,Issuer)
    ,extract(Issuer,trustScheme,TrustMemClaim)
    ,trustschemeX(Issuer,'pumpkin_Oil_Federation',TrustListEntry)
    ,extract(TrustListEntry,pubKey,PKIss)
    ,verify_signature(Certificate,PKIss)
    ,verify_signature(Form2,PK)
    .


trustschemeX(IssuerCert, TrustedScheme, TrustListEntry) :-
    extract(IssuerCert, trustscheme, Claim),
    trustlist(Claim, IssuerCert, TrustListEntry),
    trustscheme(Claim, TrustedScheme).

trustschemeX(IssuerCert, TrustedScheme, TrustedTrustListEntry) :-
    extract(IssuerCert, trustscheme, Claim),
    trustlist(Claim, IssuerCert, TrustListEntry),
    encode_translation_domain(Claim, TrustedScheme, TTAdomain),
    lookup(TTAdomain, TranslationEntry),
    translate(TranslationEntry, TrustListEntry, TrustedTrustListEntry).

checkMandate(Delegation, Transaction) :-
    extract(Delegation, proxyCert, ProxyCert),
    extract(ProxyCert, proxyKey, PkSig),
    verify_signature(Transaction, PkSig),
    extract(Delegation, mandatorCert, MandatorCert),
    extract(MandatorCert, mandatorKey, PkMandator),
    verify_signature(Delegation, PkMandator).

checkMandatorKey(Delegation,TrustScheme) :-
    extract(Delegation, mandatorCert, MandatorCert),
    extract(MandatorCert, trustScheme, TrustSchemeClaim),
    trustscheme(TrustSchemeClaim, TrustScheme),
    trustlist(TrustSchemeClaim, MandatorCert, TrustListEntry),
    extract(TrustListEntry, format, generic_trustlist_format),
    extract(TrustListEntry, pubKey, PkIss),
    verify_signature(MandatorCert, PkIss).

checkValidDelegation(Delegation) :-
    extract(Delegation, delegationProvider, DP),
    lookup(DP, DPEntry),
    extract(DPEntry, format, dp_format),
    extract(DPEntry, fingerprint, HMandate),
    verify_hash(Delegation, HMandate).

