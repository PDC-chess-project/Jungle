package com.chess.jungle.viewModel;

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
}
