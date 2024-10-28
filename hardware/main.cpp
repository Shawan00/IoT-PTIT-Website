#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <DHT.h>
#include <ESP8266HTTPClient.h>

const char* ssid = "FPT Telecom-5D76";          
const char* password = "12356789";  
const char* mqttServer = "192.168.1.12"; 
const int mqttPort = 1883;                

#define DHTPIN D4           
#define DHTTYPE DHT11       
DHT dht(DHTPIN, DHTTYPE);

#define LED1 D5            
#define LED2 D6            
#define LED3 D7            

WiFiClient espWifiClient;
PubSubClient mqttClient(espWifiClient);

void connectToWiFi();
void reconnect();
void mqttCallback(char* topic, byte* payload, unsigned int length);

void setup() {
  Serial.begin(115200);
  dht.begin();

  pinMode(LED1, OUTPUT);
  pinMode(LED2, OUTPUT);
  pinMode(LED3, OUTPUT);

  connectToWiFi();
  
  mqttClient.setServer(mqttServer, mqttPort);
  mqttClient.setCallback(mqttCallback);
}

void loop() {
  if (!mqttClient.connected()) {
    reconnect();
  }
  mqttClient.loop();

  float h = dht.readHumidity();
  float t = dht.readTemperature();
  int lightLevel = analogRead(A0); 

  if (isnan(h) || isnan(t)) {
    Serial.println("Không thể đọc từ cảm biến DHT!");
    return;
  }

  String mqttPayload = String("Temp: ") + t + ", Hum: " + h + ", Light: " + lightLevel;
  mqttClient.publish("sensor/data", mqttPayload.c_str());

  if (WiFi.status() == WL_CONNECTED) {  
    String url = "http://192.168.1.12:8080/api/iot/data-sensor";
    WiFiClient wifiClient; 
    HTTPClient http;

    http.begin(wifiClient, url);
    http.addHeader("Content-Type", "application/json");

    String httpPayload = "{\"temperature\":" + String(t) + ",\"humidity\":" + String(h) + ",\"brightness\":" + String(lightLevel) + "}";
    int httpCode = http.POST(httpPayload);

    if (httpCode == HTTP_CODE_OK) {
      Serial.println("Data sent successfully");
    } else {
      Serial.println(httpCode);
      Serial.println("Error sending data");
      Serial.println(http.getString());
    }
    http.end();
  }

  delay(2000); 
}


void connectToWiFi() {
  Serial.print("Kết nối đến WiFi");
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println(" Đã kết nối!");
}

void reconnect() {
  while (!mqttClient.connected()) {
    Serial.print("Đang kết nối đến MQTT...");
    if (mqttClient.connect("ESP8266Client")) {
      Serial.println("Đã kết nối đến MQTT!");
      mqttClient.subscribe("led/control");
    } else {
      Serial.print("Lỗi kết nối, mã lỗi: ");
      Serial.print(mqttClient.state());
      delay(2000);
    }
  }
}

void mqttCallback(char* topic, byte* payload, unsigned int length) {
  String message;
  for (int i = 0; i < length; i++) {
    message += (char)payload[i];
  }

  if (strcmp(topic, "led/control") == 0) {
    if (message == "led1/on") {
      digitalWrite(LED1, HIGH);
    } else if (message == "led1/off") {
      digitalWrite(LED1, LOW);
    } else if (message == "led2/on") {
      digitalWrite(LED2, HIGH);
    } else if (message == "led2/off") {
      digitalWrite(LED2, LOW);
    } else if (message == "led3/on") {
      digitalWrite(LED3, HIGH);
    } else if (message == "led3/off") {
      digitalWrite(LED3, LOW);
    } else if (message == "led/on") {
      digitalWrite(LED1, HIGH);
      digitalWrite(LED2, HIGH);
      digitalWrite(LED3, HIGH);
    } else if (message == "led/off") {
      digitalWrite(LED1, LOW);
      digitalWrite(LED2, LOW);
      digitalWrite(LED3, LOW);
    }
  }
}
