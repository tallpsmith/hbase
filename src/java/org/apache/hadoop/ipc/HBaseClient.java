/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.ipc;

import javax.net.SocketFactory;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Writable;

/**
 * Subclass of hadoop's Client just so we can make some methods accessible
 * in {@link org.apache.hadoop.hbase.ipc.HbaseRPC}
 */
public class HBaseClient extends Client {
  /**
   * @param valueClass
   * @param conf
   * @param factory
   */
  public HBaseClient(Class<? extends Writable> valueClass, Configuration conf,
      SocketFactory factory) {
    super(valueClass, conf, factory);
  }
  
  @Override
  public void incCount() {
    super.incCount();
  }
  
  @Override
  public void decCount() {
    super.decCount();
  }
  
  @Override
  public boolean isZeroReference() {
    return super.isZeroReference();
  }
  
  @Override
  public SocketFactory getSocketFactory() {
    return super.getSocketFactory();
  }
}