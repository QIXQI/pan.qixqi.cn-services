package cn.qixqi.pan.fs.service;

import cn.qixqi.pan.fs.model.FileMd5;
import cn.qixqi.pan.fs.repository.FileMd5RedisRepository;
import cn.qixqi.pan.fs.repository.FileMd5Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileMd5Service {

    private static final Logger logger = LoggerFactory.getLogger(FileMd5Service.class);

    @Autowired
    private FileMd5Repository repository;

    @Autowired
    private FileMd5RedisRepository redisRepository;

    private FileMd5 checkRedisCache(String md5){
        try {
            return redisRepository.findFileMd5(md5);
        } catch (Exception e){
            logger.error("在Redis缓存中获取file_md5 {}发生异常，异常信息：{}", md5, e);
            return null;
        }
    }

    private void cacheFileMd5(FileMd5 fileMd5){
        try {
            redisRepository.saveFileMd5(fileMd5);
        } catch (Exception ex){
            logger.error("在Redis中缓存file_md5 {}发生异常，异常信息：{}", fileMd5.getMd5(), ex);
        }
    }

    public FileMd5 getFileMd5(String md5){
        // 查找缓存
        FileMd5 fileMd5 = checkRedisCache(md5);
        if (fileMd5 != null){
            logger.debug("在Redis缓存中获取到file_md5：{}", fileMd5.toString());
            return fileMd5;
        }
        logger.debug("在Redis缓存中不能获取file_md5: {}", md5);
        // 查找数据库
        fileMd5 = repository.findByMd5(md5);
        // 存入缓存
        if (fileMd5 != null){
            cacheFileMd5(fileMd5);
        }
        return fileMd5;
    }

    /**
     * 管理员或测试使用
     * @return
     */
    public List<FileMd5> getAllFileMd5(){
        return repository.findAll();
    }

    public FileMd5 addFileMd5(FileMd5 fileMd5){
        return repository.save(fileMd5);
    }

    public FileMd5 updateFileMd5(FileMd5 fileMd5){
        // 删除缓存对象
        redisRepository.deleteFileMd5(fileMd5.getMd5());
        return repository.save(fileMd5);
    }

    public String deleteFileMd5(String md5){
        // 删除缓存对象
        redisRepository.deleteFileMd5(md5);
        // 删除数据库记录
        repository.deleteById(md5);
        logger.debug("删除file_md5记录：{}", md5);
        return String.format("删除file_md5记录：%s", md5);
    }
}
