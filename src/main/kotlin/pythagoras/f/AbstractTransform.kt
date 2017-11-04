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
 * Implements some code shared by the various [Transform] implementations.
 */
abstract class AbstractTransform : Transform {
    override // from Transform
    val scale: Vector
        get() {
            return Vector(scaleX, scaleY)
        }

    override // from Transform
    val translation: Vector
        get() {
            return Vector(tx, ty)
        }

    override // from Transform
    fun setUniformScale(scale: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun setScale(scaleX: Float, scaleY: Float): Transform {
        setScaleX(scaleX)
        setScaleY(scaleY)
        return this
    }

    override // from Transform
    fun setScaleX(scaleX: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun setScaleY(scaleY: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun setRotation(angle: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun setTranslation(tx: Float, ty: Float): Transform {
        setTx(tx)
        setTy(ty)
        return this
    }

    override // from Transform
    fun uniformScale(scale: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun scale(scaleX: Float, scaleY: Float): Transform {
        scaleX(scaleX)
        scaleY(scaleY)
        return this
    }

    override // from Transform
    fun scaleX(scaleX: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun scaleY(scaleY: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun rotate(angle: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun translate(tx: Float, ty: Float): Transform {
        translateX(tx)
        translateY(ty)
        return this
    }

    override // from Transform
    fun translateX(tx: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun translateY(ty: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun shear(sx: Float, sy: Float): Transform {
        shearX(sx)
        shearY(sy)
        return this
    }

    override // from Transform
    fun shearX(sx: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun shearY(sy: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun setTx(tx: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun setTy(ty: Float): Transform {
        throw UnsupportedOperationException()
    }

    override // from Transform
    fun setTransform(m00: Float, m01: Float, m10: Float, m11: Float, tx: Float, ty: Float): Transform {
        throw UnsupportedOperationException()
    }

    abstract override // from Transform
    fun copy(): Transform
}
