package hanlonglin.com.mvvm_model.viewmodel;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;
import android.util.Log;


import hanlonglin.com.mvvm_model.model.Board;
import hanlonglin.com.mvvm_model.model.Player;

public class MainViewModel {
    private static final String TAG = "MainViewModel";
    private Board model;

    public final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();
    public final ObservableField<String> currentPlayer = new ObservableField<>();

    public MainViewModel() {
        this.model = new Board();
    }

    public void onResetSelect() {
        Log.e(TAG, "重新开始");
        model.restart();
        cells.clear();
        winner.set(null);
        currentPlayer.set("进行中，当前棋手："+model.getCurrentPlayer().toString());
    }

    public void onClickCellAt(int row, int col) {
        Log.e(TAG, "点击" + row + "," + col);
        Player player = model.mark(row, col);
        if (player == null) return;
        cells.put("" + row + col, player.toString());
        if(model.getWinner()!=null){
            //结束 显示胜利者
            winner.set(model.getWinner().toString());
        } else if (model.isAllPulled()) {
            //结束 平局
            winner.set("平局");
        } else {
            //进行中 切换棋手
            model.changeCurrentPlayer();
            currentPlayer.set("进行中，当前棋手："+model.getCurrentPlayer().toString());
        }

    }
}
