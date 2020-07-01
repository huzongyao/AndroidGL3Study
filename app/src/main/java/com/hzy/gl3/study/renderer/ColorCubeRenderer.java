package com.hzy.gl3.study.renderer;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.hzy.gl3.study.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ColorCubeRenderer implements GLSurfaceView.Renderer {

    private final FloatBuffer vertexBuffer;
    private final FloatBuffer colorBuffer;

    public ColorCubeRenderer() {
        float[] vertexPoints = new float[]{
                //背面矩形
                0.75f, 0.75f, 0.0f, //V5
                -0.25f, 0.75f, 0.0f, //V6
                -0.25f, -0.25f, 0.0f, //V7
                0.75f, 0.75f, 0.0f, //V5
                -0.25f, -0.25f, 0.0f, //V7
                0.75f, -0.25f, 0.0f, //V4

                //左侧矩形
                -0.25f, 0.75f, 0.0f, //V6
                -0.75f, 0.25f, 0.0f, //V1
                -0.75f, -0.75f, 0.0f, //V2
                -0.25f, 0.75f, 0.0f, //V6
                -0.75f, -0.75f, 0.0f, //V2
                -0.25f, -0.25f, 0.0f, //V7

                //底部矩形
                0.75f, -0.25f, 0.0f, //V4
                -0.25f, -0.25f, 0.0f, //V7
                -0.75f, -0.75f, 0.0f, //V2
                0.75f, -0.25f, 0.0f, //V4
                -0.75f, -0.75f, 0.0f, //V2
                0.25f, -0.75f, 0.0f, //V3

                //正面矩形
                0.25f, 0.25f, 0.0f,  //V0
                -0.75f, 0.25f, 0.0f, //V1
                -0.75f, -0.75f, 0.0f, //V2
                0.25f, 0.25f, 0.0f,  //V0
                -0.75f, -0.75f, 0.0f, //V2
                0.25f, -0.75f, 0.0f, //V3

                //右侧矩形
                0.75f, 0.75f, 0.0f, //V5
                0.25f, 0.25f, 0.0f, //V0
                0.25f, -0.75f, 0.0f, //V3
                0.75f, 0.75f, 0.0f, //V5
                0.25f, -0.75f, 0.0f, //V3
                0.75f, -0.25f, 0.0f, //V4

                //顶部矩形
                0.75f, 0.75f, 0.0f, //V5
                -0.25f, 0.75f, 0.0f, //V6
                -0.75f, 0.25f, 0.0f, //V1
                0.75f, 0.75f, 0.0f, //V5
                -0.75f, 0.25f, 0.0f, //V1
                0.25f, 0.25f, 0.0f  //V0
        };
        float[] colors = {
                //背面矩形颜色
                1f, 0f, 1f, 1f,
                1f, 0f, 1f, 1f,
                1f, 0f, 1f, 1f,
                1f, 0f, 1f, 1f,
                1f, 0f, 1f, 1f,
                1f, 0f, 1f, 1f,

                //左侧矩形颜色
                0f, 1f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 1f, 0f, 1f,

                //底部矩形颜色
                1f, 0f, 0.5f, 1f,
                1f, 0f, 0.5f, 1f,
                1f, 0f, 0.5f, 1f,
                1f, 0f, 0.5f, 1f,
                1f, 0f, 0.5f, 1f,
                1f, 0f, 0.5f, 1f,

                //正面矩形颜色
                0.9f, 0.3f, 0.2f, 1f,
                0.2f, 0.3f, 0.2f, 1f,
                0.2f, 0.3f, 0.2f, 1f,
                0.9f, 0.3f, 0.2f, 1f,
                0.2f, 0.3f, 0.2f, 1f,
                0.2f, 0.3f, 0.2f, 1f,

                //右侧矩形颜色
                0.1f, 0.2f, 0.3f, 1f,
                0.1f, 0.2f, 0.3f, 1f,
                0.1f, 0.2f, 0.3f, 1f,
                0.1f, 0.2f, 0.3f, 1f,
                0.1f, 0.2f, 0.3f, 1f,
                0.1f, 0.2f, 0.3f, 1f,

                //顶部矩形颜色
                0.3f, 0.4f, 0.5f, 1f,
                0.3f, 0.9f, 0.5f, 1f,
                0.3f, 0.4f, 0.5f, 1f,
                0.3f, 0.4f, 0.5f, 1f,
                0.3f, 0.4f, 0.5f, 1f,
                0.3f, 0.4f, 0.5f, 1f
        };
        //分配内存空间,每个浮点型占4字节空间
        vertexBuffer = ByteBuffer.allocateDirect(vertexPoints.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        //传入指定的坐标数据
        vertexBuffer.put(vertexPoints);
        vertexBuffer.position(0);

        //分配内存空间,每个浮点型占4字节空间
        colorBuffer = ByteBuffer.allocateDirect(colors.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        //传入指定的数据
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //设置背景颜色
        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
        //编译
        final int vertexShaderId = ShaderUtils.compileAssetVertexShader("color_shape/vertex.glsl");
        final int fragmentShaderId = ShaderUtils.compileAssetFragmentShader("color_shape/fragment.glsl");
        //链接程序片段
        int mProgram = ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);
        //使用程序片段
        GLES30.glUseProgram(mProgram);

        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 0, vertexBuffer);
        //启用位置顶点属性
        GLES30.glEnableVertexAttribArray(0);

        GLES30.glVertexAttribPointer(1, 4, GLES30.GL_FLOAT, false, 0, colorBuffer);
        //启用颜色顶点属性
        GLES30.glEnableVertexAttribArray(1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 36);
    }
}
