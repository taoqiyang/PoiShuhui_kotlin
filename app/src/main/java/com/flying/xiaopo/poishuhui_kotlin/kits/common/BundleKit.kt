package com.flying.xiaopo.poishuhui_kotlin.kits.common

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * @author wupanjie
 */

fun Map<String, Any>.toBundle(): Bundle {
  val bundle = Bundle()
  this.forEach { entry ->
    when (entry.value) {
      is Int -> bundle.putInt(entry.key, entry.value as Int)
      is String -> bundle.putString(entry.key, entry.value as String)
      is Long -> bundle.putLong(entry.key, entry.value as Long)
      is Short -> bundle.putShort(entry.key, entry.value as Short)
      is Float -> bundle.putFloat(entry.key, entry.value as Float)
      is Double -> bundle.putDouble(entry.key, entry.value as Double)
      is Byte -> bundle.putByte(entry.key, entry.value as Byte)
      is Parcelable -> bundle.putParcelable(entry.key, entry.value as Parcelable)
      is Serializable -> bundle.putSerializable(entry.key, entry.value as Serializable)
    }
  }

  return bundle
}