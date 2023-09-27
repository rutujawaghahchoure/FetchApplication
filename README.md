# FetchApplication<br>
<br>
This is a native Android Application built in Java that fetches data from an API. <br><br>
The data is displayed to the user on following requirements:<br>
1. Elements are displayed grouped by their ID.<br>
2. Elements are sorted by their group ID and then by their name.<br><br>
This application is submitted as a part of a take home assesment for Fetch. <br>

## Approach used<br>
Since this my first attempt to developing an Android Application I took help from the internet and learned a lot in the process. I started with creating the design of the application first and then implementing the functionality. I have used the Volley Library to handle the network requests and used the Singleton Pattern as only one instance of class will be created. By using the Singleton pattern network requests can be limited ensuring only once instance is created and requests are handled in the order they are received. I have made use of the callbacks to handle asynchronous requests. The purpose of using callback is it offers a way to schedule a method call to occur when another method finishes its task. 
