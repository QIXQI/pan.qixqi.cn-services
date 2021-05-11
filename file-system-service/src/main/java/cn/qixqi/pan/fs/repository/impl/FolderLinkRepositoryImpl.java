package cn.qixqi.pan.fs.repository.impl;

import cn.qixqi.pan.fs.model.FolderLink;
import cn.qixqi.pan.fs.repository.FolderLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FolderLinkRepositoryImpl implements FolderLinkRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public FolderLink findByFolderId(String uid, String folderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(folderId));
        query.addCriteria(Criteria.where("uid").is(uid));
        FolderLink folderLink = mongoTemplate.findOne(query, FolderLink.class);
        return folderLink;
    }

    @Override
    public List<FolderLink> findByUid(String uid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("uid").is(uid));
        List<FolderLink> folderLinkList = mongoTemplate.find(query, FolderLink.class);
        return folderLinkList;
    }

    @Override
    public List<FolderLink> findAll(){
        return mongoTemplate.findAll(FolderLink.class);
    }

    @Override
    public FolderLink save(FolderLink folderLink) {
        return mongoTemplate.save(folderLink);
    }

    @Override
    public void update(FolderLink folderLink) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(folderLink.getFolderId()));
        query.addCriteria(Criteria.where("uid").is(folderLink.getUid()));

        Update update = new Update();
        if (folderLink.getFolderName() != null){
            update.set("folderName", folderLink.getFolderName());
        }
        if (folderLink.getParent() != null){
            update.set("parent", folderLink.getParent());
        }
        if (folderLink.getChildren() != null){
            update.set("children", folderLink.getChildren());
        }
        mongoTemplate.updateFirst(query, update, FolderLink.class);
    }

    @Override
    public void deleteByFolderId(String uid, String folderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("uid").is(uid));
        query.addCriteria(Criteria.where("_id").is(folderId));
        mongoTemplate.remove(query, FolderLink.class);
    }
}
