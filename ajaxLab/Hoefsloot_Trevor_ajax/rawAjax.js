var ajax = new XMLHttpRequest();


function getCitiesFromProvince() {
    "use strict";

    var province, url, animation;
    province = document.getElementById("provSelect").value;

    var animation = document.getElementById("animation");
    animation.innerHTML = ('<img id="loading" src="../images/ajax-loader.gif" alt="Currently Loading" />');

    animation.style.opacity = 1;
    var interval = setInterval(function () {
      animation.style.opacity -= 0.1;
    }, 100);

    url = "cities_from_province.php?prov=" + window.encodeURI(province);

    ajax.open("GET", url, true);
    ajax.onreadystatechange = handleHttpResponse;
    ajax.send(null);
}

function handleHttpResponse() {
    "use strict";
     var cities;
     if (ajax.readyState == 4 && ajax.status == 200) {
        cities = ajax.responseText;
        var splitCities = cities.split("~");

        //Clear the dropdown list
        var cityDropDown = document.getElementById("citySelect");
        cityDropDown.options.length=0;

        //Populate the dropdown list with new options
        for(var ndx = 0; ndx < splitCities.length; ndx++) {
            cityDropDown.options[ndx] = new Option(splitCities[ndx]);
        }
    }
}
/*
var fadeOut = function() {
    opacity -= 0.000000001;
    document.getElementById("animation").style.opacity = opacity;
}
*/
