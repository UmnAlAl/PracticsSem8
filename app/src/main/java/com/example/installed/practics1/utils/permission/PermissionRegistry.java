package com.example.installed.practics1.utils.permission;

import java.util.HashMap;

/**
 * Created by Installed on 14.07.2017.
 */

public class PermissionRegistry implements IPermissionRegistry {

    private HashMap<String, Permission> _mapManifestNameToPermission = new HashMap<>();
    private HashMap<Integer, Permission> _mapMessageResultCodeToPermission = new HashMap<>();

    public void insertPermission(Permission permission) {
        _mapManifestNameToPermission.put(permission.getManifestName(), permission);
        _mapMessageResultCodeToPermission.put(permission.getMessageResultCode(), permission);
    }

    public void removePermission(Permission permission) {
        _mapManifestNameToPermission.remove(permission.getManifestName());
        _mapMessageResultCodeToPermission.remove(permission.getMessageResultCode());
    }

    public Permission getPermissionByManifestName(String manifestName) {
        return _mapManifestNameToPermission.get(manifestName);
    }

    public Permission getPermissionByMessageResultCode(int code) {
        return _mapMessageResultCodeToPermission.get(code);
    }

}
