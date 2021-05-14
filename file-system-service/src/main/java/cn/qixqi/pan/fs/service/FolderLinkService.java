package cn.qixqi.pan.fs.service;

import cn.qixqi.pan.fs.model.FolderLink;
import cn.qixqi.pan.fs.repository.FolderLinkRedisRepository;
import cn.qixqi.pan.fs.repository.FolderLinkRepository;
import cn.qixqi.pan.fs.util.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderLinkService {

    private final static Logger logger = LoggerFactory.getLogger(FolderLinkService.class);

    @Autowired
    private FolderLinkRepository folderLinkRepository;

    @Autowired
    private FolderLinkRedisRepository folderLinkRedisRepository;

    public FolderLink getFolderLink(String folderId){
        String uid = UserContextHolder.get().getUid();
        if (uid == null){
            logger.error("UserContext.uid为空");
            return null;
        }

        // 缓存中获取
        FolderLink folderLink = checkRedisCache(folderId);
        if (folderLink != null) {
            logger.debug("在Redis缓存中获取到文件夹链接：{}", folderLink.toString());
            return folderLink;
        }
        logger.debug("在Redis缓存中没有获取到文件夹链接{}", folderId);
        // 数据库获取
        folderLink = folderLinkRepository.findByFolderId(uid, folderId);
        // 存入缓存
        if (folderLink != null){
            cacheFolderLink(folderLink);
        }
        return folderLink;
    }

    public List<FolderLink> getFolderLinks(){
        String uid = UserContextHolder.get().getUid();
        if (uid == null){
            logger.error("UserContext.uid为空");
            return null;
        }

        return folderLinkRepository.findByUid(uid);
    }

    public List<FolderLink> getAllFolderLink(){
        return folderLinkRepository.findAll();
    }

    public FolderLink addFolderLink(FolderLink folderLink){
        return folderLinkRepository.save(folderLink);
    }

    public void updateFolderLink(FolderLink folderLink){
        // 删除缓存
        folderLinkRedisRepository.deleteFolderLink(folderLink.getFolderId());
        folderLinkRepository.update(folderLink);
    }

    public void deleteFolderLink(String folderId){
        String uid = UserContextHolder.get().getUid();
        if (uid == null){
            logger.error("UserContext.uid为空");
            return;
        }

        // 删除缓存
        folderLinkRedisRepository.deleteFolderLink(folderId);
        folderLinkRepository.deleteByFolderId(uid, folderId);
    }

    private FolderLink checkRedisCache(String folderId){
        try {
            return folderLinkRedisRepository.getFolderLink(folderId);
        } catch (Exception ex){
            logger.error("在Redis缓存中获取文件夹链接{}发生异常，异常信息：{}", folderId, ex);
            return null;
        }
    }

    private void cacheFolderLink(FolderLink folderLink){
        try {
            folderLinkRedisRepository.saveFolderLink(folderLink);
        } catch (Exception ex){
            logger.error("在Redis中缓存文件夹链接{}发生异常，异常信息：{}", folderLink.getFolderId(), ex);
        }
    }
}
