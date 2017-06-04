/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.domain;

import javax.persistence.metamodel.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 实体信息类，通过此类的方法可获得实体类的一些静态信息
 *
 * @param <T> 实体类
 */
public class EntityInformation<T> {

    private final IdMetadata<T> idMetadata;
    private String entityName;

    /**
     * 构造函数
     *
     * @param domainClass 实体类CLASS
     * @param metamodel   模型元数据,可从jpa的实体管理器EntityManage获取
     */
    public EntityInformation(Class<T> domainClass, Metamodel metamodel) {

        ManagedType<T> type = metamodel.managedType(domainClass);

        this.entityName = ((EntityType<?>) type).getName();

        IdentifiableType<T> identifiableType = (IdentifiableType<T>) type;

        this.idMetadata = new IdMetadata<T>(identifiableType);
    }

    /**
     * 获取实体类的名称
     *
     * @return 实体类名称
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * 获取实体类的id属性列表
     *
     * @return id属性列表
     */
    public Iterable<String> getIdAttributeNames() {

        List<String> attributeNames = new ArrayList<String>(idMetadata.attributes.size());

        for (SingularAttribute attribute : idMetadata.attributes) {
            attributeNames.add(attribute.getName());
        }
//        attributeNames.addAll(idMetadata.attributes.stream().map(Attribute::getName).collect(Collectors.toList()));

        return attributeNames;
    }

    private static class IdMetadata<T> {

        private final Set<SingularAttribute<? super T, ?>> attributes;


        @SuppressWarnings("unchecked")
        IdMetadata(IdentifiableType<T> source) {

            this.attributes = (Set<SingularAttribute<? super T, ?>>) (source.hasSingleIdAttribute()
                    ? Collections.singleton(source.getId(source.getIdType().getJavaType()))
                    : source.getIdClassAttributes());
        }
    }
}
