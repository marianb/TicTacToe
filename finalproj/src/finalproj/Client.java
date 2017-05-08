/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproj;

import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private PrintWriter out;
	private Socket sock;
	private Scanner scan;
	
	public Client(String serverAddress) throws Exception {
		int PORT = 8080;
		String input;
                try{
		sock = new Socket(serverAddress, PORT);
		if(sock != null){
                        System.out.println("HTTP/1.1/ 200 OK"); 
			System.out.println("Connection Established with the server");
		} else {
                       System.out.println("HTTP/1.1/ 400 Bad Request"); 
			System.out.println("Couldn't establish connection with server");
		}
		out = new PrintWriter(sock.getOutputStream(), true);
		ClientReceiver cr = new ClientReceiver(sock);
		scan = new Scanner(System.in);
		System.out.print("Please enter username: ");
		out.println(scan.nextLine());
		cr.start();
		while((input = scan.nextLine()) != null){
			out.println(input);
		}
	}
                catch(UnknownHostException e)
                {
                    e.printStackTrace();
                }
                catch(ConnectException e)
                {
                    System.out.println("HTTP/1.1/ 400 Bad Request"); 
			System.out.println("Couldn't establish connection with server");
                   
                }
                    
        }
	       
	public static void main(String[] args) throws Exception {
		String serverAddress = (args.length == 0) ? "localhost" : args[1];
		Client c = new Client(serverAddress);
	}
}

class ClientReceiver extends Thread {
	Socket sock;
	private BufferedReader in;
	
	public ClientReceiver(Socket s) throws Exception {
		sock = s;
	}
	
	@Override
	public void run() {
		String response;
        try {
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            response = in.readLine();
            while ((response = in.readLine()) != null) {
				System.out.println(response);
            }
        } catch (IOException e) {
				e.printStackTrace();
		}
	}
}