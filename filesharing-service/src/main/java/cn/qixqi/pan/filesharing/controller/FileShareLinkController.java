package cn.qixqi.pan.filesharing.controller;

import cn.qixqi.pan.filesharing.model.FileShareLink;
import cn.qixqi.pan.filesharing.service.FileShareLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/filesharing")
public class FileShareLinkController {

    private static final Logger logger = LoggerFactory.getLogger(FileShareLinkController.class);

    @Autowired
    private FileShareLinkService fileShareLinkService;

    @RequestMapping(value = "/fileShare/{shareId}/fileShareLink/{folderId}", method = RequestMethod.GET)
    public FileShareLink getFileShareLink(@PathVariable String shareId, @PathVariable String folderId){
        return fileShareLinkService.getFileShareLink(shareId, folderId);
    }

    @RequestMapping(value = "/fileShareLink/{shareId}", method = RequestMethod.GET)
    public List<FileShareLink> getFileShareLinks(@PathVariable String shareId){
        return fileShareLinkService.getFileShareLinks(shareId);
    }

    @RequestMapping(value = "/fileShareLink", method = RequestMethod.GET)
    public List<FileShareLink> getAllFileShareLink(){
        return fileShareLinkService.getAllFileShareLink();
    }

    @RequestMapping(value = "/fileShareLink", method = RequestMethod.POST)
    public FileShareLink addFileShareLink(@RequestBody FileShareLink fileShareLink){
        return fileShareLinkService.addFileShareLink(fileShareLink);
    }

    @RequestMapping(value = "/fileShareLink", method = RequestMethod.PUT)
    public long updateFileShareLink(@RequestBody FileShareLink fileShareLink){
        return fileShareLinkService.updateFileShareLink(fileShareLink);
    }

    @RequestMapping(value = "/fileShare/{shareId}/fileShareLink/{folderId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFileShareLink(@PathVariable String shareId, @PathVariable String folderId){
        fileShareLinkService.deleteFileShareLink(shareId, folderId);
        return String.format("删除文件分享链接：%s", folderId);
    }
}
