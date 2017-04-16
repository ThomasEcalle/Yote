package com.example.my_pc.yote.engine.board;

/**
 * Created by Thomas Ecalle on 15/04/2017.
 */

public class Tile
{
    protected int tileCoordonate;
    private Piece piece;

    public Tile(final int tileCoordonate, final Piece piece)
    {

        this.tileCoordonate = tileCoordonate;
        this.piece = piece;
    }

    public Tile(final int tileCoordonate)
    {
        this.tileCoordonate = tileCoordonate;
    }

    public int getTileCoordonate()
    {
        return tileCoordonate;
    }

    public boolean isTileOccupied()
    {
        return piece != null;
    }

    public Piece getPiece()
    {
        return piece;
    }
}
