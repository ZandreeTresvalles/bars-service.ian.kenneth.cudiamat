package com.accenture.bars.factory;

import java.io.File;

import com.accenture.bars.exception.BarsException;
import com.accenture.bars.file.AbstractInputFile;
import com.accenture.bars.file.CSVInputFileImpl;
import com.accenture.bars.file.TextInputFileImpl;

public class InputFileFactory {
	
	
	private static InputFileFactory factory;
	
	private InputFileFactory() {
		
	}
	
	public AbstractInputFile getInputFile (File file) throws BarsException{
		
		
			if(file.exists()) {
				if(file.getName().endsWith(".txt")){
					if(file.length() > 0 ) {
						return new TextInputFileImpl();
					}
					else {
						throw new BarsException(BarsException.NO_RECORDS_TO_READ);
					}
				}
				else if (file.getName().endsWith(".csv")) {
					if(file.length() > 0 ) {
						return new CSVInputFileImpl();
					}
					else {
						throw new BarsException(BarsException.NO_RECORDS_TO_READ);
					}
				}
				else {
					throw new BarsException(BarsException.FILE_NOT_SUPPORTED);
				}
			}
			else {
				throw new BarsException(BarsException.PATH_DOES_NOT_EXIST);
			}
			
	}
	
	public static InputFileFactory getInstance() {
		if (factory == null) {
			factory = new InputFileFactory();
			}
			return factory;
	}

}
