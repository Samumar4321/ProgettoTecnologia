//definizione pin motori
#define enSinistra 9
#define enDestra 3
#define sinistra1 12
#define sinistra2 13
#define destra1 10
#define destra2 11
//definizione pin leds
#define luciDavanti1 2
#define luciDavanti2 4
#define luciDietro 8
//definizione pin buzzer
#define buzzer 7
//definizione pin sensore ultrasuoni
const int pinTrigger=6;
const int pinEcho=5;

//definizione variabili
int  asseX, asseY;
int velocitaSinistra;
int velocitaDestra;
int distanza;
int ledDavanti;
int ledDietro;
int clacson;

void setup() {
  //setup motori
  pinMode(enSinistra, OUTPUT);
  pinMode(enDestra, OUTPUT);
  pinMode(sinistra1, OUTPUT);
  pinMode(sinistra2, OUTPUT);
  pinMode(destra1, OUTPUT);
  pinMode(destra2, OUTPUT);
  //setup luci
  pinMode(luciDavanti1,OUTPUT);
  pinMode(luciDavanti2,OUTPUT);
  pinMode(luciDietro,OUTPUT);
  //setup ultrasuoni
  pinMode(pinTrigger,OUTPUT);
  pinMode(pinEcho,INPUT);
  //setup comunicazione seriale
  Serial.begin(9600);
  //inizializzazione variabili
  inizializzazioneVariabili();
}

void loop() 
{
  leggiSeriale();
  controlloMotori();
  controlloLuci();
  controlloClacson();
  invioDistanza();
  delay(40);
}


void controlloMotori()
{
  //se l'asseY è minore di 470 faccio andare i motori in avanti
  if (asseY < 470)
  {
    //imposto i motori di sinistra in avanti
    digitalWrite(sinistra1, HIGH);
    digitalWrite(sinistra2, LOW);
    //imposto i motori di destra in avanti
    digitalWrite(destra1, HIGH);
    digitalWrite(destra2, LOW);
    //mappo la posizione dell'asseY in un valore PWM(0-255) che viene usato per regolare la velocità dei motori
    velocitaSinistra = map(asseY, 470, 0, 0, 255);
    velocitaDestra = map(asseY, 470, 0, 0, 255);
  }
  //se l'asseY è maggiore di 550 faccio andare i motori all'indietro
  else if (asseY > 550)
  {
    //imposto i motori di sinistra all'indietro
    digitalWrite(sinistra1, LOW);
    digitalWrite(sinistra2, HIGH);
    //imposto i motori di destra  all'indietro
    digitalWrite(destra1, LOW);
    digitalWrite(destra2, HIGH);
    //mappo la posizione dell'asseY in un valore PWM(0-255) che viene usato per regolare la velocità dei motori
    velocitaSinistra = map(asseY, 550, 1023, 0, 255);
    velocitaDestra = map(asseY, 550, 1023, 0, 255);
  }
  //se il joystick si trova al centro imposto la velocita dei motori a 0 per fermare la macchina
  else
  {
    velocitaSinistra = 0;
    velocitaDestra = 0;
  }
  //se l'asseX è minore di 470 faccio andare la macchina verso sinistra
  if (asseX < 470)
  {
    //mappo la posizione dell'asseX in un valore PWM(0-255) che viene usato per regolare la velocità dei motori
    int xMappato = map(asseX, 470, 0, 0, 255);
    //per far andare la macchina verso sinistra riduco la velocita dei motori di sinistra e aumento la velocita di quelli di destra
    velocitaSinistra = velocitaSinistra - xMappato;
    velocitaDestra = velocitaDestra + xMappato;
    //per evitare errori controllo se le velocita escono dal range 0-255
    if (velocitaSinistra < 0) {
      velocitaSinistra = 0;
    }
    if (velocitaDestra > 255) {
      velocitaDestra = 255;
    }
  }
  //se l'asseX è maggiore di 550 faccio andare la macchina verso destra
  else if (asseX > 550)
  {
    //mappo la posizione dell'asseX in un valore PWM(0-255) che viene usato per regolare la velocità dei motori
    int xMappato = map(asseX, 550, 1023, 0, 255);
    //per far andare la macchina verso destra riduco la velocita dei motori di destra e aumento la velocita di quelli di sinistra
    velocitaSinistra = velocitaSinistra + xMappato;
    velocitaDestra = velocitaDestra - xMappato;
    //per evitare errori controllo se le velocita escono dal range 0-255
    if (velocitaSinistra > 255) {
      velocitaSinistra = 255;
    }
    if (velocitaDestra < 0) {
      velocitaDestra = 0;
    }
  }
  //evito il problema di ronzio dei motori ad una velocita troppo bassa (i nostri motori sotto il valore 125 ronzano e non girano)
  if (velocitaSinistra < 125) {
    velocitaSinistra = 0;
  }
  if (velocitaDestra < 125) {
    velocitaDestra = 0;
  }
  //invio la velocita ai motori di sinistra
  analogWrite(enSinistra, velocitaSinistra);
  //invio la velocita ai motori di destra
  analogWrite(enDestra, velocitaDestra);
}
void controlloLuci()
{
  //se ledDavanti==1 accendo i due led frontali, se ledDavanti==0 li spengo
  if(ledDavanti==1)
  {
    digitalWrite(luciDavanti1,HIGH);
    digitalWrite(luciDavanti2,HIGH);
  }
  else if(ledDavanti==0)
  {
    digitalWrite(luciDavanti1,LOW);
    digitalWrite(luciDavanti2,LOW); 
  }
  //se ledDietro==1 accendo il led posteriore, se ledDietro==0 lo spengo
  if(ledDietro==1)
    digitalWrite(luciDietro,HIGH);
  else if(ledDietro==0)
    digitalWrite(luciDietro,LOW);
}
void controlloClacson()
{
  //se clacson==1 accendo il clacson ad una frequenza di 400hz, se clacson==0 lo spengo
  if(clacson==1)
    tone(buzzer, 400);
  else
    noTone(buzzer);
}
void invioDistanza()
{
  calcolaDistanza();
  Serial.print(distanza);
}

void calcolaDistanza()
{
  long durata=0;
  digitalWrite(pinTrigger,LOW);
  digitalWrite(pinTrigger,HIGH);
  delayMicroseconds(10);
  digitalWrite(pinTrigger,LOW);
  durata=pulseIn(pinEcho,HIGH);
  distanza=(int)durata/58.31;
}
void leggiSeriale()
{
  asseX=leggiFino('-');
  asseY=leggiFino('-');
  clacson=leggiFino('-');
  ledDavanti=leggiFino('-');
  ledDietro=leggiFino(';');
}
void inizializzazioneVariabili()
{
  asseX = 0;
  asseY = 0;
  velocitaSinistra = 0;
  velocitaDestra = 0;
  distanza = 0;
  ledDavanti=0;
  ledDietro=0;
  clacson=0;
}
int leggiFino(char terminatore)
{
  String temp="";
  //continuo a leggere dalla seriale fino a quando non leggo il carattere terminatore
  while(true)
  {
    if(Serial.available()>0)
    {
      char c=Serial.read();
      if(c==terminatore)
        return temp.toInt();
      else
        temp+=c;
    }
  }
}
