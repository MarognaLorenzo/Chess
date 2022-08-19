package Pieces;

import Board.ChessBoard;
import Board.Tile;
import Game.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece{

    public static void main(String[] Args){
        ChessBoard cb = new ChessBoard();
        Game game = new Game(cb);

        Pawn wr1 = new Pawn(PIECE_COLOR.WHITE);
        cb.addPiece(wr1, 'e',4);
        Pawn wr2 = new Pawn(PIECE_COLOR.WHITE);
        cb.addPiece(wr2,'e',2);
        Pawn br1 = new Pawn(PIECE_COLOR.BLACK);
        cb.addPiece(br1, 'f', 5);

        while(true){
            cb.print();
            if(Move.makeAMove(Move.askForAMove(game.getNumberOfMoves(), cb),game)>=0) game.incrementNumberOfMoves();
        }
    }

    public Pawn(){setName("P");}
    public Pawn(ChessPiece.PIECE_COLOR c){ setName("P"); color = c; }

    public Pawn (Tile position){
        setName("P");
        this.position = position;
    }
    public Pawn(Tile position, PIECE_COLOR c){
        setName("P");
        this.position = position;
        color = c;
    }

    @Override
    public List<Tile> availableTiles(Tile position, ChessBoard cb) {
        List<Tile> availableTiles = new ArrayList<>();
        Tile tmp;
        /*White pawns go up, black ones come down*/
        if(position.getPieceOn().getColor() == PIECE_COLOR.WHITE){
            if(( tmp = Tile.getTopTile (position, cb)) .getID() != -100 && tmp.isEmpty()) availableTiles.add(cb.getTiles().get(tmp.getID()));
            if(( tmp = Tile.getTopRightTile(position, cb)) .getID() != -100 && !tmp.isEmpty() &&tmp.getPieceOn().getColor()==PIECE_COLOR.BLACK) availableTiles.add(cb.getTiles().get(tmp.getID()));
            if(( tmp = Tile.getTopLeftTile(position, cb)) .getID() != -100 && !tmp.isEmpty() && tmp.getPieceOn().getColor()==PIECE_COLOR.BLACK) availableTiles.add(cb.getTiles().get(tmp.getID()));
            if( position.getRow() == 2) if( (tmp = Tile.getTopTile(Tile.getTopTile(position, cb), cb)) .isEmpty() && Tile.getTopTile(position, cb).isEmpty() ) availableTiles.add(cb.getTiles().get(tmp.getID()));
        } else {
            if(( tmp = Tile.getDownTile(position, cb)) .getID() != -100 && tmp.isEmpty()) availableTiles.add(cb.getTiles().get(tmp.getID()));
            if(( tmp = Tile.getDownRightTile(position, cb)) .getID() != -100 && !tmp.isEmpty() && tmp.getPieceOn().getColor()==PIECE_COLOR.WHITE) availableTiles.add(cb.getTiles().get(tmp.getID()));
            if(( tmp = Tile.getDownLeftTile(position, cb)) .getID() != -100 && !tmp.isEmpty() && tmp.getPieceOn().getColor()==PIECE_COLOR.WHITE) availableTiles.add(cb.getTiles().get(tmp.getID()));
            if( position.getRow() == 7) if( (tmp = Tile.getDownTile(Tile.getDownTile(position, cb), cb)) .isEmpty() && Tile.getDownTile(position, cb).isEmpty() ) availableTiles.add(cb.getTiles().get(tmp.getID()));
        }
        return availableTiles;
    }
}
