(ns clj-math-api.core
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [ring.adapter.jetty :as jetty]))

(defn plus-action
  [num1 num2]
  (try
    (let [x (Integer. num1) y (Integer. num2)]
      {:status 200 :body (str (+ x y))})
    (catch Exception e
      {:status 400 :body "Bad Request"})))

(defroutes app
  (GET "/" [] "Hello, World!")
  (GET "/:num1/plus/:num2" [num1 num2] (plus-action num1 num2)))

(defn -main []
  (jetty/run-jetty app {:port 3000}))
