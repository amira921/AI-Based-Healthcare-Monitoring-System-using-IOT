<?php
 
	
	define('HOST','localhost');
	define('USER','root');
	define('PASS','');
	define('DB','dpuphysical');
	
	$conn = mysqli_connect(HOST,USER ,PASS)or die ("Unable to connect to server");
        @mysqli_select_db($conn,DB) or die ("Unable to connect to server");
        mysqli_set_charset($conn, 'utf8');
       
