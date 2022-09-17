package Pieces;

import Board.ChessBoard;
import Board.Tile;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece{
    private boolean hasEverMoved = false;

    public King(){
        setName("K");
    }
    public King(PIECE_COLOR c){ color = c; setName("K"); }
    public King (Tile position){
        setName("K");
        this.position = position;
    }
    public King(Tile position, PIECE_COLOR c){
        setName("K");
        this.position = position;
        color = c;
    }
    @Override
    public Tile getPosition() {
        return position;
    }

    /**
     * it looks around the 8 squares for the king and it also checks that no other pieces of the same color are on the square
     * @param position
     * @param cb
     * @return
     */
    @Override
    public List<Tile> availableTiles(Tile position, ChessBoard cb) {
        List<Tile> availableTiles = new ArrayList<>();
        Tile tmp;
        if(((tmp=Tile.getTopTile(position, cb)).getID()!=-100)&&(tmp.isEmpty() || tmp.getPieceOn().getColor() != position.getPieceOn().getColor())) availableTiles.add(cb.getTiles().get(tmp.getID()));
        if(((tmp=Tile.getDownTile(position, cb)).getID()!=-100)&&(tmp.isEmpty() || tmp.getPieceOn().getColor() != position.getPieceOn().getColor())) availableTiles.add(cb.getTiles().get(tmp.getID()));
        if(((tmp=Tile.getRightTile(position, cb)).getID()!=-100)&&(tmp.isEmpty() || tmp.getPieceOn().getColor() != position.getPieceOn().getColor())) availableTiles.add(cb.getTiles().get(tmp.getID()));
        if(((tmp=Tile.getLeftTile(position, cb)).getID()!=-100)&&(tmp.isEmpty() || tmp.getPieceOn().getColor() != position.getPieceOn().getColor())) availableTiles.add(cb.getTiles().get(tmp.getID()));
        if(((tmp=Tile.getTopRightTile(position, cb)).getID()!=-100)&&(tmp.isEmpty() || tmp.getPieceOn().getColor() != position.getPieceOn().getColor())) availableTiles.add(cb.getTiles().get(tmp.getID()));
        if(((tmp=Tile.getTopLeftTile(position, cb)).getID()!=-100)&&(tmp.isEmpty() || tmp.getPieceOn().getColor() != position.getPieceOn().getColor())) availableTiles.add(cb.getTiles().get(tmp.getID()));
        if(((tmp=Tile.getDownRightTile(position, cb)).getID()!=-100)&&(tmp.isEmpty() || tmp.getPieceOn().getColor() != position.getPieceOn().getColor())) availableTiles.add(cb.getTiles().get(tmp.getID()));
        if(((tmp=Tile.getDownLeftTile(position, cb)).getID()!=-100)&&(tmp.isEmpty() || tmp.getPieceOn().getColor() != position.getPieceOn().getColor())) availableTiles.add(cb.getTiles().get(tmp.getID()));
        //ABSOLOTLY NEEDS TO BE DOUBLE-CHECKED
        if(!hasEverMoved && cb.getTiles().get(Tile.getID('h',1)).getPieceOn() instanceof Rook && !((Rook) cb.getTiles().get(Tile.getID('h',1)).getPieceOn()).isHasEverMoved() && cb.getTiles().get(Tile.getID('g',1)).isEmpty()){
            System.out.println("short castle for white is available");
        }
        return availableTiles;
    }

    public static void main(String[] Args){
        King king = new King(new Tile(1,4));
        System.out.println(king.availableTiles(king.position,new ChessBoard()));
    }

    public boolean isHasEverMoved() {
        return hasEverMoved;
    }

    public void setHasEverMoved(boolean hasEverMoved) {
        this.hasEverMoved = hasEverMoved;
    }
}
