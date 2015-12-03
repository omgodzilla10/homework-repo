function getCitiesFromProvince() {
  "use strict";

  var province, url;
  province = document.getElementById("provSelect").value;
  url = "cities_from_province.php?prov=" + window.encodeURI(province);
  
  ajax.open("GET", url, true);
  ajax.onreadystatechange = handleHttpResponse;
  ajax.send(null);
}
