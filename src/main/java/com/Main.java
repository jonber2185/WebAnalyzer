package main.java.com;

import main.java.com.view.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class Main {

	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("FlatLaf 적용 실패: " + ex.getMessage());
        }
		
		SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
	}
}
