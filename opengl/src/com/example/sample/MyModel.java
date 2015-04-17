package com.example.sample;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MyModel {

	private FloatBuffer vertexBuffer;

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
	}

	/**
	 * ���f����`��B
	 * 
	 * @param gl
	 */
	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CCW); // �S��: �����v���
		gl.glEnable(GL10.GL_CULL_FACE); // �Жʂ�\�����Ȃ�
		gl.glCullFace(GL10.GL_BACK); // ���ʂ�\�����Ȃ�
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY); // ���_�z���L��
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer); // xyz, float, padding �Ȃ�, vertexBuffer
		/* ���_�@���x�N�g��
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY); // �@���z���L��
		gl.glNormalPointer(GL10.GL_FLOAT, 0, vertexBuffer); // xyz, float, padding �Ȃ�, vertexBuffer
		/**/
		for (int face = 0; face < 6; ++ face) {
			//* �ʖ@���x�N�g��
			float vx1 = vertexBuffer.get((face * 4 + 1) * 3 + 0) - vertexBuffer.get((face * 4 + 0) * 3 + 0);
			float vx2 = vertexBuffer.get((face * 4 + 2) * 3 + 0) - vertexBuffer.get((face * 4 + 0) * 3 + 0);
			float vy1 = vertexBuffer.get((face * 4 + 1) * 3 + 1) - vertexBuffer.get((face * 4 + 0) * 3 + 1);
			float vy2 = vertexBuffer.get((face * 4 + 2) * 3 + 1) - vertexBuffer.get((face * 4 + 0) * 3 + 1);
			float vz1 = vertexBuffer.get((face * 4 + 1) * 3 + 2) - vertexBuffer.get((face * 4 + 0) * 3 + 2);
			float vz2 = vertexBuffer.get((face * 4 + 2) * 3 + 2) - vertexBuffer.get((face * 4 + 0) * 3 + 2);
			float nx = vy1 * vz2 - vy2 * vz1;
			float ny = vz1 * vx2 - vz2 * vx1;
			float nz = vx1 * vy2 - vx2 * vy1;
			float nr = (float)Math.sqrt(nx * nx + ny * ny + nz * nz);
			gl.glNormal3f(nx / nr, ny / nr, nz / nr); // �ʖ@���x�N�g��
			/**/
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, face * 4, 4); // 0120 1231? // �v���~�e�B�u��`��
		}
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); // ���_�z��𖳌�
		gl.glDisable(GL10.GL_CULL_FACE); // �Жʂ�\�����Ȃ��𖳌�
	}
}
