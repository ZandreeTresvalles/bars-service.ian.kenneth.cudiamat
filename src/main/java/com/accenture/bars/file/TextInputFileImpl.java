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
import com.accenture.bars.exception.BarsException;
import com.accenture.bars.domain.Request;

public class TextInputFileImpl extends AbstractInputFile {
	
	 private static final Logger log = LoggerFactory.getLogger(TextInputFileImpl.class);
	    
	    public TextInputFileImpl() {
	        
	    }
	    
	    public List<Request> readFile() throws BarsException {
	        log.info("Reading File....");
	        
	        List<Request> records = new ArrayList<>();
	        String line;
	        Request req = new Request();
	        DateValidator validator = new DateValidatorUsingDateFormat("MMddyyyy");

	           

	        try {
	            log.info("try catch process on TextInputFile....");
	            BufferedReader reader = new BufferedReader(new FileReader(new File(this.getFile().getAbsolutePath())));
	            
	            int lineCount = 1;
	            
	            while((line = reader.readLine())!=null) {
	                log.info("processing txt");
	                String [] values = new String[3];
	                values[0] = line.substring(0,2);
	                values[1] = line.substring(2,10);
	                if(validator.isValid(values[1]) == false) {
	                	throw new BarsException(BarsException.INVALID_START_DATE_FORMAT+ lineCount);
	                }
	                values[2] = line.substring(10,18);
	                if(validator.isValid(values[2]) == false) {
	                	throw new BarsException(BarsException.INVALID_END_DATE_FORMAT+ lineCount);
	                }
	                int range = Integer.parseInt(values[0]);
	                
	                    if(range >= MIN_BILLING_CYCLE && range <= MAX_BILLING_CYCLE ) {
	                        log.info("if condition");
	                        req = new Request();
	                        req.setBillingCycle(Integer.parseInt(values[0]));
	                        req.setStartDate(LocalDate.parse(values[1], DateTimeFormatter.ofPattern("MMddyyyy")));
	                        req.setEndDate(LocalDate.parse(values[2], DateTimeFormatter.ofPattern("MMddyyyy")));
	                        records.add(req);
	                        lineCount = lineCount + 1;
	                        }    
	                    else {
	                    	throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE + lineCount);
	                    }
	                    
	                }
	            reader.close();
	            }
	        catch (IOException e) {
	        }
	        log.info("{}", records.size());
	        return records;
	    }
	}