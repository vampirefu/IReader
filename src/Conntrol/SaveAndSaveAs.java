package Conntrol;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JTextArea;

public class SaveAndSaveAs {
	 public SaveAndSaveAs(JTextArea textArea,String filePath){  
	        BufferedWriter bw = null;  
	        try {  
	            OutputStream os = new FileOutputStream(filePath);  
	            bw = new BufferedWriter(new OutputStreamWriter(os));  
	            for (String value : textArea.getText().split("\n")) {  
	                bw.write(value);  
	                bw.newLine();//换行  
	            }  
	        } catch (IOException e1) {  
	            e1.printStackTrace();  
	        } finally {  
	            if (bw != null) {  
	                try {  
	                    bw.close();  
	                } catch (IOException e1) {  
	                    e1.printStackTrace();  
	                }  
	            }  
	        }  
	    }
}
