package com.example.sample;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRenderer implements Renderer {

	private MyModel model = new MyModel(); // モデル

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnable(GL10.GL_DEPTH_TEST); // デプスバッファを有効
		gl.glDepthFunc(GL10.GL_LEQUAL); // デプスバッファ比較: 以下
//		gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_POSITION, new float[]{-20.0f, -20.0f, 100.0f, 1.0f}, 0); // 照明の位置
//		gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_DIFFUSE, new float[]{1.0f, 1.0f, 1.0f, 1.0f}, 0); // 拡散光
//		gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_AMBIENT, new float[]{0.2f, 0.2f, 0.2f, 1.0f}, 0); // 環境光
//		gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_SPECULAR, new float[]{0.5f, 0.5f, 0.5f, 1.0f}, 0); // 鏡面光
		gl.glEnable(GL10.GL_LIGHTING); // ライティング有効
		gl.glEnable(GL10.GL_LIGHT0); // ライティング0有効
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height); // ビューポートの設定
		gl.glMatrixMode(GL10.GL_PROJECTION); // 行列の選択: 射影
		gl.glLoadIdentity(); // 単位行列に置き換え
		GLU.gluPerspective(gl, 45f, (float)width / height, 1f, 50f); // 視野の設定
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // バッファクリア: カラーバッファ, デプスバッファ
		gl.glMatrixMode(GL10.GL_MODELVIEW); // 行列の選択: モデルビュー
		gl.glLoadIdentity(); // 単位行列に置き換え
		gl.glTranslatef(0, 0, -10f); // 移動: z方向
		gl.glRotatef(30f, 0, 1, 0); // 回転: y軸
		gl.glRotatef(30f, 1, 0, 0); // 回転: x軸
		model.draw(gl);
	}
}
