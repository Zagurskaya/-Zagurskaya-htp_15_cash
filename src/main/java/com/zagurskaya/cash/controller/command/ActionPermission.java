package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.entity.RoleType;

import java.util.HashMap;
import java.util.Map;
/**
 * Разрешительные права на действия
 */
class ActionPermission {
    private static Map<String, RoleType> actionPermissionMap = new HashMap<>();
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
            actionPermissionMap.put(ActionType.ADMIN.name(), RoleType.ADMIN);
            actionPermissionMap.put(ActionType.EDITUSERS.name(), RoleType.ADMIN);
            actionPermissionMap.put(ActionType.CREATEUSER.name(), RoleType.ADMIN);
            actionPermissionMap.put(ActionType.UPDATEUSER.name(), RoleType.ADMIN);
//            kassir
            actionPermissionMap.put(ActionType.MAIN.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.ALLCURRENCY.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.RATECB.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.RATENB.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.DUTIES.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.BALANCE.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.USEROPERATIONS.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT1000.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT1100.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT1100BALANCE.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT10_01.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT10_02.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT20_01.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT20_02.name(), RoleType.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT998.name(), RoleType.KASSIR);

        }
        return instance;
    }
    /**
     * Получение всех прав
     *
     * @return Map всех прав
     */
    Map<String, RoleType> getActionPermissionMap() {
        return new HashMap<>(actionPermissionMap);
    }
}
