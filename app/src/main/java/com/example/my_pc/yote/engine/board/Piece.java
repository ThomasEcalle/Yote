package com.example.my_pc.yote.engine.board;

import com.example.my_pc.yote.engine.Alliance;
import com.example.my_pc.yote.engine.moves.Move;
import com.example.my_pc.yote.engine.moves.PlacementMove;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas Ecalle on 15/04/2017.
 */

public final class Piece
{
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-1, -6, 1, 6};
    private Alliance pieceAlliance;
    private int piecePosition;
    private boolean isInReserve;


    public Piece(final Alliance pieceAlliance, final int piecePosition)
    {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
//        this.isInReserve = isInReserve;
    }

    public Alliance getPieceAlliance()
    {
        return pieceAlliance;
    }

    public int getPiecePosition()
    {
        return piecePosition;
    }

    public boolean isInReserve()
    {
        return isInReserve;
    }

    public List<Move> calculateLegalMoves(final Board board)
    {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateMoveCoordinate : CANDIDATE_MOVE_COORDINATES)
        {
            final int candidateDestination = piecePosition + candidateMoveCoordinate;
            if (BoardUtils.isValidCoordonate(candidateDestination))
            {
                if (isFirstRowExclusion(piecePosition, candidateMoveCoordinate)
                        || isLastRowExclusion(piecePosition, candidateMoveCoordinate))
                {
                    continue;
                }

                final Tile destinationTile = board.getTile(candidateDestination);
                if (destinationTile.isTileOccupied() == false)
                {
                    legalMoves.add(new PlacementMove(board, candidateDestination, this));
                }
            }
        }
        return legalMoves;
    }

    private boolean isLastRowExclusion(int position, int candidateDestination)
    {
        return (Board.lastRow[position] && candidateDestination == 1);
    }

    private boolean isFirstRowExclusion(int position, int candidateDestination)
    {
        return (Board.firstRow[position] && candidateDestination == -1);
    }
}
