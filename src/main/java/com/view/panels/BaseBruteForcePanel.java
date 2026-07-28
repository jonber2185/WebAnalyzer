package main.java.com.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import main.java.com.view.components.ResultBox;
import main.java.com.view.components.TokenInputArea;
import main.java.com.view.utils.MyInputField;

public abstract class BaseBruteForcePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private TokenInputArea inputArea = new TokenInputArea();
	private MyInputField filePathField = new MyInputField("Select a file...");
    private JButton fileOpenBtn = new JButton("Find file");
    private JButton attackBtn = new JButton("Attack");
    private ResultBox resultBox = new ResultBox("Secret Key", 450, 50);

	public BaseBruteForcePanel() {
		setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JPanel centerContainer = new JPanel(new BorderLayout(0, 10));
        
        // input area
        centerContainer.add(inputArea, BorderLayout.CENTER);
        
        JPanel filePanel = new JPanel(new BorderLayout(5, 0));
        filePanel.setBorder(new TitledBorder("Dictionary File (Wordlist)"));
        filePathField.setEditable(false);
        filePathField.setFont(new Font("Pretendard", Font.PLAIN, 13));
        filePanel.add(filePathField, BorderLayout.CENTER);
        filePanel.add(fileOpenBtn, BorderLayout.EAST);
        
        centerContainer.add(filePanel, BorderLayout.SOUTH);
        add(centerContainer, BorderLayout.CENTER);
        
        // bottom
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        bottomPanel.add(attackBtn, BorderLayout.WEST);
        bottomPanel.add(resultBox, BorderLayout.EAST);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        fileOpenBtn.addActionListener(e -> openFileSelector());
        attackBtn.addActionListener(e -> onAttackClick());
	}
	
	private void openFileSelector() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }
	
	public abstract void onAttackClick();
	
	public String getFilePath() { return filePathField.getText(); }
	public String getToken() { return inputArea.getText(); }
	
	public void setResult(String secret, Color color) {
		resultBox.updateResult(secret, new Font("Pretendard", Font.PLAIN, 13), color);
	}
	
	public void setAttackButtonEnabled(boolean enabled) {
        attackBtn.setEnabled(enabled);
        fileOpenBtn.setEnabled(enabled);
    }
}
