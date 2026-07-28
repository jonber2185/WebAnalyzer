package main.java.com.view.panels;

// controller
import main.java.com.controller.IdentifierController;

// swing pack
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// components
import main.java.com.view.components.ResultBox;
import main.java.com.view.components.TokenInputArea;
import main.java.com.view.utils.WindowSizable;

public class IdentifierPanel extends JPanel implements WindowSizable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IdentifierController controller;

	private TokenInputArea inputArea = new TokenInputArea();
    private JButton identifyBtn = new JButton("Token Analyze");
    private ResultBox resultBox = new ResultBox("Token Type", 300, 33);

    public IdentifierPanel() {
    	// controller connect
    	this.controller = new IdentifierController(this);
    	
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // input area
        add(inputArea, BorderLayout.CENTER);
        
        // bottom
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        bottomPanel.add(identifyBtn, BorderLayout.WEST);
        bottomPanel.add(resultBox, BorderLayout.EAST);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        identifyBtn.addActionListener(e -> controller.handleIdentify());
    }

    public String getToken() { return inputArea.getText(); }

    public void updateResult(String text, Color color) {
        resultBox.updateResult(text, new Font("Pretendard", Font.BOLD, 13), color);
    }

	@Override
	public Dimension getWindowSize() {
		return new Dimension(600, 300);
	}
}
