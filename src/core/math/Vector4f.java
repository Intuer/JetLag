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
    public Vector4f mul(Matrix4f m) {

    }

    /**
     * TODO
     * Returns the product between this vector and a scalar.
     *
     * @param scalar to multiply by.
     * @return the product scalar * this.
     */
    public Vector4f mul(float scalar) {

    }

    /**
     * TODO
     * Returnes the sum between this and the specified vector.
     *
     * @param v vector to add.
     * @return the sum this + v.
     */
    public Vector4f add(Vector4f v) {

    }

    /**
     * TODO
     * Returnes the difference between this and the specified vector.
     *
     * @param v vector to subtract.
     * @return the difference this - v.
     */
    public Vector4f sub(Vector4f v) {

    }

    /**
     * TODO
     * Returns the dot product between this and the specified vector.
     *
     * @param v second vector.
     * @return the dot/inner product <this, v>
     */
    public Vector4f dot(Vector4f v) {

    }

    /**
     * TODO
     * Returns the length of this vector.
     *
     * @return the length of this vector.
     */
    public float length() {

    }

    /**
     * TODO
     * Normalized this vector.
     */
    public void normalize() {

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
}

