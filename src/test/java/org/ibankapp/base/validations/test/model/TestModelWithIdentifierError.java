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

package org.ibankapp.base.validations.test.model;

import org.ibankapp.base.types.Idtp;
import org.ibankapp.base.validations.constraints.Identifier;

@Identifier(typefield = "idtp",codefield = "idno1")
public class TestModelWithIdentifierError {
    private Idtp idtp;

    private String idno;

    public Idtp getIdtp() {
        return idtp;
    }

    public void setIdtp(Idtp idtp) {
        this.idtp = idtp;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }
}
