package com.chess.jungle.viewModel;

import com.chess.jungle.logic.JungleGame;
import com.chess.jungle.logic.Piece;
import com.chess.jungle.ui.PieceComponent;
import com.chess.jungle.utils.LiveData;
import com.chess.jungle.utils.MutableLiveData;

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

    protected MutableLiveData<Exception> error = new MutableLiveData<>();

    protected MutableLiveData<JungleGame> currentGame = new MutableLiveData<>();
    protected MutableLiveData<Piece.Side> currentSide = new MutableLiveData<>(Piece.Side.RED);
    protected MutableLiveData<PieceComponent> selectedPiece = new MutableLiveData<>();

    public LiveData<JungleGame> getCurrentJungleGame() {
        return currentGame;
    }

    public void setCurrentGame(JungleGame game) {
        currentGame.setValue(game);
    }

    public LiveData<Piece.Side> getCurrentSide() {
        return currentSide;
    }

    public void setCurrentSide(Piece.Side side) {
        this.currentSide.setValue(side);
    }

    public LiveData<PieceComponent> getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(PieceComponent pieceComponent) {
        this.selectedPiece.setValue(pieceComponent);
    }

    public LiveData<Exception> getError() {
        return error;
    }

    public void setError(Exception e) {
        error.setValue(e);
    }
}
