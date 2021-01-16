package com.chess.jungle.database;

public class User {
    private String name;
    private int win;
    private int loss;

    public User() {
    }

    /**
     * constructor
     *
     * @param name name
     * @param win  win
     * @param loss loss
     */
    public User(String name, int win, int loss) {
        this.name = name;
        this.win = win;
        this.loss = loss;
    }

    /**
     * get PlayerName
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * get win
     *
     * @return win
     */
    public int getWin() {
        return this.win;
    }

    /**
     * get loss
     *
     * @return loss
     */
    public int getLoss() {
        return this.loss;
    }

}
