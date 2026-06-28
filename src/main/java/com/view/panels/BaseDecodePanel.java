package main.java.com.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.com.model.type.TokenType;
import main.java.com.view.components.ResultBox;
import main.java.com.view.components.TokenInputArea;

public abstract class BaseDecodePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TokenInputArea inputArea = new TokenInputArea();
	private JButton decodeBtn = new JButton("DECODE");
	private ResultBox headerArea;
    private ResultBox payloadArea = new ResultBox("Payload (JSON)", 250, 100);
    
    public BaseDecodePanel(TokenType type) {
    	String headerText = type == TokenType.FLASK ? "Timestamp" : "Header (JSON)"; 
    	this.headerArea = new ResultBox(headerText, 250, 100);
    	
		setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // input token
        add(inputArea, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        
        // decode button
        bottomPanel.add(Box.createVerticalStrut(10));
        decodeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(decodeBtn);
        bottomPanel.add(Box.createVerticalStrut(15));

        // result (header, payload)
        JPanel resultPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        resultPanel.add(headerArea);
        resultPanel.add(payloadArea);
        bottomPanel.add(resultPanel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
        
        decodeBtn.addActionListener(e -> onDecodeClick());
    }
    
    public abstract void onDecodeClick();

    public String getToken() { return inputArea.getText(); }
	
	public void setResult(String header, String payload) {
		headerArea.updateResult(header, new Font("Pretendard", Font.PLAIN, 14), Color.BLACK);
		payloadArea.updateResult(payload, new Font("Pretendard", Font.PLAIN, 14), Color.BLACK);
	}
}
