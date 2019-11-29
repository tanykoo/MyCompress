package com.tanykoo.compress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 17:57
 * @Since
 */
public class ArchiveFileBuilder {
    private static Log logger = LogFactory.getLog(ArchiveFileBuilder.class);

//    public static ArchiveFile build(String fileName){
//
//    }
//    public static ArchiveFile build(File file){
//
//    }


    private static ArchiveType getType(){
        return ArchiveType.AR;
    }
}
