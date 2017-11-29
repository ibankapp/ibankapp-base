package org.ibankapp.base.validation.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XmlValidator {

  public static void validateXmlByXsd(InputStream xml, InputStream xsd) throws SAXException, IOException {
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema schema = factory.newSchema(new StreamSource(xsd));
    Validator validator = schema.newValidator();
    validator.validate(new StreamSource(xml));
  }

  public static void validateXmlByXsd(byte[] xml, byte[] xsd) throws IOException, SAXException {
    validateXmlByXsd(new ByteArrayInputStream(xml), new ByteArrayInputStream(xsd));
  }

  public static void validateXmlByXsd(byte[] xml, InputStream xsd) throws IOException, SAXException {
    validateXmlByXsd(new ByteArrayInputStream(xml), xsd);
  }
}
