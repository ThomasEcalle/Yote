package com.example.my_pc.yote.engine.board;

/**
 * Created by Thomas Ecalle on 15/04/2017.
 */

public final class PlacementMove extends Move
{

    protected PlacementMove(final Board board, final int destinationCoordinate, final Piece movedPiece)
    {
        super(board, destinationCoordinate, movedPiece);
    }

    @Override
    public Board execute()
    {
        final Board.Builder builder = new Board.Builder();
        builder.setPiece(movedPiece);

        for (final Piece piece : board.getCurrentPlayer().getActivePieces())
        {
            builder.setPiece(piece);
        }

        for (final Piece piece : board.getCurrentPlayer().getOpponent().getActivePieces())
        {
            builder.setPiece(piece);
        }

        builder.setNextMoveMaker(board.getCurrentPlayer().getOpponent().getAlliance());

        return builder.build();
    }
}
