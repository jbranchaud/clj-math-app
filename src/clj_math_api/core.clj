(ns clj-math-api.core
  (:require [ring.util.response :as response]
            [ring.adapter.jetty :as jetty]))

(defn handler [request]
  (-> (response/response "Hello, World!")
      (response/content-type "text/html")))

(defn -main []
  (jetty/run-jetty handler {:port 3000}))
