package dao.interfaceDao;

import dao.common.BaseDao;
import entity.Log;

/**
 * Interface for the ability to implement a standard set commands to work with the database.
 * Here you can write your lacking methods for later implementation on {@link dao.daoImpl.LogDaoImpl}.
 */
public interface LogDao extends BaseDao<Log> {
}
