package dao;

import dao.interfaceDao.WorkDao;
import entity.Work;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class WorkTest extends BaseTest {

    @Autowired
    private WorkDao workDao;

    @Test
    public void findByIdAndSaveTest() {
        Work work = new Work();
        work.setWorks("Test");
        work.setSizeWork(5);
        workDao.save(work);
        Work byId = workDao.findById(work.getId());
        assertEquals(byId.getWorks(), "Test");
    }

    @Test
    public void deleteTest() {
        Work work = new Work();
        work.setWorks("Test");
        work.setSizeWork(5);
        workDao.save(work);
        Work byId = workDao.findById(work.getId());
        workDao.delete(byId);
        Work id = workDao.findById(byId.getId());
        assertNull(id);
    }

    @Test
    public void updateTest(){
        Work work = new Work();
        work.setWorks("Test");
        work.setSizeWork(5);
        workDao.save(work);
        Work byId = workDao.findById(work.getId());
        assertEquals(byId.getWorks(),"Test");
        work.setWorks("Update test");
        workDao.update(work);
        Work id = workDao.findById(work.getId());
        assertEquals(id.getWorks(), "Update test");
    }

    @Test
    public void findAllTest (){
        Work workFirst = new Work();
        Work workSecond = new Work();
        workFirst.setWorks("First work");
        workFirst.setSizeWork(5);
        workDao.save(workFirst);
        workSecond.setWorks("Second work");
        workSecond.setSizeWork(4);
        workDao.save(workSecond);
        List<Work> all = workDao.findAll();
        assertNotNull(all);
    }
}
