#include <Arduino.h>
#include <Wire.h>
#include "MAX30100_PulseOximeter.h"
#define REPORTING_PERIOD_MS     1000
PulseOximeter pox;
uint32_t tsLastReport = 0;
void onBeatDetected()
{
    Serial.println("Beat!");
}

void setup()
{
    Serial.begin(9600);
    if (!pox.begin(PULSEOXIMETER_DEBUGGINGMODE_PULSEDETECT)) {
        Serial.println("ERROR: Failed to initialize pulse oximeter");
        for(;;);
    }

    pox.setOnBeatDetectedCallback(onBeatDetected); 
}

void loop()
{
    pox.update();
    if (millis() - tsLastReport > REPORTING_PERIOD_MS) {
        Serial.print("Heart rate:");
        Serial.print(pox.getHeartRate());
        Serial.print("bpm / SpO2:");
        Serial.print(pox.getSpO2());
        Serial.println("%");
        tsLastReport = millis();
    }
}
