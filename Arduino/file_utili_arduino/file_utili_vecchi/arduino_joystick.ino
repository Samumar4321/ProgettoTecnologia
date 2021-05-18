//define pin joystick
#define joystickX A3
#define joystickY A4
#define tasto 2
void setup() {
  //inizializzone seriale
  Serial.begin(9600);
  //inizializzazione pin joystick
  pinMode(joystickX,INPUT);
  pinMode(joystickY,INPUT);
  pinMode(tasto, INPUT_PULLUP);
}
//definizione variabili
int asseX=0;
int asseY=0;
int bottone=0;
String invio="";
 
void loop() 
{
  leggiJoystick();
  invioSeriale();
  delay(150);
}

void leggiJoystick()
{
  //leggo i valori dell'asse x, dell'asse y e del bottone del joystick
  asseX = analogRead(joystickX);
  asseY = analogRead(joystickY);
  bottone=!digitalRead(tasto);
}

void invioSeriale()
{
  //invio una stringa contenente i valori del joystick
  invio=String(asseX)+"-"+String(asseY)+"-"+String(bottone);
  Serial.print(invio);
}
