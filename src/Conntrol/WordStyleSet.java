package Conntrol;

import java.awt.Font;

import javax.swing.JTextArea;

public class WordStyleSet {
	 public static void StyleSet(JTextArea jtextarea,String wordName,int wordStyle,int wordSize){  
	        Font font = new Font(wordName,wordStyle,wordSize);  
	        jtextarea.setFont(font);  
	    }     

}
