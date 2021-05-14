package cn.qixqi.pan.filesharing.repository;

import cn.qixqi.pan.filesharing.model.FileShareLink;

public interface FileShareLinkRedisRepository {
    void saveFileShareLink(FileShareLink fileShareLink);
    void deleteFileShareLink(String folderId);
    FileShareLink getFileShareLink(String folderId);
}
