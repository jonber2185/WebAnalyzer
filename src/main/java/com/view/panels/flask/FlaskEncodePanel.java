package main.java.com.view.panels.flask;

import main.java.com.controller.EncodeController;
import main.java.com.model.core.FlaskProcessor;
import main.java.com.model.type.TokenType;
import main.java.com.view.panels.BaseEncodePanel;

public class FlaskEncodePanel extends BaseEncodePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EncodeController controller;
	
    public FlaskEncodePanel() {
    	super(TokenType.FLASK);
    	this.controller = new EncodeController(this, new FlaskProcessor());
    }
    
    @Override
    public void onEncodeClick() {
    	controller.handleEncode();
    }
}
