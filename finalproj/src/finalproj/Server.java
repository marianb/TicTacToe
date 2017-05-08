/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproj;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author idagr
 */
class Game {
	private final String[][] board = new String[3][3];
	Player currentPlayer;
	int gameId;

	public String printBoard() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n----BOARD----\n");
		synchronized (board) {
			for (String[] col : board) {
				for (String row : col) {
					sb.append("|" + (row == null ? "   " : row));
				}
				
				sb.append("|\n");
				sb.append("-------------");
				sb.append("\n");
			}
		}
		
		sb.append("--- Boxes are numbered from 1 to 9 from top-left corner ---");
		return sb.toString();
	}

	public boolean makeMove(int boxNumber, Player player) {
		synchronized (this) {
			if (player != currentPlayer)
				return false;
		}
		
		boxNumber--;
		int col = boxNumber % 3;
		int row = boxNumber / 3;
		synchronized (board) {
			if (board[row][col] == null) {
				board[row][col] = player.getMark();
				return true;
			}
		}
			
		return false;
	}
	
	public boolean isWinning(Player player) {
		String mark = player.getMark();
		boolean isWinning = false;
		synchronized (board) {
			isWinning = 
					mark.equals(board[0][0]) && mark.equals(board[0][1]) && mark.equals(board[0][2]) ||
					mark.equals(board[1][0]) && mark.equals(board[1][1]) && mark.equals(board[1][2]) ||
					mark.equals(board[2][0]) && mark.equals(board[2][1]) && mark.equals(board[2][2]) ||
					mark.equals(board[0][0]) && mark.equals(board[1][0]) && mark.equals(board[2][0]) ||
					mark.equals(board[0][1]) && mark.equals(board[1][1]) && mark.equals(board[2][1]) ||
					mark.equals(board[0][2]) && mark.equals(board[1][2]) && mark.equals(board[2][2]) ||
					mark.equals(board[0][0]) && mark.equals(board[1][1]) && mark.equals(board[2][2]) ||
					mark.equals(board[0][2]) && mark.equals(board[1][1]) && mark.equals(board[2][0]);
		}
		
		return isWinning;
				
	}

	public synchronized boolean isOnTurn(Player player) {
		if (currentPlayer == player)
			return true;
			
		return false;
	}
	
	public synchronized void setOnTurn(Player player) {
		if (currentPlayer != null) {
			currentPlayer.getOut().println("Please wait, it is your opponents turn");
			this.currentPlayer = player;
			currentPlayer.getOut().println("It is your turn");
//                        if(!player.getOpponent().isAlive())
//                        {
//                            player.getOpponent().getOut().println("Someone left");
////                         getOpponent().getOut().println("Your opponent has left.");  
//                         
//                        }
		}
//                else if(player.getOpponent().isAlive())
//                {
//                System.out.println("Your opponent has left.");  
//                }
                else {
			currentPlayer = player;
		}
	}

	public void reset() {
		synchronized (board) {
			for (int i = 0; i < board[0].length; i++) {
				for (int j = 0; j < board.length; j++) {
					board[i][j] = null;
				}
			}
		}
		
	}

	public boolean tie() {
		synchronized (board) {
			for (int i = 0; i < board[0].length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j] == null) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public void setGameId(int gameId){
		this.gameId = gameId;
	}
	
	public int getGameId(){
		return gameId;
	}
	
	public void end(Player p) {
		PlayerManager pm = new PlayerManager();
		pm.addPlayerToQueue(p.getOpponent());
		p.getOpponent().getOut().println(p.getUserName() + " disconnected\nWaiting for players...");
	}
	
	class Player extends Thread {
		final Socket socket;
		private final BufferedReader in;
		private final PrintWriter out;
		private String mark;
		private Game game;
		private String username;
		private Player opponent;

		public Player(Socket socket) throws IOException {
			this.socket = socket;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Hello, '" + mark + "'!");
			out.println("Waiting for an opponent...");	
			setUserName(in.readLine());
		}

		@Override
		public void run() {
			String input;
			try {
				out.println("You are playing against " + opponent.getUserName());
				out.println("Game ID: " + game.getGameId());
				letsPlay();
				while ((input = in.readLine()) != null) {
					if (game.isOnTurn(this))
						handleRequest(input);
				}
			} catch (IOException e) {
				game.end(this);
			} finally {
				try { socket.close(); } catch (IOException e) {}
			}
			
		}
    class boxOutOfBounds extends Exception
{
      //Parameterless Constructor
      public boxOutOfBounds() {}

      //Constructor that accepts a message
      public boxOutOfBounds(String message)
      {
         super(message);
         out.println("Invalid move. Please enter a valid input. ");

      }
 }

		private void handleRequest(String input) {
			int boxNumber = 0;
			try {
//                                int selection = Integer.parseInt(input);
                                if(input.length()!=1)
                                {
//                                out.println("Invalid move. Please enter a valid input. ");
                                throw new boxOutOfBounds();
                                }
                                else if(input.matches("h"))
                                {
                                out.println("h = help; p = place a move; l = login; e = exit");
                                }
                                else if(input.matches("e"))
                                {
                                out.println("Exiting.");
                                System.out.println("The player " + getUserName()+ " has exited. ");
                                System.exit(0);
                                }
                                else if(input.matches("p"))
                                {
                                out.println("Enter a valid move (1-9) that isn't already occupied");
                                }
                                else{
				Matcher matcher = Pattern.compile("\\d").matcher(input);
				matcher.find();
				boxNumber = Integer.parseInt(matcher.group());
                                }
                        } 
                        catch (boxOutOfBounds ex) {
                                {
                                out.println("Invalid move. Please enter a valid input. ");                                    
                                }
			}
                        catch (IllegalStateException | NumberFormatException e) {
				out.println("Please enter one digit from 1 to 9!");
			}
			
			if (boxNumber > 0 && game.makeMove(boxNumber, this)) {
				getOpponent().printBoard();
				printBoard();
				if (game.isWinning(this)) {
					winning();
					getOpponent().loosing();
				} else {
					if (game.tie()) {
						tie();
						getOpponent().tie();
					} else {
						game.setOnTurn(getOpponent());
					}
				}
			}
                        
		}
		
		private void tie() {
			out.println("\nNo winner!\n");
			newGame();
		}

		private void loosing() {
			out.println("\nSorry! You Win!\n");
			newGame();
		}

		private void winning() {
			out.println("\nYou lose!\n");
			newGame();
		}

		private void newGame() {
			game.reset();
			letsPlay();
		}

		private void letsPlay() {
			out.println("TicTacToe game is ready! you are letter  " + getMark());
			printBoard();
			if (game.isOnTurn(this)) {
				out.println("It is your turn. Press 'h' for help.");
			} else {
				out.println("It's opponent's turn. Please, wait!");
			}
		}
		
		public String getMark() {
			return mark;
		}
		
		public void setMark(String mark){
			this.mark = mark;
		}
		
		public void setGame(Game game){
			this.game = game;
		}
		
		public void setUserName(String username){
			this.username = username;
		}
		
		public String getUserName() {
			return username;
		}
		
		public synchronized PrintWriter getOut() {
			return out;
		}
		
		public synchronized Player getOpponent() {
			return opponent;
		}
		
		public synchronized void setOpponent(Player player) {
			this.opponent = player;
		}

		private void printBoard() {
			out.println(game.printBoard());
		}
	}
}

