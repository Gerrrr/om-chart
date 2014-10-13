(ns om-chart.app
  (:require
   [om.core :as om :include-macros true]
   [om-tools.dom :as dom :include-macros true]
   [om-tools.core :refer-macros [defcomponent]]))


(def app-state (atom [4 8 15 16 23 42]))

(defn draw-chart [data container-selector]
  (let [data (clj->js data)
        x (-> (.-scale js/d3)
              (.linear)
              (.domain (clj->js [0 (.max js/d3 data)]))
              (.range (clj->js [0 600])))]
    (-> (.select js/d3 container-selector)
        (.html "")
        (.selectAll "div")
        (.data data)
        (.enter)
        (.append "div")
        (.style "width" #(str (x %) "px"))
        (.text #(str %)))))

(defcomponent root [data owner]
  (did-update [_ _ _]
    (draw-chart data ".chart"))
  (did-mount [_]
    (draw-chart data ".chart"))
  (render [_]
    (dom/div
     (dom/div {:class "chart"})
     (dom/button
      {:on-click (fn []
                   (om/transact! data
                                 #(conj % (->>
                                           (.random js/Math)
                                           (* 100)
                                           (+ 1)
                                           (.floor js/Math)))))}
      "+")
     (dom/button
      {:on-click #(om/transact! data pop)}
      "-"))))

(om/root root app-state
         {:target (. js/document (getElementById "app"))})
