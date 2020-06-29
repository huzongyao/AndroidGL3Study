package com.hzy.gl3.study.renderer;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.hzy.gl3.study.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SimpleShapeRenderer implements GLSurfaceView.Renderer {

    private FloatBuffer mVertexBuffer;

    public SimpleShapeRenderer() {
        //分配内存空间,每个浮点型占4字节空间
        float[] vertexPoints = new float[]{
                0.0f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.4f, 0.0f
        };
        mVertexBuffer = ByteBuffer.allocateDirect(vertexPoints.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        //传入指定的坐标数据
        mVertexBuffer.put(vertexPoints);
        mVertexBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //设置背景颜色
        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
        //编译
        final int vertexShaderId = ShaderUtils.compileAssetVertexShader("simple_shape/vertex.glsl");
        final int fragmentShaderId = ShaderUtils.compileAssetFragmentShader("simple_shape/fragment.glsl");
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
        //准备坐标数据
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 0, mVertexBuffer);
        //启用顶点的句柄
        GLES30.glEnableVertexAttribArray(0);
        //绘制三角形
        GLES30.glDrawArrays(GLES30.GL_POINTS, 0, 3);
        GLES30.glDrawArrays(GLES30.GL_LINE_LOOP, 0, 3);
        GLES30.glLineWidth(4);
        //禁止顶点数组的句柄
        GLES30.glDisableVertexAttribArray(0);
    }
}
