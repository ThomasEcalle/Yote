package com.example.my_pc.yote.engine.board;

import android.provider.ContactsContract;

import com.example.my_pc.yote.Constants;

/**
 * Created by Thomas Ecalle on 15/04/2017.
 */

public class BoardUtils
{
    public static boolean isValidCoordonate(final int coordonate)
    {
        return coordonate >= 0 && coordonate < Constants.NUM_TILES;
    }
}

