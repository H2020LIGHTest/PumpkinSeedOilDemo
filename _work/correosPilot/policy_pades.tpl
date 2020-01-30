accept(Form) :-

	extract(Form, format, pades),

	extract(Form, certificate, ESeal),

	extract(ESeal, format, eIDAS_qualified_certificate),

	extract(ESeal, pubKey, SealPK),

	verify_signature(Form, SealPK),

	extract(ESeal, issuer, IssuerCertificate),

	trustschemeX(IssuerCertificate, eIDAS_qualified, TrustedTrustListEntry),

	extract(TrustedTrustListEntry, format, trustlist_entry),
	extract(TrustedTrustListEntry, pubKey, PkIss),
	verify_signature(ESeal, PkIss).



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
