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
import java.util.*;
        
public class TicTacToeGame {
 
    public static void main(String[] args){
        
        Player x = new Player();
        Player o = new Player();
        
        x.setPawn("X");
        o.setPawn("O");
        
        int turn =0;
        
        String[][] board = new String[][]{
        {"-","-", "-"},
        {"-","-", "-"},
        {"-","-", "-"}
        };
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Player 1 enter name: ");
        x.setUsername(sc.nextLine());
        System.out.println("Player 2 enter name: ");
        o.setUsername(sc.nextLine()); //make sure its unique
  
        while(isOver(board)==true){
            String pawn,currentUsername;
            printBoard(board);
            if(turn%2==0){
                pawn = x.getPawn();
                currentUsername = x.getUsername();
            }
            else{
                pawn = o.getPawn();
                currentUsername = o.getUsername();
            }
            System.out.println(currentUsername+"'s turn to move. Press P");
            Scanner w = new Scanner(System.in);
            String command = w.next();
            if(command.trim().toLowerCase().equals("p")){
                    System.out.println("Enter the position number of the space you would like to make a move in [1,9]");
                    int place= w.nextInt();
                    while((place>9&&place<1)||!isLegal(board, place)){
                        System.out.println("Illegal move. Please enter a number of an empty spot.");
                        place=w.nextInt();
                    }
                    if(turn%2==0) makeMove(x, place, board);
                    else makeMove(o, place, board);
            }
            printBoard(board);
            if(isDraw(board)==true){
                System.out.println(" There is a draw");
                break;
            }
            if(isWinner(board)==true){
                System.out.println(currentUsername+" has won! Congrats!");
                break;
        }
            turn++;
        }
    }
    
    public static boolean isFull(String[][] s){
        boolean isFull = false;
        for(int i =0; i<3; i++){
            for(int j= 0; j<3; j++){
                if (s[i][j].equals("-")){
                    isFull=true;
                }
            }System.out.println();
        }
        return isFull;
    }
    public static void printBoard(String[][] s){
        for(int i =0; i<3; i++){
            System.out.println();
            for(int j= 0; j<3; j++){
                System.out.print(s[i][j]);
            }
        }System.out.println();
    }
    public static boolean isWinner(String[][] s){
        boolean winner = false;
        for(int i =0; i<3; i++){
            if(s[i][0].equals(s[i][1])&&s[i][1].equals(s[i][2])&&(s[i][0].equals("-")==false)){
                winner = true;
            }
        }
        for(int i =0; i<3; i++){
            for(int j= 0; j<3; j++){
                if (s[0][j].equals(s[1][j])&&s[1][j].equals(s[2][j])&&(s[0][j].equals("-")==false)){
                    winner=true;
                }
            }
        }
        if(s[0][0].equals(s[1][1])&&s[1][1].equals(s[2][2])&&s[1][1].equals("-")==false){
            winner=true;
        }
        if(s[0][2].equals(s[1][1])&&s[1][1].equals(s[2][0])&&s[1][1].equals("-")==false){
            winner=true;
        }
        
        return winner;
    }
    public static boolean isDraw(String[][] s){
        boolean draw = false;
        if(isWinner(s)==false&&isFull(s)==false)
            draw = true;
        return draw;
    }
    public static void makeMove(Player p, int n, String s[][]){
        String pawn = p.getPawn();
        if(n==1){
            s[0][0]=pawn;
        }
        if(n==2){
            s[0][1]=pawn;
        }
        if(n==3){
            s[0][2]=pawn;
        }
        if(n==4){
            s[1][0]=pawn;
        }
        if(n==5){
            s[1][1]=pawn;
        }
        if(n==6){
            s[1][2]=pawn;
        }
        if(n==7){
            s[2][0]=pawn;
        }
        if(n==8){
            s[2][1]=pawn;
        }
        if(n==9){
            s[2][2]=pawn;
        }
    }
    public static boolean isLegal(String[][] s, int n){
        boolean legal = true;
        if(n==1&&!"-".equals(s[0][0])) legal=false;
        if(n==2&&!"-".equals(s[0][1])) legal=false;
        if(n==3&&!"-".equals(s[0][2])) legal=false;
        if(n==4&&!"-".equals(s[1][0])) legal=false;
        if(n==5&&!"-".equals(s[1][1])) legal=false;
        if(n==6&&!"-".equals(s[1][2])) legal=false;
        if(n==7&&!"-".equals(s[2][0])) legal=false;
        if(n==8&&!"-".equals(s[2][1])) legal=false;
        if(n==9&&!"-".equals(s[2][2])) legal=false;
        return legal; 
   }
    public static boolean isOver(String[][] s){
        boolean over=false;
        if(isWinner(s)==true||isFull(s)==true)
            over = true;
        return over;
    }
    
}
