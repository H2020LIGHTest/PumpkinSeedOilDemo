accept(Form) :-
	extract(Form, format, theAuctionHouse2019),

	extract(Form, certificate, Certificate),
	extract(Certificate, format, eIDAS_qualified_certificate),

	extract(Certificate, pubKey, PK),
	verify_signature(Form, PK),

	extract(Certificate, issuer, IssuerCertificate),

	trustschemeX(IssuerCertificate, fantasyland_qualified, TrustedTrustListEntry),
	extract(TrustedTrustListEntry, format, trustlist_entry),

	extract(TrustedTrustListEntry, mouseApproved, yes),
	extract(TrustedTrustListEntry, sillinessLevel, 1),
	extract(TrustedTrustListEntry, sillinessLevel, Level),
	Level <= 3,
	
	extract(TrustedTrustListEntry, pubKey, PkIss),
	verify_signature(Certificate, PkIss).


trustschemeX(IssuerCert, TrustedScheme, TrustListEntry) :-
	extract(IssuerCert, trustScheme, Claim),
	trustscheme(Claim, TrustedScheme),
	trustlist(Claim, IssuerCert, TrustListEntry).

trustschemeX(IssuerCert, TrustedScheme, TrustedTrustListEntry) :-
	extract(IssuerCert, trustScheme, Claim),
	trustlist(Claim, IssuerCert, TrustListEntry),

	encode_translation_domain(Claim, TrustedScheme, TTAdomain),
	lookup(TTAdomain, TranslationEntry),

	translate(TranslationEntry, TrustListEntry, TrustedTrustListEntry).
