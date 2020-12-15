package com.accenture.bars.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.accenture.bars.domain.RecordToWrite;


public class OutputXMLImpl extends AbstractOutputXML{
	
	private static final Logger log = LoggerFactory.getLogger(OutputXMLImpl.class);
	
	public OutputXMLImpl() {
		
	}
	
	@Override
	public void xmlWriter(RecordToWrite recToWrite) throws JAXBException {
		
		File file = getFile();
		File schemaFile = new File("C:/BARS/xsd/bars_output.xsd");
		JAXBContext jaxbContext = JAXBContext.newInstance(RecordToWrite.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(recToWrite,file);

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));
            log.info("Successfully Validated XML file " + file);

        } catch (SAXException | IOException e) {
            log.info("Invalid XML file");
        }
		
		
	}

}
