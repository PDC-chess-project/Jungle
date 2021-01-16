package com.chess.jungle.viewModel;

import com.chess.jungle.database.Database;
import com.chess.jungle.database.User;
import com.chess.jungle.logic.Piece;
import com.chess.jungle.utils.LiveData;

import java.util.List;

/**
 * @author Chengjie Luo
 */
public class LeaderBoardViewModel {

    private static LeaderBoardViewModel instance;

    private LeaderBoardViewModel() {
    }

    public static LeaderBoardViewModel get() {
        if (instance == null) {
            synchronized (LeaderBoardViewModel.class) {
                if (instance == null) {
                    instance = new LeaderBoardViewModel();
                }
            }
        }
        return instance;
    }

    protected String redPlayerName;
    protected String bluePlayerName;

    private final Database database = Database.getInstance();

    /**
     * Set current player names.
     *
     * @param redPlayerName  Red player name you are going to set.
     * @param bluePlayerName Blue player name you are going to set.
     * @return If it is valid (It can not be the same).
     */
    public boolean setPlayerNames(String redPlayerName, String bluePlayerName) {
        if (!(redPlayerName != null && redPlayerName.isEmpty()))
            this.redPlayerName = redPlayerName;
        if (!(bluePlayerName != null && bluePlayerName.isEmpty()))
            this.bluePlayerName = bluePlayerName;
        return validatePlayerName();
    }

    public boolean validatePlayerName() {
        return redPlayerName != null && !redPlayerName.isEmpty() && bluePlayerName != null && !bluePlayerName.isEmpty() && !redPlayerName.equals(bluePlayerName);
    }

    public String getRedPlayerName() {
        return redPlayerName;
    }

    public String getBluePlayerName() {
        return bluePlayerName;
    }

    public LiveData<List<User>> getLeaderboard() {
        return database.getUserList();
    }

    /**
     * Record the game result.
     *
     * @param winSide The side of player wins.
     */
    public void recordResult(Piece.Side winSide) {
        String winPlayer = winSide == Piece.Side.BLUE ? bluePlayerName : redPlayerName;
        String losePlayer = winSide == Piece.Side.BLUE ? redPlayerName : bluePlayerName;
        database.createPlayerRecord(winPlayer, true);
        database.createPlayerRecord(losePlayer, false);
    }

    public void clearLeaderboard() {
        database.deleteAllRecords();
    }
}
