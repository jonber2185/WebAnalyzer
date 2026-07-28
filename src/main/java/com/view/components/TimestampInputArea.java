package main.java.com.view.components;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.com.view.utils.MyInput;

public class TimestampInputArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyInput timestampField;
//	private JTextField timestampField = new JTextField(20);
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
	
	public TimestampInputArea() {
		setLayout(new BorderLayout(0, 5));
		
		JPanel inputPanel = new JPanel();
		zoneComboBox.setEditable(false);
		zoneComboBox.setSelectedItem("UTC");
		
		// input label
		JLabel label = new JLabel("Timestamp,:");
		label.setFont(new Font("Pretendard", Font.BOLD, 12));
		inputPanel.add(label, BorderLayout.NORTH);
		
		// input area
		timestampField = new MyInput("", 2, 10);
		timestampField.setFont(new Font("Pretendard", Font.PLAIN, 15));
		timestampField.setLineWrap(true);
		timestampField.setWrapStyleWord(true);
//		JScrollPane scrollPane = new JScrollPane(timestampField);
		inputPanel.add(timestampField, BorderLayout.CENTER);
		
		add(inputPanel, BorderLayout.NORTH);
		add(zoneComboBox, BorderLayout.CENTER);
	}
	
	public String getText() { return timestampField.getText().trim(); }
	
	public void setText(String text) { timestampField.setText(text); }
}
