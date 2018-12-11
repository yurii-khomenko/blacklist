package org.test.bwl.model.conf

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.test.bwl.model.Config
import org.test.bwl.model.dao.BlackListRule
import scala.collection.JavaConverters._

@RunWith(classOf[JUnitRunner])
class DBTest extends Config with DB {

  s"The DB subsystem" should {

    s"1. Get BlackListRule" in {

      val expected = BlackListRule(380670000001L, Set("777", "bigtits", "pravexbank").asJava)
      val actual = blackListRuleAccessor.get(380670000001L).one

      actual shouldEqual expected
    }
  }
}