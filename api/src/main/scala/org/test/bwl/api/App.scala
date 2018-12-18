package org.test.bwl.api

import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.server.HttpApp
import org.test.bwl.api.auth.BasicAuth
import org.test.bwl.model.conf.DB

object App extends HttpApp with BasicAuth with DB {

  def main(args: Array[String]): Unit =
    startServer("0.0.0.0", 8080)

  override def routes = path("api" / "pass") {

    get {

      withBasicAuth {

        parameters('msisdn.as[Long], 'sn) { (msisdn, sn) =>

          val rule = blackListRuleAccessor.get(msisdn).one //NPE! if msisdn not found

          val pass = !rule.shortNumbers.contains(sn)

          complete(HttpEntity(`application/json`, s"""{"passed":$pass}"""))
        }
      }
    }
  }

  // GET  http://127.0.0.1:8080/api/rules/${msisdn}

  // GET  http://127.0.0.1:8080/api/rules/${msisdn}/${sn}


  // GET  http://127.0.0.1:8080/api/pass?msisdn=${msisdn}&sn=${sn}

  /*
    {
      result: false
    }
  */
}