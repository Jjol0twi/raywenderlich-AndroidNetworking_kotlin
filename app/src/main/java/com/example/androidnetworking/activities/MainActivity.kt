/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

//package com.raywenderlich.githubrepolist.ui.activities
package com.example.androidnetworking.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnetworking.R
import com.example.androidnetworking.adapters.RepoListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    private val items = listOf(
        "JetBrains/kotlin - The Kotlin Programming Language",
        "exercism/kotlin - Exercism exercises in Kotlin",
        "cbeust/kobalt - A Kotlin-based build system for the JVM",
        "JetBrains/kotlin - The Kotlin Programming Language",
        "exercism/kotlin - Exercism exercises in Kotlin",
        "cbeust/kobalt - A Kotlin-based build system for the JVM",
        "JetBrains/kotlin - The Kotlin Programming Language"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repoList.layoutManager = LinearLayoutManager(this)
        repoList.adapter = RepoListAdapter(items)

        val url =
            "https://api.github.com/search/repositories?q=mario+language:kotlin&sort=stars&order=desc"
        if (isNetworkConnected()){
            Log.d("please","is conneted")
        }else {
            AlertDialog.Builder(this).setTitle("No Internet Connection")    //messageBox
                .setMessage("please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok){_,_->}  //_: substitues an unused parameter in lambda expression
                .setIcon(android.R.drawable.ic_dialog_alert).show()

        }
//        doAsync {
//            Request(url).run()
//            uiThread { longToast("Request performed") }
//        }
    }

    private fun isNetworkConnected(): Boolean { //Check network
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}
