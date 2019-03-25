package hanlonglin.com.mvp_model.presenter;

import java.lang.ref.WeakReference;

import hanlonglin.com.mvp_model.model.Board;
import hanlonglin.com.mvp_model.model.Player;
import hanlonglin.com.mvp_model.view.IMainView;

public class MainPresenter<T extends IMainView> {
    Board model;
    WeakReference<T> mainView;
    public MainPresenter(T iMainView){
        mainView=new WeakReference<T>(iMainView);
        model=new Board();
        model.restart();
    }

    public void onButtonSelect(int row,int col){
        Player playerTharMoved=model.mark(row,col);
        if(playerTharMoved==null)
            return;
        mainView.get().setButtonText(row,col,playerTharMoved.toString());
        if (model.getWinner()!=null) {
           mainView.get().showWinner(model.getWinner().toString());
        }else if(model.isAllPulled()){
            mainView.get().showPingju();
        }else{
            model.changeCurrentPlayer();
            mainView.get().showCurrentPlayer(model.getCurrentPlayer().toString());
        }
    }


    public void onRestartSelected(){
        model.restart();
        mainView.get().resetView();
    }

    /**
     * 解除和view的联系，防止内存泄露，在onDestory()中调用
     */
    public void detachView(){
        this.mainView.clear();
    }
}
