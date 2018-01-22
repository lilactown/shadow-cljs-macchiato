(ns server.routes
  (:require
    [bidi.bidi :as bidi]
    [hiccups.runtime]
    [macchiato.util.response :as r]
    [reagent.dom.server :as rg])
  (:require-macros
   [hiccups.core :refer [html]]))

(defn home-page []
  [:html
   [:head]
   [:body
    [:h2 "Hallo reagent!"]
    [:div#app]
    [:script {:src "js/main.js"}]]])

(defn home [req res raise]
  (-> (rg/render-to-static-markup [home-page])
      (r/ok)
      (r/content-type "text/html")
      (res)))

(defn not-found [req res raise]
  (-> (html
        [:html
         [:body
          [:h2 (:uri req) " was not found"]]])
      (r/not-found)
      (r/content-type "text/html")
      (res)))

(def routes
  ["/" {:get home}])

(defn router [req res raise]
  (if-let [{:keys [handler route-params]} (bidi/match-route* routes (:uri req) req)]
    (handler (assoc req :route-params route-params) res raise)
    (not-found req res raise)))
