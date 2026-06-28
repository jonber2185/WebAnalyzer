package main.java.com.view.components;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import main.java.com.view.utils.MyInput;

public class JsonInputArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyInput textArea;
	
	public JsonInputArea(String title, String initialText) {
        setLayout(new BorderLayout());
        
        textArea = new MyInput(initialText, 5, 15);
        textArea.setFont(new Font("Pretendard", Font.PLAIN, 13));
        textArea.setTabSize(2);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new TitledBorder(title));
        
        add(scrollPane, BorderLayout.CENTER);
    }

    public String getText() {
        return textArea.getText().trim();
    }

    public void setText(String text) {
        textArea.setText(text);
    }
}
