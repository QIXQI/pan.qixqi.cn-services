package cn.qixqi.pan.fs.repository;

import cn.qixqi.pan.fs.model.FolderLink;

public interface FolderLinkRedisRepository {
    void saveFolderLink(FolderLink folderLink);
    void deleteFolderLink(String folderId);
    FolderLink getFolderLink(String folderId);
}
