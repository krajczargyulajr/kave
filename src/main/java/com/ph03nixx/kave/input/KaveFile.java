package com.ph03nixx.kave.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KaveFile {

	String filePath;
	String fileContent = "";
	List<String> lines;
	
	public KaveFile() { }
	
	private KaveFile(String filePath) throws IOException {
		this.filePath = filePath;
		
		readFile();
	}
	
	public List<String> getLines() {
		return this.lines;
	}
	
	public void setLines(List<String> value) {
		this.lines = value;
	}
	
	public String getFilePath() {
		return this.filePath;
	}
	
	public void setFilePath(String value) {
		this.filePath = value;
	}
	
	private void readFile() throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath)));
			List<String> ln = new ArrayList<>();
			String s = "", l;
			while((l = reader.readLine()) != null) {
				// TODO: keep original line endings from file
				ln.add(l);
				s += l + "\n";
			}
			
			this.fileContent = s;
			this.lines = ln;
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
	}
	
	public static KaveFile fromClasspath(String fileName) throws IOException {
		return new KaveFile(fileName);
	}
	
	public static KaveFile copyOf(KaveFile f) {
		KaveFile nf = new KaveFile();
		
		nf.filePath = f.filePath;
		
		
		return nf;
	}
}
