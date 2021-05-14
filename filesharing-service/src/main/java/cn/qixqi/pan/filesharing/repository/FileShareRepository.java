package cn.qixqi.pan.filesharing.repository;


import cn.qixqi.pan.filesharing.model.FileShare;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileShareRepository extends MongoRepository<FileShare, String>{

    FileShare findByUidAndShareId(String uid, String shareId);
    FileShare findByUidAndShareIdAndSharePass(String uid, String shareId, String sharePass);
    List<FileShare> findByUid(String uid);
    List<FileShare> findAll();          // 管理员或测试
    int deleteByUidAndShareId(String uid, String shareId);
}
