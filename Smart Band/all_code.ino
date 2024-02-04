#include <TouchScreen.h> // Touch library
#include <LCDWIKI_GUI.h> // Core graphics library
#include <LCDWIKI_KBV.h> // Hardware-specific library

// Touchscreen pins
#define YP A3 // Must be an analog pin, use "An" notation!
#define XM A2 // Must be an analog pin, use "An" notation!
#define YM 9  // Can be a digital pin
#define XP 8  // Can be a digital pin

// LCD dimensions and pins
LCDWIKI_KBV my_lcd(240, 320, A3, A2, A1, A0, A4); // Width, height, cs, cd, wr, rd, reset

// Touchscreen sensitivity thresholds
#define TS_MINX 124
#define TS_MAXX 906
#define TS_MINY 83
#define TS_MAXY 893
#define MINPRESSURE 10
#define MAXPRESSURE 1000

// Button colors
#define BLACK 0x0000
#define WHITE 0xFFFF
#define RED 0xF800
#define GREEN 0x07E0
#define BLUE 0x001F

// UI button details
#define BUTTON_R 40          // Radius of button
#define BUTTON_SPACING_X 10  // Horizontal distance between buttons
#define BUTTON_SPACING_Y 4   // Vertical distance between buttons
#define EDG_Y 5              // Lower edge distance
#define EDG_X 7              // Left and right distance

// Page identifiers
#define Welcome_Page 1
#define Page_1 2
#define Page_2 3
#define Page_3 4

// Button struct
typedef struct _button_info {
  uint8_t button_name[10];
  uint8_t button_name_size;
  uint16_t button_name_colour;
  uint16_t button_colour;
  uint16_t button_x;
  uint16_t button_y;
} button_info;

// Define buttons
button_info phone_button[2] = {
  {"HR&SPO2", 2, WHITE, RED, EDG_X + BUTTON_R + 1, my_lcd.Get_Display_Height() - EDG_Y - 2 * BUTTON_SPACING_Y - 3 * BUTTON_R - 1},
  {"TEMP", 2, WHITE, RED, EDG_X + 4 * BUTTON_R + 2 * BUTTON_SPACING_X - 1, my_lcd.Get_Display_Height() - EDG_Y - 1 * BUTTON_SPACING_Y - 3 * BUTTON_R - 1}
};

// Function prototypes
void welcome_page();
void Vitals_Menu();
void show_string(uint8_t *str, int16_t x, int16_t y, uint8_t csize, uint16_t fc, uint16_t bc, boolean mode);
void page_1();
void page_2();
void page_3();
void Navigation(int pagename);
boolean is_pressed(int16_t x1, int16_t y1, int16_t x2, int16_t y2, int16_t px, int16_t py);
char Read_Sensor1();
char Read_Sensor2();

void setup() {
  Serial.begin(9600);
  my_lcd.Init_LCD();
  Navigation(Welcome_Page);
}

void loop() {
  uint16_t i;
  digitalWrite(13, HIGH);
  TSPoint p = ts.getPoint();
  digitalWrite(13, LOW);

  pinMode(XM, OUTPUT);
  pinMode(YP, OUTPUT);

  if (p.z > MINPRESSURE && p.z < MAXPRESSURE) {
    p.x = map(p.x, TS_MINX, TS_MAXX, 0, my_lcd.Get_Display_Width());
    p.y = map(p.y, TS_MINY, TS_MAXY, 0, my_lcd.Get_Display_Height());

    for (i = 0; i < sizeof(phone_button) / sizeof(button_info); i++) {
      if (is_pressed(phone_button[i].button_x - BUTTON_R, phone_button[i].button_y - BUTTON_R, phone_button[i].button_x + BUTTON_R, phone_button[i].button_y + BUTTON_R, p.x, p.y)) {
        // Button press handling
        if (i == 0) {
          Navigation(Page_2);
        } else {
          Navigation(Page_3);
        }
      }
    }
  }
}

void welcome_page() {
  my_lcd.Set_Rotation(-1);
  my_lcd.Set_Text_Mode(0);
  my_lcd.Fill_Screen(BLACK);
  my_lcd.Fill_Screen(BLACK);
  my_lcd.Set_Text_colour(GREEN);
  my_lcd.Set_Text_Size(3);
  my_lcd.Print_String("Welcome to ", 70, 60);
  my_lcd.Set_Text_colour(GREEN);
  my_lcd.Set_Text_Size(3);
  my_lcd.Print_String("our application ", 20, 120);
  delay(1000);
  Navigation(Page_1);
}

