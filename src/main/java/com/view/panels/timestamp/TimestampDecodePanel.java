package main.java.com.view.panels.timestamp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.java.com.controller.timestamp.TimestampDecodeController;
import main.java.com.view.components.ResultBox;
import main.java.com.view.utils.WindowSizable;

public class TimestampDecodePanel extends JPanel implements WindowSizable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TimestampDecodeController controller;
	
	private JTextField timestampField = new JTextField(20);
	private JComboBox<String> zoneComboBox = new JComboBox<>(new String[] {
			"UTC",
			"Asia/Seoul",
			"Asia/Tokyo",
			"Asia/Shanghai",
			"Europe/London",
			"Europe/Paris",
			"America/New_York",
			"America/Los_Angeles",
			"Australia/Sydney"
	});
	private JButton decodeBtn = new JButton("Convert");
	private ResultBox resultBox = new ResultBox("Decoded Time", 400, 33);
	 
	public TimestampDecodePanel() {
		// controller connect
		this.controller = new TimestampDecodeController(this);
 
		zoneComboBox.setEditable(false);
		zoneComboBox.setSelectedItem("UTC");
 
		timestampField.setPreferredSize(new Dimension(timestampField.getPreferredSize().width, 32));
		zoneComboBox.setPreferredSize(new Dimension(zoneComboBox.getPreferredSize().width, 32));
 
		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(15, 15, 15, 15));
 
		// input area
		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
 
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		JLabel timestampLabel = new JLabel("Timestamp");
		inputPanel.add(timestampLabel, gbc);
 
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		inputPanel.add(timestampField, gbc);
 
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		JLabel zoneLabel = new JLabel("Zone");
		inputPanel.add(zoneLabel, gbc);
 
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		inputPanel.add(zoneComboBox, gbc);
 
		add(inputPanel, BorderLayout.CENTER);
 
		// bottom
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		bottomPanel.add(decodeBtn, BorderLayout.WEST);
		bottomPanel.add(resultBox, BorderLayout.EAST);
 
		add(bottomPanel, BorderLayout.SOUTH);
 
		decodeBtn.addActionListener(e -> controller.handleDecode());
	}
 
	public String getTimestamp() { return timestampField.getText().trim(); }
 
	public String getZoneId() {
		Object selected = zoneComboBox.getSelectedItem();
		return selected == null ? "" : selected.toString();
	}
 
	public void setResult(String text) {
		resultBox.updateResult(text, new Font("Pretendard", Font.BOLD, 13), Color.BLACK);
	}

	
	@Override
	public Dimension getWindowSize() {
		return new Dimension(600, 350);
	}
}
