package main.java.com.view.panels.flask;

import javax.swing.JPanel;

import main.java.com.view.panels.TokenPanel;

public class FlaskPanel extends TokenPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FlaskPanel() { super(); }

	@Override
	public JPanel encode() { return new FlaskEncodePanel(); }

	@Override
	public JPanel decode() { return new FlaskDecodePanel(); }

	@Override
	public JPanel bruteForce() { return new FlaskBruteForcePanel(); }
}
