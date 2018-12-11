package org.test.bwl.api

import akka.http.scaladsl.server.HttpApp
import org.test.bwl.model.conf.DB

object App extends HttpApp with DB {

  def main(args: Array[String]): Unit =
    startServer("0.0.0.0", 8080)

  override def routes = path("api" / "rules" / LongNumber) { msisdn =>

    val rule = blackListRuleAccessor.get(msisdn).one

    complete(s"Hello, msisdn: $msisdn, rule: $rule")
  }

  // GET  http://127.0.0.1:8080/api/rules/${msisdn}

  // GET  http://127.0.0.1:8080/api/rules/${msisdn}/${sn}

  /*

    {
      result: false
    }

   */
}