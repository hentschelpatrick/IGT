##Welche Datenbank bringt die beste Performance für das gegebene Szenario?

| NumberOfInstance | MySQL | MongoDB | Neo4J | PostgreSQL | Redis | Cassandra | Infinispan |
|---|-------|-------|-------|-------|-------|-------|-------|
| 10 | test | test | test | test | test | test | test |
| 100 | test | test | test | test | test | test | test |
| 500 | test | test | test | test | test | test | test |

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
| 10 | test | test | test | test | test | test | test |
| 100 | test | test | test | test | test | test | test |
| 500 | test | test | test | test | test | test | test |

begründen und belegen Sie Ihre Antwort mit entsprechenden
Performancemessungen!