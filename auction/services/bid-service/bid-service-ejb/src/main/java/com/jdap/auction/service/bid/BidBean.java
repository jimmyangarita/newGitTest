package com.jdap.auction.service.bid;

// ~--- non-JDK imports ---------------------------------------------------------

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author jangarita
 * 
 */
@Stateless(name = "BidEJB", mappedName = "ejb.jdap.auction.service.BidEJB")
public class BidBean extends BaseBidService implements BidRemote, BidLocal {

	private static final String OUTPUT_FILE = "/home/oracle/outputzip/file1.txt";

	/**
	 * Default constructor.
	 */
	public BidBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method description
	 * 
	 */
	@PostConstruct
	public void initialize() {
		logger.log(Level.WARNING,
				"\n...................Bean Test Initialize........................\n:");
	}

	public void startTransfer(String umgIDJson) throws Exception {
		
		String engineID = getEngineID(umgIDJson);
		logger.warning("EJB StartTransfer, Engine ID: " + engineID);			
	}

	public void finishTransfer(String umgIDJson) throws Exception {		
		String engineID = getEngineID(umgIDJson);
		logger.warning("EJB finishTransfer, Engine ID: " + engineID);		
	}

	public void uploadHistoricalData(byte[] file, String umgIDJson)
			throws Exception {
		System.out.println("\n EJB Start uploadHistoricalData ..................................");
		String engineID = getEngineID(umgIDJson);
		logger.warning("EJB uploadHistoricalData, Engine ID: " + engineID);	
		logger.warning("\n\n EJB uploadHistoricalData With array size: " + file.length);
		//logger.fine("EJB uploadHistoricalData data to process:"	+ new String(file));
		
		System.out.println("\n EJB Start Creating file................................." + umgIDJson);
		
		byte[] buffer = new byte[1024];
		GZIPInputStream gzis = new GZIPInputStream(new ByteArrayInputStream(file));
		FileOutputStream out = new FileOutputStream(OUTPUT_FILE);

		int len;
		while ((len = gzis.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}

		gzis.close();
		out.close();

		System.out.println("\n EJB Finish Creating file........................................................");
		logger.warning("EJB uploadHistoricalData END!!\n\n");

	}

	
	public String getEngineID(String umgIDJson) throws Exception {
		System.out.println("EJB, Transfer at : " + System.currentTimeMillis());
		JSONParser parser = new JSONParser();
		String engineID = "NO ID";
		try {
			Object obj = parser.parse(umgIDJson);
			JSONObject engineJson = (JSONObject) obj;
			engineID = engineJson.get("UMGID").toString();
			logger.info("EJB, UMGID : " + engineID);
			
			System.out.println("EJB,--- JA --- UMGID :: " + engineID);
			
		} catch (ParseException pe) {
			logger.severe("position: " + pe.getPosition());
			logger.severe("pe: " + pe);
		}
		
		return engineID;
	}
	
	public String ProcessGZIPFile(byte[] data) throws IOException {
		logger.info("\n\n JA JAVA EJB ProcessGZIPFile START! With array size:"
				+ data.length);
		logger.fine("JA JAVA EJB ProcessGZIPFile START! With value::"
				+ new String(data));

		/*
		 * byte[] dataDecoded = Base64.decodeBase64(data);
		 * logger.warning("\nJA JAVA EJB ProcessGZIPFile dataDecoded array size::"
		 * + dataDecoded.length);
		 * logger.warning("JA JAVA EJB ProcessGZIPFile dataDecoded value::" +
		 * new String (dataDecoded));
		 */

		GZIPInputStream gbzis = new GZIPInputStream(new ByteArrayInputStream(
				data));
		InputStreamReader streamReader = new InputStreamReader(gbzis);
		BufferedReader bufferReader = new BufferedReader(streamReader);
		StringWriter stringWritter = new StringWriter();

		String line = "EMPTY!!";
		while ((line = bufferReader.readLine()) != null) {
			stringWritter.write(line);
		}

		bufferReader.close();

		String str = stringWritter.toString();
		logger.fine("JA File String : \n" + str);
		logger.info("JA JAVA EJB ProcessFile END!!\n\n");

		// ProcessGZIPcreateFile(dataDecoded);

		return str;
	}

	/**
	 * ProcessGZIPcreateFile
	 */
	public String ProcessGZIPcreateFile(byte[] data) throws IOException {
		logger.info("\n\n JA JAVA EJB ProcessGZIPcreateFile START! With array size:"
				+ data.length);
		logger.fine("JA JAVA EJB ProcessGZIPcreateFile data to process:"
				+ new String(data));

		GZIPInputStream gbzis = new GZIPInputStream(new ByteArrayInputStream(
				data));
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

		logger.severe("\nJA EJB Start Creating file........................................................");

		byte[] buffer = new byte[1024];
		GZIPInputStream gzis = new GZIPInputStream(new ByteArrayInputStream(
				data));
		FileOutputStream out = new FileOutputStream(OUTPUT_FILE);

		int len;
		while ((len = gzis.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}

		gzis.close();
		out.close();

		logger.info("JA JAVA EJB ProcessGZIPcreateFile END!!\n\n");
		return str;
	}

	/**
	 * 
	 */
	public void changeBidAmount(String bidId, Double newAmount)
			throws Exception {

		logger.log(Level.WARNING, "Start: change bid amount for bid: " + bidId);

		logger.log(Level.WARNING, "End: change Bid Amount bid:" + bidId);
	}

	public String ProcessFile(byte[] data) throws IOException {
		logger.log(Level.WARNING, "\nEJB ProcessFile START! With array size:"
				+ data.length);

		ByteArrayInputStream baInputStraem = new ByteArrayInputStream(data);
		InputStreamReader streamReader = new InputStreamReader(baInputStraem);
		BufferedReader bufferReader = new BufferedReader(streamReader);
		StringWriter stringWritter = new StringWriter();

		String line = "EMPTY!!";
		while ((line = bufferReader.readLine()) != null) {
			stringWritter.write(line);
		}

		bufferReader.close();

		logger.log(Level.WARNING, "stringWritter.toString(): \n"
				+ stringWritter.toString());
		logger.log(Level.WARNING, "EJB ProcessFile END!!\n");
		return stringWritter.toString();
	}

	public void ProcessFile2(byte[] data) throws Exception {
		System.out.println("JA ProcessFile2");
		logger.log(Level.WARNING, "\nEJB ProcessFile2 START! With array size:");
		logger.log(Level.WARNING, "JA " + data.length);

	}

	public String ProcessFileString(String data) throws Exception {
		System.out.println("JA ProcessFileString");
		logger.log(Level.WARNING, "\nEJB ProcessFileString START! With data:");
		logger.log(Level.WARNING, "" + data);
		logger.log(Level.WARNING, "\nEJB ProcessFileString END!!");
		return data;
	}

}
