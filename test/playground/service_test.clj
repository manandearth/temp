(ns playground.service-test
  (:require
   [clojure.test :refer :all]
   [io.pedestal.test :refer :all]
   [io.pedestal.http :as bootstrap]
   [playground.service :as service]))

#_(def service
  (::bootstrap/service-fn (bootstrap/create-servlet service/service)))

#_(deftest home-page-test
  (is (= (-> service (response-for :get "/") :body)
         "Hello World!"))
  (is (= (-> service (response-for :get "/") :headers)
         {"Content-Type" "text/html;charset=UTF-8"
          "Strict-Transport-Security" "max-age=31536000; includeSubdomains"
          "X-Frame-Options" "DENY"
          "X-Content-Type-Options" "nosniff"
          "X-XSS-Protection" "1; mode=block"
          "X-Download-Options" "noopen"
          "X-Permitted-Cross-Domain-Policies" "none"
          "Content-Security-Policy" "object-src 'none'; script-src 'unsafe-inline' 'unsafe-eval' 'strict-dynamic' https: http:;"})))

#_(deftest about-page-test
  (is (-> service (response-for :get "/about") :body (.contains "Clojure 1.8")))
  (is (= (-> service (response-for :get "/about") :headers)
         {"Content-Type" "text/html;charset=UTF-8"
          "Strict-Transport-Security" "max-age=31536000; includeSubdomains"
          "X-Frame-Options" "DENY"
          "X-Content-Type-Options" "nosniff"
          "X-XSS-Protection" "1; mode=block"
          "X-Download-Options" "noopen"
          "X-Permitted-Cross-Domain-Policies" "none"
          "Content-Security-Policy" "object-src 'none'; script-src 'unsafe-inline' 'unsafe-eval' 'strict-dynamic' https: http:;"})))

