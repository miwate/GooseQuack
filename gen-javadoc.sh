#!/bin/bash
javadoc -d docs -encoding UTF-8 -charset UTF-8 -sourcepath src/main/java -subpackages fr.GooseQuack
xdg-open docs/index.html