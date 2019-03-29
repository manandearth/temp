(ns playground.system-test
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.test :refer [response-for]]
            [com.stuartsierra.component :as component]
            [clojure.test :refer :all]
            [user]
            [playground.server]
            [playground.service :as service]))


(def url-for (route/url-for-routes
              (route/expand-routes service/routes)))

(defn service-fn
  [system]
  (get-in system [:pedestal :service ::http/service-fn]))

(defmacro with-system
  [[bound-var binding-expr] & body]
  `(let [~bound-var (component/start ~binding-expr)]
     (try
       ~@body
       (finally
         (component/stop ~bound-var)))))

(deftest greeting-test
  (with-system [sut (user/dev-system)]                       ;; <1>
    (let [service               (service-fn sut)                 ;; <2>
          {:keys [status body]} (response-for service
                                              :get
                                              (url-for :greet))] ;; <3>
      (is (= 200 status))                                        ;; <4>
      (is (= "Hello, world!" body)))))                           ;; <5>
