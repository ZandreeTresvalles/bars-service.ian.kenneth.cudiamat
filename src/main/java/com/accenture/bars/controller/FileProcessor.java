package com.accenture.bars.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.bars.domain.Record;
import com.accenture.bars.domain.RecordToWrite;
import com.accenture.bars.domain.Request;
import com.accenture.bars.entity.Billing;
import com.accenture.bars.exception.BarsException;
import com.accenture.bars.factory.InputFileFactory;
import com.accenture.bars.file.AbstractInputFile;
import com.accenture.bars.repository.BillingRepository;
import com.accenture.bars.xml.OutputXMLImpl;

@Service
public class FileProcessor {

	@SpringBootApplication(scanBasePackages={
			"com.accenture.bars.repository.BillingRepository", "com.accenture.bars.controller.FileProcessor"})
	@Autowired
	private BillingRepository billingRepository;

	public FileProcessor() {
		
	}
	
	public List<Request> execute (File file) throws BarsException{
		InputFileFactory inputFileFactory = InputFileFactory.getInstance();
		AbstractInputFile abstractInputFile = inputFileFactory.getInputFile(file);
		abstractInputFile.setFile(file);
		List<Request> requests = abstractInputFile.readFile();
		return requests;
	}
		    
	public List<Record> retrieveRecordfromDB(List<Request> requests) throws BarsException{
		List<Record> records = new ArrayList<>();
		List<Billing> bill = new ArrayList<>();
		
		for(Request request: requests) {
			bill.add(billingRepository.findByBillingCycleAndStartDateAndEndDate(request.getBillingCycle(),request.getStartDate(),request.getEndDate()));
		}
		
		if(bill.get(0)!=null) {
			for(Billing billing: bill) {
					Record record = new Record();
					record.setBillingCycle(billing.getBillingCycle());
					record.setStartDate(billing.getStartDate());
					record.setEndDate(billing.getEndDate());
					record.setAccountName(billing.getAccountId().getAccountName());
					record.setFirstName(billing.getAccountId().getCustomerId().getFirstName());
					record.setLastName(billing.getAccountId().getCustomerId().getLastName());
					record.setAmount(billing.getAmount());
					records.add(record);
				}
		} 
		else {
			throw new BarsException(BarsException.NO_RECORDS_TO_WRITE);
		}
		
		return records;
		
	}
	
public void writeOutput(List <Record> retrieveRecordsfromDB) throws JAXBException{
        
        List<RecordToWrite> recordToWrite = new ArrayList<>();
        
        for(Record records: retrieveRecordsfromDB) {
            RecordToWrite recordsToWrite = new RecordToWrite();
            recordsToWrite.setBillingCycle(records.getBillingCycle());
            recordsToWrite.setStartDate(records.getStartDate());
            recordsToWrite.setEndDate(records.getEndDate());
            recordsToWrite.setAccountName(records.getAccountName());
            recordsToWrite.setFirstName(records.getFirstName());
            recordsToWrite.setLastName(records.getLastName());
            recordsToWrite.setAmount(records.getAmount());
            recordToWrite.add(recordsToWrite);
            }
        
         Date date = new Date();
         SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy_HHmmss");
         File xmlFile = new File("C:/BARS/Report/BARS_Report-"+ dateFormat.format(date)+".xml");
       
         RecordToWrite recToWrite = new RecordToWrite();
         recToWrite.setRecord(recordToWrite);
    
         OutputXMLImpl abstractOutputXml = new OutputXMLImpl();
             abstractOutputXml.setFile(xmlFile);
             abstractOutputXml.xmlWriter(recToWrite);

 

        
    }
}
