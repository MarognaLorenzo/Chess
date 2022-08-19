package Pieces;

import Board.ChessBoard;
import Board.Tile;

import java.util.List;

public abstract class ChessPiece {
    private String name;
    PIECE_COLOR color;
    Tile position;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public enum PIECE_COLOR {WHITE, BLACK};


    public void setPosition(Tile t){
        position = t;
    }

    public Tile getPosition() { return position; }

    public PIECE_COLOR getColor(){return color;}


    /**
     * It gives back the list of theorically available tiles based on the current position of the piece
     * @param tile
     * @return List<Tile>
     */
    public abstract List<Tile> availableTiles(Tile tile, ChessBoard cb);
}
