(ns xtdb-bundle-test.core
  (:require [com.brunobonacci.mulog :as u]
            [xtdb.node :as xtn]
            [xtdb.api :as xt])
  (:gen-class))

(defn -main [& _args]
  (u/start-publisher! {:type :console})
  (with-open [node (xtn/start-node)]
    (xt/execute-tx node [[:put-docs
                          :cheeses
                          {:xt/id :smelly
                           :name "Pálpusztai"
                           :pairs-with "Beer"}]])
    (prn (first (xt/q node '(from :cheeses [{:xt/id :smelly} *]))))))
