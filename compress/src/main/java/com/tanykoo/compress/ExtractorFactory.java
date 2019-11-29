package com.tanykoo.compress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 17:18
 * @Since
 */
public class ExtractorFactory {
    private static Log logger = LogFactory.getLog(ExtractorFactory.class);

    public static AbstractExtractor createExtractor(ArchiveFile archiveFile){
        return archiveFile.getType().get();
    }
}
