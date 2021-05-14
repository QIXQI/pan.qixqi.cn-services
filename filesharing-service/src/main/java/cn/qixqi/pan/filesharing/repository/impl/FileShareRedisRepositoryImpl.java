package cn.qixqi.pan.filesharing.repository.impl;

import cn.qixqi.pan.filesharing.model.FileShare;
import cn.qixqi.pan.filesharing.repository.FileShareRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class FileShareRedisRepositoryImpl implements FileShareRedisRepository {

    private static final String HASH_NAME = "fileShare";
    private RedisTemplate<String, FileShare> redisTemplate;
    private HashOperations hashOperations;

    public FileShareRedisRepositoryImpl() {
    }

    @Autowired
    private FileShareRedisRepositoryImpl(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public void saveFileShare(FileShare fileShare) {
        hashOperations.put(HASH_NAME, fileShare.getShareId(), fileShare);
    }

    @Override
    public void deleteFileShare(String shareId) {
        hashOperations.delete(HASH_NAME, shareId);
    }

    @Override
    public FileShare getFileShare(String shareId) {
        return (FileShare) hashOperations.get(HASH_NAME, shareId);
    }
}
