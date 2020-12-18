package com.example.wrap.zip;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author zhangxiaoyu
 * @date 2020/12/11
 */
public class ZipSimple {

    @Test
    public void tset(){
        File file = new File("/tmp/dim/temp/归档.zip");
        System.out.println(file.getName());
        System.out.println(file.getPath());
    }


    @Test
    public  void m() throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream("/tmp/dim/temp/归档.zip"));
        File destDir = new File("/Users/zhangxiaoyu/tmp/unzipTest");
        byte[] buffer = new byte[1024 * 1024];
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    @Test
    public void t() throws IOException {
        ZipFile zipFile = new ZipFile("/tmp/dim/temp/归档.zip");

        Path target = Paths.get("/Users/zhangxiaoyu/tmp/unzip/");
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()){
            final ZipEntry zipEntry = entries.nextElement();
            final InputStream inputStream = zipFile.getInputStream(zipEntry);

            Path newPath = zipSlipProtect(zipEntry, target);
            if (zipEntry.isDirectory()) {
                Files.createDirectories(newPath);
            } else {
                if (newPath.getParent() != null) {
                    if (Files.notExists(newPath.getParent())) {
                        Files.createDirectories(newPath.getParent());
                    }
                }
                Files.copy(inputStream, newPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    @Test
    public void  t1() throws IOException {
        Path target = Paths.get("/Users/zhangxiaoyu/tmp/unzip/");
        try (FileInputStream fis = new FileInputStream("/Users/zhangxiaoyu/tmp/1.zip");BufferedInputStream bis = new BufferedInputStream(fis);
             ZipInputStream zis = new ZipInputStream(bis)) {
            // list files in zip
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                Path newPath = zipSlipProtect(zipEntry, target);
                if (zipEntry.isDirectory()) {
                    Files.createDirectories(newPath);
                } else {
                    if (newPath.getParent() != null) {
                        if (Files.notExists(newPath.getParent())) {
                            Files.createDirectories(newPath.getParent());
                        }
                    }
                    // copy files, nio
                    Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
        }
    }
    // protect zip slip attack
    public static Path zipSlipProtect(ZipEntry zipEntry, Path targetDir)
            throws IOException {

        // test zip slip vulnerability
        // Path targetDirResolved = targetDir.resolve("../../" + zipEntry.getName());

        Path targetDirResolved = targetDir.resolve(zipEntry.getName());

        // make sure normalized file still has targetDir as its prefix
        // else throws exception
        Path normalizePath = targetDirResolved.normalize();
        if (!normalizePath.startsWith(targetDir)) {
            throw new IOException("Bad zip entry: " + zipEntry.getName());
        }

        return normalizePath;
    }




}
