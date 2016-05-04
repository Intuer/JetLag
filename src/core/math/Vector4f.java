package core.math;

/**
 * @author Nicklas Hersen
 */
public class Vector4f {
    private float data[];

    /**
     * Creates a Vector4f initialized to 0.
     */
    public Vector4f() {
        data = new float[4];
    }

    /**
     * Creates a Vector4f initialized to the specified values.
     *
     * @param x first component.
     * @param y second component.
     * @param z third component.
     * @param w fourth component.
     */
    public Vector4f(float x, float y, float z, float w) {
        this();
        data[0] = x;
        data[1] = y;
        data[2] = z;
        data[3] = w;
    }

    /**
     * Creates a Vector4f from a float array.
     *
     * @param data data to copy.
     * @throw IllegalArgumentException if the specified arrays length isn't 4.
     */
    public Vector4f(float data[]) {
        if (data.length != 4) throw new IllegalArgumentException("Invalid array length!");

        this.data = data.clone();
    }

    /**
     * Copies an existing Vector4f.
     *
     * @param v vector to copy.
     */
    public Vector4f(Vector4f v) {
        data = v.data.clone();
    }

    /**
     * TODO
     * Returns the product between this vector and the specified matrix.
     *
     * @param m matrix to multiply this vector with.
     * @return the vector, matrix product this * m.
     */
    /**public Vector4f mul(Matrix4f m) {

    }*/

    /**
     * Returns the product between this vector and a scalar.
     *
     * @param scalar to multiply by.
     * @return the product scalar * this.
     */
    public Vector4f mul(float scalar) {
        Vector4f v = new Vector4f(this);
        v.data[0] = scalar*data[0];
        v.data[1] = scalar*data[1];
        v.data[2] = scalar*data[2];
        v.data[3] = scalar*data[3];

        return v;
    }

    /**
     * Returnes the sum between this and the specified vector.
     *
     * @param v vector to add.
     * @return the sum this + v.
     */
    public Vector4f add(Vector4f v) {
        Vector4f w = new Vector4f(this);
        w.data[0] = data[0] + v.data[0];
        w.data[1] = data[1] + v.data[1];
        w.data[2] = data[2] + v.data[2];
        w.data[3] = data[3] + v.data[3];

        return w;
    }

    /**
     * Returnes the difference between this and the specified vector.
     *
     * @param v vector to subtract.
     * @return the difference this - v.
     */
    public Vector4f sub(Vector4f v) {
        Vector4f w = new Vector4f(this);
        w.data[0] = data[0] - v.data[0];
        w.data[1] = data[1] - v.data[1];
        w.data[2] = data[2] - v.data[2];
        w.data[3] = data[3] - v.data[3];

        return w;
    }

    /**
     * Returns the dot product between this and the specified vector.
     *
     * @param v second vector.
     * @return the dot/inner product <this, v>
     */
    public float dot(Vector4f v) {
        return (data[0]*v.data[0])+(data[1]*v.data[1])+(data[2]*v.data[2])+(data[3]*v.data[3]);
    }

    /**
     * Returns the length of this vector.
     *
     * @return the length of this vector.
     */
    public float length() {
        return (float)Math.sqrt( (data[0]*data[0]) + (data[1]*data[1]) + (data[2]*data[2]) + (data[3]*data[3]) );
    }

    /**
     * Normalized this vector.
     */
    public void normalize() {
        float length = length();
        data[0] = data[0]/length;
        data[1] = data[1]/length;
        data[2] = data[2]/length;
        data[3] = data[3]/length;
    }

    /**
     * Returned a normalized copy of this vector
     *
     * @return a normalized copy of this vector.
     */
    public Vector4f normalized() {
        Vector4f res = new Vector4f(this);
        res.normalize();

        return res;
    }

    /**
     * Returns the data stored in the vector as a float array.
     *
     * @return a float array containing the vector data.
     */
    public float[] asFloatArray() {
        return data.clone();
    }
}

