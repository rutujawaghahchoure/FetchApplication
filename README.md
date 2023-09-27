# FetchApplication<br>
<br>
This is a native Android Application built in Java that fetches data from an API. <br><br>
The data is displayed to the user on following requirements:<br>
1. Elements are displayed grouped by their ID.<br>
2. Elements are sorted by their group ID and then by their name.<br><br>
This application is submitted as a part of a take home assesment for Fetch. <br>

## Approach Used<br>
Since this is my first attempt at developing an Android Application, I sought help from the internet and learned a lot in the process. I started by creating the design of the application first and then implementing the functionality. I used the Volley Library to handle network requests and employed the Singleton Pattern, ensuring that only one instance of the class will be created. By using the Singleton pattern, network requests can be limited, ensuring that only one instance is created, and requests are handled in the order they are received. I have made use of callbacks to handle asynchronous requests. The purpose of using callbacks is that they offer a way to schedule a method call to occur when another method finishes its task.<br><br>

## Installation<br>
To run the application on your local system, you will need to install the latest JDK version and the Android Studio Application.<br><br>

Follow the steps below:<br><br>
<ol>
<li>Git clone the repository.<br><br> 
   ```
   git clone https://github.com/rutujawaghahchoure/FetchApplication.git
   ```
</li>
<br>
<li>Open the Android Studio Application, then open the file you cloned in the application.<br><br></li>

<li>Once all the plugins are completed, you can click the green Run button at the top right.<br><br></li>

</ol>
Note: You will also need to install the Android SDK version for the emulator to start and launch a device (this installation is part of the Android Studio Application installation process).<br><br>


## Features<br>
<ul>
<li>When the application is launched, a page will be displayed while the data is fetched from the API.</li>
<li>Once the data is retrieved, it will be displayed grouped by element ID.</li>
<li>There is an option that, when selected, will display the data sorted by ID and the name of the element.</li>
</ul>




