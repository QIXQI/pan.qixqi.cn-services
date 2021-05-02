package cn.qixqi.pan.fs;

import cn.qixqi.pan.fs.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;


@SpringBootApplication
public class FileSystemApplication {

    @Autowired
    private ServiceConfig config;

    /**
     * 初始化 Mongodb 数据库
     * @return
     */
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator(){
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        if (config.isDbMongoRebuild()){
            // 重新生成数据库
            Resource initData = new ClassPathResource(config.getDbMongoInitFile());
            factory.setResources(new Resource[]{initData});
        } else {
            factory.setResources(new Resource[]{});
        }
        return factory;
    }



    public static void main(String[] args){
        SpringApplication.run(FileSystemApplication.class, args);
    }
}
