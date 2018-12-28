package com.tanykoo.compress.rar;

import com.github.junrar.Archive;
import com.github.junrar.UnrarCallback;
import com.github.junrar.Volume;
import com.github.junrar.exception.RarException;
import com.github.junrar.impl.FileVolumeManager;
import com.tanykoo.compress.ArchiveFile;
import com.tanykoo.compress.Extractor;
import com.tanykoo.compress.UnpackCallBack;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 18:01
 * @Since
 */
public class RarExtractor implements Extractor {
    private static Log logger = LogFactory.getLog(RarExtractor.class);

    private List<UnpackCallBack> unpackCallBackList = new ArrayList<>();

    private Callback callback = new Callback();


    @Override
    public boolean extract(ArchiveFile archiveFile, ArchiveEntry archiveEntry, String destDirectory) {
        try {
            Archive archive = new Archive(new FileVolumeManager(archiveFile.getFile()),callback);

        } catch (RarException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean extract(ArchiveFile archiveFile, String destDirectory) {

        return false;
    }

    @Override
    public void addUnpackCallBack(UnpackCallBack unpackCallBack) {
        unpackCallBackList.add(unpackCallBack);
    }

    class Callback implements UnrarCallback{

        @Override
        public boolean isNextVolumeReady(Volume nextVolume) {
            return false;
        }

        @Override
        public void volumeProgressChanged(long current, long total) {
            for(UnpackCallBack unpackCallBack : unpackCallBackList){
                unpackCallBack.unpackProgressChanged(current,total);
            }
        }
    }

    @Override
    public void setCrtDir(boolean flag) {

    }
}
