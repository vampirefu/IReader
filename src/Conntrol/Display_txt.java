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
	        //���������������ʾ���ڣ�  
	        content = new JTextArea(10,50);  
	        content.setAutoscrolls(true);  
	        JScrollPane contentScroll = new JScrollPane(content);  
	        content.setBorder(BorderFactory.createBevelBorder(1));  
	        JPanel upper = new JPanel(new BorderLayout());  
	        upper.add(contentScroll);  
	        //�����С���ô���  
	        wordSizeSetArea = new JTextArea(1,3);  
	        wordSizeSetArea.setBorder(BorderFactory.createBevelBorder(1));  
	        wordSizeSetArea.setText("12");  
	        //������ʽ���ô��ڣ��Ӵֵȣ�  
	        wordStyleSetArea = new JTextArea(1,3);  
	        wordStyleSetArea.setBorder(BorderFactory.createBevelBorder(1));  
	        wordStyleSetArea.setText("0");  
	        //�����������ô��ڣ�����ȣ�  
	        wordNameSetArea = new JTextArea(1,3);  
	        wordNameSetArea.setBorder(BorderFactory.createBevelBorder(1));  
	        wordNameSetArea.setText("����");  
	        //��ť  
	        JButton filePath = new JButton("���ļ�");  
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
	        //��ť  
	        JButton wordSizeSet = new JButton("���������С");  
	        wordSizeSet.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){  
	                try{  
	                    WordStyleSet.StyleSet(content,wordNameSetArea.getText(),Integer.parseInt(wordStyleSetArea.getText()),Integer.parseInt(wordSizeSetArea.getText()));  
	                }catch(Exception e0){  
	                    e0.printStackTrace();                 
	                }  
	            }  
	        });  
	        //��ť  
	        JButton wordStyleSet = new JButton("����������ʽ");  
	        wordStyleSet.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){  
	                try{  
	                    WordStyleSet.StyleSet(content,wordNameSetArea.getText(),Integer.parseInt(wordStyleSetArea.getText()),Integer.parseInt(wordSizeSetArea.getText()));  
	                }catch(Exception e0){  
	                    e0.printStackTrace();                 
	                }  
	            }  
	        });  
	        //��ť  
	        JButton wordNameSet = new JButton("������������");  
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
	        jframe.setTitle("TXTС˵�Ķ���");  
	        jframe.setVisible(true);  
	        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    }  
	      
	    public static void main(String args[]){  
	        Display_txt display_demo = new Display_txt();  
	    }  

}
