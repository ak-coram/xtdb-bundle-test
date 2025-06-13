#!/bin/sh

clojure -T:build compiled-uber
java --add-opens=java.base/java.nio=ALL-UNNAMED -Dio.netty.tryReflectionSetAccessible=true -jar target/xtdb-bundle-test-0.0.0-SNAPSHOT-standalone.jar
