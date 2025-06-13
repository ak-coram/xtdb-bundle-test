#!/bin/sh

clojure -T:build clean
clojure -A:base -M -m xtdb-bundle-test.core
