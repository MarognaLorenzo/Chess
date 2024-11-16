package org.example.GUI;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.example.Board.Tile;
import org.example.Game.Game;
import org.example.Game.Move;

import java.io.FileNotFoundException;

public class G_Move {
    Move move;
    static String buffer="";
    public G_Move() {
        buffer = null;
    }

    public G_Move(Move m){
        move= m;
        buffer = null;
    }
    public static void makeAMove(G_ChessBoard Gcb, String st, boolean isACaptuirngMove, int type_of_move) throws FileNotFoundException {
        Game game = Gcb.getGame();

        G_Piece support;
        G_Tiles starting_graphic_tile =
                Gcb.getTiles().get(
                        game.getChessBoard().getTiles().get(Tile.getID(st.charAt(0),Integer.parseInt(st.substring(1,2)))).getID());
        G_Tiles arriving_graphic_tile =
                Gcb.getTiles().get(
                        game.getChessBoard().getTiles().get(Tile.getID(st.charAt(2),Integer.parseInt(st.substring(3,4)))).getID());

        support = starting_graphic_tile.g_pieceOn;
        starting_graphic_tile.g_pieceOn = arriving_graphic_tile.g_pieceOn;
        arriving_graphic_tile.g_pieceOn = support;

        /*
            board è una gridpane di stackpane, ogni stackpane con getchildren è una lista di nodi dei quali il primo è un
             rectangle e il secondo è un grapghic piece che estende l'image view
         */

        Node stackPanedellaCasellaDiPartenza = null;
        Node stackPanedellaCasellaDiArrivo = null;
        ObservableList<Node> ListaDiStackPanes = Gcb.getChildren();
        for(Node node: ListaDiStackPanes){
            if(GridPane.getRowIndex(node) == 8- starting_graphic_tile.tile.getRow() && GridPane.getColumnIndex(node) == starting_graphic_tile.tile.getColumn()) {
                stackPanedellaCasellaDiPartenza = node;
                break;
            }
        }
        for(Node node: ListaDiStackPanes){
            if(GridPane.getRowIndex(node) == 8-arriving_graphic_tile.tile.getRow() && GridPane.getColumnIndex(node) == arriving_graphic_tile.tile.getColumn()) {
                stackPanedellaCasellaDiArrivo = node;
                break;
            }
        }
        if (stackPanedellaCasellaDiPartenza instanceof StackPane
            && ((StackPane) stackPanedellaCasellaDiPartenza).getChildren().get(0) instanceof G_Tiles) {
            if(isACaptuirngMove){
                G_Piece empty_piece = new G_Piece(null, Gcb);
                ((StackPane) stackPanedellaCasellaDiPartenza).getChildren().set(1, empty_piece);
                starting_graphic_tile.g_pieceOn = empty_piece;
            } else {
                ((StackPane) stackPanedellaCasellaDiPartenza).getChildren().set(1, ((G_Tiles) ((StackPane) stackPanedellaCasellaDiPartenza).getChildren().get(0)).g_pieceOn);
            }

        }

        if (stackPanedellaCasellaDiArrivo instanceof StackPane
                && ((StackPane) stackPanedellaCasellaDiArrivo).getChildren().get(0) instanceof G_Tiles){
            if (((StackPane) stackPanedellaCasellaDiArrivo).getChildren().size()<2){
                ((StackPane) stackPanedellaCasellaDiArrivo).getChildren().add(1, ((G_Tiles) ((StackPane) stackPanedellaCasellaDiArrivo).getChildren().get(0)).g_pieceOn);
            }
            else {
                ((StackPane) stackPanedellaCasellaDiArrivo).getChildren().set(1, ((G_Tiles) ((StackPane) stackPanedellaCasellaDiArrivo).getChildren().get(0)).g_pieceOn);
            }
        }

        if(type_of_move==4 || type_of_move == 5){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Congratulation!");
            a.setHeaderText("It's checkmate!");
            a.show();
            a.setOnCloseRequest(e-> System.exit(0));
        }

    }

    public static void add_to_buffer(String string_to_add, G_ChessBoard Gcb) throws FileNotFoundException {
        System.out.println("BUFFER = "+ buffer+ "trying to add: " + string_to_add);
        assert (string_to_add.length() == 2);

        if (buffer.isEmpty() || buffer.length() == 4) {
            buffer = string_to_add;
        } else {
            buffer = buffer.concat(string_to_add);
            Gcb.doAMove();
        }
    }


}
