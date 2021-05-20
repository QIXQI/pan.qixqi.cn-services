package cn.qixqi.pan.fs.controller;

import cn.qixqi.pan.fs.config.ServiceConfig;
import cn.qixqi.pan.fs.model.File;
import cn.qixqi.pan.fs.service.FileService;
import cn.qixqi.pan.fs.util.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/filesystem/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private ServiceConfig serviceConfig;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/{fileId}/url", method = RequestMethod.GET)
    public String getFileUrl(@PathVariable String fileId){
        File file = fileService.getFileById(fileId);
        return file != null ? file.getUrl() : null;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/{fileId}", method = RequestMethod.GET)
    public File getFileById(@PathVariable String fileId){
        return fileService.getFileById(fileId);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<File> getFiles(){
        // logger.debug("FileController trace_id: " + UserContextHolder.get().getTraceId());
        return fileService.getFiles();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public File addFile(@RequestBody File file){
        return fileService.addFile(file);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public File updateFile(@RequestBody File file){
        return fileService.updateFile(file);
    }

    @RequestMapping(value = "/{fileId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFile(@PathVariable String fileId){
        return fileService.deleteFile(fileId);
    }
}
