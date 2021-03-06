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

package com.datamountaineer.streamreactor.connect.rethink.config

import com.datamountaineer.connector.config.Config

/**
  * Created by andrew@datamountaineer.com on 22/09/16. 
  * stream-reactor
  */
case class ReThinkSourceSettings (db : String,
                                  routes: Set[Config],
                                  tableTopicMap : Map[String, String])


object ReThinkSourceSettings {
  def apply(config: ReThinkSourceConfig) : ReThinkSourceSettings = {
    val raw = config.getString(ReThinkSourceConfig.IMPORT_ROUTE_QUERY)
    require(raw != null && !raw.isEmpty,  s"No ${ReThinkSourceConfig.IMPORT_ROUTE_QUERY} provided!")
    val routes = raw.split(";").map(r => Config.parse(r)).toSet
    val tableTopicMap = routes.map(rm => (rm.getSource, rm.getTarget)).toMap
    val db = config.getString(ReThinkSourceConfig.RETHINK_DB)
    ReThinkSourceSettings(db, routes, tableTopicMap)
  }
}
