package sk.upjs.ics.novotnyr.pki.rfc3161;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.logging.Logger;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.Store;

public class TimestampFactory {
	
	private static final Logger logger = Logger.getLogger(TimestampFactory.class.getName());
	
	private DigestAlgorithmResolver algorithmResolver = new DigestAlgorithmResolver();

	public Timestamp read(InputStream inputStream) throws IOException, TSPException {
		byte[] buffer = FileCopyUtils.copyToByteArray(inputStream);
		TimeStampResponse tsResponse = new TimeStampResponse(buffer);
		TimeStampToken timeStampToken = tsResponse.getTimeStampToken();

		Timestamp ts = new Timestamp();
		ts.setAlgorithmName(getHashAlgorithmName(timeStampToken));
		ts.setSubjectCn(getSubject(timeStampToken));
		ts.setIssuerCn(timeStampToken.getSID().getIssuer().toString());
		ts.setSerialNumber(timeStampToken.getTimeStampInfo().getSerialNumber());
		ts.setTimestamp(timeStampToken.getTimeStampInfo().getGenTime());
		
		return ts;
	}
	
	private String getSubject(TimeStampToken timeStampToken) {
		Store store = timeStampToken.getCertificates();
		Collection<Object> storedItems = store.getMatches(new AcceptAllSelector());
		if(storedItems.size() > 1) {
			logger.warning("More that 1 certificate is present in the TimeStampToken. Subject will be resolved from the 1st certificate!");
		}
		
		for (Object item : storedItems) {
			if(item instanceof X509CertificateHolder) {
				X509CertificateHolder certHolder = (X509CertificateHolder) item;
				
				return certHolder.getSubject().toString();
			}
		}
		
		return null;
	}

	protected String getHashAlgorithmName(TimeStampToken timeStampToken) {
		AlgorithmIdentifier hashAlgorithm = timeStampToken.getTimeStampInfo().getHashAlgorithm();
		return algorithmResolver.getAlgorithmName(hashAlgorithm.getAlgorithm());
	}
}
