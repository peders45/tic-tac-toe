package app;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Tictactoe {
	
	private TictactoeField[][] board;
	private char currentPlayer;
	private TictactoeSaveHandler saveHandler = new TictactoeSaveHandler();
	
    public Tictactoe() {
        this.board = new TictactoeField[3][3];
        newBoard();
    }
    
    public Tictactoe(String savedGameString) {
    	this.board = new TictactoeField[3][3];
    	int pos = 0;
    	for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                board[y][x] = new TictactoeField(savedGameString.charAt(pos));
                pos++;
            }
        }
        this.currentPlayer = savedGameString.charAt(savedGameString.length()-1);
    }
    
    public char getCurrentPlayer() {
    	return currentPlayer;
    }
        
    public void newGame() {
    	newBoard();
    }
    
    private void newBoard() {
    	for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                board[y][x] = new TictactoeField();
            }
        }
        this.currentPlayer = 'X';
    }
    
    public boolean changeField(int x, int y) {
    	if (isValidChange(x, y)) {
			if (currentPlayer == 'X') {
				board[y][x].setStatusX();
			}
			else if (currentPlayer == 'O') {
				board[y][x].setStatusO();
			}
			return true;
		}
    	else {
    		return false;
    	}
    }
    
    private boolean isValidChange(int x, int y) {
    	return board[y][x].isStatusNull() && !isWin();
    }
    
    public char getField(int x, int y) {
    	return board[y][x].getStatus();
    }
    
    public void changeTurn() {
    	if (!isTie() && !isWin()) {
    		if (currentPlayer == 'X') {
    			this.currentPlayer = 'O';
    		}
    		else if (currentPlayer == 'O') {
    			this.currentPlayer = 'X';
    		}
		}
    }
    
    public boolean isTie() {
    	//check if board have available fields
    	for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (board[y][x].isStatusNull()) {
					return false;
				}
            }
        }
    	//check if anyone hasn't won with full board either
    	return !isWin();
    	
    }
    
    public boolean isWin() {
    	for (int i = 0; i < 3; i++) {
    		//check columns
    		if (checkThreeFields(board[0][i], board[1][i], board[2][i])) {
    			return true;
    		}
    		//check rows
    		if (checkThreeFields(board[i][0], board[i][1], board[i][2])) {
    			return true;
    		}
    	}
    	//check diagonals
    	if (checkThreeFields(board[0][0], board[1][1], board[2][2]) || checkThreeFields(board[2][0], board[1][1], board[0][2])) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkThreeFields(TictactoeField c1, TictactoeField c2, TictactoeField c3) {
    	return (!c1.isStatusNull() && c1.getStatus() == c2.getStatus() && c2.getStatus() == c3.getStatus());
    }
    
    //I/O
    public void saveGame() throws IOException {
    	saveHandler.saveGame(this);
    }
    public Tictactoe loadGame() throws IOException {
    	return saveHandler.loadGame();
    }
	
	public String toString() {
		String result = Arrays.stream(board).map(Arrays::toString).collect(Collectors.joining(System.lineSeparator()));
		return result + "\nTurn:" + currentPlayer;
	}	
}
