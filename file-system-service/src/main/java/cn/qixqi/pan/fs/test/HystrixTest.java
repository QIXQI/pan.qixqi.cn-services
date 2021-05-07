package cn.qixqi.pan.fs.test;

import cn.qixqi.pan.fs.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class HystrixTest {
    private static final Logger logger = LoggerFactory.getLogger(HystrixTest.class);

    public void randomlyRunLong(){
        Random random = new Random();
        int randomNum = random.nextInt(3) + 1;
        logger.debug("randomNum = " + randomNum);
        if (randomNum == 3){
            sleep();
        }
    }

    private void sleep(){
        try{
            Thread.sleep(3500);
        } catch (InterruptedException e){
            logger.error(e.getMessage());
        }
    }
}
