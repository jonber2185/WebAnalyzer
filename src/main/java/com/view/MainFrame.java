package main.java.com.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import main.java.com.view.panels.IdentifierPanel;
import main.java.com.view.panels.flask.FlaskPanel;
import main.java.com.view.panels.jwt.JwtPanel;
import main.java.com.view.panels.timestamp.TimestampPanel;
import main.java.com.view.utils.WindowSizable;

public class MainFrame extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane = new JTabbedPane();
	public static final Dimension DEFAULT_SIZE = new Dimension(600, 400);
	
	public MainFrame() {
		setTitle("WEB Analyzer");
		setSize(new Dimension(600, 300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // center
		setResizable(false); // resize X
 
		tabbedPane = new JTabbedPane();
 
		addTab("Identifier", new IdentifierPanel());
		addTab("JSON Web Token (JWT)", new JwtPanel());
		addTab("Flask Session Token", new FlaskPanel());
		addTab("Timestamp", new TimestampPanel());
 
		add(tabbedPane);
 
		// 바깥 탭 전환 감지
		tabbedPane.addChangeListener(e -> updateFrameSize());
	}
	
	private void addTab(String title, JPanel panel) {
		tabbedPane.addTab(title, panel);
		if (panel instanceof WindowSizable) {
			((WindowSizable) panel).addSizeChangeListener(this::updateFrameSize);
		}
	}
 
	private void updateFrameSize() {
		Component selected = tabbedPane.getSelectedComponent();
		Dimension size = (selected instanceof WindowSizable)
				? ((WindowSizable) selected).getWindowSize()
				: DEFAULT_SIZE;
 
		setSize(size);
	}
}
