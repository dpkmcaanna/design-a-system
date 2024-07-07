package com.system.design.tictoe;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicToeGame {

	PlayingBoard playingBoard;
	Deque<Player> players;

	public static void main(String[] args) {
		TicToeGame ticToeGame = new TicToeGame();
		ticToeGame.initialiseGame();

		String result = ticToeGame.startGame();
		System.out.print("Game Result: " + result);
	}

	public void initialiseGame() {
		// System.out.print("Ent");
		playingBoard = new PlayingBoard(3);
		players = new LinkedList<Player>();
		players.add(Player.builder().name("Player-X").playingPiece(PieceType.X).build());
		players.add(Player.builder().name("Player-Y").playingPiece(PieceType.O).build());
	}

	public String startGame() {
		boolean isGameCompleted = false;
		while (!isGameCompleted) {
			Player player = players.removeFirst();

			playingBoard.printBoard();

			List<Pair> availableSpace = playingBoard.getAvailableSpace();

			if (availableSpace.isEmpty()) {
				isGameCompleted = true;
				continue;
			}

			System.out.print("Player:" + player.name + " Enter row,column: ");
			Scanner scanner = new Scanner(System.in);
			String s = scanner.nextLine();
			String[] values = s.split(",");
			int row = Integer.valueOf(values[0]);
			int col = Integer.valueOf(values[1]);

			boolean isPieceAdded = playingBoard.addPice(row, col, player.getPlayingPiece());
			if (!isPieceAdded) {
				players.addFirst(player);
				continue;
			}

			players.addLast(player);

			boolean isWinnerFound = searchWinner(row, col, player.playingPiece);
			
			if (isWinnerFound) {
				return player.name;
			}

		}
		return "Game has been tie";
	}

	public boolean searchWinner(int row, int col, PieceType pieceType) {

		boolean isMatchFoundInRow = true;
		boolean isMatchFoundInColumn = true;
		boolean isMatchFoundInDiagonal = true;
		boolean isMatchFoundInAntiDiagonal = true;

		int size = playingBoard.size;

		for (int i = 0; i < size; i++) {
			if (playingBoard.board[row][i] == null || playingBoard.board[row][i] != pieceType) {
				isMatchFoundInRow = false;
			}
		}

		for (int i = 0; i < size; i++) {
			if (playingBoard.board[i][col] == null || playingBoard.board[i][col] != pieceType) {
				isMatchFoundInColumn = false;
			}
		}

		for (int i = 0, j = 0; i < size; i++, j++) {
			if (playingBoard.board[i][j] == null || playingBoard.board[i][j] != pieceType) {
				isMatchFoundInDiagonal = false;
			}
		}

		for (int i =0, j = size - 1; i < size; i++, j--) {
			if (playingBoard.board[i][j] == null || playingBoard.board[i][j] != pieceType) {
				isMatchFoundInAntiDiagonal = false;
			}
		}

		return isMatchFoundInRow || isMatchFoundInColumn || isMatchFoundInDiagonal || isMatchFoundInAntiDiagonal;
	}

}
