package main.java.com.view.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.undo.UndoManager;

public class MyInputField extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final UndoManager undoManager = new UndoManager();
	
	public MyInputField() {
		super();
		initUndoManager();
	}
	
	public MyInputField(String text) {
		super(text);
		initUndoManager();
	}

    private void initUndoManager() {
        this.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown()) {
                	if (e.getKeyCode() == KeyEvent.VK_Z) {
                        if (e.isShiftDown()) {
                            // 1. CTRL + SHIFT + Z (Redo)
                            if (undoManager.canRedo()) {
                                undoManager.redo();
                            }
                        } else {
                            // 2. CTRL + Z (Undo)
                            if (undoManager.canUndo()) {
                                undoManager.undo();
                            }
                        }
                    }
                }
            }
        });
    }
}
