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
public class Client {
    public static void main(String[] args) throws IOException {
        int serverPort = 2000;
        Socket theClient = new Socket(args[0], serverPort);
        String command;
        try{
            while(true){
                BufferedReader userInput = 
                    new BufferedReader(new InputStreamReader(System.in));
                
                DataOutputStream sendToServer = 
                    new DataOutputStream(theClient.getOutputStream()); 
                
                BufferedReader serverInput = 
                    new BufferedReader(new InputStreamReader(theClient.getInputStream())); 
                command = userInput.readLine();
                if(command.trim().toLowerCase().equals("h")){
                    System.out.print("Commands: h = help, l = login, p= place, e = exit\nADD THE RULES HERE");
                }if(command.trim().toLowerCase().equals("l")){
                    //CODE FOR LOGIN
                    //TAKES ARG: username, sends username to server
                    //When server getslogin name, the server records the player and sets them to AVILABLE
                }if(command.trim().toLowerCase().equals("p")){
                    System.out.print("Enter the position number of the space you would like to make a move in [1,9]");
                    int place = userInput.read();
                    if(place<10||place>0){
                        sendToServer.writeInt(place);
                    }else{ //Does this work tho
                        System.out.print("Invalid movement. Please enter an integer [1,9]");
                    }
                    //takes argument int 1-9, sends to server
                    //server checks if its player's turn/is legal move
                }
            }
        }catch(IOException e){
            
        }
    }
}
