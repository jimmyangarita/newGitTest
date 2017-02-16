package com.jdap.auction.service.client.bid;

import java.io.File;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
 
public class RESTClient {
     
    public static void main(String args[]) throws Exception {
        File f = new File("C:\\mss\\delayed.gz");
        sendFile(f);
    }
   
    public static void sendFile(File file) throws Exception 
    {
    String BASE_URL="http://10.207.52.140:8011/Multipart/MultipartBinaryFileProxy";
        HttpClient client = new DefaultHttpClient() ;
        HttpPost postRequest = new HttpPost (BASE_URL) ;
        try
        {
             
            //Set various attributes 
            MultipartEntity multiPartEntity = new MultipartEntity () ;
            
           // multiPartEntity.addPart("fileName", new StringBody(file.getName() != null ? file.getName() : file.getName())) ;
   
            //FileBody fileBody = new FileBody(file, "application/octect-stream") ;
            FileBody fileBody = new FileBody(file, "application/gzip") ;
            //Prepare payload
            multiPartEntity.addPart("attachment", fileBody) ;
   
            //Set to request body
            postRequest.setEntity(multiPartEntity) ;
              
            //Send request
            HttpResponse response = client.execute(postRequest) ;
              
            //Verify response if any
            if (response != null)
            {
                System.out.println("Status Response header: " + response.getStatusLine().getStatusCode());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace() ;
        }
    }
}