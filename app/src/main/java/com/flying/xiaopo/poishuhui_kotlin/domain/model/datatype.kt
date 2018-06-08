package com.flying.xiaopo.poishuhui_kotlin.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Response<out T>(val errNo: Int, val errMsg: String, val data: T?){
    fun isSuccessful() : Boolean{
        return errNo == 0
    }

    fun isFaild() :Boolean{
        return errNo != 0
    }
}
open class PageData<out T>(val count: Int, val totalPages: Int, val numsPerPage: Int, val currentPage: Int, val data: List<T>)

data class Cover(val book: Int, val title: String, val id: Int, val part_id: Int, val chapter: Int, val number: Int, val name: String, val thumb: String, val authorize: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(book)
        parcel.writeString(title)
        parcel.writeInt(id)
        parcel.writeInt(part_id)
        parcel.writeInt(chapter)
        parcel.writeInt(number)
        parcel.writeString(name)
        parcel.writeString(thumb)
        parcel.writeInt(authorize)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cover> {
        override fun createFromParcel(parcel: Parcel): Cover {
            return Cover(parcel)
        }

        override fun newArray(size: Int): Array<Cover?> {
            return arrayOfNulls(size)
        }
    }
}
data class BookInfo(val id: Int, val name: String, val author_name: String, val desc: String, val next_post_day: String, val banner: String, val thumb: String)
data class CartoonInfo(val title: String, val posts: LinkedHashMap<String, List<Page>>)
data class Cartoon(@SerializedName("0") val info: CartoonInfo)

data class BookDetail(val book: BookInfo, val cartoon: Cartoon)

data class News(val title: String, val createdTime: String, val link: String)

data class NewsContainer(val title: String, val newsList: List<News>)


data class Page(val id: Int, val title: String, val source: String){
    var num: Int = 0
    var pageKey: String = ""
}

data class Comic(val title: String, val url: String, val source: Int, @SerializedName("source_name") val sourceName: String)

data class PageDetail(val title: String, val book_name: String, val author_name: String, @SerializedName("posts") val comics: List<Comic>)