package core.math;

/**
 * For creating and working with matrices.
 * Supports all the standard methods.
 *
 * @author Nicklas Hersen
 * @author Jacob Sörme
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
     * Returns the transpose of the matrix.
     *
     * @return the transpose of this matrix.
     */
    public Matrix4f transpose() {
        Matrix4f m = new Matrix4f(this);
        for (int i = 0; i < 4; i++ ) {
            m.data[i + (0*4)] = data[i];
            m.data[i + (1*4)] = data[i+1];
            m.data[i + (2*4)] = data[i+2];
            m.data[i + (3*4)] = data[i+3];
        }
        return m;
    }

    /**
     * Returns the matrix product of this and the specified matrix.
     *
     * @return the matrix product (this * m).
     */
    public Matrix4f mul(Matrix4f m) {
        Matrix4f n = new Matrix4f();
        for(int i = 0; i < 4; i++){
            n.data[i] = data[i]*m.data[0] + data[i+4]*m.data[1] + data[i+8]*m.data[2] + data[i+12]*m.data[3];
            n.data[i+4] = data[i]*m.data[4] + data[i+4]*m.data[5] + data[i+8]*m.data[6] + data[i+12]*m.data[7];
            n.data[i+8] = data[i]*m.data[8] + data[i+4]*m.data[9] + data[i+8]*m.data[10] + data[i+12]*m.data[11];
            n.data[i+12] = data[i]*m.data[12] + data[i+4]*m.data[13] + data[i+8]*m.data[14] + data[i+12]*m.data[15];
        }
        return n;
    }

    /**
     * Returns product between the this matrix and the specified vector.
     *
     * @param v vector to multiply with.
     * @return the matrix vector product this * v.
     */
    public Vector4f mul(Vector4f v) {
        float[] vd = v.asFloatArray();
        float[] d = new float[4];
        d[0] = vd[0]*data[0] + vd[1]*data[1] + vd[2]*data[2] + vd[3]*data[3];
        d[1] = vd[0]*data[4] + vd[1]*data[5] + vd[2]*data[6] + vd[3]*data[7];
        d[2] = vd[0]*data[8] + vd[1]*data[9] + vd[2]*data[10] + vd[3]*data[11];
        d[3] = vd[0]*data[12] + vd[1]*data[13] + vd[2]*data[14] + vd[3]*data[15];
        Vector4f w = new Vector4f(d);
        return w;
    }

    /**
     * Returns the product of a matrix, scalar operation.
     *
     * @return the product (s * this).
     */
    public Matrix4f mul(float scalar) {
        Matrix4f m = new Matrix4f(this);
        for(int i = 0; i < 16; i++){
            m.data[i] = scalar*m.data[i];
        }
        return m;
    }

    /**
     * Returns the sum of the two specified matrices.
     *
     * @param m matrix term.
     * @return sum of this + m.
     */
    public Matrix4f add(Matrix4f m) {
        Matrix4f n = new Matrix4f(this);
        for(int i = 0; i < 16; i++){
            n.data[i] = n.data[i] + m.data[i];
        }
        return n;
    }

    /**
     * Returns the difference between the two specified matrices.
     *
     * @param m matrix term.
     * @return the difference this - m.
     */
    public Matrix4f sub(Matrix4f m) {
        Matrix4f n = new Matrix4f(this);
        for(int i = 0; i < 16; i++){
            n.data[i] = n.data[i] - m.data[i];
        }
        return n;
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
