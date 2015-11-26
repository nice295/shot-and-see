#include <Bridge.h>

// this constant won't change.  It's the pin number of the sensor's output:
const int pingPin = 7;
int initDistance = 0;

void setup() {
  Serial.begin(9600);

  // for debugging, wait until a serial console is connected
  delay(4000);
  while(!Serial);

  Bridge.begin();
}

void loop()
{
  int currentDistance = 0;
  
  if (initDistance == 0)
  {
     initDistance = getDistance();
  }

  // Check who there is
  currentDistance = getDistance();
  Serial.println("Now (" + String(currentDistance) + " cm), Initial (" + String(initDistance) + " cm)");
  if ( currentDistance == 0 || abs(currentDistance - initDistance) < 10 )
  {
    delay(1000);
    return;
  }

  // Detect
  Process picture;
  picture.runShellCommand("python /www/nice295/upload.py " + String(currentDistance));
  while(picture.running());
  
  Serial.print("Success to upload pic.jpg");
  Serial.println();
}

int getDistance()
{
  // establish variables for duration of the ping,
  // and the distance result in inches and centimeters:
  long duration, cm;

  // The PING))) is triggered by a HIGH pulse of 2 or more microseconds.
  // Give a short LOW pulse beforehand to ensure a clean HIGH pulse:
  pinMode(pingPin, OUTPUT);
  digitalWrite(pingPin, LOW);
  delayMicroseconds(2);
  digitalWrite(pingPin, HIGH);
  delayMicroseconds(5);
  digitalWrite(pingPin, LOW);

  // The same pin is used to read the signal from the PING))): a HIGH
  // pulse whose duration is the time (in microseconds) from the sending
  // of the ping to the reception of its echo off of an object.
  pinMode(pingPin, INPUT);
  duration = pulseIn(pingPin, HIGH);

  // convert the time into a distance
  cm = microsecondsToCentimeters(duration);

  return cm;
}

long microsecondsToCentimeters(long microseconds)
{
  // The speed of sound is 340 m/s or 29 microseconds per centimeter.
  // The ping travels out and back, so to find the distance of the
  // object we take half of the distance travelled.
  return microseconds / 29 / 2;
}