public class Server {
	public static void main(String[] args) throws IOException {
        int portNum = 8080;
        ServerSocket listener = new ServerSocket (portNum); 
        System.out.println("Server is Running on port 8080"); 
		PlayerManager pm = new PlayerManager();
		Game g = new Game();
		pm.start();
        try{
            while(true){
                Socket connectionSocket = listener.accept();
                System.out.println("Connection socket" + connectionSocket.toString());
				pm.addPlayerToQueue(g.new Player(connectionSocket));                        
            }
        } finally {

        }       
    }
}

class PlayerManager extends Thread {
	
	BlockingQueue<Game.Player> playerList = new ArrayBlockingQueue<Game.Player>(1024);
	
	@Override
	public void run() {
		int gameId = 0;
		while(true){
			try {
				if (playerList.size() > 1) {
					Game.Player p1 = playerList.take();
					Game.Player p2 = playerList.take();
					startGame(p1, p2, gameId++);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void startGame(Game.Player player1, Game.Player player2, int gameId){
		Game game = new Game();
		game.setGameId(gameId);
		player1.setMark("X");
		player2.setMark("O");
		player1.setGame(game);
		player2.setGame(game);
		player1.setOpponent(player2);
		player2.setOpponent(player1);
		game.currentPlayer = player1;
		player1.start();
		player2.start();
	}
	
	public void addPlayerToQueue(Game.Player p) {
		try {
			playerList.put(p);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}