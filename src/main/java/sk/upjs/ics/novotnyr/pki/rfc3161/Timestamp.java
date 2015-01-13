package sk.upjs.ics.novotnyr.pki.rfc3161;

import java.math.BigInteger;
import java.util.Date;

public class Timestamp {
	private String subjectCn;
	
	private String issuerCn;
	
	private BigInteger serialNumber;
	
	private Date timestamp;
	
	private String algorithmName;

	public String getIssuerCn() {
		return issuerCn;
	}

	public void setIssuerCn(String issuerCn) {
		this.issuerCn = issuerCn;
	}

	public BigInteger getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(BigInteger serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSubjectCn() {
		return subjectCn;
	}

	public void setSubjectCn(String subject) {
		this.subjectCn = subject;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	@Override
	public String toString() {
		return "Timestamp [subjectCn=" + subjectCn + ", issuerCn=" + issuerCn
				+ ", serialNumber=" + serialNumber + ", timestamp=" + timestamp
				+ ", algorithmName=" + algorithmName + "]";
	}
	
	
}
