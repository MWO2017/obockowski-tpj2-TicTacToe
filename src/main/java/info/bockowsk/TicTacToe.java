package info.bockowsk;

import java.util.InputMismatchException;
import java.util.Scanner;

@Details(revision = 1)
public class TicTacToe {
	// properties

	String[][] board = new String[][] { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };
	// who is playing currently
	String actualPlayer = null;

	String[] players = { "X", "O" };

	String winner = null;
	
	int counter=0;

	Scanner scanner = new Scanner(System.in);

	// constructor

	public TicTacToe() {
		// who is starting
		actualPlayer = players[(int) (Math.random() * 2)];
	}

	public void printBoard() {
		String structure = "";
		structure += "\0097\n";
		for (int o = 0; o < 3; o++) {
			structure += "|";
			for (int i = 0; i < 3; i++) {
				structure += board[o][i] + "|";
			}
			structure += "\n";
		}
		System.out.print(structure);
	}

	public boolean move(int x, int y) {
		if (this.board[x - 1][y - 1] == " ") {
			return false;
		} else {
			System.out.println("coordinates: " + x + ", " + y + " are already used.");
			return true;
		}
	}

	public String checkWhoWon() {
		// start 0,0
		String diagnonal1 = "";
		// start 2,0
		String diagnonal2 = "";
		for (short i = 0, bias1 = 0, bias2 = 2; i < 3 && bias1 < 3 && bias2 >= 0; i++, bias1++, bias2--) {
			String horizontal = "";
			String vertical = "";
			for (int j = 0; j < 3; j++) {
				// horizontal
				horizontal += this.board[i][j];
				// vertical
				vertical += this.board[j][i];
				// diagnonals
				if (i == j) {
					diagnonal1 += this.board[i][j];
				}
				if (Math.abs(i - j) == 2 || (i == 1 && j == 1)) {
					diagnonal2 += this.board[i][j];
				}
			}
			// check horizontal
			if (horizontal.equals("XXX") || horizontal.equals("OOO")) {
				return horizontal.substring(0, 1);
			}
			// check vertical
			if (vertical.equals("XXX") || vertical.equals("OOO")) {
				return vertical.substring(0, 1);
			}
		}
		// check diagnonal (0,0)
		if (diagnonal1.equals("XXX") || diagnonal1.equals("OOO")) {
			return diagnonal1.substring(0, 1);
		}
		// check diagnonal (2,0)
		if (diagnonal2.equals("XXX") || diagnonal2.equals("OOO")) {
			return diagnonal2.substring(0, 1);
		}
		return null;
	}

	public static void printBox() {
		for (int i = 0x2500; i <= 0x257F; i++) {
			System.out.printf("0x%x : %c\n\n", i, (char) i);
		}
	}

	public static void main(String[] args) {

		// new game instance
		TicTacToe ticTacToe = new TicTacToe();
		System.out.println("The actual player is: " + ticTacToe.actualPlayer);
		// the main loop, it repeats till winner
		while (ticTacToe.winner == null && ticTacToe.counter < 9) {
			ticTacToe.printBoard();
			// coordinates
			int x = 0;
			int y = 0;
			// repeats until coordinates are correct
			do {
				// a proper range
				while (x < 1 || x > 3) {
					System.out.println("Player: " + ticTacToe.actualPlayer + ". Enter a coordinate X (from 1 to 3)");
					// check is it an integer
					while (!ticTacToe.scanner.hasNextInt()) {
						System.out.println("you have to enter the integer from 1 to 3.");
						ticTacToe.scanner.next();
					}
					x = ticTacToe.scanner.nextInt();
				}
				// a proper range
				while (y < 1 || y > 3) {
					System.out.println("Player: " + ticTacToe.actualPlayer + ". Enter a coordinate Y (from 1 do 3)");
					// check is it an integer
					while (!ticTacToe.scanner.hasNextInt()) {
						System.out.println("you have to enter the integer from 1 to 3.");
						ticTacToe.scanner.next();
					}
					y = ticTacToe.scanner.nextInt();
				}
			} while (ticTacToe.move(x, y));
			ticTacToe.counter+=1;
			ticTacToe.board[x - 1][y - 1] = ticTacToe.actualPlayer;
			if ((ticTacToe.winner = ticTacToe.checkWhoWon()) != null) {
				ticTacToe.printBoard();
				System.out.println("And the WINNER is " + ticTacToe.actualPlayer + " ! CONGRATULATION !!!");
			} else {
				ticTacToe.actualPlayer = (ticTacToe.actualPlayer == "X") ? "O" : "X";
			}
		}
		if (ticTacToe.counter == 9) {
			ticTacToe.printBoard();
			System.out.println("DRAW! GG!");
		}
	}
}
