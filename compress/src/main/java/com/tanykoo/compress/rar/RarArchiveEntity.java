package com.tanykoo.compress.rar;

import com.tanykoo.compress.ArchiveEntity;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 17:33
 * @Since
 */
public class RarArchiveEntity implements ArchiveEntity {

    private String name;
    private String fullName;
    private boolean isDirectory;
    private long size;
    private long compressSize;
    private long lastModifyTime;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDirectory() {
        return isDirectory;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public long getLastModifyTime() {
        return lastModifyTime;
    }

    @Override
    public String getCompressMethod() {
        return null;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public long getCompressSize() {
        return compressSize;
    }
}
