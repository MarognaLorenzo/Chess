package Game;

import Board.ChessBoard;
import Board.Coordinate;
import Board.Tile;
import Pieces.ChessPiece;
import Pieces.King;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Move {
    private String string; //2122
    private ChessPiece pieceToMove;
    private Tile startingTile;
    private Tile arrivingTile;

    public static void main(String[] Args){
        ChessBoard cb =new ChessBoard();
        King WK = new King();
        cb.addPiece(WK,'h',8);
        cb.print();
        Game game = new Game(cb);
        if(WK.getPosition().isValid()) System.out.println("CIAO");
        Move.makeAMove(WK, WK.getPosition(), cb.getTiles().get(Tile.getID('g',7)),cb );
        cb.print();
    }

    public Move(ChessPiece piece, Tile sT, Tile aT){
        setPieceToMove(piece);
        setStartingTile(sT);
        setArrivingTile(aT);
        string = startingTile.getCharColumn() + String.valueOf(startingTile.getRow()) +
                arrivingTile.getCharColumn() + String.valueOf(arrivingTile.getRow());
    }

    /**
     * It returns true if starting and arriving tiles exists and if the arriving tile is avilable for the selected piece from the starting position and
     * it also checks wheter there is a piece in the starting tile and whether the starting tile corresponds with the piece position
     */
    public boolean isValid(ChessBoard cb){
        return startingTile.isValid() && arrivingTile.isValid() && pieceToMove.availableTiles(pieceToMove.getPosition(),cb).contains(arrivingTile) && pieceToMove!=null && pieceToMove.getPosition()==startingTile;
    }

    public void consumeMove(){
        pieceToMove.setPosition(arrivingTile);
        arrivingTile.setPieceOn(pieceToMove);
        startingTile.removePieceOn();
    };

    public static void makeAMove(ChessPiece piece, Tile sT, Tile aT, ChessBoard cb){
        Move move1 = new Move(piece, piece.getPosition(), cb.getTiles().get(aT.getID()));
        if (move1.isValid(cb)) move1.consumeMove();
        else System.out.println("Mossa non valida");
    }

    /**
     * It makes the move for the piece on the starting position to the tile in the arriving position, it depends a lot on how the user does the move
     * It won't do it if either the move is not valid (check for the isValid(Chessboard cb) function) or the piece color is different from the color that has to move in that turn
     * @param st the string with only starting tile and arriving tile such as b2c3
     * @param  game it is used only to have all the game datas
     * @return -1 if impossible, 0 if it is possible, 1 if it is a capturing move, 2 if it is check, 3 if it is check by capturing, 4 if it is checkmate, 5 if it is checkmating by capturing
     */
    public static int makeAMove(String st, Game game){
        if (st.charAt(2) == 't'){Move.printAvailableTiles(st.charAt(0), Integer.parseInt(st.substring(1,2)), game); return 0;}
        try {
            if(game.getChessBoard().getTiles().get(Tile.getID(st.charAt(0),Integer.parseInt(st.substring(1,2)))).getPieceOn()==null) {System.out.println("Mossa non valida"); return -1;}

            Move move = new Move(game.getChessBoard().getTiles().get(Tile.getID(st.charAt(0),Integer.parseInt(st.substring(1,2)))).getPieceOn(),
                    game.getChessBoard().getTiles().get(Tile.getID(st.charAt(0),Integer.parseInt(st.substring(1,2)))),
                    game.getChessBoard().getTiles().get(Tile.getID(st.charAt(2),Integer.parseInt(st.substring(3,4)))));

        //It will check both that the move is valid and the fact that the piece color must be the same of the player turn
            if(move.isValid(game.getChessBoard()) &&
                    move.pieceColorEqualPlayerColor(game, st)
                    && !Move.is_this_move_putting_my_king_in_check(game, st, Game.calculateTurn(game.getNumberOfMoves()))
            ) {
                //if the move is capturing another piece I have to remove the piece before setting the new piece on it.
                int cont = 0;
                if (move.isACapturingMove()) {

                    //Removing the piece from the player pieceList
                    if (move.arrivingTile.getPieceOn().getColor() == ChessPiece.PIECE_COLOR.WHITE) {
                        game.getWhitePlayer().getChessPieceList().remove(move.arrivingTile.getPieceOn());
                    } else {
                        game.getBlackPlayer().getChessPieceList().remove(move.arrivingTile.getPieceOn());
                    }

                    game.getChessBoard().removePiece(move.getArrivingTile().getCharColumn(), move.getArrivingTile().getRow());
                    cont+=1;
                }
                move.consumeMove();
                switch(checkingControl(move.pieceToMove.getColor(), game)){
                    case 1 -> cont+= 2;
                    case 2 -> cont+= 4;
                }
                return cont;
            }
            else System.out.println("Mossa non valida");
            return -1;
        } catch (Exception e){ System.out.println("Stringa non valida"); return  -1; }
    }

    /**
     * After a move is done by the color player, the function check whether the other player is in check or checkmated
     * @param color the player which has done the move
     * @param game the game position
     * @return It returns 0 if there is no check, 1 if its check, 2 if its check mate
     */
    public static int checkingControl(ChessPiece.PIECE_COLOR color, Game game) { //esempio: il bianco ha appena fatto la sua mossa
        if (Move.KingInCheck(color == ChessPiece.PIECE_COLOR.WHITE ? ChessPiece.PIECE_COLOR.BLACK : ChessPiece.PIECE_COLOR.WHITE, game)){ //controllo se il re nero è sotto scacco
            List<Move> availablemoves = Move.getAllAvailableMove(color == ChessPiece.PIECE_COLOR.WHITE ? game.getBlackPlayer() : game.getWhitePlayer(), game); // guardo le mosse disponibili per il nero
            availablemoves = cutOutnotProtectingfromCheckMoves(availablemoves, color == ChessPiece.PIECE_COLOR.WHITE ? game.getBlackPlayer() : game.getWhitePlayer(), game);
            if (availablemoves.isEmpty()) return 2;
            else return 1;
        }
        return 0;
    }

    /**
     * A semplified version of the MakeAMove (String st, Game game). This must be used ONLY in the equal chess board to check if this move puts the king in check
     * @param st the string of the move, it should come from the Move.buffer
     * @param game is the equal game generated with the apposite function
     */
    public static void makeTheHypotheticMove(String st, Game game){
        Move move = new Move(game.getChessBoard().getTiles().get(Tile.getID(st.charAt(0),Integer.parseInt(st.substring(1,2)))).getPieceOn(),
                game.getChessBoard().getTiles().get(Tile.getID(st.charAt(0),Integer.parseInt(st.substring(1,2)))),
                game.getChessBoard().getTiles().get(Tile.getID(st.charAt(2),Integer.parseInt(st.substring(3,4)))));
        if (move.isACapturingMove()) {
            //Removing the piece from the player pieceList
            if (move.arrivingTile.getPieceOn().getColor() == ChessPiece.PIECE_COLOR.WHITE) {
                game.getWhitePlayer().getChessPieceList().remove(move.arrivingTile.getPieceOn());
            } else {
                game.getBlackPlayer().getChessPieceList().remove(move.arrivingTile.getPieceOn());
            }
            game.getChessBoard().removePiece(move.getArrivingTile().getCharColumn(), move.getArrivingTile().getRow());
        }
        move.consumeMove();
    }

    /**
     * It should print the avilable tiles for the piece in the selected position
     * @param column the column of the tile
     * @param row the row of the tile
     */
    private static void printAvailableTiles(char column, int row, Game game) {
        ChessPiece piece = game.getChessBoard().getTiles().get(Tile.getID(column,row)).getPieceOn();
        if (piece == null) {System.out.println("No piece found in "+ new Coordinate(column, row).toString() +" square"); return; }
        System.out.println("Available tiles for: " + piece.getName() + piece.getPosition().getCoordinate()+"\n");
        try{
            if (piece.availableTiles(piece.getPosition(), game.getChessBoard()).isEmpty()){System.out.println("No available tiles"); }
            for (Tile tile: piece.availableTiles(piece.getPosition(), game.getChessBoard())){
                System.out.print(tile.getCoordinate() + " - ");
            }
        } catch (NullPointerException e){System.out.println("No available tiles"); }
    }

    public boolean isACapturingMove(){
        return arrivingTile.getPieceOn() != null && arrivingTile.getPieceOn().getColor() != pieceToMove.getColor();
    }

    /**
     * it checks whether the piece on the starting tile of the move has the same color of the playing player
     * @param game the game being played
     * @param st string of the move
     * @return boolean
     */
    public boolean pieceColorEqualPlayerColor(Game game, String st){ return game.getChessBoard().getTiles().get(Tile.getID(st.charAt(0),Integer.parseInt(st.substring(1,2)))).getPieceOn().getColor()==Game.calculateTurn(game.getNumberOfMoves()); }

    public static String askForAMove(int numberOfTheMove, ChessBoard cb) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("hi," + ((Game.calculateTurn(numberOfTheMove) == ChessPiece.PIECE_COLOR.WHITE) ? "White " : "Black") + "player, what's your move?\n");
        String s;
        try {
            s = br.readLine();
        } catch (Exception e) {
            System.out.println(e);
            s = "ERROR";
        }
        return s;
    }
    public static boolean isACapturingMove(String st, Game game){
            return (new Move(game.getChessBoard().getTiles().get(Tile.getID(st.charAt(0),Integer.parseInt(st.substring(1,2)))).getPieceOn(),
                    game.getChessBoard().getTiles().get(Tile.getID(st.charAt(0),Integer.parseInt(st.substring(1,2)))),
                    game.getChessBoard().getTiles().get(Tile.getID(st.charAt(2),Integer.parseInt(st.substring(3,4)))))
                    .isACapturingMove());
        }


    public static List<Move> getAllAvailableMove(Player player, Game game) {
        List<Move> availableMoves = new ArrayList<>();
        for (ChessPiece piece : player.getChessPieceList()) {
            //piece.availableTiles(piece.getPosition(), game.getChessBoard());
            for (Tile tile : piece.availableTiles(piece.getPosition(), game.getChessBoard())) {
                Move tmp = new Move(piece, piece.getPosition(), tile);
                    availableMoves.add(tmp);

            }
        }
        return availableMoves;
    }
    public static List<Move> cutOutnotProtectingfromCheckMoves(List<Move> startingList,Player player, Game game){
        startingList.removeIf(move -> Move.is_this_move_putting_my_king_in_check(game, move.getString(), player.getPlayerColor()));
        return startingList;
    }


        public static boolean KingInCheck (ChessPiece.PIECE_COLOR kingColor, Game game){
            for (Move move : getAllAvailableMove(kingColor == ChessPiece.PIECE_COLOR.WHITE ? game.getBlackPlayer() : game.getWhitePlayer(), game)) {
                if (move.isACapturingMove() && move.arrivingTile.getPieceOn() instanceof King) {
                    return true;
                }
            }
            return false;
        }
        public static boolean is_this_move_putting_my_king_in_check (Game game, String string, ChessPiece.PIECE_COLOR color){
            Game test_game = Game.generate_equal_game(game);
            Move.makeTheHypotheticMove(string, test_game);
            return Move.KingInCheck(color, test_game);
        }

        public String getString () {
            return string;
        }

        public void setString (String string){
            this.string = string;
        }

        public ChessPiece getPieceToMove () {
            return pieceToMove;
        }

        public void setPieceToMove (ChessPiece pieceToMove){
            this.pieceToMove = pieceToMove;
        }

        public Tile getStartingTile () {
            return startingTile;
        }

        public void setStartingTile (Tile startingTile){
            this.startingTile = startingTile;
        }
        public void setArrivingTile (Tile arrivingTile){
            this.arrivingTile = arrivingTile;
        }

        public Tile getArrivingTile () {
            return arrivingTile;
        }
}