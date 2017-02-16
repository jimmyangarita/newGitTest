package com.jdap.auction.service.client.bid;

//~--- non-JDK imports ---------------------------------------------------------

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.logging.Logger;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * Class description
 *
 *
 * @version        9.0, 2012.March.27 05:41 PM
 * @author         Avocent Corporation
 */
public class ClientCustom2WaySSL
{
    /** Field description */
    private static Logger logger =
        Logger.getLogger( com.jdap.auction.service.client.bid
            .ClientCustom2WaySSL.class.getName() );    
	

    /**
     * Method description
     *
     *
     * @param args
     *
     * @throws Exception
     */
    public static void main( String[] args ) throws Exception
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        try
        {
            SSLContext ctx = SSLContext.getInstance( "SSL" );
            TrustManager[] trustManagers =
                getTrustManagers(
                    "jks",
                    new FileInputStream(
                        new File(
                            //"C:\\ssl\\opensslEngine\\engine-trust.jks" ) ), "changeit" );
                        	  "C:\\ssl\\52.140\\trelliskeys\\api-client.jks" ) ), "YjM5OThiNGFjNjY26" );
            KeyManager[] keyManagers =
                getKeyManagers(
                    "pkcs12",
                    new FileInputStream(
                        new File(
                            //"C:\\ssl\\opensslEngine\\mss-engine-priv.p12" ) ), "changeit" );
        					"C:\\ssl\\52.140\\trelliskeys\\mss-engine.p12" ) ), "YjM5OThiNGFjNjY26" );

            ctx.init( keyManagers, trustManagers, new SecureRandom() );

            SSLSocketFactory factory =
                new SSLSocketFactory( ctx, new StrictHostnameVerifier() );
            ClientConnectionManager manager = httpClient.getConnectionManager();

            manager.getSchemeRegistry().register( new Scheme( "https", 8012,
                                                              factory ) );

            InputStream is = ClientCustom2WaySSL.class.getResourceAsStream(
                                 "sample-JsonDataPoints.txt" );
            String jsontext = IOUtils.toString( is );
            HttpPost httppost =
                new HttpPost(
                   // "https://10.207.67.59:8012/hybrid/commonplatform/highdatapoints" );
                		"https://sun-tr-52140.systemtest.com:8012/hybrid/commonplatform/highdatapoints-v2");

            httppost.setEntity( new StringEntity( jsontext ) );
            logger.info( "Request: \n" + httppost.getRequestLine() );

            HttpResponse response = httpClient.execute( httppost );
            BufferedReader rd = new BufferedReader(
                                    new InputStreamReader(
                                        response.getEntity().getContent() ) );
            String line = "";

            logger.info( "Response: " );

            while( ( line = rd.readLine() ) != null )
            {
                logger.info( line );
            }
        }
        catch( IOException e )
        {
            logger.severe("IOException Occurred..." + e);
        }

        // httpClient.getConnectionManager().shutdown(); }
    }

    /**
     * Method description
     *
     *
     * @param keyStoreType
     * @param keyStoreFile
     * @param keyStorePassword
     *
     * @return
     *
     * @throws Exception
     */
    protected static
    KeyManager[] getKeyManagers( String keyStoreType,
                                 InputStream keyStoreFile,
                                 String keyStorePassword ) throws Exception
    {
        KeyStore keyStore = KeyStore.getInstance( keyStoreType );

        keyStore.load( keyStoreFile, keyStorePassword.toCharArray() );

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(
                                    KeyManagerFactory.getDefaultAlgorithm() );

        kmf.init( keyStore, keyStorePassword.toCharArray() );

        return kmf.getKeyManagers();
    }

    /**
     * Method description
     *
     *
     * @param trustStoreType
     * @param trustStoreFile
     * @param trustStorePassword
     *
     * @return
     *
     * @throws Exception
     */
    protected static
    TrustManager[] getTrustManagers( String trustStoreType,
                                     InputStream trustStoreFile,
                                     String trustStorePassword )
                                             throws Exception
    {
        KeyStore trustStore = KeyStore.getInstance( trustStoreType );

        trustStore.load( trustStoreFile, trustStorePassword.toCharArray() );

        TrustManagerFactory tmf =
            TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm() );

        tmf.init( trustStore );

        return tmf.getTrustManagers();
    }
}
