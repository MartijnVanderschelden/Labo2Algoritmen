# Kraanbewegingen op een yard - labo 2

## Eerste les
* Coordinaten systeem
* Movement: (t_p1,p2,v) --> t_travel
    * Manhatten distance
    * Kan enkel via x-as en y-as bewegen
* Trajectories: T=[t1:p1, t2:p2,...,tk:pk]
* Safety distance: d(t_1; t_2) > sigma

### Suggestie:
1. Ontwerp op papier
2. Stapsgewijze implementatie met test cases
3. Visualisatie (met exern programma)

## Algemeen
* Max 3 kranen
* Per twee
* Alle volgende labo's
* Kort verslag op het einde (2-3 pag)
* Korte verdediging
* Git
* Kranen kunnen niet overlappen: volgorde van de kranen verandert niet


## Coordinaten systeem
class crane
  * trajectory
  * int speed
  * int safetyDistance

class coordinaat
  * int x
  * int y

class trajectory
  * Arraylist<int, Coordinates> <tijd, coordinaat>

## Kranen
