package dao.daoImpl;

import com.querydsl.jpa.impl.JPAQuery;
import dao.common.BaseDaoImpl;
import dao.interfaceDao.WorkDao;
import entity.QWork;
import entity.Work;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class WorkDaoImpl extends BaseDaoImpl<Work> implements WorkDao {
    @Override
    public Work minId() {
        Session session = getSessionFactory().getCurrentSession();
        QWork qWork = new QWork("myWork");
        JPAQuery<Work> query = new JPAQuery<>(session);
        return query.fetchOne();
    }
}
