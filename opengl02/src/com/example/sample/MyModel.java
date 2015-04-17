package com.example.sample;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MyModel {

	/** �v���~�e�B�u�̒��_���W */
	private static final float[] vertices = {
		// front
		-1.0f, -1.0f,  1.0f,
		 1.0f, -1.0f,  1.0f,
		-1.0f,  1.0f,  1.0f,
		 1.0f,  1.0f,  1.0f,
		// back
		 1.0f, -1.0f, -1.0f,
		-1.0f, -1.0f, -1.0f,
		 1.0f,  1.0f, -1.0f,
		-1.0f,  1.0f, -1.0f,
		// left
		-1.0f, -1.0f, -1.0f,
		-1.0f, -1.0f,  1.0f,
		-1.0f,  1.0f, -1.0f,
		-1.0f,  1.0f,  1.0f,
		// right
		 1.0f, -1.0f,  1.0f,
		 1.0f, -1.0f, -1.0f,
		 1.0f,  1.0f,  1.0f,
		 1.0f,  1.0f, -1.0f,
		// top
		-1.0f,  1.0f,  1.0f,
		 1.0f,  1.0f,  1.0f,
		-1.0f,  1.0f, -1.0f,
		 1.0f,  1.0f, -1.0f,
		// bottom
		-1.0f, -1.0f, -1.0f,
		 1.0f, -1.0f, -1.0f,
		-1.0f, -1.0f,  1.0f,
		 1.0f, -1.0f,  1.0f,
	};

	/** ���_�o�b�t�@ */
	private static FloatBuffer vertexBuffer;
	/** �ʖ@���x�N�g�� */
	private static float[] normalVectors;

	/**
	 * ���f�����\�z�B
	 */
	public MyModel() {
		// ���_��`�� FloatBuffer ���쐬
		ByteBuffer buf = ByteBuffer.allocateDirect(vertices.length * 4);
		buf.order(ByteOrder.nativeOrder());
		vertexBuffer = buf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		// �ʖ@���x�N�g��
		normalVectors = new float[6 * 3];
		for (int face = 0; face < 6; ++ face) {
			float vx1 = vertices[(face * 4 + 1) * 3 + 0] - vertices[face * 4 * 3 + 0];
			float vx2 = vertices[(face * 4 + 2) * 3 + 0] - vertices[face * 4 * 3 + 0];
			float vy1 = vertices[(face * 4 + 1) * 3 + 1] - vertices[face * 4 * 3 + 1];
			float vy2 = vertices[(face * 4 + 2) * 3 + 1] - vertices[face * 4 * 3 + 1];
			float vz1 = vertices[(face * 4 + 1) * 3 + 2] - vertices[face * 4 * 3 + 2];
			float vz2 = vertices[(face * 4 + 2) * 3 + 2] - vertices[face * 4 * 3 + 2];
			float nx = vy1 * vz2 - vy2 * vz1;
			float ny = vz1 * vx2 - vz2 * vx1;
			float nz = vx1 * vy2 - vx2 * vy1;
			float nr = (float)Math.sqrt(nx * nx + ny * ny + nz * nz);
			normalVectors[face * 3 + 0] = nx / nr;
			normalVectors[face * 3 + 1] = ny / nr;
			normalVectors[face * 3 + 2] = nz / nr;
		}
	}

	/**
	 * ���f����`��B
	 * 
	 * @param gl
	 */
	public void draw(GL10 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(MyRenderer.translateX, MyRenderer.translateY, MyRenderer.translateZ); // �ړ�
		gl.glRotatef(MyRenderer.rotateX, 1, 0, 0); // ��]: x��
		gl.glRotatef(MyRenderer.rotateY, 0, 1, 0); // ��]: y��
		gl.glRotatef(MyRenderer.rotateZ, 0, 0, 1); // ��]: z��
		gl.glFrontFace(GL10.GL_CCW); // �S��: �����v���
		gl.glEnable(GL10.GL_CULL_FACE); // �Жʂ�\�����Ȃ�
		gl.glCullFace(GL10.GL_BACK); // ���ʂ�\�����Ȃ�
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY); // ���_�z���L��
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer); // xyz, float, padding �Ȃ�, vertexBuffer
		if (MyRenderer.vertexNormal) {
			gl.glEnableClientState(GL10.GL_NORMAL_ARRAY); // �@���z���L��
			gl.glNormalPointer(GL10.GL_FLOAT, 0, vertexBuffer); // xyz, float, padding �Ȃ�, vertexBuffer
		} else {
			gl.glDisableClientState(GL10.GL_NORMAL_ARRAY); // �@���z��𖳌�
		}
		for (int face = 0; face < 6; ++ face) {
			if (MyRenderer.planeNormal) {
				gl.glNormal3f(normalVectors[face * 3 + 0], normalVectors[face * 3 + 1], normalVectors[face * 3 + 2]); // �ʖ@���x�N�g��
			}
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, face * 4, 4); // 0120 1231 // �v���~�e�B�u��`��
		}
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); // ���_�z��𖳌�
		gl.glDisable(GL10.GL_CULL_FACE); // �Жʂ�\�����Ȃ��𖳌�
		gl.glPopMatrix();
	}
}
