package com.example.picturesharingapp

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentImageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //calling function to load a meme as soon as app is started
        loadMeme()
    }

    //this function uses Glide nad Volley to create API request and convert to image
    private fun loadMeme() {

        //making progress bar visible
        progressBar.visibility = View.VISIBLE
        // Instantiate the RequestQueue for Volley
        val queue = Volley.newRequestQueue(this)
        //this is the API url
        currentImageUrl = "https://meme-api.herokuapp.com/gimme"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, currentImageUrl,null,
            { response ->
                //from the API JSON file, getting the "url" as it contains the image url
                currentImageUrl = response.getString("url")

                //using Glide to downlaod and convert the url to an image and output to the memeImage object on the screen
                //added a listener object with the Glide call to turn off the progress bar when the image loads up
                Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        //progress bar becomes invisible
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        //progress bar goes invisible
                        progressBar.visibility = View.GONE
                        return false
                    }
                }).into(memeImage)
            },
            {
                //creates a toast if there is error to call the API
                Toast.makeText(this,"Something went wrong", Toast.LENGTH_LONG).show()
            })

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    //SHARE button
    fun shareMeme(view: View) {
        //creating intent object
        val intent = Intent(Intent.ACTION_SEND)
        //setting the type of the data that intent will send
        intent.type = "text/plain"
        //the string that will be pushed by intent
        intent.putExtra(Intent.EXTRA_TEXT, "Epic funny meme lol $currentImageUrl")
        //creating a chooser object to let user choose which app they want to send data through
        val chooser = Intent.createChooser(intent, "Share this meme using")
        //starts the chooser activityu
        startActivity(chooser)
    }
    //NEXT button
    fun nextMeme(view: View) {
        //calls function to laod the meme
        loadMeme()
    }
}