package service;

import entity.Work;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.serviceInteface.WorkService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test for {@link WorkService} class.
 */
public class WorkServiceTest extends BaseTest {

    @Autowired
    private WorkService workService;


    /**
     * Method testing save and find by ID query and checks the receives result.
     */
    @Test
    public void findByIdAndSaveTest() {
        Work work = new Work();
        work.setWorks("Test");
        work.setSizeWork(5);
        workService.save(work);
        Work byId = workService.findById(work.getId());
        assertEquals(byId.getWorks(), "Test");
    }

    /**
     * Method testing delete on database entity.
     */
    @Test
    public void deleteTest() {
        Work work = new Work();
        work.setWorks("Test");
        work.setSizeWork(5);
        workService.save(work);
        Work byId = workService.findById(work.getId());
        workService.delete(byId);
        Work id = workService.findById(byId.getId());
        assertNull(id);
    }

    /**
     * Method testing update created entity new information.
     */
    @Test
    public void updateTest(){
        Work work = new Work();
        work.setWorks("Test");
        work.setSizeWork(5);
        workService.save(work);
        Work byId = workService.findById(work.getId());
        assertEquals(byId.getWorks(),"Test");
        work.setWorks("Update test");
        workService.update(work);
        Work id = workService.findById(work.getId());
        assertEquals(id.getWorks(), "Update test");
    }

    /**
     * The method tests the receipt of all entities
     * of this type from the database.
     */
    @Test
    public void findAllTest (){
        Work workFirst = new Work();
        Work workSecond = new Work();
        workFirst.setWorks("First work");
        workFirst.setSizeWork(5);
        workService.save(workFirst);
        workSecond.setWorks("Second work");
        workSecond.setSizeWork(4);
        workService.save(workSecond);
        List<Work> all = workService.getAll();
        assertNotNull(all);
    }
}
