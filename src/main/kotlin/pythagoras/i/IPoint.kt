/*
 * Copyright 2017 The Pythagoras-kt Authors
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

package pythagoras.i

/**
 * Provides read-only access to a [Point].
 */
interface IPoint {
    /** Returns this point's x-coordinate.  */
    val x: Int

    /** Returns this point's y-coordinate.  */
    val y: Int

    /** Returns the squared Euclidian distance between this point and the specified point.  */
    fun distanceSq(px: Int, py: Int): Int

    /** Returns the squared Euclidian distance between this point and the supplied point.  */
    fun distanceSq(p: IPoint): Int

    /** Returns the Euclidian distance between this point and the specified point.  */
    fun distance(px: Int, py: Int): Int

    /** Returns the Euclidian distance between this point and the supplied point.  */
    fun distance(p: IPoint): Int

    /** Translates this point by the specified offset.
     * @return a new point containing the result.
     */
    fun add(x: Int, y: Int): Point

    /** Translates this point by the specified offset and stores the result in the object provided.
     * @return a reference to the result, for chaining.
     */
    fun add(x: Int, y: Int, result: Point): Point

    /** Subtracts the supplied point from `this`.
     * @return a new point containing the result.
     */
    fun subtract(x: Int, y: Int): Point

    /** Subtracts the supplied point from `this` and stores the result in `result`.
     * @return a reference to the result, for chaining.
     */
    fun subtract(x: Int, y: Int, result: Point): Point

    /** Subtracts the supplied point from `this` and stores the result in `result`.
     * @return a reference to the result, for chaining.
     */
    fun subtract(other: IPoint, result: Point): Point

    /** Returns a mutable copy of this point.  */
    fun copy(x: Int = this.x, y: Int = this.y): Point
}
