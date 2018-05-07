package com.example.fileservices.services;

import com.example.fileservices.model.FileInfo;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Interface for file services
 * Methods for File services, wherein we list action implemented by File Service.
 *
 */
public interface FileServices {

    /**
     * Scan and retrieve complete list of files from the configured location.
     * @return
     */
    public Collection<FileInfo> getAllFiles();

    /**
     * Retrieve File for the fileName specified.
     * @return
     * @throws IOException
     */
    public Collection<File> getPermittedFiles() throws IOException;


}
