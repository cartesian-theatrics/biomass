(ns biomass.assignments
  (:require [biomass.request :refer :all]
            [biomass.response.assignments :refer :all]
            [biomass.util :as util]))

(defn- send-and-parse
  [operation params]
  (let [resp (send-request operation params)]
    (when (= (:status resp) 200)
      (parse operation (:body resp)))))

(defn get-assignments-for-hit
  [hit-id]
  {:pre [(string? hit-id) (not (empty? hit-id))]}
  (send-and-parse "GetAssignmentsForHIT" {:HITId hit-id}))
