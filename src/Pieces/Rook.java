package Pieces;

import Board.ChessBoard;
import Board.Tile;
import Game.*;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece{

    public Rook(){
        setName("R");
    }
    public Rook(PIECE_COLOR c){ color = c; setName("R"); }
    public Rook (Tile position){
        setName("R");
        this.position = position;
    }
    public Rook(Tile position, PIECE_COLOR c){
        setName("R");
        this.position = position;
        color = c;
    }

    @Override
    public List<Tile> availableTiles(Tile tile, ChessBoard cb) {
        List <Tile> tileList = new ArrayList<>();
        tileList.addAll(Tile.watchRightTiles(tile, cb));
        tileList.addAll(Tile.watchTopTiles(tile, cb));
        tileList.addAll(Tile.watchDownTiles(tile, cb));
        tileList.addAll(Tile.watchLeftTiles(tile, cb));
        return tileList;
    }
    public static void main(String[] Args){
        ChessBoard cb = new ChessBoard();
        Game game = new Game(cb);

        Rook wr1 = new Rook(PIECE_COLOR.WHITE);
        cb.addPiece(wr1, 'e',4);
        Rook wr2 = new Rook(PIECE_COLOR.WHITE);
        cb.addPiece(wr2,'c',2);
        Rook br1 = new Rook(PIECE_COLOR.BLACK);
        cb.addPiece(br1, 'f', 1);

        while(true){
            cb.print();
            if(Move.makeAMove(Move.askForAMove(game.getNumberOfMoves(), cb),game)>=0) game.incrementNumberOfMoves();
        }
    }
}
