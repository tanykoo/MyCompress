package com.tanykoo.compress;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 压缩文件提取器
 * @Author ThinkPad
 * Created : 2018-12-28 17:14
 * @Since
 */
public abstract class AbstractExtractor {
    private List<UnpackCallBack> unpackCallBackList = new ArrayList<UnpackCallBack>();
    protected boolean crtDir=false;

    /**
     * 解压压缩文件中某个实体到指定目录下
     * @param archiveFile 压缩文件
     * @param archiveEntity 指定实体
     * @param destDirectory 解压路径
     * @return true ： 解压成功
     *          false： 解压失败
     */
    public abstract boolean extract(ArchiveFile archiveFile, ArchiveEntity archiveEntity,File destDirectory);

    /**
     * 解压压缩文件到指定目录下
     * @param archiveFile 压缩文件
     * @param destDirectory 解压路径
     * @return true ： 解压成功
     *          false： 解压失败
     */
    public abstract boolean extract(ArchiveFile archiveFile, String destDirectory);

    /**
     * 增加解压事件处理器
     * @param unpackCallBack
     */
    public void addUnpackCallBack(UnpackCallBack unpackCallBack) {
        unpackCallBackList.add(unpackCallBack);
    }
    public List<UnpackCallBack> getUnpackCallBack(){
        return unpackCallBackList;
    }
    public void setCrtDir(boolean flag){
        this.crtDir = flag;
    }

    protected void startUnpack(ArchiveEntity archiveEntity){
        for(UnpackCallBack unpackCallBack : getUnpackCallBack()){
            unpackCallBack.entityStatUnpack(archiveEntity);
        }
    }

    protected void unpackOk(ArchiveEntity archiveEntity){
        for(UnpackCallBack unpackCallBack : getUnpackCallBack()){
            unpackCallBack.entityUnpackOk(archiveEntity);
        }
    }

    protected boolean unpackError(ArchiveEntity archiveEntity,Exception e){
        for(UnpackCallBack unpackCallBack : getUnpackCallBack()){
            if(unpackCallBack.unpackError(archiveEntity,e)){
                continue;
            }else {
                return false;
            }
        }
        return true;
    }

    protected boolean unpackWarn(ArchiveEntity archiveEntity,Exception e){
        for(UnpackCallBack unpackCallBack : getUnpackCallBack()){
            if(unpackCallBack.unpackWarn(archiveEntity,e)){
                continue;
            }else {
                return false;
            }
        }
        return true;
    }
}
