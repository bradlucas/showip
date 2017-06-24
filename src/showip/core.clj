(ns showip.core
  (:gen-class)
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.string :as string]))
  
;; https://github.com/dakrone/clj-http

(defn showip
  []
  ;; curl -H"X-Forwarded-For: 8.8.8.8" http://httpbin.org/ip

  ;; set header

  ;; curl http://httpbin.org/ip

  ;; {
  ;; "origin": "209.90.32.80"
  ;; }

  ;; is it json? 
  ;; {
  ;;   "origin": "8.8.8.8, 209.90.32.80"
  ;; }
  
  (let [url "http://httpbin.org/ip"
        result (json/read-str (:body (client/get url {:headers {:X-Forwarded-For "8.8.8.8"}})))]
    (clojure.string/trim (second (clojure.string/split (get result "origin") #",")))
    ))

(def cli-options
  [["-h" "--help"]])

(defn usage [options-summary]
  (->> [""
        "Usage: showip [options]"
        ""
        "Options:"
        options-summary
        ""
        "Returns the IP address of the machine showip is running from."
        ""]
       (string/join \newline)))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn -main 
  [& args]
  (let [{:keys [options arguments summary errors]} (parse-opts args cli-options)]
    (cond 
     (:help options) (exit 0 (usage summary)))
    true (println (showip))))
