package tp.p1.juego;

import tp.p1.auxiliar.MyStringUtils;

public class BoardPrinter extends GamePrinter{
	
	int numRows; 
	int numCols;
	String[][] board;
	final String space = " ";
	
	/*
	public BoardPrinter (Game game, int rows, int cols) {
		this.numRows = rows;
		this.numCols = cols;		
		encodeGame(game);
	}*/
	
	public BoardPrinter (int rows, int cols) {
		this.numRows = rows;
		this.numCols = cols;		
	}
	

	private void encodeGame(Game game) {
		board = new String[numRows][numCols];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				board[i][j] = game.positionToString(i, j);
			}
		}
		if (!game.isPlayerAlive()) {
			board[game.getPlayerX()][game.getPlayerY()] = "!xx!";
		}
	}
	
	@Override
	public String toString() {

		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String intersect = space;
		String vIntersect = space;
		String hIntersect = "-";
		String corner = space;
		
		String cellDelimiter = MyStringUtils.repeat(hDelimiter, cellSize);
		
		String rowDelimiter = vIntersect + MyStringUtils.repeat(cellDelimiter + intersect, numCols-1) + cellDelimiter + vIntersect;
		String hEdge =  corner + MyStringUtils.repeat(cellDelimiter + hIntersect, numCols-1) + cellDelimiter + corner;
		
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineEdge = String.format("%n%s%s%n", margin, hEdge);
		String lineDelimiter = String.format("%n%s%s%n", margin, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineEdge);
		for(int i=0; i<numRows; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<numCols; j++)
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				if (i != numRows - 1) str.append(lineDelimiter);
				else str.append(lineEdge);	
		}
		
		return str.toString();
	}

	@Override
	public void setGame(Game game) {
		encodeGame(game);
	}
}