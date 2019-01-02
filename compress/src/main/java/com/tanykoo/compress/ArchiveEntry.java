package com.tanykoo.compress;


import java.util.Date;
import java.util.List;

/**
 * @author Tany
 * @createtime 2018-12-29 8:36 PM
 * @since
 */
public interface ArchiveEntry {
    long SIZE_UNKNOWN = -1;

    String getFileName();

    String getEntryName();

    long getSize();

    long getPackSize();

    boolean isDirectory();

    long getLastModifyTime();

    String getEntryPath();

    List<ArchiveEntry> getChildren();

    void addChild(ArchiveEntry archiveEntry);

    boolean contains(ArchiveEntry archiveEntry);

}
