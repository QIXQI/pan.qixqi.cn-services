package cn.qixqi.pan.fs.service;

import cn.qixqi.pan.fs.event.source.SimpleSourceBean;
import cn.qixqi.pan.fs.model.File;
import cn.qixqi.pan.fs.repository.FileRedisRepository;
import cn.qixqi.pan.fs.repository.FileRepository;
import cn.qixqi.pan.fs.test.HystrixTest;
import cn.qixqi.pan.fs.util.UserContextHolder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileRedisRepository fileRedisRepository;

    @Autowired
    private SimpleSourceBean simpleSourceBean;

    public File getFileById(String fileId){
        // 首先在缓存中查找
        File file = checkRedisCache(fileId);
        if (file != null){
            logger.debug("在Redis缓存中获取到文件：{}", file.toString());
            return file;
        }
        logger.debug("在Redis缓存中不能获取文件{}", fileId);
        // 缓存中没有时，调用数据库查找
        file = fileRepository.findByFileId(fileId);
        // 存入缓存
        if (file != null){
            cacheFile(file);
        }
        return file;
    }

    /**
     * 数据库调用，断路器模式
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "buildFallbackFileList",   // 后备策略
            threadPoolKey = "getFilesThreadPool",       // 线程池名称
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),  // 线程池中线程最大数量
                    @HystrixProperty(name = "maxQueueSize", value = "10")   // 队列大小
            },
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 给定时间内，最少失败次数，默认20
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"), // 调用出错百分比阈值，默认50%
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"), // 断路器跳闸后，休眠时间，7秒后尝试恢复，默认5秒
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"), // 滚动窗口的时间，默认10秒
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5"),    // 滚动窗口划分的桶数，即收集统计信息的次数，默认10
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")}) // 设置最大超时时间
    public List<File> getFiles(){
        // simpleSourceBean.publishMsg();
        logger.debug("FileService trace_id: " + UserContextHolder.get().getTraceId());

        /** 测试使用 */
        HystrixTest hystrixTest = new HystrixTest();
        hystrixTest.randomlyRunLong();

        return fileRepository.findAll();
    }

    public List<File> buildFallbackFileList(){
        List<File> fallbackFileList = new ArrayList<>();
        File file = new File();
        file.setFileId("0000000-00-0000");
        file.setFileName("对不起，获取失败");
        fallbackFileList.add(file);
        return fallbackFileList;
    };

    public File addFile(File file){
        return fileRepository.save(file);
    }

    public File updateFile(File file){
        // 删除缓存对象
        fileRedisRepository.deleteFile(file.getFileId());
        return fileRepository.save(file);
    }

    public String deleteFile(String fileId){
        // 删除缓存对象
        fileRedisRepository.deleteFile(fileId);
        // 删除数据库记录
        fileRepository.deleteById(fileId);
        logger.debug("删除文件记录：{}", fileId);
        return String.format("删除文件记录：%s", fileId);
    }

    private File checkRedisCache(String fileId){
        try{
            return fileRedisRepository.findFile(fileId);
        } catch (Exception ex){
            logger.error("在Redis缓存中获取文件{}发生异常，异常信息：{}", fileId, ex);
            return null;
        }
    }

    private void cacheFile(File file){
        try{
           fileRedisRepository.saveFile(file);
        } catch (Exception ex){
            logger.error("在Redis中缓存文件{}发生异常，异常信息：{}", file.getFileId(), ex);
        }
    }
}
