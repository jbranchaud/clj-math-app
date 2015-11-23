(defproject clj-math-app "0.1.0-SNAPSHOT"
  :description "a Clojure app for doing basic maths"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [compojure "1.4.0"]
                 [ring/ring-mock "0.3.0"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler clj-math-app.core/app}
  :main ^:skip-aot clj-math-app.core)
