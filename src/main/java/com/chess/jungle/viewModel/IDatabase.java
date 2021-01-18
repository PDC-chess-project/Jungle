package com.chess.jungle.viewModel;

import com.chess.jungle.database.User;
import com.chess.jungle.utils.LiveData;

import java.util.List;

/**
 * @author Chengjie Luo
 */
public interface IDatabase {

    LiveData<List<User>> getUserList();

    void createPlayerRecord(String name, boolean won);

    public void deleteAllRecords();
}
