package hanlonglin.com.mvp_model.view;

public interface IMainView {
    public void showWinner(String winnerName);
    public void showPingju();
    public void showCurrentPlayer(String currentPlayer);
    public void resetView();
    public void setButtonText(int row,int col,String text);
}
