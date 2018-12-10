##Welche Datenbank bringt die beste Performance für das gegebene Szenario?

| NumberOfInstance | MySQL | MongoDB | Neo4J | PostgreSQL | Redis | Cassandra | Infinispan |
|---|-------|-------|-------|-------|-------|-------|-------|
| 10 | 4,9 sec | 4,8 sec | 5 sec | PostgreSQL | 13,2 sec | 34 sec | 36 sec |
| 100 | 6,1 sec | 4,7 sec | 12 sec | PostgreSQL | 13,8 sec | 31 sec | 39 sec |
| 500 | 12,1 sec | 7,3 sec | 38 sec | PostgreSQL | 16,4 sec | 39 sec | 47 sec |


Wie der Tabelle zu entnehmen ist, hat MongoDB die beste Perfomance erbracht. Für alle genannten Datenbanken
wurden die CRUD Operation, mit Außnahme der update Methode, verwendet.
  
##Begründen Sie die Performanceunterschiede - warum sind diese so groß bzw. klein?

Einige Datenbanken haben relationen zwischen den einzelnen Daten. Diese führen dazu, dass das Porgramm
selbst die Transaktion langsamer ausführen kann. Darüber hinaus unterscheiden sich die Datenbanken auf die jeweiligen
CRUD Operation. Für ein KeyValue Datastore kann das hinzufügen von einer Datei schneller durchgeführt werden, wie
bespielsweise bei einer Graph Datenbank. Jede Datenbank hat somit seine Vor- und Nachteile. 
Je nach Anwendungsbereich sollte man seine Datenbank gut auswählen.

##Welche Architektur hat eine bessere Performance:

###wenn die DBs virtualisiert laufen?

| NumberOfInstance | MySQL | MongoDB | Neo4J | PostgreSQL | Redis | Cassandra | Infinispan |
|---|-------|-------|-------|-------|-------|-------|-------|
| 10 | test | test | test | test | test | test | test |
| 100 | test | test | test | test | test | test | test |
| 500 | test | test | test | test | test | test | test |

###wenn die DBs docker-basiert laufen?

| NumberOfInstance | MySQL | MongoDB | Neo4J | PostgreSQL | Redis | Cassandra | Infinispan |
|---|-------|-------|-------|-------|-------|-------|-------|
| 10 | 4,9 sec | 4,8 sec | 5 sec | PostgreSQL | 13,2 sec | 34 sec | 36 sec |
| 100 | 6,1 sec | 4,7 sec | 12 sec | PostgreSQL | 13,8 sec | 31 sec | 39 sec |
| 500 | 12,1 sec | 7,3 sec | 38 sec | PostgreSQL | 16,4 sec | 39 sec | 47 sec |



Aus der Tabelle ist zu entnehmen, dass die Perfomance der virtualisierten Datenbanken,
ein besseres Ergebnis erzielt haben, als die Datenbanken welche local auf dem Docker gelaufen sind.

begründen und belegen Sie Ihre Antwort mit entsprechenden
Performancemessungen!