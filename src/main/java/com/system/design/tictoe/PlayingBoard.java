package com.system.design.tictoe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class PlayingBoard {
	
	int size;
	PieceType[][] board;
	
	public PlayingBoard(int size) {
		board = new PieceType[size][size];
		this.size = size;
	}
	
	public boolean addPice(int row, int column, PieceType pieceType) {
		//System.out.println("size, Row, column:"+ size + "," + row + ", " + column + " val at: " + board[row][column]);
		if(row >= size || column >= size || board[row][column] != null) {
			System.out.println("No space available, plz try again");
			return false;
		}
		board[row][column] = pieceType;
		return true;
	}
	
	public List<Pair> getAvailableSpace() {
		List<Pair> availbeSpace = new LinkedList<Pair>();
		int row = board.length;
		int col = board[0].length;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(board[i][j] == null) {
					availbeSpace.add(Pair.builder().row(i).column(col).build());
				}
			}
		}
		return availbeSpace;
	}
	
	public void printBoard() {
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(board[i][j] != null) {
					System.out.print(board[i][j] + "  ");
				} else {
					System.out.print("   ");
				}
				System.out.print(" | ");
			}
			System.out.println("");
		}
	}
}


@Getter
@Setter
@Builder(toBuilder = true)
class Pair {
	int row;
	int column;
}