package org.example.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.Pieces.ChessPiece;
import org.example.Pieces.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class G_Piece extends ImageView {
    ChessPiece piece;
    G_ChessBoard Gcb;
    public G_Piece(ChessPiece p, G_ChessBoard graphic_chess_board) throws FileNotFoundException {
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
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/whiteKing.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/blackKing.png"))));
                    }
                }
                if (piece instanceof Queen) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/whiteQueen.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/blackQueen.png"))));
                    }
                }
                if (piece instanceof Pawn) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/whitePawn.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/blackPawn.png"))));
                    }
                }
                if (piece instanceof Bishop) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/whiteBishop.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/blackBishop.png"))));
                    }
                }
                if (piece instanceof Knight) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/whiteKnight.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/blackKnight.png"))));
                    }
                }
                if (piece instanceof Rook) {
                    if (piece.getColor() == ChessPiece.PIECE_COLOR.WHITE)
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/whiteRook.png"))));
                    else {
                        this.setImage(new Image(new FileInputStream(basic_path.concat("/src/main/java/org/example/Images/blackRook.png"))));
                    }
                }
            }
        }

        this.setFitHeight(80);
        this.setFitWidth(80);
        this.setOnMouseClicked(e -> {
            try {
                G_Move.add_to_buffer(this.piece.getPosition().getCoordinate().toString(), this.Gcb);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
}
