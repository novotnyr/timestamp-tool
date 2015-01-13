package sk.upjs.ics.novotnyr.pki.rfc3161;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopyUtils {

	private static final int BUFFER_SIZE = 4096;

	public static byte[] copyToByteArray(InputStream inputStream) throws IOException {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream(BUFFER_SIZE);
		copy(inputStream, bytes);
		return bytes.toByteArray();
	}

	public static int copy(InputStream in, OutputStream out) throws IOException {
		if(in == null) {
			throw new IllegalArgumentException("InputStream cannot be null");
		}
		if(out == null) {
			throw new IllegalArgumentException("OutputStream cannot be null");
		}
		int byteCount = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = in.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
			byteCount += bytesRead;
		}
		out.flush();
		return byteCount;
	}	
}
