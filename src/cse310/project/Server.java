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
        boolean listening = true;
        //creates our server!
        ServerSocket theServer = new ServerSocket(portNumber); 
//            while(listening)
//            {
//                new serverThread(ServerSocket.accept()).start();
//            }
//        
        
        try{
            while(true){
                //Accepts client's request for a connection
                Socket connect = theServer.accept();
                //Reads input from socket
                BufferedReader serverInput = new BufferedReader
                (new InputStreamReader(connect.getInputStream()));
                if(serverInput.equals("l")){
                    
                }
                //GameState g = new GameState();
            
                //Output stream of data from socket
                DataOutputStream serverOutput = new DataOutputStream(connect.getOutputStream());
            } 
        }catch(IOException e){
                
        }
    }
    
    public void execute()
    {
        for (int i = 0; i < players.length; i++)
        {
            
        }
    }
    
}

