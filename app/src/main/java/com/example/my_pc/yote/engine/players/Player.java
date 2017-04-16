package com.example.my_pc.yote.engine.players;

import com.example.my_pc.yote.engine.Alliance;
import com.example.my_pc.yote.engine.board.Piece;
import com.example.my_pc.yote.engine.board.Board;
import com.example.my_pc.yote.engine.board.Tile;
import com.example.my_pc.yote.engine.moves.Move;
import com.example.my_pc.yote.engine.moves.MoveStatus;
import com.example.my_pc.yote.engine.moves.MoveTransition;
import com.example.my_pc.yote.engine.moves.PlacementMove;

import java.util.List;

/**
 * Created by Thomas Ecalle on 15/04/2017.
 */

public abstract class Player
{
    protected final Board board;
    protected final List<Move> legalMoves;
    protected final List<Move> opponentMoves;

    Player(final Board board, final List<Move> legalMoves, final List<Move> opponentLegalMoves)
    {
        this.board = board;
        this.legalMoves = legalMoves;
        this.opponentMoves = opponentLegalMoves;
    }

    public MoveTransition makeMove(final Move move)
    {
        if (!isMoveLegal(move))
        {
            return new MoveTransition(this.board, this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionBoard = move.execute();

        return new MoveTransition(this.board, transitionBoard, move, MoveStatus.DONE);
    }

    public boolean isMoveLegal(final Move move)
    {
        if (move instanceof PlacementMove)
        {
            final PlacementMove placementMove = (PlacementMove) move;
            final Tile tile = board.getTile(placementMove.getDestinationCoordinate());
            return tile.isTileOccupied() == false;
        }
        return this.legalMoves.contains(move);
    }

    public abstract List<Piece> getActivePieces();

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();

}
