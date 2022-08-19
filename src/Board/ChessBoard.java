package Board;

import Pieces.ChessPiece;
import Pieces.King;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private List<Tile> tiles = new ArrayList<>();

    public static void main(String[]Args){
        ChessBoard cb = new ChessBoard();
        King k01=new King();
        cb.addPiece(k01,'e',1);
        System.out.println(cb);

    }

    //CONSTRUCTOR
    public ChessBoard(){
        getTiles().add(new Tile(-100)); // faccio partire l'indice della lista con 1 come l'ID, così è più facile gestire ( forse )
         for (int i=0; i<64; i++)  getTiles().add(new Tile());
         Tile.n ++;
     }

    //METHODS
    public String toString() {
        StringBuilder st= new StringBuilder();
        System.out.println("Chess Board\n");
        for(int i=7; i>=0; i--){
            for( int j=1; j<9; j++){
                st.append(getTiles().get(8 * i + j));
            }
            st.append("\n");
        }
        return st.toString();
    }

    public void print(){
        System.out.println(this.toString());
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    /**
     * It sets the piece position with the tile and the tile Piece with the given piece
     * @param piece the piece to move
     * @param column the column in which you want it
     * @param row the row in which you want it
     */
    public void addPiece(ChessPiece piece, char column, int row){
        piece.setPosition(tiles.get(Tile.getID( column ,row)));
        tiles.get(Tile.getID( column ,row)).setPieceOn(piece);
    }
    public void removePiece (char column, int row){
        tiles.get(Tile.getID((int) (column-'a'+1)   ,row)).getPieceOn().setPosition(null);
        tiles.get(Tile.getID((int) (column-'a'+1)   ,row)).setPieceOnOnly(null);
    }

}
