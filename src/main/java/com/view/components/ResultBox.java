package main.java.com.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import main.java.com.view.utils.MyInput;

public class ResultBox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyInput resultArea = new MyInput();
	
	public ResultBox(String title, int width, int height) {
		resultArea.setMargin(new Insets(5, 5, 5, 5));
		resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setTabSize(2);
        
        // label
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(new TitledBorder(title));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(width, height));
        resultPanel.add(scrollPane, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.CENTER);
	}
	
	public void updateResult(String text, Font font, Color textColor) {
		resultArea.setText(text);
		resultArea.setFont(font);
		resultArea.setForeground(textColor);
	}
}
