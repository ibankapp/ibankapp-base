/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util.test;

import org.ibankapp.base.util.Jaxb2Util;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Jaxb2UtilTest {

    @Test
    public void testCovertToXml() throws JAXBException {

        new Jaxb2Util();

        TestBean bean = new TestBean();

        Person person = new Person("codelder", 35);

        bean.setPerson(person);

        bean.setPhone("12345678");

        String xml = Jaxb2Util.convertToXml(bean, "UTF-8");

        Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<testBean>\n" +
                "    <person>\n" +
                "        <age>35</age>\n" +
                "        <name>codelder</name>\n" +
                "    </person>\n" +
                "    <phone>12345678</phone>\n" +
                "</testBean>\n", xml);
    }

    @XmlRootElement
    private static class TestBean {

        @XmlElement
        private Person person;

        @XmlElement
        private String phone;

        public Person getPerson() {
            return person;
        }

        void setPerson(Person person) {
            this.person = person;
        }

        public String getPhone() {
            return phone;
        }

        void setPhone(String phone) {
            this.phone = phone;
        }
    }

    private static class Person {

        private String name;

        private int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
