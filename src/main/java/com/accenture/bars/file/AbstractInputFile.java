package com.accenture.bars.file;

import java.io.File;
import java.util.List;

import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;

public abstract class AbstractInputFile {
	
	private File file;
	public final int MIN_BILLING_CYCLE = 1;
	public final int MAX_BILLING_CYCLE = 12;
	
	public AbstractInputFile() {
		
	}
	
	public List<Request> readFile() throws BarsException{
		return null;
		
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
}
