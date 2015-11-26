import sys
import json,httplib,urllib
import os

imagePath = "/www/nice295/image.png"
imageName = "image.png"

# Take a picture
retvalue = os.system("fswebcam " + imagePath)
print retvalue

# Upload pics
connection = httplib.HTTPSConnection('api.parse.com', 443)
connection.connect()
connection.request('POST', '/1/files/'+imageName, open(imagePath, 'rb').read(), {
       "X-Parse-Application-Id": "---",
       "X-Parse-REST-API-Key": "---",
       "Content-Type": "image/jpeg"
     })
result = json.loads(connection.getresponse().read())
print result

name = result['name']
print name

# Associating with Object
connection = httplib.HTTPSConnection('api.parse.com', 443)
connection.connect()
connection.request('POST', '/1/classes/Events', json.dumps({
	"Data": sys.argv[1],
	"Image": {
		"name": name,
		"__type": "File"
	}
	}), {
	"X-Parse-Application-Id": "---",
	"X-Parse-REST-API-Key": "---",
	"Content-Type": "application/json"
	})
result = json.loads(connection.getresponse().read())
print result

# Send push to Android 
connection = httplib.HTTPSConnection('api.parse.com', 443)
connection.connect()
connection.request('POST', '/1/push', json.dumps({
       "channels": [
         "Android"
       ],
       "data": {
         "alert": "New image updated."
       }
     }), {
       "X-Parse-Application-Id": "---",
       "X-Parse-REST-API-Key": "---",
       "Content-Type": "application/json"
     })
result = json.loads(connection.getresponse().read())
print result

