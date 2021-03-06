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

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * An axis-aligned box.
 *
 * TODO(cdi) make this into a data class
 */
class Box : IBox {

    /**
     * Creates a box with the values contained in the supplied minimum and maximum extents.
     */
    constructor(minExtent: IVector3, maxExtent: IVector3) {
        set(minExtent, maxExtent)
    }

    /**
     * Copy constructor.
     */
    constructor(other: IBox) {
        set(other)
    }

    /**
     * Creates an empty box.
     */
    constructor() {
        setToEmpty()
    }

    /**
     * Sets the parameters of the box to the empty values ([Vector3.MAX_VALUE] and
     * [Vector3.MIN_VALUE] for the minimum and maximum, respectively).
     * @return a reference to this box, for chaining.
     */
    fun setToEmpty(): Box {
        return set(Vector3.MAX_VALUE, Vector3.MIN_VALUE)
    }

    /**
     * Copies the parameters of another box.
     * @return a reference to this box, for chaining.
     */
    fun set(other: IBox): Box {
        return set(other.minimumExtent(), other.maximumExtent())
    }

    /**
     * Sets the box parameters to the values contained in the supplied vectors.
     * @return a reference to this box, for chaining.
     */
    fun set(minExtent: IVector3, maxExtent: IVector3): Box {
        _minExtent.set(minExtent)
        _maxExtent.set(maxExtent)
        return this
    }

    /**
     * Initializes this box with the extents of an array of points.
     * @return a reference to this box, for chaining.
     */
    fun fromPoints(vararg points: IVector3): Box {
        setToEmpty()
        for (point in points) {
            addLocal(point)
        }
        return this
    }

    /**
     * Expands this box in-place to include the specified point.
     * @return a reference to this box, for chaining.
     */
    fun addLocal(point: IVector3): Box {
        return add(point, this)
    }

    /**
     * Expands this box to include the bounds of another box.
     * @return a reference to this box, for chaining.
     */
    fun addLocal(other: IBox): Box {
        return add(other, this)
    }

    /**
     * Finds the intersection between this box and another box and places the result in this box.
     * @return a reference to this box, for chaining.
     */
    fun intersectLocal(other: IBox): Box {
        return intersect(other, this)
    }

    // /**
    //  * Transforms this box in-place.
    //  *
    //  * @return a reference to this box, for chaining.
    //  */
    // public Box transformLocal (Transform3D transform) {
    //     return transform(transform, this);
    // }

    /**
     * Projects this box in-place.
     * @return a reference to this box, for chaining.
     */
    fun projectLocal(matrix: IMatrix4): Box {
        return project(matrix, this)
    }

    /**
     * Expands the box in-place by the specified amounts.
     * @return a reference to this box, for chaining.
     */
    fun expandLocal(x: Float, y: Float, z: Float): Box {
        return expand(x, y, z, this)
    }

    override fun minimumExtent(): IVector3 {
        return _minExtent
    }

    override fun maximumExtent(): IVector3 {
        return _maxExtent
    }

    override fun center(): Vector3 {
        return center(Vector3())
    }

    override fun center(result: Vector3): Vector3 {
        return _minExtent.add(_maxExtent, result).multLocal(0.5f)
    }

    override fun diagonalLength(): Float {
        return _minExtent.distance(_maxExtent)
    }

    override fun longestEdge(): Float {
        return max(max(_maxExtent.x - _minExtent.x, _maxExtent.y - _minExtent.y),
                _maxExtent.z - _minExtent.z)
    }

    override val isEmpty: Boolean
        get() = _minExtent.x > _maxExtent.x || _minExtent.y > _maxExtent.y ||
                _minExtent.z > _maxExtent.z

    override fun add(point: IVector3): Box {
        return add(point, Box())
    }

    override fun add(point: IVector3, result: Box): Box {
        result._minExtent.set(
                min(_minExtent.x, point.x),
                min(_minExtent.y, point.y),
                min(_minExtent.z, point.z))
        result._maxExtent.set(
                max(_maxExtent.x, point.x),
                max(_maxExtent.y, point.y),
                max(_maxExtent.z, point.z))
        return result
    }

    override fun add(other: IBox): Box {
        return add(other, Box())
    }

    override fun add(other: IBox, result: Box): Box {
        val omin = other.minimumExtent()
        val omax = other.maximumExtent()
        result._minExtent.set(
                min(_minExtent.x, omin.x),
                min(_minExtent.y, omin.y),
                min(_minExtent.z, omin.z))
        result._maxExtent.set(
                max(_maxExtent.x, omax.x),
                max(_maxExtent.y, omax.y),
                max(_maxExtent.z, omax.z))
        return result
    }

