package com.tanykoo.compress.rar;

import org.apache.commons.compress.archivers.ArchiveEntry;

import java.util.Date;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 17:33
 * @Since
 */
public class RarArchiveEntity implements ArchiveEntry {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public Date getLastModifiedDate() {
        return null;
    }
}
