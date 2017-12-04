##Introduction :

This repository contains the work I have done with colleagues on the road sign detection project at the ENSEM. The project was meant to be the basis of a more devolleped computer vision model that would be later added to the Twizzy, an electric vehicle created jointly by the ENSEM and Renault.

##Description of project :

The project is mainly about the detection and recognition of road signs in 2D images.

It was implemented using the OpenCV library in Java ( The library is also available in C++ and python ).

The final version takes both 2D images and videos and does real-time recongnition of road signs.  In this project we only consider "European speed limits signs" but the idea behind the project can be generalized to other types of signs.

There are three main parts in the project :

- Tresholding

-Edges and outlning detection

-Matching



## Running instructions:
The code is in Java and we used the OpenCV library available for free here https://github.com/opencv/opencv/releases. The code was run on an IDE and was sectionned into packages. In order to test the code: 

- Make sure to add OpenCV as a user library. 

- Add the reference images to your project.

 - Run "FenetrePrincipale.java" : This will open a graphical interface that will allows you to choose between 3 types of modes.

##Modes

The project allows you to :
 - "Mode automatique" allows you to directly determine the reference image closest to your actual image.
 
 - " Mode pas à pas " allows you to look at the outpus step by step of procedure we have implemented.

 - "Mode video" allows you to identify in real time the signs going through your video.