package cn.qixqi.pan.fs.repository.impl;

import cn.qixqi.pan.fs.model.File;
import cn.qixqi.pan.fs.repository.FileRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class FileRedisRepositoryImpl implements FileRedisRepository {
    private static final String HASH_NAME = "file";
    private RedisTemplate<String, File> redisTemplate;
    private HashOperations hashOperations;

    public FileRedisRepositoryImpl(){
        super();
    }

    @Autowired
    private FileRedisRepositoryImpl(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public void saveFile(File file){
        hashOperations.put(HASH_NAME, file.getFileId(), file);
    }

    @Override
    public void deleteFile(String fileId){
        hashOperations.delete(HASH_NAME, fileId);
    }

    @Override
    public File findFile(String fileId){
        return (File) hashOperations.get(HASH_NAME, fileId);
    }
}
