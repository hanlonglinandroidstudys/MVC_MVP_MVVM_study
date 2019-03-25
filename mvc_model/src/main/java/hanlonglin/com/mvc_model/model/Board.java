package hanlonglin.com.mvc_model.model;

import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Board {

    private final static String TAG="Board";

    //定义变量
    Player winner; //胜利者
    Player currentPlayer = Player.O; //当前下棋选手
    GameState gameState = GameState.FINISHED;
    Cell cells[][] = new Cell[3][3];


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
        if (isOutBounds(row, col)){
            Log.e(TAG, "row or col is out bounds!");
            return false;
        }
        if (isHaveBeenChoose(cells[row][col])){
            Log.e(TAG, "cell has been choosen!");
            return false;
        }
        return true;
    }

    public void changeCurrentPlayer() {
        currentPlayer=(currentPlayer==Player.O?Player.X:Player.O);
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
     * 是否全布下满
     */
    public boolean isAllPulled(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(cells[i][j].getPlayer()==null)
                    return false;
            }
        }
        return true;
    }

    /**
     * 标记cell
     * @param row
     * @param col
     * @return
     */
    public Player mark(int row,int col){
        if(!isValid(row,col))
            return null;
        cells[row][col].setPlayer(currentPlayer);
        if(isWinByWinnerMove(currentPlayer,row,col)){
            winner=currentPlayer;
            gameState=GameState.FINISHED;
        }
        return currentPlayer;
    }

    /**
     * 重新开始游戏
     */
    public void restart() {
        gameState = GameState.IN_PROCESS;
        clearCells();
        winner=null;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
