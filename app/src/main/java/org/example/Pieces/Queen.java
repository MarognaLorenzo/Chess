package org.example.Pieces;


import org.example.Board.ChessBoard;
import org.example.Board.Tile;
import org.example.Game.Game;
import org.example.Game.Move;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece{

    public Queen(){setName("Q");}
    public Queen(ChessPiece.PIECE_COLOR c){ setName("Q"); color = c; }

    public Queen (Tile position){
        setName("Q");
        this.position = position;
    }
    public Queen(Tile position, PIECE_COLOR c){
        setName("Q");
        this.position = position;
        color = c;
    }
    @Override
    public List<Tile> availableTiles(Tile tile, ChessBoard cb) {
        List <Tile> tileList = new ArrayList<>();
        tileList.addAll(Tile.watchTopRightTiles(tile, cb));
        tileList.addAll(Tile.watchTopLeftTiles(tile, cb));
        tileList.addAll(Tile.watchDownRightTiles(tile, cb));
        tileList.addAll(Tile.watchDownLeftTiles(tile, cb));
        tileList.addAll(Tile.watchRightTiles(tile, cb));
        tileList.addAll(Tile.watchTopTiles(tile, cb));
        tileList.addAll(Tile.watchDownTiles(tile, cb));
        tileList.addAll(Tile.watchLeftTiles(tile, cb));
        return tileList;
    }
    public static void main(String[] Args){
        ChessBoard cb = new ChessBoard();
        Game game = new Game(cb);

        Queen wr1 = new Queen(PIECE_COLOR.WHITE);
        cb.addPiece(wr1, 'e',4);
        Queen wr2 = new Queen(PIECE_COLOR.WHITE);
        cb.addPiece(wr2,'c',2);
        Queen br1 = new Queen(PIECE_COLOR.BLACK);
        cb.addPiece(br1, 'f', 1);

        while(true){
            cb.print();
            if(Move.makeAMove(Move.askForAMove(game.getNumberOfMoves(), cb),game)>=0) game.incrementNumberOfMoves();
        }
    }
}
