package com.example.my_pc.yote.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.my_pc.yote.Constants;
import com.example.my_pc.yote.engine.board.Board;
import com.example.my_pc.yote.engine.board.Piece;
import com.example.my_pc.yote.engine.moves.MoveTransition;
import com.example.my_pc.yote.engine.moves.PlacementMove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thomas Ecalle on 16/04/2017.
 */


public final class GraphicBoard extends View
{
    private static final String TAG = GraphicBoard.class.getSimpleName();

    private Board board;

    private static final int COLS = 5;
    private static final int ROWS = 6;

    private final List<GraphicTile> tiles;

    private int x0 = 0;
    private int y0 = 0;
    private int squareSize = 0;

    /**
     * 'true' if black is facing player.
     */
    private boolean flipped = false;

    public GraphicBoard(final Context context, final AttributeSet attrs)
    {
        super(context, attrs);
        this.tiles = new ArrayList<>(Constants.NUM_TILES);

        setFocusable(true);

        buildTiles();
    }

    private void buildTiles()
    {
        for (int index = 0; index < Constants.NUM_TILES; index++)
        {
            tiles.add(new GraphicTile(index));
        }
    }

    @Override
    protected void onDraw(final Canvas canvas)
    {
        final int width = getWidth();
        final int height = getHeight();

        this.squareSize = Math.min(
                getSquareSizeWidth(width),
                getSquareSizeHeight(height)
        );

        computeOrigins(width, height);

        int counter = 0;
        for (int c = 0; c < COLS; c++)
        {
            for (int r = 0; r < ROWS; r++)
            {
                final int xCoord = getXCoord(c);
                final int yCoord = getYCoord(r);

                final Rect tileRect = new Rect(
                        xCoord,               // left
                        yCoord,               // top
                        xCoord + squareSize,  // right
                        yCoord + squareSize   // bottom
                );

                tiles.get(counter).setTileRect(tileRect);
                tiles.get(counter).draw(canvas);
                counter++;
            }
        }
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event)
    {
        final int x = (int) event.getX();
        final int y = (int) event.getY();

        GraphicTile tile;
        for (final GraphicTile graphicTile : tiles)
        {
            if (graphicTile.isTouched(x, y))
            {
                handleTouch(graphicTile);
            }
        }
        return true;
    }

    private void handleTouch(final GraphicTile tile)
    {
        Log.d(Constants.TAG, "handleTouch(): position: " + tile.getTileCoordonate());
        if (tile.isTileOccupied() == false)
        {
            Log.d(Constants.TAG, "tile is not occupied");
            final Piece piece = new Piece(board.getCurrentPlayer().getAlliance(), tile.getTileCoordonate());
            final PlacementMove move = new PlacementMove(board, tile.getTileCoordonate(), piece);
            final MoveTransition moveTransition = board.getCurrentPlayer().makeMove(move);

            if (moveTransition.getMoveStatus().isDone())
            {
                setBoard(moveTransition.getToBoard());
                drawBoard();
            }
        }
    }

    private void drawBoard()
    {

    }

    public void setBoard(final Board board)
    {
        this.board = board;
    }

    private int getSquareSizeWidth(final int width)
    {
        return width / COLS;
    }

    private int getSquareSizeHeight(final int height)
    {
        return height / ROWS;
    }

    private int getXCoord(final int x)
    {
        return x0 + squareSize * (flipped ? (ROWS - 1) - x : x);
    }

    private int getYCoord(final int y)
    {
        return y0 + squareSize * (flipped ? y : (COLS - 1) - y);
    }

    private void computeOrigins(final int width, final int height)
    {
        this.x0 = (width - squareSize * COLS) / 2;
        this.y0 = (height - squareSize * ROWS) / 2;
    }

}
