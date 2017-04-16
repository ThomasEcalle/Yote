package com.example.my_pc.yote.engine.board;

import com.example.my_pc.yote.engine.moves.Move;

/**
 * Created by Thomas Ecalle on 15/04/2017.
 */

public final class CaptureMove extends Move
{
    protected CaptureMove(final Board board, final int destinationCoordinate, final Piece movedPiece)
    {
        super(board, destinationCoordinate, movedPiece);
    }

    @Override
    public Board execute()
    {
        return null;
    }
}
