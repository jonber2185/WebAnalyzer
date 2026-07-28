package main.java.com.controller;

import java.awt.Color;

import javax.swing.JOptionPane;

import main.java.com.model.core.JwtProcessor; // type 확인용
import main.java.com.model.core.TokenProcessor;
import main.java.com.model.dto.TokenEncodeResult;
import main.java.com.view.panels.BaseEncodePanel;

public class EncodeController {
	private BaseEncodePanel view;
	private TokenProcessor processor;
	
	public EncodeController(BaseEncodePanel view, TokenProcessor processor) {
		this.view = view;
		this.processor = processor;
	}
	
	public void handleEncode() {
        try {
            String headerJson = null;
            if (processor instanceof JwtProcessor) headerJson = view.getHeader();
            String payloadJson = view.getPayload();
            String secret = view.getSecret();

            TokenEncodeResult result = processor.encode(headerJson, payloadJson, secret);
            if (result.isSuccess()) {
            	view.setResult(result.getToken(), Color.BLACK);
            } else throw new Exception(result.getMessage());
        } catch (Exception e) {
        	view.setResult("ERROR OCCUR!\n" + e.getMessage(), Color.RED);
        	JOptionPane.showMessageDialog(view, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
