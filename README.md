# Devops-Demo-Projekt

## API
Das Projekt für diese Demo ist eine API mit CRUD-Endpunkten für eine einfache Filmdatenbank. HSQL wurde als In-Memory Datenbank verwendet, der Zugriff auf die Datenbank erfolgt mit einem JpaRepository und einem entsprechenden Entity-Objekt. Die Datenbank wird mit einer Flyway-Migration beim Start der Anwendung befüllt um einen initialen Stand zu gewährleisten.

Die Schnittstelle und das DTO werden aus einer OpenAPI-Spezifikation generiert mithilfe des OpenAPI-Generator Plugins für Maven. Das Plugin wird während des Compile Goals von Maven ausgeführt. Der Controller implementiert das generierte Interface für die API. Der Controller ruft einen Service auf der die Businesslogik kapselt und erstellt die zugehörigen ResponseEntitys.

Es werden automatische Unittests mit JUnit ausgeführt. Die Code-Coverage wird mit Jacoco sichergestellt. Die Sicherheit der Dependencys kann mit dem owasp-dependency-check Plugin geprüft werden, was derzeit auskommentiert ist.

## Pipeline
Das Repository kann über das Git-Plugin von Jenkins angebunden werden. Zum Beginn jeder Pipeline werden per Https die Änderungen des jeweiligen Branches per git checkout abgeholt.

Die Jenkins-Pipeline installiert im ersten Schritt die Dependencies des Projekts und kompiliert anschließend die Klassen mithilfe der auf dem Jenkinsserver hinterlegetn Maven Version.

Im zweiten Schritt werden die Unittests mithilfe von Maven ausgeführt. 

Der dritte Schritt erstellt mithilfe des Jacoco Maven-Plugins den Code-Coverage-Report.
An dieser Stelle sollte auch der Dependency-Check mithilfe des Owasp-Plugins ausgeführt werden, wenn das Plugin einkommentiert ist. Auskommentiert ist es derzeit, da kein API-Key für die NVD(National Vulnerability Database) zur Verfügung steht. Daher ist das prüfen der Dependencies extrem langsam, wegen den Rate-Limits der API zum abholen der bekannten Sicherheitslücken.

Abschließend wir der Jacoco-Report archiviert und auf dem Jenkinsserver zur Verfügung gestellt.