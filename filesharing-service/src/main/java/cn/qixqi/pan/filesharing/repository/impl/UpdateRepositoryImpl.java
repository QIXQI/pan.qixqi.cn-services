package cn.qixqi.pan.filesharing.repository.impl;

import cn.qixqi.pan.filesharing.model.FileShare;
import cn.qixqi.pan.filesharing.model.FileShareLink;
import cn.qixqi.pan.filesharing.repository.UpdateRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;



@Repository
public class UpdateRepositoryImpl implements UpdateRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long updateFileShare(FileShare fileShare) {
        Query query = new Query();
        // 片键 uid
        query.addCriteria(Criteria.where("uid").is(fileShare.getUid()));
        // 主键 shareId
        query.addCriteria(Criteria.where("_id").is(fileShare.getShareId()));

        Update update = new Update();
        if (fileShare.getSharePass() != null){
            update.set("sharePass", fileShare.getSharePass());
        }
        if (fileShare.getActiveTime() != null){
            update.set("activeTime", fileShare.getActiveTime());
        }
        UpdateResult result = mongoTemplate.updateFirst(query, update, FileShare.class);
        return result.getModifiedCount();
    }

    @Override
    public long updateFileShareLink(FileShareLink fileShareLink) {
        Query query = new Query();
        // 片键 shareId
        query.addCriteria(Criteria.where("shareId").is(fileShareLink.getShareId()));
        // 主键 folderId
        query.addCriteria(Criteria.where("_id").is(fileShareLink.getFolderId()));

        Update update = new Update();
        if (fileShareLink.getFolderName() != null){
            update.set("folderName", fileShareLink.getFolderName());
        }
        if (fileShareLink.getChildren() != null){
            update.set("children", fileShareLink.getChildren());
        }
        UpdateResult result = mongoTemplate.updateFirst(query, update, FileShareLink.class);
        return result.getModifiedCount();
    }
}
