(ns hello.core
  (:require [reagent.core :as r]
            [markdown.core :refer [md->html]]))

(defonce counter-state (r/atom 0))
(defonce text-state (r/atom "#yo!\nWrite some **markdown** in me!"))

(.log js/console
      (md->html "###This is a heading\n with a p!"))

(defn counter []
  [:div {:on-click #(swap! counter-state inc)}
   "I have been clicked " @counter-state " times."])

(defn dangerous [text]
  [:div {:dangerouslySetInnerHTML {:__html (md->html text)}}])

(def text "
  # Yo!
  Welcome to my hello worldliness.
")

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
   [:h3 "markdown editor"]
   [editor]])


(defn start! []
  (r/render-component [app]
                      (.getElementById js/document "app")))

(start!)
