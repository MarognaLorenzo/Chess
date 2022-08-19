package Game;

import Board.ChessBoard;
import Board.Tile;
import Pieces.*;

import java.util.List;

public class Game {
    private Player WhitePlayer;

    private Player BlackPlayer;
    private int numberOfMoves;
    private ChessBoard chessBoard;
    public Game(ChessBoard cb){
        chessBoard = cb;
        WhitePlayer = new Player(ChessPiece.PIECE_COLOR.WHITE);
        BlackPlayer = new Player(ChessPiece.PIECE_COLOR.BLACK);
        numberOfMoves = 1;
    }

    public static ChessPiece.PIECE_COLOR calculateTurn (int numberOfMoves){ return (numberOfMoves%2==1) ? ChessPiece.PIECE_COLOR.WHITE: ChessPiece.PIECE_COLOR.BLACK; }

    public int getNumberOfMoves(){ return numberOfMoves; }
    public ChessBoard getChessBoard(){return chessBoard; }
    public void incrementNumberOfMoves(){numberOfMoves +=1; }

    public static void main(String[] Args){
        ChessBoard cb = new ChessBoard();
        King wk = new King(ChessPiece.PIECE_COLOR.WHITE);
        King bk = new King(ChessPiece.PIECE_COLOR.BLACK);
        King thirdking = new King(ChessPiece.PIECE_COLOR.WHITE);
        Game game = new Game(cb);
        cb.addPiece(wk,'e',1);
        cb.addPiece(bk,'e',8);

        while(true){
            cb.print();
            if(Move.makeAMove(Move.askForAMove(game.numberOfMoves, cb),game)>=0) game.numberOfMoves +=1;
        }

    }

    /**
     * It generates a new Game object with the same position of pieces of the originale Game. It can be used to test the validity of moves.
     * @param game actual game
     * @return a new object Game with the same position of the old game
     */
    public static Game generate_equal_game(Game game){
        Game result = new Game(new ChessBoard());
        for(Tile tile: game.getChessBoard().getTiles()){
            ChessPiece firstPiece = tile.getPieceOn();
            ChessPiece finalPiece = null;
            if(firstPiece == null) continue;
            if(firstPiece instanceof King) finalPiece = new King(result.getChessBoard().getTiles().get(firstPiece.getPosition().getID()), firstPiece.getColor());
            else if(firstPiece instanceof Bishop) finalPiece = new Bishop(result.getChessBoard().getTiles().get(firstPiece.getPosition().getID()), firstPiece.getColor());
            else if(firstPiece instanceof Knight) finalPiece = new Knight(result.getChessBoard().getTiles().get(firstPiece.getPosition().getID()), firstPiece.getColor());
            else if(firstPiece instanceof Pawn) finalPiece = new Pawn(result.getChessBoard().getTiles().get(firstPiece.getPosition().getID()), firstPiece.getColor());
            else if(firstPiece instanceof Queen) finalPiece = new Queen(result.getChessBoard().getTiles().get(firstPiece.getPosition().getID()), firstPiece.getColor());
            else if(firstPiece instanceof Rook) finalPiece = new Rook(result.getChessBoard().getTiles().get(firstPiece.getPosition().getID()), firstPiece.getColor());
            result.getChessBoard().getTiles().get(tile.getID()).setPieceOn(finalPiece);
        }
        for(Tile t: result.getChessBoard().getTiles()){
            if(!t.isEmpty()) {
                if(t.getPieceOn().getColor() == ChessPiece.PIECE_COLOR.WHITE) result.WhitePlayer.getChessPieceList().add(t.getPieceOn());
                else result.BlackPlayer.getChessPieceList().add(t.getPieceOn());
            }
        }
        result.numberOfMoves = game.getNumberOfMoves();
        return result;
    }

    public static Game initialize_Game(){
        ChessBoard cb = new ChessBoard();
        Game game = new Game(cb);

        King wk = new King(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wk,'e',1);
        King bk = new King(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bk, 'e', 8);

        Rook wr1 = new Rook(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wr1,'a',1);
        Rook wr2 = new Rook(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wr2,'h',1);
        Rook br1 = new Rook(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(br1,'a',8);
        Rook br2 = new Rook(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(br2,'h',8);

        Bishop wb1 = new Bishop(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wb1,'c',1);
        Bishop wb2 = new Bishop(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wb2,'f',1);
        Bishop bb1 = new Bishop(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bb1,'c',8);
        Bishop bb2 = new Bishop(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bb2,'f',8);

        Queen wq = new Queen(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wq,'d',1);
        Queen bq = new Queen(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bq,'d',8);

        Pawn wp1 = new Pawn(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wp1,'a',2);
        Pawn wp2 = new Pawn(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wp2,'b',2);
        Pawn wp3 = new Pawn(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wp3,'c',2);
        Pawn wp4 = new Pawn(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wp4,'d',2);
        Pawn wp5 = new Pawn(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wp5,'e',2);
        Pawn wp6 = new Pawn(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wp6,'f',2);
        Pawn wp7 = new Pawn(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wp7,'g',2);
        Pawn wp8 = new Pawn(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wp8,'h',2);

        Pawn bp1 = new Pawn(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bp1,'a',7);
        Pawn bp2 = new Pawn(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bp2,'b',7);
        Pawn bp3 = new Pawn(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bp3,'c',7);
        Pawn bp4 = new Pawn(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bp4,'d',7);
        Pawn bp5 = new Pawn(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bp5,'e',7);
        Pawn bp6 = new Pawn(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bp6,'f',7);
        Pawn bp7 = new Pawn(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bp7,'g',7);
        Pawn bp8 = new Pawn(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bp8,'h',7);

        Knight wkn1 = new Knight(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wkn1,'b',1);
        Knight wkn2 = new Knight(ChessPiece.PIECE_COLOR.WHITE);
        cb.addPiece(wkn2,'g',1);
        Knight bkn1 = new Knight(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bkn1,'b',8);
        Knight bkn2 = new Knight(ChessPiece.PIECE_COLOR.BLACK);
        cb.addPiece(bkn2,'g',8);
        for(Tile t: cb.getTiles()){
            if(!t.isEmpty()) {
                if(t.getPieceOn().getColor() == ChessPiece.PIECE_COLOR.WHITE) game.WhitePlayer.getChessPieceList().add(t.getPieceOn());
                else game.BlackPlayer.getChessPieceList().add(t.getPieceOn());
            }
        }
        //System.out.println(game.WhitePlayer.getChessPieceList());
        return game;
    }

    public Player getWhitePlayer() {
        return WhitePlayer;
    }

    public void setWhitePlayer(Player whitePlayer) {
        WhitePlayer = whitePlayer;
    }

    public Player getBlackPlayer() {
        return BlackPlayer;
    }

    public void setBlackPlayer(Player blackPlayer) {
        BlackPlayer = blackPlayer;
    }
}
