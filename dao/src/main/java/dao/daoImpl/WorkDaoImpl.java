package dao.daoImpl;

import dao.common.BaseDaoImpl;
import dao.interfaceDao.WorkDao;
import entity.Work;
import org.springframework.stereotype.Repository;

/**
 * Here you can write the implementation on{@link WorkDao } of methods that are missing to work with database.
 */
@Repository
public class WorkDaoImpl extends BaseDaoImpl<Work> implements WorkDao {
}
