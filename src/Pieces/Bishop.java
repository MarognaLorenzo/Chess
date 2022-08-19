package Pieces;

import Board.ChessBoard;
import Board.Tile;
import Game.*;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(){setName("B");}
    public Bishop(PIECE_COLOR c){ setName("B"); color = c; }

    public Bishop (Tile position){
        setName("B");
        this.position = position;
    }
    public Bishop(Tile position, PIECE_COLOR c){
        setName("B");
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
        return tileList;
    }

    public static void main(String[] Args){
        ChessBoard cb = new ChessBoard();
        Game game = new Game(cb);

        Bishop wr1 = new Bishop(PIECE_COLOR.WHITE);
        cb.addPiece(wr1, 'e',4);
        Bishop wr2 = new Bishop(PIECE_COLOR.WHITE);
        cb.addPiece(wr2,'c',2);
        Bishop br1 = new Bishop(PIECE_COLOR.BLACK);
        cb.addPiece(br1, 'f', 1);

        while(true){
            cb.print();
            if(Move.makeAMove(Move.askForAMove(game.getNumberOfMoves(), cb),game)>=0) game.incrementNumberOfMoves();
        }
    }
}
