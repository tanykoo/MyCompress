package com.tanykoo.compress;

import java.io.File;
import java.util.List;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 16:37
 * @Since
 */
public interface ArchiveFile {

    List<ArchiveEntity> getArchiveEntities();

    File getFile() ;

    ArchiveType getType();

    ArchiveEntity getArchiveEntity(String entityName);
}
