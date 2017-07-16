package com.example.installed.practics1.helper_classes.permission;

/**
 * Created by Installed on 14.07.2017.
 */

public interface IPermissionRegistry {
    public void insertPermission(Permission permission);
    public void removePermission(Permission permission);
    public Permission getPermissionByManifestName(String manifestName);
    public Permission getPermissionByMessageResultCode(int code);
}
