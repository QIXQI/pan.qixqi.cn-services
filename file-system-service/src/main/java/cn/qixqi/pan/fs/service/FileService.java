package cn.qixqi.pan.fs.service;

import cn.qixqi.pan.fs.event.source.SimpleSourceBean;
import cn.qixqi.pan.fs.model.File;
import cn.qixqi.pan.fs.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private SimpleSourceBean simpleSourceBean;

    public File getFileById(String fileId){
        return fileRepository.findByFileId(fileId);
    }

    public List<File> getFiles(){
        simpleSourceBean.publishMsg();
        return fileRepository.findAll();
    }

    public File addFile(File file){
        return fileRepository.save(file);
    }

    public File updateFile(File file){
        return fileRepository.save(file);
    }

    public String deleteFile(String fileId){
        fileRepository.deleteById(fileId);
        return String.format("删除：%s", fileId);
    }

}
