package sk.upjs.ics.novotnyr.pki.rfc3161;

import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;

/**
 * Resolves an ASN.1 OID of the algorithm to descriptive algorithm name.
 * 
 * @see org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator
 */
public class DigestAlgorithmResolver {
	private final static Map<ASN1ObjectIdentifier, String> digestAlgs = new HashMap<ASN1ObjectIdentifier, String>();
	
	static {
	    digestAlgs.put(PKCSObjectIdentifiers.md2, "MD2");
	    digestAlgs.put(PKCSObjectIdentifiers.md4, "MD4");
	    digestAlgs.put(PKCSObjectIdentifiers.md5, "MD5");
	    digestAlgs.put(OIWObjectIdentifiers.idSHA1, "SHA1");
	    digestAlgs.put(NISTObjectIdentifiers.id_sha224, "SHA224");
	    digestAlgs.put(NISTObjectIdentifiers.id_sha256, "SHA256");
	    digestAlgs.put(NISTObjectIdentifiers.id_sha384, "SHA384");
	    digestAlgs.put(NISTObjectIdentifiers.id_sha512, "SHA512");
	    digestAlgs.put(TeleTrusTObjectIdentifiers.ripemd128, "RIPEMD128");
	    digestAlgs.put(TeleTrusTObjectIdentifiers.ripemd160, "RIPEMD160");
	    digestAlgs.put(TeleTrusTObjectIdentifiers.ripemd256, "RIPEMD256");
	    digestAlgs.put(CryptoProObjectIdentifiers.gostR3411,  "GOST3411");
	    digestAlgs.put(new ASN1ObjectIdentifier("1.3.6.1.4.1.5849.1.2.1"),  "GOST3411");
	}
	
	public String getAlgorithmName(ASN1ObjectIdentifier oid) {
		return digestAlgs.get(oid);
	}
}
