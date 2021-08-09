package com.java.clientServerApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server implements Runnable {
	Socket s;
	@SuppressWarnings("rawtypes")
	public static Vector client = new Vector();
	Server(Socket s){
		this.s = s;
	}

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(2007);
		while(true) {
			Socket s = ss.accept();
			Server server = new Server(s);
			Thread thread = new Thread(server);
			thread.start();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			client.add(bw);
			while(true) {
				String data = br.readLine().trim();
				for(int i=0; i<client.size(); i++) {
				BufferedWriter writer = (BufferedWriter) client.get(i);
				writer.write(data);
				writer.write("\n");
				writer.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
