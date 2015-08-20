package com.ph03nixx.kave.processor;

import java.util.ArrayList;
import java.util.List;

import com.ph03nixx.kave.input.KaveFile;

public class MultilineStringProcessor {

	public KaveFile process(KaveFile f) {
		KaveFile processedFile = new KaveFile();
		processedFile.setFilePath(f.getFilePath());
		
		List<String> processedLines = new ArrayList<>();
		boolean insideMultilineString = false;
		for(String line : f.getLines()) {
			String processedLine = line;
			
			if(!insideMultilineString) {
				if(line.trim().endsWith("\"\"\"")) {
					processedLine = line.substring(0, line.length() - 3);
					insideMultilineString = true;
				}
			} else {
				if(line.trim().startsWith("\"\"\"")) {
					processedLine = "\"\";";
					insideMultilineString = false;
				} else {
					processedLine = "\"" + line + "\"+";
				}
			}
			
			if(processedLine.trim().length() > 0) {
				processedLines.add(processedLine);
			}
		}
		
		processedFile.setLines(processedLines);
		
		return processedFile;
	}
	
}
