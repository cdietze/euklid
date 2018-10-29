/*
 * Copyright 2017 The Euklid Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



package euklid.f

/**
 * A plane consisting of a unit `normal` and a `constant`.
 *
 * All points on the plane satisfy the equation `Ax + By + Cz + D = 0`,
 * where (A, B, C) is the plane normal and D is the constant.
 */
data class Plane(
    override val normal: Vector3 = Vector3(),
    override var constant: Float = 0f
) : IPlane {

    /**
     * Creates a plane with the specified parameters.
     */
    constructor(values: FloatArray) : this(Vector3(values[0], values[1], values[2]), values[3])

    /**
     * Creates a plane with the specified parameters.
     */
    constructor(normal: IVector3, constant: Float) : this(normal.copy(), constant)

    /**
     * Creates a plane with the specified parameters.
     */
    constructor(a: Float, b: Float, c: Float, d: Float) : this(Vector3(a, b, c), d)

    /**
     * Copies the parameters of another plane.
     * @return a reference to this plane (for chaining).
     */
    fun set(other: Plane): Plane = set(other.normal, other.constant)

    /**
     * Sets the parameters of the plane.
     * @return a reference to this plane (for chaining).
     */
    fun set(normal: IVector3, constant: Float): Plane {
        return set(normal.x, normal.y, normal.z, constant)
    }

    /**
     * Sets the parameters of the plane.
     * @return a reference to this plane (for chaining).
     */
    fun set(values: FloatArray): Plane {
        return set(values[0], values[1], values[2], values[3])
    }

    /**
     * Sets the parameters of the plane.
     * @return a reference to this plane (for chaining).
     */
    fun set(a: Float, b: Float, c: Float, d: Float): Plane {
        normal.set(a, b, c)
        constant = d
        return this
    }

    /**
     * Sets this plane based on the three points provided.
     * @return a reference to the plane (for chaining).
     */
    fun fromPoints(p1: IVector3, p2: IVector3, p3: IVector3): Plane {
        // compute the normal by taking the cross product of the two vectors formed
        p2.subtract(p1, _v1)
        p3.subtract(p1, _v2)
        _v1.cross(_v2, normal).normalizeLocal()

        // use the first point to determine the constant
        constant = -normal.dot(p1)
        return this
    }

    /**
     * Sets this plane based on a point on the plane and the plane normal.
     * @return a reference to the plane (for chaining).
     */
    fun fromPointNormal(pt: IVector3, normal: IVector3): Plane = set(normal, -normal.dot(pt))

    // /**
    //  * Transforms this plane in-place by the specified transformation.
    //  *
    //  * @return a reference to this plane, for chaining.
    //  */
    // public Plane transformLocal (Transform3D transform) {
    //     return transform(transform, this);
    // }

    /**
     * Negates this plane in-place.
     * @return a reference to this plane, for chaining.
     */
    fun negateLocal(): Plane = negate(this)

    // @Override
    // public Plane transform (Transform3D transform) {
    //     return transform(transform, new Plane());
    // }

    // @Override
    // public Plane transform (Transform3D transform, Plane result) {
    //     transform.transformPointLocal(_normal.mult(-constant, _v1));
    //     transform.transformVector(_normal, _v2).normalizeLocal();
    //     return result.fromPointNormal(_v1, _v2);
    // }

    /** Working vectors for computation.  */
    private val _v1 = Vector3()
    private val _v2 = Vector3()

    companion object {
        /** The X/Y plane.  */
        val XY_PLANE = Plane(Vector3.UNIT_Z, 0f)

        /** The X/Z plane.  */
        val XZ_PLANE = Plane(Vector3.UNIT_Y, 0f)

        /** The Y/Z plane.  */
        val YZ_PLANE = Plane(Vector3.UNIT_X, 0f)
    }
}
