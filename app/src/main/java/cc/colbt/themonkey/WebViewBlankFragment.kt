package cc.colbt.themonkey

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.webkit.WebViewAssetLoader
import cc.colbt.themonkey.util.Const


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WebViewBlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WebViewBlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_web_view_blank, container, false)

        val myWebView: WebView = view.findViewById(R.id.thewebview)
        val assetLoader = WebViewAssetLoader.Builder()
            .setDomain("colbt.cc")
            .setHttpAllowed(true)
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(requireActivity()))
            .addPathHandler("/res/", WebViewAssetLoader.ResourcesPathHandler(requireActivity()))
            .build()
        myWebView.webViewClient = LocalContentWebViewClient(assetLoader)
        myWebView.settings.javaScriptEnabled = true
        myWebView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(message: ConsoleMessage): Boolean {
                Log.d("MyApplication", "${message.message()} -- From line " +
                        "${message.lineNumber()} of ${message.sourceId()}")
                return true
            }

        }

        myWebView.loadUrl("http://colbt.cc/assets/localweb/build/index.html")

        myWebView.addJavascriptInterface(MonkeyJavaScriptInterface(requireActivity()),
            Const.AndroidJsObjNameSpace)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WebViewBlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WebViewBlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}