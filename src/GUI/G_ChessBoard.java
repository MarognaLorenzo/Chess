package GUI;

import Board.*;
import Game.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class G_ChessBoard extends GridPane {
    private final ChessBoard cb;

    private final List <G_Tiles> tiles = new ArrayList<G_Tiles>();
    private final Game game;
    private G_Tiles tmp_tile;
    public G_ChessBoard() throws FileNotFoundException, URISyntaxException {
        game = Game.initialize_Game();
        cb = game.getChessBoard();
        G_Tiles SHITPANE = new G_Tiles(cb.getTiles().get(0), this);
        SHITPANE.setHeight(0); SHITPANE.setWidth(0);
        G_ChessBoard.setConstraints(SHITPANE,0,0);
        this.getChildren().add(0,SHITPANE);
        tiles.add(SHITPANE);

        for(int r=1; r<=8; r++){
            for(int c=1; c<=8; c++){
                tmp_tile = new G_Tiles(cb.getTiles().get(Tile.getID(c,r)), this);
                if (((r % 2 == 0 && c % 2 == 0) || (r % 2 == 1 && c % 2 == 1))) {
                    tmp_tile.setFill(Color.web("rgb(118, 150, 86)"));
                } else {
                    tmp_tile.setFill(Color.web("rgb(238, 238, 210)"));
                }
                StackPane tmp_StackPane = new StackPane(tmp_tile, tmp_tile.g_pieceOn);
                G_ChessBoard.setConstraints(tmp_StackPane, c, 8-r);
                this.getChildren().add(tmp_StackPane);
                tiles.add(tmp_tile);
            }
        }
    }
    public void updateChessBoard() throws FileNotFoundException {
        for(int r=1; r<=8; r++){
            for(int c=1; c<=8; c++){
                //G_ChessBoard.clearConstraints();
                StackPane tmp_StackPane = new StackPane(tmp_tile, tmp_tile.g_pieceOn);
                G_ChessBoard.setConstraints(tmp_StackPane, c, 8-r);
                this.getChildren().add(tmp_StackPane);
                tiles.add(tmp_tile);
            }
        }
    }
    public ChessBoard getChessBoard() {
        return cb;
    }

    public List<G_Tiles> getTiles() {
        return tiles;
    }

    public Game getGame() {
        return game;
    }
    public static void main(String[] Args){
    }

    public void doAMove() throws FileNotFoundException {
        int type_of_move = Move.makeAMove(G_Move.buffer, this.getGame());
        if( type_of_move >=0 ){
            this.getChessBoard().print();
            this.getGame().incrementNumberOfMoves();
            G_Move.makeAMove(this, G_Move.buffer, type_of_move%2==1, type_of_move);
        }
        /*else if (//Il colore del pezzo da muovere è uguale al colore del giocatore che deve muovere
                Game.calculateTurn(this.game.getNumberOfMoves())
                ==
                this.getChessBoard().getTiles().get(Tile.getID(G_Move.buffer.charAt(0), Integer.parseInt(G_Move.buffer.substring(1,2)))).getPieceOn().getColor()
                )   G_Move.buffer = G_Move.buffer.substring(0,2);*/

    }



}
