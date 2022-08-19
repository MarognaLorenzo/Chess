package Board;


import Pieces.*;

import java.util.ArrayList;

public class Tile {
    protected static int n = 1;
    private final int ID;
    private final Coordinate coordinate;    private ChessPiece PieceOn;

    //CONSTRUCTOR
    public Tile(){
        ID=n%65; n++;
        coordinate = getCoordinate(ID);
        PieceOn = null;
    }
    public Tile(int ID){
        this.ID = ID;
        coordinate = getCoordinate(ID);
    }
    public Tile (int c, int r){
        coordinate = new Coordinate(c , r);
        ID = getID(coordinate);
    }
    public Tile (char c, int r){
        coordinate = new Coordinate(c,r);
        ID =getID(coordinate);
    }
    public Tile (Coordinate co){
        coordinate = co;
        ID = getID(co);
    }


    //METHODS & FUNCTION

    /**
     *
     * @param t starting tile
     * @param cb current chessboard
     * @return tile with ID = -100 if the square isn't part of the chessboard
     */
    public static Tile getTopTile(Tile t, ChessBoard cb){
        if(new Tile(t.getColumn(), t.getRow()+1).isValid())
        return cb.getTiles().get(Tile.getID(t.getColumn(),t.getRow()+1));
        else return cb.getTiles().get(0);
    }
    /**
     *
     * @param t starting tile
     * @param cb current chessboard
     * @return tile with ID = -100 if the square isn't part of the chessboard
     */
    public static Tile getDownTile(Tile t, ChessBoard cb){
        if(new Tile(t.getColumn(), t.getRow()-1).isValid())
        return cb.getTiles().get(Tile.getID(t.getColumn(),t.getRow()-1));
        else return cb.getTiles().get(0);
    }
    public static Tile getRightTile(Tile t, ChessBoard cb){
        if(new Tile(t.getColumn()+1, t.getRow()).isValid())
        return cb.getTiles().get(Tile.getID(t.getColumn()+1,t.getRow()));
        else return cb.getTiles().get(0);
    }
    /**
     *
     * @param t starting tile
     * @param cb current chessboard
     * @return tile with ID = -100 if the square isn't part of the chessboard
     */
    public static Tile getLeftTile(Tile t, ChessBoard cb){
        if(new Tile(t.getColumn()-1, t.getRow()).isValid())
        return cb.getTiles().get(Tile.getID(t.getColumn()-1,t.getRow()));
        else return cb.getTiles().get(0);
    }
    /**
     *
     * @param t starting tile
     * @param cb current chessboard
     * @return tile with ID = -100 if the square isn't part of the chessboard
     */
    public static Tile getTopRightTile(Tile t, ChessBoard cb){
        if(new Tile(t.getColumn()+1, t.getRow()+1).isValid())
        return cb.getTiles().get(Tile.getID(t.getColumn()+1,t.getRow()+1));
        else return cb.getTiles().get(0);
    }
    /**
     *
     * @param t starting tile
     * @param cb current chessboard
     * @return tile with ID = -100 if the square isn't part of the chessboard
     */
    public static Tile getTopLeftTile(Tile t, ChessBoard cb){
        if(new Tile(t.getColumn()-1, t.getRow()+1).isValid())
        return cb.getTiles().get(Tile.getID(t.getColumn()-1,t.getRow()+1));
        else return cb.getTiles().get(0);
    }
    /**
     *
     * @param t starting tile
     * @param cb current chessboard
     * @return tile with ID = -100 if the square isn't part of the chessboard
     */
    public static Tile getDownRightTile(Tile t, ChessBoard cb){
        if(new Tile(t.getColumn()+1, t.getRow()-1).isValid())
        return cb.getTiles().get(Tile.getID(t.getColumn()+1,t.getRow()-1));
        else return cb.getTiles().get(0);
    }
    /**
     *
     * @param t starting tile
     * @param cb current chessboard
     * @return tile with ID = -100 if the square isn't part of the chessboard
     */
    public static Tile getDownLeftTile(Tile t, ChessBoard cb){
        if(new Tile(t.getColumn()-1, t.getRow()-1).isValid())
        return cb.getTiles().get(Tile.getID(t.getColumn()-1,t.getRow()-1));
        else return cb.getTiles().get(0);
    }

