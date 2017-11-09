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
    override val scale: Vector
        get() {
            return Vector(scaleX, scaleY)
        }

    override val translation: Vector
        get() {
            return Vector(tx, ty)
        }

    override fun setUniformScale(scale: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun setScale(scaleX: Float, scaleY: Float): Transform {
        setScaleX(scaleX)
        setScaleY(scaleY)
        return this
    }

    override fun setScaleX(scaleX: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun setScaleY(scaleY: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun setRotation(angle: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun setTranslation(tx: Float, ty: Float): Transform {
        setTx(tx)
        setTy(ty)
        return this
    }

    override fun uniformScale(scale: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun scale(scaleX: Float, scaleY: Float): Transform {
        scaleX(scaleX)
        scaleY(scaleY)
        return this
    }

    override fun scaleX(scaleX: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun scaleY(scaleY: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun rotate(angle: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun translate(tx: Float, ty: Float): Transform {
        translateX(tx)
        translateY(ty)
        return this
    }

    override fun translateX(tx: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun translateY(ty: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun shear(sx: Float, sy: Float): Transform {
        shearX(sx)
        shearY(sy)
        return this
    }

    override fun shearX(sx: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun shearY(sy: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun setTx(tx: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun setTy(ty: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun setTransform(m00: Float, m01: Float, m10: Float, m11: Float, tx: Float, ty: Float): Transform {
        throw UnsupportedOperationException()
    }

    abstract override fun copy(): Transform
}
