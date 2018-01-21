(ns server.app
  (:require
   [server.core :as core]
   [cljs.nodejs :as node]
   [mount.core :as mount]))

(mount/in-cljc-mode)

(cljs.nodejs/enable-util-print!)

(.on js/process "uncaughtException" #(js/console.error %))

(defonce server-ref
  (volatile! nil))

(def init core/server)

(defn start [& cli-args]
  (vreset! server-ref (core/server)))

(defn stop [done]
  (when-some [srv @server-ref]
    (.close srv
            (fn [err]
              (js/console.log "stop completed" err)
              (done)))))
