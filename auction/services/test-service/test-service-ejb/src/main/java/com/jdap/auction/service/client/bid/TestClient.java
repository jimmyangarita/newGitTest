package com.jdap.auction.service.client.bid;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jdap.auction.service.bid.BidRemote;

public class TestClient {
	
	public static Logger logger = Logger.getLogger(TestClient.class.getName());
	
	private static final String INPUT_GZIP_FILE = "C:\\mss\\delayed.gz";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BidRemote remoteTest;
		 
		String s = "{\"UMGID\" : \"0853b5bb-2a5e-47e3-93b4-511e1f625cf8\"}";
		try {
			final Context context = getInitialContext();
			
			logger.info("BEFORE......");
			remoteTest = (BidRemote) context.lookup("ejb.jdap.auction.service.BidEJB");
			//logger.info("::: " +remoteTest.ActiveWSFilters(" Totos "));
			
			remoteTest.startTransfer(s);
			remoteTest.finishTransfer(s);
			
			logger.info("AFTER adding the Bid......");

			TestClient local = new TestClient();
			
			GZIPInputStream gzis = 
		       		new GZIPInputStream(new FileInputStream(INPUT_GZIP_FILE));
			
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			int len;
			while ((len = gzis.read(buffer)) > 0) {
			    out.write(buffer, 0, len);			    
			}

			gzis.close();
			out.close();
			
			System.out.println("JA BEFORE --");
			//GZIPInputStream gbzis = new GZIPInputStream(new ByteArrayInputStream(out.toByteArray()));
			System.out.println("JA AFTER---" + out.toByteArray().length);
			
			
			/*String responseFile = local.ProcessGZIPcreateFile(gzis.);
			System.out.println("JA responseFile: " +responseFile);*/
			
			//remoteTest.changeBidAmount("1.000.000", new Double(12.5));
			String response = "no";
			byte[] responseByte;
			
			responseByte = local.stringToByteArray("data input test abc!!");
			logger.warning("responseByte: " + responseByte);
			response = local.ProcessFile(responseByte);
			/*response = remoteTest.ProcessFile("data String ".getBytes());
			response = remoteTest.ProcessFileString("data String");
			remoteTest.ProcessFile2("data".getBytes());*/
			
			
			logger.info("response: "+response);
			
		}catch( Exception a) {
		
			logger.log(Level.SEVERE, "\"ServiceException\": " + a + "\nCause: " + a.getCause());
			logger.log(Level.SEVERE, "\"ServiceException\": " + a.getMessage());

		}
	}
	
	/**
	 * ProcessGZIPcreateFile
	 */
	public String ProcessGZIPcreateFile(byte[] data) throws IOException {
		logger.info("\n\n JA JAVA EJB ProcessGZIPcreateFile START! With array size:" + data.length);
		logger.fine("JA JAVA EJB ProcessGZIPcreateFile data to process:" + new String(data));

		GZIPInputStream gbzis = new GZIPInputStream(new ByteArrayInputStream(data));
		InputStreamReader streamReader = new InputStreamReader(gbzis);
		BufferedReader bufferReader = new BufferedReader(streamReader);
		StringWriter stringWritter = new StringWriter();

		String line = "EMPTY!!";
		while ((line = bufferReader.readLine()) != null) {
			stringWritter.write(line);
		}

		bufferReader.close();

		String str = stringWritter.toString();
		logger.fine("\n\nJA File String : \n" + str + "\n");
				
		logger.info("\nJA EJB Start Creating file........................................................");
		
/*		GZIPInputStream gzis = new GZIPInputStream(new ByteArrayInputStream(data));
		FileOutputStream out = new FileOutputStream(OUTPUT_FILE);

		int len;
		while ((len = gzis.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}

		gzis.close();
		out.close();
*/
		logger.info("JA JAVA EJB ProcessGZIPcreateFile END!!\n\n");
		return str;
	}
	
	private static Context getInitialContext() throws NamingException {
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, "t3://10.207.52.140:8011");
		return new InitialContext(env);
	}
	
	
	public byte[] stringToByteArray(String data)
    {
    	System.out.println("\n JA JAVA CALLOUT stringToByteArray input data: " + data);
    	byte [] dataBytes = data.getBytes();
    	
    	System.out.println("\n JA JAVA CALLOUT stringToByteArray data in bytes:: " + dataBytes);
    	
    	return dataBytes;
    }
	
	public String ProcessFile(byte[] data) throws IOException {
		logger.log(Level.WARNING, "\nEJB ProcessFile START! With array size:"
				+ data);

		ByteArrayInputStream baInputStraem = new ByteArrayInputStream(data);
		InputStreamReader streamReader = new InputStreamReader(baInputStraem);
		BufferedReader bufferReader = new BufferedReader(streamReader);
		StringWriter stringWritter = new StringWriter();

		String line = "EMPTY!!";
		while ((line = bufferReader.readLine()) != null) {
			stringWritter.write(line);
		}

		bufferReader.close();

		logger.log(Level.WARNING, "stringWritter.toString(): \n" + stringWritter.toString());
		logger.log(Level.WARNING, "EJB ProcessFile END!!\n");
		return stringWritter.toString();
	}


}
