package cn.qixqi.pan.filesharing.repository.impl;

import cn.qixqi.pan.filesharing.model.FileShareLink;
import cn.qixqi.pan.filesharing.repository.FileShareLinkRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class FileShareLinkRedisRepositoryImpl implements FileShareLinkRedisRepository {

    private static final String HASH_NAME = "fileShareLink";
    private RedisTemplate<String, FileShareLink> redisTemplate;
    private HashOperations hashOperations;

    public FileShareLinkRedisRepositoryImpl(){

    }

    @Autowired
    private FileShareLinkRedisRepositoryImpl(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public void saveFileShareLink(FileShareLink fileShareLink) {
        hashOperations.put(HASH_NAME, fileShareLink.getFolderId(), fileShareLink);
    }

    @Override
    public void deleteFileShareLink(String folderId) {
        hashOperations.delete(HASH_NAME, folderId);
    }

    @Override
    public FileShareLink getFileShareLink(String folderId) {
        return (FileShareLink) hashOperations.get(HASH_NAME, folderId);
    }
}
