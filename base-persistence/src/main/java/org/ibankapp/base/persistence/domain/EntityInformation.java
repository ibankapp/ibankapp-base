/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

public class EntityInformation<T> {

    private final IdMetadata<T> idMetadata;
    private Metamodel metamodel;
    private String entityName;
    private ManagedType<T> type;

    public EntityInformation(Class<T> domainClass, Metamodel metamodel) {

        ManagedType<T> type = metamodel.managedType(domainClass);

        this.entityName = ((EntityType<?>) type).getName();

        IdentifiableType<T> identifiableType = (IdentifiableType<T>) type;

        this.idMetadata = new IdMetadata<>(identifiableType);

        this.type = type;
        this.metamodel = metamodel;
    }

    public String getEntityName() {
        return entityName;
    }

    public ManagedType<T> getType() {
        return type;
    }

    public Metamodel getMetamodel() {
        return metamodel;
    }

    public Iterable<String> getIdAttributeNames() {

        List<String> attributeNames = new ArrayList<>(idMetadata.attributes.size());

        attributeNames.addAll(idMetadata.attributes.stream().map(Attribute::getName).collect(Collectors.toList()));

        return attributeNames;
    }

    public IdMetadata<T> getIdMetadata() {
        return this.idMetadata;
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
