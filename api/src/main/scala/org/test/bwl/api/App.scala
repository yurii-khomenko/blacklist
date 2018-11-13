package org.test.bwl.api

import akka.http.scaladsl.server.HttpApp
import org.test.bwl.model.conf.DB

object App extends HttpApp with DB {

  def main(args: Array[String]): Unit = {

   // println(conf.getList("client.db.contact.points"))

//    println(">>>" + session.getState.getConnectedHosts)

    println(">>>" + configAccessor.getAll.all)
    println(">>>" + userAccessor.getAll.all)

    //startServer("0.0.0.0", 8080)
  }

  override def routes = pathSingleSlash {
    complete("Hello Akka!")
  }
}