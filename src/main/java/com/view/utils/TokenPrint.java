package main.java.com.view.utils;

public final class TokenPrint {

	public static String toPrettyJSON(String json) {
		if (json == null || json.isEmpty()) return "";
		if (!json.contains("{")) return json;
	    
	    StringBuilder pretty = new StringBuilder();
	    int indentLevel = 0;
	    String indentString = "\t";

	    for (int i = 0; i < json.length(); i++) {
	        char letter = json.charAt(i);

	        if (letter == '{' || letter == '[') {
	            pretty.append(letter).append("\n");
	            indentLevel++;
	            addIndent(pretty, indentLevel, indentString);
	        } else if (letter == '}' || letter == ']') {
	            pretty.append("\n");
	            indentLevel--;
	            addIndent(pretty, indentLevel, indentString);
	            pretty.append(letter);
	        } else if (letter == ',') {
	            pretty.append(letter).append("\n");
	            addIndent(pretty, indentLevel, indentString);
	        } else {
	            pretty.append(letter);
	        }
	    }
	    return pretty.toString();
	}
	
	private static void addIndent(StringBuilder sb, int count, String indent) {
	    for (int i = 0; i < count; i++) sb.append(indent);
	}
}
