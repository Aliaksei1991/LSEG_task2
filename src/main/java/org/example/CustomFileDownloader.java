package org.example;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class CustomFileDownloader {
    public String downloadXlsFile(String path) {
        String localFilePath = "file.xls";
        try (BufferedInputStream input = new BufferedInputStream(new URL(path).openStream());
             FileOutputStream output = new FileOutputStream(localFilePath)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(dataBuffer, 0, 1024)) != -1) {
                output.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return localFilePath;
    }
}
