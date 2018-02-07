package dao.daoImpl;

import dao.common.BaseDaoImpl;
import dao.interfaceDao.RobotDao;
import entity.Robot;
import org.springframework.stereotype.Repository;

/**
 * Here you can write the implementation from {@link RobotDao } and methods that are not enough for working with database.
 */
@Repository
public class RobotDaoImpl extends BaseDaoImpl<Robot> implements RobotDao {
}
