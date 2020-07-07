package app;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TictactoeSaveHandler implements TictactoeSaveInterface{
	
	public void saveGame(Tictactoe tictactoe) throws IOException {
		String rootPath = new File("").getAbsolutePath();
		PrintWriter outFile = new PrintWriter(rootPath + "/src/app/save.txt");
		outFile.println(tictactoe.toString());
		outFile.close();
	}

	public Tictactoe loadGame() throws IOException {
		Tictactoe savedGame;
		String savedGameString = "";
		Scanner scanner;

		String rootPath = new File("").getAbsolutePath();
		scanner = new Scanner(new FileReader(rootPath + "/src/app/save.txt"));

		while(scanner.hasNext()){
			scanner.useDelimiter(",\\s|\\]|\\["); // meaning: ,&whitespace || ] || [
			String token = scanner.next();
			savedGameString += token;
		}
		scanner.close();
        
		//remove newline and return new object
        savedGameString = savedGameString.replace("\n", "").replace("\r", "");
        savedGame = new Tictactoe(savedGameString);
		return savedGame;
	}
}
