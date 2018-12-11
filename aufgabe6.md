## Welche Datenbank bringt die beste Performance für das gegebene Szenario?

| NumberOfInstance | MySQL | MongoDB | Neo4J | Redis | Cassandra | Infinispan |
|---|-------|-------|-------|-------|-------|-------|
| 10 | 4,9 sec | 4,8 sec | 5 sec | 13,2 sec | 34 sec | 36 sec |
| 100 | 6,1 sec | 4,7 sec | 12 sec | 13,8 sec | 31 sec | 39 sec |
| 500 | 12,1 sec | 7,3 sec | 38 sec | 16,4 sec | 39 sec | 47 sec |


Wie der Tabelle zu entnehmen ist, hat MongoDB die beste Perfomance erbracht. Für alle genannten Datenbanken
wurden die CRUD Operation, mit Außnahme der update Methode, verwendet. Die Zeitspanne entspricht der Erstellung eines Kunden, das Auslesen des Kunden und wiederum löschen dieses Kunden. Alle Operationen wurden einzeln ohne Batches ausgeführt.
  
## Begründen Sie die Performanceunterschiede - warum sind diese so groß bzw. klein?

Einer der wesentlichen Unterschiede besteht zwischen den NoSQL und SQL Datenbanken. Ein Impedance mismatcht lässt sich deshalb durch z.B. mit MongoDB lösen, da es bei dieser Datenbank keine Join-Operationen gibt. Diese Operationen führen dazu, dass das Porgramm
selbst die Transaktion langsamer ausführen kann. Darüber hinaus unterscheiden sich die Datenbanken auf die jeweiligen
CRUD Operation. Für ein KeyValue Datastore kann das hinzufügen von einer Datei schneller durchgeführt werden, wie
bespielsweise bei einer Graph Datenbank. Jede Datenbank hat somit seine Vor- und Nachteile. 
Je nach Anwendungsbereich sollte man seine Datenbank gut auswählen.

## Welche Architektur hat eine bessere Performance:

### wenn die DBs virtualisiert laufen?

| NumberOfInstance | MySQL | MongoDB | Neo4J | Redis | Cassandra | Infinispan |
|---|-------|-------|-------|-------|-------|-------|
| 10 | 5,0 sec | 4,8 sec | 5,2 sec | 12,7 sec | 33,7 sec | 40,1 sec |
| 100 | 6,1 sec | 4,9 sec | 13,3 sec | 13,9 sec | 30,4 sec | 44,7 sec |
| 500 | 12,0 sec | 7,4 sec | 39,8 sec | 16,4 sec | 39,8 sec | 61,2 sec |

Tools:

* VirtualBox
* Image: Ubuntu 18.10 Cosmic Cuttlefish
* Docker CE for Ubuntu
* IntelliJ IDEA

Hardware:

* Intel Prozess i7 8700K übertaktet auf 4.3GhZ
* Nvidia GPU 1080
* RAM: 16 GB
* SSD: Samsung Evo 860 (250GB, 500GB, 500GB)

VM zugewiesene Hardware:

* Intel Prozess i7 8700K übertaktet auf 4.3GhZ x 2 Kerne
* Nvidia GPU 1080 128MB
* RAM: 2048MB
* SSD: Samsung Evo 860 (20GB)

### wenn die DBs docker-basiert laufen?

| NumberOfInstance | MySQL | MongoDB | Neo4J | Redis | Cassandra | Infinispan |
|---|-------|-------|-------|-------|-------|-------|
| 10 | 4,9 sec | 4,8 sec | 5 sec | 13,2 sec | 34 sec | 36 sec |
| 100 | 6,1 sec | 4,7 sec | 12 sec | 13,8 sec | 31 sec | 39 sec |
| 500 | 12,1 sec | 7,3 sec | 38 sec | 16,4 sec | 39 sec | 47 sec |


Tools:

* OS:Windows
* Docker CE for Windows
* IntelliJ IDEA


Hardware: 
* Intel Prozess i7 8700K übertaktet auf 4.3GHZ
* Nvidia GPU 1080
* RAM: 16 GB
* SSD: Samsung Evo 860 (250GB, 500GB, 500GB)


Laut den gegebenen Perfomancemessung, werden die Operationen auf der docker-basierten Lösung
schneller ausgeführt, als auf der Virtuellen Maschine. Zwischen der Virtuellen Maschine und dem Host
liegt eine Schicht, die für den Rückstand verantwortlich ist.
