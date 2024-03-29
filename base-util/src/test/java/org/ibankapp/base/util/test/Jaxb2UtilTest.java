/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util.test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.ibankapp.base.util.Jaxb2Util;
import org.junit.Assert;
import org.junit.Test;

public class Jaxb2UtilTest {

  @Test
  public void testCovertToXml() throws JAXBException {

    new Jaxb2Util();

    TestBean bean = new TestBean();

    Person person = new Person("张三", 35);

    bean.setPerson(person);

    bean.setPhone("12345678");

    String xml = Jaxb2Util.convertToXml(bean, "UTF-8");

    Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        + "<testBean>"
        + "<person>"
        + "<age>35</age>"
        + "<name>张三</name>"
        + "</person>"
        + "<phone>12345678</phone>"
        + "</testBean>", xml);

    xml = Jaxb2Util.convertToXml(bean, "ISO-8859-1");
    Assert.assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
        + "<testBean>"
        + "<person>"
        + "<age>35</age>"
        + "<name>&#24352;&#19977;</name>"
        + "</person>"
        + "<phone>12345678</phone>"
        + "</testBean>", xml);
  }

  @Test
  public void testPrettyFormat() throws JAXBException {
    TestBean bean = new TestBean();

    Person person = new Person("张三", 35);

    bean.setPerson(person);

    bean.setPhone("12345678");

    String xml = Jaxb2Util.convertToXml(bean, "UTF-8");

    xml = Jaxb2Util.prettyFormat(xml,4);

    Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        + "<testBean>\n"
        + "    <person>\n"
        + "        <age>35</age>\n"
        + "        <name>张三</name>\n"
        + "    </person>\n"
        + "    <phone>12345678</phone>\n"
        + "</testBean>\n", xml);
  }

  @Test
  public void testConvertToJavaBean() throws JAXBException {

    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
        + "<testBean>\n"
        + "    <person>\n"
        + "        <age>35</age>\n"
        + "        <name>张三</name>\n"
        + "    </person>\n"
        + "    <phone>12345678</phone>\n"
        + "</testBean>\n";

    TestBean bean = Jaxb2Util.converyToJavaBean(xml, TestBean.class);

    Assert.assertEquals(35, bean.getPerson().getAge());
    Assert.assertEquals("张三", bean.getPerson().getName());
    Assert.assertEquals("12345678", bean.getPhone());
  }

  @XmlRootElement
  private static class TestBean {

    @XmlElement
    private Person person;

    @XmlElement
    private String phone;

    Person getPerson() {
      return person;
    }

    void setPerson(Person person) {
      this.person = person;
    }

    String getPhone() {
      return phone;
    }

    void setPhone(String phone) {
      this.phone = phone;
    }
  }

  private static class Person {

    private String name;

    private int age;

    Person() {

    }

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
