(in-ns 'game.core)
(require '[potemkin :refer [import-vars]])

(import-vars

  [game.core.access
   access-bonus
   access-bonus-count
   access-card
   access-cost
   access-cost-bonus
   access-count
   access-end
   access-helper-archives
   access-helper-hq
   access-helper-rd
   access-helper-remote
   access-non-agenda
   clean-access-args
   choose-access
   do-access
   facedown-cards
   faceup-accessible
   get-all-content
   get-all-hosted
   get-only-card-to-access
   interactions
   max-access
   msg-handle-access
   must-continue?
   no-trash-or-steal
   num-cards-central
   num-cards-to-access
   root-content
   set-only-card-to-access
   steal
   steal-cost
   steal-cost-bonus
   turn-archives-faceup]

  [game.core.actions
   advance
   click-advance
   click-credit
   click-draw
   click-run
   close-deck
   do-purge
   generate-install-list
   generate-runnable-zones
   get-runnable-zones
   move-card
   play
   play-ability
   play-auto-pump
   play-auto-pump-and-break
   play-corp-ability
   play-dynamic-ability
   play-heap-breaker-auto-pump-and-break
   play-runner-ability
   play-subroutine
   play-unbroken-subroutines
   remove-tag
   resolve-prompt
   score
   select
   trash-resource
   view-deck]

  [game.core.agendas
   advancement-cost
   advancement-cost-bonus
   get-agenda-points
   update-advancement-cost
   update-all-advancement-costs
   update-all-agenda-points]

  [game.core.bad-publicity
   bad-publicity-prevent
   gain-bad-publicity
   lose-bad-publicity]

  [game.core.board
   all-active
   all-active-installed
   all-installed
   all-installed-runner-type
   card->server
   get-all-installed
   get-remote-names
   get-remote-zones
   get-remotes
   get-zones
   in-play?
   installable-servers
   installed-byname
   server->zone
   server-list]

  [game.core.card
   active?
   agenda?
   asset?
   assoc-host-zones
   can-be-advanced?
   card-index
   condition-counter?
   corp-installable-type?
   corp?
   event?
   facedown?
   faceup?
   fake-identity?
   get-card
   get-card-hosted
   get-cid
   get-counters
   get-nested-host
   get-zone
   hardware?
   has-subtype?
   ice?
   identity?
   in-archives-root?
   in-current?
   in-deck?
   in-discard?
   in-hand?
   in-hq-root?
   in-play-area?
   in-rd-root?
   in-root?
   in-scored?
   in-server?
   installed?
   is-type?
   map->Card
   operation?
   private-card
   program?
   resource?
   rezzed?
   runner?
   upgrade?
   virus-program?]

  [game.core.card-defs
   card-def]

  [game.core.change-vals
   change]

  [game.core.commands
   parse-command]

  [game.core.cost-fns
   break-sub-ability-cost
   card-ability-cost
   has-trash-ability?
   ignore-install-cost?
   install-additional-cost-bonus
   install-cost
   jack-out-cost
   play-additional-cost-bonus
   play-cost
   rez-additional-cost-bonus
   rez-cost
   run-additional-cost-bonus
   run-cost
   trash-cost]

  [game.core.costs
   total-available-credits]

  [game.core.damage
   chosen-damage
   corp-can-choose-damage?
   damage
   damage-bonus
   damage-count
   damage-prevent
   enable-corp-damage-choice
   enable-runner-damage-choice
   runner-can-choose-damage?]

  [game.core.def-helpers
   combine-abilities
   corp-rez-toast
   define-card
   do-brain-damage
   do-meat-damage
   do-net-damage
   reorder-choice
   trash-on-empty]

  [game.core.drawing
   draw
   draw-bonus
   max-draw
   remaining-draws]

  [game.core.effects
   any-effects
   gather-effects
   get-effects
   register-constant-effects
   register-floating-effect
   sum-effects
   unregister-constant-effects
   unregister-floating-effects]

  [game.core.eid
   complete-with-result
   effect-completed
   eid-set-defaults
   make-eid
   make-result
   register-effect-completed]

  [game.core.events
   ability-as-handler
   card-as-handler
   card-for-ability
   default-locations
   effect-as-handler
   event-count
   event-title
   first-event?
   first-installed-trash-own?
   first-installed-trash?
   first-run-event?
   first-successful-run-on-server?
   first-trash?
   gather-events
   get-installed-trashed
   get-turn-damage
   last-turn?
   log-event
   no-event?
   no-run-event?
   not-last-turn?
   register-events
   register-suppress
   run-event-count
   run-events
   second-event?
   trigger-event
   trigger-event-simult
   trigger-event-sync
   trigger-suppress
   turn-events
   unregister-event-by-uuid
   unregister-events
   unregister-floating-events
   unregister-floating-events-for-card
   unregister-suppress
   unregister-suppress-by-uuid]

  [game.core.expose
   expose
   expose-prevent]

  [game.core.finding
   find-card
   find-cid
   find-latest
   get-scoring-owner]

  [game.core.flags
   ab-can-prevent?
   any-flag-fn?
   can-access-loud
   can-access?
   can-advance?
   can-host?
   can-rez?
   can-run-server?
   can-run?
   can-score?
   can-steal?
   can-trash?
   card-can-prevent?
   card-flag-fn?
   card-flag?
   card-is-public?
   cards-can-prevent?
   check-flag-types?
   clear-all-flags-for-card!
   clear-persistent-flag!
   clear-run-flag!
   clear-run-register!
   clear-turn-flag!
   clear-turn-register!
   enable-run-on-server
   get-card-prevention
   get-prevent-list
   get-preventing-cards
   has-flag?
   in-corp-scored?
   in-runner-scored?
   is-scored?
   lock-zone
   persistent-flag?
   prevent-current
   prevent-draw
   prevent-jack-out
   prevent-run-on-server
   register-persistent-flag!
   register-run-flag!
   register-turn-flag!
   release-zone
   run-flag?
   turn-flag?
   untrashable-while-resources?
   untrashable-while-rezzed?
   when-scored?
   zone-locked?]

  [game.core.gaining
   available-mu
   base-mod-size
   change-hand-size
   deduct
   free-mu
   gain
   gain-credits
   hand-size
   lose
   lose-credits
   safe-inc-n
   sub->0
   toast-check-mu
   use-mu]

  [game.core.hosting
   host
   remove-from-host]

  [game.core.ice
   add-extra-sub!
   add-sub
   add-sub!
   all-subs-broken-by-card?
   all-subs-broken?
   any-subs-broken-by-card?
   any-subs-broken?
   auto-icebreaker
   break-all-subroutines
   break-all-subroutines!
   break-sub
   break-subroutine
   break-subroutine!
   break-subroutines
   break-subroutines-msg
   breakable-subroutines-choice
   breaker-strength
   dont-resolve-all-subroutines
   dont-resolve-all-subroutines!
   dont-resolve-subroutine
   dont-resolve-subroutine!
   get-current-ice
   get-run-ices
   get-strength
   ice-strength
   pump
   pump-all-ice
   pump-all-icebreakers
   pump-ice
   remove-extra-subs!
   remove-sub
   remove-sub!
   remove-subs
   remove-subs!
   reset-all-ice
   reset-all-subs
   reset-all-subs!
   reset-sub
   reset-sub!
   resolve-subroutine
   resolve-subroutine!
   resolve-unbroken-subs!
   set-current-ice
   strength-pump
   sum-ice-strength-effects
   unbroken-subroutines-choice
   update-all-ice
   update-all-icebreakers
   update-breaker-strength
   update-ice-in-server
   update-ice-strength]

  [game.core.identities
   disable-card
   disable-identity
   enable-card
   enable-identity
   flip-facedown
   flip-faceup]

  [game.core.initializing
   ability-init
   card-init
   deactivate
   make-card
   subroutines-init
   update-abilities-cost-str
   update-ability-cost-str
   update-all-card-labels]

  [game.core.installing
   corp-install
   corp-install-list
   corp-install-msg
   install-locked?
   runner-can-install?
   runner-install]

  [game.core.moving
   as-agenda
   discard-from-hand
   forfeit
   mill
   move
   move-zone
   remove-old-current
   swap-agendas
   swap-ice
   swap-installed
   trash
   trash-cards
   trash-prevent
   trash-resource-bonus
   uninstall
   update-current-ice-to-trash
   update-installed-card-indices]

  [game.core.optional
   get-autoresolve
   never?
   optional-ability
   set-autoresolve]

  [game.core.payment
   add-cost-label-to-ability
   build-cost-label
   build-cost-string
   build-spend-msg
   can-pay?
   cost->string
   cost-name
   cost-ranks
   handler
   label
   merge-costs
   pay
   payable?
   sentence-join
   value]

  [game.core.play-instants
   play-instant]

  [game.core.pick-counters
   pick-credit-providing-cards
   pick-virus-counters-to-spend]

  [game.core.process-actions
   command-parser
   process-action]

  [game.core.props
   add-counter
   add-icon
   add-prop
   remove-icon
   set-prop]

  [game.core.player
   map->Corp
   map->Runner
   new-corp
   new-runner]

  [game.core.prompts
   cancellable
   choice-parser
   clear-wait-prompt
   resolve-select
   show-prompt
   show-prompt-with-dice
   show-select
   show-trace-prompt
   show-wait-prompt]

  [game.core.psi
   psi-game]

  [game.core.purging
   purge]

  [game.core.resolve-ability
   can-trigger?
   is-ability?
   not-used-once?
   prompt!
   register-ability-type
   register-once
   resolve-ability
   should-trigger?]

  [game.core.revealing
   conceal-hand
   reveal
   reveal-hand]

  [game.core.rezzing
   derez
   get-rez-cost
   rez]

  [game.core.runs
   add-run-effect
   bypass-ice
   can-bypass-ice
   check-auto-no-action
   check-for-empty-server
   complete-run
   continue
   corp-phase-43
   encounter-ends
   end-run
   end-run-prevent
   gain-next-run-credits
   gain-run-credits
   handle-end-run
   jack-out
   jack-out-prevent
   make-run
   pass-ice
   prevent-access
   redirect-run
   register-successful-run
   replace-access
   run-cleanup
   run-cleanup-2
   set-next-phase
   set-phase
   start-next-phase
   successful-run
   successful-run-effect-impl
   toggle-auto-no-action
   total-cards-accessed
   total-run-cost]

  [game.core.say
   enforce-msg
   indicate-action
   play-sfx
   say
   system-msg
   system-say
   typing
   typingstop]

  [game.core.servers
   central->name
   central->zone
   from-same-server?
   get-server-type
   in-same-server?
   is-central?
   is-remote?
   is-root?
   name-zone
   protecting-same-server?
   remote->name
   remote-num->name
   same-server?
   target-server
   type->rig-zone
   unknown->kw
   zone->name
   zone->sort-key
   zones->sorted-names]

  [game.core.set-up
   build-card
   create-deck
   init-game
   init-identity
   keep-hand
   mulligan]

  [game.core.shuffling
   shuffle!
   shuffle-deck
   shuffle-into-deck
   shuffle-into-rd-effect]

  [game.core.state
   make-rid
   map->State
   new-state]

  [game.core.tags
   gain-tags
   lose-tags
   tag-prevent]

  [game.core.to-string
   card-str]

  [game.core.toasts
   show-error-toast
   toast]

  [game.core.trace
   init-trace
   init-trace-bonus]

  [game.core.turns
   end-phase-12
   end-turn
   start-turn]

  [game.core.update
   update!
   update-hosted!]

  [game.core.virus
   count-virus-programs
   get-virus-counters
   number-of-virus-counters]

  [game.core.winning
   check-winner
   clear-win
   concede
   flatline
   win
   win-decked]

  [game.macros
   continue-ability
   effect
   msg
   req
   wait-for
   when-let*])
