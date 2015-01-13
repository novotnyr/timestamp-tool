package sk.upjs.ics.novotnyr.pki.rfc3161.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class ProxyAction extends AbstractAction {

    private Action delegateAction;

    public ProxyAction(Action action) {
        this.delegateAction = action;
    }

	public void actionPerformed(ActionEvent e) {
        getDelegateAction().actionPerformed(e);
	}
	
	protected Action getDelegateAction() {
		return this.delegateAction;
	}


}