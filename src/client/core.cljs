(ns client.core
  (:require [reagent.core :as r]))

(defonce app-state (r/atom {:text "Hello"}))

(defn change-text [event]
  (swap! app-state assoc :text (.-value (.-target event))))

(defn app [state-atom]
  (let [state @state-atom
        text (:text state)]
    [:div
     [:div [:span text]]
     [:div [:input {:type "text" :value text :on-change change-text}]]]))

(r/render [app app-state] (.getElementById js/document "app"))

(println "reloaded")
