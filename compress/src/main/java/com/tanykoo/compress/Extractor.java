package com.tanykoo.compress;

import org.apache.commons.compress.archivers.ArchiveEntry;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 17:14
 * @Since
 */
public interface Extractor {
    boolean extract(ArchiveFile archiveFile, ArchiveEntry archiveEntry,String destDirectory);
    boolean extract(ArchiveFile archiveFile, String destDirectory);
    void addUnpackCallBack(UnpackCallBack unpackCallBack);
    void setCrtDir(boolean flag);
}
