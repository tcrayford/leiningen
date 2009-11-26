(ns leiningen.doc
  "Generate HTML documentation for all namespaces listed in project.clj or all namespaces in src."
  (:use [clojure.contrib.java-utils :only [file]]
        [clojure.contrib.find-namespaces :only [find-namespaces-in-dir]]
        [clojure.contrib.gen-html-docs :only [generate-documentation-to-file]])
  (:import [java.io File])
  (:refer-clojure :exclude [doc]))

;; TODO - make the doc location configurable via a defproject keyword
;; TODO - generate an index page at ${doc}/index.html
;; TODO - prettier docmentation, probably by enhancing gen-html-docs
;; TODO - hyperlinks between docs

(defn doc
  "Generate docuentation for the project.
   Looks for all namespaces under src unless a list of :namespaces is provided in project.clj."
  [project]
  (doseq [n (or (:namespaces project)
                (find-namespaces-in-dir (file (:root project) "src")))]
    (let [dir  (str "doc/" (-> (name n)
                               (.replaceAll "\\." "/")
                               (.replaceAll "-" "_")))
          file (str dir "/index.html")]
      (println "Generating" file "...")
      (.mkdirs (File. dir))
      (generate-documentation-to-file file [n]))))
