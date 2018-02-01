package dao.daoImpl;

import dao.common.BaseDao;
import dao.common.BaseDaoImpl;
import dao.interfaceDao.LogDao;
import entity.Log;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao {
}
