package com.chess.jungle.viewModel;

import com.chess.jungle.logic.Coordinate;
import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.logic.Piece;
import com.chess.jungle.utils.LiveData;
import com.chess.jungle.utils.MutableLiveData;

/**
 * @author Chengjie Luo
 */
public class GameViewModel {

    private static volatile GameViewModel instance = null;

    private GameViewModel() {
    }

    public static GameViewModel get() {
        if (instance == null)
            synchronized (GameViewModel.class) {
                if (instance == null) {
                    instance = new GameViewModel();
                }
            }
        return instance;
    }

    protected MutableLiveData<IJungleGame> currentGame = new MutableLiveData<>();
    protected MutableLiveData<Piece.Side> currentSide = new MutableLiveData<>(Piece.Side.RED);

    protected MutableLiveData<Piece.Side> winSide = new MutableLiveData<>();

    public LiveData<IJungleGame> getCurrentJungleGame() {
        return currentGame;
    }

    public void movePiece(Piece piece, Coordinate coordinate) {
        currentGame.get().movePiece(piece, coordinate);
        Piece.Side winner = currentGame.get().getWinner();
        if (winner != null) {
            winSide.setValue(winner);
        }
    }

    public void startNewGame() {
        currentGame.setValue(new JungleGame());
        currentSide.setValue(Piece.Side.RED);
    }

    public LiveData<Piece.Side> getCurrentSide() {
        return currentSide;
    }

    public void flipCurrentSide() {
        this.currentSide.setValue(currentSide.get() == Piece.Side.RED ? Piece.Side.BLUE : Piece.Side.RED);
    }

    public LiveData<Piece.Side> getWinSide() {
        return winSide;
    }
}
