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

/**
 * Provides read-only access to a [Plane].
 */
interface IPlane {

    /** Returns the plane normal.  */
    val normal: IVector3

    /** Returns the plane constant.  */
    val constant: Float

    /**
     * Computes and returns the signed distance from the plane to the specified point.
     */
    fun distance(pt: IVector3): Float = normal.dot(pt) + constant

    // /**
    //  * Transforms this plane by the specified transformation.
    //  *
    //  * @return a new plane containing the result.
    //  */
    // Plane transform (Transform3D transform);

    // /**
    //  * Transforms this plane by the specified transformation, placing the result in the object
    //  * provided.
    //  *
    //  * @return a reference to the result plane, for chaining.
    //  */
    // Plane transform (Transform3D transform, Plane result);

    /**
     * Negates this plane.
     * @return a new plane containing the result.
     */
    fun negate(): Plane = negate(Plane())

    /**
     * Negates this plane, placing the result in the object provided.
     * @return a reference to the result, for chaining.
     */
    fun negate(result: Plane): Plane {
        normal.negate(result.normal)
        result.constant = -constant
        return result
    }

    /**
     * Computes the intersection of the supplied ray with this plane, placing the result
     * in the given vector (if the ray intersects).
     * @return true if the ray intersects the plane (in which case the result will contain
     * * the point of intersection), false if not.
     */
    fun intersection(ray: IRay3, result: Vector3): Boolean {
        val distance = distance(ray)
        return if (distance.isNaN() || distance < 0f) {
            false
        } else {
            ray.origin.addScaled(ray.direction, distance, result)
            true
        }
    }

    /**
     * Computes the signed distance to this plane along the specified ray.
     * @return the signed distance, or [Float.NaN] if the ray runs parallel to the plane.
     */
    fun distance(ray: IRay3): Float {
        val dividend = -distance(ray.origin)
        val divisor = normal.dot(ray.direction)
        return if (abs(dividend) < MathUtil.EPSILON) {
            0f // origin is on plane
        } else if (abs(divisor) < MathUtil.EPSILON) {
            Float.NaN // ray is parallel to plane
        } else {
            dividend / divisor
        }
    }
}
