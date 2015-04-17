package com.example.sample;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRenderer implements Renderer {

	/** ���f�� */
	private MyModel model = new MyModel();

	/** �r���[�|�[�g */
	private int width, height;

	// option
	/** ���� */
	public static float perspectiveFovy = 45, perspectiveNearZ = 1, perspectiveFarZ = 100;
	/** ���_ */
	public static float lookAtEyeX = 0, lookAtEyeY = 0, lookAtEyeZ = 0;
	/** ���_ */
	public static float lookAtCenterX = 0, lookAtCenterY = 0, lookAtCenterZ = -1;
	/** ���_ */
	public static float lookAtUpperX = 0, lookAtUpperY = 1, lookAtUpperZ = 0;
	/** �ړ� */
	public static float translateX = 0, translateY = 0, translateZ = -10;
	/** ��] */
	public static float rotateX = 30, rotateY = 30, rotateZ = 0;
	/** �Ɩ��̈ʒu */
	public static boolean isLightPosition = true;
	/** �Ɩ��̈ʒu */
	public static float[] lightPosition = {-20.0f, 20.0f, 100.0f, 1.0f};
	/** ���� */
	public static boolean isLightAmbient = true;
	/** ���� */
	public static float[] lightAmbient = {0.0f, 0.5f, 0.0f, 1.0f};
	/** �g�U�� */
	public static boolean isLightDiffuse = true;
	/** �g�U�� */
	public static float[] lightDiffuse = {1.0f, 0.0f, 0.0f, 1.0f};
	/** ���ʌ� */
	public static boolean isLightSpecular = true;
	/** ���ʌ� */
	public static float[] lightSpecular = {0.0f, 0.0f, 1.0f, 1.0f};
	/** ������ */
	public static float[] lightZero = {0, 0, 0, 0};
	/** �ʖ@�� */
	public static boolean planeNormal = true;
	/** ���_�@�� */
	public static boolean vertexNormal = false;
	/** option ���ύX���ꂽ */
	public static volatile boolean optionChanged = false;

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnable(GL10.GL_DEPTH_TEST); // �f�v�X�o�b�t�@��L��
		gl.glDepthFunc(GL10.GL_LEQUAL); // �f�v�X�o�b�t�@��r: �ȉ�
		if (isLightPosition) {
			gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_POSITION, lightPosition, 0); // �Ɩ��̈ʒu
			gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_AMBIENT, isLightAmbient ? lightAmbient : lightZero, 0); // ����
			gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_DIFFUSE, isLightDiffuse ? lightDiffuse : lightZero, 0); // �g�U��
			gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_SPECULAR, isLightSpecular ? lightSpecular : lightZero, 0); // ���ʌ�
			gl.glEnable(GL10.GL_LIGHTING); // ���C�e�B���O�L��
			gl.glEnable(GL10.GL_LIGHT0); // ���C�e�B���O0�L��
		}
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		this.width = width;
		this.height = height;
		gl.glViewport(0, 0, width, height); // �r���[�|�[�g�̐ݒ�
		gl.glMatrixMode(GL10.GL_PROJECTION); // �s��̑I��: �ˉe
		gl.glLoadIdentity(); // �P�ʍs��ɒu������
		GLU.gluPerspective(gl, perspectiveFovy, (float)width / height, perspectiveNearZ, perspectiveFarZ); // ����̐ݒ�
		gl.glMatrixMode(GL10.GL_MODELVIEW); // �s��̑I��: ���f���r���[
		gl.glLoadIdentity(); // �P�ʍs��ɒu������
		GLU.gluLookAt(gl, lookAtEyeX, lookAtEyeY, lookAtEyeZ, lookAtCenterX, lookAtCenterY, lookAtCenterZ, lookAtUpperX, lookAtUpperY, lookAtUpperZ); // ���_�̐ݒ�
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // �o�b�t�@�N���A: �J���[�o�b�t�@, �f�v�X�o�b�t�@
		if (optionChanged) {
			optionChanged = false;
			gl.glMatrixMode(GL10.GL_PROJECTION); // �s��̑I��: �ˉe
			gl.glLoadIdentity(); // �P�ʍs��ɒu������
			GLU.gluPerspective(gl, perspectiveFovy, (float)width / height, perspectiveNearZ, perspectiveFarZ); // ����̐ݒ�
			gl.glMatrixMode(GL10.GL_MODELVIEW); // �s��̑I��: ���f���r���[
			gl.glLoadIdentity(); // �P�ʍs��ɒu������
			GLU.gluLookAt(gl, lookAtEyeX, lookAtEyeY, lookAtEyeZ, lookAtCenterX, lookAtCenterY, lookAtCenterZ, lookAtUpperX, lookAtUpperY, lookAtUpperZ); // ���_�̐ݒ�
			if (isLightPosition) {
				gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_POSITION, lightPosition, 0); // �Ɩ��̈ʒu
				gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_AMBIENT, isLightAmbient ? lightAmbient : lightZero, 0); // ����
				gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_DIFFUSE, isLightDiffuse ? lightDiffuse : lightZero, 0); // �g�U��
				gl.glLightfv(GL10.GL_LIGHT0,  GL10.GL_SPECULAR, isLightSpecular ? lightSpecular : lightZero, 0); // ���ʌ�
				gl.glEnable(GL10.GL_LIGHTING); // ���C�e�B���O�L��
				gl.glEnable(GL10.GL_LIGHT0); // ���C�e�B���O0�L��
			} else {
				gl.glDisable(GL10.GL_LIGHTING); // ���C�e�B���O����
				gl.glDisable(GL10.GL_LIGHT0); // ���C�e�B���O0����
			}
		}
		model.draw(gl);
	}
}