    override fun intersect(other: IBox): Box {
        return intersect(other, Box())
    }

    override fun intersect(other: IBox, result: Box): Box {
        val omin = other.minimumExtent()
        val omax = other.maximumExtent()
        result._minExtent.set(
                max(_minExtent.x, omin.x),
                max(_minExtent.y, omin.y),
                max(_minExtent.z, omin.z))
        result._maxExtent.set(
                min(_maxExtent.x, omax.x),
                min(_maxExtent.y, omax.y),
                min(_maxExtent.z, omax.z))
        return result
    }

    // /**
    //  * Transforms this box.
    //  *
    //  * @return a new box containing the result.
    //  */
    // public Box transform (Transform3D transform) {
    //     return transform(transform, new Box());
    // }

    // /**
    //  * Transforms this box, placing the result in the provided object.
    //  *
    //  * @return a reference to the result box, for chaining.
    //  */
    // public Box transform (Transform3D transform, Box result) {
    //     // the corners of the box cover the eight permutations of ([minX|maxX], [minY|maxY],
    //     // [minZ|maxZ]). To find the new minimum and maximum for each element, we transform
    //     // selecting either the minimum or maximum for each component based on whether it will
    //     // increase or decrease the total (which depends on the sign of the matrix element).
    //     transform.update(Transform3D.AFFINE);
    //     Matrix4f matrix = transform.matrix();
    //     float minx =
    //         matrix.m00 * (matrix.m00 > 0f ? _minExtent.x : _maxExtent.x) +
    //         matrix.m10 * (matrix.m10 > 0f ? _minExtent.y : _maxExtent.y) +
    //         matrix.m20 * (matrix.m20 > 0f ? _minExtent.z : _maxExtent.z) + matrix.m30;
    //     float miny =
    //         matrix.m01 * (matrix.m01 > 0f ? _minExtent.x : _maxExtent.x) +
    //         matrix.m11 * (matrix.m11 > 0f ? _minExtent.y : _maxExtent.y) +
    //         matrix.m21 * (matrix.m21 > 0f ? _minExtent.z : _maxExtent.z) + matrix.m31;
    //     float minz =
    //         matrix.m02 * (matrix.m02 > 0f ? _minExtent.x : _maxExtent.x) +
    //         matrix.m12 * (matrix.m12 > 0f ? _minExtent.y : _maxExtent.y) +
    //         matrix.m22 * (matrix.m22 > 0f ? _minExtent.z : _maxExtent.z) + matrix.m32;
    //     float maxx =
    //         matrix.m00 * (matrix.m00 < 0f ? _minExtent.x : _maxExtent.x) +
    //         matrix.m10 * (matrix.m10 < 0f ? _minExtent.y : _maxExtent.y) +
    //         matrix.m20 * (matrix.m20 < 0f ? _minExtent.z : _maxExtent.z) + matrix.m30;
    //     float maxy =
    //         matrix.m01 * (matrix.m01 < 0f ? _minExtent.x : _maxExtent.x) +
    //         matrix.m11 * (matrix.m11 < 0f ? _minExtent.y : _maxExtent.y) +
    //         matrix.m21 * (matrix.m21 < 0f ? _minExtent.z : _maxExtent.z) + matrix.m31;
    //     float maxz =
    //         matrix.m02 * (matrix.m02 < 0f ? _minExtent.x : _maxExtent.x) +
    //         matrix.m12 * (matrix.m12 < 0f ? _minExtent.y : _maxExtent.y) +
    //         matrix.m22 * (matrix.m22 < 0f ? _minExtent.z : _maxExtent.z) + matrix.m32;
    //     result._minExtent.set(minx, miny, minz);
    //     result._maxExtent.set(maxx, maxy, maxz);
    //     return result;
    // }

    override fun project(matrix: IMatrix4): Box {
        return project(matrix, Box())
    }

    override fun project(matrix: IMatrix4, result: Box): Box {
        var minx = +Float.MAX_VALUE
        var miny = +Float.MAX_VALUE
        var minz = +Float.MAX_VALUE
        var maxx = -Float.MAX_VALUE
        var maxy = -Float.MAX_VALUE
        var maxz = -Float.MAX_VALUE
        for (ii in 0..7) {
            val x = if (ii and (1 shl 2) == 0) _minExtent.x else _maxExtent.x
            val y = if (ii and (1 shl 1) == 0) _minExtent.y else _maxExtent.y
            val z = if (ii and (1 shl 0) == 0) _minExtent.z else _maxExtent.z
            val rw = 1f / (matrix.m03 * x + matrix.m13 * y + matrix.m23 * z + matrix.m33)
            val px = (matrix.m00 * x + matrix.m10 * y + matrix.m20 * z + matrix.m30) * rw
            val py = (matrix.m01 * x + matrix.m11 * y + matrix.m21 * z + matrix.m31) * rw
            val pz = (matrix.m02 * x + matrix.m12 * y + matrix.m22 * z + matrix.m32) * rw
            minx = min(minx, px)
            miny = min(miny, py)
            minz = min(minz, pz)
            maxx = max(maxx, px)
            maxy = max(maxy, py)
            maxz = max(maxz, pz)
        }
        result._minExtent.set(minx, miny, minz)
        result._maxExtent.set(maxx, maxy, maxz)
        return result
    }

