package cn.qixqi.pan.fs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

import java.util.Date;

@Document(collection = "folderLink")
@Sharded(shardingStrategy = ShardingStrategy.HASH, shardKey = {"uid"})
public class FolderLink {

    @Id
    private String folderId;
    private String folderName;
    @Field("uid")
    private String uid;
    private String parent;
    private Date createTime;
    private FolderChildren children;

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public FolderChildren getChildren() {
        return children;
    }

    public void setChildren(FolderChildren children) {
        this.children = children;
    }
}
