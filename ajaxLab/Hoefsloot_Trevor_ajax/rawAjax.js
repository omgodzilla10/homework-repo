var ajax = new XMLHttpRequest();

var opacity = 1;


function getCitiesFromProvince() {
    "use strict";

    var province, url, animation;
    province = document.getElementById("provSelect").value;
    
    var animation = document.getElementById("animation");
    animation.innerHTML = ('<img id="loading" src="../images/ajax-loader.gif" alt="Currently Loading" />');
    
    var fadeOut = function() {
        opacity -= 0.9;
        document.getElementById("animation").style.opacity = opacity;
    }

    
    var interval = setInterval(fadeOut(), 10000);
    
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

