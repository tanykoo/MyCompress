package com.tanykoo.compress.rar;

import com.tanykoo.compress.AbstractArchiveEntry;
import com.tanykoo.compress.ArchiveEntry;

import java.util.Date;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 17:33
 * @Since
 */
public class RarArchiveEntity extends AbstractArchiveEntry {


    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public String getEntryName() {
        return null;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public long getPackSize() {
        return 0;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public long getLastModifyTime() {
        return 0;
    }

    @Override
    public String getEntryPath() {
        return null;
    }
}
