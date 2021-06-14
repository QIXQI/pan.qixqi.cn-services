package cn.qixqi.pan.filesharing.controller;

import cn.qixqi.pan.filesharing.model.FileShare;
import cn.qixqi.pan.filesharing.model.FileShareLink;
import cn.qixqi.pan.filesharing.service.FileShareService;
import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.map.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/filesharing")
public class FileShareController {

    private static final Logger logger = LoggerFactory.getLogger(FileShareController.class);

    @Autowired
    private FileShareService fileShareService;

    @RequestMapping(value = "/fileShare/{shareId}", method = RequestMethod.GET)
    public FileShare getFileShare(@PathVariable String shareId){
        return fileShareService.getFileShare(shareId);
    }

    @RequestMapping(value = "/fileShare/user", method = RequestMethod.GET)
    public List<FileShare> getFileShares(){
        return fileShareService.getFileShares();
    }

    @RequestMapping(value = "/fileShare", method = RequestMethod.GET)
    public List<FileShare> getAllFileShare(){
        return fileShareService.getAllFileShare();
    }

    @RequestMapping(value = "/fileShare", method = RequestMethod.POST)
    public FileShare addFileShare(@RequestBody FileShare fileShare){
        return fileShareService.addFileShare(fileShare);
    }

    @RequestMapping(value = "/fileShare/generator", method = RequestMethod.POST)
    public String generateShare(@RequestBody FileShareLink fileShareLink){
        JSONObject object = new JSONObject();
        int status = fileShareService.generateShare(fileShareLink);
        object.put("status", status);
        return object.toJSONString();
    }

    @RequestMapping(value = "/fileShare", method = RequestMethod.PUT)
    public long updateFileShare(@RequestBody FileShare fileShare){
        return fileShareService.updateFileShare(fileShare);
    }

    @RequestMapping(value = "/fileShare/{shareId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFileShare(@PathVariable String shareId){
        fileShareService.deleteFileShare(shareId);
        return String.format("删除文件分享：%s", shareId);
    }
}
