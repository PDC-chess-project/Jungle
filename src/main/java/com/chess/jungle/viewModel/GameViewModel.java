package com.chess.jungle.viewModel;

import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.logic.Piece;
import com.chess.jungle.utils.LiveData;

/**
 *
 * @author Chengjie Luo
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

    protected LiveData<Exception> error = new LiveData<>();

    protected LiveData<JungleGame> currentGame = new LiveData<>();
    protected LiveData<Piece> selectedPiece = new LiveData<>();

    public LiveData<JungleGame> getCurrentJungleGame() {
        return currentGame;
    }

    public void setCurrentGame(JungleGame game) {
        currentGame.setValue(game);
    }

    public LiveData<Piece> getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece piece) {
        this.selectedPiece.setValue(piece);
    }
    
    public void setError(Exception e) {
        error.setValue(e);
    }

    public void clear() {
        currentGame.clear();
        selectedPiece.clear();
        error.clear();
    }
}
