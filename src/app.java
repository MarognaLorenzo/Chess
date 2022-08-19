import Game.Game;
import Game.Move;

import java.util.List;

public class app {
    public static void main(String[] Args){
        Game game = Game.initialize_Game();

        while(true){
            game.getChessBoard().print();
            if(Move.makeAMove(Move.askForAMove(game.getNumberOfMoves(), game.getChessBoard()),game)>=0) game.incrementNumberOfMoves();
        }
    }
}
