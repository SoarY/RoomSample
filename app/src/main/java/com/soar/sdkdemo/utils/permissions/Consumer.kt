package com.soar.sdkdemo.utils.permissions


/**
 * NAME：YONG_
 * Created at: 2023/5/16 15
 * Describe:
 */
fun interface Consumer<T> {
    fun accept(t: T)
}