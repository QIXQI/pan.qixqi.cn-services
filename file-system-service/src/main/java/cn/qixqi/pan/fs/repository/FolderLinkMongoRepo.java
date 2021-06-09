package cn.qixqi.pan.fs.repository;

import cn.qixqi.pan.fs.model.FolderLink;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderLinkMongoRepo extends MongoRepository<FolderLink, String> {
}
