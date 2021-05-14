package cn.qixqi.pan.filesharing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

import java.util.Date;

@Document(collection = "fileShare")
@Sharded(shardingStrategy = ShardingStrategy.HASH, shardKey = {"uid"})
public class FileShare {

    @Id
    private String shareId;
    private String sharePass;
    @Field("uid")
    private String uid;
    private String rootId;
    private Date createTime;
    private Integer activeTime;

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getSharePass() {
        return sharePass;
    }

    public void setSharePass(String sharePass) {
        this.sharePass = sharePass;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Integer activeTime) {
        this.activeTime = activeTime;
    }
}
