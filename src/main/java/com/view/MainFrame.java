package main.java.com.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import main.java.com.view.panels.IdentifierPanel;
import main.java.com.view.panels.flask.FlaskPanel;
import main.java.com.view.panels.jwt.JwtPanel;

public class MainFrame extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainFrame() {
        setTitle("Token Analyzer");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center
        setResizable(false); // resize X
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Identifier", new IdentifierPanel());
        tabbedPane.addTab("JSON Web Token (JWT)", new JwtPanel());
        tabbedPane.addTab("Flask Session Token", new FlaskPanel());

        add(tabbedPane);
    }
}
