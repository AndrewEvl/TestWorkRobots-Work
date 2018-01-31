package dao.daoImpl;

import dao.common.BaseDao;
import dao.common.BaseDaoImpl;
import dao.interfaceDao.RobotDao;
import entity.Robot;
import org.springframework.stereotype.Repository;

@Repository
public class RobotDaoImpl extends BaseDaoImpl<Robot> implements RobotDao {
}
