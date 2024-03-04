(ns datahike.api.macros
  (:require [datahike.api.specification :refer [api-specification spec-args->argslist]]))

(defmacro emit-api []
  `(do
     ~@(for [[n {:keys [args ret fn doc impl]}] api-specification]
        ; (eval
        ;  `(s/fdef ~n :args ~args :ret ~ret ~@(when fn [:fn fn])))
        `(def
             ~(with-meta n
                {:arglists `(spec-args->argslist (quote ~args))
                 :doc      doc})
             ~impl))))