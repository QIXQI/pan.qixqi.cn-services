package cn.qixqi.pan.fs.repository;

import cn.qixqi.pan.fs.model.FolderLink;

import java.util.List;

public interface FolderLinkRepository{
    FolderLink findByFolderId(String uid, String folderId);
    List<FolderLink> findByUid(String uid);
    List<FolderLink> findAll();         // 管理员或测试使用
    FolderLink save(FolderLink folderLink);
    void update(FolderLink folderLink);
    void deleteByFolderId(String uid, String folderId);
}
