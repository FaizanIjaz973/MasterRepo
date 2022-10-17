# MasterRepo
This is aimed to to be a master repo which would contain different snippets of code relating to the mostly used features in any professionally developed android app ranging from basic recyclerview implementations to retrofit usage. It is designed to follow the best practices like dependency injection using dagger hilt and MVVM clean architecture. Moreover, best effort is made to add the unit and UI tests as well to keep the development cycle adherent to the automated testing. 

<img src="https://user-images.githubusercontent.com/50318121/196207065-987a7489-9cd9-45a9-90bf-9d015c662730.png" width="400">

Following are the components in the application.

**1 – Room**

Room is a preferred method of sharing data in a structured format in local storage. The activity allows the user to enter new entries which contain personal information like first name, last name etc. 

All those entries are listed in a recyclerview in a material design’s card format. Deletion functionality is also provided. 

<img src = "https://user-images.githubusercontent.com/50318121/196207159-2eece99d-4fa7-4707-9389-4b41c5ad6410.png" width=400>

**2 – Retrofit**

Calling API end points and getting/writing data is a need of almost all the applications today. Retrofit is a popular library to do so. In this activity, a simple get call is made to an open API which returns random cat facts. The text is then displayed on the canvas using livedata.

<img src="https://user-images.githubusercontent.com/50318121/196207214-844b7e01-676d-4631-b3e9-d15992324e4f.png" width=400>

**3 – ShareData**

This module tackles the functionality of sharing data with external applications. A text is shared with the application of user’s choosing. Likewise this activity can also accept data in format of text from external applications and show it in a textview.

<img src="https://user-images.githubusercontent.com/50318121/196207253-2bfd05c4-72a2-4921-9247-fe8713fa4f9f.png" width=400>

**4 – ReadWriteResource**

It writes to scoped staged area in a ‘test’ file. And reads from the same file on the button click of reading. 

<img src="https://user-images.githubusercontent.com/50318121/196207312-348b6040-8948-46a2-a1f8-afd21147d36e.png" width=400>

**5 – VideoPlayFromLocalStorage**

Plays video from local storage.  It also consists of the control like play, pause and seek bar.

<img src="https://user-images.githubusercontent.com/50318121/196207370-fb602773-787e-45d8-a553-e84c47a5f80e.png" width=400>

**6 – AudioPlayFromLocalStorage**

Plays audio from the local storage.

<img src="https://user-images.githubusercontent.com/50318121/196207412-d900105f-cc8d-4c56-adb5-18d91a7e7b99.png" width=400>

**7 – VideoStreamFromInternet**

The activity renders a surface that shows video being streamed from a remote location. It automatically takes care of buffering and all the relevant network-related activities. Supports both portrait and landscape mode. 

<img src="https://user-images.githubusercontent.com/50318121/196207485-97e84050-0617-415c-a5dd-3a9b17c46202.png" width=400>
