package cn.qixqi.pan.fs.repository;

import cn.qixqi.pan.fs.model.FolderLink;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderLinkRepository extends MongoRepository<FolderLink, String> {
    FolderLink findByFolderId(String folderId);
    List<FolderLink> findByUid(String uid);
    List<FolderLink> findAll();     // 测试或管理员使用
}
