# Geographic Information System

## This project was built using Intellij IDE.

### This project builds a KML file from a given CSV folder/file, see: https://developers.google.com/kml/

### Resources used:
https://gis.stackexchange.com/questions/108171/calculating-azimuth-from-two-points-both-having-latitude-longtiude
https://www.movable-type.co.uk/scripts/latlong.html
https://en.wikipedia.org/wiki/Geographic_coordinate_system#Expressing_latitude_and_longitude_as_linear_units
https://stackoverflow.com/questions/7486012/static-classes-in-java
https://stackoverflow.com/questions/5125242/java-list-only-subdirectories-from-a-directory-not-files
https://developers.google.com/kml/documentation/kml_tut
http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
https://stackoverflow.com/questions/23520208/how-to-create-xml-file-with-specific-structure-in-java
https://stackoverflow.com/questions/1937684/java-saving-streamresult-to-a-file

### How to use:

You can run the driver class or simply use the following syntax:
NOTE: FOR EASE OF USE, PUT FILES/FOLDERS IN THE FILES FOLDER OF THE PROJECT THEN USE THE RELATIVE PATH LIKE THE FOLLOWING: /Files/-->PATH.

#### To parse info:
Layer_GIS layer = (Layer_GIS) CsvReader.parsing("CSV FILE TO PARSE"); 
Project_GIS project = (Project_GIS) MultiCSV.readFromFolder("FOLDER TO PARSE");

#### To build KML file from info:
WriteToKML.FromFolder(project);
WriteToKML.FromFile(layer);

### Project_GIS (Folder) --> Layer_GIS (File) --> Element_GIS (Line).
### You can find explination about each function in detail within the JavaDoc folder.