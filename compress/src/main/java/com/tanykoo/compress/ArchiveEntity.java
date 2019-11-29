package com.tanykoo.compress;

/**
 * @author ThinkPad
 * Created : 2019-11-29 9:39
 * @since
 */
public interface ArchiveEntity {
    String getName();
    boolean isDirectory();
    long getSize();
    long getLastModifyTime();
    String getCompressMethod();
    String getFullName();
    long getCompressSize();
}
