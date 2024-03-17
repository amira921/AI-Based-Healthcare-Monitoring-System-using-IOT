package com.example.app_login.dbconnection;

import android.icu.util.LocaleData;

import java.time.LocalDate;

public class Config {
  //Address for scripts php
  // public static final String URL_DELETE="http://127.0.0.1/connection/delete.php?id";s
  public static final String URL_ADD= "http://192.168.1.91/connection/add.php";
 // public static final String URL_ADD_DATE_HOURS= "http://192.168.1.5/connection/addhours.php";
  //  public static final String URL_ADD_HOUR="http://192.168.1.5/connection/adddh.php";
  public static final String URL_GET_ALL= "http://192.168.1.91/connection/get.php?id=";
  public static final String URL_GET_ALLH= "http://192.168.1.91/connection/gethis.php?id=";
  public static final String URL_GET_ALLPH= "http://192.168.1.91/connection/getphis.php?id=";
 public static final String URL_GET_ALLPV= "http://192.168.1.91/connection/getphomevitals.php?id=";
 //public static final String URL_GET_ALLFD= "http://192.168.1.5/connection/getfschdoc.php?id=";
  public static final String URL_GET_ALLS= "http://192.168.1.91/connection/getsym.php?id=";
  public static final String URL_GET_ALLFS= "http://192.168.1.91/connection/getfsch.php?id=";
  public static final String URL_GET_ALLFSD= "http://192.168.1.91/connection/getfschdoc.php?id=";
  public static final String URL_DELETE= "http://192.168.1.91/connection/deleter.php?id=";
  public static final String URL_GET_TEXT= "http://192.168.1.91/connection/gettext.php?id=";


  //keys that will be used to send the request to php scripts

  //Schedule (calender,add appointment) and delete final schdeule
  public static final String KEY_D_ID="doctor_id";  //get when show final scgedule &// for select patient id &name (bta3 eldoctor dah) in his_id_table &sym_id_table
  public static final String KEY_DATE="date";
  public static final String KEY_DAY="days";
  public static final String KEY_HOUR="time";
  public static final String KEY_DAY_ID="work_day_id";//delet hour when work_day_id=work_day_id in table
  //for history
  public static final String KEY_P_ID="patient_id";//get when show history & Symptoms
  //ADD SYMPTOMS
  public static final String KEY_STOMACH_ULCER="stomach_ulcer";
  public static final String KEY_HBURN="heart_burn";
  public static final String KEY_VOMITING="vomiting";
  public static final String KEY_NAUSEA="nausea";
  public static final String KEY_DIARRHEA="diarrhea";
  public static final String KEY_INCONTINENCE="incontinence";
  public static final String KEY_BLOAT="bloating";
  public static final String KEY_CONSTIPATION="constipation";
  public static final String KEY_BLEED="bleeding";
  public static final String KEY_COUGH="cough";
  public static final String KEY_SNEEZ="neezing";
  public static final String KEY_S_NOSE="stuffy_nose";
  public static final String KEY_S_THROAT="sore_throat";
  public static final String KEY_BREATH="breathlessness";
  public static final String KEY_HEADACHE="headache";
  public static final String KEY_DIZZ="dizziness";
  public static final String KEY_BODY_ACHES="body_ahes";
  public static final String KEY_SKIN_IRRI="skin_irritation";




  //json tags
  //RETERN RESULT
  public static final String TAG_JSON_ARRAY="result";
  //CONNECT TWO ACTIVITIES
  public static final String P_ID = "p_id";



  //get for final Schedule
  public static final String TAG_DATE="date";
  public static final String TAG_DAY="work_day";
  public static final String TAG_HOUR="work_hour";
  public static final String TAG_WD_ID="work_day_id";

  //public static final String TAG_P_NAME="patient_name";
  //His&sym id ,name
  public static final String TAG_P_ID="patient_id";
  public static final String TAG_P_NAME="patient_name"; //use in final Schedule
  public static final String TAG_P_SNAME="patient_sname";


  //get patient Histoty && TEXTVIEW IN HOME(if signs are dingeres show texttview) in doctor part
  public static final String TAG_TEMP="htemp";
  public static final String TAG_OXGEN="hoxgen";
  public static final String TAG_PULSE="hheart_pulse";
  public static final String TAG_PRESSURE="hpressure";
  public static final String TAG_TIME="htime";
  public static final String TAG_HDATE="hdate";


 //get patient Histoty in patient part
 public static final String TAG_PTEMP="ptemp";
 public static final String TAG_POXGEN="poxgen";
 public static final String TAG_PHEARTRATE="pheart_pulse";
 public static final String TAG_PPRESSURE="ppressure";
 public static final String TAG_PTIME="ptime";
 public static final String TAG_PHDATE="pdate";

 //get patient Histoty && TEXTVIEW IN HOME in patient part
 public static final String TAG_PHTEMP="phtemp";
 public static final String TAG_PHOXGEN="phoxgen";
 public static final String TAG_PHHEARTRATE="phheart_pulse";
 public static final String TAG_PHPRESSURE="phpressure";




  //SYMPYOMS
  public static final String TAG_STOMACH_ULCER="sstomach_ulcer";
  public static final String TAG_HBURN="sheart_burn";
  public static final String TAG_VOMITING="svomiting";
  public static final String TAG_NAUSEA="snausea";
  public static final String TAG_DIARRHEA="sdiarrhea";
  public static final String TAG_INCONTINENCE="sincontinence";
  public static final String TAG_BLOAT="sbloating";
  public static final String TAG_CONSTIPATION="sconstipation";
  public static final String TAG_BLEED="sbleeding";
  public static final String TAG_COUGH="scough";
  public static final String TAG_SNEEZ="ssneezing";
  public static final String TAG_S_NOSE="sstuffy_nose";
  public static final String TAG_S_THROAT="ssore_throat";
  public static final String TAG_BREATH="sbreathlessness";
  public static final String TAG_HEADACHE="sheadache";
  public static final String TAG_DIZZ="sdizziness";
  public static final String TAG_BODY_ACHES="sbody_ahes";
  public static final String TAG_SKIN_IRRI="sskin_irritation";
  public static final String TAG_SDATE="sydate";








  //



}
