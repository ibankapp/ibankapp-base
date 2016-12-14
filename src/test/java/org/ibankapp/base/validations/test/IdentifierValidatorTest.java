/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validations.test;

import org.ibankapp.base.exceptions.BaseException;
import org.ibankapp.base.types.Idtp;
import org.ibankapp.base.validations.test.model.TestModelWithIdentifier;
import org.ibankapp.base.validations.test.model.TestModelWithIdentifierError;
import org.ibankapp.base.validations.validators.BeanValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.validation.ValidationException;

public class IdentifierValidatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testIDCARD() {
        TestModelWithIdentifier  model = new TestModelWithIdentifier();
        Idtp idtp = Idtp.IDCARD;
        model.setIdtp(idtp);
        model.setIdno("130404197602293014");
        BeanValidator.validate(model);
    }

    @Test
    public void testIDCARDInvalid() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("证件号码不合法");

        TestModelWithIdentifier  model = new TestModelWithIdentifier();
        model.setIdtp(Idtp.IDCARD);
        model.setIdno("130404197602293015");
        BeanValidator.validate(model);
    }

    @Test
    public void testOCC() {
        TestModelWithIdentifier  model = new TestModelWithIdentifier();
        model.setIdtp(Idtp.OCC);
        model.setIdno("81852090X");
        BeanValidator.validate(model);
    }

    @Test
    public void testOCCInvalid() {

        TestModelWithIdentifier  model = new TestModelWithIdentifier();
        model.setIdtp(Idtp.OCC);
        model.setIdno("818520900");
        BeanValidator.validate(model);
    }

    @Test
    public void testUSCIC() {
        TestModelWithIdentifier  model = new TestModelWithIdentifier();
        model.setIdtp(Idtp.USCIC);
        model.setIdno("911202246818640656");
        BeanValidator.validate(model);
    }

    @Test
    public void testUSCICInvalid() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("证件号码不合法");

        TestModelWithIdentifier  model = new TestModelWithIdentifier();
        model.setIdtp(Idtp.USCIC);
        model.setIdno("911202246818640657");
        BeanValidator.validate(model);
    }

    @Test
    public void testPASSPORT() {
        TestModelWithIdentifier  model = new TestModelWithIdentifier();
        model.setIdtp(Idtp.PASSPORT);
        model.setIdno("911202246818640656");
        BeanValidator.validate(model);
    }

    @Test
    public void testBaseException() {

        thrown.expect(ValidationException.class);

        TestModelWithIdentifierError model = new TestModelWithIdentifierError();
        model.setIdtp(Idtp.USCIC);
        model.setIdno("911202246818640656");
        BeanValidator.validate(model);
    }
}
