(ns clj-math-api.core-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clj-math-api.core :refer :all]))

(deftest test-app
  (testing "plus endpoint"
    (let [response (app (mock/request :get "/2/plus/2"))]
      (is (= (:status response) 200))
      (is (= (:body response) "4")))
    (let [response (app (mock/request :get "/-1/plus/3"))]
      (is (= (:status response) 200))
      (is (= (:body response) "2")))
    (let [response (app (mock/request :get "/11/plus/-3"))]
      (is (= (:status response) 200))
      (is (= (:body response) "8")))
    (let [response (app (mock/request :get "/a/plus/b"))]
      (is (= (:status response) 400))
      (is (= (:body response) "Bad Request")))))
