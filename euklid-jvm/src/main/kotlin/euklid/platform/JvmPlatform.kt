package euklid.platform

actual fun arrayCopy(src: ByteArray, srcPos: Int, dest: ByteArray, destPos: Int, length: Int) {
    java.lang.System.arraycopy(src, srcPos, dest, destPos, length)
}

actual fun arrayCopy(src: IntArray, srcPos: Int, dest: IntArray, destPos: Int, length: Int) {
    java.lang.System.arraycopy(src, srcPos, dest, destPos, length)
}

actual fun arrayCopy(src: FloatArray, srcPos: Int, dest: FloatArray, destPos: Int, length: Int) {
    java.lang.System.arraycopy(src, srcPos, dest, destPos, length)
}

actual fun <T> arrayCopy(src: Array<T>, srcPos: Int, dest: Array<T>, destPos: Int, length: Int) {
    java.lang.System.arraycopy(src, srcPos, dest, destPos, length)
}