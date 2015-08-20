package com.ph03nixx.kave;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.ph03nixx.kave.input.KaveFile;
import com.ph03nixx.kave.processor.MultilineStringProcessor;

public class MultilineStringProcessorTest {

	private static final String INPUT_0 = "/inputfiles/MultilineStringProcessorTest_Input0.kave";
	private static final String INPUT_1 = "/inputfiles/MultilineStringProcessorTest_Input1.kave";
	
	@Test
	public void placeholder() {
		assertTrue(true);
	}
	
	@Test
	public void convertStandaloneMultilineStringToSeparatedLines() throws Exception {
		KaveFile inputFile = KaveFile.fromClasspath(INPUT_0);
		
		MultilineStringProcessor processor = new MultilineStringProcessor();
		
		KaveFile processedFile = processor.process(inputFile);
		
		List<String> processedLines = processedFile.getLines();
		for(int i = 0; i < processedLines.size(); i++) {
			String processedLine = processedLines.get(i);
			
			if(i != processedLines.size() - 1) {
				assertThat(processedLine, startsWith("\""));
				assertThat(processedLine, endsWith("\"+"));
			} else {
				// last line
				assertThat(processedLine, equalTo("\"\";"));
			}
		}
	}
	
	@Test
	public void convertMultilineStringInCode() throws IOException {
		KaveFile inputFile = KaveFile.fromClasspath(INPUT_1);
		
		MultilineStringProcessor processor = new MultilineStringProcessor();
		
		KaveFile processedFile = processor.process(inputFile);
		
		String controlString = "\"		This\"+\"		is\"+\"		a\"+\"		simple\"+\"		multiline\"+\"		testfile\"+\"\";";
		String actualString = "";
		
		List<String> processedLines = processedFile.getLines();
		for(int l = 3; l < 10; l++) {
			actualString += processedLines.get(l);
		}
		
		assertThat(actualString, equalTo(controlString));
	}
	
}
