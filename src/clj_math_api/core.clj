(ns clj-math-api.core
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [ring.adapter.jetty :as jetty]))

(defroutes app
  (GET "/" [] "Hello, World!")
  (GET "/:num1/plus/:num2" [num1 num2] (str (+ (Integer. num1) (Integer. num2)))))

(defn -main []
  (jetty/run-jetty app {:port 3000}))
