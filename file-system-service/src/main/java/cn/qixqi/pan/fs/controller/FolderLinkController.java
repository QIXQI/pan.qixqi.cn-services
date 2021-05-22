package cn.qixqi.pan.fs.controller;

import cn.qixqi.pan.fs.model.FolderLink;
import cn.qixqi.pan.fs.service.FolderLinkService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/filesystem/folderLink")
public class FolderLinkController {

    private static final Logger logger = LoggerFactory.getLogger(FolderLinkController.class);

    @Autowired
    private FolderLinkService folderLinkService;

    @RequestMapping(value = "/{folderId}", method = RequestMethod.GET)
    public FolderLink getFolderLink(@PathVariable String folderId){
        return folderLinkService.getFolderLink(folderId);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<FolderLink> getFolderLinks(){
        return folderLinkService.getFolderLinks();
    }

    /**
     * 仅用于测试，或管理员使用
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<FolderLink> getAllFolderLink(){
//        logger.debug("In FolderLinkController, call getAllFolderLink()");
        return folderLinkService.getAllFolderLink();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public FolderLink addFolderLink(@RequestBody FolderLink folderLink){
        return folderLinkService.addFolderLink(folderLink);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void updateFolderLink(@RequestBody FolderLink folderLink){
        folderLinkService.updateFolderLink(folderLink);
    }

    @RequestMapping(value = "/{folderId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFolderLink(@PathVariable String folderId){
        folderLinkService.deleteFolderLink(folderId);
        return String.format("删除文件夹链接：%s", folderId);
    }

    @RequestMapping(value = "/child/children", method = RequestMethod.POST)
    public String addChildren(@RequestBody FolderLink folderLink){
        long status = folderLinkService.addChildren(folderLink);
        JSONObject object = new JSONObject();
        object.put("status", status);
        return object.toJSONString();
    }

    @RequestMapping(value = "/child/children", method = RequestMethod.DELETE)
    public String deleteChildren(@RequestBody FolderLink folderLink){
        long status = folderLinkService.deleteChildren(folderLink);
        JSONObject object = new JSONObject();
        object.put("status", status);
        return object.toJSONString();
    }

    @RequestMapping(value = "/child/children", method = RequestMethod.PUT)
    public String updateChildren(@RequestBody FolderLink folderLink){
        long status = folderLinkService.updateChildren(folderLink);
        JSONObject object = new JSONObject();
        object.put("status", status);
        return object.toJSONString();
    }
}
