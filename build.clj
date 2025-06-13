(ns build
  (:require [clojure.tools.build.api :as build]))

(def lib 'debug/xtdb-bundle-test)
(def version (format "0.0.0-SNAPSHOT"))
(def class-dir "target/classes")
(def uber-file (format "target/%s-%s-standalone.jar" (name lib) version))
(def basis (delay (build/create-basis {:project "deps.edn"})))

(defn clean [_]
  (build/delete {:path "target"}))

(defn uber [_]
  (clean nil)
  (build/copy-dir {:src-dirs ["src"] :target-dir class-dir})
  (build/uber {:class-dir class-dir
               :uber-file uber-file
               :basis @basis}))

(defn compiled-uber [_]
  (clean nil)
  (build/copy-dir {:src-dirs ["src"] :target-dir class-dir})
  (build/compile-clj {:basis @basis
                      :ns-compile ['xtdb-bundle-test.core]
                      :class-dir class-dir})
  (build/uber {:class-dir class-dir
               :uber-file uber-file
               :basis @basis
               :main 'xtdb-bundle-test.core}))
