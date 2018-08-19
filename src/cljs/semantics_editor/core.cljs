(ns semantics_editor.core)

(defn semantics-editor
    []
    (let [  svg (-> js/document
                (.createElementNS "http://www.w3.org/2000/svg" "svg"))
            client-width (.-clientWidth (.-body js/document))
            client-height (.-clientHeight (.-body js/document))]
        (-> svg (.setAttribute "width" client-width))
        (-> svg (.setAttribute "height" client-height))
        (-> svg (.setAttribute "viewBox", (str "0 0 " client-width " " client-height)))
        (set! (.-width (.-style svg)) (str client-width "px"))
        (set! (.-height (.-style svg)) (str client-height "px"))
        (set! (.-backgroundColor (.-style svg)) "violet")
        svg
    )
)

(-> js/document
        .-body
        (.appendChild (semantics-editor)))
