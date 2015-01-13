package sk.upjs.ics.novotnyr.pki.rfc3161;

import org.bouncycastle.util.Selector;

public class AcceptAllSelector implements Selector {

	public boolean match(Object obj) {
		return true;
	}

	@Override
	public Object clone() {
		return new AcceptAllSelector();
	}
}
