### Setup Arduino Yun

opkg update

opkg install kmod-video-uvc

opkg install python-openssl

opkg install fswebcam

opkg install mjpg-streamer


## Test
fswebcam image.png

Look at with browser
- like http://192.168.0.8/nice295/image.png


## Python code to upload image to Parse server
Note PC
 - python -m SimpleHTTPServer 8080
Yun
 - wget http://192.168.0.6:8080/upload.py
 - python upload.py 32
