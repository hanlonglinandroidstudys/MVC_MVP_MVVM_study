package hanlonglin.com.normal;

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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView txt_winner;
    TextView txt_title;
    ViewGroup viewGroup_finish;
    ViewGroup gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_winner = (TextView) findViewById(R.id.txt_winner);
        viewGroup_finish = (LinearLayout) findViewById(R.id.li_finish);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        restart();
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
                restart();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //选手枚举
    public enum Player {
        X, O
    }

    //游戏状态枚举
    public enum GameState {
        IN_PROCESS,
        FINISHED
    }

    /**
     * 格子
     */
    public class Cell {
        Player player = null;

        public void setPlayer(Player player) {
            this.player = player;
        }

        public Player getPlayer() {
            return player;
        }
    }

    //定义变量
    Player winner; //胜利者
    Player currentPlayer = Player.O; //当前下棋选手
    GameState gameState = GameState.FINISHED;
    Cell cells[][] = new Cell[3][3];

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
        Player playerThatMoved = mark(row, col);
        if (playerThatMoved != null) {
            ((Button) v).setText(currentPlayer.toString());
            if (isWinByWinnerMove(currentPlayer, row, col)) {
                gameState = GameState.FINISHED;
                winner=currentPlayer;
                txt_winner.setText(winner.toString());
                viewGroup_finish.setVisibility(View.VISIBLE);
                txt_title.setText("游戏结束");
            } else {
                changeCurrentPlayer();
                txt_title.setText("进行中，当前棋手：" + currentPlayer.toString());
            }
        }

    }

    private Player mark(int row, int col) {
        if (!isValid(row, col)) {
            Log.e(TAG, "cell is not valid!");
            return null;
        }
        cells[row][col].setPlayer(currentPlayer);
        return currentPlayer;
    }

    /**
     * 是否已经被点击
     *
     * @param cell
     * @return
     */
    private boolean isHaveBeenChoose(Cell cell) {
        return cell.getPlayer() != null;
    }

    /**
     * 是否数组越界
     *
     * @param row
     * @param col
     * @return
     */
    private boolean isOutBounds(int row, int col) {
        return row < 0 || row > 2 || col < 0 || col > 2;
    }

    /**
     * 是否合法
     *
     * @param row
     * @param col
     * @return
     */
    private boolean isValid(int row, int col) {
        if (gameState == GameState.FINISHED) {
            Log.e(TAG, "gameState is finished!");
            return false;
        }
        if (isOutBounds(row, col)) {
            Log.e(TAG, "row or col is out bounds!");
            return false;
        }
        if (isHaveBeenChoose(cells[row][col])) {
            Log.e(TAG, "cell has been choosen!");
            return false;
        }
        return true;
    }

    /**
     * 切换棋手
     */
    private void changeCurrentPlayer() {
        currentPlayer = (currentPlayer == Player.O ? Player.X : Player.O);
    }

    /**
     * 清除cell
     */
    private void clearCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((Button) gridLayout.getChildAt(i)).setText("");
        }
    }

    /**
     * 是否游戏结束
     *
     * @param player
     * @param currentRow
     * @param currentCol
     * @return
     */
    private boolean isWinByWinnerMove(Player player, int currentRow, int currentCol) {
        if (cells[currentRow][0].getPlayer() == player
                && cells[currentRow][1].getPlayer() == player
                && cells[currentRow][2].getPlayer() == player        //三行
                ||
                cells[0][currentCol].getPlayer() == player
                        && cells[1][currentCol].getPlayer() == player
                        && cells[2][currentCol].getPlayer() == player        //三列
                ||
                currentRow == currentCol
                        && cells[0][0].getPlayer() == player
                        && cells[1][1].getPlayer() == player
                        && cells[2][2].getPlayer() == player       //对角线
                ||
                currentRow + currentCol == 2
                        && cells[0][2].getPlayer() == player
                        && cells[1][1].getPlayer() == player
                        && cells[2][0].getPlayer() == player)     //反对角线
        {
            //游戏结束
            return true;
        } else
            return false;
    }

    /**
     * 重新开始游戏
     */
    private void restart() {
        txt_title.setText("进行中，当前棋手：" + currentPlayer.toString());
        gameState = GameState.IN_PROCESS;
        clearCells();
        viewGroup_finish.setVisibility(View.GONE);
    }
}
