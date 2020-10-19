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
            actionPermissionMap.put(ActionType.ADMIN.name(), RoleEnum.ADMIN);
            actionPermissionMap.put(ActionType.EDITUSERS.name(), RoleEnum.ADMIN);
            actionPermissionMap.put(ActionType.CREATEUSER.name(), RoleEnum.ADMIN);
            actionPermissionMap.put(ActionType.UPDATEUSER.name(), RoleEnum.ADMIN);
//            kassir
            actionPermissionMap.put(ActionType.MAIN.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.ALLCURRENCY.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.RATECB.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.RATENB.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.DUTIES.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.BALANCE.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.USEROPERATIONS.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT1000.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT1100.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT1100BALANCE.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT10_01.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT10_02.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT20_01.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT20_02.name(), RoleEnum.KASSIR);
            actionPermissionMap.put(ActionType.PAYMENT998.name(), RoleEnum.KASSIR);

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
