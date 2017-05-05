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
 * Provides read-only access to a [Line].
 */
interface ILine : IShape {
    /** Returns the x-coordinate of the start of this line.  */
    val x1: Float

    /** Returns the y-coordinate of the start of this line.  */
    val y1: Float

    /** Returns the x-coordinate of the end of this line.  */
    val x2: Float

    /** Returns the y-coordinate of the end of this line.  */
    val y2: Float

    /** Returns a copy of the starting point of this line.  */
    fun p1(): Point

    /** Initializes the supplied point with this line's starting point.
     * @return the supplied point.
     */
    fun p1(target: Point): Point

    /** Returns a copy of the ending point of this line.  */
    fun p2(): Point

    /** Initializes the supplied point with this line's ending point.
     * @return the supplied point.
     */
    fun p2(target: Point): Point

    /** Returns the square of the distance from the specified point to the line defined by this
     * line segment.  */
    fun pointLineDistSq(px: Float, py: Float): Float

    /** Returns the square of the distance from the supplied point to the line defined by this line
     * segment.  */
    fun pointLineDistSq(p: XY): Float

    /** Returns the distance from the specified point to the line defined by this line segment.  */
    fun pointLineDist(px: Float, py: Float): Float

    /** Returns the distance from the supplied point to the line defined by this line segment.  */
    fun pointLineDist(p: XY): Float

    /** Returns the square of the distance from the specified point this line segment.  */
    fun pointSegDistSq(px: Float, py: Float): Float

    /** Returns the square of the distance from the supplied point this line segment.  */
    fun pointSegDistSq(p: XY): Float

    /** Returns the distance from the specified point this line segment.  */
    fun pointSegDist(px: Float, py: Float): Float

    /** Returns the distance from the supplied point this line segment.  */
    fun pointSegDist(p: XY): Float

    /** Returns an indicator of where the specified point (px,py) lies with respect to this line
     * segment.  */
    fun relativeCCW(px: Float, py: Float): Int

    /** Returns an indicator of where the specified point lies with respect to this line segment.  */
    fun relativeCCW(p: XY): Int

    /** Returns a mutable copy of this line.  */
    fun clone(): Line
}
