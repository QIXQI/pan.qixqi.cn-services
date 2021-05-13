package cn.qixqi.pan.fs.repository.impl;

import cn.qixqi.pan.fs.model.FolderLink;
import cn.qixqi.pan.fs.repository.FolderLinkRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class FolderLinkRedisRepositoryImpl implements FolderLinkRedisRepository {

    private static final String HASH_NAME = "folderLink";
    private RedisTemplate<String, FolderLink> redisTemplate;
    private HashOperations hashOperations;

    public FolderLinkRedisRepositoryImpl(){
        super();
    }

    @Autowired
    private FolderLinkRedisRepositoryImpl(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public void saveFolderLink(FolderLink folderLink) {
        hashOperations.put(HASH_NAME, folderLink.getFolderId(), folderLink);
    }

    @Override
    public void deleteFolderLink(String folderId) {
        hashOperations.delete(HASH_NAME, folderId);
    }

    @Override
    public FolderLink getFolderLink(String folderId) {
        return (FolderLink) hashOperations.get(HASH_NAME, folderId);
    }
}
