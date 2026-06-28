package main.java.com.view.panels.jwt;

import main.java.com.controller.EncodeController;
import main.java.com.model.core.JwtProcessor;
import main.java.com.model.type.TokenType;
import main.java.com.view.panels.BaseEncodePanel;

public class JwtEncodePanel extends BaseEncodePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EncodeController controller;
    
    public JwtEncodePanel() {
    	super(TokenType.JWT);
    	this.controller = new EncodeController(this, new JwtProcessor());
    }

	@Override
	public void onEncodeClick() {
		controller.handleEncode();
	}
    
}
