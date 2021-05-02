package cn.qixqi.pan.user.service;


import cn.qixqi.pan.user.model.TestTable;
import cn.qixqi.pan.user.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public TestTable getTest(String uid){
        TestTable testTable = testRepository.findByUid(uid);
        return testTable;
    }

    public List<TestTable> gets(){
        return testRepository.findAll();
    }

    public void saveTest(TestTable testTable){
        testRepository.save(testTable);
    }

    public void updateTest(TestTable testTable){
        testRepository.save(testTable);
    }

    public void deleteTest(TestTable testTable){
        testRepository.deleteById(testTable.getUid());
    }















}
