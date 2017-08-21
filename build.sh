#!/bin/bash
mvn clean package -Dmaven.test.skip=true
java -jar ./target/blog-1.0-SNAPSHOT.jar
