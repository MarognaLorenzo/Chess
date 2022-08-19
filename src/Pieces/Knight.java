package Pieces;

import Board.ChessBoard;
import Board.Tile;
import Game.*;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece{
    public Knight(){
        setName("kn");
    }
    public Knight(PIECE_COLOR c){ color = c; setName("kn"); }
    public Knight (Tile position){
        setName("kn");
        this.position = position;
    }
    public Knight(Tile position, PIECE_COLOR c){
        setName("kn");
        this.position = position;
        color = c;
    }


    public static void main(String[] Args){
        ChessBoard cb = new ChessBoard();
        Game game = new Game(cb);

        Knight wr1 = new Knight(PIECE_COLOR.WHITE);
        cb.addPiece(wr1, 'e',4);
        Knight wr2 = new Knight(PIECE_COLOR.WHITE);
        cb.addPiece(wr2,'e',2);
        Knight br1 = new Knight(PIECE_COLOR.BLACK);
        cb.addPiece(br1, 'f', 5);

        while(true){
            cb.print();
            if(Move.makeAMove(Move.askForAMove(game.getNumberOfMoves(), cb),game)>=0) game.incrementNumberOfMoves();
        }
    }


    /**
     * It watches up, down, right and left using the Y rule to get accessible squares.
     *
     * @param position
     * @param cb
     * @return
     */
    @Override
    public List<Tile> availableTiles(Tile position, ChessBoard cb) {
        List<Tile> availableTiles = new ArrayList<Tile>();
        Tile tmp1, tmp2;
        if ((tmp1 = Tile.getTopTile(position, cb)).getID() != -100 ){
            if((tmp2 = Tile.getTopRightTile(tmp1, cb)).getID() != -100 && (tmp2.isEmpty() || tmp2.getPieceOn().getColor() != position.getPieceOn().getColor()) ) availableTiles.add(tmp2);
            if((tmp2 = Tile.getTopLeftTile(tmp1, cb)).getID() != -100 && (tmp2.isEmpty() || tmp2.getPieceOn().getColor() != position.getPieceOn().getColor()) ) availableTiles.add(tmp2);
        }
        if ((tmp1 = Tile.getDownTile(position, cb)).getID() != -100 ){
            if((tmp2 = Tile.getDownRightTile(tmp1, cb)).getID() != -100 && (tmp2.isEmpty() || tmp2.getPieceOn().getColor() != position.getPieceOn().getColor()) ) availableTiles.add(tmp2);
            if((tmp2 = Tile.getDownLeftTile(tmp1, cb)).getID() != -100 && (tmp2.isEmpty() || tmp2.getPieceOn().getColor() != position.getPieceOn().getColor()) ) availableTiles.add(tmp2);
        }
        if ((tmp1 = Tile.getRightTile(position, cb)).getID() != -100 ){
            if((tmp2 = Tile.getTopRightTile(tmp1, cb)).getID() != -100 && (tmp2.isEmpty() || tmp2.getPieceOn().getColor() != position.getPieceOn().getColor()) ) availableTiles.add(tmp2);
            if((tmp2 = Tile.getDownRightTile(tmp1, cb)).getID() != -100 && (tmp2.isEmpty() || tmp2.getPieceOn().getColor() != position.getPieceOn().getColor()) ) availableTiles.add(tmp2);
        }
        if ((tmp1 = Tile.getLeftTile(position, cb)).getID() != -100 ){
            if((tmp2 = Tile.getTopLeftTile(tmp1, cb)).getID() != -100 && (tmp2.isEmpty() || tmp2.getPieceOn().getColor() != position.getPieceOn().getColor()) ) availableTiles.add(tmp2);
            if((tmp2 = Tile.getDownLeftTile(tmp1, cb)).getID() != -100 && (tmp2.isEmpty() || tmp2.getPieceOn().getColor() != position.getPieceOn().getColor()) ) availableTiles.add(tmp2);
        }
        return availableTiles;
    }
}
