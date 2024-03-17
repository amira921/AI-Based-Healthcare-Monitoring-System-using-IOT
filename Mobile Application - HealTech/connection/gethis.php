<?php 
require "conn.php";

$id = $_GET['id'];
// $id = 20303140102388;
	
	$sql = "SELECT temperture,oxygen,heart_rate,blood_pressure,time,date FROM patient_vitals WHERE ID=$id";
	$r = mysqli_query($conn,$sql);
	
	$result = array();
	

		//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"htemp"=>$row['temperture'],
			"hoxgen"=>$row['oxygen'],
			"hheart_pulse"=>$row['heart_rate'],
			"hpressure"=>$row['blood_pressure'],
			"htime"=>$row['time'],
			"hdate"=>$row['date'],
		));
	}
	echo json_encode(array('result'=>$result));
	
	mysqli_close($conn);
	
	