<?php
  $myArray = file("province_city.txt");
  sort($myArray);
?>
    <!DOCTYPE HTML>
    <html lang="en">

    <head>
        <title>Canada</title>
        <meta charset="utf-8" />
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" href="css/canada.css" type="text/css" />
        <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="jQueryAjax.js"></script> 
    </head>

    <body>
        <div class="container">
            <div class="jumbotron">
                <h1>Canadian provinces &amp; cities</h1>
            </div>
            <form role="form" class="form-horizontal">
                <div class="form-group">
                    <label for="provSelect" class="control-label col-sm-4">Provinces: </label>
                    <div class="col-sm-4">
                        <select id="provSelect" name="provSelect">
                            <?php
                          $aProv = "";
                          foreach ($myArray as $provCity) {
                            if (preg_match("/[~]/", trim($provCity))) {
                              // remove newline character from end of line
                              $provCity = chop( $provCity );
                              // make $province first string and $city second string
                              list($province, $city) = explode("~", $provCity, 2);
                              if ($aProv != $province) {
                                echo "<option value='$province'>" . $province . "</option>";
                                $aProv = $province;
                              }
                            }
                          }
                        ?>
                        </select>
                    </div>
                    <div class="col-sm-4"></div>
                </div>
                <div class="form-group">
                    <label for="citySelect" class="control-label col-sm-4">City: </label>
                    <div class="col-sm-4">
                        <select id="citySelect">
                            <?php
                          foreach ($myArray as $provCity) {
                            if (preg_match("/^Alberta/", trim($provCity))) {
                              // remove newline character from end of line
                              $provCity = chop( $provCity );
                              // make $province first string and $city second string
                              list($province, $city) = explode("~", $provCity, 2);
                              echo "<option>" . $city . "</option>";
                            }
                          }
                        ?>
                        </select>
                    </div>
                    <div class="col-sm-4"></div>
                </div>
                <div id="animation"></div>
            </form>
        </div>
    </body>

    </html>
