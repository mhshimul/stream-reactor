/*
 * Copyright 2017 Datamountaineer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.datamountaineer.streamreactor.connect.azure.documentdb.config

import java.util

import org.apache.kafka.common.config.ConfigDef.{Importance, Type}
import org.apache.kafka.common.config.{AbstractConfig, ConfigDef}

object DocumentDbConfig {
  val DATABASE_CONFIG = "connect.documentdb.database.name"
  val DATABASE_CONFIG_DOC = "The Azure DocumentDb target database."


  val CONNECTION_CONFIG = "connect.documentdb.endpoint"
  val CONNECTION_CONFIG_DOC = "The Azure DocumentDb end point."
  val CONNECTION_DISPLAY = "Connection endpoint."

  val MASTER_KEY_CONFIG = "connect.documentdb.master.key"
  val MASTER_KEY_DOC = "The connection master key"
  val MASTER_KEY_DISPLAY = "Master Key"

  val BATCH_SIZE_CONFIG = "connect.documentdb.sink.batch.size"
  val BATCH_SIZE_DOC = "The number of records the sink would push to DocumentDb at once."
  val BATCH_SIZE_CONFIG_DEFAULT = 100

  val ERROR_POLICY_CONFIG = "connect.documentdb.error.policy"
  val ERROR_POLICY_DOC = "Specifies the action to be taken if an error occurs while inserting the data.\n" +
    "There are two available options: \n" + "NOOP - the error is swallowed \n" +
    "THROW - the error is allowed to propagate. \n" +
    "RETRY - The exception causes the Connect framework to retry the message. The number of retries is based on \n" +
    "The error will be logged automatically"
  val ERROR_POLICY_DEFAULT = "THROW"

  val ERROR_RETRY_INTERVAL_CONFIG = "connect.documentdb.retry.interval"
  val ERROR_RETRY_INTERVAL_DOC = "The time in milliseconds between retries."
  val ERROR_RETRY_INTERVAL_DEFAULT = "60000"

  val NBR_OF_RETRIES_CONFIG = "connect.documentdb.max.retries"
  val NBR_OF_RETRIES_DOC = "The maximum number of times to try the write again."
  val NBR_OF_RETIRES_DEFAULT = 20

  val KCQL_CONFIG = "connect.documentdb.sink.kcql"
  val KCQL_DOC = "KCQL expression describing field selection and data routing to the target DocumentDb."

  val CONSISTENCY_CONFIG = "connect.documentdb.sink.consistency.level"
  val CONSITENSCY_DOC = "Determines the write visibility. There are four possible values: Strong,BoundedStaleness,Session or Eventual"
  val CONSISTENCY_DISPLAY = "Writes consistency"
  val CONSISTENCY_DEFAULT = "Session"

  val CREATE_DATABASE_CONFIG = "connect.documentdb.sink.database.create"
  val CREATE_DATABASE_DOC = "If set to true it will create the database if it doesn't exist. If this is set to default(false) an exception will be raised."
  val CREATE_DATABASE_DISPLAY = "Auto-create database"
  val CREATE_DATABASE_DEFAULT = false

  val PROXY_HOST_CONFIG="connect.documentdb.proxy"
  val PROXY_HOST_DOC = "Specifies the connection proxy details."
  val PROXY_HOST_DISPLAY = "Proxy URI"

  val configDef: ConfigDef = new ConfigDef()
    .define(CONNECTION_CONFIG, Type.STRING, Importance.HIGH, CONNECTION_CONFIG_DOC, "Connection", 1, ConfigDef.Width.LONG, CONNECTION_DISPLAY)
    .define(MASTER_KEY_CONFIG, Type.PASSWORD, Importance.HIGH, MASTER_KEY_DOC, "Connection", 2, ConfigDef.Width.LONG, MASTER_KEY_CONFIG)
    .define(CONSISTENCY_CONFIG, Type.STRING, CONSISTENCY_DEFAULT, Importance.HIGH, CONSITENSCY_DOC, "Connection", 3, ConfigDef.Width.LONG, CONSISTENCY_DISPLAY)
    .define(DATABASE_CONFIG, Type.STRING, Importance.HIGH, DATABASE_CONFIG_DOC, "Connection", 4, ConfigDef.Width.MEDIUM, DATABASE_CONFIG)
    .define(CREATE_DATABASE_CONFIG, Type.BOOLEAN, CREATE_DATABASE_DEFAULT, Importance.MEDIUM, CREATE_DATABASE_DOC, "Connection", 5, ConfigDef.Width.MEDIUM, CREATE_DATABASE_DISPLAY)
  .define(PROXY_HOST_CONFIG, Type.STRING, null, Importance.LOW,PROXY_HOST_DOC,"Connection", 6,ConfigDef.Width.MEDIUM, PROXY_HOST_DISPLAY)

    .define(KCQL_CONFIG, Type.STRING, Importance.HIGH, KCQL_DOC, "Mappings", 1, ConfigDef.Width.LONG, KCQL_CONFIG)
    .define(BATCH_SIZE_CONFIG, Type.INT, BATCH_SIZE_CONFIG_DEFAULT, Importance.MEDIUM, BATCH_SIZE_DOC, "Mappings", 2, ConfigDef.Width.MEDIUM, BATCH_SIZE_CONFIG)
    .define(ERROR_POLICY_CONFIG, Type.STRING, ERROR_POLICY_DEFAULT, Importance.HIGH, ERROR_POLICY_DOC, "Error", 1, ConfigDef.Width.LONG, ERROR_POLICY_CONFIG)
    .define(NBR_OF_RETRIES_CONFIG, Type.INT, NBR_OF_RETIRES_DEFAULT, Importance.MEDIUM, NBR_OF_RETRIES_DOC, "Error", 2, ConfigDef.Width.LONG, NBR_OF_RETRIES_CONFIG)
    .define(ERROR_RETRY_INTERVAL_CONFIG, Type.INT, ERROR_RETRY_INTERVAL_DEFAULT, Importance.MEDIUM, ERROR_RETRY_INTERVAL_DOC, "Error", 3, ConfigDef.Width.LONG, ERROR_RETRY_INTERVAL_CONFIG)

}


case class DocumentDbConfig(props: util.Map[String, String]) extends AbstractConfig(DocumentDbConfig.configDef, props)
