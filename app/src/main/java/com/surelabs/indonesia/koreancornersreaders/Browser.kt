package com.surelabs.indonesia.koreancornersreaders

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent


class Browser(context: Context, url: String?) {
    init {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(context.resources.getColor(R.color.purple_700))
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        val p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setActionButton(
            BitmapFactory.decodeResource(
                context.resources,
                R.drawable.ic_share
            ), "", p, true
        );
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}