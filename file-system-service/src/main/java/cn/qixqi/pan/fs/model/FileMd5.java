package cn.qixqi.pan.fs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fileMd5")
public class FileMd5 {

    @Id
    private String md5;
    private String fileId;

    public FileMd5(){
        super();
    }

    public FileMd5(String md5, String fileId) {
        this.md5 = md5;
        this.fileId = fileId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "FileMd5{" +
                "md5='" + md5 + '\'' +
                ", fileId='" + fileId + '\'' +
                '}';
    }
}
