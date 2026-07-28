package main.java.com.controller;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import main.java.com.model.core.TokenProcessor;
import main.java.com.model.dto.BruteForceResult;
import main.java.com.view.panels.BaseBruteForcePanel;

public class BruteForceController {
	private BaseBruteForcePanel view;
	private TokenProcessor processor;
	
	public BruteForceController(BaseBruteForcePanel view, TokenProcessor processor) {
		this.view = view;
		this.processor = processor;
	}
	
	public void handleBruteForce() {
        String token = view.getToken();
        String filePath = view.getFilePath();
            
        SwingWorker<BruteForceResult, Void> worker = new SwingWorker<>() {
        	@Override
            public BruteForceResult doInBackground() throws Exception {
        		view.setResult("In progress..", Color.BLACK);
        		view.setAttackButtonEnabled(false); // 버튼 비활성화
                BruteForceResult result = processor.bruteForce(token, filePath);
                return result;
        	}
        	
        	@Override
        	public void done() {
        		try {
        			BruteForceResult result = get(); 
        			
        			if (result.isSuccess()) view.setResult(result.getFoundKey(), Color.BLUE);
        			else {
        				if (result.getMessage().equals("No Match Result.")) {
        					view.setResult("No Match Result.", Color.RED);
        				} else throw new Exception(result.getMessage());
        			}
        		} catch (Exception e) {
        			view.setResult("ERROR occur", Color.RED);
                    JOptionPane.showMessageDialog(view, "error: " + e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        		} finally {
        			view.setAttackButtonEnabled(true); // 버튼 활성화
        		}
            }
        };
            
        worker.execute();
	}
}
