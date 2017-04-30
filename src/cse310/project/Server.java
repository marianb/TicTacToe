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
    public static void main(String[]args) throws IOException{               
        int portNumber = 2000;
        int uniquePlayerID = 0;
        //creates our server!
        ServerSocket theServer = new ServerSocket(portNumber); 
        try{
            while(true){
                //Accepts client's request for a connection
                Socket connect = theServer.accept();
                //Reads input from socket
                BufferedReader in = new BufferedReader
                (new InputStreamReader(connect.getInputStream()));
                //GameState g = new GameState();
            
                //Output stream of data from socket
                DataOutputStream out = new DataOutputStream(connect.getOutputStream());
            } 
        }catch(IOException e){
                
        }
    }
}

