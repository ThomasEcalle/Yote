package com.example.my_pc.yote.engine.players;

import com.example.my_pc.yote.engine.Alliance;
import com.example.my_pc.yote.engine.board.Piece;
import com.example.my_pc.yote.engine.board.Board;
import com.example.my_pc.yote.engine.board.Move;

import java.util.List;

/**
 * Created by Thomas Ecalle on 15/04/2017.
 */

public abstract class Player
{
    protected final Board board;
    protected final List<Move> legalMoves;
    protected final List<Move> opponentMoves;

    Player(final Board board, final List<Move> legalMoves, final List<Move> opponentLegalMoves)
    {
        this.board = board;
        this.legalMoves = legalMoves;
        this.opponentMoves = opponentLegalMoves;
    }

    public abstract List<Piece> getActivePieces();

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();

}
