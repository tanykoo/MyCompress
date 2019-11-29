package com.tanykoo.compress.rar;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.impl.FileVolumeManager;
import com.github.junrar.rarfile.FileHeader;
import com.tanykoo.compress.ArchiveEntity;
import com.tanykoo.compress.ArchiveFile;
import com.tanykoo.compress.ArchiveType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * Created : 2019-11-29 13:40
 * @since
 */
public class RarArchiveFile implements ArchiveFile {
    private File file;
    private List<ArchiveEntity> list;
    private ArchiveType type;

    public RarArchiveFile(File file) throws IOException, RarException {
        this.file = file;
        list = new ArrayList<>();
        type = ArchiveType.RAR;
        Archive archive = new Archive(new FileVolumeManager(file),null);

        for(FileHeader fh : archive){
            RarArchiveEntity entity = new RarArchiveEntity();
            RarExtractor.getName(fh);
            list.add(entity);
        }

    }

    @Override
    public List<ArchiveEntity> getArchiveEntities() {
        return list;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public ArchiveType getType() {
        return type;
    }

    @Override
    public ArchiveEntity getArchiveEntity(String entityName) {
        return null;
    }
}
