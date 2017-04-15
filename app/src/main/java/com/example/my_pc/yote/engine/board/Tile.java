package com.example.my_pc.yote.engine.board;

/**
 * Created by Thomas Ecalle on 15/04/2017.
 */

public class Tile
{
    private int tileCoordonate;
    private boolean isTileOccupied = false;
    private Piece piece;

    public Tile(final int tileCoordonate, final Piece piece)
    {

        this.tileCoordonate = tileCoordonate;
        this.piece = piece;
    }

    public int getTileCoordonate()
    {
        return tileCoordonate;
    }

    public boolean isTileOccupied()
    {
        return isTileOccupied;
    }

    public Piece getPiece()
    {
        return piece;
    }
}
