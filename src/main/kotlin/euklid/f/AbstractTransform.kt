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
 * Implements some code shared by the various [Transform] implementations.
 */
abstract class AbstractTransform : Transform {

    override fun setUniformScale(scale: Float): Transform {
        throw UnsupportedOperationException()
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

    override fun uniformScale(scale: Float): Transform {
        throw UnsupportedOperationException()
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

    override fun translateX(tx: Float): Transform {
        throw UnsupportedOperationException()
    }

    override fun translateY(ty: Float): Transform {
        throw UnsupportedOperationException()
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
}
