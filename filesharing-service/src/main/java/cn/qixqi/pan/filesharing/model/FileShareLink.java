package cn.qixqi.pan.filesharing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

@Document(collection = "fileShareLink")
@Sharded(shardingStrategy = ShardingStrategy.HASH, shardKey = {"shareId"})
public class FileShareLink {

    @Id
    private String folderId;
    @Field("shareId")
    private String shareId;
    private String folderName;
    private String parent;
    private FolderChildren children;

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public FolderChildren getChildren() {
        return children;
    }

    public void setChildren(FolderChildren children) {
        this.children = children;
    }
}
