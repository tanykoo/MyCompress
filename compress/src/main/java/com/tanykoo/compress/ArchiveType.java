package com.tanykoo.compress;

public enum ArchiveType {
    SEVEN_ZIP,ZIP,XZ,GZIP,TAR,RAR,AR;

    Extractor get(){
        switch (this){
            case AR:
                return null;
        }
        return null;
    }
}
