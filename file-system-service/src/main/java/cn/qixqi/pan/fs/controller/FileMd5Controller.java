package cn.qixqi.pan.fs.controller;

import cn.qixqi.pan.fs.model.FileMd5;
import cn.qixqi.pan.fs.service.FileMd5Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/filesystem/fileMd5")
public class FileMd5Controller {

    private static final Logger logger = LoggerFactory.getLogger(FileMd5Controller.class);

    @Autowired
    private FileMd5Service fileMd5Service;

    @RequestMapping(value = "/{md5}", method = RequestMethod.GET)
    public String getFileId(@PathVariable String md5){
        FileMd5 fileMd5 = fileMd5Service.getFileMd5(md5);
        if (fileMd5 != null){
            return fileMd5.getFileId();
        } else {
            return "";
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<FileMd5> getAllFileMd5(){
        return fileMd5Service.getAllFileMd5();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public FileMd5 addFileMd5(@RequestBody FileMd5 fileMd5){
        return fileMd5Service.addFileMd5(fileMd5);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public FileMd5 updateFileMd5(@RequestBody FileMd5 fileMd5){
        return fileMd5Service.updateFileMd5(fileMd5);
    }

    @RequestMapping(value = "/{md5}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFileMd5(@PathVariable String md5){
        return fileMd5Service.deleteFileMd5(md5);
    }
}
