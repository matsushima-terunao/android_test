package com.example.sample;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private MyView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.view = new MyView(this);
		setContentView(view); // �A�N�e�B�r�e�B�[�Ƀr���[��ݒ�
	}

	@Override
	protected void onResume() {
		super.onResume();
		view.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		view.onPause();
	}
}
