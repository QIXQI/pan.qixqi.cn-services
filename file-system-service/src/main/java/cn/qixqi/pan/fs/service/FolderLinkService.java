package cn.qixqi.pan.fs.service;

import cn.qixqi.pan.fs.model.FileLink;
import cn.qixqi.pan.fs.model.FolderChildren;
import cn.qixqi.pan.fs.model.FolderLink;
import cn.qixqi.pan.fs.model.SimpleFolderLink;
import cn.qixqi.pan.fs.repository.FolderLinkRedisRepository;
import cn.qixqi.pan.fs.repository.FolderLinkRepository;
import cn.qixqi.pan.fs.util.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        // 为文件夹链接分配ID和创建时间
        folderLink.setFolderId(UUID.randomUUID().toString());
        folderLink.setCreateTime(new Date());
        return folderLinkRepository.save(folderLink);
    }

    public void updateFolderLink(FolderLink folderLink){
        // 删除缓存
        folderLinkRedisRepository.deleteFolderLink(folderLink.getFolderId());
        folderLinkRepository.update(folderLink);
    }

    /**
     * 添加子文件夹或子文件
     * @param folderLink
     * @return 修改行数
     */
    public long addChildren(FolderLink folderLink){
        // 删除缓存
        folderLinkRedisRepository.deleteFolderLink(folderLink.getFolderId());
        FolderChildren children = folderLink.getChildren();
        if (children.getFiles() != null){
            // 为文件链接分配ID和创建时间
            for (FileLink fileLink : children.getFiles()){
                fileLink.setLinkId(UUID.randomUUID().toString());
                fileLink.setCreateTime(new Date());
            }
        }
        return folderLinkRepository.addChildren(folderLink);
    }

    /**
     * 删除子文件夹或子文件
     * @param folderLink
     * @return 修改行数
     */
    public long deleteChildren(FolderLink folderLink){
        // 删除缓存
        folderLinkRedisRepository.deleteFolderLink(folderLink.getFolderId());
        return folderLinkRepository.deleteChildren(folderLink);
    }

    /**
     * 一次只能更新一个子文件夹名或一个子文件名
     * @param folderLink
     * @return 修改行数
     */
    public long updateChildren(FolderLink folderLink){
        // 删除缓存
        folderLinkRedisRepository.deleteFolderLink(folderLink.getFolderId());
        return folderLinkRepository.updateChildren(folderLink);
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
