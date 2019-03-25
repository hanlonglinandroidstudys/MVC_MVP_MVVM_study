package hanlonglin.com.mvp_model.view;

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
import hanlonglin.com.mvp_model.R;
import hanlonglin.com.mvp_model.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IMainView {

    private static final String TAG = "MainActivity";
    TextView txt_winner;
    TextView txt_title;
    ViewGroup viewGroup_finish;
    ViewGroup gridLayout;

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_winner = (TextView) findViewById(R.id.txt_winner);
        viewGroup_finish = (LinearLayout) findViewById(R.id.li_finish);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        mainPresenter = new MainPresenter(this);
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
                mainPresenter.onRestartSelected();
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
        mainPresenter.onButtonSelect(row,col);
    }

    @Override
    public void showWinner(String winnerName) {
        txt_winner.setText(winnerName);
        viewGroup_finish.setVisibility(View.VISIBLE);
        txt_title.setText("游戏结束");
    }

    @Override
    public void showCurrentPlayer(String currentPlayer) {
        txt_title.setText("进行中，当前棋手：" + currentPlayer);
    }

    @Override
    public void showPingju() {
        txt_winner.setText("平局");
        viewGroup_finish.setVisibility(View.VISIBLE);
        txt_title.setText("游戏结束");
    }

    @Override
    public void resetView(){
//        txt_title.setText("进行中，当前棋手：" + model.getCurrentPlayer().toString());
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((Button) gridLayout.getChildAt(i)).setText("");
        }
        viewGroup_finish.setVisibility(View.GONE);
    }

    @Override
    public void setButtonText(int row, int col, String text) {
        int index=row*3+col;
        Button btn=(Button)gridLayout.getChildAt(index);
        btn.setText(text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除和view的联系
        mainPresenter.detachView();
    }
}
