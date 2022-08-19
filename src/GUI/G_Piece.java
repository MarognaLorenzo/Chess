package GUI;

import Pieces.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

public class G_Piece extends ImageView {
    ChessPiece piece;
    G_ChessBoard Gcb;
    public G_Piece(ChessPiece p, G_ChessBoard graphic_chess_board) throws FileNotFoundException, URISyntaxException {
        piece = p;
        Gcb = graphic_chess_board;
        String basic_path = new File("").getAbsolutePath();
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
            if(piece != null ) {
                if (piece instanceof King) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\whiteKing.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\blackKing.png"))));
                    }
                }
                if (piece instanceof Queen) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\whiteQueen.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\blackQueen.png"))));
                    }
                }
                if (piece instanceof Pawn) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\whitePawn.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\blackPawn.png"))));
                    }
                }
                if (piece instanceof Bishop) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\whiteBishop.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\blackBishop.png"))));
                    }
                }
                if (piece instanceof Knight) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\whiteKnight.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\blackKnight.png"))));
                    }
                }
                if (piece instanceof Rook) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\whiteRook.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("\\src\\Images\\blackRook.png"))));
                    }
                }
            }
        } else {
            if(piece != null ) {
                if (piece instanceof King) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/whiteKing.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/blackKing.png"))));
                    }
                }
                if (piece instanceof Queen) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/whiteQueen.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/blackQueen.png"))));
                    }
                }
                if (piece instanceof Pawn) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/whitePawn.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/blackPawn.png"))));
                    }
                }
                if (piece instanceof Bishop) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/whiteBishop.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/blackBishop.png"))));
                    }
                }
                if (piece instanceof Knight) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/whiteKnight.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/blackKnight.png"))));
                    }
                }
                if (piece instanceof Rook) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/whiteRook.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/Images/blackRook.png"))));
                    }
                }
            }
        }

        this.setFitHeight(80);
        this.setFitWidth(80);
        this.setOnMouseClicked(e -> {
            try {
                G_Move.add_to_buffer(this.piece.getPosition().getCoordinate().toString(), this.Gcb);
            } catch (FileNotFoundException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
}
