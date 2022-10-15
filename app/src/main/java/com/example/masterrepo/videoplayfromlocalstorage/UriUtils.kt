package com.example.masterrepo.videoplayfromlocalstorage

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import androidx.annotation.AnyRes

object UriUtils {
    @Throws(Resources.NotFoundException::class)
    fun getUriToResource(context: Context, @AnyRes resId: Int): Uri {
        val res: Resources = context.getResources()
        return Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" + res.getResourcePackageName(resId)
                    + '/' + res.getResourceTypeName(resId)
                    + '/' + res.getResourceEntryName(resId)
        )
    }
}