package cn.qixqi.pan.filesharing.repository;

import cn.qixqi.pan.filesharing.model.FileShare;

public interface FileShareRedisRepository {
    void saveFileShare(FileShare fileShare);
    void deleteFileShare(String shareId);
    FileShare getFileShare(String shareId);
}
