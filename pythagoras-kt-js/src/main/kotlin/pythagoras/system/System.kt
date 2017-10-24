package pythagoras.system

actual fun arrayCopy(src: ByteArray, srcPos: Int, dest: ByteArray, destPos: Int, length: Int) {
    if (srcPos < 0 || destPos < 0 || length < 0 || srcPos + length > src.size || destPos + length > dest.size) {
        throw IndexOutOfBoundsException()
    }
    val offset = destPos - srcPos
    val range = if (offset > 0)
        srcPos..(srcPos + length) else
        (srcPos + length)..srcPos
    for (i in range) {
        dest[i + offset] = src[i]
    }
}

actual fun arrayCopy(src: IntArray, srcPos: Int, dest: IntArray, destPos: Int, length: Int) {
    if (srcPos < 0 || destPos < 0 || length < 0 || srcPos + length > src.size || destPos + length > dest.size) {
        throw IndexOutOfBoundsException()
    }
    val offset = destPos - srcPos
    val range = if (offset > 0)
        srcPos..(srcPos + length) else
        (srcPos + length)..srcPos
    for (i in range) {
        dest[i + offset] = src[i]
    }
}

actual fun arrayCopy(src: FloatArray, srcPos: Int, dest: FloatArray, destPos: Int, length: Int) {
    if (srcPos < 0 || destPos < 0 || length < 0 || srcPos + length > src.size || destPos + length > dest.size) {
        throw IndexOutOfBoundsException()
    }
    val offset = destPos - srcPos
    val range = if (offset > 0)
        srcPos..(srcPos + length) else
        (srcPos + length)..srcPos
    for (i in range) {
        dest[i + offset] = src[i]
    }
}
