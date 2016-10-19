/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.validators;

import org.apache.commons.beanutils.BeanUtils;
import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.validation.constraints.Unique;
import org.ibankapp.base.validation.constraints.Uniques;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueValidator {

    public UniqueValidator(){

    }

    public static <T> void validate(T bean, EntityManager em) {

        List<Unique> uniqueList = new ArrayList<>();

        if(bean.getClass().isAnnotationPresent(Uniques.class)){
            Uniques uniques = bean.getClass().getAnnotation(Uniques.class);
            Collections.addAll(uniqueList, uniques.constraints());
        }

        if (bean.getClass().isAnnotationPresent(Unique.class)) {
            uniqueList.add(bean.getClass().getAnnotation(Unique.class));
        }

        for(Unique unique:uniqueList){

            String[] properties = unique.properties();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery c = cb.createQuery(bean.getClass());

            @SuppressWarnings("unchecked")
            Root root = c.from(bean.getClass());

            Predicate condition = cb.conjunction();

            for (String property : properties) {
                try {
                    condition = cb.and(condition, cb.equal(root.get(property), BeanUtils.getProperty(bean, property)));
                } catch (Exception e) {
                    throw new BaseException("E-BASE-000001", e.getMessage()).initCause(e);
                }
            }

            c.where(condition);

            @SuppressWarnings("unchecked")
            int count = em.createQuery(c).getResultList().size();

            if (count != 0) {
                throw new BaseException("E-BASE-000008", unique.message());
            }
        }
    }
}