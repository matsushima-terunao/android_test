package com.example.sample;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRenderer implements Renderer {

	private MyModel model = new MyModel(); // ���f��

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnable(GL10.GL_DEPTH_TEST); // �f�v�X�o�b�t�@��L��
		gl.glDepthFunc(GL10.GL_LEQUAL); // �f�v�X�o�b�t�@��r: �ȉ�
//		gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_POSITION, new float[]{-20.0f, -20.0f, 100.0f, 1.0f}, 0); // �Ɩ��̈ʒu
//		gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_DIFFUSE, new float[]{1.0f, 1.0f, 1.0f, 1.0f}, 0); // �g�U��
//		gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_AMBIENT, new float[]{0.2f, 0.2f, 0.2f, 1.0f}, 0); // ����
//		gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_SPECULAR, new float[]{0.5f, 0.5f, 0.5f, 1.0f}, 0); // ���ʌ�
		gl.glEnable(GL10.GL_LIGHTING); // ���C�e�B���O�L��
		gl.glEnable(GL10.GL_LIGHT0); // ���C�e�B���O0�L��
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height); // �r���[�|�[�g�̐ݒ�
		gl.glMatrixMode(GL10.GL_PROJECTION); // �s��̑I��: �ˉe
		gl.glLoadIdentity(); // �P�ʍs��ɒu������
		GLU.gluPerspective(gl, 45f, (float)width / height, 1f, 50f); // ����̐ݒ�
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // �o�b�t�@�N���A: �J���[�o�b�t�@, �f�v�X�o�b�t�@
		gl.glMatrixMode(GL10.GL_MODELVIEW); // �s��̑I��: ���f���r���[
		gl.glLoadIdentity(); // �P�ʍs��ɒu������
		gl.glTranslatef(0, 0, -10f); // �ړ�: z����
		gl.glRotatef(30f, 0, 1, 0); // ��]: y��
		gl.glRotatef(30f, 1, 0, 0); // ��]: x��
		model.draw(gl);
	}
}
