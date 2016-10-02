package org.ibankapp.base.persistence.test;

import org.easymock.EasyMockRule;
import org.easymock.IAnswer;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.persistence.DefaultJpaDaoImpl;
import org.ibankapp.base.persistence.Model;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import javax.persistence.*;
import java.util.*;

public class DefaultJpaDaoImplTest {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private EntityManager entityManager;

    @TestSubject
    private final DefaultJpaDaoImpl jpaDao = new DefaultJpaDaoImpl();

    @Test
    public void testPersist() {
        Model model = new Model();
        jpaDao.persist(model);
        replay();
    }

    @Before
    public void mockEntityManager() {
        expect(entityManager.getTransaction()).andReturn(new EntityTransaction() {
            @Override
            public void begin() {

            }

            @Override
            public void commit() {

            }

            @Override
            public void rollback() {

            }

            @Override
            public void setRollbackOnly() {

            }

            @Override
            public boolean getRollbackOnly() {
                return false;
            }

            @Override
            public boolean isActive() {
                return false;
            }
        });

        expect(entityManager.createQuery(anyString())).andReturn(new Query() {
            @Override
            public List getResultList() {
                return null;
            }

            @Override
            public Object getSingleResult() {
                return null;
            }

            @Override
            public int executeUpdate() {
                return 0;
            }

            @Override
            public Query setMaxResults(int maxResult) {
                return null;
            }

            @Override
            public int getMaxResults() {
                return 0;
            }

            @Override
            public Query setFirstResult(int startPosition) {
                return null;
            }

            @Override
            public int getFirstResult() {
                return 0;
            }

            @Override
            public Query setHint(String hintName, Object value) {
                return null;
            }

            @Override
            public Map<String, Object> getHints() {
                return null;
            }

            @Override
            public <T> Query setParameter(Parameter<T> param, T value) {
                return null;
            }

            @Override
            public Query setParameter(Parameter<Calendar> param, Calendar value, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(Parameter<Date> param, Date value, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(String name, Object value) {
                return null;
            }

            @Override
            public Query setParameter(String name, Calendar value, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(String name, Date value, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(int position, Object value) {
                return null;
            }

            @Override
            public Query setParameter(int position, Calendar value, TemporalType temporalType) {
                return null;
            }

            @Override
            public Query setParameter(int position, Date value, TemporalType temporalType) {
                return null;
            }

            @Override
            public Set<Parameter<?>> getParameters() {
                return null;
            }

            @Override
            public Parameter<?> getParameter(String name) {
                return null;
            }

            @Override
            public <T> Parameter<T> getParameter(String name, Class<T> type) {
                return null;
            }

            @Override
            public Parameter<?> getParameter(int position) {
                return null;
            }

            @Override
            public <T> Parameter<T> getParameter(int position, Class<T> type) {
                return null;
            }

            @Override
            public boolean isBound(Parameter<?> param) {
                return false;
            }

            @Override
            public <T> T getParameterValue(Parameter<T> param) {
                return null;
            }

            @Override
            public Object getParameterValue(String name) {
                return null;
            }

            @Override
            public Object getParameterValue(int position) {
                return null;
            }

            @Override
            public Query setFlushMode(FlushModeType flushMode) {
                return null;
            }

            @Override
            public FlushModeType getFlushMode() {
                return null;
            }

            @Override
            public Query setLockMode(LockModeType lockMode) {
                return null;
            }

            @Override
            public LockModeType getLockMode() {
                return null;
            }

            @Override
            public <T> T unwrap(Class<T> cls) {
                return null;
            }
        });


    }

    @Test
    public void testBeginTrans() {
        replay(entityManager);
        jpaDao.beginTrans();
    }

    @Test
    public void testCommitTrans() {
        replay(entityManager);
        jpaDao.commitTrans();
    }

    @Test
    public void testCreateQuery() {
        replay(entityManager);
        Query query = jpaDao.createQuery("select * from user");
        assertNotNull(query);
    }

    @Test
    public void testCreateQueryWithNullJpql() {
        replay(entityManager);

        thrown.expect(BaseException.class);
        thrown.expectMessage("jpql语句不能为空");
        jpaDao.createQuery(null);
    }

    @Test
    public void testCreateQueryWithEmptyJpql() {
        replay(entityManager);

        thrown.expect(BaseException.class);
        thrown.expectMessage("jpql语句不能为空");
        jpaDao.createQuery(" ");
    }

    @Test
    public void testRollbackTrans() {
        replay(entityManager);
        jpaDao.rollbackTrans();
    }

    @Test
    public void testGetEntityManager() {
        EntityManager manager = jpaDao.getEntityManager();
        assertSame(entityManager, manager);
    }

    @Test
    public void testSetEntityManager() {
        jpaDao.setEntityManager(entityManager);
    }
}
