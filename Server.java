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
import java.util.Hashtable;
public class Server {
    private static final  int portNumber = 2000;
    private static final String welcomeMessage = "Server waiting on port" + portNumber;
    private ServerSocket theServer; 
    
    public static void main(String[]args) {                
        new Server().startGame(); 
    }
    private void startGame () {
        int clientID = 0;
        int gameID = 0;
        
        Hashtable<String, String> ticHash = new Hashtable<String, String>();
//        ticHash.put("gameID1", "clientID");
//        ticHash.put(Integer.toString(gameID), Integer.toString(clientID));
        //ticHash.put(welcomeMessage, "");
        int hashSize = ticHash.size();
        
        try{
            theServer = new ServerSocket(portNumber);
            System.out.println(welcomeMessage); 
            while(true){
                //Creates a new game
                ticGame game = new ticGame();
                gameID++;
                Client client1 = new Client(theServer.accept(), "X", game); 
                Client client2 = new Client(theServer.accept(), "O", game);
                new Thread(client1).start();
                clientID++;
                // put in clientID 1, 2, 3, .... as key.
                // put in gameID 1, 2, 3, .... as value.
                // clients 1,2 share gameID 1, 
                // clients 3,4 share gameID 2, etc. 
                ticHash.put(Integer.toString(clientID), Integer.toString(gameID));
                new Thread(client2).start();
                clientID++;
                ticHash.put(Integer.toString(clientID), Integer.toString(gameID));

            } 
        }catch(IOException e){
                e.printStackTrace();
        }
        finally {
            try {theServer.close();} catch (IOException e) { e.printStackTrace(); }
        }
        
        
        
    }    
        
//retrieve:
//       Integer n = numbers.get("two");
//   if (n != null) {
//     System.out.println("two = " + n);

}

