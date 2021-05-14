package cn.qixqi.pan.filesharing.service;

import cn.qixqi.pan.filesharing.model.FileShare;
import cn.qixqi.pan.filesharing.model.FileShareLink;
import cn.qixqi.pan.filesharing.repository.FileShareLinkRepository;
import cn.qixqi.pan.filesharing.repository.FileShareRedisRepository;
import cn.qixqi.pan.filesharing.repository.FileShareRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFileShareLinkService {

    private static final Logger logger = LoggerFactory.getLogger(GetFileShareLinkService.class);

    @Autowired
    private FileShareRepository fileShareRepository;

    @Autowired
    private FileShareRedisRepository fileShareRedisRepository;

    @Autowired
    private FileShareLinkRepository fileShareLinkRepository;

    private FileShare checkRedisCache(String shareId){
        try {
            return fileShareRedisRepository.getFileShare(shareId);
        } catch (Exception ex){
            logger.error("在Redis缓存中获取文件分享{}发生异常，异常信息：{}", shareId, ex);
            return null;
        }
    }

    private void cacheFileShare(FileShare fileShare){
        try {
            fileShareRedisRepository.saveFileShare(fileShare);
        } catch (Exception ex){
            logger.error("在Redis中缓存文件分享{}发生异常，异常信息：{}", fileShare.getShareId(), ex);
        }
    }

    private FileShare checkFileShareByPass(String uid, String shareId, String sharePass){
        // 获取缓存
        FileShare fileShare = checkRedisCache(shareId);
        if (fileShare != null){
            logger.debug("在Redis缓存中获取到文件分享：{}", fileShare.toString());
            if (fileShare.getUid().equals(uid) && fileShare.getSharePass().equals(sharePass)){
                return fileShare;
            } else {
                logger.debug("获取分享文件信息不匹配, uid:{}, shareId:{}, sharePass:{}", uid, shareId, sharePass);
                return null;
            }
        }
        logger.debug("在Redis缓存中没有获取到文件分享：{}", shareId);

        // 数据库获取
        fileShare = fileShareRepository.findByUidAndShareIdAndSharePass(uid, shareId, sharePass);

        // 存入缓存
        if (fileShare != null){
            cacheFileShare(fileShare);
        }

        return fileShare;
    }

    private List<FileShareLink> getFileShareLinks(FileShare fileShare){
        if (fileShare == null){
            logger.debug("获取文件分享链接失败，fileShare为空");
            return null;
        }
        return fileShareLinkRepository.findByShareId(fileShare.getShareId());
    }

    public List<FileShareLink> checkShareInfo(String uid, String shareId, String sharePass){
        FileShare fileShare = checkFileShareByPass(uid, shareId, sharePass);
        return getFileShareLinks(fileShare);
    }
}
