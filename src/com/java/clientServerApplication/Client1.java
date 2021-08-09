package com.java.clientServerApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.*;


public class Client1 implements ActionListener,Runnable {
	JPanel jp;
	static JLabel label;
	JLabel label2;
	Container c;
	JTextField textField;
	JButton b1;
	BufferedReader br;
	BufferedWriter bw;
	static JTextArea textArea;
	static Socket s;
	
	Client1(){
		JFrame jf = new JFrame();
		jf.setBounds(40,40,300,500);
		c = jf.getContentPane();
		c.setLayout(null);
		
		jp = new JPanel();
		jp.setBounds(0,0,300,50);
		jp.setBackground(new Color(7,94,84));
		jp.setLayout(null);
		c.add(jp);
		
		label = new JLabel("Brothers");
		label.setFont(new Font("SAN_SHERIF",Font.BOLD,20));
		label.setForeground(Color.WHITE);
		label.setBounds(20,10,100,20);
		jp.add(label);
		
		label2 = new JLabel("You,Pradeep,Hemant,Satyadev");
		label2.setFont(new Font("SAN_SHERIF",Font.BOLD,10));
		label2.setForeground(Color.WHITE);
		label2.setBounds(20,30,200,20);
		jp.add(label2);
		
		textArea = new JTextArea();
		textArea.setBounds(0,55,300,360);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("SAN_SHERIF",Font.PLAIN,12));
		textArea.setEditable(false);
		c.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(5,420,180,40);
		c.add(textField);
		
		b1 = new JButton("Send");
		b1.setBounds(190,420,90,40);
		b1.setBackground(Color.DARK_GRAY);
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("SAN_SHERIF",Font.PLAIN,15));
		c.add(b1);
		b1.addActionListener(this);
		c.add(b1);
		
		jf.setVisible(true);
		 try {
	          s = new Socket("localhost",2007);
	          bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
	          br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		  }catch(Exception e1) {
			  e1.printStackTrace();
		    }
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 String str = "Sagar: " + textField.getText();
		  try {
			  bw.write(str);
			  bw.write("\n");
			  bw.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
    
	public static void main(String[] args) {
		Client1 c1 = new Client1();
		Thread t1 = new Thread(c1);
		t1.start();
	}

	@Override
	public void run() {
		 try {
			 String msg = "";
			 while((msg  = br.readLine()) != null) {
				 textArea.append(msg + "\n");
				 textField.setText("");
			 }
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
