package main.java.com.view.panels.timestamp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import main.java.com.controller.timestamp.TimestampEncodeController;
import main.java.com.view.components.ResultBox;
import main.java.com.view.utils.WindowSizable;

public class TimestampEncodePanel extends JPanel implements WindowSizable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TimestampEncodeController controller;
	
	private JTextField timeField = new JTextField(20);
 
	private JButton encodeBtn = new JButton("ENCODE");
 
	private ResultBox epochSecDecBox = new ResultBox("Epoch Seconds (dec)", 240, 60);
	private ResultBox epochSecHexBox = new ResultBox("Epoch Seconds (hex)", 240, 60);
	private ResultBox epochMillisDecBox = new ResultBox("Epoch Millis (dec)", 240, 60);
	private ResultBox epochMillisHexBox = new ResultBox("Epoch Millis (hex)", 240, 60);
 
	public TimestampEncodePanel() {
		// controller connect
		this.controller = new TimestampEncodeController(this);
 
		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(15, 15, 15, 15));
 
		// input area
		JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
		inputPanel.setBorder(new TitledBorder("Time"));
		timeField.setFont(new Font("Pretendard", Font.PLAIN, 13));
		inputPanel.add(timeField, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.CENTER);
 
		// bottom div, flex-direction : column
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
 
		// encode button
		encodeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomPanel.add(encodeBtn);
		bottomPanel.add(Box.createVerticalStrut(10));
 
		// result boxes (2 x 2)
		JPanel resultPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		resultPanel.add(epochSecDecBox);
		resultPanel.add(epochSecHexBox);
		resultPanel.add(epochMillisDecBox);
		resultPanel.add(epochMillisHexBox);
		bottomPanel.add(resultPanel);
 
		add(bottomPanel, BorderLayout.SOUTH);
 
		encodeBtn.addActionListener(e -> controller.handleEncode());
	}
 
	// getter
	public String getTimeText() { return timeField.getText(); }
 
	// setter
	public void setResult(String epochSecDec, String epochSecHex,
			String epochMillisDec, String epochMillisHex) {
		Font font = new Font("Pretendard", Font.PLAIN, 14);
		epochSecDecBox.updateResult(epochSecDec, font, Color.black);
		epochSecHexBox.updateResult(epochSecHex, font, Color.black);
		epochMillisDecBox.updateResult(epochMillisDec, font, Color.black);
		epochMillisHexBox.updateResult(epochMillisHex, font, Color.black);
	}

	@Override
	public Dimension getWindowSize() {
		return new Dimension(600, 430);
	}
}
