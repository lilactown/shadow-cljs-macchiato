(ns server.app
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [server.core-test]))

(doo-tests 'server.core-test)


