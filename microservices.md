#Microservices

Wie im Namen bereits erwähnt, sind Microservices jeweils viele kleine Services, die in den meisten Fällen
nur eine Aufgabe verrichten. Microservices laufen selbstständig und sind nicht voneinander abhängig.
In den meisten Fällen kümmert sich ein Microservice, um genau ein Prozess und nicht um die Prozesse außerhalb.
Sie sind daher sehr unterschiedlich zu betrachten und müssen deshalb auch unterschiedlich angesprochen werden.
Der Begriff Microservices ist auch eher irreführend, weil es sich hierbei um ein vollständiges System handelt,
welches Autonom und ohne äußere Einflüsse ("self contained") sich als Funktionsfähig erweist.

Unsere DockerContainer können als Microservices betrachtet werden, da sich diese jeweils auf eine
spezifische Datenbank kümmern und nicht von äußerlichen Faktoren abhängig sind. Im vorhandenen Programmcode
kann Hibernate als eine Art Management & Remote System betrachtet werden, da Hibernate dafür sorgt, dass die
Datenbanken mit den richtigen Daten belegt werden und es dadurch nicht zu Fehlermeldungen kommt.

Wie auch in den meisten Microservices, werden die Schnittstellen klar definiert, damit Außenstehende sich nicht
um die Umsetzung innerhalb des Services kümmern müssen, sondern diese müssen nur die Anforderungen an die
Schnittstellen beachten, um den jeweiligen Service zu verwenden.
