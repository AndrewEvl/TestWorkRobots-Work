package dao.daoImpl;

import dao.common.BaseDaoImpl;
import dao.interfaceDao.RobotDao;
import entity.Robot;
import org.springframework.stereotype.Repository;

/**
 * Here you can write the implementation on{@link RobotDao } of methods that are missing to work with database.
 */
@Repository
public class RobotDaoImpl extends BaseDaoImpl<Robot> implements RobotDao {
}
