package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.entity.RoleType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Permissive action rights
 */
public class ActionPermission {
    private static Map<String, RoleType> actionPermissionMap = new HashMap<>();
    private static ActionPermission instance;

    private ActionPermission() {
    }

    /**
     * Constructor
     *
     * @return action permission
     */
    public static ActionPermission getInstance() {
        if (instance == null) {
            instance = new ActionPermission();
            fillPermission();
        }
        return instance;
    }

    /**
     * Obtaining all permission
     *
     * @return Map all permission
     */
    public Map<String, RoleType> getActionPermissionMap() {
        return Collections.unmodifiableMap((actionPermissionMap));
    }

    private static void fillPermission() {

        actionPermissionMap.put(ActionType.ADMIN.name(), RoleType.ADMIN);
        actionPermissionMap.put(ActionType.EDIT_USERS.name(), RoleType.ADMIN);
        actionPermissionMap.put(ActionType.CREATE_USER.name(), RoleType.ADMIN);
        actionPermissionMap.put(ActionType.UPDATE_USER.name(), RoleType.ADMIN);

        actionPermissionMap.put(ActionType.MAIN.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.ALL_CURRENCY.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.RATE_CB.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.RATE_NB.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.DUTIES.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.PAYMENT.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.BALANCE.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.USER_OPERATIONS.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.PAYMENT1000.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.PAYMENT1100.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.PAYMENT1100BALANCE.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.PAYMENT10_01.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.PAYMENT10_02.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.PAYMENT20_01.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.PAYMENT20_02.name(), RoleType.KASSIR);
        actionPermissionMap.put(ActionType.PAYMENT998.name(), RoleType.KASSIR);

    }
}
