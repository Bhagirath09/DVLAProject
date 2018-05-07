package com.example.fileservices.services;


import com.example.fileservices.model.FileInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * File Service implementation;.
 *
 */
public class FileServicesImpl implements FileServices{

    private static final String SCAN_DIR = "scan.dir";
    private static final String EXTN_TYPES = "extn.types";
    private static FileServicesImpl fileServicesImpl = FileServicesHolder.INSTANCE;
    private String scanDir = null;
    private String extnTypes = null;
    private String[]  arrExtn = null;

    /**
     * Private Constructor.
     */
    private FileServicesImpl(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources");
        scanDir = resourceBundle.getString(SCAN_DIR);
        extnTypes =  resourceBundle.getString(EXTN_TYPES);
        arrExtn = extnTypes.split(",");
    }

    /**
    *   Provide Single Instance of the Service.
     */
    public static final FileServices getInstance(){
        return fileServicesImpl;
    }

    @Override
    public Collection<FileInfo> getAllFiles(){
        List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
        if(scanDir!=null && !StringUtils.isEmpty(scanDir) && Files.exists(Paths.get(scanDir))){
            try {
                List<Path> pathList = Files.list(Paths.get(scanDir))
                        .collect(Collectors.toList());
                for(Path path: pathList){
                    if(!path.toFile().isDirectory()){
                        String fileExtn = FilenameUtils.getExtension(path.getFileName().toString());
                        long filesize = path.toFile().length();
                        String fileMimeType = Files.probeContentType(path);
                        String fileName = path.getFileName().toAbsolutePath().toString();
                        FileInfo fileInfo = new FileInfo(fileName, fileMimeType, filesize, fileExtn);
                        fileInfoList.add(fileInfo);
                    }
                }


            }catch(IOException iEx){
                System.out.println("Exception while performing retrieval for files. "+ iEx.getMessage());
            }
        }
        return  fileInfoList;
    }

    @Override
    public Collection<File> getPermittedFiles() throws IOException{
        if(scanDir!=null && arrExtn !=null && !StringUtils.isEmpty(scanDir)){
            return  FileUtils.listFiles(Paths.get(scanDir).toFile(),arrExtn, false );
        }
        return null;
    }

    private static class FileServicesHolder{
        public static final FileServicesImpl INSTANCE = new FileServicesImpl();
    }

}//end of class.
