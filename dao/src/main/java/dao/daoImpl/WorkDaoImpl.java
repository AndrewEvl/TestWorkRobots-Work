package dao.daoImpl;

import dao.common.BaseDaoImpl;
import dao.interfaceDao.WorkDao;
import entity.Work;
import org.springframework.stereotype.Repository;

/**
 * Here you can write the implementation from {@link WorkDao } and methods that are not enough for working with database.
 */
@Repository
public class WorkDaoImpl extends BaseDaoImpl<Work> implements WorkDao {
}
