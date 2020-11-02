package com.zagurskaya.cash.controller.command.impl.cash.commandOperation.commandPayment.check;

import com.zagurskaya.cash.controller.command.CheckData;
import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.entity.UserOperation;

import java.util.List;

public class CheckData10 implements CheckData {
    private UserOperation userOperation;
    private List<UserEntry> userEntriesList;

    public UserOperation getUserOperation() {
        return userOperation;
    }

    public void setUserOperation(UserOperation userOperation) {
        this.userOperation = userOperation;
    }

    public List<UserEntry> getUserEntriesList() {
        return userEntriesList;
    }

    public void setUserEntriesList(List<UserEntry> userEntriesList) {
        this.userEntriesList = userEntriesList;
    }

    @Override
    public String toString() {
        return "check10{" +
                "userOperation=" + userOperation +
                ", userEntriesList=" + userEntriesList +
                '}';
    }
}
