package Conntrol;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Display_txt extends JFrame{
	 JFrame jframe = new JFrame();  
	    JTextArea content,wordSizeSetArea,wordStyleSetArea,wordNameSetArea;  
	    boolean flag = true;  
	    String str_filePath = null;  
	      
	      
	    public Display_txt(){  
	        //文字输入框（文字显示窗口）  
	        content = new JTextArea(10,50);  
	        content.setAutoscrolls(true);  
	        JScrollPane contentScroll = new JScrollPane(content);  
	        content.setBorder(BorderFactory.createBevelBorder(1));  
	        JPanel upper = new JPanel(new BorderLayout());  
	        upper.add(contentScroll);  
	        //字体大小设置窗口  
	        wordSizeSetArea = new JTextArea(1,3);  
	        wordSizeSetArea.setBorder(BorderFactory.createBevelBorder(1));  
	        wordSizeSetArea.setText("12");  
	        //字体样式设置窗口（加粗等）  
	        wordStyleSetArea = new JTextArea(1,3);  
	        wordStyleSetArea.setBorder(BorderFactory.createBevelBorder(1));  
	        wordStyleSetArea.setText("0");  
	        //字体名字设置窗口（宋体等）  
	        wordNameSetArea = new JTextArea(1,3);  
	        wordNameSetArea.setBorder(BorderFactory.createBevelBorder(1));  
	        wordNameSetArea.setText("宋体");  
	        //按钮  
	        JButton filePath = new JButton("打开文件");  
	        filePath.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){  
	                try{  
	                    JFileChooser jfc = new JFileChooser();  
	                    if(jfc.showOpenDialog(jframe)==JFileChooser.APPROVE_OPTION ){  
	                        str_filePath = jfc.getSelectedFile().getAbsolutePath();  
	                    }  
	                    BufferedReader bufferedReader = new BufferedReader(new FileReader(str_filePath));  
	                    String str_line;  
	                    while((str_line=bufferedReader.readLine())!=null){  
	                        if(flag){  
	                            content.setText(str_line);  
	                            flag = false;  
	                        }  
	                        else{  
	                            content.setText(content.getText()+"\n"+str_line);  
	                        }  
	                    }  
	                    bufferedReader.close();  
	                }catch(FileNotFoundException e1){  
	                    e1.printStackTrace();  
	                }catch(IOException e2){  
	                    e2.printStackTrace();  
	                }  
	            }  
	        });  
	        //按钮  
	        JButton wordSizeSet = new JButton("设置字体大小");  
	        wordSizeSet.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){  
	                try{  
	                    WordStyleSet.StyleSet(content,wordNameSetArea.getText(),Integer.parseInt(wordStyleSetArea.getText()),Integer.parseInt(wordSizeSetArea.getText()));  
	                }catch(Exception e0){  
	                    e0.printStackTrace();                 
	                }  
	            }  
	        });  
	        //按钮  
	        JButton wordStyleSet = new JButton("设置字体样式");  
	        wordStyleSet.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){  
	                try{  
	                    WordStyleSet.StyleSet(content,wordNameSetArea.getText(),Integer.parseInt(wordStyleSetArea.getText()),Integer.parseInt(wordSizeSetArea.getText()));  
	                }catch(Exception e0){  
	                    e0.printStackTrace();                 
	                }  
	            }  
	        });  
	        //按钮  
	        JButton wordNameSet = new JButton("设置字体名字");  
	        wordNameSet.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){  
	                try{  
	                    WordStyleSet.StyleSet(content,wordNameSetArea.getText(),Integer.parseInt(wordStyleSetArea.getText()),Integer.parseInt(wordSizeSetArea.getText()));  
	                }catch(Exception e0){  
	                    e0.printStackTrace();                 
	                }  
	            }  
	        });  
	          
	        JPanel buttonp = new JPanel();  
	        buttonp.add(filePath);  
	        buttonp.add(wordSizeSet);  
	        buttonp.add(wordSizeSetArea);  
	        buttonp.add(wordStyleSet);  
	        buttonp.add(wordStyleSetArea);  
	        buttonp.add(wordNameSet);  
	        buttonp.add(wordNameSetArea);  
	        JPanel all = new JPanel(new GridLayout(1,1));  
	        all.add(upper);  
	        jframe.add(buttonp,BorderLayout.SOUTH);  
	        jframe.add(all,BorderLayout.CENTER);  
	        jframe.pack();  
	        Toolkit tool = Toolkit.getDefaultToolkit();  
	        Dimension screen = tool.getScreenSize();  
	        jframe.setLocation(screen.width/2-jframe.getWidth()/2,screen.height/2-jframe.getHeight()/2);  
	        jframe.setTitle("TXT小说阅读器");  
	        jframe.setVisible(true);  
	        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    }  
	      
	    public static void main(String args[]){  
	        Display_txt display_demo = new Display_txt();  
	    }  

}
