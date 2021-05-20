package cn.qixqi.pan.fs.repository;

import cn.qixqi.pan.fs.model.FileMd5;

public interface FileMd5RedisRepository {
    void saveFileMd5(FileMd5 fileMd5);
    void deleteFileMd5(String md5);
    FileMd5 findFileMd5(String md5);
}
