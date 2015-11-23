## What is the project of Shot and See?

1. User can have a notification when somebody comes in my house.
2. If user wants to know the current view of my home, he/she can just click the button and view my home.

### How to work
1. (Yun ATmega) Detect somebody coming with Ultrasonic sensor or PIR sensor
2. (Yun Atheros) Take a picture and send it to Pasre server
3. (Parse) Save the picture with meta data and send the push notification to Android App.
4. (Android) Receive the notification and find the new picture