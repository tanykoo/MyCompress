package com.tanykoo.compress;


/**
 * 归档文件提取器
 * @Author ThinkPad
 * Created : 2018-12-28 17:14
 * @Since
 */
public interface Extractor<T extends ArchiveEntry> {
    /**
     *
     * @param archiveFile
     * @param archiveEntry
     * @param destDirectory
     * @return
     */
    void extract(ArchiveFile archiveFile, T[] archiveEntry,String destDirectory);
    void extract(ArchiveFile archiveFile, String destDirectory);
    void addUnpackCallBack(UnpackCallBack unpackCallBack);

}
