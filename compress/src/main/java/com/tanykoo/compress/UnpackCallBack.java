package com.tanykoo.compress;

/**
 * @Author ThinkPad
 * Created : 2018-12-28 17:01
 * @Since
 */
public interface UnpackCallBack {

    /**
     * 解压进度变化
     * @param count 已解压长度
     * @param total 总解压长度
     */
    void unpackProgressChanged(long count,long total);

    /**
     * 当一个实体开始解压时触发
     * @param archiveEntity
     */
    void entityStatUnpack(ArchiveEntity archiveEntity);

    /**
     * 当一个实体解压成功后触发
     * @param archiveEntity
     */
    void entityUnpackOk(ArchiveEntity archiveEntity);

    /**
     * 当一个实体解压报错时触发
     * @param archiveEntity
     * @return 当选择多个实体解压时，若返回false ，则不继续往下解压，若返回true，跳过该报错实体继续解压
     */
    boolean unpackError(ArchiveEntity archiveEntity,Exception e);

    /**
     * 当一个实体解压警告时触发
     * @param archiveEntity
     */
    boolean unpackWarn(ArchiveEntity archiveEntity,Exception e);

}
