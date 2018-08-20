(ns semantics_editor.core)

(defn create-svg-element
    [name]
    (-> js/document 
        (.createElementNS "http://www.w3.org/2000/svg" name))
)

(defn translate-svg-element
    [svg svg-element vector]
    (let [transformations (.-baseVal (.-transform svg-element))]
        (-> (.appendItem transformations (
            let [translation (-> (.createSVGTransform svg))]
            (-> (.setTranslate translation (:x vector) (:y vector)))
            translation
        )))
    )
)

(defn insert-default-notion-view-at
    [point svg notions]
    (let [notion-view (create-svg-element "g")]
        (-> svg (.appendChild notion-view))
        (translate-svg-element svg notion-view point)
        
        (-> notion-view (.appendChild 
            (let [text (create-svg-element "text")]
                (set! (.-textContent text) "vertex")
                (set! (.-userSelect (.-style text)) "none")
                text
            )
        ))
        (-> notion-view (.insertBefore 
            (let [rect (create-svg-element "rect") 
                    bbox (-> notion-view .getBBox)]
                (-> rect (.setAttribute "width" (+ 1(.-width bbox))))
                (-> rect (.setAttribute "height" (+ 1(.-height bbox))))
                (-> rect (.setAttribute "fill" "white"))
                (-> rect (.setAttribute "stroke" "skyblue"))
                (translate-svg-element svg rect {:x -1 :y (+ 1 (- (.-height bbox)))})
                rect
            ) (-> notion-view .-lastChild)
        ))
        notion-view
    )
)

(defn semantics-editor
    [notions relations]
    (let [  svg (create-svg-element "svg")
            client-width (.-clientWidth (.-body js/document))
            client-height (.-clientHeight (.-body js/document))]
        (-> svg (.setAttribute "width" client-width))
        (-> svg (.setAttribute "height" client-height))
        (-> svg (.setAttribute "viewBox", (str "0 0 " client-width " " client-height)))
        (set! (.-width (.-style svg)) (str client-width "px"))
        (set! (.-height (.-style svg)) (str client-height "px"))
        (set! (.-backgroundColor (.-style svg)) "darkgreen")
        (-> svg (.addEventListener "click" (fn [event] (
            insert-default-notion-view-at {:x (.-clientX event) :y (.-clientY event)} svg notions
        )) false))
        svg
    )
)

(-> js/document
        .-body
        (.appendChild (semantics-editor {} {})))
