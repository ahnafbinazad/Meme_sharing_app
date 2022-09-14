Author: Ahnaf Azad

An android app that uses a REST API to present its users with dank memes and allows them to share it with their friends
Created using: Kotlin, Android Studio, Glide and Volley

The app uses the Volley library to pull a URL from an API with JSON data. Using the Glide library, the URL is downloaded as an image and presented on the user interaface
The user interface presents the user with a dark theme with two buttons: the NEXT button and the SHARE button. 
The next button calls the function tha loads nwe images and presents to the user
The share buton calls an intent object that allows gives the user options to share the url of the meme as plain text

This app uses API calls, useful libraries and the functionalities of Kotlin and Android Studio to give a clean and efficient build of a software

As this was a personal learning project, here is my notes of the process of this build:

User interface:
Created and empty page on android studio and selected the constraint layout, which is usually already selected
Added imageview fro the main image to be presented and added buttons underneath. 
Added vertical and horizontal guidelines to align the buttons and imageview properly
In the theme, changed the primary colours to give it a dark theme 
Added onClick attributes to the buttons so that they can be clicked
These buttons had functions created for them in the main activity to define what occurs when they are clicked
All the objects and views created have Ids on them so they can accessed in the main function
Added a progress bar to use when image is loading

Using and sending requests to the API (Volley Library):
Using Volley library to access APIs 
Using a chrome extension to view the JSON files in the API

From the Volley library, followed instructions to set it up in the app:
Added dependencies to the build.gradle(project) file
Added internet permission in the AndroidManifest file

Created a new function to call API:
Made the progress bar visible so that it shows when the function is loading images
Copied the code from Volley and placed in the function
Imported volley library to run some functions
Added the URL of the API on the url value 
Using Get request to get data
As we are getting a JSON object request, the default string request is changed to jsonObjectRequest
The listener should take in an JSON object
From the API, I want the url string as that is the thing contains the image 

Convert the url received into an image (Glide Library):
Added the dependencies from the Glide library GitHub page
Using Glide, the url is loaded and outpuot into the imageview

Adding a listener object to the glide call:
This implements an interface for when the image loading fails or works
Progress bar is set to invisible when the image loads up using Glide 
Progress bar is also set to invisible and an error message shown when image doesn't load 

Configuring Buttons:
Inside the nextMeme button, the loadMeme function is called which gets new memes 

To share memes (Intent):
Created a var on top of main activity and set it to the currentImageUrl so that the image can be used from any part of the code
To share the data, intent is used 
An intent object is created called intent with an action to send 
To put text with the meme url, I used intent.Extra and the url
Added a chooser from intent to give options on to choose how to share text
Added a type for the intent as plain text so that the data is shared as plain text
