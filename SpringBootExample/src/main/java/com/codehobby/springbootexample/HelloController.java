package com.codehobby.springbootexample;

import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController
{
	@RequestMapping("/")
	public String hello()
	{
		URL url;
		StringBuilder powerballDataSB = new StringBuilder();
		String powerballURL = "http://www.powerball.com/powerball/winnums-text.txt";
		int numLines = 1;
		try
		{
			url = new URL( powerballURL );
			BufferedReader in = new BufferedReader( new InputStreamReader(url.openStream()) );
			String line;
			String delimiters = "[ ]+";
			String[] powerballData;
			int maxTokens = 8;
			in.readLine();//First line, which has column headers not data
			while ((line = in.readLine()) != null)
			{
				numLines++;
				powerballData = line.split(delimiters, maxTokens);
				powerballDataSB.append( Arrays.toString(powerballData) );
				powerballDataSB.append( "\n<br>\n");
			}
			in.close();
		} catch (MalformedURLException e) {
			System.err.println("Malformed URL: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("I/O Error: " + e.getMessage());
			e.printStackTrace();
		} 
		return "Number of lines: " + numLines + "\n<br>\n" + powerballDataSB.toString();
	}

}
