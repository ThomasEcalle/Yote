package com.example.my_pc.yote.engine.moves;

import com.example.my_pc.yote.engine.board.Board;

/**
 * Created by Thomas Ecalle on 16/04/2017.
 */

public class MoveTransition
{
    private final Board fromBoard;
    private final Board toBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board fromBoard,
                          final Board toBoard,
                          final Move move,
                          final MoveStatus moveStatus)
    {
        this.fromBoard = fromBoard;
        this.toBoard = toBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public Board getToBoard()
    {
        return this.toBoard;
    }

    public Board getFromBoard()
    {
        return this.fromBoard;
    }

    public MoveStatus getMoveStatus()
    {
        return this.moveStatus;
    }
}
