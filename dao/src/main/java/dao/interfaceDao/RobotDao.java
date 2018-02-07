package dao.interfaceDao;

import dao.common.BaseDao;
import entity.Robot;

/**
 * Interface for ability to implement a standard set of commands to work with database.
 * Here you can write your absent methods for later implementation on {@link dao.daoImpl.RobotDaoImpl}.
 */
public interface RobotDao extends BaseDao<Robot> {
}
