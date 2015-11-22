(ns clj-math-api.core
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as jetty]))

(defn wrap-exception-handling
  [handler]
  (fn [request]
    (try
      (handler request)
      (catch Exception e
        {:status 400 :body "Bad Request"}))))

(defn plus-action
  [num1 num2]
  (let [x (Integer. num1) y (Integer. num2)]
    {:status 200 :body (str (+ x y))}))

(defroutes app-routes
  (GET "/" [] "Hello, World!")
  (GET "/:num1/plus/:num2" [num1 num2] (plus-action num1 num2)))

(def app
  (-> (handler/api app-routes)
      (wrap-exception-handling)))

(defn -main []
  (jetty/run-jetty app {:port 3000}))
