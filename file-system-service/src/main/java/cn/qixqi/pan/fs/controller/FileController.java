package cn.qixqi.pan.fs.controller;

import cn.qixqi.pan.fs.config.ServiceConfig;
import cn.qixqi.pan.fs.model.File;
import cn.qixqi.pan.fs.repository.FileRedisRepository;
import cn.qixqi.pan.fs.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping(value = "filesystem")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private ServiceConfig serviceConfig;

    @Autowired
    private FileService fileService;

    @Autowired
    private FileRedisRepository fileRedisRepository;

    @RequestMapping(value = "/redis/{fileId}", method = RequestMethod.GET)
    public File redis(@PathVariable String fileId){
        return fileRedisRepository.findFile(fileId);
    }

    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public String example(){
//        logger.debug("In FileController, call example()");
//        logger.info("In FileController, call example()");
        return serviceConfig.getExampleProperty();
    }

    @RequestMapping(value = "/file/{fileId}", method = RequestMethod.GET)
    public File getFileById(@PathVariable String fileId){
        File file = fileService.getFileById(fileId);
        fileRedisRepository.saveFile(file);
        return file;
    }

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public List<File> getFiles(){
//        logger.debug("In FileController, call getFiles()");
//        logger.info("In FileController, call getFiles()");
        return fileService.getFiles();
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public File addFile(@RequestBody File file){
        return fileService.addFile(file);
    }

    @RequestMapping(value = "/file", method = RequestMethod.PUT)
    public File updateFile(@RequestBody File file){
        return fileService.updateFile(file);
    }

    @RequestMapping(value = "/file/{fileId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFile(@PathVariable String fileId){
        return fileService.deleteFile(fileId);
    }


}
