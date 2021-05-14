package cn.qixqi.pan.filesharing.controller;

import cn.qixqi.pan.filesharing.model.FileShareLink;
import cn.qixqi.pan.filesharing.service.GetFileShareLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/filesharing")
public class GetFileShareLinkController {

    private static final Logger logger = LoggerFactory.getLogger(GetFileShareLinkController.class);

    @Autowired
    private GetFileShareLinkService getFileShareLinkService;

    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public List<FileShareLink> checkShareInfo(@RequestParam String uid, @RequestParam String shareId,
                                              @RequestParam String sharePass){
        return getFileShareLinkService.checkShareInfo(uid, shareId, sharePass);
    }
}
