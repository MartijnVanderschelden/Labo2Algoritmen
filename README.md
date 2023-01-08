# Kraanbewegingen op een containerterminal
## Algemeen
Twee mogelijke probleemsituaties
1. Een yard bekomen waarbij de hoogte van de stapels containers een bepaalde maximumhoogte niet mag overschrijden
2. Een yard bekomen waarbij alle containers eindigen hun gewenste plek (targetPosition)

## Constraints
* Max hoogte van stapel containers mag niet overschreden worden
* Containers moeten zich binnen yard bevinden
* De stacking constraints die terug te vinden zijn in: `StackingConstraints.pdf`
* Max 3 kranen
  * SafetyDistance: 1
  * Overlappen niet toegestaan
  * Op zelfde plek niet toegestaan

## Output
De output wordt weggeschreven naar `/outputFiles` als een `.csv` bestand. Op de eerste regel van het bestand 
wordt de volgorde en de naam van de gegevens neergeschreven. De overige lijnen bestaan uit elk één kraanbeweging
met de waarden in dezelfde volgorde als terugtevinden op de eerste regel.