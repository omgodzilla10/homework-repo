$(function () { 
    "use strict"; 
    $('#provSelect').change(function () { 
        $('#animation').append('<img id="loading" src="../images/ajax-loader.gif" alt="Currently Loading" />');         
        var province = $('#provSelect').val(); 
        $.ajax({             
        url: 'cities_from_province.php',             
        type: 'GET',             
        data: 'prov=' + province,             
        success: function (result) { 
        
                $('#citySelect')[0].options.length = 0;                 
                var index, cityArray;                 
                cityArray = result.split("~");                 
                for (index in cityArray) { 
                    if ($.trim(cityArray[index]) !== "") {                        
                    $("#citySelect").append($("<option/>", {                             
                    value: cityArray[index],                             
                    text: cityArray[index] 
                        })); 
 
                    } 
                } 
                
                $('#loading').fadeOut(500, function () { 
                    $(this).remove(); 
                }); 
            } 
        }); 
    }); 
}); 
