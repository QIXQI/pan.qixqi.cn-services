package cn.qixqi.pan.fs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {
    @Value("${example.property}")
    private String exampleProperty;

    @Value("${db.mongodb.rebuild}")
    private boolean dbMongoRebuild;

    @Value("${db.mongodb.init.file}")
    private String dbMongoInitFile;

    @Value("${db.mongodb.init.folder_link}")
    private String dbMongoInitFolderLink;

    public String getExampleProperty(){
        return this.exampleProperty;
    }

    public boolean isDbMongoRebuild(){
        return this.dbMongoRebuild;
    }

    public String getDbMongoInitFile(){
        return this.dbMongoInitFile;
    }

    public String getDbMongoInitFolderLink(){
        return this.dbMongoInitFolderLink;
    }
}
