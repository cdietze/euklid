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



package pythagoras.f

/**
 * Provides read-only access to an [Arc].
 */
interface IArc : IRectangularShape {

    /** Returns the type of this arc: [ArcType.OPEN], etc.  */
    val arcType: ArcType

    /** Returns the starting angle of this arc.  */
    val angleStart: Float

    /** Returns the angular extent of this arc.  */
    val angleExtent: Float

    /** Returns the intersection of the ray from the center (defined by the starting angle) and the
     * elliptical boundary of the arc.  */
    val startPoint: Point

    /** Writes the intersection of the ray from the center (defined by the starting angle) and the
     * elliptical boundary of the arc into `target`.
     * @return the supplied point.
     */
    fun startPoint(target: Point): Point

    /** Returns the intersection of the ray from the center (defined by the starting angle plus the
     * angular extent of the arc) and the elliptical boundary of the arc.  */
    val endPoint: Point

    /** Writes the intersection of the ray from the center (defined by the starting angle plus the
     * angular extent of the arc) and the elliptical boundary of the arc into `target`.
     * @return the supplied point.
     */
    fun endPoint(target: Point): Point

    /** Returns whether the specified angle is within the angular extents of this arc.  */
    fun containsAngle(angle: Float): Boolean

    /** Returns a mutable copy of this arc.  */
    fun clone(): Arc

    enum class ArcType {
        /** An arc type indicating a simple, unconnected curve.  */
        OPEN,

        /** An arc type indicating a closed curve, connected by a straight line from the starting to
         * the ending point of the arc.  */
        CHORD,

        /** An arc type indicating a closed curve, connected by a line from the starting point of the
         * arc to the center of the circle defining the arc, and another straight line from that center
         * to the ending point of the arc.  */
        PIE
    }
}
