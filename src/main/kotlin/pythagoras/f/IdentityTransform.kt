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
 * Implements the identity transform.
 */
object IdentityTransform : AbstractTransform() {

    override val uniformScale: Float
        get() = 1f

    override val scaleX: Float
        get() = 1f

    override val scaleY: Float
        get() = 1f

    override val rotation: Float
        get() = 0f

    override val tx: Float
        get() = 0f

    override val ty: Float
        get() = 0f

    override fun get(matrix: FloatArray) {
        matrix[0] = 1f
        matrix[1] = 0f
        matrix[2] = 0f
        matrix[3] = 1f
        matrix[4] = 0f
        matrix[5] = 0f
    }

    override fun invert(): Transform = this

    override fun concatenate(other: Transform): Transform = other

    override fun preConcatenate(other: Transform): Transform = other

    override fun lerp(other: Transform, t: Float): Transform {
        throw UnsupportedOperationException() // TODO
    }

    override fun transform(p: IPoint, into: Point): Point = into.set(p)

    override fun transform(src: Array<IPoint>, srcOff: Int, dst: Array<Point>, dstOff: Int, count: Int) {
        var srcOff2 = srcOff
        var dstOff2 = dstOff
        for (ii in 0 until count) {
            transform(src[srcOff2++], dst[dstOff2++])
        }
    }

    override fun transform(src: FloatArray, srcOff: Int, dst: FloatArray, dstOff: Int, count: Int) {
        var srcOff2 = srcOff
        var dstOff2 = dstOff
        for (ii in 0 until count) {
            dst[dstOff2++] = src[srcOff2++]
        }
    }

    override fun inverseTransform(p: IPoint, into: Point): Point = into.set(p)

    override fun transformPoint(v: IVector, into: Vector): Vector = into.set(v)

    override fun transform(v: IVector, into: Vector): Vector = into.set(v)

    override fun inverseTransform(v: IVector, into: Vector): Vector = into.set(v)

    override fun copy(): IdentityTransform = this

    override fun generality(): Int = GENERALITY

    override fun toString(): String = "ident"

    /** Identifies the identity transform in [generality].  */
    val GENERALITY = 0
}
