/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * JAXB2应用类
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class Jaxb2Util {

  /**
   * 将JavaBean转换为指定字符集的XML字符串.
   *
   * @param obj      JavaBean
   * @param encoding 字符集编码
   * @return XML字符串
   * @throws JAXBException 转换错误
   */
  public static String convertToXml(Object obj, String encoding) throws JAXBException {

    JAXBContext context = JAXBContext.newInstance(obj.getClass());
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
    marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

    StringWriter writer = new StringWriter();
    marshaller.marshal(obj, writer);

    String declare = "<?xml version=\"1.0\" encoding=\""+encoding+"\"?>";
    return declare + writer.toString();

  }

  /**
   * 将xml转换为指定类型的JavaBean.
   *
   * @param xml    xml字符串
   * @param tclass JavaBean类型
   * @param <T>    JavaBean类型
   * @return JavaBean
   * @throws JAXBException 转换错误
   */
  @SuppressWarnings("unchecked")
  public static <T> T converyToJavaBean(String xml, Class<T> tclass) throws JAXBException {

    JAXBContext context = JAXBContext.newInstance(tclass);
    Unmarshaller unmarshaller = context.createUnmarshaller();

    return (T) unmarshaller.unmarshal(new StringReader(xml));

  }

  public static String prettyFormat(String input, int indent) {
    try {
      Source xmlInput = new StreamSource(new StringReader(input));
      StringWriter stringWriter = new StringWriter();
      StreamResult xmlOutput = new StreamResult(stringWriter);
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      transformerFactory.setAttribute("indent-number", indent);
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(xmlInput, xmlOutput);
      String output = xmlOutput.getWriter().toString();
      output = StringUtils.replace(output, "?>", "?>\n");
      return output;
    } catch (TransformerException e) {
      return input;
    }
  }
}