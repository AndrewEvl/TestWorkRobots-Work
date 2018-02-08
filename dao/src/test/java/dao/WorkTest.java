package dao;

import dao.interfaceDao.WorkDao;
import entity.Work;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test for {@link WorkDao} class.
 */
public class WorkTest extends BaseTest {

    @Autowired
    private WorkDao workDao;

    /**
     * Method tests of saving and finding by ID query and checks received result.
     */
    @Test
    public void findByIdAndSaveTest() {
        Work work = new Work();
        work.setWorks("Test");
        work.setSizeWork(5);
        workDao.save(work);
        Work byId = workDao.findById(work.getId());
        assertEquals(byId.getWorks(), "Test");
    }

    /**
     * Method tests of deleting entity form database.
     */
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

    /**
     * Method tests of updating of created entity with new information.
     */
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

    /**
     * The method tests receiving of all entities of this type from the database.
     */
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
