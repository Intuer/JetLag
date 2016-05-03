package core.math;

/**
 * @author Nicklas Hersen
 */
public class Matrix4f {
    private float data[];

    /**
     * Creates an identity matrix.
     */
    public Matrix4f() {
        this(1.0f);
    }

    /**
     * Creates an matrix with a specified diagonal.
     *
     * @param diagonal diagonal value
     */
    public Matrix4f(float diagonal) {
        data = new float[4 * 4];

        for (int i = 0; i < 4; i++) {
            data[i + i * 4] = diagonal;
        }
    }

    /**
     * Creates a 4x4 matrix from a 4x4 float array
     *
     * @param data matrix data.
     * @throw IllegalArgumentException if the length of the specified array isn't 16.
     */
    public Matrix4f(float data[]) {
        if (data.length != 4 * 4) throw new IllegalArgumentException("Invalid array length!");

        this.data = data.clone();
    }

    /**
     * Copies an existing matrix.
     *
     * @param m matrix to copy.
     */
    public Matrix4f(Matrix4f m) {
        data = m.data.clone();
    }

    /**
     * TODO
     * Returns the transpose of the matrix.
     *
     * @return the transpose of this matrix.
     */
    public Matrix4f transpose() {

    }

    /**
     * TODO
     * Returns the matrix product of this and the specified matrix.
     *
     * @return the matrix product (this * m).
     */
    public Matrix4f mul(Matrix4f m) {

    }

    /**
     * TODO
     * Returns product between the this matrix and the specified vector.
     *
     * @param v vector to multiply with.
     * @return the matrix vector product this * v.
     */
    public Vector4f mul(Vector4f v) {

    }

    /**
     * TODO
     * Returns the product of a matrix, scalar operation.
     *
     * @return the product (s * this).
     */
    public Matrix4f mul(float scalar) {

    }

    /**
     * TODO
     * Returns the sum of the two specified matrices.
     *
     * @param m matrix term.
     * @return sum of this + m.
     */
    public Matrix4f add(Matrix4f m) {

    }

    /**
     * TODO
     * Returns the difference between the two specified matrices.
     *
     * @param m matrix term.
     * @return the difference this - m.
     */
    public Matrix4f sub(Matrix4f m) {

    }

    /**
     * Returns the data stored in the matrix as a float array.
     *
     * @return a float array containing the matrix data.
     */
    public float[] asFloatArray() {
        return data.clone();
    }
}
