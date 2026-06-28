package main.java.com.view.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

public class MyInput extends JTextArea {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final UndoManager undoManager = new UndoManager();
	
	public MyInput() {
		super();
		initUndoManager();
	}
	
	public MyInput(String text) {
		super(text);
		initUndoManager();
	}

    public MyInput(String text, int rows, int cols) {
        super(text, rows, cols);
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
