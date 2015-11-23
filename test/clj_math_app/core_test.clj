(ns clj-math-app.core-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clj-math-app.core :refer :all]))

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
      (is (= (:body response) "Bad Request")))
    (let [response (app (mock/request :get "/7/plus/"))]
      (is (= (:status response) 404))
      (is (= (:body response) "Not Found"))))
  (testing "minus endpoint"
    (let [response (app (mock/request :get "/2/minus/2"))]
      (is (= (:status response) 200))
      (is (= (:body response) "0")))
    (let [response (app (mock/request :get "/-1/minus/3"))]
      (is (= (:status response) 200))
      (is (= (:body response) "-4")))
    (let [response (app (mock/request :get "/11/minus/-3"))]
      (is (= (:status response) 200))
      (is (= (:body response) "14")))
    (let [response (app (mock/request :get "/a/minus/b"))]
      (is (= (:status response) 400))
      (is (= (:body response) "Bad Request")))
    (let [response (app (mock/request :get "/7/minus/"))]
      (is (= (:status response) 404))
      (is (= (:body response) "Not Found"))))
  (testing "times endpoint"
    (let [response (app (mock/request :get "/2/times/2"))]
      (is (= (:status response) 200))
      (is (= (:body response) "4")))
    (let [response (app (mock/request :get "/-1/times/3"))]
      (is (= (:status response) 200))
      (is (= (:body response) "-3")))
    (let [response (app (mock/request :get "/11/times/-3"))]
      (is (= (:status response) 200))
      (is (= (:body response) "-33")))
    (let [response (app (mock/request :get "/a/times/b"))]
      (is (= (:status response) 400))
      (is (= (:body response) "Bad Request")))
    (let [response (app (mock/request :get "/7/times/"))]
      (is (= (:status response) 404))
      (is (= (:body response) "Not Found"))))
  (testing "by endpoint"
    (let [response (app (mock/request :get "/2/by/2"))]
      (is (= (:status response) 200))
      (is (= (:body response) "1")))
    (let [response (app (mock/request :get "/-1/by/3"))]
      (is (= (:status response) 200))
      (is (= (:body response) "-1/3")))
    (let [response (app (mock/request :get "/11/by/-3"))]
      (is (= (:status response) 200))
      (is (= (:body response) "-11/3")))
    (let [response (app (mock/request :get "/5/by/0"))]
      (is (= (:status response) 400))
      (is (= (:body response) "Bad Request")))
    (let [response (app (mock/request :get "/a/by/b"))]
      (is (= (:status response) 400))
      (is (= (:body response) "Bad Request")))
    (let [response (app (mock/request :get "/7/by/"))]
      (is (= (:status response) 404))
      (is (= (:body response) "Not Found")))))
