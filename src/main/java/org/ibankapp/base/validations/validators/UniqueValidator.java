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

package org.ibankapp.base.validations.validators;

import org.apache.commons.beanutils.PropertyUtils;
import org.ibankapp.base.exceptions.BaseException;
import org.ibankapp.base.models.Model;
import org.ibankapp.base.validations.constraints.Unique;
import org.ibankapp.base.validations.constraints.Uniques;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueValidator {

    public UniqueValidator(){

    }

    public static <T extends Model> void validate(T bean, EntityManager em) {

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
            CriteriaQuery<?> c = cb.createQuery(bean.getClass());

            Root<?> root = c.from(bean.getClass());

            Predicate condition = cb.conjunction();

            for (String property : properties) {
                try {
                    condition = cb.and(condition, cb.equal(root.get(property), PropertyUtils.getProperty(bean, property)));
                } catch (Exception e) {
                    throw new BaseException("E-BASE-000001", e.getMessage()).initCause(e);
                }
            }

            c.where(condition);

            int count = em.createQuery(c).getResultList().size();

            if (count != 0) {
                throw new BaseException("E-BASE-000008", unique.message());
            }
        }
    }
}