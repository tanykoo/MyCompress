package com.tanykoo.compress;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tany
 * @createtime 2018-12-29 9:00 PM
 * @since
 */
public abstract class AbstractArchiveEntry implements ArchiveEntry {

    private static Log log = LogFactory.getLog(AbstractArchiveEntry.class);

    private List<ArchiveEntry> children;


    @Override
    public List<ArchiveEntry> getChildren() {
        return children;
    }

    @Override
    public void addChild(ArchiveEntry archiveEntry) {
        if(isDirectory()){
            if(children == null){
                children = new ArrayList<>();
            }
            if(archiveEntry.getEntryPath().equals(this.getEntryPath()) && !this.contains(archiveEntry)){
                children.add(archiveEntry);
            }
        }
    }

    @Override
    public boolean contains(ArchiveEntry archiveEntry) {

        if(this.equals(archiveEntry))
            return true;

        if(isDirectory() && children != null){
            boolean flag = false;
            for(ArchiveEntry archiveEntry1 : children){
                flag = archiveEntry1.contains(archiveEntry);
                if(flag){
                    return flag;
                }
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ArchiveEntry){
            return getEntryName().equals(((ArchiveEntry) obj).getEntryName()) &&
                    getEntryPath().equals(((ArchiveEntry) obj).getEntryPath());
        }
        return false;
    }
}
