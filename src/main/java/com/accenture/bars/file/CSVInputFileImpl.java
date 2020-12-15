package com.accenture.bars.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;

public class CSVInputFileImpl extends AbstractInputFile {
	
	private static final Logger log = LoggerFactory.getLogger(CSVInputFileImpl.class);
	
	public CSVInputFileImpl() {
		
	}
	
	public List<Request> readFile() throws BarsException{
		log.info("Reading File....");
		
		List<Request> records = new ArrayList<>();
		String line;
		Request req = new Request();
		 DateValidator validator = new DateValidatorUsingDateFormat("MM/dd/yyyy");
		try {
			log.info("try catch process on CSVInputFile....");
			BufferedReader reader = new BufferedReader(new FileReader(new File(this.getFile().getAbsolutePath())));
			int lineCount = 1;
			while((line = reader.readLine())!=null) {
				
				log.info("processing csv");
				String [] values = line.split(",");
				int range = Integer.parseInt(values[0]);
				  if(validator.isValid(values[1]) == false) {
	                	throw new BarsException(BarsException.INVALID_START_DATE_FORMAT+ lineCount);
	                }
				  if(validator.isValid(values[2]) == false) {
	                	throw new BarsException(BarsException.INVALID_END_DATE_FORMAT+ lineCount);
	                }
				
				
					if(range >= MIN_BILLING_CYCLE && range <= MAX_BILLING_CYCLE ) {
					log.info("if condition");
						req = new Request();
						req.setBillingCycle(Integer.parseInt(values[0]));
						req.setStartDate(LocalDate.parse(values[1], DateTimeFormatter.ofPattern("MM/dd/yyyy")));
						req.setEndDate(LocalDate.parse(values[2], DateTimeFormatter.ofPattern("MM/dd/yyyy")));
						records.add(req);
						lineCount = lineCount + 1;
                        }    
					 else {
	                    	throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE + lineCount);
	                    }
                    
                }
			reader.close();
			}
		catch (IOException io) {
		}
		log.info("{}", records.size());
		return records;
	}
}
