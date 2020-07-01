package com.hzy.gl3.study.renderer;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.hzy.gl3.study.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class LineCubeRenderer implements GLSurfaceView.Renderer {

    private final FloatBuffer vertexBuffer;

    public LineCubeRenderer() {
        float[] vertexPoints = new float[]{
                0.25f, 0.25f, 0.0f,  //V0
                -0.75f, 0.25f, 0.0f, //V1
                -0.75f, -0.75f, 0.0f, //V2
                0.25f, -0.75f, 0.0f, //V3

                0.75f, -0.25f, 0.0f, //V4
                0.75f, 0.75f, 0.0f, //V5
                -0.25f, 0.75f, 0.0f, //V6
                -0.25f, -0.25f, 0.0f, //V7

                -0.25f, 0.75f, 0.0f, //V6
                -0.75f, 0.25f, 0.0f, //V1
                0.75f, 0.75f, 0.0f, //V5
                0.25f, 0.25f, 0.0f, //V0

                -0.25f, -0.25f, 0.0f, //V7
                -0.75f, -0.75f, 0.0f, //V2
                0.75f, -0.25f, 0.0f, //V4
                0.25f, -0.75f, 0.0f //V3
        };
        //分配内存空间,每个浮点型占4字节空间
        vertexBuffer = ByteBuffer.allocateDirect(vertexPoints.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        //传入指定的坐标数据
        vertexBuffer.put(vertexPoints);
        vertexBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //设置背景颜色
        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
        //编译
        final int vertexShaderId = ShaderUtils.compileAssetVertexShader("simple_shape/vertex.glsl");
        final int fragmentShaderId = ShaderUtils.compileAssetFragmentShader("simple_shape/fragment.glsl");
        //链接程序片段
        int mProgram = ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);
        //使用程序片段
        GLES30.glUseProgram(mProgram);
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 0, vertexBuffer);
        GLES30.glEnableVertexAttribArray(0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        //指定线宽
        GLES30.glLineWidth(5);
        GLES30.glDrawArrays(GLES30.GL_LINE_LOOP, 0, 4);
        GLES30.glDrawArrays(GLES30.GL_LINE_LOOP, 4, 4);
        GLES30.glDrawArrays(GLES30.GL_LINES, 8, 8);
    }
}
