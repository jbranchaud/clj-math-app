(ns clj-math-api.core
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [ring.adapter.jetty :as jetty]))

(defroutes app
  (GET "/" [] "Hello, World!"))

(defn -main []
  (jetty/run-jetty app {:port 3000}))
