package core.math;

/**
 * A vector with two elements of type float
 *
 * @author Nicklas Hersen
 * @author Jacob Sörme
 */
public class Vector2f {
    private float data[];

    /**
     * Creates a Vector2f initialized to 0.
     */
    public Vector2f() {
        data = new float[2];
    }

    /**
     * Creates a Vector2f initialized to the specified values.
     *
     * @param x first component.
     * @param y second component.
     */
    public Vector2f(float x, float y, float z, float w) {
        this();
        data[0] = x;
        data[1] = y;
    }

    /**
     * Creates a Vector2f from a float array.
     *
     * @param data data to copy.
     * @throw IllegalArgumentException if the specified arrays length isn't 2.
     */
    public Vector2f(float data[]) {
        if (data.length != 2) throw new IllegalArgumentException("Invalid array length!");

        this.data = data.clone();
    }

    /**
     * Copies an existing Vector2f.
     *
     * @param v vector to copy.
     */
    public Vector2f(Vector2f v) {
        data = v.data.clone();
    }

    /**
     * Returns the product between this vector and a scalar.
     *
     * @param scalar to multiply by.
     * @return the product scalar * this.
     */
    public Vector2f mul(float scalar) {
        Vector2f v = new Vector2f(this);
        v.data[0] = scalar*data[0];
        v.data[1] = scalar*data[1];

        return v;
    }

    /**
     * Returnes the sum between this and the specified vector.
     *
     * @param v vector to add.
     * @return the sum this + v.
     */
    public Vector2f add(Vector2f v) {
        Vector2f w = new Vector2f(this);
        w.data[0] = data[0] + v.data[0];
        w.data[1] = data[1] + v.data[1];

        return w;
    }

    /**
     * Returnes the difference between this and the specified vector.
     *
     * @param v vector to subtract.
     * @return the difference this - v.
     */
    public Vector2f sub(Vector2f v) {
        Vector2f w = new Vector2f(this);
        w.data[0] = data[0] - v.data[0];
        w.data[1] = data[1] - v.data[1];

        return w;
    }

    /**
     * Returns the dot product between this and the specified vector.
     *
     * @param v second vector.
     * @return the dot/inner product <this, v>
     */
    public float dot(Vector2f v) {
        return (data[0]*v.data[0])+(data[1]*v.data[1]);
    }

    /**
     * Returns the length of this vector.
     *
     * @return the length of this vector.
     */
    public float length() {
        return (float)Math.sqrt( (data[0]*data[0]) + (data[1]*data[1]) );
    }

    /**
     * Normalized this vector.
     */
    public void normalize() {
        float length = length();
        data[0] = data[0]/length;
        data[1] = data[1]/length;
    }

    /**
     * Returned a normalized copy of this vector
     *
     * @return a normalized copy of this vector.
     */
    public Vector2f normalized() {
        Vector2f res = new Vector2f(this);
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

