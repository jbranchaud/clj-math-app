(ns clj-math-api.core
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]))

(defn wrap-html-response
  [handler]
  (fn [request]
    (let [response (handler request)]
      (if (= 200 (:status response))
        (ring.util.response/content-type response "text/html")
        response))))

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

(defn minus-action
  [num1 num2]
  (let [x (Integer. num1) y (Integer. num2)]
    {:status 200 :body (str (- x y))}))

(defn times-action
  [num1 num2]
  (let [x (Integer. num1) y (Integer. num2)]
    {:status 200 :body (str (* x y))}))

(defn by-action
  [num1 num2]
  (let [x (Integer. num1) y (Integer. num2)]
    {:status 200 :body (str (/ x y))}))

(defroutes app-routes
  (GET "/" [] "Hello, World!")
  (GET "/:num1/plus/:num2" [num1 num2] (plus-action num1 num2))
  (GET "/:num1/minus/:num2" [num1 num2] (minus-action num1 num2))
  (GET "/:num1/times/:num2" [num1 num2] (times-action num1 num2))
  (GET "/:num1/by/:num2" [num1 num2] (by-action num1 num2))
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (wrap-exception-handling)
      (wrap-html-response)))

(defn -main []
  (jetty/run-jetty app {:port 3000}))
