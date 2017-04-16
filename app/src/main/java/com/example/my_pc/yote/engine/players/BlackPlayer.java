package com.example.my_pc.yote.engine.players;

import com.example.my_pc.yote.engine.Alliance;
import com.example.my_pc.yote.engine.board.Piece;
import com.example.my_pc.yote.engine.board.Board;
import com.example.my_pc.yote.engine.moves.Move;

import java.util.List;

/**
 * Created by Thomas Ecalle on 15/04/2017.
 */

public final class BlackPlayer extends Player
{
    public BlackPlayer(final Board board, final List<Move> whiteMoves, final List<Move> blackMoves)
    {
        super(board, blackMoves, whiteMoves);
    }

    @Override
    public List<Piece> getActivePieces()
    {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance()
    {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent()
    {
        return this.board.getWhitePlayer();
    }
}
