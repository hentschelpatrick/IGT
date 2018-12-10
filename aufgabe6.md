##Welche Datenbank bringt die beste Performance für das gegebene Szenario?

| NumberOfInstance | MySQL | MongoDB | Neo4J | PostgreSQL | Redis | Cassandra | Infinispan |
|---|-------|-------|-------|-------|-------|-------|-------|
| 10 | 4,9 sec | 4,8 sec | 5 sec | PostgreSQL | 13,2 sec | 34 sec | 36 sec |
| 100 | 6,1 sec | 4,7 sec | 12 sec | PostgreSQL | 13,8 sec | 31 sec | 39 sec |
| 500 | 12,1 sec | 7,3 sec | 38 sec | PostgreSQL | 16,4 sec | 39 sec | 47 sec |

Die Key-Value Datenbank, da diese sofort auf die Werte zugreifen kann, sobald diese gefunden wurden.
  
##Begründen Sie die Performanceunterschiede - warum sind diese so groß bzw. klein?

Einige Datenbanken haben relationen zwischen den einzelnen Daten. Diese führen dazu, dass das Porgramm
selbst langsamer läuft.

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

begründen und belegen Sie Ihre Antwort mit entsprechenden
Performancemessungen!