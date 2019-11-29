package com.tanykoo.compress;

import com.tanykoo.compress.rar.RarExtractor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author ThinkPad
 * Created : 2019-11-29 14:32
 * @since
 */
public class ExtractorUtils {
    private static Log logger = LogFactory.getLog(RarExtractor.class);
    public static File mkdir(ArchiveEntity archiveEntity, String subDir, File destDir){
        if(archiveEntity.isDirectory()){
            File f = new File(destDir,archiveEntity.getFullName().substring(subDir.length()-1));
            if (!f.exists()) {
                try {
                    f = makeFile(destDir, archiveEntity.getFullName().substring(subDir.length()-1));
                } catch (final IOException e) {
                    logger.error("error creating the new file: " + f.getName(), e);
                }
            }
            return f;
        }
        return null;
    }

    private static File makeFile(final File destination, final String name) throws IOException {
        final String[] dirs = name.split("\\\\");
        if (dirs == null) {
            return null;
        }
        String path = "";
        final int size = dirs.length;
        if (size == 1) {
            return new File(destination, name);
        } else if (size > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                path = path + File.separator + dirs[i];
                new File(destination, path).mkdir();
            }
            path = path + File.separator + dirs[dirs.length - 1];
            final File f = new File(destination, path);
            f.createNewFile();
            return f;
        } else {
            return null;
        }
    }
    public static File createFile(ArchiveEntity archiveEntity, String subDir, File destDirectory) {
        return null;
    }
}
