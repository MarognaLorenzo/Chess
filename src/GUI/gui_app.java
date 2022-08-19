package GUI;

import Board.ChessBoard;
import Game.*;
import Pieces.ChessPiece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;


public class gui_app extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, URISyntaxException {
        Stage window = primaryStage;
        G_ChessBoard board = new G_ChessBoard();
        ChessBoard cb = board.getChessBoard();
        Game game = board.getGame();
        TextArea textArea = new TextArea("Content of text area");
        Button actionButton = new Button("Title of the button");
        TextField textField = new TextField();
        actionButton.setOnAction( e -> action(game, textArea, textField));
        VBox vBox = new VBox (actionButton, textField, textArea);

        HBox bigHorizontalBox= new HBox(board, vBox);
        Scene scene = new Scene(bigHorizontalBox);
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("Self-chess Official Game");
        window.show();
    }

    private void action(Game game, TextArea textArea, TextField textField) {
        List<Move> moves = Move.getAllAvailableMove(game.getBlackPlayer(), game);
        System.out.println(moves);
        textArea.setText(Move.KingInCheck(ChessPiece.PIECE_COLOR.BLACK, game) ? "true": "false");
        Game test_game = Game.generate_equal_game(game);
        System.out.println(test_game.getChessBoard());
        textArea.appendText(Move.is_this_move_putting_my_king_in_check(game, textField.getText(), ChessPiece.PIECE_COLOR.BLACK)? "\n true": "\nfalse");
        textArea.appendText("\n"+ String.valueOf(Move.checkingControl(ChessPiece.PIECE_COLOR.WHITE, game)));
    }
}
