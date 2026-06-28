package main.java.com.controller;

import main.java.com.model.type.TokenType;
import main.java.com.model.core.TokenIdentifier;

import java.awt.Color;

import main.java.com.view.panels.IdentifierPanel;

public class IdentifierController {
	private IdentifierPanel view;

	public IdentifierController(IdentifierPanel view) {
		this.view = view;
	}

	public void handleIdentify() {
		String token = view.getToken();

		if (token.isEmpty()) {
			view.updateResult("토큰이 입력되지 않았습니다.", Color.RED);
			return;
		}
		
		TokenType type = TokenIdentifier.identify(token);
		String typeStr = type.toString();
		if (typeStr.equals("FLASK")) typeStr = "Flask Session Token (추정)";
		else if (typeStr.equals("JWT")) typeStr = "JSON Web Token (JWT)";
		
		view.updateResult(typeStr, new Color(0, 102, 204));
	}
}
