package com.share.PACManager.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class Server 
{
	// 服务器地址
	public String server = "";
	// 是否为https方式
	public int https = 0;
	// 端口
	public int port = 80;
	
	@Override
	public String toString() {
		String http = https != 0 ? "https" : "http";
		return String.format("%s//%s:%d", http, server, port);
	}
	
	public boolean query(Context context)
	{
		try{
			ContentResolver resolver = context.getContentResolver();
			Cursor c = resolver.query(Provider.ServerColumns.CONTENT_URI, 
									new String[]{Provider.ServerColumns.SERVER, Provider.ServerColumns.HTTPS, Provider.ServerColumns.PORT},null, null, null);
	    	if (c != null && c.moveToFirst()) {
	    		server = c.getString(c.getColumnIndexOrThrow(Provider.ServerColumns.SERVER));
	    		https = c.getInt(c.getColumnIndexOrThrow(Provider.ServerColumns.HTTPS));
	    		port = c.getInt(c.getColumnIndexOrThrow(Provider.ServerColumns.PORT));
	    		return true;
	    	}
		}catch(Exception e){
			e.toString();
		}
		
		return false;
	}
	
	public boolean update(Context context)
	{
		try{
			ContentValues values = new ContentValues();  
			values.put(Provider.ServerColumns.SERVER, server);  
			values.put(Provider.ServerColumns.HTTPS, https); 
			values.put(Provider.ServerColumns.PORT, port);    
			
			ContentResolver resolver = context.getContentResolver();
			resolver.insert(Provider.ServerColumns.CONTENT_URI, values);
			return true;
		} catch(Exception e){
			
		}		
		return false;
	}
}
