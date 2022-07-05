# FormulaDashboard


<!-- Inhaltsverzeichnis -->
<details open="open">
  <summary>Inhaltsverzeichnis</summary>
  <ol>
    <li>
      <a href="#über-das-projekt">Über das Projekt</a>
      <ul>
        <li><a href="###verwendete-programmiersprachen">Verwendete Programmiersprachen</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#voraussetzungen">Voraussetzungen</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#Verwendung">Verwendung</a></li>
  </ol>
</details>


<!-- Über das Projekt -->
## Über das Projekt
Dieser Service wurde als Teil der Prüfungsleistung für die Veranstaltung IT-Management der Anwendungsentwicklung an der Westfälischen Hochschule von Jonas Fiener, Enrico Fröse, Arne Kottenhahn und Alexander Dall entwickelt.
Im Zuge der Ausarbeitung haben wir uns für die Entwicklung eine Dashboards über Formel-E entschieden, das wichtige Kennzahlen über die Domänen Rennen, Fahrer, Teams und Rundenzeiten visualisiert. 
```
   https://app.powerbi.com/links/BdoH4HftFG?ctid=6c89528e-0824-4e0e-ad98-cab16af8c189&pbi_source=linkShare
   ```

### Verwendete Programmiersprachen
Grundsätzlich wurde unser Dashboard angesichts der Anforderungen mit den Programmiersprachen Java und SQL umgesetzt. Als Datenmanagement-System wurde MySQL verwendet und die gehostet wurde die Datenbank in Azure.

<!-- GETTING STARTED -->
## Getting Started

Hier ist eine kleine Anleitung, wie unser Dashboard lokal installiert und gestartet werden kann. Grundsätzlich empfehlen wir die Verwendung von XAMPP zum Starten einer lokalen MySQL-Instanz, da hier bereits viele Funktionen bereitgestellt werden. Zudem sollte ein passendes Java-JDK installiert sein, um den Service auszuführen.

### Voraussetzungen
* Sportradar-Account mit Access für die [Sportradar API](https://developer.sportradar.com/docs/read/racing/Formula_E_v2)
* Installiertes [Java-JDK](https://www.azul.com/downloads/?package=jdk)
* [Eclipse](https://www.eclipse.org/downloads/) oder [IntelliJ](https://www.jetbrains.com/idea/download/#section=mac) zum Ausführen des Services
* [PowerBI-Account](https://app.powerbi.com/singleSignOn?ru=https:%2f%2fapp.powerbi.com%2f%3fnoSignUpCheck%3d1) zum Aufruf und Download des Dashboards

### Installation
1. XAMPP installieren und Server aus dem Dashboard starten
2. Das Repository clonen
    ```sh
   git clone https://github.com/DallAlexander/FormulaDashboard.git
   ```
3. Datenbank-Dump in das Datenbankmanagementsystem einpflegen, siehe: `dump.sql`
4. Die Verbindung zur lokalen Datenbank in `Service.java` herstellen
5. Anschließend die Token der API in `Service.java` hinterlegen
6. Nun kann der Service gestartet werden, infolgedessen die Datenbank mit Daten aus der API gefüllt wird
7. Letztlich können die Daten aus der lokalen MySQL-Datenbank in das PowerBI-Dashboard importiert werden

## Verwendung
Es steht jedem Entwickler frei zur Verfügung, dieses Projekt zu clonen und weiter zu verwenden.