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
            int threadCount = 0;
            while(true){
                //Creates a new game
                GameState g = new GameState();
                
                //Accepts client's request for a connection
                
                Socket connect = theServer.accept();
                
                threadImp t1 = new threadImp(" thread" + threadCount);
                t1.start();
                threadCount++;
                
                //Reads input from socket
                BufferedReader serverInput = new BufferedReader
                (new InputStreamReader(connect.getInputStream()));
                if(serverInput.equals("l")){
                    
                }
                //GameState g = new GameState();
            
                //Output stream of data from socket
                DataOutputStream serverOutput = 
                    new DataOutputStream(connect.getOutputStream());
            } 
        }catch(IOException e){
                
        }
    }
    
//    public void execute()
//    {
//        for (int i = 0; i < players.length; i++)
//        {
//            
//        }    
}

// implementation of thread in accordance with
    //https://www.tutorialspoint.com/java/java_multithreading.htm
    
    class threadImp implements Runnable{
        private Thread t;
        private String threadName;
    threadImp(String name)
    {
        threadName = name;
        System.out.println("creating " + threadName);
    }
    public void run()
    {
        System.out.println("thread " + threadName + "is running" + t.getState());
    }
    public void start()
    {
        System.out.println("Starting " + threadName);
        if (t == null)
        {
            t = new Thread (this, threadName);
            t.start();
        }
    }
    
    
    }
