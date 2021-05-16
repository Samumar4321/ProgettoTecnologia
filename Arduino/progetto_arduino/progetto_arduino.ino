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
int tempMin = 0;
int luceMax = 0;
int luceMin = 0;
int umMax = 0;
int umMin = 0;
char stato = " ";




void setup() {
  pinMode(ledR,OUTPUT);
  pinMode(buzzer,OUTPUT);
  Serial.begin(9600);
}

void loop() {
  char c = ' ';
  c = leggiFino(';');
  if(c == 's')
  {
      leggiSeriale();
  }

  setVariabili();

 invioDati(umiditySensorValue,lum,tempValue);

 controlloLuce&Buzzer();
}

void controlloLuce&Buzzer()
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
  lum = VLuminosita();
  tempValue = VTemp();
}

int VUmidita()
{
  int um = analogRead(A0);
  return um;
}
int VTemp()
{
  tempValue = analogRead(A2);
  cent = ((tempValue * 0.00488) - 0.5) / 0.01;
  return cent;
   
}
int VLuminosita()
{
  lum = analogRead(A1);
  return lum;
}
void invioDati(int umiditySensorValue, int lum, int cent)
{
  String csv="";
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