    override fun expand(x: Float, y: Float, z: Float): Box {
        return expand(x, y, z, Box())
    }

    override fun expand(x: Float, y: Float, z: Float, result: Box): Box {
        result._minExtent.set(_minExtent.x - x, _minExtent.y - y, _minExtent.z - z)
        result._maxExtent.set(_maxExtent.x + x, _maxExtent.y + y, _maxExtent.z + z)
        return result
    }

    override fun vertex(code: Int, result: Vector3): Vector3 {
        return result.set(if (code and (1 shl 2) == 0) _minExtent.x else _maxExtent.x,
                if (code and (1 shl 1) == 0) _minExtent.y else _maxExtent.y,
                if (code and (1 shl 0) == 0) _minExtent.z else _maxExtent.z)
    }

    override fun contains(point: IVector3): Boolean {
        return contains(point.x, point.y, point.z)
    }

    override fun contains(x: Float, y: Float, z: Float): Boolean {
        return x >= _minExtent.x && x <= _maxExtent.x &&
                y >= _minExtent.y && y <= _maxExtent.y &&
                z >= _minExtent.z && z <= _maxExtent.z
    }

    override fun extentDistance(other: IBox): Float {
        return other.minimumExtent().manhattanDistance(_minExtent) + other.maximumExtent().manhattanDistance(_maxExtent)
    }

    override fun contains(other: IBox): Boolean {
        val omin = other.minimumExtent()
        val omax = other.maximumExtent()
        return omin.x >= _minExtent.x && omax.x <= _maxExtent.x &&
                omin.y >= _minExtent.y && omax.y <= _maxExtent.y &&
                omin.z >= _minExtent.z && omax.z <= _maxExtent.z
    }

    override fun intersects(other: IBox): Boolean {
        val omin = other.minimumExtent()
        val omax = other.maximumExtent()
        return _maxExtent.x >= omin.x && _minExtent.x <= omax.x &&
                _maxExtent.y >= omin.y && _minExtent.y <= omax.y &&
                _maxExtent.z >= omin.z && _minExtent.z <= omax.z
    }

    override fun intersects(ray: IRay3): Boolean {
        val dir = ray.direction
        return abs(dir.x) > MathUtil.EPSILON && (intersectsX(ray, _minExtent.x) || intersectsX(ray, _maxExtent.x)) ||
                abs(dir.y) > MathUtil.EPSILON && (intersectsY(ray, _minExtent.y) || intersectsY(ray, _maxExtent.y)) ||
                abs(dir.z) > MathUtil.EPSILON && (intersectsZ(ray, _minExtent.z) || intersectsZ(ray, _maxExtent.z))
    }

    override fun intersection(ray: IRay3, result: Vector3): Boolean {
        val origin = ray.origin
        if (contains(origin)) {
            result.set(origin)
            return true
        }
        val dir = ray.direction
        var t = Float.MAX_VALUE
        if (abs(dir.x) > MathUtil.EPSILON) {
            t = min(t, intersectionX(ray, _minExtent.x))
            t = min(t, intersectionX(ray, _maxExtent.x))
        }
        if (abs(dir.y) > MathUtil.EPSILON) {
            t = min(t, intersectionY(ray, _minExtent.y))
            t = min(t, intersectionY(ray, _maxExtent.y))
        }
        if (abs(dir.z) > MathUtil.EPSILON) {
            t = min(t, intersectionZ(ray, _minExtent.z))
            t = min(t, intersectionZ(ray, _maxExtent.z))
        }
        if (t == Float.MAX_VALUE) {
            return false
        }
        origin.addScaled(dir, t, result)
        return true
    }

    override // documentation inherited
    fun toString(): String {
        return "[min=$_minExtent, max=$_maxExtent]"
    }

    override // documentation inherited
    fun hashCode(): Int {
        return _minExtent.hashCode() + 31 * _maxExtent.hashCode()
    }

    override // documentation inherited
    fun equals(other: Any?): Boolean {
        if (other !is Box) {
            return false
        }
        val obox = other
        return _minExtent == obox._minExtent && _maxExtent == obox._maxExtent
    }

