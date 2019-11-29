package com.tanykoo.compress;

/**
 * @author Tanykoo
 */
public enum ArchiveType {
    //
    SEVEN_ZIP,
    //
    ZIP,
    //
    XZ,
    //
    GZIP,
    //
    TAR,
    //
    RAR,
    //
    AR;

    AbstractExtractor get(){
        switch (this){
            case AR:
                return null;
        }
        return null;
    }
    byte[] getHeader(){
        switch (this){
            case AR:
                return null;
        }
        return null;
    }
}
