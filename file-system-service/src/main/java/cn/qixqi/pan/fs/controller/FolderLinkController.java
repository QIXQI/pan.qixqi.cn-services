package cn.qixqi.pan.fs.controller;

import cn.qixqi.pan.fs.model.FolderLink;
import cn.qixqi.pan.fs.service.FolderLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "filesystem/folderLink")
public class FolderLinkController {

    @Autowired
    private FolderLinkService folderLinkService;

    @RequestMapping(value = "/{folderId}", method = RequestMethod.GET)
    public FolderLink getFolderLink(@PathVariable String folderId){
        return folderLinkService.getFolderLink(folderId);
    }

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
    public List<FolderLink> getFolderLinks(@PathVariable String uid){
        return folderLinkService.getFolderLinks(uid);
    }

    /**
     * 仅用于测试，或管理员使用
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<FolderLink> getAllFolderLink(){
        return folderLinkService.getAllFolderLink();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public FolderLink addFolderLink(@RequestBody FolderLink folderLink){
        return folderLinkService.addFolderLink(folderLink);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public FolderLink updateFolderLink(@RequestBody FolderLink folderLink){
        return folderLinkService.updateFolderLink(folderLink);
    }

    @RequestMapping(value = "/{folderId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFolderLink(@PathVariable String folderId){
        folderLinkService.deleteFolderLink(folderId);
        return String.format("删除：%s", folderId);
    }

}
