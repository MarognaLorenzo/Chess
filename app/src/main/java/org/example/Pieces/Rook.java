package org.example.Pieces;


import org.example.Board.ChessBoard;
import org.example.Board.Tile;
import org.example.Game.Game;
import org.example.Game.Move;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece{

    private boolean hasEverMoved = false;
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

    public boolean isHasEverMoved() {
        return hasEverMoved;
    }

    public void setHasEverMoved(boolean hasEverMoved) {
        this.hasEverMoved = hasEverMoved;
    }
}
