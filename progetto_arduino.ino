//definizione sensore umidità
int umiditySensorValue = 0;
//definizione fotoresistenza
int lum = 0;
//definizione temperatura
int tempValue = 0;
int cent = 0;
//definizione led
#define ledR 8
#define buzzer 7

//definizione variabili
int tempMax = 0;
int temoMin = 0;
int luceMax = 0;
int luceMin = 0;
int umMax = 0;
int umMin = 0;




void setup() {
  pinMode(ledR,OUTPUT);
  pinMode(buzzer,OUTPUT);
  Serial.begin(9600);
}

void loop() {
  leggiSeriale();
  invioDati();
}

void invioDati()
{
  String csv="";
  umiditySensorValue = analogRead(A0);
  lum = analogRead(A1);
  tempValue = analogRead(A2);
  cent = ((tempValue * 0.00488) - 0.5) / 0.01;
  csv = umiditySensorValue + ";" + lum + ";" + cent;
  Serial.print(csv);
}



void leggiSeriale()
{
  char term1 = '-';
  char term2 = ';';
  umMax = leggiFino(term1);
  umMin = leggiFino(term1);
  tempMax = leggiFino(term1);
  tempMin = leggiFino(term1);
  luceMax = leggiFino(term1);
  luceMin = leggiFino(term2);
}

int leggiFino(char terminatore)
{
  String r="";
  while(true)
  {
    if(Serial.available()>0)
    {
      char c= Serial.read();
      if(c==terminatore)
      {
        return r.toInt();
      }else{
        r += c;
      }
    }
  }
}
