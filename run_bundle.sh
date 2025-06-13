#!/bin/sh

clojure -T:build uber
java --add-opens=java.base/java.nio=ALL-UNNAMED -Dio.netty.tryReflectionSetAccessible=true -cp target/xtdb-bundle-test-0.0.0-SNAPSHOT-standalone.jar clojure.main -m xtdb-bundle-test.core
