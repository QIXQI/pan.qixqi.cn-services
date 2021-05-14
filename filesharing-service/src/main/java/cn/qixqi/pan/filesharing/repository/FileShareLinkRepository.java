package cn.qixqi.pan.filesharing.repository;

import cn.qixqi.pan.filesharing.model.FileShareLink;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileShareLinkRepository extends MongoRepository<FileShareLink, String> {

    FileShareLink findByShareIdAndFolderId(String shareId, String folderId);
    List<FileShareLink> findByShareId(String shareId);
    List<FileShareLink> findAll();          // 管理员或测试
    int deleteByShareIdAndFolderId(String shareId, String folderId);
}
