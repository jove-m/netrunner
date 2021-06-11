(ns game.core.shuffling
  (:require
    [clojure.string :as string]
    [game.core.card :refer [corp? in-discard?]]
    [game.core.engine :refer [trigger-event-simult]]
    [game.core.eid :refer [make-eid effect-completed]]
    [game.core.moving :refer [move move-zone]]
    [game.core.say :refer [system-msg]]
    [game.macros :refer [continue-ability msg req wait-for]]
    [game.utils :refer [quantify]]))

(defn shuffle!
  "Shuffles the vector in @state [side kw]."
  [state side eid kw]
  (when (contains? #{:deck :hand :discard} kw)
    (wait-for (trigger-event-simult state side (make-eid state eid) (when (= :deck kw) (if (= :corp side) :corp-shuffle-deck :runner-shuffle-deck)) nil nil)
              (when (and (:access @state)
                         (= :deck kw))
                (swap! state assoc-in [:run :shuffled-during-access :rd] true))
              (swap! state update-in [side kw] shuffle)
              (effect-completed state side eid))))

(defn shuffle-into-deck
  [state side eid & args]
  (doseq [zone (filter keyword? args)]
    (move-zone state side zone :deck))
  (shuffle! state side eid :deck))

(defn shuffle-into-rd-effect
  ([state side eid card n] (shuffle-into-rd-effect state side eid card n false))
  ([state side eid card n all?]
   (continue-ability
     state side
     {:show-discard  true
      :choices {:max (min (-> @state :corp :discard count) n)
                :card #(and (corp? %)
                            (in-discard? %))
                :all all?}
      :msg (msg "shuffle "
                (let [seen (filter :seen targets)
                      m (count (filter #(not (:seen %)) targets))]
                  (str (string/join ", " (map :title seen))
                       (when (pos? m)
                         (str (when-not (empty? seen) " and ")
                              (quantify m "unseen card")))))
                " into R&D")
      :async true
      :effect (req (doseq [c targets]
                     (move state side c :deck))
                   (shuffle! state side eid :deck))
      :cancel-effect {:async true
                      :effect (req (shuffle! state side eid :deck))}}
     card nil)))

(defn shuffle-deck
  "Shuffle R&D/Stack."
  [state side {:keys [close]}]
  (swap! state update-in [side :deck] shuffle)
  (if close
    (do
      (swap! state update-in [side] dissoc :view-deck)
      (system-msg state side "stops looking at their deck and shuffles it"))
    (system-msg state side "shuffles their deck")))
