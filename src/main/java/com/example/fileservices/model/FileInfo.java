package com.example.fileservices.model;

import java.io.Serializable;

/**
 * Model class containing information about the File.
 *
 *
 *
 *
 */

public class FileInfo implements Serializable{

    private String fileName;
    private String fileMimeType;
    private long fileSize;
    private String fileExtension;

    private FileInfo(){

    }

    public FileInfo(String fileName, String fileMimeType, long fileSize, String fileExtension){
        this.fileName = fileName;
        this.fileMimeType = fileMimeType;
        this.fileSize = fileSize;
        this.fileExtension = fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileMimeType() {
        return fileMimeType;
    }

    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileInfo)) return false;

        FileInfo fileInfo = (FileInfo) o;

        if (fileSize != fileInfo.fileSize) return false;
        if (!fileName.equals(fileInfo.fileName)) return false;
        if (!fileMimeType.equals(fileInfo.fileMimeType)) return false;
        return fileExtension.equals(fileInfo.fileExtension);
    }

    @Override
    public int hashCode() {
        int result = fileName.hashCode();
        result = 31 * result + fileMimeType.hashCode();
        result = 31 * result + (int) (fileSize ^ (fileSize >>> 32));
        result = 31 * result + fileExtension.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileName='" + fileName + '\'' +
                ", fileMimeType='" + fileMimeType + '\'' +
                ", fileSize=" + fileSize +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }
}
