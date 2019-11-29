package com.tanykoo.compress.rar;

import com.github.junrar.Archive;
import com.github.junrar.UnrarCallback;
import com.github.junrar.Volume;
import com.github.junrar.exception.RarException;
import com.github.junrar.impl.FileVolumeManager;
import com.github.junrar.rarfile.FileHeader;
import com.tanykoo.compress.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 18:01
 * @Since
 */
public class RarExtractor extends AbstractExtractor {
    private static Log logger = LogFactory.getLog(RarExtractor.class);
    private Callback callback = new RarExtractor.Callback();

    @Override
    public boolean extract(ArchiveFile archiveFile, ArchiveEntity archiveEntity, File destDirectory) {
        Map<String,ArchiveEntity> map = new HashMap<>(16);
        if(archiveEntity.isDirectory()){
            for(ArchiveEntity entity : archiveFile.getArchiveEntities()){
                if(entity.getFullName().startsWith(archiveEntity.getFullName())){
                    map.put(entity.getFullName(),entity);
                }
            }
        }else {
            map.put(archiveEntity.getFullName(),archiveEntity);
        }
        String subdir = archiveEntity.getFullName().substring(0,archiveEntity.getFullName().lastIndexOf("\\\\"));
        try {
            Archive archive = new Archive(new FileVolumeManager(archiveFile.getFile()),callback);
            return extract(archive,map,subdir,destDirectory);
        } catch (RarException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean extract(Archive archive, Map<String,ArchiveEntity> archiveEntityMap,String subDir,File destDirectory){
        for(FileHeader fh : archive){
            if(archiveEntityMap.containsKey(getName(fh))){
                ArchiveEntity entity = archiveEntityMap.get(getName(fh));
                startUnpack(entity);
                File f = null;
                try {
                    if(fh.isDirectory()){
                        f = ExtractorUtils.mkdir(entity,subDir,destDirectory);
                    }else {
                        f = ExtractorUtils.createFile(entity, subDir,destDirectory);
                        FileOutputStream os = new FileOutputStream(f);
                        archive.extractFile(fh, os);
                        os.close();
                    }
                    f.setLastModified(entity.getLastModifyTime());
                    unpackOk(entity);
                } catch (RarException e) {
                    if(f!=null && f.exists()){
                        f.delete();
                    }
                    logger.error("unpack Entity:[" + getName(fh) +"] error",e);
                    if(unpackError(archiveEntityMap.get(getName(fh)),e)){
                        continue;
                    }else{
                        return false;
                    }
                } catch (IOException e) {
                    logger.error("unpack Entity:[" + getName(fh) +"] error",e);
                    if(f!=null && f.exists()){
                        f.delete();
                    }
                    return false;
                }
            }
        }
        return true;
    }



    @Override
    public boolean extract(ArchiveFile archiveFile, String destDirectory) {

        return false;
    }
    class Callback implements UnrarCallback{

        @Override
        public boolean isNextVolumeReady(Volume nextVolume) {
            return false;
        }

        @Override
        public void volumeProgressChanged(long current, long total) {
            if(current!=0) {
                for (UnpackCallBack unpackCallBack : getUnpackCallBack()) {
                    unpackCallBack.unpackProgressChanged(current, total);
                }
            }
        }
    }


    public static String getName(FileHeader fileHeader){
        if(fileHeader.isUnicode()){
            return fileHeader.getFileNameString();
        }else{
            return fileHeader.getFileNameW();
        }
    }
}
