package hanlonglin.com.mvc_model.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import hanlonglin.com.mvc_model.R;
import hanlonglin.com.mvc_model.model.Board;
import hanlonglin.com.mvc_model.model.Player;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;
    TextView txt_winner;
    TextView txt_title;
    ViewGroup viewGroup_finish;
    ViewGroup gridLayout;

    Board model=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_winner = (TextView) findViewById(R.id.txt_winner);
        viewGroup_finish = (LinearLayout) findViewById(R.id.li_finish);
        gridLayout=(GridLayout)findViewById(R.id.gridLayout);

        model=new Board();
        model.restart();
        resetView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.right_top, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                model.restart();
                resetView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * cell点击事件
     *
     * @param v
     */
    public void onCellClicked(View v) {
        int row = -1;
        int col = -1;
        switch (v.getId()) {
            case R.id.btn00:
                row = 0;
                col = 0;
                break;
            case R.id.btn01:
                row = 0;
                col = 1;
                break;
            case R.id.btn02:
                row = 0;
                col = 2;
                break;
            case R.id.btn10:
                row = 1;
                col = 0;
                break;
            case R.id.btn11:
                row = 1;
                col = 1;
                break;
            case R.id.btn12:
                row = 1;
                col = 2;
                break;
            case R.id.btn20:
                row = 2;
                col = 0;
                break;
            case R.id.btn21:
                row = 2;
                col = 1;
                break;
            case R.id.btn22:
                row = 2;
                col = 2;
                break;
        }
        Player playerTharMoved=model.mark(row,col);
        if(playerTharMoved==null)
            return;
        ((Button) v).setText(playerTharMoved.toString());
        if (model.getWinner()!=null) {
            txt_winner.setText(model.getWinner().toString());
            viewGroup_finish.setVisibility(View.VISIBLE);
            txt_title.setText("游戏结束");
        }else if(model.isAllPulled()){
            txt_winner.setText("平局");
            viewGroup_finish.setVisibility(View.VISIBLE);
            txt_title.setText("游戏结束");
        }else{
            model.changeCurrentPlayer();
            txt_title.setText("进行中，当前棋手："+model.getCurrentPlayer().toString());
        }


    }

    private void resetView(){
        txt_title.setText("进行中，当前棋手："+model.getCurrentPlayer().toString());
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((Button)gridLayout.getChildAt(i)).setText("");
        }
        viewGroup_finish.setVisibility(View.GONE);
    }
}
