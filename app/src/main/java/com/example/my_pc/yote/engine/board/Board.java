package com.example.my_pc.yote.engine.board;


import android.util.Log;

import com.example.my_pc.yote.Constants;
import com.example.my_pc.yote.engine.Alliance;
import com.example.my_pc.yote.engine.moves.Move;
import com.example.my_pc.yote.engine.players.BlackPlayer;
import com.example.my_pc.yote.engine.players.Player;
import com.example.my_pc.yote.engine.players.WhitePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas Ecalle on 15/04/2017.
 */

public final class Board
{
    private final List<Tile> gameBoard;
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;

    public static boolean[] firstRow;
    public static boolean[] lastRow;

    static
    {
        firstRow = initRow(0);
        lastRow = initRow(Constants.NUM_ROWS - 1);
    }

    public Board(final Builder builder)
    {
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(builder, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(builder, Alliance.BLACK);
        final List<Move> whiteMoves = calculateLegalMoves(this.whitePieces);
        final List<Move> blackMoves = calculateLegalMoves(this.blackPieces);
        this.whitePlayer = new WhitePlayer(this, whiteMoves, blackMoves);
        this.blackPlayer = new BlackPlayer(this, whiteMoves, blackMoves);
        this.currentPlayer = selectNextPlayer(builder.nextMoveMaker);

        Log.w(Constants.TAG, "current player = "  + currentPlayer.getAlliance());
    }

    public static Board createInitialBoard()
    {
        final Builder builder = new Builder();
        builder.setMoveMaker(Alliance.WHITE);
        return builder.build();
    }

    private Player selectNextPlayer(final Alliance nextMoveMaker)
    {
        return nextMoveMaker == Alliance.BLACK ? blackPlayer : whitePlayer;
    }

    private static List<Tile> createGameBoard(final Builder boardBuilder)
    {
        final Tile[] tiles = new Tile[Constants.NUM_TILES];
        for (int i = 0; i < Constants.NUM_TILES; i++)
        {
            tiles[i] = new Tile(i, boardBuilder.boardConfig.get(i));
        }
        return Arrays.asList(tiles);
    }


    private static List<Piece> calculateActivePieces(final Builder builder,
                                                     final Alliance alliance)
    {
        final List<Piece> activePieces = new ArrayList<>(16);
        for (final Piece piece : builder.boardConfig.values())
        {
            if (piece.getPieceAlliance() == alliance)
            {
                activePieces.add(piece);
            }
        }
        return activePieces;
    }


    private List<Move> calculateLegalMoves(final List<Piece> pieces)
    {
        final List<Move> legalMoves = new ArrayList<>();
        for (final Piece piece : pieces)
        {
            legalMoves.addAll(piece.calculateLegalMoves(this));
        }
        return legalMoves;
    }

    private static boolean[] initRow(int index)
    {
        final boolean[] row = new boolean[Constants.NUM_TILES];
        int count = 0;
        do
        {
            row[index] = true;
            index += Constants.NUM_ROWS;
            count++;
        }
        while (count < Constants.NUM_COLUMNS);
        return row;
    }

    public Tile getTile(final int position)
    {
        return gameBoard.get(position);
    }

    public List<Piece> getWhitePieces()
    {
        return whitePieces;
    }

    public List<Piece> getBlackPieces()
    {
        return blackPieces;
    }

    public WhitePlayer getWhitePlayer()
    {
        return whitePlayer;
    }

    public BlackPlayer getBlackPlayer()
    {
        return blackPlayer;
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public static class Builder
    {
        private final Map<Integer, Piece> boardConfig;
        private Alliance nextMoveMaker;

        public Builder()
        {
            this.boardConfig = new HashMap<>();
        }

        public Builder setPiece(final Piece piece)
        {
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker)
        {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Board build()
        {
            return new Board(this);
        }

        public void setNextMoveMaker(Alliance nextMoveMaker)
        {
            this.nextMoveMaker = nextMoveMaker;
        }
    }
}
