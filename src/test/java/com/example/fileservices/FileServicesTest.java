package com.example.fileservices;

import com.example.fileservices.model.FileInfo;
import com.example.fileservices.services.FileServices;
import com.example.fileservices.services.FileServicesImpl;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
/**
 * Created by avantrik on 06/05/2018.
 */
public class FileServicesTest {



    @Test
    public void testGetAllFiles(){
        FileServices fileServices = FileServicesImpl.getInstance();
        Collection<FileInfo> fileList =  fileServices.getAllFiles();
        assertTrue("Condition for getAllFileInfo successful ", fileList!=null && !fileList.isEmpty());
        for(FileInfo fileInfo : fileList){
            System.out.println(fileInfo);
        }
    }


    /**
     * Set the scan.dir to invalid path
     *
     */
    @Test
    public void testInvalidGetAllFiles(){
        FileServices fileServices = FileServicesImpl.getInstance();
        Collection<FileInfo> fileList =  fileServices.getAllFiles();
        System.out.println("Size of fileList "+fileList==null?0:fileList.size());
        assertTrue("Condition for getAllFileInfo successful ", fileList==null || fileList.isEmpty());
    }

    @Test
    public void testGetPermittedFiles(){
        FileServices fileServices = FileServicesImpl.getInstance();
        try {
            Collection<File> permittedFileList = fileServices.getPermittedFiles();
            assertTrue("Permitted files are present ", permittedFileList!=null && !permittedFileList.isEmpty());
            for(File file : permittedFileList){
                System.out.println("File : "+ file.getAbsolutePath());
            }
        }catch (IOException iEx){
            System.out.println("Exception while retriving Permitted Files List. "+ iEx.getMessage());

        }
    }


}
