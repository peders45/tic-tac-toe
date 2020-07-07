package app;

import java.io.IOException;

public interface TictactoeSaveInterface {
	
	public void saveGame(Tictactoe tictactoe) throws IOException;
	public Tictactoe loadGame() throws IOException;
}
