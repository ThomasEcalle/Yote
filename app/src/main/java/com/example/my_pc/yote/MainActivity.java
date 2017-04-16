package com.example.my_pc.yote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.my_pc.yote.engine.board.Board;
import com.example.my_pc.yote.engine.players.Player;
import com.example.my_pc.yote.engine.players.WhitePlayer;
import com.example.my_pc.yote.views.GraphicBoard;

public class MainActivity extends AppCompatActivity
{
    private GraphicBoard graphicBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphicBoard = (GraphicBoard) findViewById(R.id.graphicBoard);

        graphicBoard.setBoard(Board.createInitialBoard());
    }

}
