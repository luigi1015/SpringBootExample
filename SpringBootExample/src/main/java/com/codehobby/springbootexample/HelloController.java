package com.codehobby.springbootexample;

import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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
		try
		{
			url = new URL( powerballURL );
			BufferedReader in = new BufferedReader( new InputStreamReader(url.openStream()) );
			String line;
			while ((line = in.readLine()) != null)
			{
				powerballDataSB.append( line );
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
		return powerballDataSB.toString();
	}

}
