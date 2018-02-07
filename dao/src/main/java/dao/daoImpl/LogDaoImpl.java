package dao.daoImpl;

import dao.common.BaseDaoImpl;
import dao.interfaceDao.LogDao;
import entity.Log;
import org.springframework.stereotype.Repository;

/**
 * Here you can write the implementation from {@link LogDao } and methods that are not enough for working with database.
 */
@Repository
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao {
}
