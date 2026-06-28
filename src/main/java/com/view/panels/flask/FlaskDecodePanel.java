package main.java.com.view.panels.flask;

import main.java.com.controller.DecodeController;
import main.java.com.model.core.FlaskProcessor;
import main.java.com.model.type.TokenType;
import main.java.com.view.panels.BaseDecodePanel;

public class FlaskDecodePanel extends BaseDecodePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DecodeController controller;

	public FlaskDecodePanel() {
		super(TokenType.FLASK);
    	this.controller = new DecodeController(this, new FlaskProcessor());
    }
	
	@Override
	public void onDecodeClick() {
		controller.handleDecode();
	}
}
