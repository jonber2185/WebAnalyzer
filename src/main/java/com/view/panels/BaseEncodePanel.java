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
import javax.swing.border.TitledBorder;

import main.java.com.model.type.TokenType;
import main.java.com.view.components.JsonInputArea;
import main.java.com.view.components.ResultBox;
import main.java.com.view.utils.MyInputField;

public abstract class BaseEncodePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JsonInputArea headerArea = new JsonInputArea("Header (JSON)", "{\n\t\"alg\": \"HS256\",\n\t\"typ\": \"JWT\"\n}");
	private JsonInputArea payloadArea = new JsonInputArea("Payload (JSON)", "{\n\t\"sub\": \"1234567890\"\n}");
	private MyInputField secretField = new MyInputField("your-secret");
	
	private JButton encodeBtn = new JButton("ENCODE");
    private ResultBox resultBox;
    
    public BaseEncodePanel(TokenType type) {
    	setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // input payload
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        if (type == TokenType.JWT) inputPanel.add(headerArea);
        inputPanel.add(payloadArea);
        add(inputPanel, BorderLayout.CENTER);

        // bottom div, flex-direction : column
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        // input secret key
        JPanel secretPanel = new JPanel(new BorderLayout(5, 5));
        secretPanel.setBorder(new TitledBorder("Secret Key"));
        secretField.setFont(new Font("Pretendard", Font.PLAIN, 13));
        secretPanel.add(secretField, BorderLayout.CENTER);
        bottomPanel.add(secretPanel);

        // encode button
        bottomPanel.add(Box.createVerticalStrut(10));
        encodeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(encodeBtn);
        bottomPanel.add(Box.createVerticalStrut(10));
        
        // result Box
        if (type == TokenType.JWT) {
        	resultBox = new ResultBox("JSON Web Token (JWT)", 500, 100);
        } else if (type == TokenType.FLASK) {
        	resultBox = new ResultBox("Flask Session Token", 500, 100);
        }
        bottomPanel.add(resultBox);
        add(bottomPanel, BorderLayout.SOUTH);
        
        encodeBtn.addActionListener(e -> onEncodeClick());
    }
    
    public abstract void onEncodeClick();
    
    // getter
    public String getHeader() { return headerArea.getText(); }
    public String getPayload() { return payloadArea.getText(); }
    public String getSecret() { return secretField.getText(); }
    
    // setter
    public void setResult(String jwt, Color color) { 
    	resultBox.updateResult(jwt, new Font("Pretendard", Font.PLAIN, 14), color); 
	}

}
