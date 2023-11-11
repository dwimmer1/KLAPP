# KLAPP

Aufgabenstellung: Programmieren Sie eine Klettersteig Applikation, welche sowohl auf Android als auch am Web ausführbar ist und funktioniert. Diese App soll dazu dienen Klettersteige zu erstellen, anzuzeigen und sich mit anderen Klettersteigbesuchern auzutauschen. Dies soll mittels QR-Codes, welche beim Klettersteig angebracht werden möglich gemacht werden. Beim Scannen dieser Codes soll man in einen Chat-Room mit den anderen Klettersteig besuchern geworfen werden.



Umsetzung:


- Die Entwicklerumgebung ist Android-Studio

- Als Datenbank wird MySQL innerhalb des Schulnetzwerkes benutzt

- Version Control wird mit GIT umgesetzt
Tutorial: https://youtu.be/uGLQF2kUwOA

 - Datensätze für Datenbank:
   Alle Datensätze werden in englisch und in CamelCase in die Datenbank übertragen
  
    Klettersteig:

     Attribute:
      - ID [number]
      - der Name des Klettersteigs [STRING]
      - die Beschreibung des Klettersteigs [STRING]
      - die Anstiegszeit [STRING]
      - die Abstiegszeit [STRING]
      - [?] das Datum ab dem der Klettersteig begehbar ist [string]
      - [?] das Datum ab dem der Klettersteig nicht mehr begehbar ist [string]
      - der Status des Klettersteigs (begehbar/nicht begehbar) [boolean]
      - die Meereshöhe des Startpunktes [number]
      - die Meereshöhe des Endpunktes [number]
      - [?] das Bundesland in dem sich der Klettersteig befindet [STRING] (geolink?)
      - die Schwierigkeit des Klettersteigs (A-F) [STRING]
      - [?] die Ausrichtung des Klettersteigs nach den Himmelsrichtungen [STRING] (geolink?)
      - der Link zur Website des Klettersteigs (falls vorhanden) [STRING]
      - die Account-ID des Users, der den Klettersteig erstellt hat [STRING]
      - das Datum an dem der Klettersteig erstellt wurde [late DateTime?]
      - eine Liste mit den Speicherorten aller Bilder [STRING]
  
  - Account:

    Attribute:
      - der Benutzername [late STRING]
      - die E-Mail [late STRING]
      - die User-ID [late STRING]
      - [?] der Speicherpfad des Profilbildes [late STRING?]
      - die Telefonnummer des Users [late STRING?]
      - das Datum an dem der Account erstellt wurde [late DateTime]
      - die Benutzer-Rolle [string]
      


- Beim Rechtesystem wird ein User - Moderator - Admin - SuperAdmin Modell eingesetzt
  -User: Account erstellen, Klettersteige anzeigen lassen, filtern, Chat beitreten, Profildaten anpassen
  -Moderator: Gleiche Berechtigungen wie User + ist für das Moderieren verschiedener Klettersteig-Chats zuständig
  -Admin: Gleiche Berechtigungen wie der User + Klettersteige erstellen, bearbeiten und Löschen + kann Moderatoren setzen
  -SuperAdmin: Hat vollunfänglichen Zugriff auf die App mit Statisiken, Löschen von Klettersteigen + Ernennen von Admins und Moderatoren + Voller SourceCode zugriff


NICHT BEARBEITET VON ALTEM KLAPP


- Das Anzeigen der verschiedenen Klettersteige wird ebenfalls in einer List-View umgesetzt, welche diese zunächst als "Kacheln" mit 
den essenziellen Infos anzeigt:
  - Wetter
  - Offen Ja/Nein
  - Klettersteigname
  - Klettersteigbeschreibung



- Beim antippen/klicken einer dieser Kacheln soll eine Klettersteig-Ansicht mit allen Klettersteig spezifischen Daten angezeigt werden. Alle Daten des Klettersteigs
werden angezeigt. In dieser Ansicht soll eventuell eine Kommentar-Funktion integriert werden - diese ist aber noch nicht spezifisch ausgearbeitet


- Um einen "Klettersteig-Chat" beizutreten, soll wie oben beschrieben ein QR-Code gescannt werden, welcher den User in einen Klettersteig
spezifischen Chat-Room wirft. Die Funktion für den QR-Code Scanner wird mit einem Button im Hauptfenster aufgerufen. Für den QR-Code Scanner 
wird die qr_code_scanner: - Library benötigt

- Die verschiedenen Ansichten werden so angeordnet, dass beim Start der App, nach dem Login das "Haupfenster" angezeigt wird. Durch swipen nach rechts kommt man zur Chat-Ansicht
  (falls durch QR-Code bereits in Chat beigetreten). Durch swipen nach links kommt man zu der Klettersteig - Übersicht. Durch einen Button in dieser Ansicht kommt man zu der 
Klettersteig-Erstellen-Ansicht (nur wenn berechtigt).

  
