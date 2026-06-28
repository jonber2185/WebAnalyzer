package main.java.com.view.components;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;

import main.java.com.view.utils.MyInput;

import javax.swing.JScrollPane;

public class TokenInputArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyInput textArea;
	
	public TokenInputArea() {
		setLayout(new BorderLayout(0, 5));
		
		// input label
		JLabel label = new JLabel("토큰을 입력하세요:");
		label.setFont(new Font("Pretendard", Font.BOLD, 12));
		add(label, BorderLayout.NORTH);
		
		// input area
		textArea = new MyInput("", 2, 10);
		textArea.setFont(new Font("Pretendard", Font.PLAIN, 15));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
	}
	
	public String getText() { return textArea.getText().trim(); }
	
	public void setText(String text) { textArea.setText(text); }
}
