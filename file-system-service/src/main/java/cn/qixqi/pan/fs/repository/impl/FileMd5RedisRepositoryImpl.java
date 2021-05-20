package cn.qixqi.pan.fs.repository.impl;

import cn.qixqi.pan.fs.model.FileMd5;
import cn.qixqi.pan.fs.repository.FileMd5RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class FileMd5RedisRepositoryImpl implements FileMd5RedisRepository {

    private static final String HASH_NAME = "file_md5";
    private RedisTemplate<String, FileMd5> redisTemplate;
    private HashOperations hashOperations;

    public FileMd5RedisRepositoryImpl(){
        super();
    }

    @Autowired
    private FileMd5RedisRepositoryImpl(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public void saveFileMd5(FileMd5 fileMd5) {
        hashOperations.put(HASH_NAME, fileMd5.getMd5(), fileMd5);
    }

    @Override
    public void deleteFileMd5(String md5) {
        hashOperations.delete(HASH_NAME, md5);
    }

    @Override
    public FileMd5 findFileMd5(String md5) {
        return (FileMd5) hashOperations.get(HASH_NAME, md5);
    }
}
