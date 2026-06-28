package main.java.com.view.panels.jwt;

import main.java.com.controller.DecodeController;
import main.java.com.model.core.JwtProcessor;
import main.java.com.model.type.TokenType;
import main.java.com.view.panels.BaseDecodePanel;

public class JwtDecodePanel extends BaseDecodePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DecodeController controller;

	public JwtDecodePanel() {
		super(TokenType.JWT);
    	this.controller = new DecodeController(this, new JwtProcessor());
    }
	
	@Override
	public void onDecodeClick() {
		controller.handleDecode();
	}
}
