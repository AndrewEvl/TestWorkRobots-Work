package dao.daoImpl;

import dao.common.BaseDaoImpl;
import dao.interfaceDao.LogDao;
import entity.Log;
import org.springframework.stereotype.Repository;

/**
 * Here you can write the implementation on{@link LogDao } of methods that are missing to work with database.
 */
@Repository
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao {
}
