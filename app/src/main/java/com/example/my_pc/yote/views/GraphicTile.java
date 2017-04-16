package com.example.my_pc.yote.views;

import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.my_pc.yote.Constants;
import com.example.my_pc.yote.engine.board.Board;
import com.example.my_pc.yote.engine.board.Piece;
import com.example.my_pc.yote.engine.board.Tile;
import com.example.my_pc.yote.engine.moves.MoveTransition;
import com.example.my_pc.yote.engine.moves.PlacementMove;

/**
 * Created by Thomas Ecalle on 16/04/2017.
 */

public class GraphicTile extends Tile
{
    private static final String TAG = GraphicTile.class.getSimpleName();

    private final Paint squareColor;
    private Rect tileRect;

    public GraphicTile(final int position)
    {
        super(position);

        this.squareColor = new Paint();
        squareColor.setColor(Color.BLACK);
        squareColor.setStyle(Paint.Style.STROKE);
    }

    public void draw(final Canvas canvas)
    {
        canvas.drawRect(tileRect, squareColor);
    }

    public boolean isTouched(final int x, final int y)
    {
        return tileRect.contains(x, y);
    }

    public void setTileRect(final Rect tileRect)
    {
        this.tileRect = tileRect;
    }

    public String toString()
    {
        return "<GraphicTile " + getTileCoordonate() + ">";
    }
}
