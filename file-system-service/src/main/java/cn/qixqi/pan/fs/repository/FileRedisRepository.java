package cn.qixqi.pan.fs.repository;

import cn.qixqi.pan.fs.model.File;

public interface FileRedisRepository {
    void saveFile(File file);
    void deleteFile(String fileId);
    void updateFile(File file);
    File findFile(String fileId);
}
