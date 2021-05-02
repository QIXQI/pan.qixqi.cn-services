package cn.qixqi.pan.fs.repository;

import cn.qixqi.pan.fs.model.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends MongoRepository<File, String> {
    public File findByFileId(String fileId);
    public List<File> findAll();
}