    public static ArrayList<Tile> watchRightTiles(Tile tile, ChessBoard cb) {
        ArrayList<Tile> rightTileList= new ArrayList<Tile>();
        Tile tmp;
        Tile current_position = new Tile(tile.getColumn(), tile.getRow());
        while ((tmp=Tile.getRightTile(current_position, cb)).getID() != -100) {
            if(tmp.isEmpty() || tmp.getPieceOn().getColor() != tile.getPieceOn().getColor()) {
                rightTileList.add(tmp);
                if(!tmp.isEmpty()) break;
                current_position = new Tile(tmp.getColumn(), tmp.getRow());
            } else break;

        }
        return rightTileList;
    }

    public static ArrayList<Tile> watchTopTiles(Tile tile, ChessBoard cb) {
        ArrayList<Tile> TopTileList= new ArrayList<Tile>();
        Tile tmp;
        Tile current_position = new Tile(tile.getColumn(), tile.getRow());
        while ((tmp=Tile.getTopTile(current_position, cb)).getID() != -100) {
            if(tmp.isEmpty() || tmp.getPieceOn().getColor() != tile.getPieceOn().getColor()) {
                TopTileList.add(tmp);
                if(!tmp.isEmpty()) break;
                current_position = new Tile(tmp.getColumn(), tmp.getRow());
            } else break;

        }
        return TopTileList;
    }

    public static ArrayList<Tile> watchDownTiles(Tile tile, ChessBoard cb) {
        ArrayList<Tile> LeftTileList= new ArrayList<Tile>();
        Tile tmp;
        Tile current_position = new Tile(tile.getColumn(), tile.getRow());
        while ((tmp=Tile.getDownTile(current_position, cb)).getID() != -100) {
            if(tmp.isEmpty() || tmp.getPieceOn().getColor() != tile.getPieceOn().getColor()){
                LeftTileList.add(tmp);
                if(!tmp.isEmpty()) break;
                current_position = new Tile(tmp.getColumn(), tmp.getRow());
            } else break;

        }
        return LeftTileList;
    }

    public static ArrayList<Tile> watchLeftTiles(Tile tile, ChessBoard cb) {
        ArrayList<Tile> LeftTileList= new ArrayList<Tile>();
        Tile tmp;
        Tile current_position = new Tile(tile.getColumn(), tile.getRow());
        while ((tmp=Tile.getLeftTile(current_position, cb)).getID() != -100) {
            if(tmp.isEmpty() || tmp.getPieceOn().getColor() != tile.getPieceOn().getColor()){
                LeftTileList.add(tmp);
                if(!tmp.isEmpty()) break;
                current_position = new Tile(tmp.getColumn(), tmp.getRow());
            }else break;
        }
        return LeftTileList;
    }

    public static ArrayList<Tile> watchTopRightTiles(Tile tile, ChessBoard cb) {
        ArrayList<Tile> rightTileList= new ArrayList<Tile>();
        Tile tmp;
        Tile current_position = new Tile(tile.getColumn(), tile.getRow());
        while ((tmp=Tile.getTopRightTile(current_position, cb)).getID() != -100) {
            if(tmp.isEmpty() || tmp.getPieceOn().getColor() != tile.getPieceOn().getColor()) {
                rightTileList.add(tmp);
                if(!tmp.isEmpty()) break;
                current_position = new Tile(tmp.getColumn(), tmp.getRow());
            } else break;

        }
        return rightTileList;
    }

