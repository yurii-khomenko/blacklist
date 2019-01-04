package org.test.bwl.api.route

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.headers.`WWW-Authenticate`
import akka.http.scaladsl.server.Route
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.test.bwl.api.Config

@RunWith(classOf[JUnitRunner])
class ApiRouteTest extends Config {

  val uri = "/api/pass"

  s"The hash system with REST resource $uri" should {

    s"1. Return Unauthorized error for GET requests to $uri without credentials" in {

      Get(uri) ~>
        Route.seal(routes) ~> check {

        status shouldEqual Unauthorized
        responseAs[String] shouldEqual requiresAuth
        header[`WWW-Authenticate`].get.challenges.head shouldEqual responseWWWAuthHeader
      }
    }

    s"2. Return Unauthorized error for GET requests to $uri with empty credentials" in {

      Get(uri) ~>
        addCredentials(invalidCredentials) ~>
        Route.seal(routes) ~> check {

        status shouldEqual Unauthorized
        responseAs[String] shouldEqual invalidAuth
        header[`WWW-Authenticate`].get.challenges.head shouldEqual responseWWWAuthHeader
      }
    }

    s"3. Return Unauthorized error for GET requests to $uri with invalid credentials" in {

      Get(uri) ~>
        addCredentials(invalidCredentials) ~>
        Route.seal(routes) ~> check {

        status shouldEqual Unauthorized
        responseAs[String] shouldEqual invalidAuth
        header[`WWW-Authenticate`].get.challenges.head shouldEqual responseWWWAuthHeader
      }
    }

//    s"4. Return Forbidden error with right error body for GET requests to $uri with other role credentials" in {
//
//      Get(uri) ~>
//        addCredentials(validMetricCredentials) ~>
//        Route.seal(routes) ~> check {
//
//        status shouldEqual Forbidden
//        responseAs[String] shouldEqual invalidRole + ClientRole.role
//      }
//    }

    s"5. Return OK code with right body for GET requests to $uri with valid credentials" in {

      Get(s"$uri?msisdn=$msisdn&sn=$sn") ~>
        addCredentials(validClientCredentials) ~>
        routes ~> check {

        status shouldEqual OK
        entityAs[String] should include ("false")
      }
    }
  }
}