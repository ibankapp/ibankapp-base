/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.validation.validator;

import org.apache.commons.beanutils.PropertyUtils;
import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.persistence.BasePersistenceException;
import org.ibankapp.base.persistence.domain.EntityInformation;
import org.ibankapp.base.persistence.validation.constraint.Unique;
import org.ibankapp.base.persistence.validation.constraint.Uniques;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UniqueValidator {

    public UniqueValidator() {

    }

    @SuppressWarnings("unchecked")
    public static <T> void validate(T bean, EntityManager em) {

        EntityInformation ei = new EntityInformation(bean.getClass(), em.getMetamodel());

        Iterable<String> idAttributeNames = ei.getIdAttributeNames();

        List<Unique> uniqueList = new ArrayList<Unique>();

        if (bean.getClass().isAnnotationPresent(Uniques.class)) {
            Uniques uniques = bean.getClass().getAnnotation(Uniques.class);
            Collections.addAll(uniqueList, uniques.constraints());
        }

        if (bean.getClass().isAnnotationPresent(Unique.class)) {
            uniqueList.add(bean.getClass().getAnnotation(Unique.class));
        }

        for (Unique unique : uniqueList) {


            String[] properties = unique.properties();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<?> c = cb.createQuery(bean.getClass());

            Root<?> root = c.from(bean.getClass());

            Predicate condition = cb.conjunction();

            try {

                for (String idAttributeName : idAttributeNames) {
                    condition = cb.and(condition, cb.notEqual(root.get(idAttributeName), PropertyUtils.getProperty
                            (bean, idAttributeName)));
                }

                for (String property : properties) {
                    condition = cb.and(condition, cb.equal(root.get(property), PropertyUtils.getProperty(bean, property)));
                }
            } catch (Exception e) {
                throw new BaseException("E-BASE-000001", e.getMessage()).initCause(e);
            }

            c.where(condition);

            int count = em.createQuery(c).getResultList().size();

            if (count != 0) {
                throw new BasePersistenceException("E-BASE-PERSISTENCE-000001", unique.message());
            }
        }
    }
}