    /**
     * Helper method for [.intersects]. Determines whether the ray intersects the box
     * at the plane where x equals the value specified.
     */
    protected fun intersectsX(ray: IRay3, x: Float): Boolean {
        val origin = ray.origin
        val dir = ray.direction
        val t = (x - origin.x) / dir.x
        if (t < 0f) {
            return false
        }
        val iy = origin.y + t * dir.y
        val iz = origin.z + t * dir.z
        return iy >= _minExtent.y && iy <= _maxExtent.y &&
                iz >= _minExtent.z && iz <= _maxExtent.z
    }

    /**
     * Helper method for [.intersects]. Determines whether the ray intersects the box
     * at the plane where y equals the value specified.
     */
    protected fun intersectsY(ray: IRay3, y: Float): Boolean {
        val origin = ray.origin
        val dir = ray.direction
        val t = (y - origin.y) / dir.y
        if (t < 0f) {
            return false
        }
        val ix = origin.x + t * dir.x
        val iz = origin.z + t * dir.z
        return ix >= _minExtent.x && ix <= _maxExtent.x &&
                iz >= _minExtent.z && iz <= _maxExtent.z
    }

    /**
     * Helper method for [.intersects]. Determines whether the ray intersects the box
     * at the plane where z equals the value specified.
     */
    protected fun intersectsZ(ray: IRay3, z: Float): Boolean {
        val origin = ray.origin
        val dir = ray.direction
        val t = (z - origin.z) / dir.z
        if (t < 0f) {
            return false
        }
        val ix = origin.x + t * dir.x
        val iy = origin.y + t * dir.y
        return ix >= _minExtent.x && ix <= _maxExtent.x &&
                iy >= _minExtent.y && iy <= _maxExtent.y
    }

    /**
     * Helper method for [.intersection]. Finds the `t` value where the ray
     * intersects the box at the plane where x equals the value specified, or returns
     * [Float.MAX_VALUE] if there is no such intersection.
     */
    protected fun intersectionX(ray: IRay3, x: Float): Float {
        val origin = ray.origin
        val dir = ray.direction
        val t = (x - origin.x) / dir.x
        if (t < 0f) {
            return Float.MAX_VALUE
        }
        val iy = origin.y + t * dir.y
        val iz = origin.z + t * dir.z
        return if (iy >= _minExtent.y && iy <= _maxExtent.y &&
                iz >= _minExtent.z && iz <= _maxExtent.z)
            t
        else
            Float.MAX_VALUE
    }

    /**
     * Helper method for [.intersection]. Finds the `t` value where the ray
     * intersects the box at the plane where y equals the value specified, or returns
     * [Float.MAX_VALUE] if there is no such intersection.
     */
    protected fun intersectionY(ray: IRay3, y: Float): Float {
        val origin = ray.origin
        val dir = ray.direction
        val t = (y - origin.y) / dir.y
        if (t < 0f) {
            return Float.MAX_VALUE
        }
        val ix = origin.x + t * dir.x
        val iz = origin.z + t * dir.z
        return if (ix >= _minExtent.x && ix <= _maxExtent.x &&
                iz >= _minExtent.z && iz <= _maxExtent.z)
            t
        else
            Float.MAX_VALUE
    }

    /**
     * Helper method for [.intersection]. Finds the `t` value where the ray
     * intersects the box at the plane where z equals the value specified, or returns
     * [Float.MAX_VALUE] if there is no such intersection.
     */
    protected fun intersectionZ(ray: IRay3, z: Float): Float {
        val origin = ray.origin
        val dir = ray.direction
        val t = (z - origin.z) / dir.z
        if (t < 0f) {
            return Float.MAX_VALUE
        }
        val ix = origin.x + t * dir.x
        val iy = origin.y + t * dir.y
        return if (ix >= _minExtent.x && ix <= _maxExtent.x &&
                iy >= _minExtent.y && iy <= _maxExtent.y)
            t
        else
            Float.MAX_VALUE
    }

    /** The box's minimum extent.  */
    protected val _minExtent = Vector3()

    /** The box's maximum extent.  */
    protected val _maxExtent = Vector3()

    companion object {
        /** The unit box.  */
        val UNIT = Box(Vector3.UNIT_XYZ.negate(), Vector3.UNIT_XYZ)

        /** The zero box.  */
        val ZERO = Box(Vector3.ZERO, Vector3.ZERO)

        /** The empty box.  */
        val EMPTY = Box(Vector3.MAX_VALUE, Vector3.MIN_VALUE)

        /** A box that's as large as boxes can get.  */
        val MAX_VALUE = Box(Vector3.MIN_VALUE, Vector3.MAX_VALUE)
    }
}
