(defproject om-chart "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljsbuild "1.0.3"]]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]
                 [om "0.7.3"]
                 [prismatic/om-tools "0.3.4" :exclusions [org.clojure/clojure]]]
  :cljsbuild {:builds
              {:dev {:source-paths ["src/cljs"],
                     :compiler {:output-dir "resources/public/js/compiled/out"
                                :output-to "resources/public/js/compiled/app.js"
                                :pretty-print true
                                :optimizations :whitespace
                                :preamble ["public/js/react.js"]
                                :externs ["resources/externs/react.js"]}}}})
