package sk.upjs.ics.novotnyr.pki.rfc3161.gui;

public class Base64Formatter {
	public static final int DEFAULT_WRAP_SIZE = 64;
	
	public String wrapBase64Text(String base64, int wrapSize) {
		String normalizedText = base64
				.replace(" ", "")
				.replace("\n", "");
		StringBuilder sb = new StringBuilder(normalizedText);
		for(int i = 0; i < sb.length(); i++) {
			if(i % (wrapSize + 1) == 0) {
				sb.insert(i, "\n");
			}
		}
		sb.deleteCharAt(0);
		return sb.toString();
	}
}