    public static ArrayList<Tile> watchTopLeftTiles(Tile tile, ChessBoard cb) {
        ArrayList<Tile> rightTileList= new ArrayList<Tile>();
        Tile tmp;
        Tile current_position = new Tile(tile.getColumn(), tile.getRow());
        while ((tmp=Tile.getTopLeftTile(current_position, cb)).getID() != -100) {
            if(tmp.isEmpty() || tmp.getPieceOn().getColor() != tile.getPieceOn().getColor()) {
                rightTileList.add(tmp);
                if(!tmp.isEmpty()) break;
                current_position = new Tile(tmp.getColumn(), tmp.getRow());
            } else break;

        }
        return rightTileList;
    }

    public static ArrayList<Tile> watchDownRightTiles(Tile tile, ChessBoard cb) {
        ArrayList<Tile> rightTileList= new ArrayList<Tile>();
        Tile tmp;
        Tile current_position = new Tile(tile.getColumn(), tile.getRow());
        while ((tmp=Tile.getDownRightTile(current_position, cb)).getID() != -100) {
            if(tmp.isEmpty() || tmp.getPieceOn().getColor() != tile.getPieceOn().getColor()) {
                rightTileList.add(tmp);
                if(!tmp.isEmpty()) break;
                current_position = new Tile(tmp.getColumn(), tmp.getRow());
            } else break;

        }
        return rightTileList;
    }

    public static ArrayList<Tile> watchDownLeftTiles(Tile tile, ChessBoard cb) {
        ArrayList<Tile> rightTileList= new ArrayList<Tile>();
        Tile tmp;
        Tile current_position = new Tile(tile.getColumn(), tile.getRow());
        while ((tmp=Tile.getDownLeftTile(current_position, cb)).getID() != -100) {
            if(tmp.isEmpty() || tmp.getPieceOn().getColor() != tile.getPieceOn().getColor()) {
                rightTileList.add(tmp);
                if(!tmp.isEmpty()) break;
                current_position = new Tile(tmp.getColumn(), tmp.getRow());
            } else break;

        }
        return rightTileList;
    }




    /**
     *
     * @return Coordinate
     * @implNote  It calculates the corresponding coordinate starting from the id
     */
    private Coordinate getCoordinate(int id){
        int c= (id%8);
        int r= (id/8);
        if(c==0) {c=8; r -=1;}
        return new Coordinate(c,r+1);
    }
    public Coordinate getCoordinate() {
        return coordinate;
    }
    public String toString(){
        if (PieceOn instanceof King) return "K   ";
        if (PieceOn instanceof Rook) return "R   ";
        if (PieceOn instanceof Bishop) return "B   ";
        if (PieceOn instanceof Queen) return "Q   ";
        if (PieceOn instanceof Pawn) return "P   ";
        if (PieceOn instanceof Knight) return "kn  ";


        else return "-   ";
    }

    public boolean isEmpty(){return PieceOn == null; }
    public int getRow(){return coordinate.getRow();}
    public int getColumn(){return coordinate.getColumn();}
    public char getCharColumn(){return coordinate.getCharColumn();}
    public int getID(){ return ID; }

    /**
     * It sets the piece position on the tile and the tile PieceOn with the piece
     * @param piece
     */
    public void setPieceOn(ChessPiece piece){PieceOn = piece; piece.setPosition(this);}

    /**
     * It only sets the tile PieceOn with the piece
     * @param piece
     */
    public void setPieceOnOnly(ChessPiece piece){PieceOn = piece;}
    public void removePieceOn(){PieceOn = null;}
    public ChessPiece getPieceOn(){return PieceOn;}

    /**
     * it calculates the corresponding ID to some certain coordinates
     * @return ID
     */
    public int getID(Coordinate co){
        return (8* (co.getRow()-1)) + (co.getColumn());
    }
    public static int getID(int column, int row){ return 8*row-8+column; }
    public static int getID(char column, int row){int co = (int) (column-'a'+1); return 8*row-8+co;}
    public boolean isValid(){return coordinate.isValid();}
    public static void main(String[] Args){
        System.out.println(Tile.getID('h',8));
    }
}
