package info.bockowsk;

import java.util.Scanner;

@Details(revision = 1)
public class TicTacToe {
	// properties

	String[][] board = new String[][] { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };
	// who is playing currently
	String actualPlayer = null;

	String[] players = { "X", "O" };

	String winner = null;

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

		return true;
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
			if (horizontal == "XXX" || horizontal == "OOO") {
				return horizontal.substring(0, 1);
			}
			// check vertical
			if (vertical == "XXX" || vertical == "OOO") {
				return vertical.substring(0, 1);
			}
		}
		// check diagnonal (0,0)
		if (diagnonal1 == "XXX" || diagnonal1 == "OOO") {
			return diagnonal1.substring(0, 1);
		}
		// check diagnonal (2,0)
		if (diagnonal2 == "XXX" || diagnonal2 == "OOO") {
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
		// TODO Auto-generated method stub
		TicTacToe ticTacToe = new TicTacToe();
		ticTacToe.printBoard();
		while (ticTacToe.winner == null) {
			ticTacToe.printBoard();
			int x=0;int y=0;
			while ((x<1 || x >3) || (y<1 || y>3) || (ticTacToe.board[x-1][y-1]!=" ")) {
				System.out.println("Gracz: "+ticTacToe.actualPlayer+". Podaj wspolrzedna pozioma od 1 do 3");
				x=ticTacToe.scanner.nextInt();
				System.out.println("Gracz: "+ticTacToe.actualPlayer+". Podaj wspolrzedna pionowa od 1 do 3");
				y=ticTacToe.scanner.nextInt();
			}
			ticTacToe.board[x-1][y-1]=ticTacToe.actualPlayer;
			ticTacToe.winner=ticTacToe.checkWhoWon();
			ticTacToe.actualPlayer=(ticTacToe.actualPlayer=="X") ? "O": "X";
			
			
		}
		// sprawdz czy ktos wygral

		// }
		// wypisz plansze
		// pogratuluj zwyciezcy
		// zakoncz dzialanie porgramu
	}
}
