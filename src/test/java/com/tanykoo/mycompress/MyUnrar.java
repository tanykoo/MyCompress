package com.tanykoo.mycompress;


import com.github.junrar.UnrarCallback;
import com.github.junrar.Volume;

/**
 * @Author ThinkPad
 * Created : 2018-12-25 17:52
 * @Since
 */
public class MyUnrar implements UnrarCallback {


    @Override
    public boolean isNextVolumeReady(Volume nextVolume) {
        return false;
    }

    @Override
    public void volumeProgressChanged(long current, long total) {
        System.out.println("----------"+current + ":" + total);

    }
}
