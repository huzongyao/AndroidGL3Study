package com.hzy.gl3.study.renderer;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.hzy.gl3.study.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ColorShapeRenderer implements GLSurfaceView.Renderer {

    private final FloatBuffer mVertexBuffer;
    private final FloatBuffer mColorBuffer;

    public ColorShapeRenderer() {
        float[] vertexPoints = new float[]{
                0.0f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.4f, 0.0f
        };
        mVertexBuffer = ByteBuffer.allocateDirect(vertexPoints.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        mVertexBuffer.put(vertexPoints);
        mVertexBuffer.position(0);

        float[] color = {
                0.0f, 1.0f, 0.0f, 1.0f, //绿
                1.0f, 0.0f, 0.0f, 1.0f, //红
                0.0f, 0.0f, 1.0f, 1.0f //蓝
        };
        mColorBuffer = ByteBuffer.allocateDirect(color.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        //传入指定的数据
        mColorBuffer.put(color);
        mColorBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //设置背景颜色
        GLES30.glClearColor(0.9f, 0.9f, 0.9f, 1f);

        //编译
        final int vertexShaderId = ShaderUtils.compileAssetVertexShader("color_shape/vertex.glsl");
        final int fragmentShaderId = ShaderUtils.compileAssetFragmentShader("color_shape/fragment.glsl");
        //链接程序片段
        int program = ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);
        //在OpenGLES环境中使用程序片段
        GLES30.glUseProgram(program);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        //启用顶点的句柄
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glEnableVertexAttribArray(1);

        //准备坐标数据
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 0, mVertexBuffer);
        GLES30.glVertexAttribPointer(1, 4, GLES30.GL_FLOAT, false, 0, mColorBuffer);

        GLES30.glDrawArrays(GLES30.GL_POINTS, 0, 3);
        GLES30.glLineWidth(5);
        GLES30.glDrawArrays(GLES30.GL_LINE_LOOP, 0, 3);

        //禁止顶点数组的句柄
        GLES30.glDisableVertexAttribArray(0);
        GLES30.glDisableVertexAttribArray(1);
    }
}
