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
public class GameState {
    String[][] board = new String[][]{
        {"-","-", "-"},
        {"-","-", "-"},
        {"-","-", "-"}
    };

    int nextTurn;
    int numberOfPlayers; 
    
    boolean playerX = false;
    boolean player0 = false;
    
    boolean xIsBusy = false;
    boolean oIsBusy = false;
    
    public void setPlayer2(boolean b){
        player0 = b;
    }
    
    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }
    public void setTurn(int t){
        nextTurn = t;
    }
    int getTurn(){
        return nextTurn;
    }
    public void printBoard(){
        for(int i =0; i<3; i++){
            for(int j= 0; j<3; j++){
                System.out.print(board[i][j]);
            }
        }
    }
    public void makeMove(int n){
        String pawn="";
        if(nextTurn==2){//It's 1's turn
            pawn="X";
        }
        if (nextTurn==1){
            pawn="O";
        }
        if(n==1){
            board[0][0]=pawn;
        }
        if(n==2){
            board[0][1]=pawn;
        }
        if(n==3){
            board[0][2]=pawn;
        }
        if(n==4){
            board[1][0]=pawn;
        }
        if(n==5){
            board[1][1]=pawn;
        }
        if(n==6){
            board[1][2]=pawn;
        }
        if(n==7){
            board[2][0]=pawn;
        }
        if(n==8){
            board[2][1]=pawn;
        }
        if(n==9){
            board[2][2]=pawn;
        }
    }
    
    
}
