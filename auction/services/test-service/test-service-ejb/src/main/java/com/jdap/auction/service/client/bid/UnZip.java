package com.jdap.auction.service.client.bid;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.codec.binary.Base64;


public class UnZip
{
    List<String> fileList;
    private static final String INPUT_ZIP_FILE = "C:\\mss\\delayed.zip";
    private static final String OUTPUT_FOLDER = "C:\\mss\\outputzip";
    
    private static final String INPUT_GZIP_FILE = "C:\\mss\\delayed.gz";
    private static final String OUTPUT_FILE = "C:\\mss\\outputzip\\file1.txt";
    
		
    public static void main( String[] args )
    {
    	UnZip gunZip = new UnZip();
    	//unZip.unZipIt(INPUT_ZIP_FILE,OUTPUT_FOLDER);
    	gunZip.gunzipIt();
    }
    
    /**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
     */
    public void unZipIt(String zipFile, String outputFolder){

     byte[] buffer = new byte[1024];
    	
     try{
    		
    	//create output directory is not exists
    	File folder = new File(OUTPUT_FOLDER);
    	if(!folder.exists()){
    		folder.mkdir();
    	}
    		
    	//get the zip file content
    	ZipInputStream zis = 
    		new ZipInputStream(new FileInputStream(zipFile));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();
    		
    	while(ze!=null){
    			
    	   String fileName = ze.getName();
           File newFile = new File(outputFolder + File.separator + fileName);
                
           //System.out.println("file unzip : "+ newFile.getAbsoluteFile());
                
            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();
              
            FileOutputStream fos = new FileOutputStream(newFile);             

            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }
        		
            fos.close();   
            ze = zis.getNextEntry();
    	}
    	
        zis.closeEntry();
    	zis.close();
    		
    	//System.out.println("Done");
    		
    }catch(IOException ex){
       ex.printStackTrace(); 
    }
   }    
    
    public void gunzipIt(){
    	 
        byte[] buffer = new byte[1024];
    
        try{
    
       	 GZIPInputStream gzis = 
       		new GZIPInputStream(new FileInputStream(INPUT_GZIP_FILE));
       	 
       	InputStreamReader streamReader = new InputStreamReader( gzis );

        BufferedReader bufferReader = new BufferedReader( streamReader );
        StringWriter stringWritter = new StringWriter();

        String line = "EMPTY!!";
        while ( ( line = bufferReader.readLine() ) != null )
        {
            stringWritter.write( line );
        }

        bufferReader.close();

        String str = stringWritter.toString();
      

        //System.out.println( "JA File String : \n" + str );
        
         
        byte [] valueEncoded = ProcessFileEncoded(str.getBytes());
        //System.out.println( "Encoded value is " + new String( valueEncoded ) );
        
        byte[] valueDecoded = Base64.decodeBase64( valueEncoded );
        //System.out.println( "Decoded value is " + new String( valueDecoded ) );
        
        //ejb
        byte[] finalValueDecoded = Base64.decodeBase64( new String( valueDecoded ) );
        //System.out.println( "Final Decoded value is " + new String( finalValueDecoded ) );
        
        gzis = 
      		new GZIPInputStream(new FileInputStream(INPUT_GZIP_FILE));
    
       	 FileOutputStream out = 
               new FileOutputStream(OUTPUT_FILE);
    
           int len;
           while ((len = gzis.read(buffer)) > 0) {
           	out.write(buffer, 0, len);
           }
    
           gzis.close();
       	out.close();
    
       	//System.out.println("Done: " + out.toString());
       	
       }catch(IOException ex){
          ex.printStackTrace();   
       }
      } 
    
    
    public static byte[] ProcessFileEncoded( byte[] data ) throws IOException
    {
        //System.out.println( "\n JA JAVA CALLOUT ProcessFileEncoded START! With array size:" + data.length );

        byte[] bytesEncoded = Base64.encodeBase64( data );
       
        //System.out.println( "JA JAVA CALLOUT ProcessFileEncoded END!!\n" + bytesEncoded );

        return bytesEncoded;
    }

    
    public static String ProcessFile( byte[] data ) throws IOException
    {
        //System.out.println( "\n JA JAVA CALLOUT ProcessFile START! With array size:" + data.length );

        GZIPInputStream gbzis = new GZIPInputStream( new ByteArrayInputStream( data ) );

        InputStreamReader streamReader = new InputStreamReader( gbzis );

        BufferedReader bufferReader = new BufferedReader( streamReader );
        StringWriter stringWritter = new StringWriter();

        String line = "EMPTY!!";
        while ( ( line = bufferReader.readLine() ) != null )
        {
            stringWritter.write( line );
        }

        bufferReader.close();

        String str = stringWritter.toString();

        //System.out.println( "JA File String : \n" + str );

        //System.out.println( "JA JAVA CALLOUT ProcessFile END!!\n" );

        return str;
    }
}