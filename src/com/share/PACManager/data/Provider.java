package com.share.PACManager.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * �������ݿ��еĳ���
 * @author jacp
 *
 */
public class Provider {
	
	// ����ֻ��һ��authority
	public static final String AUTHORITY = "com.share.PACManager.data";
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.share.pac";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.share.pac";

    /**
     * ����programmer�����õ��ĳ���
     * @author jacp
     *
     */
	public static final class ServerColumns implements BaseColumns {
		// ע������ط�������LeaderColumns����CONTENT_URIһ�����õ���ͬһ��AUTHORITY
		public static final Uri CONTENT_URI = Uri.parse("content://"+ AUTHORITY +"/server");
		public static final String TABLE_NAME = "server";
		public static final String DEFAULT_SORT_ORDER = "_id desc";
		
		public static final String SERVER = "server";
		public static final String HTTPS = "https";
		public static final String PORT = "port";
		
	}
	
	/**
	 * ����leader�����õ��ĳ���
	 * @author mayliang
	 *
	 */
	public static final class LeaderColumns implements BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://"+ AUTHORITY +"/leaders");
		public static final String TABLE_NAME = "leader";
		public static final String DEFAULT_SORT_ORDER = "level desc";
		
		public static final String NAME = "name";
		public static final String TITLE = "title";
		public static final String LEVEL = "level";
		
	}
	
}
