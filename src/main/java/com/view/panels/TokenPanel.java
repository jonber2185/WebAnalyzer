package main.java.com.view.panels;

import java.awt.*;
import javax.swing.*;

public abstract class TokenPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenPanel() {
        setLayout(new BorderLayout());
        JTabbedPane subTabPane = new JTabbedPane();

        subTabPane.addTab("encode", encode());
        subTabPane.addTab("decode", decode());
        subTabPane.addTab("brute-force", bruteForce());

        add(subTabPane, BorderLayout.CENTER);
    }
	
	public abstract JPanel encode();
	public abstract JPanel decode();
	public abstract JPanel bruteForce();
}
