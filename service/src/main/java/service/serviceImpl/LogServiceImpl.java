package service.serviceImpl;

import dao.interfaceDao.LogDao;
import entity.Log;
import org.springframework.stereotype.Service;
import service.serviceInteface.LogService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
/**
 * Here you can write the implementation on{@link LogService } of methods that are missing to work with database.
 */
public class LogServiceImpl implements LogService {

    private final LogDao logDao;

    public LogServiceImpl(LogDao logDao) {
        this.logDao = logDao;
    }

    @Override
    public void save(Log log) {
        log.setTime(LocalDateTime.now());
        logDao.save(log);
    }

    @Override
    public List<Log> getAll() {
        return logDao.findAll();
    }

    @Override
    public Log findById(Long id) {
        return logDao.findById(id);
    }

    @Override
    public void update(Log log) {
        logDao.update(log);
    }

    @Override
    public void delete(Log log) {
        logDao.delete(log);
    }
}
