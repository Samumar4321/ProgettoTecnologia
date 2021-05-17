#include <DHT.h>
#include "DHT.h"

//definizione sensore umidità
int umiditySensorValue = 0;
//definizione fotoresistenza
const int lum = A1;
int luminosita = 0;
//definizione temperatura
int tempValue = 0;
int cent = 0;
//definizione led
#define ledR 8
#define buzzer 7
#define DHTPIN 12
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);

//definizione variabili
int tempMax = 0;
int tempMin = 0;
int luceMax = 0;
int luceMin = 0;
int umMax = 0;
int umMin = 0;
String stato = " ";


void setup() {
  pinMode(ledR,OUTPUT);
  pinMode(buzzer,OUTPUT);
  pinMode(lum, INPUT);
  Serial.begin(9600);
}

void loop() {
  /*String c = "";
  c = leggiFino(';');
  if(c == "s")
  {
      leggiSeriale();
  }*/
  setVariabili();

  invioDati(umiditySensorValue,luminosita,tempValue);

  //controlloLuceEBuzzer();
}

void controlloLuceEBuzzer()
{
  if(umiditySensorValue<umMin || umiditySensorValue>umMax)
  {
    digitalWrite(ledR,HIGH);
    tone(buzzer,400);
  }else
  {
    digitalWrite(ledR,LOW);
    noTone(buzzer);
  }
  if(cent<tempMin || cent>tempMax)
  {
    digitalWrite(ledR,HIGH);
    tone(buzzer,400);
  }else
  {
    digitalWrite(ledR,LOW);
    noTone(buzzer);
  }
  if(lum<luceMin || lum>luceMax)
  {
    digitalWrite(ledR,HIGH);
    tone(buzzer,400);
  }else
  {
    digitalWrite(ledR,LOW);
    noTone(buzzer);
  }
}

void setVariabili()
{
  umiditySensorValue = VUmidita();
  luminosita = VLuminosita();
  tempValue = VTemp();
}

int VUmidita()
{
  int um = analogRead(A0);
  return um;
}

int VTemp()
{
  int t = dht.readTemperature();
  return t;
}

int VLuminosita()
{
  int val = 0;
  val = analogRead(lum);
  return val;
}

void invioDati(int umiditySensorValue, int lum, int cent)
{
  String csv = "";
  String u = String(umiditySensorValue);
  String l = String(lum);
  String t = String(cent);
  csv = u + ";" + l + ";" + t+ ";";
  Serial.println(csv);
}

void leggiSeriale()
{
  char term1 = '-';
  char term2 = ';';
  stato = leggiFino(term1);
  umMax = leggiFino(term1).toInt();
  umMin = leggiFino(term1).toInt();
  tempMax = leggiFino(term1).toInt();
  tempMin = leggiFino(term1).toInt();
  luceMax = leggiFino(term1).toInt();
  luceMin = leggiFino(term2).toInt();
}

String leggiFino(char terminatore)
{
  String r="";
  while(true)
  {
    if(Serial.available()>0)
    {
      char c= Serial.read();
      if(c==terminatore)
      {
        return r;
      }else{
        r += c;
      }
    }
  }
  return r;
}
