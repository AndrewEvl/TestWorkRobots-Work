package service.serviceImpl;

import dao.interfaceDao.WorkDao;
import entity.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.serviceInteface.WorkService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WorkServiceImpl implements WorkService {

    private final WorkDao workDao;

    @Autowired
    public WorkServiceImpl(WorkDao workDao) {
        this.workDao = workDao;
    }

    @Override
    public void save(Work work) {
        workDao.save(work);
    }

    @Override
    public List<Work> getAll() {
        return workDao.findAll();
    }

    @Override
    public Work findById(Long id) {
        return workDao.findById(id);
    }

    @Override
    public void update(Work work) {
        workDao.update(work);
    }

    @Override
    public void delete(Work work) {
        workDao.delete(work);
    }

    @Override
    public Work minId() {
        return workDao.minId();
    }
}
