package main.java.com.view.utils;

import java.awt.Dimension;

public interface WindowSizable {

	Dimension getWindowSize();

	default void addSizeChangeListener(Runnable onChange) {
	}
}
