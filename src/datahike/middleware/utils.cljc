(ns datahike.middleware.utils)

(defn apply-middlewares
  "Combines a list of middleware functions into one."
  [middlewares handler]
  #?(:cljs
     (throw (ex-info "resolve is not supported at this time"
                     {:caller 'datahike.middleware.utils/apply-middlewares
                      :middlewares middlewares}))
     :clj
     (reduce
      (fn [acc f-sym]
        (if-let [f (resolve f-sym)]
          (f acc)
          (throw (ex-info "Invalid middleware.😱" {:fn f-sym}))))
      handler
      middlewares)))
