package main.java.com.view.panels.flask;

import main.java.com.controller.BruteForceController;
import main.java.com.model.core.FlaskProcessor;
import main.java.com.view.panels.BaseBruteForcePanel;

public class FlaskBruteForcePanel extends BaseBruteForcePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BruteForceController controller;

	public FlaskBruteForcePanel() {
		this.controller = new BruteForceController(this, new FlaskProcessor());
	}
	
	@Override
	public void onAttackClick() {
		controller.handleBruteForce();
	}
}
