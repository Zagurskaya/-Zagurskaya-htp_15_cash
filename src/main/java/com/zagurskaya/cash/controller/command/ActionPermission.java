package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.entity.RoleEnum;

import java.util.HashMap;
import java.util.Map;
/**
 * Разрешительные права на действия
 */
class ActionPermission {
    private static Map<String, RoleEnum> actionPermissionMap = new HashMap<>();
    private static ActionPermission instance;

    private ActionPermission() {
    }

    /**
     * Получение прав
     *
     * @return права
     */
    static ActionPermission getInstance() {
        if (instance == null) {
            instance = new ActionPermission();
//            admin
            actionPermissionMap.put(Action.ADMIN.name(), RoleEnum.ADMIN);
            actionPermissionMap.put(Action.EDITUSERS.name(), RoleEnum.ADMIN);
            actionPermissionMap.put(Action.CREATEUSER.name(), RoleEnum.ADMIN);
            actionPermissionMap.put(Action.UPDATEUSER.name(), RoleEnum.ADMIN);
//            kassir
            actionPermissionMap.put(Action.MAIN.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(Action.ALLCURRENCY.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(Action.RATECB.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(Action.RATENB.name(), RoleEnum.KASSIR);
        }
        return instance;
    }
    /**
     * Получение всех прав
     *
     * @return Map всех прав
     */
    Map<String, RoleEnum> getActionPermissionMap() {
        return new HashMap<>(actionPermissionMap);
    }
}
