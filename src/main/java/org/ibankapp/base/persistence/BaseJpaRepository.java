/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence;

import org.ibankapp.base.model.Model;
import org.ibankapp.base.util.StringUtil;
import org.ibankapp.base.validation.validators.BeanValidator;
import org.ibankapp.base.validation.validators.UniqueValidator;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseJpaRepository<T extends Model, ID extends Serializable> extends SimpleJpaRepository<T, ID> {

    private EntityManager entityManager;

    public BaseJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public <S extends T> S save(S entity) {
        if (StringUtil.isEmpty(entity.getId())) {
            entity.setId(entity.generateId());
        }
        BeanValidator.validate(entity);
        UniqueValidator.validate(entity, entityManager);
        return super.save(entity);
    }
}
