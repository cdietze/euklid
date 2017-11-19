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
 * Used to return the boundary of an [IShape], one segment at a time.
 */
interface PathIterator {

    /**
     * Returns the winding rule used to determine the interior of this path.
     */
    fun windingRule(): Int

    /**
     * Returns true if this path has no additional segments.
     */
    val isDone: Boolean

    /**
     * Advances this path to the next segment.
     */
    fun next()

    /**
     * Returns the coordinates and type of the current path segment. The number of points stored in
     * `coords` differs by path segment type:
     * 0 - [SEG_CLOSE],
     * 1 - [SEG_MOVETO], [SEG_LINETO],
     * 2 - [SEG_QUADTO],
     * 3 - [SEG_CUBICTO].
     * @param coords a buffer into which the current coordinates will be copied. It must be of length 6.
     * Each point is stored as a pair of x,y coordinates.
     *
     * @return the path segment type, e.g. [SEG_MOVETO].
     */
    fun currentSegment(coords: FloatArray): Int

    companion object {
        /** Specifies the even/odd rule for determining the interior of a path.  */
        val WIND_EVEN_ODD = 0

        /** Specifies the non-zero rule for determining the interior of a path.  */
        val WIND_NON_ZERO = 1

        /** Indicates the starting location for a new subpath.  */
        val SEG_MOVETO = 0

        /** Indicates the end point of a line to be drawn from the most recently specified point.  */
        val SEG_LINETO = 1

        /** Indicates a pair of points that specify a quadratic parametric curve to be drawn from the
         * most recently specified point.  */
        val SEG_QUADTO = 2

        /** Indicates a pair of points that specify a cubic parametric curve to be drawn from the most
         * recently specified point.  */
        val SEG_CUBICTO = 3

        /** Indicates that the preceding subpath should be closed by appending a line segment back to
         * the point corresponding to the most recent [.SEG_MOVETO].  */
        val SEG_CLOSE = 4
    }
}
