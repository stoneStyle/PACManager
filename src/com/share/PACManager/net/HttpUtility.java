package com.share.PACManager.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpUtility {

	public void doHttpGet(String url, String method) 
	{
		HttpGet httpRequest = new HttpGet(url);
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doHttpPost(String url)
	{
        HttpPost httpRequest = new HttpPost(url); 
        List <NameValuePair> params = new ArrayList <NameValuePair>(); 
        params.add(new BasicNameValuePair("str", "I am Post String")); 
        try 
        { 
          /*发出HTTP request*/
          httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); 
          /*取得HTTP response*/
          HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); 
          /*若状态码为200 ok*/
          if(httpResponse.getStatusLine().getStatusCode() == 200)  
          { 
            /*取出响应字符串*/
            String strResult = EntityUtils.toString(httpResponse.getEntity()); 
          } 
        } 
        catch (ClientProtocolException e) 
        {  
          e.printStackTrace(); 
        } 
        catch (IOException e) 
        {  
          e.printStackTrace(); 
        } 
        catch (Exception e) 
        {  
          e.printStackTrace();  
        }  
	}
}
