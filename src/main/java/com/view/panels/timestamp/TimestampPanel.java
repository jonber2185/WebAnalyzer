package main.java.com.view.panels.timestamp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import main.java.com.view.MainFrame;
import main.java.com.view.utils.WindowSizable;

public class TimestampPanel extends JPanel implements WindowSizable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane subTabPane = new JTabbedPane();
	
	public TimestampPanel() {
        setLayout(new BorderLayout());

        subTabPane.addTab("encode", new TimestampEncodePanel());
        subTabPane.addTab("decode", new TimestampDecodePanel());

        add(subTabPane, BorderLayout.CENTER);
    }
	
	@Override
	public Dimension getWindowSize() {
		Component selected = subTabPane.getSelectedComponent();
		if (selected instanceof WindowSizable) {
			return ((WindowSizable) selected).getWindowSize();
		}
		return MainFrame.DEFAULT_SIZE;
	}
 
	@Override
	public void addSizeChangeListener(Runnable onChange) {
		subTabPane.addChangeListener(e -> onChange.run());
	}
}
