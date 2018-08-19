(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"html"}

 :dependencies '[[adzerk/boot-cljs "1.7.228-2"]
                 [pandeiro/boot-http "0.7.6"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [adzerk/boot-reload "0.5.1"]])

(require '[adzerk.boot-cljs :refer [cljs]]
         '[pandeiro.boot-http :refer [serve]]
         '[adzerk.boot-reload :refer [reload]])
