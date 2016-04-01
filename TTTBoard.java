
/**
 * Represents the state of a tic-tac-toe board. This specification assumes that
 * you know how to play tic-tac-toe. Consult Wikipedia if you don't.
 * 
 * A tic-tac-toe board consists of nine squares, numbered like this:
 * 
 * <pre>
 *  1 | 2 | 3 
 * ---+---+--- 
 *  4 | 5 | 6
 * ---+---+--- 
 *  7 | 8 | 9
 * </pre>
 * 
 * Each square can be unoccupied, contain an X, or contain an O.
 * 
 * Beginning from an empty board, the players take turns moving until X wins, O
 * wins, or there is a draw. X always moves first.
 * 
 * In addition, a tic-tac-toe board knows how many times X has won, how many
 * times O has won, and how many times there has been a draw.
 */
public class TTTBoard {
	// set variable to empty content
	private static final char EMPTY = ' ';

	// class variables
	// number of wins
	private int X;
	private int O;
	private int draw;
	// 2D array 
	private char[] board;
	// turns between X and  O
	private boolean turn;

	/**
	 * Constructs an empty board in which X has won zero times, O has won zero
	 * times, and there have been no draws.
	 */

	public TTTBoard() {
		// set size of an array
		board = new char[9];

		// initialize board array so that EMPTY is a space
		for (int i = 0; i < 9; i++) {
			board[i] = ' ';
		}
	}

	// helper method to check if there is a win
	private char check() {
		// return X if X wins, O if O wins, D if draw, N if
		// not complete

		// first check horizontally
		for (int j = 0; j < 3; j++) {
			int i = j*3;
			if (board[0 + i] == board[1 + i] && board[1 + i] == board[2 + i]) return board[j*3];
		}

		// check vertically
		for (int j = 0; j < 3; j++) {
			if (board[0 + j] == board[3 + j] && board[3 + j] == board[6 + j]) return board[j];
		}

		// check diagonals
		if (board[0] == board[4] && board[4] == board[8]
				|| board[6] == board[4] && board[4] == board[2])
			return board[4];

		// will return N if there is one empty space

		for (int i = 0; i < 9; i++) {
			if (board[i] == EMPTY) return 'N';
		}
		// will return D which is a draw (board is full)
		return 'D';

	}

	/**
	 * If the current position is a win for X, a win for O, or a draw, throws an
	 * IllegalArgumentException.
	 * 
	 * If the specified square is already occupied, throws an
	 * IllegalArgumentException.
	 * 
	 * If square is invalid (less than 1 or greater than 9), throws an
	 * IllegalArgumentException.
	 * 
	 * Otherwise, if it is X's turn to move, records an X move to the specified
	 * square and returns "X". If it is O's turn to move, records an O move to
	 * the specified square and returns "O". If the move makes the position a
	 * win for X, the "wins for X" count is incremented. If the move makes the
	 * position a win for O, the "wins for O" count is incremented. If the move
	 * makes the position a draw, the "draws" count is incremented.
	 */

	public String move(int square) {
		// throw illegalArgumentException if current position is a win or draw
		if (isDrawn() || isWon()) throw new IllegalArgumentException();

		// convert square into coordinates

		// throw illegalArgumentException if specified square is already
		// occupied
		if (board[square-1] != ' ') throw new IllegalArgumentException();

		// throw illegalArgumentException if square is invalid (less than 1 or
		// greater than 9)
		if (square < 1 || square > 9) throw new IllegalArgumentException();

		// if it is X's turn to move, records an X move to the specified square
		// and returns "X"
		// if it is O's turn to move, records an O move to the specified square
		// and returns "O"
		/*
		 * If the move makes the position a win for X, the "wins for X" count is
		 * incremented. If the move makes the position a win for O, the
		 * "wins for O" count is incremented. If the move makes the position a
		 * draw, the "draws" count is incremented.
		 */

		if (turn) {
			board[square-1] = 'O';
			// switches turns
			turn = false;
			if (check() == 'O') O++;
			if (check() == 'D') draw++;
			return "O";
		} 
		else {
			board[square-1] = 'X';
			// switches turns
			turn = true;
			if (check() == 'X') X++;
			if (check() == 'D') draw++;
			return "X";
		}
	}

	/**
	 * Returns "X" (if it is X's turn to move) or "O" (otherwise).
	 */
	public String getToMove() {
		if (turn) return "O";
		return "X";

	}

	/**
	 * Reports whether or not the board has a drawn position (all squares filled
	 * in, neither X nor O has three in a row).
	 */
	public boolean isDrawn() {
		if (check() == 'D') return true;
		return false;
	}

	/**
	 * Reports whether or not the board has a won position (either X or O has
	 * three in a row).
	 */
	public boolean isWon() {
		if (check() == 'X' || check() == 'O') return true;
		return false;
	}

	/**
	 * Resets the board so that a fresh game can be played. Does not modify the
	 * scoring records.
	 */
	public void reset() {
		// iterating through board and setting all to " "
		for (int i = 0; i < 9; i++)
			board[i] = ' ';
	}

	/**
	 * Returns the number of games that X has won.
	 */
	public int getXWins() {
		return X;
	}

	/**
	 * Returns the number of games that O has won.
	 */
	public int getOWins() {
		return O;
	}

	/**
	 * Returns the number of games that have been drawn.
	 */
	public int getDrawCount() {
		return draw;
	}
}
