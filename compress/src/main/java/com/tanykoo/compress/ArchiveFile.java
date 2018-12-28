package com.tanykoo.compress;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.List;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 16:37
 * @Since
 */
public class ArchiveFile {
    private static Log logger = LogFactory.getLog(ArchiveFile.class);

    private ArchiveType type;

    private List<UnpackCallBack> unpackCallBackList;

    private List<ArchiveEntry> archiveEntries;

    private Extractor extractor;

    private File file;

    private byte[] password;

    public ArchiveFile(String file){
        this(new File(file));
    }
    public ArchiveFile(File file){
        this(file,null);
    }
    public ArchiveFile(File file,String password){

    }

    public List<ArchiveEntry> getArchiveEntries(){
        return archiveEntries;
    }

    public File getFile() {
        return file;
    }
}
