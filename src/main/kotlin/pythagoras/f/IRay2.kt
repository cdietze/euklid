/*
 * Copyright 2017 The Pythagoras.kt Authors
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



package pythagoras.f

/**
 * Provides read-only access to a [Ray2].
 */
interface IRay2 {
    /**
     * Returns a reference to the ray's point of origin.
     */
    val origin: IVector

    /**
     * Returns a reference to the ray's unit direction vector.
     */
    val direction: IVector

    /**
     * Transforms this ray.

     * @return a new ray containing the result.
     */
    fun transform(transform: Transform): Ray2

    /**
     * Transforms this ray, placing the result in the object provided.

     * @return a reference to the result ray, for chaining.
     */
    fun transform(transform: Transform, result: Ray2): Ray2

    /**
     * Determines whether the ray intersects the specified point.
     */
    fun intersects(pt: IVector): Boolean

    /**
     * Finds the intersection between the ray and a line segment with the given start and end
     * points.

     * @return true if the ray intersected the segment (in which case the result will contain the
     * * point of intersection), false otherwise.
     */
    fun getIntersection(start: IVector, end: IVector, result: Vector): Boolean

    /**
     * Finds the intersection between the ray and a capsule with the given start point, end point,
     * and radius.

     * @return true if the ray intersected the circle (in which case the result will contain the
     * * point of intersection), false otherwise.
     */
    fun getIntersection(start: IVector, end: IVector, radius: Float, result: Vector): Boolean

    /**
     * Finds the intersection between the ray and a circle with the given center and radius.

     * @return true if the ray intersected the circle (in which case the result will contain the
     * * point of intersection), false otherwise.
     */
    fun getIntersection(center: IVector, radius: Float, result: Vector): Boolean

    /**
     * Computes the nearest point on the Ray to the supplied point.
     * @return `result` for chaining.
     */
    fun getNearestPoint(point: IVector, result: Vector): Vector
}
