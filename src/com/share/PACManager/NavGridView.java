package com.share.PACManager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class NavGridView extends GridView {

	private GridAdapter mAdapter;	
	private List<GridItem> mGridItems;
	private class GridItem
	{
		int id;
		int resId;
		String text;
	}
	
	private OnGridItemListener mOnGridItemListener;	
	public interface OnGridItemListener{
		public void onClickItem(int id);
	}	
	public void setOnGridItemListener(OnGridItemListener listener)
	{
		mOnGridItemListener = listener;
	}
	
	public NavGridView(Context context) {
		super(context);
		initGridView();
	}
	
	public NavGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGridView();
	}
	
	private void initGridView()
	{		
		mNumColumns = 3;
		mAdapter = new GridAdapter();
		mGridItems = new ArrayList<GridItem>();
		setAdapter(mAdapter);
		
		setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(mOnGridItemListener !=null && arg2 < mGridItems.size())
				{
					GridItem item = mGridItems.get(arg2);
					mOnGridItemListener.onClickItem(item.id);
				}
			}			
		});
	}
	
	private int mNumColumns;
	@Override
	public void setNumColumns(int numColumns) {
		// TODO Auto-generated method stub
		mNumColumns = numColumns;
		super.setNumColumns(numColumns);
	}
	
	public void addItem(int id, String text, int resId)
	{
		GridItem item = new GridItem();
		item.id = id;
		item.resId = resId;
		item.text = text;
		
		mGridItems.add(item);
		mAdapter.notifyDataSetChanged();	
		
		
	}
	
	private class GridAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mGridItems.size();
		}

		@Override
		public Object getItem(int position) {
			return mGridItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			return mGridItems.get(position).id;
		}
		
		private void showBorder(int position, View convertView)
		{
			View top = convertView.findViewById(R.id.id_border_top);
			View right = convertView.findViewById(R.id.id_border_right);
			
			
			int index = position % mNumColumns;
			switch(index)
			{
			case 0:
				top.setVisibility(View.INVISIBLE);
				right.setVisibility(View.VISIBLE);
				break;
			case 1:
				top.setVisibility(View.INVISIBLE);
				right.setVisibility(View.VISIBLE);
				break;	
			case 2:
				top.setVisibility(View.INVISIBLE);
				right.setVisibility(View.INVISIBLE);
				break;	
			}
			
			if(position < mNumColumns)
				top.setVisibility(View.VISIBLE);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null)
			{
				LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.nav_item, parent, false);
			}			
			
			GridItem item = mGridItems.get(position);
			
			TextView text = (TextView) convertView.findViewById(R.id.id_text);
			text.setText(item.text);
			ImageView icon = (ImageView) convertView.findViewById(R.id.id_icon);
			icon.setImageResource(item.resId);			
			
			showBorder(position, convertView);
			return convertView;
		}		
	};	
}
