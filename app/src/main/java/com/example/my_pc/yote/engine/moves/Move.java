package com.example.my_pc.yote.engine.moves;

import com.example.my_pc.yote.engine.board.Board;
import com.example.my_pc.yote.engine.board.Piece;

/**
 * Created by Thomas Ecalle on 15/04/2017.
 */
public abstract class Move
{
    protected final Board board;
    protected final int destinationCoordinate;
    protected final Piece movedPiece;


    protected Move(Board board, int destinationCoordinate, Piece movedPiece)
    {
        this.board = board;
        this.destinationCoordinate = destinationCoordinate;
        this.movedPiece = movedPiece;
    }

    public abstract Board execute();

    public int getDestinationCoordinate()
    {
        return destinationCoordinate;
    }
}
