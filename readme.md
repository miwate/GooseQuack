# GooseQuack
https://github.com/miwate/GooseQuack

## Préalable : Gradle (sur Ubuntu)
```bash
sudo apt install gradle
```
Versions :
Java 21
Gradle 8.5

## Comment build/run
Sur linux
```bash
./gradlew build
```
```bash
./gradlew run
```
```bash
./gradlew clean
```
Clean + build + fix (en cas de problèmes de dépendances)
```bash
./gradlew clean build --refresh-dependencies
```

Sur Windows idem mais ``gradlew.bat``

## Comment test
Tous les tests
```bash
./gradlew test --warning-mode all
```

Un fichier test
```bash
./gradlew test --tests NomDuTest --warning-mode all
```
```bash
./gradlew test --tests VersSacADosTest --warning-mode all
```

Résultats des tests
```bash
./gradlew test --warning-mode all --info
```

## Générer la javadoc
```bash
./gradlew javadoc
```

## Benchmark Instances
Folder sac94 is from https://www.researchgate.net/publication/271198281_Benchmark_instances_for_the_Multidimensional_Knapsack_Problem