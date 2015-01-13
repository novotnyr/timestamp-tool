package sk.upjs.ics.novotnyr.pki.rfc3161.gui;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.REMAINDER;
import static sk.upjs.ics.novotnyr.pki.rfc3161.gui.Base64Formatter.DEFAULT_WRAP_SIZE;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.bouncycastle.util.encoders.Base64;

import sk.upjs.ics.novotnyr.pki.rfc3161.Timestamp;
import sk.upjs.ics.novotnyr.pki.rfc3161.TimestampFactory;

public class TimestampTool extends JFrame {
	private Base64Formatter base64Formatter = new Base64Formatter();
	
	private JLabel lblSubjectCnLabel = new JLabel("Subject CN");

	private JTextField lblSubjectCn = new JTextField(30);

	private JLabel lblIssuerCnLabel = new JLabel("Issuer CN");
	
	private JTextField lblIssuerCn = new JTextField(30);

	private JLabel lblSerialNumberLabel = new JLabel("Serial Number");

	private JTextField lblSerialNumber = new JTextField(30);
	
	private JLabel lblTimestampLabel = new JLabel("Timestamp");

	private JTextField lblTimestamp = new JTextField(30);

	private JLabel lblAlgorithmNameLabel = new JLabel("Hash Algorithm");
		
	private JTextField lblAlgorithmName = new JTextField(30);
	
	private JTextArea txtBase64Timestamp = new JTextArea(10, 30);
	
	private JButton btnDisplay = new JButton("Display info");
	
	public TimestampTool() {
		setTitle("Timestamp Tool");
		
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        c.gridy = 0; c.gridx = 0; c.weightx = 0; add(lblTimestampLabel, c);
        c.gridy = 0; c.gridx = 1; c.weightx = 1; add(lblTimestamp, c);

        c.gridy = 1; c.gridx = 0; c.weightx = 0; add(lblAlgorithmNameLabel, c);
        c.gridy = 1; c.gridx = 1; c.weightx = 1; add(lblAlgorithmName, c);

        c.gridy = 2; c.gridx = 0; c.weightx = 0; add(lblSubjectCnLabel, c);
        c.gridy = 2; c.gridx = 1; c.weightx = 1; add(lblSubjectCn, c);
        
        c.gridy = 3; c.gridx = 0; c.weightx = 0; add(lblIssuerCnLabel, c);
        c.gridy = 3; c.gridx = 1; c.weightx = 1; add(lblIssuerCn, c);

        c.gridy = 4; c.gridx = 0; c.weightx = 0; add(lblSerialNumberLabel, c);
        c.gridy = 4; c.gridx = 1; c.weightx = 1; add(lblSerialNumber, c);



        c.gridy = 5; c.gridx = 0; 
        	c.gridwidth = REMAINDER;
        	c.fill = BOTH;
        	c.weighty = 1;
        	add(new JScrollPane(txtBase64Timestamp, 
        			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
        			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);
        Font currentFont = txtBase64Timestamp.getFont();
        txtBase64Timestamp.setFont(new Font("monospaced", currentFont.getStyle(), currentFont.getSize()));
        	
        Action action = txtBase64Timestamp.getActionMap().get("paste-from-clipboard");
        txtBase64Timestamp.getActionMap().put("paste-from-clipboard", new HandlePasteAction(action));

        c.fill = GridBagConstraints.HORIZONTAL;
        	
        c.gridy = 6; c.gridx = 0; 
        	c.gridwidth = REMAINDER;
        	c.weighty = 0;
        	add(btnDisplay, c);
        btnDisplay.setVisible(false);
	}
	
	protected class HandlePasteAction extends ProxyAction {

		public HandlePasteAction(Action action) {
			super(action);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			
			txtBase64Timestamp.setText(
					base64Formatter.wrapBase64Text(
							txtBase64Timestamp.getText(), DEFAULT_WRAP_SIZE));
			
			refresh();
		}
		
	}
	public void refresh() {
		try {
			TimestampFactory timestampFactory = new TimestampFactory();
			byte[] buf = Base64.decode(txtBase64Timestamp.getText());
			Timestamp timestamp = timestampFactory.read(new ByteArrayInputStream(buf));
			
			lblAlgorithmName.setText(timestamp.getAlgorithmName());
			lblIssuerCn.setText(timestamp.getIssuerCn());
			lblSerialNumber.setText(timestamp.getSerialNumber().toString());
			lblSubjectCn.setText(timestamp.getSubjectCn());
			lblTimestamp.setText(timestamp.getTimestamp().toString());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Cannot decode Base64 string: " + e.getMessage());
		} 
		
	}
	
	public void setBase64Formatter(Base64Formatter base64Formatter) {
		this.base64Formatter = base64Formatter;
	}
	
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TimestampTool timestampTool = new TimestampTool();
				timestampTool.setVisible(true);
				timestampTool.pack();			
			}
		});
	}
	
}
