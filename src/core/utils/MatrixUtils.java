package core.utils;

import core.math.Matrix4f;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * This class contains some extended matrix operations, such
 * as methods to generate translation, rotation and scaling matrices.
 *
 * @author Nicklas Hersen
 * @version 05.08.2016
 */
public class MatrixUtils {
    /**
     * Returns a matrix representation of the specified translation.
     *
     * @param x translation along the x-axis.
     * @param y translation along the y-axis.
     * @param z translation along the z-axis.
     * @return a matrix representation of the translation.
     */
    public static Matrix4f translate(float x, float y, float z) {
        float data[] = { 1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                x, y, z, 1.0f};

        return new Matrix4f(data);
    }

    /**
     * Returns a rotation matrix representing a rotation around the z-axis.
     *
     * @param amount rotation amount.
     * @return a matrix specifying the rotation.
     */
    public static  Matrix4f rotateRoll(float amount) {
        float s = (float) sin(amount);
        float c = (float) cos(amount);

        float data[] = {c, s, 0.0f, 0.0f,
                -s, c, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f};

        return new Matrix4f(data);
    }

    /**
     * Returns a rotation matrix representing a rotation around the x-axis.
     *
     * @param amount rotation amount.
     * @return a matrix specifying the rotation.
     */
    public static Matrix4f rotatePitch(float amount) {
        float s = (float) sin(amount);
        float c = (float) cos(amount);

        float data[] = { 1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, c, s, 0.0f,
                0.0f, -s, c, 1.0f,
                0.0f, 0.0f, 0.0f, 1.0f };

        return new Matrix4f(data);
    }

    /**
     * Returns a rotation matrix representing a rotation around the y-axis.
     *
     * @param amount rotation amount.
     * @return a matrix representing a rotation around the y-axis.
     */
    public static Matrix4f rotateYaw(float amount) {
        float s = (float) sin(amount);
        float c = (float) cos(amount);

        float data[] = {c, 0.0f, -s, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                s, 0.0f, c, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f };

        return new Matrix4f(data);
    }

    /**
     * Returns a matrix representing a rotationg around all axis.
     *
     * @param pitch rotation amount (x-axis).
     * @param yaw rotation amount (y-axis).
     * @param roll rotation amount (z-axis).
     * @return a matrix specifying a rotation around all axis.
     */
    public static Matrix4f rotate(float pitch, float yaw, float roll) {
        Matrix4f p = pitch == 0 ? new Matrix4f() : rotatePitch(pitch);
        Matrix4f y = yaw == 0 ? new Matrix4f() : rotateYaw(yaw);
        Matrix4f r = roll == 0 ? new Matrix4f() : rotateRoll(roll);

        return p.mul(y.mul(r));
    }

    /**
     * Returns a matrix representation of the scale operation.
     *
     * @param x scale amount on the x-axis.
     * @param y scale amount on the y-axis.
     * @param z scale amount on the z-axis.
     * @return a matrix representation of the scale operation.
     */
    public static Matrix4f scale(float x, float y, float z) {
        float data[] = {x, 0.0f, 0.0f, 0.0f,
            0.0f, y, 0.0f, 0.0f,
            0.0f, 0.0f, z, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f};
        return new Matrix4f(data);
    }
}
