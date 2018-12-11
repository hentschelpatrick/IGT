## Welche Datenbank bringt die beste Performance für das gegebene Szenario?

| NumberOfInstance | MySQL | MongoDB | Neo4J | PostgreSQL | Redis | Cassandra | Infinispan |
|---|-------|-------|-------|-------|-------|-------|-------|
| 10 | 4,9 sec | 4,8 sec | 5 sec | PostgreSQL | 13,2 sec | 34 sec | 36 sec |
| 100 | 6,1 sec | 4,7 sec | 12 sec | PostgreSQL | 13,8 sec | 31 sec | 39 sec |
| 500 | 12,1 sec | 7,3 sec | 38 sec | PostgreSQL | 16,4 sec | 39 sec | 47 sec |


Wie der Tabelle zu entnehmen ist, hat MongoDB die beste Perfomance erbracht. Für alle genannten Datenbanken
wurden die CRUD Operation, mit Außnahme der update Methode, verwendet.
  
## Begründen Sie die Performanceunterschiede - warum sind diese so groß bzw. klein?

Einige Datenbanken haben relationen zwischen den einzelnen Daten. Diese führen dazu, dass das Porgramm
selbst die Transaktion langsamer ausführen kann. Darüber hinaus unterscheiden sich die Datenbanken auf die jeweiligen
CRUD Operation. Für ein KeyValue Datastore kann das hinzufügen von einer Datei schneller durchgeführt werden, wie
bespielsweise bei einer Graph Datenbank. Jede Datenbank hat somit seine Vor- und Nachteile. 
Je nach Anwendungsbereich sollte man seine Datenbank gut auswählen.

## Welche Architektur hat eine bessere Performance:

### wenn die DBs virtualisiert laufen?

| NumberOfInstance | MySQL | MongoDB | Neo4J | PostgreSQL | Redis | Cassandra | Infinispan |
|---|-------|-------|-------|-------|-------|-------|-------|
| 10 | 7,2 sec | 5,2 sec | 5,4 sec | PostgreSQL | 11,2 sec | 29,7 sec | 40,1 sec |
| 100 | 9 sec | 5,6 sec | 13,1 sec | PostgreSQL | 19,8 sec | 34,4 sec | 46,7 sec |
| 500 | 10,3 sec | 9,2 sec | 41,8 sec | PostgreSQL | 22,4 sec | 44,8 sec | 61,2 sec |

Tools:

* VirtualBox
* Image: Ubuntu 18.10 Cosmic Cuttlefish
* Docker CE for Ubuntu
* IntelliJ IDEA

### wenn die DBs docker-basiert laufen?

| NumberOfInstance | MySQL | MongoDB | Neo4J | PostgreSQL | Redis | Cassandra | Infinispan |
|---|-------|-------|-------|-------|-------|-------|-------|
| 10 | 4,9 sec | 4,8 sec | 5 sec | PostgreSQL | 13,2 sec | 34 sec | 36 sec |
| 100 | 6,1 sec | 4,7 sec | 12 sec | PostgreSQL | 13,8 sec | 31 sec | 39 sec |
| 500 | 12,1 sec | 7,3 sec | 38 sec | PostgreSQL | 16,4 sec | 39 sec | 47 sec |


Tools:

* OS:Ubuntu
* Docker CE for Ubuntu
* IntelliJ IDEA


Laut den gegebenen Perfomance Messung, werden die Operationen auf der docker-basierten Lösung
schneller ausgeführt, als auf der Virtuellen Maschine. Zwischen der Virtuellen Maschine und dem Host
liegt eine Schicht, die für den Rückstand verantwortlich ist.
