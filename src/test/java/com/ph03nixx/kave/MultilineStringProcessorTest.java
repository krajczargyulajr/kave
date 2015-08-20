package com.ph03nixx.kave;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.ph03nixx.kave.input.KaveFile;
import com.ph03nixx.kave.processor.MultilineStringProcessor;

public class MultilineStringProcessorTest {

	private static final String INPUT_0 = "/inputfiles/MultilineStringProcessorTest_Input0.kave";
	@Test
	public void placeholder() {
		assertTrue(true);
	}
	
	@Test
	public void convertMultilineStringCodeToSeparatedLinesInArbitraryString() throws Exception {
		KaveFile inputFile = KaveFile.fromClasspath(INPUT_0);
		
		MultilineStringProcessor processor = new MultilineStringProcessor();
		
		KaveFile processedFile = processor.process(inputFile);
		
		List<String> processedLines = processedFile.getLines();
		for(int i = 0; i < processedLines.size(); i++) {
			String processedLine = processedLines.get(i);
			System.out.println(processedLine);
			
			if(i != processedLines.size() - 1) {
				assertThat(processedLine, startsWith("\""));
				assertThat(processedLine, endsWith("\"+"));
			} else {
				// last line
				assertThat(processedLine, equalTo("\"\";"));
			}
		}
	}
	
}
