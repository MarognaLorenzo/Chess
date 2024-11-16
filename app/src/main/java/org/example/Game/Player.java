package org.example.Game;


import org.example.Pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private ChessPiece.PIECE_COLOR playerColor;
    private List<ChessPiece> chessPieceList;
    public Player(ChessPiece.PIECE_COLOR c){
        setPlayerColor(c);
        chessPieceList = new ArrayList<>();
    }

    public ChessPiece.PIECE_COLOR getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(ChessPiece.PIECE_COLOR playerColor) {
        this.playerColor = playerColor;
    }

    public List<ChessPiece> getChessPieceList() {
        return chessPieceList;
    }

    public void setChessPieceList(List<ChessPiece> chessPieceList) {
        this.chessPieceList = chessPieceList;
    }
}
