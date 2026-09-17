# DevLog - Reservation de salles

Journal de bord du travail. Une entree par increment : probleme, solutions
envisagees, choix retenu, commandes de verification, branches/commits/tag,
ameliorations restantes.

---

## Increment v0.4.0 - Repositories en memoire

**Branche** : `feature/03-memory-repositories`
**Commits** : `chore: ajouter junit pour lancer les tests`,
`feat: definir les contrats repository` (`ad20f56`),
`feat: implementer les repositories en memoire` (`e4a5075`),
`docs: ajouter le diagramme des dependances` (`039626e`)
**Tag** : `v0.4.0`

### Probleme rencontre

`mvn test` echouait des le depart : le test fourni `AppTest.java` utilise JUnit 5
mais `pom.xml` ne declarait aucune dependance.

Exemple reproductible :

```text
[ERROR] package org.junit.jupiter.api does not exist
[ERROR] BUILD FAILURE
```

### Solutions envisagees

1. Supprimer le test fourni pour faire passer la compilation.
2. Ajouter la dependance `junit-jupiter` et le plugin `maven-surefire-plugin`.
3. Ne pas lancer `mvn test` et se contenter de `mvn compile`.

### Choix retenu et justification

Solution 2 : JUnit 5 (`junit-jupiter` 5.10.2, scope `test`) et surefire 3.2.5.
Cela rend la case "compiler et tester" du cycle reellement executable et evite
de perdre l'outillage de test de la suite.

### Diagramme

Produit avant le code dans `docs/diagramme_dependances.md` (regle d'inversion de
dependance : les appelants ne connaissent que les contrats).

### Commandes de verification

```bash
mvn clean test
java -cp "target/classes;<tmp>" VerifV040   # scenario jetable non commite
```

Resultats : ids 1 et 2 attribues, `findById(1)` -> `A101`, `findById(99)` absent,
2 salles listees, 1 reservation retrouvee par salle, `findAll()` immuable
(`UnsupportedOperationException`).

### Ameliorations restantes

- Les controles fonctionnels ne sont pas figes en tests JUnit commites.
- Absence de methode `delete`, non requise a ce stade.

---

## Increment v0.5.0 - Recherches avec Streams

**Branche** : `feature/04-stream-queries`
**Commits** : `feat: etendre le domaine salle` (`bb87b47`),
`feat: rechercher les salles avec les streams` (`45fadee`),
`feat: detecter les conflits de reservation` (`7cf4fd1`),
`docs: comparer boucles et streams`
**Tag** : `v0.5.0`

### Probleme rencontre

Le domaine ne portait ni capacite ni etat actif, pourtant necessaires pour
"rechercher les salles actives d'une capacite minimale". Ajout de `capacite`
(`int`) et `active` (`boolean`) sur `Salle`, et enrichissement des donnees de
demonstration.

### Solutions envisagees

1. Ecrire la recherche avec une boucle et une liste mutable.
2. Ecrire la meme recherche avec un `Stream` (filtre + tri).
3. Conserver les deux implementations dans le Repository.

### Choix retenu et justification

Une **seule** implementation finale dans le Repository : la version Stream.
Les deux versions comparatives sont conservees ci-dessous. Le tri par code a ete
ajoute car l'enonce demande un resultat "filtre et trie".

### Entree imposee : comparaison boucle / Stream (meme besoin)

Besoin : lister les salles actives d'une capacite minimale, triees par code.

Version boucle (ecartee) :

```java
public List<Salle> rechercherBoucle(int capaciteMinimale) {
    List<Salle> resultat = new ArrayList<>();
    for (Salle salle : salles.values()) {
        if (salle.isActive() && salle.getCapacite() >= capaciteMinimale) {
            resultat.add(salle);
        }
    }
    resultat.sort(Comparator.comparing(Salle::getCode));
    return resultat;
}
```

Version Stream (retenue) :

```java
public List<Salle> findActivesAvecCapaciteMinimale(int capaciteMinimale) {
    return salles.values().stream()
            .filter(Salle::isActive)
            .filter(salle -> salle.getCapacite() >= capaciteMinimale)
            .sorted(Comparator.comparing(Salle::getCode))
            .toList();
}
```

Justification selon les trois criteres demandes :

1. **Lisibilite** : le Stream exprime l'intention en trois etapes nommees
   (`filter`, `filter`, `sorted`) ; la boucle oblige a lire le corps et a suivre
   la construction de `resultat`.
2. **Mutation d'etat** : la boucle mute une liste intermediaire (effet de bord) ;
   le Stream enchaine des transformations sans etat mutable expose et `toList()`
   renvoie une liste non modifiable.
3. **Arret anticipe** : ici les deux parcourent tout (tri oblige), donc pas de
   difference. En revanche la detection de conflit utilise `anyMatch`, qui
   s'arrete des le premier chevauchement trouve — un avantage net sur une boucle
   qui n'utiliserait pas `break`.

### Pourquoi `parallelStream` n'apporte rien ici

- Les collections sont minuscules (quelques salles en memoire) : le decoupage et
  la synchronisation coutent plus cher que le travail utile.
- Le traitement n'est pas CPU-bound : la duree est negligeable, le parallelisme
  n'apporte aucun gain mesurable.
- La fusion des resultats et le respect de l'ordre de tri ajoutent de la
  complexite et risquent de reordonner la sortie.
- L'etat partage (`LinkedHashMap`, listes de reservations) devrait etre rendu
  thread-safe sans benefice : source de bugs inutiles.

### Commandes de verification

```bash
mvn clean test
java -cp "target/classes;<tmp>" VerifV050   # scenario jetable non commite
```

Resultats : `actives capacite >= 50 -> [A101, E505]` (F606 inactive exclue),
tri par date de debut `[8h, 14h]`, chevauchement `9h-11h` -> `true`, depart
exact a `10h` -> `false` (regle retenue), creneau libre -> `false`.

### Ameliorations restantes

- Les scenarios de recherche ne sont pas encore couverts par des tests JUnit.
- La recherche de salle ne filtre pas (encore) sur le type de salle.
