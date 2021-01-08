package com.chess.jungle.viewModel;

import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.logic.Piece;
import com.chess.jungle.utils.LiveData;

/**
 *
 * @author CommA
 */
public class GameViewModel {

    private static volatile GameViewModel instance = null;

    public static GameViewModel get() {
        if (instance == null)
        synchronized (GameViewModel.class) {
            if (instance == null) {
                instance = new GameViewModel();
            }
        }
        return instance;
    }

    LiveData<JungleGame> currentGame = new LiveData<>();
    LiveData<Piece> selectedPiece = new LiveData<>();

    public LiveData<JungleGame> getCurrentChessGame() {
        return currentGame;
    }

    public void setCurrentGame(JungleGame game) {
        currentGame.setValue(game);
    }

    public void clear() {
        currentGame.clear();
    }
}
