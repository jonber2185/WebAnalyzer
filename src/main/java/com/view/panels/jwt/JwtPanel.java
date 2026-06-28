package main.java.com.view.panels.jwt;

import javax.swing.JPanel;

import main.java.com.view.panels.TokenPanel;

public class JwtPanel extends TokenPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JwtPanel() { super(); }

	@Override
	public JPanel encode() { return new JwtEncodePanel(); }

	@Override
	public JPanel decode() { return new JwtDecodePanel(); }

	@Override
	public JPanel bruteForce() { return new JwtBruteForcePanel(); }

}
