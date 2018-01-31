package dao.daoImpl;

import dao.common.BaseDaoImpl;
import dao.interfaceDao.WorkDao;
import entity.Work;
import org.springframework.stereotype.Repository;

@Repository
public class WorkDaoImpl extends BaseDaoImpl<Work> implements WorkDao {
}
