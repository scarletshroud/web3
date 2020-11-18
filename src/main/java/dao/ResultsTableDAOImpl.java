package dao;

import model.Result;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ResultsTableDAOImpl implements ResultsTableDAO {
    @Override
    public List<Result> getAll() {
        List<Result> results = (List<Result>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Result").list();
        return results;
    }

    @Override
    public void save(Result result) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(result);
        transaction.commit();
        session.close();
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
