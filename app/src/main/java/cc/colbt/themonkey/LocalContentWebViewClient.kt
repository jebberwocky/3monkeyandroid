package cc.colbt.themonkey

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.annotation.RequiresApi
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import cc.colbt.themonkey.util.Const


public class LocalContentWebViewClient(private val assetLoader: WebViewAssetLoader) : WebViewClientCompat() {
    @RequiresApi(21)
    override fun shouldInterceptRequest(
        view: WebView,
        request: WebResourceRequest,
    ): WebResourceResponse? {
        return assetLoader.shouldInterceptRequest(request.url)
    }

    @RequiresApi(21)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        var uri = request.url;
        var scheme = uri.scheme;
        if (scheme != null) {
            Log.d(Const.LogTag,scheme)
        }
        Log.d(Const.LogTag,request.url.toString())
        if (scheme == "mailto" || scheme == "tel:"){
            view.context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(request.url.toString()))
            )
        } else {
            // Stay within this webview and load url
            view.loadUrl(request.url.toString())
        }
        return true
    }

    override fun onRenderProcessGone(view: WebView?, detail: RenderProcessGoneDetail?): Boolean {
        Log.d(Const.LogTag,detail.toString())
        return super.onRenderProcessGone(view, detail)
    }

    // to support API < 21
    override fun shouldInterceptRequest(
        view: WebView,
        url: String
    ): WebResourceResponse? {
        return assetLoader.shouldInterceptRequest(Uri.parse(url))
    }

    override fun shouldOverrideUrlLoading(
        view: WebView,
        url: String
    ): Boolean {
        if (url.startsWith("mailto:") || url.startsWith("tel:")) {
            view.context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(url))
            )
        } else {
            // Stay within this webview and load url
            view.loadUrl(url)
        }
        return true
    }
}