<?php

require_once('dbConnect.php');

// Get date and day values from Android app
$dat = $_POST["date"];
$day = $_POST["days"];
$tim = $_POST["time"];
$did= $_POST["doctor_id"];

//$dat = '2023-02-03';
//$day = 'Monday';
//$tim ='24:00';
//$did= '1';

// Insert new row to work_days table
$sql = "INSERT INTO work_days (date, days,time,doctor_id) VALUES ('$dat', '$day','$tim','$did')";
if ($conn->query($sql) === TRUE) {
    echo "New row added successfully to work_days table";
} else {
    echo "Error adding new row: " . $conn->error;
}

// Close database connection
$conn->close();
?>