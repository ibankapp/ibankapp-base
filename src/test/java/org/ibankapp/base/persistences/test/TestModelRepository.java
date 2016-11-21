/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistences.test;

import org.ibankapp.base.persistences.test.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestModelRepository extends JpaRepository<TestModel,String> {

}
