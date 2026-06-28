package main.java.com.view.panels.jwt;

import main.java.com.controller.BruteForceController;
import main.java.com.model.core.JwtProcessor;
import main.java.com.view.panels.BaseBruteForcePanel;

public class JwtBruteForcePanel extends BaseBruteForcePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BruteForceController controller;
	
	public JwtBruteForcePanel() {
		this.controller = new BruteForceController(this, new JwtProcessor());
	}

	@Override
	public void onAttackClick() { controller.handleBruteForce(); }
}
