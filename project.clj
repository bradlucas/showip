(defproject showip "1.0.0"
  :description "Shows the IP address of the machine showip is run from"
  :url ""
  :license {:name "MIT"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 [clj-http "3.1.0"]
                 [org.clojure/data.json "0.2.6"]]
  :profiles {:uberjar {:uberjar-nane "showip-standalone.jar"}}
  :main showip.core
  :aot [showip.core])
