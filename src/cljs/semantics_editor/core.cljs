(ns semantics_editor.core)

(defn semantics-editor
    []
    (let [  svg (-> js/document
                (.createElementNS "http://www.w3.org/2000/svg" "svg"))
            client-width (.-clientWidth (.-body js/document))
            client-height (.-clientHeight (.-body js/document))]
        (-> svg (.setAttribute "width" client-width))
        (-> svg (.setAttribute "height" client-height))
        (-> svg (.setAttribute "viewBox", "0 0 100 100"))
        (set! (.-width (.-style svg)) client-width)
        (set! (.-height (.-style svg)) client-height)
        (set! (.-backgroundColor (.-style svg)) "violet")
        svg
    )
)

(-> js/document
        .-body
        (.appendChild (semantics-editor)))
