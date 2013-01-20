package com.share.PACManager.data;
import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;


public class CommonProvider extends ContentProvider {

    private static HashMap<String, String> sProjectionMap;

    private static final int SERVERS = 1;
    private static final int SERVERS_ID = 2;
    
    // 这里要增加匹配项
    private static final int LEADERS = 3;
    private static final int LEADERS_ID = 4;

    private static final UriMatcher sUriMatcher;

    private DatabaseHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String orderBy;
        switch (sUriMatcher.match(uri)) { // 这里要对不同表的匹配结果做不同处理
        case LEADERS:
        case LEADERS_ID:
        	qb.setTables(Provider.LeaderColumns.TABLE_NAME);
        	// If no sort order is specified use the default
        	if (TextUtils.isEmpty(sortOrder)) {
        		orderBy = Provider.LeaderColumns.DEFAULT_SORT_ORDER;
        	} else {
        		orderBy = sortOrder;
        	}
        	break;
        case SERVERS:
        case SERVERS_ID:
        	qb.setTables(Provider.ServerColumns.TABLE_NAME);
        	// If no sort order is specified use the default
        	if (TextUtils.isEmpty(sortOrder)) {
        		orderBy = Provider.ServerColumns.DEFAULT_SORT_ORDER;
        	} else {
        		orderBy = sortOrder;
        	}
        	break;
        default:
        	throw new IllegalArgumentException("Unknown URI " + uri);
        }

        switch (sUriMatcher.match(uri)) {
        case LEADERS:
        case SERVERS:
            qb.setProjectionMap(sProjectionMap);
            break;

        case SERVERS_ID:
            qb.setProjectionMap(sProjectionMap);
            qb.appendWhere(Provider.ServerColumns._ID + "=" + uri.getPathSegments().get(1));
            break;
            
        case LEADERS_ID:
        	qb.setProjectionMap(sProjectionMap);
        	qb.appendWhere(Provider.LeaderColumns._ID + "=" + uri.getPathSegments().get(1));
        	break;

        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // Get the database and run the query
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);

        // Tell the cursor what uri to watch, so it knows when its source data changes
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) { // 这里也要增加匹配项
        case LEADERS:
        case SERVERS:
            return Provider.CONTENT_TYPE;
        case SERVERS_ID:
        case LEADERS_ID:
            return Provider.CONTENT_ITEM_TYPE;
        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
    	ContentValues values;
    	if (initialValues != null) {
    		values = new ContentValues(initialValues);
    	} else {
    		values = new ContentValues();
    	}
    	
    	String tableName = "";
    	String nullColumn = "";
    	switch (sUriMatcher.match(uri)) { // 这里要对不同表的匹配结果做不同处理
    	case LEADERS:
    		tableName = Provider.LeaderColumns.TABLE_NAME;
    		nullColumn = Provider.LeaderColumns.NAME;
    		// Make sure that the fields are all set
            if (values.containsKey(Provider.LeaderColumns.NAME) == false) {
                values.put(Provider.LeaderColumns.NAME, "");
            }

            if (values.containsKey(Provider.LeaderColumns.TITLE) == false) {
                values.put(Provider.LeaderColumns.TITLE, "");
            }
            
            if (values.containsKey(Provider.LeaderColumns.LEVEL) == false) {
            	values.put(Provider.LeaderColumns.LEVEL, 0);
            }
    		break;
    	case SERVERS:
    		tableName = Provider.ServerColumns.TABLE_NAME;
    		nullColumn = Provider.ServerColumns.SERVER;
    		// Make sure that the fields are all set
            if (values.containsKey(Provider.ServerColumns.SERVER) == false) {
                values.put(Provider.ServerColumns.SERVER, "");
            }

            if (values.containsKey(Provider.ServerColumns.HTTPS) == false) {
                values.put(Provider.ServerColumns.HTTPS, 0);
            }
            
            if (values.containsKey(Provider.ServerColumns.PORT) == false) {
                values.put(Provider.ServerColumns.PORT, 80);
            }
            
    		break;
		default:
			// Validate the requested uri
			throw new IllegalArgumentException("Unknown URI " + uri);
    			
    	}

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long rowId = db.insert(tableName, nullColumn, values);
        if (rowId > 0) {
            Uri noteUri = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }

        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) { // 这里要对不同表的匹配结果做不同处理，注意下面用到的表名不要弄错了
        case SERVERS:
            count = db.delete(Provider.ServerColumns.TABLE_NAME, where, whereArgs);
            break;

        case SERVERS_ID:
            String programmerId = uri.getPathSegments().get(1);
            count = db.delete(Provider.ServerColumns.TABLE_NAME, Provider.ServerColumns._ID + "=" + programmerId
                    + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
            break;
            
        case LEADERS:
        	count = db.delete(Provider.LeaderColumns.TABLE_NAME, where, whereArgs);
        	break;
        	
        case LEADERS_ID:
        	String leaderId = uri.getPathSegments().get(1);
        	count = db.delete(Provider.LeaderColumns.TABLE_NAME, Provider.LeaderColumns._ID + "=" + leaderId
        			+ (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
        	break;

        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) { // 这里要对不同表的匹配结果做不同处理，注意下面用到的表名不要弄错了
        case SERVERS:
            count = db.update(Provider.ServerColumns.TABLE_NAME, values, where, whereArgs);
            break;

        case SERVERS_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.update(Provider.ServerColumns.TABLE_NAME, values, Provider.ServerColumns._ID + "=" + noteId
                    + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
            break;
        case LEADERS:
        	count = db.update(Provider.LeaderColumns.TABLE_NAME, values, where, whereArgs);
        	break;
        	
        case LEADERS_ID:
        	String leaderId = uri.getPathSegments().get(1);
        	count = db.update(Provider.LeaderColumns.TABLE_NAME, values, Provider.LeaderColumns._ID + "=" + leaderId
        			+ (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
        	break;

        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Provider.AUTHORITY, "server", SERVERS);
        sUriMatcher.addURI(Provider.AUTHORITY, "server/#", SERVERS_ID);
        
        // 这里要增加另一张表的匹配项
        sUriMatcher.addURI(Provider.AUTHORITY, "leaders", LEADERS);
        sUriMatcher.addURI(Provider.AUTHORITY, "leaders/#", LEADERS_ID);

        // 保存所有表用到的字段
        sProjectionMap = new HashMap<String, String>();
        sProjectionMap.put(Provider.ServerColumns._ID, Provider.ServerColumns._ID);
        sProjectionMap.put(Provider.ServerColumns.SERVER, Provider.ServerColumns.SERVER);
        sProjectionMap.put(Provider.ServerColumns.HTTPS, Provider.ServerColumns.HTTPS);
        sProjectionMap.put(Provider.ServerColumns.PORT, Provider.ServerColumns.PORT);
             
        sProjectionMap.put(Provider.LeaderColumns.TITLE, Provider.LeaderColumns.TITLE);
        sProjectionMap.put(Provider.LeaderColumns.LEVEL, Provider.LeaderColumns.LEVEL);
    }
}
