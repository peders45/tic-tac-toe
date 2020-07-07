package app;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class TictactoeController {
	
	private Tictactoe tictactoe = new Tictactoe();
	
	@FXML
	GridPane board;
	@FXML
	Text turn, alert;
	@FXML
	Button newgame, savegame;
	@FXML
	Text tl, tc, tr, cl, cc, cr, bl, bc, br;
	
    @FXML
    public void initialize() {
    	// initialize tictactoe object
    	try {
    		tictactoe = tictactoe.loadGame();
    		
    		//change GUI accordingly
    		tl.setText(Character.toString(tictactoe.getField(0, 0)));
    		tc.setText(Character.toString(tictactoe.getField(1, 0)));
    		tr.setText(Character.toString(tictactoe.getField(2, 0)));
    		cl.setText(Character.toString(tictactoe.getField(0, 1)));
    		cc.setText(Character.toString(tictactoe.getField(1, 1)));
    		cr.setText(Character.toString(tictactoe.getField(2, 1)));
    		bl.setText(Character.toString(tictactoe.getField(0, 2)));
    		bc.setText(Character.toString(tictactoe.getField(1, 2)));
    		br.setText(Character.toString(tictactoe.getField(2, 2)));
    		turn.setText("Turn: " + tictactoe.getCurrentPlayer());
    		
    		//other GUI changes
    		alert.setText("Saved game loaded");
    		newgame.setDisable(false);
    	}
    	catch (IOException e) {
    		//new/empty game if no saved game exists
    		tictactoe = new Tictactoe();
    		alert.setText("New game started");
    	}
    }
	
    @FXML
    void handleNewGame() {
    	//change the logic
    	tictactoe.newGame();
    	
    	//change GUI accordingly
    	tl.setText("");
    	tc.setText("");
    	tr.setText("");
    	cl.setText("");
    	cc.setText("");
    	cr.setText("");
    	bl.setText("");
    	bc.setText("");
    	br.setText("");
    	turn.setText("Turn: X");
    	
    	//other GUI changes
    	alert.setText("New game started");
    	newgame.setDisable(true);
    	savegame.setDisable(true);
    }
    
	@FXML
	void handleSaveGame() {
		try {
			tictactoe.saveGame();
			savegame.setDisable(true);
			alert.setText("Game saved");
		}
		catch (IOException e) {
			alert.setText("Error during saving");
		}
	}
	
	@FXML
	void handleChangeRute(MouseEvent e) {
		//get id from click
		Text area = (Text) e.getSource();
		String type = area.getId();

		//check if field is already taken
		if (area.getText().contains("O") || area.getText().contains("X")) {
			alert.setText("This field is already taken");
		}
		else {
			//change correct logic and if change, also change GUI + changeTurn
			switch (type) {
			case "tl":
				if (tictactoe.changeField(0, 0)) {
					tl.setText("" + tictactoe.getCurrentPlayer());
					tictactoe.changeTurn();
				}
				break;
			case "tc":
				if (tictactoe.changeField(1, 0)) {
					tc.setText("" + tictactoe.getCurrentPlayer());
					tictactoe.changeTurn();
				}
				break;
			case "tr":
				if (tictactoe.changeField(2, 0)) {
					tr.setText("" + tictactoe.getCurrentPlayer());
					tictactoe.changeTurn();
				}
				break;
			case "cl":
				if (tictactoe.changeField(0, 1)) {
					cl.setText("" + tictactoe.getCurrentPlayer());
					tictactoe.changeTurn();
				}
				break;
			case "cc":
				if (tictactoe.changeField(1, 1)) {
					cc.setText("" + tictactoe.getCurrentPlayer());
					tictactoe.changeTurn();
				}
				break;
			case "cr":
				if (tictactoe.changeField(2, 1)) {
					cr.setText("" + tictactoe.getCurrentPlayer());
					tictactoe.changeTurn();
				}
				break;
			case "bl":
				if (tictactoe.changeField(0, 2)) {
					bl.setText("" + tictactoe.getCurrentPlayer());
					tictactoe.changeTurn();
				}
				break;
			case "bc":
				if (tictactoe.changeField(1, 2)) {
					bc.setText("" + tictactoe.getCurrentPlayer());
					tictactoe.changeTurn();
				}
				break;
			case "br":
				if (tictactoe.changeField(2, 2)) {
					br.setText("" + tictactoe.getCurrentPlayer());
					tictactoe.changeTurn();
				}
				break;
			}

			//check if change GUI buttons
			if (newgame.isDisabled()) {
				newgame.setDisable(false);
			}
			if (savegame.isDisabled()) {
				savegame.setDisable(false);
			}
			
			//change GUI
			turn.setText("Turn: " + tictactoe.getCurrentPlayer());
			alert.setText("");

			//check if GUI alert msg
			if (tictactoe.isTie()) {
				alert.setText("Tie");
			}
			if (tictactoe.isWin()) {
				alert.setText(tictactoe.getCurrentPlayer() + " has WON!");
			}
		}
	}
}
