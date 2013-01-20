package com.share.PACManager;

import com.share.PACManager.params.StaticParams;

import android.R.string;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class RegActivity extends Activity {
	private EditText m_editPolice;
	private Button m_btnAddPolice;
	private Button m_btnAddCompany;
	private CheckBox m_chboxCompCheckout;
	private CheckBox m_chboxRentCheckout;
	private Spinner m_spnPersonType;
	private Spinner m_spnCardType;

	private TextView m_tag_Specinfo;
	private LinearWithFrame m_compLayout;
	private LinearWithFrame m_rentLayout;
	private LinearLayout    m_compCheckoutLayout;
	private LinearLayout    m_rentCheckoutLayout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reg_main);

		initWidget();

	}

	private void initWidget() {
		m_editPolice = (EditText) findViewById(R.id.edit_police);

		m_btnAddPolice = (Button) findViewById(R.id.btn_reg_police);
		m_btnAddCompany = (Button) findViewById(R.id.btn_reg_company);

		m_spnPersonType = (Spinner) findViewById(R.id.spinner_persontype);
		m_spnCardType = (Spinner) findViewById(R.id.spinner_cardtype);

		m_tag_Specinfo = (TextView) findViewById(R.id.tip_type_person_info);

		m_compLayout = (LinearWithFrame) findViewById(R.id.layout_company_person_info);
		m_rentLayout = (LinearWithFrame) findViewById(R.id.layout_rent_person_info);

		m_chboxCompCheckout = (CheckBox) findViewById(R.id.check_btn_autocheckout);
		m_chboxRentCheckout = (CheckBox) findViewById(R.id.check_btn_rent_autocheckout);
		
		
		m_compCheckoutLayout = (LinearLayout) findViewById(R.id.layout_comp_person_checkout);
		m_rentCheckoutLayout = (LinearLayout) findViewById(R.id.layout_rent_person_checkout);
		initSpinner();

		initWidgetEvent();
	}

	private void initWidgetEvent() {
		m_btnAddPolice.setOnClickListener(new AddNewPolice());
		m_btnAddCompany.setOnClickListener(new AddNewCompany());
		m_editPolice.setOnFocusChangeListener(new FocusOnEdit());
		m_spnPersonType.setOnItemSelectedListener(new PersonTypeSelChange());
		
		m_chboxCompCheckout.setOnCheckedChangeListener(new AutoCheckBoxListener(m_compCheckoutLayout));
		m_chboxRentCheckout.setOnCheckedChangeListener(new AutoCheckBoxListener(m_rentCheckoutLayout));
	}

	private void initSpinner() {
		ArrayAdapter<String> cardAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item,
				StaticParams.strCardType);
		m_spnCardType.setAdapter(cardAdapter);

		ArrayAdapter<String> personAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item,
				StaticParams.strPersonType);
		m_spnPersonType.setAdapter(personAdapter);

	}

	class AutoCheckBoxListener implements CheckBox.OnCheckedChangeListener
	{
		private LinearLayout m_affLayout;
		LayoutParams lparams;
		
		public AutoCheckBoxListener(LinearLayout affLayout)
		{
			m_affLayout = affLayout;
		}
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			lparams = m_affLayout.getLayoutParams();
			lparams.height = isChecked? 0 : lparams.WRAP_CONTENT;
			m_affLayout.setLayoutParams(lparams);			
		}
	}
	
	class AddNewPolice implements Button.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(RegActivity.this, RegPolice.class);
			startActivity(intent);
		}
	}

	class PersonTypeSelChange implements Spinner.OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			LayoutParams lparams;
			switch (arg2) {
			case 0:
				lparams = m_compLayout.getLayoutParams();
				lparams.height = lparams.WRAP_CONTENT;
				m_compLayout.setLayoutParams(lparams);
				
				m_tag_Specinfo.setText("企业人员信息");
				
				lparams = m_rentLayout.getLayoutParams();
				lparams.height = 0;
				m_rentLayout.setLayoutParams(lparams);
				
				break;
			case 1:
				
				lparams = m_compLayout.getLayoutParams();
				lparams.height = 0;
				m_compLayout.setLayoutParams(lparams);
				
				m_tag_Specinfo.setText("租赁人员信息");
				
				lparams = m_rentLayout.getLayoutParams();
				lparams.height = lparams.WRAP_CONTENT;
				m_rentLayout.setLayoutParams(lparams);
				break;
			case 2:
				lparams = m_compLayout.getLayoutParams();
				lparams.height = 0;
				m_compLayout.setLayoutParams(lparams);
				
				m_tag_Specinfo.setText("流动人员信息");
				lparams = m_rentLayout.getLayoutParams();
				lparams.height = 0;
				m_rentLayout.setLayoutParams(lparams);
				break;				
			default:
				break;
			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	class AddNewCompany implements Button.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(RegActivity.this, RegCompany.class);
			startActivity(intent);
		}

	}

	class FocusOnEdit implements EditText.OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			Bundle msgData = new Bundle();
			if (hasFocus) {

			}
		}
	}
}
