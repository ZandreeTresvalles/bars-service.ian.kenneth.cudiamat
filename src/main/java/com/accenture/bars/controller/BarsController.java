package com.accenture.bars.controller;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.bars.domain.Record;
import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;

@RestController
public class BarsController {
	
//	Logger log = LoggerFactory.getLogger(BarsController.class);
	
	@Autowired
	private FileProcessor fileProcessor;
	
	public BarsController() {
		
	}
	
	@GetMapping("/bars")
	public List<Record> requestBilling(@RequestParam String filePath) throws JAXBException, BarsException{
		File file = new File("C:/BARS_TEST/" + filePath);
		List<Request> requests = fileProcessor.execute(file);

		List <Record> records =  fileProcessor.retrieveRecordfromDB(requests);
		fileProcessor.writeOutput(records);
		return records;
		
		
	}
		
	

}