void Vitals_Menu() {
  uint16_t i;
  for (i = 0; i < sizeof(phone_button) / sizeof(button_info); i++) {
    my_lcd.Set_Draw_color(phone_button[i].button_colour);
    my_lcd.Fill_Circle(phone_button[i].button_x, phone_button[i].button_y, BUTTON_R);
    show_string(phone_button[i].button_name, phone_button[i].button_x - strlen(phone_button[i].button_name) * phone_button[i].button_name_size * 1 / 2 + 1, phone_button[i].button_y - phone_button[i].button_name_size * 2 / 2 + 1, phone_button[i].button_name_size, phone_button[i].button_name_colour, BLACK, 1);
  }
  my_lcd.Set_Draw_color(BLACK);
  my_lcd.Draw_Rectangle(1, 1, my_lcd.Get_Display_Width() - 2, 31);
  my_lcd.Draw_Rectangle(1, 29, my_lcd.Get_Display_Width() - 2, 31);
  my_lcd.Draw_Rectangle(1, 1, 31, 31);
  my_lcd.Draw_Rectangle(my_lcd.Get_Display_Width() - 4, 1, my_lcd.Get_Display_Width() - 2, 31);
}

void show_string(uint8_t *str, int16_t x, int16_t y, uint8_t csize, uint16_t fc, uint16_t bc, boolean mode) {
  my_lcd.Set_Text_Mode(mode);
  my_lcd.Set_Text_Size(csize);
  my_lcd.Set_Text_colour(fc);
  my_lcd.Set_Text_Back_colour(bc);
  my_lcd.Print_String(str, x, y);
}

void page_1() {
  my_lcd.Set_Rotation(0);
  my_lcd.Fill_Screen(BLACK);
  my_lcd.Set_Text_Mode(0);
  my_lcd.Fill_Screen(BLACK);
  my_lcd.Set_Text_Back_colour(BLACK);
  my_lcd.Set_Text_colour(GREEN);
  my_lcd.Set_Text_Size(2);
  my_lcd.Print_String("Your Vital signs", 35, 60);
  Vitals_Menu();
}

void page_2() {
  my_lcd.Fill_Screen(BLACK);
  my_lcd.Set_Text_Mode(0);
  my_lcd.Set_Text_colour(GREEN);
  my_lcd.Set_Text_Size(2);
  long data1 = Read_Sensor1();
  long data2 = Read_Sensor2();
  my_lcd.Get_Text_Mode();
  my_lcd.Print_String("SPO2 :", 0, 0);
  my_lcd.Get_Text_Mode();
  my_lcd.Print_Number_Int(data1, 0, 24, 0, ' ', 10);
  my_lcd.Get_Text_Mode();
  my_lcd.Print_String("Heart Rate :", 0, 48);
  my_lcd.Get_Text_Mode();
  my_lcd.Print_Number_Int(data2, 0, 72, 0, ' ', 10);
}

void page_3() {
  my_lcd.Fill_Screen(BLACK);
  my_lcd.Set_Text_Mode(0);
  int temperature;
  int reading = analogRead(A7);
  temperature = (5.0 * reading * 100.0) / 1024.0;
  my_lcd.Set_Text_colour(GREEN);
  my_lcd.Set_Text_Size(2);
  my_lcd.Get_Text_Mode();
  my_lcd.Print_String("Temperature :", 0, 0);
  my_lcd.Get_Text_Mode();
  my_lcd.Print_Number_Int(temperature, 0, 24, 0, ' ', 10);
}

void Navigation(int pagename) {
  switch (pagename) {
    case Welcome_Page:
      welcome_page();
      delay(1000);
      break;
    case Page_1:
      page_1();
      delay(1000);
      break;
    case Page_2:
      page_2();
      delay(1000);
      break;
    case Page_3:
      page_3();
      delay(1000);
      break;
  }
}

boolean is_pressed(int16_t x1, int16_t y1, int16_t x2, int16_t y2, int16_t px, int16_t py) {
  if ((px > x1 && px < x2) && (py > y1 && py < y2)) {
    return true;
  } else {
    return false;
  }
}

char Read_Sensor1() {
  pinMode(12, INPUT);
  while (1) {
    if (digitalRead(12)) {
      if (Serial.available() > 0) {
        Serial.flush();
        char data = Serial.read();
        return data;
      }
    }
  }
}

char Read_Sensor2() {
  pinMode(13, INPUT);
  while (1) {
    if (digitalRead(13)) {
      if (Serial.available() > 0) {
        Serial.flush();
        char data = Serial.read();
        return data;
      }
    }
  }
}
