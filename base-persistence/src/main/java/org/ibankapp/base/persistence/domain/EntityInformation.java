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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 实体信息类，通过此类的方法可获得实体类的一些静态信息
 *
 * @param <T> 实体类
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class EntityInformation<T> {

  private final IdMetadata<T> idMetadata;
  private final String entityName;

  /**
   * 构造函数.
   *
   * @param domainClass 实体类CLASS
   * @param metamodel   模型元数据,可从jpa的实体管理器EntityManage获取
   */
  public EntityInformation(Class<T> domainClass, Metamodel metamodel) {

    ManagedType<T> type = metamodel.managedType(domainClass);

    this.entityName = ((EntityType<?>) type).getName();

    IdentifiableType<T> identifiableType = (IdentifiableType<T>) type;

    this.idMetadata = new IdMetadata<>(identifiableType);
  }

  /**
   * 获取实体类的名称.
   *
   * @return 实体类名称
   */
  public String getEntityName() {
    return entityName;
  }

  /**
   * 获取实体类的id属性列表.
   *
   * @return id属性列表
   */
  public Iterable<String> getIdAttributeNames() {
    return idMetadata.attributes.stream().map(Attribute::getName).collect(Collectors.toCollection(() -> new ArrayList<>(idMetadata.attributes.size())));
  }

  /**
   * Id元数据内部类.
   */
  private static class IdMetadata<T> {

    /**
     * id所包含的属性字段.
     */
    private final Set<SingularAttribute<? super T, ?>> attributes;


    /**
     * 构造函数.
     *
     * @param source id类型
     */
    IdMetadata(IdentifiableType<T> source) {
      this.attributes = source.hasSingleIdAttribute() ? Collections.singleton(source.getId(source.getIdType().getJavaType())) : source.getIdClassAttributes();
    }
  }
}
