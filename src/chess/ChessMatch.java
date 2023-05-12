package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8); // tabuleiro de xadrez tem 8 linhas e 8 colunas
        initialSetup();
    }

    public ChessPiece[][] getPieces() { // retorna uma matriz de peças de xadrez correspondente a partida
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; // matriz de peças de xadrez
        for (int i = 0; i < board.getRows(); i++) { // percorrer todas as linhas
            for (int j = 0; j < board.getColumns(); j++) { // percorrer todas as colunas
                mat[i][j] = (ChessPiece) board.piece(i, j); // downcasting
            }
        }
        return mat;
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition(); // converte a posição de xadrez para posição de matriz
        Position target = targetPosition.toPosition(); // converte a posição de xadrez para posição de matriz
        validateSourcePosition(source); // valida a posição de origem
        Piece capturedPiece = makeMove(source, target); // faz o movimento
        return (ChessPiece)capturedPiece;
    }
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source); // remove a peça da posição de origem
        Piece capturedPiece = board.removePiece(target); // remove a peça da posição de destino
        board.placePiece(p, target); // coloca a peça na posição de destino
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)){
            throw new ChessException("Não existe peça na posição de origem");
        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition()); // coloca uma nova peça no tabuleiro
    }
    
    private void initialSetup(){
        placeNewPiece('d', 1, new King(board, Color.WHITE));
        placeNewPiece('d', 2,new Rook(board, Color.WHITE));
        placeNewPiece('e', 1,new Rook(board, Color.WHITE));
        placeNewPiece('e', 2,new Rook(board, Color.WHITE));
        placeNewPiece('c', 2,new Rook(board, Color.WHITE));
        placeNewPiece('c', 1,new Rook(board, Color.WHITE));
    
        
        
        
        placeNewPiece('d', 8, new King(board, Color.BLACK));
        placeNewPiece('c', 8,new Rook(board, Color.BLACK));
        placeNewPiece('c', 7,new Rook(board, Color.BLACK));
        placeNewPiece('d', 7,new Rook(board, Color.BLACK));
        placeNewPiece('e', 8,new Rook(board, Color.BLACK));
        placeNewPiece('e', 7,new Rook(board, Color.BLACK));


    }
}