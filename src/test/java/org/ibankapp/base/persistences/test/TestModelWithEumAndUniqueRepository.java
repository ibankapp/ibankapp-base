/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistences.test;

import org.ibankapp.base.validations.test.model.TestModelWithEumAndUnique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestModelWithEumAndUniqueRepository extends JpaRepository<TestModelWithEumAndUnique,String>{

}