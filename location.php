<?php
 
$host='iix83.rumahweb.com';  //nama host lu
 
$uname=''; //username
 
$pwd=''; // password
 
$db=""; // db

$con = mysqli_connect($host,$uname,$pwd,$db);
// Check connection
if (!$con) {
    die("Connection failed: " . mysqli_connect_error());
}
 
 
$query=mysqli_query($con, "select * from Location");  //ngambil semua data dari tabel lu
 
while($row=mysqli_fetch_array($query))
{
	$flag[]=$row;
}
 echo json_encode(array('FL' => $flag));  //json output

mysqli_close($con);
?>