package com.accenture.bars.xml;

import java.io.File;

import javax.xml.bind.JAXBException;

import com.accenture.bars.domain.RecordToWrite;

public abstract class AbstractOutputXML{
	
	private File file;
	
	public AbstractOutputXML() {
		
	}

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

	public void xmlWriter(RecordToWrite recToWrite) throws JAXBException {
		
	}
}