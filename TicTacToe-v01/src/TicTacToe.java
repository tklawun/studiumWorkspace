import java.io.BufferedReader;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.io.InputStreamReader;

/**
 * @author Thomas Steuert den Programmablauf
 */
public class TicTacToe {
	private static BufferedReader eingabe;

	/**
	 * Startet das Spiel
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// startet das Spiel mit einer Void Methode play
		(new TicTacToe()).play();
	}

	/*
	 * startet das Spiel
	 * 
	 * @param
	 * 
	 * @return
	 */
	public void play() {
		// generiere ein neues Spielfeld
		//CCD:vermeide magix Numbers
		int collumn = 3;
		int row = 3;
		int[][] ticTacToeArray = new int[collumn][row];
		// inizialisieren des Array (Alles auf 0)
		for (int i = 0; i < collumn; i++) {
			for (int j = 0; j < row; j++) {
				ticTacToeArray[i][j] = 0;
			}
		}

		System.out.println("Das Spiel beginnt");
		this.tictactoeOutput(ticTacToeArray);
		tictactoeOutput(ticTacToeArray);
		// Inizialisierung des Inputstream
		eingabe = new BufferedReader(new InputStreamReader(System.in));
		// Die einzelnen Spielzuege werden abgefragt
		int[] playMove = new int[2];
		int maxPlayMove = 9;
		for (int i = 0; i < maxPlayMove; i++) {
			// ungerade i sind Spieler 2, gerade Spieler 1
			if (i == 1 || i == 3 || i == 5 || i == 7) {
				playMove = readConsolePlayer(ticTacToeArray, 0, 2);
				ticTacToeArray[playMove[0]][playMove[1]] = 2;
			} else
			// CCD: Vereinfache Code: eine zusaetzliche Pruefung auf gerade
			// Ziffern ist nicht notwendig.
			{
				playMove = readConsolePlayer(ticTacToeArray, 0, 1);
				ticTacToeArray[playMove[0]][playMove[1]] = 1;
			}
			tictactoeOutput(ticTacToeArray);
			checkWinner(ticTacToeArray);
		}
		System.out.println("Ende des Spiels");
		System.exit(0);
	}

	/**
	 * Gibt den Gewinner an die Konsole aus und beendet das Spiel
	 * TODO : copy Code vermeiden. 
	 * @param ticTacToeArray
	 */
	private void checkWinner(int[][] ticTacToeArray) {
		
		if (this.checkWin(ticTacToeArray, 1)) {
			System.out.println("Spieler 1 hat gewonnen!");
			System.out.println("Ende des Spiels");
			System.exit(0);
		}
		if (this.checkWin(ticTacToeArray, 2)) {
			System.out.println("Spieler 2 hat gewonnen!");
			System.out.println("Ende des Spiels");
			System.exit(0);
		}

	}

