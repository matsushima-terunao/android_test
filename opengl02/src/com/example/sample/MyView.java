package com.example.sample;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyView extends GLSurfaceView {

	public MyRenderer renderer;

	public MyView(Context context) {
		super(context);
		renderer = new MyRenderer();
		setRenderer(renderer); // �����_���[�̐ݒ�
	}
}
