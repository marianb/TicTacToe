/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse310.project;

/**
 *
 * @author My SONY
 */
import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[]args){               
        int portNumber = 2000;
        
        while (true){
            try{
                ServerSocket first = new ServerSocket(portNumber); 
                //Accepts client's request for a connection
                ////System.out.println("Helo1");
                Socket connect = first.accept();
                ////System.out.println("Helo2");
                //Reads input from socket
                BufferedReader in = new BufferedReader
                (new InputStreamReader(connect.getInputStream()));
                //GameState g = new GameState();
                
                //Output stream of data from socket
                DataOutputStream out = new DataOutputStream(connect.getOutputStream());
            } 
            catch(Exception e){
                
            }
        }
    }
}

