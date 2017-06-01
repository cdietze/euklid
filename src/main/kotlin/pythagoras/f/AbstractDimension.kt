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

import pythagoras.util.Platform

/**
 * Provides most of the implementation of [IDimension], obtaining only width and height from
 * the derived class.
 */
abstract class AbstractDimension : IDimension {
    override // from interface IDimension
    fun clone(): Dimension {
        return Dimension(this)
    }

    override fun hashCode(): Int {
        return Platform.hashCode(width) xor Platform.hashCode(height)
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other is AbstractDimension) {
            val d = other
            return d.width == width && d.height == height
        }
        return false
    }

    override fun toString(): String {
        return Dimensions.dimenToString(width, height)
    }
}
