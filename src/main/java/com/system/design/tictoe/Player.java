package com.system.design.tictoe;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Player {
	String name;
	PieceType playingPiece;
}
