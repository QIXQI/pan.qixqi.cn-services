package cn.qixqi.pan.user.repository;

import cn.qixqi.pan.user.model.TestTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<TestTable, String> {
    public TestTable findByUid(String uid);
    public List<TestTable> findAll();
}
