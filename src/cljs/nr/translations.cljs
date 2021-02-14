(ns nr.translations
  (:require
    [clojure.string :refer [lower-case replace]]
    [nr.appstate :refer [app-state]]
    [taoensso.tempura :as tempura]))

(def translation-dictionary
  {
   :en 
   {:missing ":en missing text"
    :side
    {:corp "Corp"
     :runner "Runner"
     :any-side "Any Side"
     :all "All"}
    :faction
    {:all "All"
     :any-faction "Any Faction"
     :anarch "Anarch"
     :criminal "Criminal"
     :shaper "Shaper"
     :adam "Adam"
     :apex "Apex"
     :sunny-lebeau "Sunny Lebeau"
     :jinteki "Jinteki"
     :haas-bioroid "Haas-Bioroid"
     :nbn "NBN"
     :weyland-consortium "Weyland Consortium"
     :neutral "Neutral"}
    :format
    {:all "All"
     :any-format "Any Format"
     :standard "Standard"
     :eternal "Eternal"
     :core-experience "Core Experience"
     :snapshot "Snapshot"
     :snapshot-plus "Snapshot Plus"
     :socr "SOCR"
     :classic "Classic"
     :casual "Casual"}
    :card-type
    {:all "All"
     :identity "Identity"
     :agenda "Agenda"
     :asset "Asset"
     :upgrade "Upgrade"
     :operation "Operation"
     :ice "ICE"
     :event "Event"
     :hardware "Hardware"
     :resource "Resource"
     :program "Program"}
    :pronouns
    {:none "Unspecified"
     :any "Any"
     :myodb "Prefer not to say"
     :blank "[blank]"
     :they "They/them"
     :she "She/her"
     :shethey "She/they"
     :he "He/him"
     :hethey "He/they"
     :it "It"
     :ne "Ne/nem"
     :ve "Ve/ver"
     :ey "Ey/em"
     :zehir "Ze/hir"
     :zezir "Ze/zir"
     :xe "Xe/xem"}
    :chat
    {:title "Play Android: Netrunner in your browser"
     :channels "Channels"
     :send "Send"
     :placeholder "Say something..."
     :delete "Delete Message"
     :delete-all "Delete All Messages From User"
     :block "Block User"
     :cancel "Cancel"}
    :nav
    {:chat "Chat"
     :cards "Cards"
     :deck-builder "Deck Builder"
     :play "Play"
     :help "Help"
     :settings "Settings"
     :stats "Stats"
     :about "About"
     :tournaments "Tournaments"
     :admin "Admin"
     :users "Users"
     :features "Features"
     :game-count (fn [[cnt]] (str cnt " Game" (when (not= cnt 1) "s")))}
    :menu
    {:settings :en.nav/settings
     :logout "Jack out"
     :admin :en.nav/admin
     :moderator "Moderator"}
    :card-browser
    {:search-hint "Search cards"
     :sort "Sort by"
     :format "Format"
     :set "Set"
     :side "Side"
     :faction "Faction"
     :type "Type"
     :clear "Clear"
     :select-art "Select Art"
     :selected-art "Selected Alt Art"
     :update-success "Updated Art"
     :update-failure "Failed to Update Art"
     :memory "Memory"
     :cost "Cost"
     :trash-cost "Trash cost"
     :strength "Strength"
     :advancement "Advancement requirement"
     :agenda-points "Agenda points"
     :min-deck "Minimum deck size"
     :inf-limit "Influence Limit"
     :influence "Influence"
     :sort-by {:faction "Faction"
              :name "Name"
              :type "Type"
              :influence "Influence"
              :cost "Cost"
              :set-number "Set number"}
     }
    :deck-builder
    {:loading-msg "Loading deck collection..."
     :new-corp "New Corp deck"
     :new-runner "New Runner deck"
     :import-button "Import deck"
     :reset "Reset"
     :import-title "Enter a Public NRDB Deck ID or URL"
     :import "Import"
     :cancel "Cancel"
     :import-placeholder "NRDB ID"
     :deck-count (fn [[cnt]] (str cnt (if (= 1 cnt) " Deck" " Decks")))
     :filtered "(filtered)"
     :save "Save"
     :confirm-delete "Confirm Delete"
     :edit "Edit"
     :delete "Delete"
     :clear-stats "Clear Stats"
     :create-game "Create Game"
     :deck-name "Deck name"
     :format "Format"
     :identity "Identity"
     :deck-notes "Deck notes"
     :decklist "Decklist"
     :decklist-inst "(Type or paste a decklist, it will be parsed)"
     :notes "Notes"
     :add-to-deck "Add to deck"
     :add-cards "Add cards"
     :card-name "Card name"
     :no-decks "No decks"
     :cards "cards"
     :min "minimum"
     :max "maximum"
     :influence "Influence"
     :agenda-points "Agenda points"
     :hash "Tournament hash"
     :why "Why?"
     :legal "legal"
     :illegal "illegal"
     :games "Games"
     :completed "Completed"
     :won "Won"
     :lost "Lost"}
   :lobby
    {:no-games "No games"
     :tournament "Tournament"
     :competitive "Competitive"
     :casual "Casual"
     :new-game "New game"
     :reload "Reload list"
     :load-replay "Load replay"
     :start-replay "Start replay"
     :save-replay "Save replay"
     :replay-link-error "Replay link invalid."
     :replay-invalid-file "Select a valid replay file."
     :create "Create"
     :cancel "Cancel"
     :title "Title"
     :side "Side"
     :format "Format"
     :options "Options"
     :spectators "Allow spectators"
     :hidden "Make players' hidden information visible to spectators"
     :password-protected "Password protected"
     :password "Password"
     :start "Start"
     :leave "Leave"
     :swap "Swap sides"
     :waiting "Waiting players deck selection"
     :players "Players"
     :deck-selected "Deck selected"
     :select-deck "Select Deck"
     :chat "Chat"
     :select-title "Select your deck"
     :spectator-count (fn [[cnt]] (str cnt " Spectator" (when (not= cnt 1) "s")))
     :closed-msg "Game lobby closed due to inactivity"
     :title-error "Please fill a game title."
     :password-error "Please fill a password."
     :too-little-data "Too little data"
     :completion-rate "Game Completion Rate"
     :watch "Watch"
     :join "Join"
     :rejoin "Rejoin"
     :private "PRIVATE"
     :reset "Reset Game Name"
     :delete "Delete Game"
     :password-for "Password for"
     :invalid-password "Invalid password"
     :not-allowed "Not allowed"
     :aborted "Connection aborted"}
   :settings
   {:invalid-password "Invalid login or password"
    :invalid-email "No account with that email address exists"
    :updated "Profile updated - Please refresh your browser"
    :updating "Updating profile..."
    :get-log-width "Get current log width"
    :get-log-top "Get current log top"
    :email-title "Change email address"
    :current-email "Current email"
    :desired-email "Desired email"
    :email-placeholder "Email address"
    :enter-valid "Please enter a valid email address"
    :update "Update"
    :cancel "Cancel"
    :email "Email"
    :change-email "Change email"
    :avatar "Avatar"
    :change-avatar "Change on gravatar.com"
    :pronouns "Pronouns"
    :language "Language"
    :sounds "Sounds"
    :enable-lobby-sounds "Enable lobby sounds"
    :enable-game-sounds "Enable game sounds"
    :volume "Volume"
    :layout-options "Layout Options"
    :server-stacking "Server stacking is on by default"
    :runner-layout "Runner layout from Corp perspective"
    :runner-classic "Runner rig layout is classic jnet (Top to bottom: Programs, Hardware, Resources)"
    :runner-reverse "Runner rig layout is reversed (Top to bottom: Resources, Hardware, Programs)"
    :background "Game board background"
    :card-backs "Card backs"
    :game-stats "Game Win/Lose statistics"
    :deck-stats "Deck statistics"
    :always "Always"
    :comp-only "Competitive Lobby Only"
    :none "None"
    :alt-art "Alt arts"
    :show-alt "Show alternate card arts"
    :set-all "Set all cards to"
    :set "Set"
    :reset "Reset All to Official Art"
    :blocked "Blocked users"
    :user-name "User name"
    :block "Block user"
    :update-profile "Update Profile"
    :nisei "NISEI"
    :ffg "FFG"}
  :stats
  {:game-stats "Game Stats"
   :corp-stats "Corp Stats"
   :runner-stats "Runner Stats"
   :clear-stats "Clear Stats"
   :no-log "No log available"
   :view-log "View log"
   :winner "Winner"
   :no-games "No games"
   :all-games "Show all games"
   :shared-games "Only show shared"
   :started "Started"
   :ended "Ended"
   :completed "Completed"
   :not-completed "Not completed"
   :won "Won"
   :lost "Lost"
   :turn-count (fn [[cnt]] (str cnt " turn" (when (not= cnt 1) "s")))
   :lobby "Lobby"
   :format "Format"
   :win-method "Win method"
   :view-games "Return to stats screen"
   :share "Share replay"
   :launch "Launch Replay"
   :download "Download replay"
   :unavailable "Replay unavailable"
   :filtered "(filtered)"
   :log-count (fn [[cnt]] (str cnt " Log" (when (not= cnt 1) "s")))
   :clicks-gained "Clicks Gained"
   :credits-gained "Credits Gained"
   :credits-spent "Credits Spent"
   :credits-click "Credits by Click"
   :cards-drawn "Cards Drawn"
   :cards-click "Cards Drawn by Click"
   :damage-done "Damage Done"
   :cards-rezzed "Cards Rezzed"
   :tags-gained "Tags Gained"
   :runs-made "Runs Made"
   :cards-accessed "Cards Accessed"}
  :log
  {:game-log "Game Log"
   :annotating "Annotating"
   :shared "Shared Annotations"}
  :annotations
  {:remote-annotations-fail "Could not get remote annotations."
   :turn-placeholder "Notes for this turn"
   :click-placeholder "Notes for this click"
   :available-annotations "Available annotations"
   :annotations-no-published-annotations "No published annotations."
   :annotations.available-annotations "Available annotations"
   :notes.publish "Publish"
   :notes.clear-annotations "Clear local annotations"
   :import-local "Import local annotation file"
   :load-local "Load"
   :save-local "Save"
   :clear "Clear"}
  :game
  {:keep "Keep"
   :mulligan "Mulligan"
   :close "Close"
   :start "Start Game"
   :remove-tag "Remove Tag"
   :run "Run"
   :purge "Purge"
   :trash-resource "Trash Resource"
   :draw "Draw"
   :gain-credit "Gain Credit"
   :game-start "Game start"
   :start-turn "Start Turn"
   :end-turn "End Turn"
   :mandatory-draw "Mandatory Draw"
   :take-clicks "Take Clicks"
   :hq "HQ"
   :grip "Grip"
   :rfg "Removed from the game"
   :play-area "Play Area"
   :current "Current"
   :scored-area "Scored Area"
   :archives "Archives"
   :max-hand "Max hand size"
   :brain-damage "Brain Damage"
   :tag-count (fn [[base additional total]]
                (str base (when (pos? additional) (str " + " additional)) " Tag" (if (not= total 1) "s" "")))
   :agenda-count (fn [[agenda-point]] (str agenda-point " Agenda Point" (when (not= agenda-point 1) "s")))
   :link-strength "Link Strength"
   :credit-count (fn [[credit run-credit]] (str credit " Credit" (if (not= credit 1) "s" "")
                                                (when (pos? run-credit)
                                                  (str " (" run-credit " for run)"))))
   :click-count (fn [[click]] (str click " Click" (if (not= click 1) "s" "")))
   :bad-pub-count (fn [[base additional]] (str base (when (pos? additional) (str " + " additional)) " Bad Publicity"))
   :mu-count (fn [[unused available]] (str unused " of " available " MU unused"))
   :indicate-action "Indicate action"
   :spec-count (fn [[c]] (str c " Spectator" (when (> c 1) "s")))
   :spec-view "Spectator View"
   :runner-view "Runner View"
   :corp-view "Corp View"
   :leave-replay "Leave Replay"
   :leave "Leave Game"
   :rig-irl "Rig layout: IRL"
   :rig-jnet "Rig layout: jnet"
   :unstack-servers "Unstack servers"
   :stack-servers "Stack servers"
   :unmute "Unmute spectators"
   :mute "Mute spectators"
   :concede "Concede"
   :inactivity "Game closed due to inactivity"
   :server "Server"
   :unimplemented "Unimplemented"
   :abilities "Abilities"
   :let-subs-fire "Let all subroutines fire"
   :subs "Subroutines"
   :actions "Actions"
   :fire-unbroken "Fire unbroken subroutines"
   :stack "Stack"
   :r&d "R&D"
   :shuffle "Shuffle"
   :show "Show"
   :close-shuffle "Close & Shuffle"
   :heap "Heap"
   :card-count (fn [[size]] (str size " card" (when (not= 1 size) "s") "."))
   :face-down-count (fn [[total face-up]] (str total " cards, " (- total face-up) " face-down."))
   :up-down-count (fn [[total face-up]] (str face-up "↑ " (- total face-up) "↓"))
   :initiation "Initiation"
   :approach-ice "Approach ice"
   :encouter-ice "Encounter ice"
   :pass-ice "Pass ice"
   :approach-server "Approach server"
   :corp-phase-43 "Corp phase 4.3"
   :access-server "Access server"
   :end-of-run "End of run"
   :no-current-run "No current run"
   :current-phase "Current phase"
   :unknown-phase "Unknown phase"
   :rez "Rez"
   :action-access "Action before access"
   :no-further "No further actions"
   :continue-to "Continue to"
   :stop-auto-pass "Stop auto-passing priority"
   :auto-pass "Auto-pass priority"
   :jack-out "Jack Out"
   :undo-click "Undo Click"
   :pass-continue "Pass ice and continue"
   :pass-jack "Pass ice and jack out"
   :trace "Trace"
   :credits "credits"
   :card "Card"
   :time-taken (fn [[t]] (str "Time taken: " t " minutes"))
   :win-decked (fn [[turn]] (str "wins due to the Corp being decked on turn " turn))
   :win-flatlined (fn [[turn]] (str "wins by flatline on turn " turn))
   :win-conceded (fn [[turn]] (str "wins by concession on turn " turn))
   :win-points (fn [[turn]] (str "wins by scoring agenda points on turn " turn))}
   }
   
   :zh-simp
   {:missing ":zh-simp missing text"
    :side
    {:corp "公司"
     :runner "潜袭者"
     :any-side "任意阵营"
     :all "全部"}
    :faction
    {:all "全部"
     :any-faction "任意派系"
     :anarch "反叛者"
     :criminal "逆法者"
     :shaper "塑造者"
     :adam "亚当"
     :apex "尖峰"
     :sunny-lebeau "桑妮·勒博"
     :jinteki "人间会社"
     :haas-bioroid "哈斯生化"
     :nbn "网际传媒"
     :weyland-consortium "威兰财团"
     :neutral "中立"}
    :format
    {:all "全部"
     :any-format "任意赛制"
     :standard "标准"
     :eternal "永久"
     :core-experience "核心体验"
     :snapshot "快照"
     :snapshot-plus "快照+"
     :socr "SOCR"
     :classic "经典"
     :casual "休闲"}
    :card-type
    {:all "全部"
     :identity "特性（ID）"
     :agenda "议案"
     :asset "资产"
     :upgrade "升级"
     :operation "事务"
     :ice "防火墙"
     :event "事件"
     :hardware "硬件"
     :resource "资源"
     :program "程序"}
    :pronouns
    {:none "未设定"
     :any "随意"
     :myodb "不愿透露"
     :blank "[空白]"
     :they "They/them"
     :she "她（She/her）"
     :shethey "她（She/they）"
     :he "他（He/him）"
     :hethey "他（He/they）"
     :it "它（It）"
     :ne "Ne/nem"
     :ve "Ve/ver"
     :ey "Ey/em"
     :zehir "Ze/hir"
     :zezir "Ze/zir"
     :xe "Xe/xem"}
    :chat
    {:title "在浏览器中玩《安卓纪元：矩阵潜袭》"
     :channels "频道"
     :send "发送"
     :placeholder "说点什么吧"
     :delete "删除消息"
     :delete-all "删除该用户发送的所有消息"
     :block "屏蔽用户"
     :cancel "取消"}
    :nav
    {:chat "聊天"
     :cards "卡牌"
     :deck-builder "牌组构筑"
     :play "对战"
     :help "帮助"
     :settings "设置"
     :stats "统计"
     :about "关于"
     :tournaments "比赛"
     :admin "网站管理"
     :users "用户"
     :features "站点功能"
     :game-count (fn [[cnt]] (str cnt "局对战"))}
    :menu
    {:settings :zh-simp.nav/settings
     :logout "退出"
     :admin "网站管理员"
     :moderator "管理员"}
    :card-browser
    {:search-hint "搜索卡牌"
     :sort "排序方式"
     :format "赛制"
     :set "系列"
     :side "阵营"
     :faction "派系"
     :type "类别"
     :clear "清除"
     :select-art "选择卡面"
     :selected-art "已选卡面"
     :update-success "卡面已更换"
     :update-failure "卡面更换失败"
     :memory "内存"
     :cost "费用"
     :trash-cost "销毁费用"
     :strength "强度"
     :advancement "推进需求"
     :agenda-points "议案分数"
     :min-deck "牌组张数下限"
     :inf-limit "影响力上限"
     :influence "影响力"
     :sort-by {:faction "派系"
              :name "牌名"
              :type "类别"
              :influence "影响力"
              :cost "费用"
              :set-number "系列编号"}
     }
    :deck-builder
    {:loading-msg "牌组加载中……"
     :new-corp "新建公司牌组"
     :new-runner "新建潜袭者牌组"
     :import-button "导入牌组"
     :reset "重置"
     :import-title "请输入NRDB上公开牌组的ID或URL"
     :import "导入"
     :cancel "取消"
     :import-placeholder "NRDB ID"
     :deck-count (fn [[cnt]] (str cnt "套牌组"))
     :filtered "（过滤后）"
     :save "保存"
     :confirm-delete "确认删除"
     :edit "编辑"
     :delete "删除"
     :clear-stats "清除统计数据"
     :create-game "创建对战"
     :deck-name "牌组名称"
     :format "赛制"
     :identity "特性（ID）"
     :deck-notes "牌组备注"
     :decklist "牌表"
     :decklist-inst "（在此输入或粘贴牌表，系统会自动解析）"
     :notes "备注"
     :add-to-deck "添加到牌组"
     :add-cards "添加卡牌"
     :card-name "卡牌名称"
     :no-decks "没有牌组"
     :cards "张卡牌"
     :min "最少"
     :max "最多"
     :influence "影响力"
     :agenda-points "议案分数"
     :hash "卡组hash（比赛用）"
     :why "为什么？"
     :legal "可用"
     :illegal "不可用"
     :games "局数"
     :completed "完成"
     :won "胜利"
     :lost "败北"}
   :lobby
    {:no-games "当前无对战"
     :tournament "比赛"
     :competitive "竞技"
     :casual "休闲"
     :new-game "新建对战"
     :reload "刷新列表"
     :load-replay "加载录像"
     :start-replay "开始回放"
     :save-replay "保存录像"
     :replay-link-error "录像链接无效。"
     :replay-invalid-file "请选择有效的录像文件。"
     :create "创建"
     :cancel "取消"
     :title "房间名"
     :side "阵营"
     :format "赛制"
     :options "选项"
     :spectators "允许旁观"
     :hidden "允许旁观者查看玩家的隐藏信息"
     :password-protected "密码保护"
     :password "密码"
     :start "开始"
     :leave "离开"
     :swap "交换阵营"
     :waiting "等待玩家选择牌组"
     :players "玩家"
     :deck-selected "牌组已选择"
     :select-deck "选择牌组"
     :chat "聊天"
     :select-title "请选择牌组"
     :spectator-count (fn [[cnt]] (str cnt "位观众"))
     :closed-msg "房间因长期无活动而关闭"
     :title-error "请设置房间名。"
     :password-error "请设置密码。"
     :too-little-data "数据不足"
     :completion-rate "游戏完成率"
     :watch "观战"
     :join "加入"
     :rejoin "重新加入"
     :private "私密"
     :reset "重置房间名"
     :delete "删除房间"
     :password-for "请输入房间密码："
     :invalid-password "密码不正确"
     :not-allowed "操作被禁止"
     :aborted "连接已中断"}
   :settings
   {:invalid-password "用户名或密码无效"
    :invalid-email "没有使用该邮箱地址的账号"
    :updated "设置已保存——请刷新页面"
    :updating "设置保存中……"
    :get-log-width "获取当前日志框宽度"
    :get-log-top "获取当前日志框顶部坐标"
    :email-title "更换电子邮件地址"
    :current-email "旧邮箱"
    :desired-email "新邮箱"
    :email-placeholder "电子邮件地址"
    :enter-valid "请输入合法的电子邮件地址"
    :update "更换"
    :cancel "取消"
    :email "电子邮箱"
    :change-email "更换电子邮箱"
    :avatar "头像"
    :change-avatar "在gravatar.com上更换"
    :pronouns "代词"
    :language "语言"
    :sounds "音效"
    :enable-lobby-sounds "开启大厅内音效"
    :enable-game-sounds "开启游戏内音效"
    :volume "音量"
    :layout-options "布局选项"
    :server-stacking "默认启用服务器堆叠"
    :runner-layout "潜袭者布局（公司视角）"
    :runner-classic "经典jnet布局（自上而下：程序、硬件、资源）"
    :runner-reverse "反转布局（自上而下：资源、硬件、程序）"
    :background "游戏背景"
    :card-backs "卡背"
    :game-stats "对战胜负统计"
    :deck-stats "牌组统计"
    :always "总是"
    :comp-only "仅竞技厅"
    :none "关闭"
    :alt-art "异画卡"
    :show-alt "显示异画卡"
    :set-all "将所有卡牌设为"
    :set "设置"
    :reset "将所有卡牌重设为原始卡面"
    :blocked "黑名单"
    :user-name "用户名"
    :block "屏蔽"
    :update-profile "保存设置"
    :nisei "NISEI"
    :ffg "FFG"}
  :stats
  {:game-stats "胜负统计"
   :corp-stats "公司统计"
   :runner-stats "潜袭者统计"
   :clear-stats "清除统计数据"
   :no-log "无日志信息"
   :view-log "查看记录"
   :winner "胜者"
   :no-games "没有对战记录"
   :all-games "显示所有记录"
   :shared-games "只显示已分享的录像"
   :started "开始时间"
   :ended "结束时间"
   :completed "完成"
   :not-completed "未完成"
   :won "胜利"
   :lost "败北"
   :turn-count (fn [[cnt]] (str cnt "个回合"))
   :lobby "大厅"
   :format "赛制"
   :win-method "胜利方式"
   :view-games "返回统计界面"
   :share "分享录像"
   :launch "播放录像"
   :download "下载录像"
   :unavailable "未保存录像"
   :filtered "（过滤后）"
   :log-count (fn [[cnt]] (str cnt "条记录"))
   :clicks-gained "获得时点数量"
   :credits-gained "获得信用点数量"
   :credits-spent "支付信用点数量"
   :credits-click "手动获取信用点数量"
   :cards-drawn "抽牌数量"
   :cards-click "手动抽牌数量"
   :damage-done "造成伤害量"
   :cards-rezzed "激活卡牌数量"
   :tags-gained "获得锁定标记数量"
   :runs-made "潜袭次数"
   :cards-accessed "读取卡牌数量"}
  :game
  {:keep "保留"
   :mulligan "调度"
   :close "关闭"
   :start "开始对战"
   :remove-tag "移除锁定标记"
   :run "潜袭"
   :purge "清除病毒指示物"
   :trash-resource "销毁资源"
   :draw "抽牌"
   :gain-credit "获得信用点"
   :game-start "对战开始时间"
   :start-turn "开始回合"
   :end-turn "结束回合"
   :mandatory-draw "回合开始抽牌"
   :take-clicks "进入行动阶段"
   :hq "总部"
   :grip "操控器"
   :rfg "移出游戏"
   :play-area "出牌区"
   :current "局势"
   :scored-area "计分区"
   :archives "档案库"
   :max-hand "手牌上限"
   :brain-damage "脑部伤害"
   :tag-count (fn [[base additional total]]
                (str base (when (pos? additional) (str " + " additional)) " 锁定标记"))
   :agenda-count (fn [[agenda-point]] (str agenda-point " 议案分数"))
   :link-strength "中转强度"
   :credit-count (fn [[credit run-credit]] (str credit " 信用点"
                                                (when (pos? run-credit)
                                                  (str " （" run-credit "个本次潜袭专用）"))))
   :click-count (fn [[click]] (str click " 时点"))
   :bad-pub-count (fn [[base additional]] (str base (when (pos? additional) (str " + " additional)) " 负面声誉"))
   :mu-count (fn [[unused available]] (str unused " / " available " 空闲内存"))
   :indicate-action "我要响应"
   :spec-count (fn [[c]] (str c " 位观众"))
   :spec-view "旁观者视图"
   :runner-view "潜袭者视图"
   :corp-view "公司视图"
   :leave-replay "关闭回放"
   :leave "离开对战"
   :rig-irl "潜袭者布局：反转"
   :rig-jnet "潜袭者布局：经典"
   :unstack-servers "禁用服务器堆叠"
   :stack-servers "启用服务器堆叠"
   :unmute "允许旁观者发言"
   :mute "禁止旁观者发言"
   :concede "投降"
   :inactivity "对战因长期无活动而关闭"
   :server "服务器"
   :unimplemented "功能未实现"
   :abilities "能力"
   :let-subs-fire "让公司结算子进程"
   :subs "子进程"
   :actions "操作"
   :fire-unbroken "结算未破解的子进程"
   :stack "存储栈"
   :r&d "研发中心"
   :shuffle "洗牌"
   :show "显示"
   :close-shuffle "关闭并洗牌"
   :heap "堆阵"
   :card-count (fn [[size]] (str size "张卡牌。"))
   :face-down-count (fn [[total face-up]] (str total "张卡牌，" (- total face-up) "张牌面朝下。"))
   :up-down-count (fn [[total face-up]] (str face-up "↑ " (- total face-up) "↓"))
   :initiation "发起潜袭"
   :approach-ice "接驳防火墙"
   :encouter-ice "遭遇防火墙"
   :pass-ice "通过防火墙"
   :approach-server "接驳服务器"
   :corp-phase-43 "公司阶段4.3"
   :access-server "访问服务器"
   :end-of-run "结束潜袭"
   :no-current-run "当前无潜袭进行中"
   :current-phase "当前阶段"
   :unknown-phase "未知阶段"
   :rez "激活"
   :action-access "在读取前进行响应"
   :no-further "没有响应"
   :continue-to "继续："
   :stop-auto-pass "取消自动让过优先权"
   :auto-pass "自动让过优先权"
   :jack-out "退出潜袭"
   :undo-click "倒回本时点"
   :pass-continue "通过防火墙并继续潜袭"
   :pass-jack "通过防火墙并退出潜袭"
   :trace "追踪强度"
   :credits "个信用点"
   :card "卡牌"
   :time-taken (fn [[t]] (str "对战用时：" t "分钟"))
   :win-decked (fn [[turn]] (str "于第" turn "回合因公司无牌可抽获胜"))
   :win-flatlined (fn [[turn]] (str "于第" turn "回合通过杀死潜袭者获胜"))
   :win-conceded (fn [[turn]] (str "于第" turn "回合因对手投降而获胜"))
   :win-points (fn [[turn]] (str "于第" turn "回合通过议案分数获胜"))}
   }

   :la-pig
   {:missing ":la-pig missing text"
    :side
    {:corp "Orpcay"
     :runner "Unnerray"
     :any-side "Anyay Idesay"
     :all "Allyay"}
    :faction
    {:all "Allyay"
     :any-faction "Anyay Actionfay"
     :anarch "Anarchyay"
     :criminal "Iminalcrimay"
     :shaper "Apershay"
     :adam "Adamyay"
     :apex "Apexyay"
     :sunny-lebeau "Unnsay Ebeaulay"
     :jinteki "Intekijay"
     :haas-bioroid "Aashay-Ioroidbay"
     :nbn "NBNYAY"
     :weyland-consortium "Eylandway Onsortiumcay"
     :neutral "Eutralnay"}
    :format
    {:all "Allyay"
     :any-format "Anyay Ormatfay"
     :standard "Andardstay"
     :eternal "Eternalyay"
     :core-experience "Orecay Experienceyay"
     :snapshot "Apshotsnay"
     :snapshot-plus "Apshotsnay Usplay"
     :socr "SOCRYAY"
     :classic "Assicclay"
     :casual "Asualcay"}
    :card-type
    {:all "Allyay"
     :identity "Identityay"
     :agenda "Agendayay"
     :asset "Assetyay"
     :upgrade "Upgradeyay"
     :operation "Operationyay"
     :ice "ICEYAY"
     :event "Eventyay"
     :hardware "Ardwarehay"
     :resource "Esourceray"
     :program "Ogrampray"}
    :pronouns
    {:none "Unspecifiedyay"
     :any "Anyay"
     :myodb "Eferpray otnay otay aysay"
     :blank "[ankblay]"
     :they "Eythay/emthay"
     :she "Eshay/erhay"
     :shethey "Eshay/eythay"
     :he "Ehay/imhay"
     :hethey "Ehay/eythay"
     :it "Ityay"
     :ne "Enay/emnay"
     :ve "Evay/ervay"
     :ey "Eyay/emay"
     :zehir "Ezay/irhay"
     :zezir "Ezay/irzay"
     :xe "Exay/emxay"}
    :chat
    {:title "Ayplay Android: Etrunnernay inyay ouryay owserbray"
     :channels "Annelschay"
     :send "Endsay"
     :placeholder "Aysay omethingsay..."
     :delete "Eleteday Essagemay"
     :delete-all "Eleteday Allyay Essagesmay Omfray Useryay"
     :block "Ockblay Useryay"
     :cancel "Ancelcay"}
    :nav
    {:chat "Atchay"
     :cards "Ardscay"
     :deck-builder "Eckday Uilderbay"
     :play "Ayplay"
     :help "Elphay"
     :settings "Ettingssay"
     :stats "Atsstay"
     :about "Aboutyay"
     :tournaments "Ournamentstay"
     :admin "Adminyay"
     :users "Usersyay"
     :features "Eaturesfay"
     :game-count (fn [[cnt]] (str cnt (if (= 1 cnt) " Amegay" " Amesgay"))) }
    :menu
    {:settings :la-pig.nav/settings
     :logout "Ackjay outyay"
     :admin :la-pig.nav/admin
     :moderator "Oderatormay"}
    :card-browser
    {:search-hint "Earchsay ardscay"
     :sort "Ortsay ybay"
     :format "Ormatfay"
     :set "Etsay"
     :side "Idesay"
     :faction "Actionfay"
     :type "Etypay"
     :clear "Earclay"
     :select-art "Electsay Artyay"
     :selected-art "Electedsay Altyay Artyay"
     :memory "Emorymay"
     :cost "Ostcay"
     :trash-cost "Ashtray ostcay"
     :strength "Engthstr"
     :advancement "Advancementyay equirementray"
     :agenda-points "Agendayay ointspay"
     :min-deck "Inimummay eckday izesay"
     :inf-limit "Influenceyay Imitlay"
     :influence "Influenceyay"
     :update-success "Updatedyay Artyay"
     :update-failure "Ailedfay otay Updateyay Artyay"
     :sort-by {:faction "Actionfay"
              :name "Amenay"
              :type "Etypay"
              :influence "Influenceyay"
              :cost "Ostcay"
              :set-number "Etsay umbernay"}}
    :deck-builder
    {:loading-msg "Oadinglay eckday ollectioncay..."
     :new-corp "Ewnay Orpcay eckday"
     :new-runner "Ewnay Unnerray eckday"
     :import-button "Importyay Eckday"
     :reset "Esetray"
     :import-title "Enteryay ayay ublicpay bnrday eckday idyay oryay urlyay"
     :import "Importyay"
     :cancel "Ancelcay"
     :import-placeholder "Bnrday idyay"
     :deck-count (fn [[cnt]] (str cnt (if (= 1 cnt) " Eckday" " Ecksday")))
     :filtered "(ilteredfay)"
     :save "Avesay"
     :confirm-delete "Onfirmcay Eleteday"
     :edit "Edityay"
     :delete "Eleteday"
     :clear-stats "Earclay Atsstay"
     :create-game "Eatecray Amegay"
     :deck-name "Eckday Amenay"
     :format "Ormatfay"
     :identity "Identityay"
     :deck-notes "Eckday otesnay"
     :decklist "Ecklistday"
     :decklist-inst "(Ypetay oryay astepay ayay ecklistday, ityay illway ebay arsedpay)"
     :notes "Otesnay"
     :add-to-deck "Addyay otay eckday"
     :add-cards "Addyay ardscay"
     :card-name "Ardcay amenay"
     :no-decks "Onay ecksday"
     :cards "ardscay"
     :min "inimummay"
     :max "aximummay"
     :influence "Influenceyay"
     :agenda-points "Agendayay ointspay"
     :hash "Ournamenttay ashhay"
     :why "Whyay?"
     :legal "egallay"
     :illegal "illegalyay"
     :games "Amesgay"
     :completed "Ompletedcay"
     :won "Onway"
     :lost "Ostlay"}
   :lobby
    {:no-games "Onay amesgay"
     :tournament "Ournamenttay"
     :competitive "Ompetitivecay"
     :casual "Asualcay"
     :new-game "Ewnay amegay"
     :reload "Eloadray istlay"
     :load-replay "Oadlay Eplayray"
     :start-replay "Artst Eplayray"
     :save-replay "Avesay Eplayray"
     :replay-link-error "Eplayray inklay invalidyay."
     :replay-invalid-file "Electsay ayay alidvay eplayray ilefay."
     :create "Eatecray"
     :cancel "Ancelcay"
     :title "Itletay"
     :side "Idesay"
     :format "Ormatfay"
     :options "Optionsyay"
     :spectators "Allowyay ectatorsspay"
     :hidden "Akemay ayersplay iddenhay informationyay isiblevay otay ectatorsspay"
     :password-protected "Asswordpay otectedpray"
     :password "Asswordpay"
     :start "Artstay"
     :leave "Eavelay"
     :swap "Apsway idessay"
     :waiting "Aitingway ayersplay eckday electionsay"
     :players "Ayersplay"
     :deck-selected "Eckday electedsay"
     :select-deck "Electsay eckday"
     :chat "Atchay"
     :select-title "Electsay ouryay eckday"
     :spectator-count (fn [[cnt]] (str cnt " Ectatorspay" (when (not= cnt 1) "s")))
     :closed-msg "Amegay obbylay osedclay ueday otay inactivityay"
     :title-error "Easeplay illfay ayay amegay itletay."
     :password-error "Easepl illfay ayay asswordpay."
     :too-little-data "Ootay ittlelay ataday"
     :completion-rate "Amegay Ompletioncay Ateray"
     :watch "Atchway"
     :join "Oinjay"
     :rejoin "Ejoinray"
     :private "IVATEPRAY"
     :reset "Esetray Amegay Amenay"
     :delete "Eleteday Amegay"
     :password-for "Asswordpay orfay"
     :invalid-password "Nvalidiay asswordpay"
     :not-allowed "Otnay allowedyay"
     :aborted "Onnectioncay abortedyay"}
   :settings
   {:invalid-password "Nvalidiay oginlay oryay asswordpay"
    :invalid-email "Onay accountyay ithway atthay emailyay addressyay existsyay"
    :updated "Ofilepray updatedyay - Leasepay efreshray ouryay owserbray"
    :updating "Updatingyay ofilepray..."
    :get-log-width "Etgay urrentcay oglay idthway"
    :get-log-top "Etgay urrentcay oglay optay"
    :email-title "Angechay emailyay addressyay"
    :current-email "Urrentcay emailyay"
    :desired-email "Esiredday emailyay"
    :email-placeholder "Emailyay addressyay"
    :enter-valid "Easeplay enteryay ayay alidvay emailyay addressyay"
    :update "Updateyay"
    :cancel "Ancelcay"
    :email "Emailyay"
    :change-email "Angechay emailyay"
    :avatar "Avataryay"
    :change-avatar "Angechay onyay gravatar.com"
    :pronouns "Onounspray"
    :language "Anguagelay"
    :sounds "Oundssay"
    :enable-lobby-sounds "Enableyay obbylay oundssay"
    :enable-game-sounds "Enableyay amegay oundssay"
    :volume "Olumevay"
    :layout-options "Ayoutlay Optionsyay"
    :server-stacking "Erversay ackingstay isyay onyay ybyay efaultday"
    :runner-layout "Unnerray ayoutlay omfray Orpcay erspectivepray"
    :runner-classic "Unnerray igray ayoutlay isyay assicclay etjnay (Optay otay ottombay: Ogramspr, Ardwarehay, Esourcesray)"
    :runner-reverse "Unnerray iray ayoutlay isyay eversedray (Optay otay ottombay: Esourcesray, Ardwarehay, Ogramspray)"
    :background "Amegay oardbay ackgroundbay"
    :card-backs "Ardcay acksbay"
    :game-stats "Amegay Inway/Oselay atisticsstay"
    :deck-stats "Eckday atisticsstay"
    :always "Alwaysyay"
    :comp-only "Ompetitivecay Obbylay Onlyay"
    :none "Onenay"
    :alt-art "Altyay artsyay"
    :show-alt "Owshay alternateyay ardcay artsyay"
    :set-all "Etsay allyay ardscay otay"
    :set "Etsay"
    :reset "Esetray Allyay otay Officialyay Artyay"
    :blocked "Ockedblay usersyay"
    :user-name "Useryay amenay"
    :block "Ockblay useryay"
    :update-profile "Updateyay Ofilepray"
    :nisei "ISEINAY"
    :ffg "FFGYAY"}
  :stats
  {:game-stats "Amegay Atsstay"
   :corp-stats "Orpcay Atsstay"
   :runner-stats "Unnerray Atsstay"
   :clear-stats "Earclay Atsstay"
   :no-log "Onay oglay availableyay"
   :view-log "Iewvay oglay"
   :winner "Innerway"
   :no-games "Onay amesgay"
   :all-games "Owshay allyay amesgay"
   :shared-games "Onlyay owshay aredshay"
   :started "Artedstay"
   :ended "Endedyay"
   :completed "Ompletedcay"
   :not-completed "Otnay ompletedcay"
   :won "Onway"
   :lost "Ostlay"
   :turn-count (fn [[cnt]] (str cnt " urntay" (when (not= cnt 1) "s")))
   :lobby "Obbylay"
   :format "Ormatfay"
   :win-method "Inway ethodmay"
   :view-games "Eturnray otay atsstay eenscray"
   :share "Areshay eplayray"
   :launch "Aunchlay Eplayray"
   :download "Ownloadday eplayray"
   :unavailable "Eplayray unavailableyay"
   :filtered "(ilteredfay)"
   :log-count (fn [[cnt]] (str cnt " Oglay" (when (not= cnt 1) "s")))
   :clicks-gained "Icksclay Ainedgay"
   :credits-gained "Editscray Ainedgay"
   :credits-spent "Editscray Entspay"
   :credits-click "Editscray ybay Ickclay"
   :cards-drawn "Ardscay Awndray"
   :cards-click "Ardscay Awndray ybay Ickclay"
   :damage-done "Amageday Oneday"
   :cards-rezzed "Ardscay Ezzedray"
   :tags-gained "Agstay Ainedgay"
   :runs-made "Unsray Ademay"
   :cards-accessed "Ardscay Accessedyay"}
  :game
  {:keep "Eepkay"
   :mulligan "Ulliganmay"
   :close "Oseclay"
   :start "Artstay Amegay"
   :remove-tag "Emoveray Agtay"
   :run "Unray"
   :purge "Urgepay"
   :trash-resource "Ashtray Esourceray"
   :draw "Awdray"
   :gain-credit "Aingay Editcray"
   :game-start "Amegay Artstay"
   :start-turn "Artstay Urntay"
   :end-turn "Endyay Urntay"
   :mandatory-draw "Andatorymay Awdray"
   :take-clicks "Aketay Icksclay"
   :hq "HQYAY"
   :grip "Ipgray"
   :rfg "Emovedray omfray ethay amegay"
   :play-area "Ayplay Areayay"
   :current "Urrentcay"
   :scored-area "Oredscay Areayay"
   :archives "Archivesyay"
   :max-hand "Axmay andhay izesay"
   :brain-damage "Ainbray Amageday"
   :tag-count (fn [[base additional total]]
                (str base (when (pos? additional) (str " + " additional)) " Agtay" (if (not= total 1) "s" "")))
   :agenda-count (fn [[agenda-point]] (str agenda-point " Agendayay Ointpay" (when (not= agenda-point 1) "s")))
   :link-strength "Inklay Engthstray"
   :credit-count (fn [[credit run-credit]] (str credit " Editcray" (if (not= credit 1) "s" "")
                                                (when (pos? run-credit)
                                                  (str " (" run-credit " orfay unray)"))))
   :click-count (fn [[click]] (str click " Ickclay" (if (not= click 1) "s" "")))
   :bad-pub-count (fn [[base additional]] (str base (when (pos? additional) (str " + " additional)) " Adbay Ublicitypay"))
   :mu-count (fn [[unused available]] (str unused " ofyay " available " UMYAY unusedyay"))
   :indicate-action "Indicateyay actionyay"
   :spec-count (fn [[c]] (str c " Ectatorspay" (when (> c 1) "s")))
   :spec-view "Ectatorspay Iewvay"
   :runner-view "Unnerray Iewvay"
   :corp-view "Orpcay Iewvay"
   :leave-replay "Eavelay Eplayray"
   :leave "Eavelay Amegay"
   :rig-irl "Igray ayoutlay: IRLYAY"
   :rig-jnet "Igray ayoutlay: etjnay"
   :unstack-servers "Unstackyay erverssay"
   :stack-servers "Ackstay erverssay"
   :unmute "Unmuteyay ectatorsspay"
   :mute "Utemay ectatorsspay"
   :concede "Oncedecay"
   :inactivity "Amegay osedclay ueday otay inactivityay"
   :server "Erversay"
   :unimplemented "Unimplementedyay"
   :abilities "Abilitiesyay"
   :let-subs-fire "Etlay allyay ubroutinessay irefay"
   :subs "Ubroutinessay"
   :actions "Actionsyay"
   :fire-unbroken "Irefay unbrokenyay ubroutinessay"
   :stack "Ackstay"
   :r&d "R&DYAY"
   :shuffle "Uffleshay"
   :show "Owshay"
   :close-shuffle "Osecla & Uffleshay"
   :heap "Eaphay"
   :card-count (fn [[size]] (str size " ardcay" (when (not= 1 size) "s") "."))
   :face-down-count (fn [[total face-up]] (str total " ardscay, " (- total face-up) " acefay-ownday."))
   :up-down-count (fn [[total face-up]] (str face-up "↑ " (- total face-up) "↓"))
   :initiation "Initiationyay"
   :approach-ice "Approachyay iceyay"
   :encouter-ice "Encounteryay iceyay"
   :pass-ice "Asspay iceyay"
   :approach-server "Approachyay erversay"
   :corp-phase-43 "Orpcay asephay 4.3"
   :access-server "Accessyay erversay"
   :end-of-run "Endyay ofyay unray"
   :no-current-run "Onay urrentcay unray"
   :current-phase "Urrentcay asephay"
   :unknown-phase "Unknownyay asephay"
   :rez "Ezray"
   :action-access "Actionyay eforebay accessyay"
   :no-further "Onay urtherfay actionsyay"
   :continue-to "Ontinuecay otay"
   :stop-auto-pass "Opstay autoyay-assingpay ioritypray"
   :auto-pass "Autoyay-asspay ioritypriay"
   :jack-out "Ackjay Outyay"
   :undo-click "Undoyay Ickclay"
   :pass-continue "Asspay iceyay andyay ontinuecay"
   :pass-jack "Asspay iceyay andyay ackjay outyay"
   :trace "Acetray"
   :credits "editscray"
   :card "Ardcay"
   :time-taken (fn [[t]] (str "Imetay akentay: " t " inutesmay"))
   :win-decked (fn [[turn]] (str "insway ueday otay ethay Orpcay eingbay eckedday onyay urntay " turn))
   :win-flatlined (fn [[turn]] (str "insway ybay atlineflay onyay urntay " turn))
   :win-conceded (fn [[turn]] (str "insway ybay oncessionay onyay urntay " turn))
   :win-points (fn [[turn]] (str "insway ybay oringcay agendayay ointspay onyay urntay " turn))}
   }})

(def opts {:dict translation-dictionary})

(defn tr [resource & params]
  (let [lang (keyword (get-in @app-state [:options :language] "en"))]
    (tempura/tr opts [lang :en] resource (vec params))))

(defn tr-string [prefix s]
  (let [side (lower-case (replace (or s "") " " "-"))
        kw (keyword (str prefix "." side))]
    (tr [kw "Unknown"])))

(def tr-type (partial tr-string "card-type"))
(def tr-side (partial tr-string "side"))
(def tr-faction (partial tr-string "faction"))
(def tr-format (partial tr-string "format"))
(def tr-sort (partial tr-string "card-browser.sort-by"))
(def tr-lobby (partial tr-string "lobby"))
(def tr-pronouns (partial tr-string "pronouns"))
(def tr-watch-join (partial tr-string "lobby"))
