<?php
# load all the lines in the text file into an array where each line is
# an element in the array
$myArray = file("province_city.txt");
sort($myArray);
$result = "";
if (isset($_GET["prov"])) {
  $province = $_GET["prov"];
  # Find cities pertaining to province selected by user
  foreach ($myArray as $provCity) {
    if (preg_match("/^$province/", chop($provCity))) {
      // remove newline character from end of line
      $provCity = chop( $provCity );
      // make $province first string and $city second string
      list($province, $city) = explode("~", $provCity, 2);
      if ($result == "")
      $result .= $city;
      else
      $result .= "~" . $city;
    }
  }
}
# send result back to requesting client
echo $result;
?>
