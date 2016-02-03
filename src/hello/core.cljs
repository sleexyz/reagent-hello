(ns hello.core
  (:require [reagent.core :as r]
            [markdown.core :refer [md->html]])
  (:require-macros [hello.macros :refer [slurp]]))

(defonce counter-state (r/atom 0))
(defonce text-state (r/atom "#yo!\nWrite some **markdown** in me!"))

(defn counter []
  [:div {:on-click #(swap! counter-state inc)}
   "I have been clicked " @counter-state " times."])

(defn dangerous [text]
  [:div {:dangerouslySetInnerHTML {:__html (md->html text)}}])

(def text (slurp "src/hello/poop.md"))

(defn editor []
  [:div
   [:textarea {:rows "10"
               :cols "50"
               :placeholder "Yo write some markdown in me!"
               :value @text-state
               :on-change #(reset! text-state (-> % .-target .-value))}]
   [:div [dangerous @text-state]]])


(defn app []
  [:div
   [:div [dangerous text]]
   [:h3 "counter"]
   [counter]
   [:br] [:br] [:br] [:br]
   [:h3 "Editor"]
   [editor]])


(defn start! []
  (r/render-component [app]
                      (.getElementById js/document "app")))

(start!)
