package org.example.GUI;

import javafx.scene.shape.Rectangle;
import org.example.Board.Tile;

import java.io.FileNotFoundException;

public class G_Tiles extends Rectangle {
    Tile tile;
    G_Piece g_pieceOn;
    G_ChessBoard Gcb;
    public G_Tiles(Tile tile, G_ChessBoard graphic_chess_board) throws FileNotFoundException {
        Gcb = graphic_chess_board;
        this.tile = tile;
        this.setWidth(80);
        this.setHeight(80);
        this.setOnMouseClicked(e -> {
            try {
                G_Move.add_to_buffer(this.tile.getCoordinate().toString(), this.Gcb);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        g_pieceOn = new G_Piece(tile.getPieceOn(), Gcb);
    }
    public static void printTile(Tile tile){System.out.println(tile.getCharColumn()+ String.valueOf(tile.getRow())+ " "+ tile);}
}
