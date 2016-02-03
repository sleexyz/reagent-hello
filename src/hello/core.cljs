(ns hello.core
  (:require [reagent.core :as r]))

(defonce counter (r/atom 0))

(defn app[]
  [:div {:on-click #(swap! counter inc)}
   "I have been clicked " @counter " times."])


(defn start! []
  (r/render-component [app]
                      (.getElementById js/document "app")))

(start!)
