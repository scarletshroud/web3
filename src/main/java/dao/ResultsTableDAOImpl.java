package dao;

import hmm.Result;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ResultsTableDAOImpl implements ResultsTableDAO {
    @Override
    public List<Result> getAll() {
        return (List<Result>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from hmm.Result").list();
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
    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Result r").executeUpdate();
        transaction.commit();
        session.close();
    }
}
