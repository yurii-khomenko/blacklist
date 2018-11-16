package org.test.bwl.api

import akka.http.scaladsl.server.HttpApp
import org.test.bwl.model.conf.DB

object App extends HttpApp with DB {

  def main(args: Array[String]): Unit = {

//    println(">>>" + configs)
//    println(">>>" + users)

//    println(blackListRuleMapper.get("380670000001"))

    println(blackListRuleAccessor.get("380670000001").one)

    //startServer("0.0.0.0", 8080)
  }

  override def routes = pathSingleSlash {
    complete("Hello Akka!")
  }
}