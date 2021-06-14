package cn.qixqi.pan.filesharing.service;

import cn.qixqi.pan.filesharing.model.FileShare;
import cn.qixqi.pan.filesharing.model.FileShareLink;
import cn.qixqi.pan.filesharing.repository.FileShareLinkRepository;
import cn.qixqi.pan.filesharing.repository.FileShareRedisRepository;
import cn.qixqi.pan.filesharing.repository.FileShareRepository;
import cn.qixqi.pan.filesharing.repository.UpdateRepository;
import cn.qixqi.pan.filesharing.util.UserContextHolder;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class FileShareService {

    private static final Logger logger = LoggerFactory.getLogger(FileShareService.class);

    @Autowired
    private FileShareRepository fileShareRepository;

    @Autowired
    private FileShareLinkRepository fileShareLinkRepository;

    @Autowired
    private FileShareRedisRepository fileShareRedisRepository;

    @Autowired
    private UpdateRepository updateRepository;

    /**
     * 生成分享链接
     * @param fileShareLink
     * @return
     */
    public int generateShare(FileShareLink fileShareLink){
        int status = 1;
        String uid = UserContextHolder.get().getUid();
        if (uid == null){
            status = 0;
            logger.error("UserContext.uid为空");
            return status;
        }

        String folderId = UUID.randomUUID().toString();
        String shareId = UUID.randomUUID().toString();
        String sharePass = createPass();

        // 存储 FileShareLink 信息
        fileShareLink.setFolderId(folderId);
        fileShareLink.setShareId(shareId);
        fileShareLinkRepository.save(fileShareLink);

        // 存储 FileShare 信息
        FileShare fileShare = new FileShare();
        fileShare.setShareId(shareId);
        fileShare.setSharePass(sharePass);
        fileShare.setUid(uid);
        fileShare.setRootId(folderId);
        fileShare.setCreateTime(new Date());
        fileShare.setActiveTime(7);
        fileShareRepository.save(fileShare);
        return status;
    }

    public FileShare getFileShare(String shareId){
        // 获取uid
        String uid = UserContextHolder.get().getUid();
        if (uid == null){
            logger.error("不能在UserContext中解析出 uid");
            return null;
        }

        // 获取缓存
        FileShare fileShare = checkRedisCache(shareId);
        if (fileShare != null){
            logger.debug("在Redis缓存中获取到文件分享：{}", fileShare.toString());
            return fileShare;
        }
        logger.debug("在Redis缓存中没有获取到文件分享：{}", shareId);

        // 数据库获取
        fileShare = fileShareRepository.findByUidAndShareId(uid, shareId);

        // 存入缓存
        if (fileShare != null){
            cacheFileShare(fileShare);
        }
        return fileShare;
    }

    public List<FileShare> getFileShares(){
        // 获取uid
        String uid = UserContextHolder.get().getUid();
        if (uid == null){
            logger.error("不能在UserContext中解析出 uid");
            return null;
        }

        // 获取数据库
        return fileShareRepository.findByUid(uid);
    }

    // 测试或管理员
    public List<FileShare> getAllFileShare(){
        return fileShareRepository.findAll();
    }

    public FileShare addFileShare(FileShare fileShare){
        return fileShareRepository.save(fileShare);
    }

    public long updateFileShare(FileShare fileShare){
        // 删除缓存
        fileShareRedisRepository.deleteFileShare(fileShare.getShareId());
        return updateRepository.updateFileShare(fileShare);
    }

    public void deleteFileShare(String shareId){
        // 获取uid
        String uid = UserContextHolder.get().getUid();
        if (uid == null){
            logger.error("不能在UserContext中解析出 uid");
            return;
        }

        // 删除缓存
        fileShareRedisRepository.deleteFileShare(shareId);
        // 删除数据库信息
        fileShareRepository.deleteByUidAndShareId(uid, shareId);
    }

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

    /**
     * 生成四位字母数字混合字符串
     * @return
     */
    private String createPass(){
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<4; i++){
            int key = random.nextInt(36);
            if(key < 10){       // 产生数字
                builder.append(key);
            }else{              // 产生小写字母
                builder.append((char)(key+87));
            }
        }
        return builder.toString();
    }
}
