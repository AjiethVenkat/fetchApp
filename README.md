# FetchApp

FUNCTIONALITY OF APPLICATION:

* FetchApp is a list of data fetched from a JSON link provided. 
* It contains three values of id, listId and name. 
* The data are grouped according to the listId.
* The name container is filtered with any data of null or empty.

DESIGN OF APPLICATION:

* The background is constructed with recycler view.
* Each cell is given a background of #757575.
* The id are kept in a backgorund of its own drawable.
* The spacing are given according.
* The application supports most of the Andriod devices.
* Orientation of the application are supported in the app.
* Based on the current release of the OS with SDK version of 29.
* Depedencies used in the application are volley for json request.

Dependencies:

    implementation 'com.android.volley:volley:1.2.0'

JSON:

    https://fetch-hiring.s3.amazonaws.com/hiring.json.
