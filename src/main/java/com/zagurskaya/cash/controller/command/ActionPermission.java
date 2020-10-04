package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.entity.RoleEnum;

import java.util.HashMap;
import java.util.Map;

public class ActionPermission {
    private static Map<String, RoleEnum> actionPermissionMap = new HashMap<>();
    private static ActionPermission instance;

    private ActionPermission() {
    }

    public static ActionPermission getInstance() {
        if (instance == null) {
            instance = new ActionPermission();
//            admin
            actionPermissionMap.put(Action.ADMIN.name(), RoleEnum.ADMIN);
            actionPermissionMap.put(Action.EDITUSERS.name(), RoleEnum.ADMIN);
            actionPermissionMap.put(Action.CREATEUSER.name(), RoleEnum.ADMIN);
            actionPermissionMap.put(Action.UPDATEUSER.name(), RoleEnum.ADMIN);
//            kassir
            actionPermissionMap.put(Action.MAIN.name(), RoleEnum.KASSIR);
        }
        return instance;
    }

    public Map<String, RoleEnum> getActionPermissionMap() {
        Map<String, RoleEnum> parametersMapCopy = new HashMap<>();
        parametersMapCopy.putAll(actionPermissionMap);
        return parametersMapCopy;
    }
}
