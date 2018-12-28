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



}