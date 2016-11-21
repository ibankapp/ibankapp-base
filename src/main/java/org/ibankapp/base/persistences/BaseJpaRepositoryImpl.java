/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistences;

import org.ibankapp.base.models.Model;
import org.ibankapp.base.utils.StringUtil;
import org.ibankapp.base.validations.validators.BeanValidator;
import org.ibankapp.base.validations.validators.UniqueValidator;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class BaseJpaRepositoryImpl<T extends Model, ID extends String> extends SimpleJpaRepository<T, ID>
        implements BaseJpaRepository<T, ID> {

    private EntityManager entityManager;

    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        if (StringUtil.isEmpty(entity.getId())) {
            entity.setId(entity.generateId());
        }
        BeanValidator.validate(entity);
        UniqueValidator.validate(entity, entityManager);
        return super.save(entity);
    }
}
