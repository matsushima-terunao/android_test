package com.example.sample;

import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	/** ビュー */
	private MyView view;

	private static final int FP = LinearLayout.LayoutParams.FILL_PARENT;
	//private static final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;

	private interface SetValue<T> {
		public void setValue(T value);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // アクティビティーにビューを設定
		// myView
		this.view = new MyView(this);
		((LinearLayout)findViewById(R.id.layout)).addView(this.view, new LinearLayout.LayoutParams(FP, FP));
		// optionLayout
		LinkedList<ToggleButton> toggleButtons = new LinkedList<ToggleButton>();
		initOptionView(toggleButtons, R.id.toggleButtonPerspectiveFovy, R.id.editTextPerspectiveFovy, MyRenderer.perspectiveFovy, 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.perspectiveFovy = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonPerspectiveNearZ, R.id.editTextPerspectiveNearZ, MyRenderer.perspectiveNearZ, 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.perspectiveNearZ = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonPerspectiveFarZ, R.id.editTextPerspectiveFarZ, MyRenderer.perspectiveFarZ, 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.perspectiveFarZ = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLookAtEyeX, R.id.editTextLookAtEyeX, MyRenderer.lookAtEyeX, 100, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lookAtEyeX = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLookAtEyeY, R.id.editTextLookAtEyeY, MyRenderer.lookAtEyeY, 100, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lookAtEyeY = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLookAtEyeZ, R.id.editTextLookAtEyeZ, MyRenderer.lookAtEyeZ, 100, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lookAtEyeZ = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLookAtCenterX, R.id.editTextLookAtCenterX, MyRenderer.lookAtCenterX, 100, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lookAtCenterX = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLookAtCenterY, R.id.editTextLookAtCenterY, MyRenderer.lookAtCenterY, 100, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lookAtCenterY = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLookAtCenterZ, R.id.editTextLookAtCenterZ, MyRenderer.lookAtCenterZ, 100, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lookAtCenterZ = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLookAtUpperX, R.id.editTextLookAtUpperX, MyRenderer.lookAtUpperX, 100, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lookAtUpperX = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLookAtUpperY, R.id.editTextLookAtUpperY, MyRenderer.lookAtUpperY, 100, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lookAtUpperY = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLookAtUpperZ, R.id.editTextLookAtUpperZ, MyRenderer.lookAtUpperZ, 100, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lookAtUpperZ = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonTranslateX, R.id.editTextTranslateX, MyRenderer.translateX, 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.translateX = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonTranslateY, R.id.editTextTranslateY, MyRenderer.translateY, 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.translateY = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonTranslateZ, R.id.editTextTranslateZ, MyRenderer.translateZ, 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.translateZ = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonRotateX, R.id.editTextRotateX, MyRenderer.rotateX, 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.rotateX = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonRotateY, R.id.editTextRotateY, MyRenderer.rotateY, 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.rotateY = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonRotateZ, R.id.editTextRotateZ, MyRenderer.rotateZ, 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.rotateZ = value; }});
		initOptionCompoundButton(R.id.checkBoxLightPosition, MyRenderer.isLightPosition, new SetValue<Boolean>() { public void setValue(Boolean value) { MyRenderer.isLightPosition = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightPositionX, R.id.editTextLightPositionX, MyRenderer.lightPosition[0], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightPosition[0] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightPositionY, R.id.editTextLightPositionY, MyRenderer.lightPosition[1], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightPosition[1] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightPositionZ, R.id.editTextLightPositionZ, MyRenderer.lightPosition[2], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightPosition[2] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightPositionW, R.id.editTextLightPositionW, MyRenderer.lightPosition[3], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightPosition[3] = value; }});
		initOptionCompoundButton(R.id.checkBoxLightAmbient, MyRenderer.isLightAmbient, new SetValue<Boolean>() { public void setValue(Boolean value) { MyRenderer.isLightAmbient = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightAmbientR, R.id.editTextLightAmbientR, MyRenderer.lightAmbient[0], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightAmbient[0] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightAmbientG, R.id.editTextLightAmbientG, MyRenderer.lightAmbient[1], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightAmbient[1] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightAmbientB, R.id.editTextLightAmbientB, MyRenderer.lightAmbient[2], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightAmbient[2] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightAmbientA, R.id.editTextLightAmbientA, MyRenderer.lightAmbient[3], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightAmbient[3] = value; }});
		initOptionCompoundButton(R.id.checkBoxLightDiffuse, MyRenderer.isLightDiffuse, new SetValue<Boolean>() { public void setValue(Boolean value) { MyRenderer.isLightDiffuse = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightDiffuseR, R.id.editTextLightDiffuseR, MyRenderer.lightDiffuse[0], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightDiffuse[0] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightDiffuseG, R.id.editTextLightDiffuseG, MyRenderer.lightDiffuse[1], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightDiffuse[1] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightDiffuseB, R.id.editTextLightDiffuseB, MyRenderer.lightDiffuse[2], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightDiffuse[2] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightDiffuseA, R.id.editTextLightDiffuseA, MyRenderer.lightDiffuse[3], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightDiffuse[3] = value; }});
		initOptionCompoundButton(R.id.checkBoxLightSpecular, MyRenderer.isLightSpecular, new SetValue<Boolean>() { public void setValue(Boolean value) { MyRenderer.isLightSpecular = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightSpecularR, R.id.editTextLightSpecularR, MyRenderer.lightSpecular[0], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightSpecular[0] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightSpecularG, R.id.editTextLightSpecularG, MyRenderer.lightSpecular[1], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightSpecular[1] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightSpecularB, R.id.editTextLightSpecularB, MyRenderer.lightSpecular[2], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightSpecular[2] = value; }});
		initOptionView(toggleButtons, R.id.toggleButtonLightSpecularA, R.id.editTextLightSpecularA, MyRenderer.lightSpecular[3], 1, new SetValue<Float>() { public void setValue(Float value) { MyRenderer.lightSpecular[3] = value; }});
		initOptionCompoundButton(R.id.radioPlaneNormal, MyRenderer.planeNormal, new SetValue<Boolean>() { public void setValue(Boolean value) { MyRenderer.planeNormal = value; }});
		initOptionCompoundButton(R.id.radioVertexNormal, MyRenderer.vertexNormal, new SetValue<Boolean>() { public void setValue(Boolean value) { MyRenderer.vertexNormal = value; }});
		// seekBar -> editText
		((SeekBar)findViewById(R.id.seekBarCommon)).setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				Object[] tag = (Object[])seekBar.getTag();
				if (null != tag) {
					float value = (progress - seekBar.getMax() / 2) / (Float)tag[1];
					((EditText)tag[0]).setText("" + value);
				}
			}
		});
		// buttonSeekBarDec
		findViewById(R.id.buttonSeekBarDec).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((SeekBar)findViewById(R.id.seekBarCommon)).setProgress(((SeekBar)findViewById(R.id.seekBarCommon)).getProgress() - 1);
			}
		});
		// buttonSeekBarInc
		findViewById(R.id.buttonSeekBarInc).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((SeekBar)findViewById(R.id.seekBarCommon)).setProgress(((SeekBar)findViewById(R.id.seekBarCommon)).getProgress() + 1);
			}
		});
		// buttonOption
		findViewById(R.id.buttonOption).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				findViewById(R.id.optionLayout).setLayoutParams(new LinearLayout.LayoutParams(FP,
						0 == findViewById(R.id.optionLayout).getHeight() ? LinearLayout.LayoutParams.WRAP_CONTENT : 0));
			}
		});
		//findViewById(R.id.optionLayout).setLayoutParams(new LinearLayout.LayoutParams(FP, 0));
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

	/**
	 * option 項目の初期化。
	 * 
	 * @param toggleButtons
	 * @param idToggleButton
	 * @param idEditText
	 * @param value
	 * @param unit
	 * @param setValue
	 */
	private void initOptionView(final LinkedList<ToggleButton> toggleButtons, final int idToggleButton, final int idEditText, float value, final float unit, final SetValue<Float> setValue) {
		final ToggleButton toggleButton = (ToggleButton)findViewById(idToggleButton);
		final EditText editText = (EditText)findViewById(idEditText);
		final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBarCommon);
		editText.setText("" + value);
		// toggleButton
		toggleButtons.add(toggleButton);
		toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					// ほかを off
					for (ToggleButton t: toggleButtons) {
						if (t != buttonView) {
							t.setChecked(false);
						}
					}
					// seekBar を editText に関連付け
					try {
						seekBar.setTag(new Object[] {editText, unit});
						float value = Float.parseFloat(editText.getText().toString());
						seekBar.setProgress((int)(value * unit + seekBar.getMax() / 2));
					} catch (Exception e) {
					}
				}
			}
		});
		// editText -> seekBar, variable
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try {
					float value = Float.parseFloat(s.toString());
					seekBar.setProgress((int)(value * unit + seekBar.getMax() / 2));
					setValue.setValue(value);
					System.out.println(s);
					MyRenderer.optionChanged = true;
				} catch (Exception e) {
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		// editText -> toggleButton
		editText.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					toggleButton.setChecked(true);
				}
			}
		});
	}

	/**
	 * CompoundButton の初期化。
	 * 
	 * @param idCompoundButton
	 * @param value
	 * @param setValue
	 */
	private void initOptionCompoundButton(int idCompoundButton, boolean value, final SetValue<Boolean> setValue) {
		CompoundButton checkBox = (CompoundButton)findViewById(idCompoundButton);
		checkBox.setChecked(value);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				setValue.setValue(isChecked);
				MyRenderer.optionChanged =  true;
			}
		});
		
	}
}
