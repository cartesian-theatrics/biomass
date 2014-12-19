(ns biomass.util
  (:require [clj-time.format :as tf]))

(defn nil-or-integer
  [s]
  (when (seq s)
    (Integer/parseInt s)))

(defn nil-or-double
  [s]
  (when (seq s)
    (Double/parseDouble s)))

(defn nil-or-datetime
  [s & time-format]
  (when (seq s)
    (tf/parse (tf/formatters (or time-format :date-time-no-ms)) s)))

(defn nil-or-boolean
  [s]
  (when (seq s)
    (Boolean/parseBoolean s)))

(defn restify-layout-params
  [params]
  (let [convert #(hash-map (keyword (str "HITLayoutParameter." %1 ".Name")) (name (first %2))
                           (keyword (str "HITLayoutParameter." %1 ".Value")) (second %2))]
    (reduce merge (map convert (range) params))))
