package cn.qixqi.pan.filesharing.service;

import cn.qixqi.pan.filesharing.model.FileShareLink;
import cn.qixqi.pan.filesharing.repository.FileShareLinkRedisRepository;
import cn.qixqi.pan.filesharing.repository.FileShareLinkRepository;
import cn.qixqi.pan.filesharing.repository.UpdateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileShareLinkService {

    private static final Logger logger = LoggerFactory.getLogger(FileShareLinkService.class);

    @Autowired
    private FileShareLinkRepository repository;

    @Autowired
    private FileShareLinkRedisRepository redisRepository;

    @Autowired
    private UpdateRepository updateRepository;

    private FileShareLink checkRedisCache(String folderId){
        try {
            return redisRepository.getFileShareLink(folderId);
        } catch (Exception ex){
            logger.error("在Redis缓存中获取文件分享链接{}发生异常，异常信息：{}", folderId, ex);
            return null;
        }
    }

    private void cacheFileShareLink(FileShareLink fileShareLink){
        try {
            redisRepository.saveFileShareLink(fileShareLink);
        } catch (Exception ex){
            logger.error("在Redis中缓存文件分享链接{}发生异常，异常信息：{}", fileShareLink.getFolderId(), ex);
        }
    }

    public FileShareLink getFileShareLink(String shareId, String folderId){
        // 获取缓存
        FileShareLink fileShareLink = checkRedisCache(folderId);
        if (fileShareLink != null){
            logger.debug("在Redis缓存中获取到文件分享链接：{}", fileShareLink.toString());
            return fileShareLink;
        }
        logger.debug("在Redis缓存中没有获取到文件分享链接：{}", folderId);

        // 数据库获取
        fileShareLink = repository.findByShareIdAndFolderId(shareId, folderId);

        // 存入缓存
        if (fileShareLink != null){
            cacheFileShareLink(fileShareLink);
        }
        return fileShareLink;
    }

    public List<FileShareLink> getFileShareLinks(String shareId){
        return repository.findByShareId(shareId);
    }

    // 测试或管理员
    public List<FileShareLink> getAllFileShareLink(){
        return repository.findAll();
    }

    public FileShareLink addFileShareLink(FileShareLink fileShareLink){
        return repository.save(fileShareLink);
    }

    public long updateFileShareLink(FileShareLink fileShareLink){
        // 删除缓存
        redisRepository.deleteFileShareLink(fileShareLink.getFolderId());
        return updateRepository.updateFileShareLink(fileShareLink);
    }

    public void deleteFileShareLink(String shareId, String folderId){
        // 删除缓存
        redisRepository.deleteFileShareLink(folderId);
        // 删除数据库信息
        repository.deleteByShareIdAndFolderId(shareId, folderId);
    }
}