	/*
	 * Konsolenausgabe-Eingabe Spieler Fragt den Spieler an der Konsole ab.
	 * TODO: Hier sollte die Eingabe vereinfacht werden. 
	 * @return int[2] Spalte und Zeile im Spiel, was der User gesetzt hat.
	 */
	private static int[] readConsolePlayer(int[][] ticTacToeArray, int failure,
			int player) {
		int[] collumnAndRow = new int[2];

		System.out.println("Spieler " + Integer.toString(player)
				+ " setze Feld");
		try {
			System.out.println("Geben Sie Spalte ein (1-3)");
			collumnAndRow[0] = Integer.parseInt(eingabe.readLine());
			collumnAndRow[0]--;
			if (collumnAndRow[0] < 0 || collumnAndRow[0] > 2) {
				System.out.println("Bitte nur Wert zwischen 1 und 3 eingeben!");
				collumnAndRow = readConsolePlayer(ticTacToeArray, failure,
						player);
			}

			System.out.println("Geben Sie Zeile ein (1-3)");
			collumnAndRow[1] = Integer.parseInt(eingabe.readLine());
			collumnAndRow[1]--;
			if (collumnAndRow[1] < 0 || collumnAndRow[1] > 2) {
				System.out.println("Bitte nur Wert zwischen 1 und 3 eingeben!");
				collumnAndRow = readConsolePlayer(ticTacToeArray, failure,
						player);
			}
			// Check ob Feld schon belegt. Rufe Methode rekursiv auf.
			if (ticTacToeArray[collumnAndRow[0]][collumnAndRow[1]] == 1
					|| ticTacToeArray[collumnAndRow[0]][collumnAndRow[1]] == 2) {
				System.out
						.println("Feld ist schon belegt, bitte ein neues auswählen.");
				failure++;
				collumnAndRow = readConsolePlayer(ticTacToeArray, failure,
						player);
				if (failure > 3) {
					System.out
							.println("drei mal falsche Zelle ausgewaehlt. Programm wird beendet!");
					System.exit(0);
				}
			}
			System.out.println("gewähltes Feld: spalte:"
					+ Integer.toString(collumnAndRow[0]) + " Zeile:"
					+ Integer.toString(collumnAndRow[1]));
			System.out.println("Anzahl fehlerhafter Eingaben: "
					+ Integer.toString(failure));
			return collumnAndRow;
		} catch (NumberFormatException en) {
			System.out.println("Bitte nur Ziffern eingeben.");
			failure++;
			collumnAndRow = readConsolePlayer(ticTacToeArray, failure, player);
			if (failure > 3) {
				System.out
						.println("drei mal falsch eingegeben. Programm wird beendet!");
				System.exit(0);
			}
		} catch (IOException e) {
			System.out.println("Bitte Eingabe wiederholen.");
			failure++;
			collumnAndRow = readConsolePlayer(ticTacToeArray, failure, player);
			if (failure > 3) {
				System.out
						.println("Unbekannter Fehler. Programm wird beendet!");
				System.exit(0);
			}
		}
		return collumnAndRow;
	}

	/*
	 * gibt das aktuelle Spielfeld an der Konsole aus
	 * 
	 * @param das int[2][2] Spielfeld
	 */
	private static void tictactoeOutput(int[][] tictactoeArray) {
		System.out.println("Der derzeitig Spielstand wird ausgegeben.");

		//CCD: Vermeide kopieren von Code
		for (int i = 0; i < 3; i++) {
			String zeile = Integer.toString(tictactoeArray[0][i]) + " | "
					+ Integer.toString(tictactoeArray[1][i]) + " | "
					+ Integer.toString(tictactoeArray[2][i]);
			System.out.println(zeile);
		}

		// }
	}

	/*
	 * Soll dann den Spielverlauf auswerten.
	 * TODO: CCD: Parametrisierung und vermeidung von Code Copy
	 * @param int[][] Spielfeld
	 * 
	 * @param int Spieler
	 */

	private boolean checkWin(int[][] ticTacToeArray, int player) {
		boolean win = false;
		/*
		 * Test 3 Parallel
		 */
		for (int i = 0; i < 3; i++) {
			if (ticTacToeArray[i][0] == player
					&& ticTacToeArray[i][1] == player
					&& ticTacToeArray[i][2] == player) {
				win = true;
			}
		}

		/*
		 * Test 3 Vertikal
		 */
		for (int j = 0; j < 3; j++) {
			if (ticTacToeArray[0][j] == player
					&& ticTacToeArray[1][j] == player
					&& ticTacToeArray[2][j] == player) {
				win = true;
			}
		}
		/*
		 * Test 3 Schraeg rechts
		 */
		if (ticTacToeArray[0][0] == player && ticTacToeArray[1][1] == player
				&& ticTacToeArray[2][2] == player) {
			win = true;
		}

		/*
		 * Test 3 Schraeg links
		 */
		if (ticTacToeArray[0][2] == player && ticTacToeArray[1][1] == player
				&& ticTacToeArray[2][0] == player) {
			win = true;
		}
		return win;

	}

	/*
	 * TODO generate algorithm for computer as player Algorithmus für den
	 * Computer als Spieler
	 */
	private int[] computerPlayer(int ticTacToeArray, int player) {
		int[] gameMove = new int[2];
		gameMove[0] = 1;

		gameMove[1] = 1;
		// TODO hier der Algorithmus für den Computer als Spieler
		return gameMove;
	}
}
