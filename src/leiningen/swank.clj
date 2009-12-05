(ns leiningen.swank
  (:require [swank.swank]))

(defn swank
  "Launch swank server for Emacs to connect."
  ([project port]
     (eval-in-project project '(do (require 'swank.swank)
                                   (@(ns-resolve 'swank.swank 'start-repl)
                                    4005))))
  ([project] (swank.swank/start-repl)))
