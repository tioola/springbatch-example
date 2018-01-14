package com.example.demo.job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class JobReader implements ItemReader<String> {

	private final Iterator<String> it;
	
	public JobReader() {
		List<String> itarr = new ArrayList<String>();
		
		itarr.add("Diogo");
		itarr.add("Nara");
		itarr.add("Mara");
		
		it = itarr.iterator();
	}
	
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if(it.hasNext()) {
			return this.it.next();
		}else {
			return null;
		}
	}

}
