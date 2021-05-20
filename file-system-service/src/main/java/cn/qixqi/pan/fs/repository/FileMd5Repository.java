package cn.qixqi.pan.fs.repository;

import cn.qixqi.pan.fs.model.FileMd5;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMd5Repository extends MongoRepository<FileMd5, String> {
    FileMd5 findByMd5(String md5);
    List<FileMd5> findAll();
}
