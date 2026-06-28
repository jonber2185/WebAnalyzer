package main.java.com.controller;

import javax.swing.JOptionPane;

import main.java.com.model.core.TokenProcessor;
import main.java.com.model.dto.TokenDecodeResult;
import main.java.com.view.panels.BaseDecodePanel;
import main.java.com.view.utils.TokenPrint;

public class DecodeController {
	private BaseDecodePanel view;
	private TokenProcessor processor;
	
	public DecodeController(BaseDecodePanel view, TokenProcessor processor) {
		this.view = view;
		this.processor = processor;
	}
	
	public void handleDecode() {
		try {
            String token = view.getToken();

            TokenDecodeResult result = processor.decode(token);
            if (result.isSuccess()) {
            	view.setResult(
            			TokenPrint.toPrettyJSON(result.getHeader()),
            			TokenPrint.toPrettyJSON(result.getPayload())
        			);
            } else throw new Exception(result.getMessage());
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(view, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
	}
}
