
import com.util.*;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.*;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @Author ThinkPad
 * Created : 2018-12-16 16:21
 * @Since
 */
public class A {

    public static void main(String[] args) {
        File f = new File("D:\\新建文件夹\\libssl32_jb51.net.rar");
        System.out.println(new MimetypesFileTypeMap().getContentType(f));
        DosFileAttributeView basicView = Files.
                getFileAttributeView(f.toPath(), DosFileAttributeView.class);
        try {
            DosFileAttributes list = basicView.readAttributes();
            System.out.println(list.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
