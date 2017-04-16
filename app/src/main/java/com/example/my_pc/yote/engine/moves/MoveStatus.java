package com.example.my_pc.yote.engine.moves;

/**
 * Created by Thomas Ecalle on 16/04/2017.
 */

public enum MoveStatus
{

    DONE
            {
                @Override
                public boolean isDone()
                {
                    return true;
                }
            },
    ILLEGAL_MOVE
            {
                @Override
                public boolean isDone()
                {
                    return false;
                }
            };

    public abstract boolean isDone();

}
