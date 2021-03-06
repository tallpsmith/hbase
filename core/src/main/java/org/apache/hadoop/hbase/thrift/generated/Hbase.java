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
package org.apache.hadoop.hbase.thrift.generated;

import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.protocol.*;

public class Hbase {

  public interface Iface {

    /**
     * Brings a table on-line (enables it)
     * 
     * @param tableName name of the table
     */
    public void enableTable(byte[] tableName) throws IOError, TException;

    /**
     * Disables a table (takes it off-line) If it is being served, the master
     * will tell the servers to stop serving it.
     * 
     * @param tableName name of the table
     */
    public void disableTable(byte[] tableName) throws IOError, TException;

    /**
     * @return true if table is on-line
     * 
     * @param tableName name of the table to check
     */
    public boolean isTableEnabled(byte[] tableName) throws IOError, TException;

    public void compact(byte[] tableNameOrRegionName) throws IOError, TException;

    public void majorCompact(byte[] tableNameOrRegionName) throws IOError, TException;

    /**
     * List all the userspace tables.
     * @return returns a list of names
     */
    public List<byte[]> getTableNames() throws IOError, TException;

    /**
     * List all the column families assoicated with a table.
     * @return list of column family descriptors
     * 
     * @param tableName table name
     */
    public Map<byte[],ColumnDescriptor> getColumnDescriptors(byte[] tableName) throws IOError, TException;

    /**
     * List the regions associated with a table.
     * @return list of region descriptors
     * 
     * @param tableName table name
     */
    public List<TRegionInfo> getTableRegions(byte[] tableName) throws IOError, TException;

    /**
     * Create a table with the specified column families.  The name
     * field for each ColumnDescriptor must be set and must end in a
     * colon (:). All other fields are optional and will get default
     * values if not explicitly specified.
     * 
     * @throws IllegalArgument if an input parameter is invalid
     * @throws AlreadyExists if the table name already exists
     * 
     * @param tableName name of table to create
     * 
     * @param columnFamilies list of column family descriptors
     */
    public void createTable(byte[] tableName, List<ColumnDescriptor> columnFamilies) throws IOError, IllegalArgument, AlreadyExists, TException;

    /**
     * Deletes a table
     * 
     * @throws IOError if table doesn't exist on server or there was some other
     * problem
     * 
     * @param tableName name of table to delete
     */
    public void deleteTable(byte[] tableName) throws IOError, TException;

    /**
     * Get a single TCell for the specified table, row, and column at the
     * latest timestamp. Returns an empty list if no such value exists.
     * 
     * @return value for specified row/column
     * 
     * @param tableName name of table
     * 
     * @param row row key
     * 
     * @param column column name
     */
    public List<TCell> get(byte[] tableName, byte[] row, byte[] column) throws IOError, TException;

    /**
     * Get the specified number of versions for the specified table,
     * row, and column.
     * 
     * @return list of cells for specified row/column
     * 
     * @param tableName name of table
     * 
     * @param row row key
     * 
     * @param column column name
     * 
     * @param numVersions number of versions to retrieve
     */
    public List<TCell> getVer(byte[] tableName, byte[] row, byte[] column, int numVersions) throws IOError, TException;

    /**
     * Get the specified number of versions for the specified table,
     * row, and column.  Only versions less than or equal to the specified
     * timestamp will be returned.
     * 
     * @return list of cells for specified row/column
     * 
     * @param tableName name of table
     * 
     * @param row row key
     * 
     * @param column column name
     * 
     * @param timestamp timestamp
     * 
     * @param numVersions number of versions to retrieve
     */
    public List<TCell> getVerTs(byte[] tableName, byte[] row, byte[] column, long timestamp, int numVersions) throws IOError, TException;

    /**
     * Get all the data for the specified table and row at the latest
     * timestamp. Returns an empty list if the row does not exist.
     * 
     * @return TRowResult containing the row and map of columns to TCells
     * 
     * @param tableName name of table
     * 
     * @param row row key
     */
    public List<TRowResult> getRow(byte[] tableName, byte[] row) throws IOError, TException;

    /**
     * Get the specified columns for the specified table and row at the latest
     * timestamp. Returns an empty list if the row does not exist.
     * 
     * @return TRowResult containing the row and map of columns to TCells
     * 
     * @param tableName name of table
     * 
     * @param row row key
     * 
     * @param columns List of columns to return, null for all columns
     */
    public List<TRowResult> getRowWithColumns(byte[] tableName, byte[] row, List<byte[]> columns) throws IOError, TException;

    /**
     * Get all the data for the specified table and row at the specified
     * timestamp. Returns an empty list if the row does not exist.
     * 
     * @return TRowResult containing the row and map of columns to TCells
     * 
     * @param tableName name of the table
     * 
     * @param row row key
     * 
     * @param timestamp timestamp
     */
    public List<TRowResult> getRowTs(byte[] tableName, byte[] row, long timestamp) throws IOError, TException;

    /**
     * Get the specified columns for the specified table and row at the specified
     * timestamp. Returns an empty list if the row does not exist.
     * 
     * @return TRowResult containing the row and map of columns to TCells
     * 
     * @param tableName name of table
     * 
     * @param row row key
     * 
     * @param columns List of columns to return, null for all columns
     * 
     * @param timestamp
     */
    public List<TRowResult> getRowWithColumnsTs(byte[] tableName, byte[] row, List<byte[]> columns, long timestamp) throws IOError, TException;

    /**
     * Apply a series of mutations (updates/deletes) to a row in a
     * single transaction.  If an exception is thrown, then the
     * transaction is aborted.  Default current timestamp is used, and
     * all entries will have an identical timestamp.
     * 
     * @param tableName name of table
     * 
     * @param row row key
     * 
     * @param mutations list of mutation commands
     */
    public void mutateRow(byte[] tableName, byte[] row, List<Mutation> mutations) throws IOError, IllegalArgument, TException;

    /**
     * Apply a series of mutations (updates/deletes) to a row in a
     * single transaction.  If an exception is thrown, then the
     * transaction is aborted.  The specified timestamp is used, and
     * all entries will have an identical timestamp.
     * 
     * @param tableName name of table
     * 
     * @param row row key
     * 
     * @param mutations list of mutation commands
     * 
     * @param timestamp timestamp
     */
    public void mutateRowTs(byte[] tableName, byte[] row, List<Mutation> mutations, long timestamp) throws IOError, IllegalArgument, TException;

    /**
     * Apply a series of batches (each a series of mutations on a single row)
     * in a single transaction.  If an exception is thrown, then the
     * transaction is aborted.  Default current timestamp is used, and
     * all entries will have an identical timestamp.
     * 
     * @param tableName name of table
     * 
     * @param rowBatches list of row batches
     */
    public void mutateRows(byte[] tableName, List<BatchMutation> rowBatches) throws IOError, IllegalArgument, TException;

    /**
     * Apply a series of batches (each a series of mutations on a single row)
     * in a single transaction.  If an exception is thrown, then the
     * transaction is aborted.  The specified timestamp is used, and
     * all entries will have an identical timestamp.
     * 
     * @param tableName name of table
     * 
     * @param rowBatches list of row batches
     * 
     * @param timestamp timestamp
     */
    public void mutateRowsTs(byte[] tableName, List<BatchMutation> rowBatches, long timestamp) throws IOError, IllegalArgument, TException;

    /**
     * Atomically increment the column value specified.  Returns the next value post increment.
     * 
     * @param tableName name of table
     * 
     * @param row row to increment
     * 
     * @param column name of column
     * 
     * @param value amount to increment by
     */
    public long atomicIncrement(byte[] tableName, byte[] row, byte[] column, long value) throws IOError, IllegalArgument, TException;

    /**
     * Delete all cells that match the passed row and column.
     * 
     * @param tableName name of table
     * 
     * @param row Row to update
     * 
     * @param column name of column whose value is to be deleted
     */
    public void deleteAll(byte[] tableName, byte[] row, byte[] column) throws IOError, TException;

    /**
     * Delete all cells that match the passed row and column and whose
     * timestamp is equal-to or older than the passed timestamp.
     * 
     * @param tableName name of table
     * 
     * @param row Row to update
     * 
     * @param column name of column whose value is to be deleted
     * 
     * @param timestamp timestamp
     */
    public void deleteAllTs(byte[] tableName, byte[] row, byte[] column, long timestamp) throws IOError, TException;

    /**
     * Completely delete the row's cells.
     * 
     * @param tableName name of table
     * 
     * @param row key of the row to be completely deleted.
     */
    public void deleteAllRow(byte[] tableName, byte[] row) throws IOError, TException;

    /**
     * Completely delete the row's cells marked with a timestamp
     * equal-to or older than the passed timestamp.
     * 
     * @param tableName name of table
     * 
     * @param row key of the row to be completely deleted.
     * 
     * @param timestamp timestamp
     */
    public void deleteAllRowTs(byte[] tableName, byte[] row, long timestamp) throws IOError, TException;

    /**
     * Get a scanner on the current table starting at the specified row and
     * ending at the last row in the table.  Return the specified columns.
     * 
     * @return scanner id to be used with other scanner procedures
     * 
     * @param tableName name of table
     * 
     * @param startRow Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     * 
     * @param columns columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public int scannerOpen(byte[] tableName, byte[] startRow, List<byte[]> columns) throws IOError, TException;

    /**
     * Get a scanner on the current table starting and stopping at the
     * specified rows.  ending at the last row in the table.  Return the
     * specified columns.
     * 
     * @return scanner id to be used with other scanner procedures
     * 
     * @param tableName name of table
     * 
     * @param startRow Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     * 
     * @param stopRow row to stop scanning on. This row is *not* included in the
     * scanner's results
     * 
     * @param columns columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public int scannerOpenWithStop(byte[] tableName, byte[] startRow, byte[] stopRow, List<byte[]> columns) throws IOError, TException;

    /**
     * Open a scanner for a given prefix.  That is all rows will have the specified
     * prefix. No other rows will be returned.
     * 
     * @return scanner id to use with other scanner calls
     * 
     * @param tableName name of table
     * 
     * @param startAndPrefix the prefix (and thus start row) of the keys you want
     * 
     * @param columns the columns you want returned
     */
    public int scannerOpenWithPrefix(byte[] tableName, byte[] startAndPrefix, List<byte[]> columns) throws IOError, TException;

    /**
     * Get a scanner on the current table starting at the specified row and
     * ending at the last row in the table.  Return the specified columns.
     * Only values with the specified timestamp are returned.
     * 
     * @return scanner id to be used with other scanner procedures
     * 
     * @param tableName name of table
     * 
     * @param startRow Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     * 
     * @param columns columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     * 
     * @param timestamp timestamp
     */
    public int scannerOpenTs(byte[] tableName, byte[] startRow, List<byte[]> columns, long timestamp) throws IOError, TException;

    /**
     * Get a scanner on the current table starting and stopping at the
     * specified rows.  ending at the last row in the table.  Return the
     * specified columns.  Only values with the specified timestamp are
     * returned.
     * 
     * @return scanner id to be used with other scanner procedures
     * 
     * @param tableName name of table
     * 
     * @param startRow Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     * 
     * @param stopRow row to stop scanning on. This row is *not* included in the
     * scanner's results
     * 
     * @param columns columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     * 
     * @param timestamp timestamp
     */
    public int scannerOpenWithStopTs(byte[] tableName, byte[] startRow, byte[] stopRow, List<byte[]> columns, long timestamp) throws IOError, TException;

    /**
     * Returns the scanner's current row value and advances to the next
     * row in the table.  When there are no more rows in the table, or a key
     * greater-than-or-equal-to the scanner's specified stopRow is reached,
     * an empty list is returned.
     * 
     * @return a TRowResult containing the current row and a map of the columns to TCells.
     * @throws IllegalArgument if ScannerID is invalid
     * @throws NotFound when the scanner reaches the end
     * 
     * @param id id of a scanner returned by scannerOpen
     */
    public List<TRowResult> scannerGet(int id) throws IOError, IllegalArgument, TException;

    /**
     * Returns, starting at the scanner's current row value nbRows worth of
     * rows and advances to the next row in the table.  When there are no more
     * rows in the table, or a key greater-than-or-equal-to the scanner's
     * specified stopRow is reached,  an empty list is returned.
     * 
     * @return a TRowResult containing the current row and a map of the columns to TCells.
     * @throws IllegalArgument if ScannerID is invalid
     * @throws NotFound when the scanner reaches the end
     * 
     * @param id id of a scanner returned by scannerOpen
     * 
     * @param nbRows number of results to return
     */
    public List<TRowResult> scannerGetList(int id, int nbRows) throws IOError, IllegalArgument, TException;

    /**
     * Closes the server-state associated with an open scanner.
     * 
     * @throws IllegalArgument if ScannerID is invalid
     * 
     * @param id id of a scanner returned by scannerOpen
     */
    public void scannerClose(int id) throws IOError, IllegalArgument, TException;

  }

  public static class Client implements Iface {
    public Client(TProtocol prot)
    {
      this(prot, prot);
    }

    public Client(TProtocol iprot, TProtocol oprot)
    {
      iprot_ = iprot;
      oprot_ = oprot;
    }

    protected TProtocol iprot_;
    protected TProtocol oprot_;

    protected int seqid_;

    public TProtocol getInputProtocol()
    {
      return this.iprot_;
    }

    public TProtocol getOutputProtocol()
    {
      return this.oprot_;
    }

    public void enableTable(byte[] tableName) throws IOError, TException
    {
      send_enableTable(tableName);
      recv_enableTable();
    }

    public void send_enableTable(byte[] tableName) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("enableTable", TMessageType.CALL, seqid_));
      enableTable_args args = new enableTable_args();
      args.tableName = tableName;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_enableTable() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      enableTable_result result = new enableTable_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      return;
    }

    public void disableTable(byte[] tableName) throws IOError, TException
    {
      send_disableTable(tableName);
      recv_disableTable();
    }

    public void send_disableTable(byte[] tableName) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("disableTable", TMessageType.CALL, seqid_));
      disableTable_args args = new disableTable_args();
      args.tableName = tableName;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_disableTable() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      disableTable_result result = new disableTable_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      return;
    }

    public boolean isTableEnabled(byte[] tableName) throws IOError, TException
    {
      send_isTableEnabled(tableName);
      return recv_isTableEnabled();
    }

    public void send_isTableEnabled(byte[] tableName) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("isTableEnabled", TMessageType.CALL, seqid_));
      isTableEnabled_args args = new isTableEnabled_args();
      args.tableName = tableName;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public boolean recv_isTableEnabled() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      isTableEnabled_result result = new isTableEnabled_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "isTableEnabled failed: unknown result");
    }

    public void compact(byte[] tableNameOrRegionName) throws IOError, TException
    {
      send_compact(tableNameOrRegionName);
      recv_compact();
    }

    public void send_compact(byte[] tableNameOrRegionName) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("compact", TMessageType.CALL, seqid_));
      compact_args args = new compact_args();
      args.tableNameOrRegionName = tableNameOrRegionName;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_compact() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      compact_result result = new compact_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      return;
    }

    public void majorCompact(byte[] tableNameOrRegionName) throws IOError, TException
    {
      send_majorCompact(tableNameOrRegionName);
      recv_majorCompact();
    }

    public void send_majorCompact(byte[] tableNameOrRegionName) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("majorCompact", TMessageType.CALL, seqid_));
      majorCompact_args args = new majorCompact_args();
      args.tableNameOrRegionName = tableNameOrRegionName;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_majorCompact() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      majorCompact_result result = new majorCompact_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      return;
    }

    public List<byte[]> getTableNames() throws IOError, TException
    {
      send_getTableNames();
      return recv_getTableNames();
    }

    public void send_getTableNames() throws TException
    {
      oprot_.writeMessageBegin(new TMessage("getTableNames", TMessageType.CALL, seqid_));
      getTableNames_args args = new getTableNames_args();
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<byte[]> recv_getTableNames() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      getTableNames_result result = new getTableNames_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "getTableNames failed: unknown result");
    }

    public Map<byte[],ColumnDescriptor> getColumnDescriptors(byte[] tableName) throws IOError, TException
    {
      send_getColumnDescriptors(tableName);
      return recv_getColumnDescriptors();
    }

    public void send_getColumnDescriptors(byte[] tableName) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("getColumnDescriptors", TMessageType.CALL, seqid_));
      getColumnDescriptors_args args = new getColumnDescriptors_args();
      args.tableName = tableName;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public Map<byte[],ColumnDescriptor> recv_getColumnDescriptors() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      getColumnDescriptors_result result = new getColumnDescriptors_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "getColumnDescriptors failed: unknown result");
    }

    public List<TRegionInfo> getTableRegions(byte[] tableName) throws IOError, TException
    {
      send_getTableRegions(tableName);
      return recv_getTableRegions();
    }

    public void send_getTableRegions(byte[] tableName) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("getTableRegions", TMessageType.CALL, seqid_));
      getTableRegions_args args = new getTableRegions_args();
      args.tableName = tableName;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<TRegionInfo> recv_getTableRegions() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      getTableRegions_result result = new getTableRegions_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "getTableRegions failed: unknown result");
    }

    public void createTable(byte[] tableName, List<ColumnDescriptor> columnFamilies) throws IOError, IllegalArgument, AlreadyExists, TException
    {
      send_createTable(tableName, columnFamilies);
      recv_createTable();
    }

    public void send_createTable(byte[] tableName, List<ColumnDescriptor> columnFamilies) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("createTable", TMessageType.CALL, seqid_));
      createTable_args args = new createTable_args();
      args.tableName = tableName;
      args.columnFamilies = columnFamilies;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_createTable() throws IOError, IllegalArgument, AlreadyExists, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      createTable_result result = new createTable_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      if (result.ia != null) {
        throw result.ia;
      }
      if (result.exist != null) {
        throw result.exist;
      }
      return;
    }

    public void deleteTable(byte[] tableName) throws IOError, TException
    {
      send_deleteTable(tableName);
      recv_deleteTable();
    }

    public void send_deleteTable(byte[] tableName) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("deleteTable", TMessageType.CALL, seqid_));
      deleteTable_args args = new deleteTable_args();
      args.tableName = tableName;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_deleteTable() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      deleteTable_result result = new deleteTable_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      return;
    }

    public List<TCell> get(byte[] tableName, byte[] row, byte[] column) throws IOError, TException
    {
      send_get(tableName, row, column);
      return recv_get();
    }

    public void send_get(byte[] tableName, byte[] row, byte[] column) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("get", TMessageType.CALL, seqid_));
      get_args args = new get_args();
      args.tableName = tableName;
      args.row = row;
      args.column = column;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<TCell> recv_get() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      get_result result = new get_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "get failed: unknown result");
    }

    public List<TCell> getVer(byte[] tableName, byte[] row, byte[] column, int numVersions) throws IOError, TException
    {
      send_getVer(tableName, row, column, numVersions);
      return recv_getVer();
    }

    public void send_getVer(byte[] tableName, byte[] row, byte[] column, int numVersions) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("getVer", TMessageType.CALL, seqid_));
      getVer_args args = new getVer_args();
      args.tableName = tableName;
      args.row = row;
      args.column = column;
      args.numVersions = numVersions;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<TCell> recv_getVer() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      getVer_result result = new getVer_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "getVer failed: unknown result");
    }

    public List<TCell> getVerTs(byte[] tableName, byte[] row, byte[] column, long timestamp, int numVersions) throws IOError, TException
    {
      send_getVerTs(tableName, row, column, timestamp, numVersions);
      return recv_getVerTs();
    }

    public void send_getVerTs(byte[] tableName, byte[] row, byte[] column, long timestamp, int numVersions) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("getVerTs", TMessageType.CALL, seqid_));
      getVerTs_args args = new getVerTs_args();
      args.tableName = tableName;
      args.row = row;
      args.column = column;
      args.timestamp = timestamp;
      args.numVersions = numVersions;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<TCell> recv_getVerTs() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      getVerTs_result result = new getVerTs_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "getVerTs failed: unknown result");
    }

    public List<TRowResult> getRow(byte[] tableName, byte[] row) throws IOError, TException
    {
      send_getRow(tableName, row);
      return recv_getRow();
    }

    public void send_getRow(byte[] tableName, byte[] row) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("getRow", TMessageType.CALL, seqid_));
      getRow_args args = new getRow_args();
      args.tableName = tableName;
      args.row = row;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<TRowResult> recv_getRow() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      getRow_result result = new getRow_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "getRow failed: unknown result");
    }

    public List<TRowResult> getRowWithColumns(byte[] tableName, byte[] row, List<byte[]> columns) throws IOError, TException
    {
      send_getRowWithColumns(tableName, row, columns);
      return recv_getRowWithColumns();
    }

    public void send_getRowWithColumns(byte[] tableName, byte[] row, List<byte[]> columns) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("getRowWithColumns", TMessageType.CALL, seqid_));
      getRowWithColumns_args args = new getRowWithColumns_args();
      args.tableName = tableName;
      args.row = row;
      args.columns = columns;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<TRowResult> recv_getRowWithColumns() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      getRowWithColumns_result result = new getRowWithColumns_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "getRowWithColumns failed: unknown result");
    }

    public List<TRowResult> getRowTs(byte[] tableName, byte[] row, long timestamp) throws IOError, TException
    {
      send_getRowTs(tableName, row, timestamp);
      return recv_getRowTs();
    }

    public void send_getRowTs(byte[] tableName, byte[] row, long timestamp) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("getRowTs", TMessageType.CALL, seqid_));
      getRowTs_args args = new getRowTs_args();
      args.tableName = tableName;
      args.row = row;
      args.timestamp = timestamp;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<TRowResult> recv_getRowTs() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      getRowTs_result result = new getRowTs_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "getRowTs failed: unknown result");
    }

    public List<TRowResult> getRowWithColumnsTs(byte[] tableName, byte[] row, List<byte[]> columns, long timestamp) throws IOError, TException
    {
      send_getRowWithColumnsTs(tableName, row, columns, timestamp);
      return recv_getRowWithColumnsTs();
    }

    public void send_getRowWithColumnsTs(byte[] tableName, byte[] row, List<byte[]> columns, long timestamp) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("getRowWithColumnsTs", TMessageType.CALL, seqid_));
      getRowWithColumnsTs_args args = new getRowWithColumnsTs_args();
      args.tableName = tableName;
      args.row = row;
      args.columns = columns;
      args.timestamp = timestamp;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<TRowResult> recv_getRowWithColumnsTs() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      getRowWithColumnsTs_result result = new getRowWithColumnsTs_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "getRowWithColumnsTs failed: unknown result");
    }

    public void mutateRow(byte[] tableName, byte[] row, List<Mutation> mutations) throws IOError, IllegalArgument, TException
    {
      send_mutateRow(tableName, row, mutations);
      recv_mutateRow();
    }

    public void send_mutateRow(byte[] tableName, byte[] row, List<Mutation> mutations) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("mutateRow", TMessageType.CALL, seqid_));
      mutateRow_args args = new mutateRow_args();
      args.tableName = tableName;
      args.row = row;
      args.mutations = mutations;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_mutateRow() throws IOError, IllegalArgument, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      mutateRow_result result = new mutateRow_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      if (result.ia != null) {
        throw result.ia;
      }
      return;
    }

    public void mutateRowTs(byte[] tableName, byte[] row, List<Mutation> mutations, long timestamp) throws IOError, IllegalArgument, TException
    {
      send_mutateRowTs(tableName, row, mutations, timestamp);
      recv_mutateRowTs();
    }

    public void send_mutateRowTs(byte[] tableName, byte[] row, List<Mutation> mutations, long timestamp) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("mutateRowTs", TMessageType.CALL, seqid_));
      mutateRowTs_args args = new mutateRowTs_args();
      args.tableName = tableName;
      args.row = row;
      args.mutations = mutations;
      args.timestamp = timestamp;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_mutateRowTs() throws IOError, IllegalArgument, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      mutateRowTs_result result = new mutateRowTs_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      if (result.ia != null) {
        throw result.ia;
      }
      return;
    }

    public void mutateRows(byte[] tableName, List<BatchMutation> rowBatches) throws IOError, IllegalArgument, TException
    {
      send_mutateRows(tableName, rowBatches);
      recv_mutateRows();
    }

    public void send_mutateRows(byte[] tableName, List<BatchMutation> rowBatches) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("mutateRows", TMessageType.CALL, seqid_));
      mutateRows_args args = new mutateRows_args();
      args.tableName = tableName;
      args.rowBatches = rowBatches;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_mutateRows() throws IOError, IllegalArgument, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      mutateRows_result result = new mutateRows_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      if (result.ia != null) {
        throw result.ia;
      }
      return;
    }

    public void mutateRowsTs(byte[] tableName, List<BatchMutation> rowBatches, long timestamp) throws IOError, IllegalArgument, TException
    {
      send_mutateRowsTs(tableName, rowBatches, timestamp);
      recv_mutateRowsTs();
    }

    public void send_mutateRowsTs(byte[] tableName, List<BatchMutation> rowBatches, long timestamp) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("mutateRowsTs", TMessageType.CALL, seqid_));
      mutateRowsTs_args args = new mutateRowsTs_args();
      args.tableName = tableName;
      args.rowBatches = rowBatches;
      args.timestamp = timestamp;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_mutateRowsTs() throws IOError, IllegalArgument, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      mutateRowsTs_result result = new mutateRowsTs_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      if (result.ia != null) {
        throw result.ia;
      }
      return;
    }

    public long atomicIncrement(byte[] tableName, byte[] row, byte[] column, long value) throws IOError, IllegalArgument, TException
    {
      send_atomicIncrement(tableName, row, column, value);
      return recv_atomicIncrement();
    }

    public void send_atomicIncrement(byte[] tableName, byte[] row, byte[] column, long value) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("atomicIncrement", TMessageType.CALL, seqid_));
      atomicIncrement_args args = new atomicIncrement_args();
      args.tableName = tableName;
      args.row = row;
      args.column = column;
      args.value = value;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public long recv_atomicIncrement() throws IOError, IllegalArgument, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      atomicIncrement_result result = new atomicIncrement_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      if (result.ia != null) {
        throw result.ia;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "atomicIncrement failed: unknown result");
    }

    public void deleteAll(byte[] tableName, byte[] row, byte[] column) throws IOError, TException
    {
      send_deleteAll(tableName, row, column);
      recv_deleteAll();
    }

    public void send_deleteAll(byte[] tableName, byte[] row, byte[] column) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("deleteAll", TMessageType.CALL, seqid_));
      deleteAll_args args = new deleteAll_args();
      args.tableName = tableName;
      args.row = row;
      args.column = column;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_deleteAll() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      deleteAll_result result = new deleteAll_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      return;
    }

    public void deleteAllTs(byte[] tableName, byte[] row, byte[] column, long timestamp) throws IOError, TException
    {
      send_deleteAllTs(tableName, row, column, timestamp);
      recv_deleteAllTs();
    }

    public void send_deleteAllTs(byte[] tableName, byte[] row, byte[] column, long timestamp) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("deleteAllTs", TMessageType.CALL, seqid_));
      deleteAllTs_args args = new deleteAllTs_args();
      args.tableName = tableName;
      args.row = row;
      args.column = column;
      args.timestamp = timestamp;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_deleteAllTs() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      deleteAllTs_result result = new deleteAllTs_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      return;
    }

    public void deleteAllRow(byte[] tableName, byte[] row) throws IOError, TException
    {
      send_deleteAllRow(tableName, row);
      recv_deleteAllRow();
    }

    public void send_deleteAllRow(byte[] tableName, byte[] row) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("deleteAllRow", TMessageType.CALL, seqid_));
      deleteAllRow_args args = new deleteAllRow_args();
      args.tableName = tableName;
      args.row = row;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_deleteAllRow() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      deleteAllRow_result result = new deleteAllRow_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      return;
    }

    public void deleteAllRowTs(byte[] tableName, byte[] row, long timestamp) throws IOError, TException
    {
      send_deleteAllRowTs(tableName, row, timestamp);
      recv_deleteAllRowTs();
    }

    public void send_deleteAllRowTs(byte[] tableName, byte[] row, long timestamp) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("deleteAllRowTs", TMessageType.CALL, seqid_));
      deleteAllRowTs_args args = new deleteAllRowTs_args();
      args.tableName = tableName;
      args.row = row;
      args.timestamp = timestamp;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_deleteAllRowTs() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      deleteAllRowTs_result result = new deleteAllRowTs_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      return;
    }

    public int scannerOpen(byte[] tableName, byte[] startRow, List<byte[]> columns) throws IOError, TException
    {
      send_scannerOpen(tableName, startRow, columns);
      return recv_scannerOpen();
    }

    public void send_scannerOpen(byte[] tableName, byte[] startRow, List<byte[]> columns) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("scannerOpen", TMessageType.CALL, seqid_));
      scannerOpen_args args = new scannerOpen_args();
      args.tableName = tableName;
      args.startRow = startRow;
      args.columns = columns;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public int recv_scannerOpen() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      scannerOpen_result result = new scannerOpen_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "scannerOpen failed: unknown result");
    }

    public int scannerOpenWithStop(byte[] tableName, byte[] startRow, byte[] stopRow, List<byte[]> columns) throws IOError, TException
    {
      send_scannerOpenWithStop(tableName, startRow, stopRow, columns);
      return recv_scannerOpenWithStop();
    }

    public void send_scannerOpenWithStop(byte[] tableName, byte[] startRow, byte[] stopRow, List<byte[]> columns) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("scannerOpenWithStop", TMessageType.CALL, seqid_));
      scannerOpenWithStop_args args = new scannerOpenWithStop_args();
      args.tableName = tableName;
      args.startRow = startRow;
      args.stopRow = stopRow;
      args.columns = columns;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public int recv_scannerOpenWithStop() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      scannerOpenWithStop_result result = new scannerOpenWithStop_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "scannerOpenWithStop failed: unknown result");
    }

    public int scannerOpenWithPrefix(byte[] tableName, byte[] startAndPrefix, List<byte[]> columns) throws IOError, TException
    {
      send_scannerOpenWithPrefix(tableName, startAndPrefix, columns);
      return recv_scannerOpenWithPrefix();
    }

    public void send_scannerOpenWithPrefix(byte[] tableName, byte[] startAndPrefix, List<byte[]> columns) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("scannerOpenWithPrefix", TMessageType.CALL, seqid_));
      scannerOpenWithPrefix_args args = new scannerOpenWithPrefix_args();
      args.tableName = tableName;
      args.startAndPrefix = startAndPrefix;
      args.columns = columns;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public int recv_scannerOpenWithPrefix() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      scannerOpenWithPrefix_result result = new scannerOpenWithPrefix_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "scannerOpenWithPrefix failed: unknown result");
    }

    public int scannerOpenTs(byte[] tableName, byte[] startRow, List<byte[]> columns, long timestamp) throws IOError, TException
    {
      send_scannerOpenTs(tableName, startRow, columns, timestamp);
      return recv_scannerOpenTs();
    }

    public void send_scannerOpenTs(byte[] tableName, byte[] startRow, List<byte[]> columns, long timestamp) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("scannerOpenTs", TMessageType.CALL, seqid_));
      scannerOpenTs_args args = new scannerOpenTs_args();
      args.tableName = tableName;
      args.startRow = startRow;
      args.columns = columns;
      args.timestamp = timestamp;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public int recv_scannerOpenTs() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      scannerOpenTs_result result = new scannerOpenTs_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "scannerOpenTs failed: unknown result");
    }

    public int scannerOpenWithStopTs(byte[] tableName, byte[] startRow, byte[] stopRow, List<byte[]> columns, long timestamp) throws IOError, TException
    {
      send_scannerOpenWithStopTs(tableName, startRow, stopRow, columns, timestamp);
      return recv_scannerOpenWithStopTs();
    }

    public void send_scannerOpenWithStopTs(byte[] tableName, byte[] startRow, byte[] stopRow, List<byte[]> columns, long timestamp) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("scannerOpenWithStopTs", TMessageType.CALL, seqid_));
      scannerOpenWithStopTs_args args = new scannerOpenWithStopTs_args();
      args.tableName = tableName;
      args.startRow = startRow;
      args.stopRow = stopRow;
      args.columns = columns;
      args.timestamp = timestamp;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public int recv_scannerOpenWithStopTs() throws IOError, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      scannerOpenWithStopTs_result result = new scannerOpenWithStopTs_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "scannerOpenWithStopTs failed: unknown result");
    }

    public List<TRowResult> scannerGet(int id) throws IOError, IllegalArgument, TException
    {
      send_scannerGet(id);
      return recv_scannerGet();
    }

    public void send_scannerGet(int id) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("scannerGet", TMessageType.CALL, seqid_));
      scannerGet_args args = new scannerGet_args();
      args.id = id;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<TRowResult> recv_scannerGet() throws IOError, IllegalArgument, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      scannerGet_result result = new scannerGet_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      if (result.ia != null) {
        throw result.ia;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "scannerGet failed: unknown result");
    }

    public List<TRowResult> scannerGetList(int id, int nbRows) throws IOError, IllegalArgument, TException
    {
      send_scannerGetList(id, nbRows);
      return recv_scannerGetList();
    }

    public void send_scannerGetList(int id, int nbRows) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("scannerGetList", TMessageType.CALL, seqid_));
      scannerGetList_args args = new scannerGetList_args();
      args.id = id;
      args.nbRows = nbRows;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<TRowResult> recv_scannerGetList() throws IOError, IllegalArgument, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      scannerGetList_result result = new scannerGetList_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.io != null) {
        throw result.io;
      }
      if (result.ia != null) {
        throw result.ia;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "scannerGetList failed: unknown result");
    }

    public void scannerClose(int id) throws IOError, IllegalArgument, TException
    {
      send_scannerClose(id);
      recv_scannerClose();
    }

    public void send_scannerClose(int id) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("scannerClose", TMessageType.CALL, seqid_));
      scannerClose_args args = new scannerClose_args();
      args.id = id;
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public void recv_scannerClose() throws IOError, IllegalArgument, TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      scannerClose_result result = new scannerClose_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.io != null) {
        throw result.io;
      }
      if (result.ia != null) {
        throw result.ia;
      }
      return;
    }

  }
  public static class Processor implements TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(Iface iface)
    {
      iface_ = iface;
      processMap_.put("enableTable", new enableTable());
      processMap_.put("disableTable", new disableTable());
      processMap_.put("isTableEnabled", new isTableEnabled());
      processMap_.put("compact", new compact());
      processMap_.put("majorCompact", new majorCompact());
      processMap_.put("getTableNames", new getTableNames());
      processMap_.put("getColumnDescriptors", new getColumnDescriptors());
      processMap_.put("getTableRegions", new getTableRegions());
      processMap_.put("createTable", new createTable());
      processMap_.put("deleteTable", new deleteTable());
      processMap_.put("get", new get());
      processMap_.put("getVer", new getVer());
      processMap_.put("getVerTs", new getVerTs());
      processMap_.put("getRow", new getRow());
      processMap_.put("getRowWithColumns", new getRowWithColumns());
      processMap_.put("getRowTs", new getRowTs());
      processMap_.put("getRowWithColumnsTs", new getRowWithColumnsTs());
      processMap_.put("mutateRow", new mutateRow());
      processMap_.put("mutateRowTs", new mutateRowTs());
      processMap_.put("mutateRows", new mutateRows());
      processMap_.put("mutateRowsTs", new mutateRowsTs());
      processMap_.put("atomicIncrement", new atomicIncrement());
      processMap_.put("deleteAll", new deleteAll());
      processMap_.put("deleteAllTs", new deleteAllTs());
      processMap_.put("deleteAllRow", new deleteAllRow());
      processMap_.put("deleteAllRowTs", new deleteAllRowTs());
      processMap_.put("scannerOpen", new scannerOpen());
      processMap_.put("scannerOpenWithStop", new scannerOpenWithStop());
      processMap_.put("scannerOpenWithPrefix", new scannerOpenWithPrefix());
      processMap_.put("scannerOpenTs", new scannerOpenTs());
      processMap_.put("scannerOpenWithStopTs", new scannerOpenWithStopTs());
      processMap_.put("scannerGet", new scannerGet());
      processMap_.put("scannerGetList", new scannerGetList());
      processMap_.put("scannerClose", new scannerClose());
    }

    protected static interface ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException;
    }

    private Iface iface_;
    protected final HashMap<String,ProcessFunction> processMap_ = new HashMap<String,ProcessFunction>();

    public boolean process(TProtocol iprot, TProtocol oprot) throws TException
    {
      TMessage msg = iprot.readMessageBegin();
      ProcessFunction fn = processMap_.get(msg.name);
      if (fn == null) {
        TProtocolUtil.skip(iprot, TType.STRUCT);
        iprot.readMessageEnd();
        TApplicationException x = new TApplicationException(TApplicationException.UNKNOWN_METHOD, "Invalid method name: '"+msg.name+"'");
        oprot.writeMessageBegin(new TMessage(msg.name, TMessageType.EXCEPTION, msg.seqid));
        x.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
        return true;
      }
      fn.process(msg.seqid, iprot, oprot);
      return true;
    }

    private class enableTable implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        enableTable_args args = new enableTable_args();
        args.read(iprot);
        iprot.readMessageEnd();
        enableTable_result result = new enableTable_result();
        try {
          iface_.enableTable(args.tableName);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing enableTable", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing enableTable");
          oprot.writeMessageBegin(new TMessage("enableTable", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("enableTable", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class disableTable implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        disableTable_args args = new disableTable_args();
        args.read(iprot);
        iprot.readMessageEnd();
        disableTable_result result = new disableTable_result();
        try {
          iface_.disableTable(args.tableName);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing disableTable", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing disableTable");
          oprot.writeMessageBegin(new TMessage("disableTable", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("disableTable", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class isTableEnabled implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        isTableEnabled_args args = new isTableEnabled_args();
        args.read(iprot);
        iprot.readMessageEnd();
        isTableEnabled_result result = new isTableEnabled_result();
        try {
          result.success = iface_.isTableEnabled(args.tableName);
          result.setSuccessIsSet(true);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing isTableEnabled", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing isTableEnabled");
          oprot.writeMessageBegin(new TMessage("isTableEnabled", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("isTableEnabled", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class compact implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        compact_args args = new compact_args();
        args.read(iprot);
        iprot.readMessageEnd();
        compact_result result = new compact_result();
        try {
          iface_.compact(args.tableNameOrRegionName);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing compact", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing compact");
          oprot.writeMessageBegin(new TMessage("compact", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("compact", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class majorCompact implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        majorCompact_args args = new majorCompact_args();
        args.read(iprot);
        iprot.readMessageEnd();
        majorCompact_result result = new majorCompact_result();
        try {
          iface_.majorCompact(args.tableNameOrRegionName);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing majorCompact", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing majorCompact");
          oprot.writeMessageBegin(new TMessage("majorCompact", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("majorCompact", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class getTableNames implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        getTableNames_args args = new getTableNames_args();
        args.read(iprot);
        iprot.readMessageEnd();
        getTableNames_result result = new getTableNames_result();
        try {
          result.success = iface_.getTableNames();
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing getTableNames", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing getTableNames");
          oprot.writeMessageBegin(new TMessage("getTableNames", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("getTableNames", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class getColumnDescriptors implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        getColumnDescriptors_args args = new getColumnDescriptors_args();
        args.read(iprot);
        iprot.readMessageEnd();
        getColumnDescriptors_result result = new getColumnDescriptors_result();
        try {
          result.success = iface_.getColumnDescriptors(args.tableName);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing getColumnDescriptors", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing getColumnDescriptors");
          oprot.writeMessageBegin(new TMessage("getColumnDescriptors", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("getColumnDescriptors", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class getTableRegions implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        getTableRegions_args args = new getTableRegions_args();
        args.read(iprot);
        iprot.readMessageEnd();
        getTableRegions_result result = new getTableRegions_result();
        try {
          result.success = iface_.getTableRegions(args.tableName);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing getTableRegions", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing getTableRegions");
          oprot.writeMessageBegin(new TMessage("getTableRegions", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("getTableRegions", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class createTable implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        createTable_args args = new createTable_args();
        args.read(iprot);
        iprot.readMessageEnd();
        createTable_result result = new createTable_result();
        try {
          iface_.createTable(args.tableName, args.columnFamilies);
        } catch (IOError io) {
          result.io = io;
        } catch (IllegalArgument ia) {
          result.ia = ia;
        } catch (AlreadyExists exist) {
          result.exist = exist;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing createTable", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing createTable");
          oprot.writeMessageBegin(new TMessage("createTable", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("createTable", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class deleteTable implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        deleteTable_args args = new deleteTable_args();
        args.read(iprot);
        iprot.readMessageEnd();
        deleteTable_result result = new deleteTable_result();
        try {
          iface_.deleteTable(args.tableName);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing deleteTable", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing deleteTable");
          oprot.writeMessageBegin(new TMessage("deleteTable", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("deleteTable", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class get implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        get_args args = new get_args();
        args.read(iprot);
        iprot.readMessageEnd();
        get_result result = new get_result();
        try {
          result.success = iface_.get(args.tableName, args.row, args.column);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing get", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing get");
          oprot.writeMessageBegin(new TMessage("get", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("get", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class getVer implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        getVer_args args = new getVer_args();
        args.read(iprot);
        iprot.readMessageEnd();
        getVer_result result = new getVer_result();
        try {
          result.success = iface_.getVer(args.tableName, args.row, args.column, args.numVersions);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing getVer", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing getVer");
          oprot.writeMessageBegin(new TMessage("getVer", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("getVer", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class getVerTs implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        getVerTs_args args = new getVerTs_args();
        args.read(iprot);
        iprot.readMessageEnd();
        getVerTs_result result = new getVerTs_result();
        try {
          result.success = iface_.getVerTs(args.tableName, args.row, args.column, args.timestamp, args.numVersions);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing getVerTs", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing getVerTs");
          oprot.writeMessageBegin(new TMessage("getVerTs", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("getVerTs", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class getRow implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        getRow_args args = new getRow_args();
        args.read(iprot);
        iprot.readMessageEnd();
        getRow_result result = new getRow_result();
        try {
          result.success = iface_.getRow(args.tableName, args.row);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing getRow", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing getRow");
          oprot.writeMessageBegin(new TMessage("getRow", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("getRow", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class getRowWithColumns implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        getRowWithColumns_args args = new getRowWithColumns_args();
        args.read(iprot);
        iprot.readMessageEnd();
        getRowWithColumns_result result = new getRowWithColumns_result();
        try {
          result.success = iface_.getRowWithColumns(args.tableName, args.row, args.columns);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing getRowWithColumns", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing getRowWithColumns");
          oprot.writeMessageBegin(new TMessage("getRowWithColumns", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("getRowWithColumns", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class getRowTs implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        getRowTs_args args = new getRowTs_args();
        args.read(iprot);
        iprot.readMessageEnd();
        getRowTs_result result = new getRowTs_result();
        try {
          result.success = iface_.getRowTs(args.tableName, args.row, args.timestamp);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing getRowTs", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing getRowTs");
          oprot.writeMessageBegin(new TMessage("getRowTs", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("getRowTs", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class getRowWithColumnsTs implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        getRowWithColumnsTs_args args = new getRowWithColumnsTs_args();
        args.read(iprot);
        iprot.readMessageEnd();
        getRowWithColumnsTs_result result = new getRowWithColumnsTs_result();
        try {
          result.success = iface_.getRowWithColumnsTs(args.tableName, args.row, args.columns, args.timestamp);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing getRowWithColumnsTs", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing getRowWithColumnsTs");
          oprot.writeMessageBegin(new TMessage("getRowWithColumnsTs", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("getRowWithColumnsTs", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class mutateRow implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        mutateRow_args args = new mutateRow_args();
        args.read(iprot);
        iprot.readMessageEnd();
        mutateRow_result result = new mutateRow_result();
        try {
          iface_.mutateRow(args.tableName, args.row, args.mutations);
        } catch (IOError io) {
          result.io = io;
        } catch (IllegalArgument ia) {
          result.ia = ia;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing mutateRow", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing mutateRow");
          oprot.writeMessageBegin(new TMessage("mutateRow", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("mutateRow", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class mutateRowTs implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        mutateRowTs_args args = new mutateRowTs_args();
        args.read(iprot);
        iprot.readMessageEnd();
        mutateRowTs_result result = new mutateRowTs_result();
        try {
          iface_.mutateRowTs(args.tableName, args.row, args.mutations, args.timestamp);
        } catch (IOError io) {
          result.io = io;
        } catch (IllegalArgument ia) {
          result.ia = ia;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing mutateRowTs", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing mutateRowTs");
          oprot.writeMessageBegin(new TMessage("mutateRowTs", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("mutateRowTs", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class mutateRows implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        mutateRows_args args = new mutateRows_args();
        args.read(iprot);
        iprot.readMessageEnd();
        mutateRows_result result = new mutateRows_result();
        try {
          iface_.mutateRows(args.tableName, args.rowBatches);
        } catch (IOError io) {
          result.io = io;
        } catch (IllegalArgument ia) {
          result.ia = ia;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing mutateRows", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing mutateRows");
          oprot.writeMessageBegin(new TMessage("mutateRows", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("mutateRows", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class mutateRowsTs implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        mutateRowsTs_args args = new mutateRowsTs_args();
        args.read(iprot);
        iprot.readMessageEnd();
        mutateRowsTs_result result = new mutateRowsTs_result();
        try {
          iface_.mutateRowsTs(args.tableName, args.rowBatches, args.timestamp);
        } catch (IOError io) {
          result.io = io;
        } catch (IllegalArgument ia) {
          result.ia = ia;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing mutateRowsTs", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing mutateRowsTs");
          oprot.writeMessageBegin(new TMessage("mutateRowsTs", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("mutateRowsTs", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class atomicIncrement implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        atomicIncrement_args args = new atomicIncrement_args();
        args.read(iprot);
        iprot.readMessageEnd();
        atomicIncrement_result result = new atomicIncrement_result();
        try {
          result.success = iface_.atomicIncrement(args.tableName, args.row, args.column, args.value);
          result.setSuccessIsSet(true);
        } catch (IOError io) {
          result.io = io;
        } catch (IllegalArgument ia) {
          result.ia = ia;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing atomicIncrement", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing atomicIncrement");
          oprot.writeMessageBegin(new TMessage("atomicIncrement", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("atomicIncrement", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class deleteAll implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        deleteAll_args args = new deleteAll_args();
        args.read(iprot);
        iprot.readMessageEnd();
        deleteAll_result result = new deleteAll_result();
        try {
          iface_.deleteAll(args.tableName, args.row, args.column);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing deleteAll", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing deleteAll");
          oprot.writeMessageBegin(new TMessage("deleteAll", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("deleteAll", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class deleteAllTs implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        deleteAllTs_args args = new deleteAllTs_args();
        args.read(iprot);
        iprot.readMessageEnd();
        deleteAllTs_result result = new deleteAllTs_result();
        try {
          iface_.deleteAllTs(args.tableName, args.row, args.column, args.timestamp);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing deleteAllTs", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing deleteAllTs");
          oprot.writeMessageBegin(new TMessage("deleteAllTs", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("deleteAllTs", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class deleteAllRow implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        deleteAllRow_args args = new deleteAllRow_args();
        args.read(iprot);
        iprot.readMessageEnd();
        deleteAllRow_result result = new deleteAllRow_result();
        try {
          iface_.deleteAllRow(args.tableName, args.row);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing deleteAllRow", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing deleteAllRow");
          oprot.writeMessageBegin(new TMessage("deleteAllRow", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("deleteAllRow", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class deleteAllRowTs implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        deleteAllRowTs_args args = new deleteAllRowTs_args();
        args.read(iprot);
        iprot.readMessageEnd();
        deleteAllRowTs_result result = new deleteAllRowTs_result();
        try {
          iface_.deleteAllRowTs(args.tableName, args.row, args.timestamp);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing deleteAllRowTs", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing deleteAllRowTs");
          oprot.writeMessageBegin(new TMessage("deleteAllRowTs", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("deleteAllRowTs", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class scannerOpen implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        scannerOpen_args args = new scannerOpen_args();
        args.read(iprot);
        iprot.readMessageEnd();
        scannerOpen_result result = new scannerOpen_result();
        try {
          result.success = iface_.scannerOpen(args.tableName, args.startRow, args.columns);
          result.setSuccessIsSet(true);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing scannerOpen", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing scannerOpen");
          oprot.writeMessageBegin(new TMessage("scannerOpen", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("scannerOpen", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class scannerOpenWithStop implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        scannerOpenWithStop_args args = new scannerOpenWithStop_args();
        args.read(iprot);
        iprot.readMessageEnd();
        scannerOpenWithStop_result result = new scannerOpenWithStop_result();
        try {
          result.success = iface_.scannerOpenWithStop(args.tableName, args.startRow, args.stopRow, args.columns);
          result.setSuccessIsSet(true);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing scannerOpenWithStop", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing scannerOpenWithStop");
          oprot.writeMessageBegin(new TMessage("scannerOpenWithStop", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("scannerOpenWithStop", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class scannerOpenWithPrefix implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        scannerOpenWithPrefix_args args = new scannerOpenWithPrefix_args();
        args.read(iprot);
        iprot.readMessageEnd();
        scannerOpenWithPrefix_result result = new scannerOpenWithPrefix_result();
        try {
          result.success = iface_.scannerOpenWithPrefix(args.tableName, args.startAndPrefix, args.columns);
          result.setSuccessIsSet(true);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing scannerOpenWithPrefix", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing scannerOpenWithPrefix");
          oprot.writeMessageBegin(new TMessage("scannerOpenWithPrefix", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("scannerOpenWithPrefix", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class scannerOpenTs implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        scannerOpenTs_args args = new scannerOpenTs_args();
        args.read(iprot);
        iprot.readMessageEnd();
        scannerOpenTs_result result = new scannerOpenTs_result();
        try {
          result.success = iface_.scannerOpenTs(args.tableName, args.startRow, args.columns, args.timestamp);
          result.setSuccessIsSet(true);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing scannerOpenTs", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing scannerOpenTs");
          oprot.writeMessageBegin(new TMessage("scannerOpenTs", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("scannerOpenTs", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class scannerOpenWithStopTs implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        scannerOpenWithStopTs_args args = new scannerOpenWithStopTs_args();
        args.read(iprot);
        iprot.readMessageEnd();
        scannerOpenWithStopTs_result result = new scannerOpenWithStopTs_result();
        try {
          result.success = iface_.scannerOpenWithStopTs(args.tableName, args.startRow, args.stopRow, args.columns, args.timestamp);
          result.setSuccessIsSet(true);
        } catch (IOError io) {
          result.io = io;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing scannerOpenWithStopTs", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing scannerOpenWithStopTs");
          oprot.writeMessageBegin(new TMessage("scannerOpenWithStopTs", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("scannerOpenWithStopTs", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class scannerGet implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        scannerGet_args args = new scannerGet_args();
        args.read(iprot);
        iprot.readMessageEnd();
        scannerGet_result result = new scannerGet_result();
        try {
          result.success = iface_.scannerGet(args.id);
        } catch (IOError io) {
          result.io = io;
        } catch (IllegalArgument ia) {
          result.ia = ia;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing scannerGet", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing scannerGet");
          oprot.writeMessageBegin(new TMessage("scannerGet", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("scannerGet", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class scannerGetList implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        scannerGetList_args args = new scannerGetList_args();
        args.read(iprot);
        iprot.readMessageEnd();
        scannerGetList_result result = new scannerGetList_result();
        try {
          result.success = iface_.scannerGetList(args.id, args.nbRows);
        } catch (IOError io) {
          result.io = io;
        } catch (IllegalArgument ia) {
          result.ia = ia;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing scannerGetList", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing scannerGetList");
          oprot.writeMessageBegin(new TMessage("scannerGetList", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("scannerGetList", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

    private class scannerClose implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        scannerClose_args args = new scannerClose_args();
        args.read(iprot);
        iprot.readMessageEnd();
        scannerClose_result result = new scannerClose_result();
        try {
          iface_.scannerClose(args.id);
        } catch (IOError io) {
          result.io = io;
        } catch (IllegalArgument ia) {
          result.ia = ia;
        } catch (Throwable th) {
          LOGGER.error("Internal error processing scannerClose", th);
          TApplicationException x = new TApplicationException(TApplicationException.INTERNAL_ERROR, "Internal error processing scannerClose");
          oprot.writeMessageBegin(new TMessage("scannerClose", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        oprot.writeMessageBegin(new TMessage("scannerClose", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

  }

  public static class enableTable_args implements TBase<enableTable_args._Fields>, java.io.Serializable, Cloneable, Comparable<enableTable_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("enableTable_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);

    /**
     * name of the table
     */
    public byte[] tableName;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of the table
       */
      TABLE_NAME((short)1, "tableName");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(enableTable_args.class, metaDataMap);
    }

    public enableTable_args() {
    }

    public enableTable_args(
      byte[] tableName)
    {
      this();
      this.tableName = tableName;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public enableTable_args(enableTable_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
    }

    public enableTable_args deepCopy() {
      return new enableTable_args(this);
    }

    @Deprecated
    public enableTable_args clone() {
      return new enableTable_args(this);
    }

    /**
     * name of the table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of the table
     */
    public enableTable_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof enableTable_args)
        return this.equals((enableTable_args)that);
      return false;
    }

    public boolean equals(enableTable_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      return builder.toHashCode();
    }

    public int compareTo(enableTable_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      enableTable_args typedOther = (enableTable_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("enableTable_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class enableTable_result implements TBase<enableTable_result._Fields>, java.io.Serializable, Cloneable, Comparable<enableTable_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("enableTable_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(enableTable_result.class, metaDataMap);
    }

    public enableTable_result() {
    }

    public enableTable_result(
      IOError io)
    {
      this();
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public enableTable_result(enableTable_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public enableTable_result deepCopy() {
      return new enableTable_result(this);
    }

    @Deprecated
    public enableTable_result clone() {
      return new enableTable_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public enableTable_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof enableTable_result)
        return this.equals((enableTable_result)that);
      return false;
    }

    public boolean equals(enableTable_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(enableTable_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      enableTable_result typedOther = (enableTable_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("enableTable_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class disableTable_args implements TBase<disableTable_args._Fields>, java.io.Serializable, Cloneable, Comparable<disableTable_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("disableTable_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);

    /**
     * name of the table
     */
    public byte[] tableName;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of the table
       */
      TABLE_NAME((short)1, "tableName");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(disableTable_args.class, metaDataMap);
    }

    public disableTable_args() {
    }

    public disableTable_args(
      byte[] tableName)
    {
      this();
      this.tableName = tableName;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public disableTable_args(disableTable_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
    }

    public disableTable_args deepCopy() {
      return new disableTable_args(this);
    }

    @Deprecated
    public disableTable_args clone() {
      return new disableTable_args(this);
    }

    /**
     * name of the table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of the table
     */
    public disableTable_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof disableTable_args)
        return this.equals((disableTable_args)that);
      return false;
    }

    public boolean equals(disableTable_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      return builder.toHashCode();
    }

    public int compareTo(disableTable_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      disableTable_args typedOther = (disableTable_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("disableTable_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class disableTable_result implements TBase<disableTable_result._Fields>, java.io.Serializable, Cloneable, Comparable<disableTable_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("disableTable_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(disableTable_result.class, metaDataMap);
    }

    public disableTable_result() {
    }

    public disableTable_result(
      IOError io)
    {
      this();
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public disableTable_result(disableTable_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public disableTable_result deepCopy() {
      return new disableTable_result(this);
    }

    @Deprecated
    public disableTable_result clone() {
      return new disableTable_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public disableTable_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof disableTable_result)
        return this.equals((disableTable_result)that);
      return false;
    }

    public boolean equals(disableTable_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(disableTable_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      disableTable_result typedOther = (disableTable_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("disableTable_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class isTableEnabled_args implements TBase<isTableEnabled_args._Fields>, java.io.Serializable, Cloneable, Comparable<isTableEnabled_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("isTableEnabled_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);

    /**
     * name of the table to check
     */
    public byte[] tableName;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of the table to check
       */
      TABLE_NAME((short)1, "tableName");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(isTableEnabled_args.class, metaDataMap);
    }

    public isTableEnabled_args() {
    }

    public isTableEnabled_args(
      byte[] tableName)
    {
      this();
      this.tableName = tableName;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public isTableEnabled_args(isTableEnabled_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
    }

    public isTableEnabled_args deepCopy() {
      return new isTableEnabled_args(this);
    }

    @Deprecated
    public isTableEnabled_args clone() {
      return new isTableEnabled_args(this);
    }

    /**
     * name of the table to check
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of the table to check
     */
    public isTableEnabled_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof isTableEnabled_args)
        return this.equals((isTableEnabled_args)that);
      return false;
    }

    public boolean equals(isTableEnabled_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      return builder.toHashCode();
    }

    public int compareTo(isTableEnabled_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      isTableEnabled_args typedOther = (isTableEnabled_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("isTableEnabled_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class isTableEnabled_result implements TBase<isTableEnabled_result._Fields>, java.io.Serializable, Cloneable, Comparable<isTableEnabled_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("isTableEnabled_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.BOOL, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public boolean success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __SUCCESS_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.BOOL)));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(isTableEnabled_result.class, metaDataMap);
    }

    public isTableEnabled_result() {
    }

    public isTableEnabled_result(
      boolean success,
      IOError io)
    {
      this();
      this.success = success;
      setSuccessIsSet(true);
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public isTableEnabled_result(isTableEnabled_result other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.success = other.success;
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public isTableEnabled_result deepCopy() {
      return new isTableEnabled_result(this);
    }

    @Deprecated
    public isTableEnabled_result clone() {
      return new isTableEnabled_result(this);
    }

    public boolean isSuccess() {
      return this.success;
    }

    public isTableEnabled_result setSuccess(boolean success) {
      this.success = success;
      setSuccessIsSet(true);
      return this;
    }

    public void unsetSuccess() {
      __isset_bit_vector.clear(__SUCCESS_ISSET_ID);
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return __isset_bit_vector.get(__SUCCESS_ISSET_ID);
    }

    public void setSuccessIsSet(boolean value) {
      __isset_bit_vector.set(__SUCCESS_ISSET_ID, value);
    }

    public IOError getIo() {
      return this.io;
    }

    public isTableEnabled_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((Boolean)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return new Boolean(isSuccess());

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof isTableEnabled_result)
        return this.equals((isTableEnabled_result)that);
      return false;
    }

    public boolean equals(isTableEnabled_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true;
      boolean that_present_success = true;
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (this.success != that.success)
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true;
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(isTableEnabled_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      isTableEnabled_result typedOther = (isTableEnabled_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.BOOL) {
                this.success = iprot.readBool();
                setSuccessIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        oprot.writeBool(this.success);
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("isTableEnabled_result(");
      boolean first = true;

      sb.append("success:");
      sb.append(this.success);
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class compact_args implements TBase<compact_args._Fields>, java.io.Serializable, Cloneable, Comparable<compact_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("compact_args");

    private static final TField TABLE_NAME_OR_REGION_NAME_FIELD_DESC = new TField("tableNameOrRegionName", TType.STRING, (short)1);

    public byte[] tableNameOrRegionName;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      TABLE_NAME_OR_REGION_NAME((short)1, "tableNameOrRegionName");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME_OR_REGION_NAME, new FieldMetaData("tableNameOrRegionName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(compact_args.class, metaDataMap);
    }

    public compact_args() {
    }

    public compact_args(
      byte[] tableNameOrRegionName)
    {
      this();
      this.tableNameOrRegionName = tableNameOrRegionName;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public compact_args(compact_args other) {
      if (other.isSetTableNameOrRegionName()) {
        this.tableNameOrRegionName = other.tableNameOrRegionName;
      }
    }

    public compact_args deepCopy() {
      return new compact_args(this);
    }

    @Deprecated
    public compact_args clone() {
      return new compact_args(this);
    }

    public byte[] getTableNameOrRegionName() {
      return this.tableNameOrRegionName;
    }

    public compact_args setTableNameOrRegionName(byte[] tableNameOrRegionName) {
      this.tableNameOrRegionName = tableNameOrRegionName;
      return this;
    }

    public void unsetTableNameOrRegionName() {
      this.tableNameOrRegionName = null;
    }

    /** Returns true if field tableNameOrRegionName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableNameOrRegionName() {
      return this.tableNameOrRegionName != null;
    }

    public void setTableNameOrRegionNameIsSet(boolean value) {
      if (!value) {
        this.tableNameOrRegionName = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME_OR_REGION_NAME:
        if (value == null) {
          unsetTableNameOrRegionName();
        } else {
          setTableNameOrRegionName((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME_OR_REGION_NAME:
        return getTableNameOrRegionName();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME_OR_REGION_NAME:
        return isSetTableNameOrRegionName();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof compact_args)
        return this.equals((compact_args)that);
      return false;
    }

    public boolean equals(compact_args that) {
      if (that == null)
        return false;

      boolean this_present_tableNameOrRegionName = true && this.isSetTableNameOrRegionName();
      boolean that_present_tableNameOrRegionName = true && that.isSetTableNameOrRegionName();
      if (this_present_tableNameOrRegionName || that_present_tableNameOrRegionName) {
        if (!(this_present_tableNameOrRegionName && that_present_tableNameOrRegionName))
          return false;
        if (!java.util.Arrays.equals(this.tableNameOrRegionName, that.tableNameOrRegionName))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableNameOrRegionName = true && (isSetTableNameOrRegionName());
      builder.append(present_tableNameOrRegionName);
      if (present_tableNameOrRegionName)
        builder.append(tableNameOrRegionName);

      return builder.toHashCode();
    }

    public int compareTo(compact_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      compact_args typedOther = (compact_args)other;

      lastComparison = Boolean.valueOf(isSetTableNameOrRegionName()).compareTo(isSetTableNameOrRegionName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableNameOrRegionName, typedOther.tableNameOrRegionName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME_OR_REGION_NAME:
              if (field.type == TType.STRING) {
                this.tableNameOrRegionName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableNameOrRegionName != null) {
        oprot.writeFieldBegin(TABLE_NAME_OR_REGION_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableNameOrRegionName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("compact_args(");
      boolean first = true;

      sb.append("tableNameOrRegionName:");
      if (this.tableNameOrRegionName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableNameOrRegionName);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class compact_result implements TBase<compact_result._Fields>, java.io.Serializable, Cloneable, Comparable<compact_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("compact_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(compact_result.class, metaDataMap);
    }

    public compact_result() {
    }

    public compact_result(
      IOError io)
    {
      this();
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public compact_result(compact_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public compact_result deepCopy() {
      return new compact_result(this);
    }

    @Deprecated
    public compact_result clone() {
      return new compact_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public compact_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof compact_result)
        return this.equals((compact_result)that);
      return false;
    }

    public boolean equals(compact_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(compact_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      compact_result typedOther = (compact_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("compact_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class majorCompact_args implements TBase<majorCompact_args._Fields>, java.io.Serializable, Cloneable, Comparable<majorCompact_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("majorCompact_args");

    private static final TField TABLE_NAME_OR_REGION_NAME_FIELD_DESC = new TField("tableNameOrRegionName", TType.STRING, (short)1);

    public byte[] tableNameOrRegionName;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      TABLE_NAME_OR_REGION_NAME((short)1, "tableNameOrRegionName");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME_OR_REGION_NAME, new FieldMetaData("tableNameOrRegionName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(majorCompact_args.class, metaDataMap);
    }

    public majorCompact_args() {
    }

    public majorCompact_args(
      byte[] tableNameOrRegionName)
    {
      this();
      this.tableNameOrRegionName = tableNameOrRegionName;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public majorCompact_args(majorCompact_args other) {
      if (other.isSetTableNameOrRegionName()) {
        this.tableNameOrRegionName = other.tableNameOrRegionName;
      }
    }

    public majorCompact_args deepCopy() {
      return new majorCompact_args(this);
    }

    @Deprecated
    public majorCompact_args clone() {
      return new majorCompact_args(this);
    }

    public byte[] getTableNameOrRegionName() {
      return this.tableNameOrRegionName;
    }

    public majorCompact_args setTableNameOrRegionName(byte[] tableNameOrRegionName) {
      this.tableNameOrRegionName = tableNameOrRegionName;
      return this;
    }

    public void unsetTableNameOrRegionName() {
      this.tableNameOrRegionName = null;
    }

    /** Returns true if field tableNameOrRegionName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableNameOrRegionName() {
      return this.tableNameOrRegionName != null;
    }

    public void setTableNameOrRegionNameIsSet(boolean value) {
      if (!value) {
        this.tableNameOrRegionName = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME_OR_REGION_NAME:
        if (value == null) {
          unsetTableNameOrRegionName();
        } else {
          setTableNameOrRegionName((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME_OR_REGION_NAME:
        return getTableNameOrRegionName();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME_OR_REGION_NAME:
        return isSetTableNameOrRegionName();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof majorCompact_args)
        return this.equals((majorCompact_args)that);
      return false;
    }

    public boolean equals(majorCompact_args that) {
      if (that == null)
        return false;

      boolean this_present_tableNameOrRegionName = true && this.isSetTableNameOrRegionName();
      boolean that_present_tableNameOrRegionName = true && that.isSetTableNameOrRegionName();
      if (this_present_tableNameOrRegionName || that_present_tableNameOrRegionName) {
        if (!(this_present_tableNameOrRegionName && that_present_tableNameOrRegionName))
          return false;
        if (!java.util.Arrays.equals(this.tableNameOrRegionName, that.tableNameOrRegionName))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableNameOrRegionName = true && (isSetTableNameOrRegionName());
      builder.append(present_tableNameOrRegionName);
      if (present_tableNameOrRegionName)
        builder.append(tableNameOrRegionName);

      return builder.toHashCode();
    }

    public int compareTo(majorCompact_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      majorCompact_args typedOther = (majorCompact_args)other;

      lastComparison = Boolean.valueOf(isSetTableNameOrRegionName()).compareTo(isSetTableNameOrRegionName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableNameOrRegionName, typedOther.tableNameOrRegionName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME_OR_REGION_NAME:
              if (field.type == TType.STRING) {
                this.tableNameOrRegionName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableNameOrRegionName != null) {
        oprot.writeFieldBegin(TABLE_NAME_OR_REGION_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableNameOrRegionName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("majorCompact_args(");
      boolean first = true;

      sb.append("tableNameOrRegionName:");
      if (this.tableNameOrRegionName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableNameOrRegionName);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class majorCompact_result implements TBase<majorCompact_result._Fields>, java.io.Serializable, Cloneable, Comparable<majorCompact_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("majorCompact_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(majorCompact_result.class, metaDataMap);
    }

    public majorCompact_result() {
    }

    public majorCompact_result(
      IOError io)
    {
      this();
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public majorCompact_result(majorCompact_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public majorCompact_result deepCopy() {
      return new majorCompact_result(this);
    }

    @Deprecated
    public majorCompact_result clone() {
      return new majorCompact_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public majorCompact_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof majorCompact_result)
        return this.equals((majorCompact_result)that);
      return false;
    }

    public boolean equals(majorCompact_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(majorCompact_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      majorCompact_result typedOther = (majorCompact_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("majorCompact_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getTableNames_args implements TBase<getTableNames_args._Fields>, java.io.Serializable, Cloneable, Comparable<getTableNames_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("getTableNames_args");



    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
;

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }
    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getTableNames_args.class, metaDataMap);
    }

    public getTableNames_args() {
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getTableNames_args(getTableNames_args other) {
    }

    public getTableNames_args deepCopy() {
      return new getTableNames_args(this);
    }

    @Deprecated
    public getTableNames_args clone() {
      return new getTableNames_args(this);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getTableNames_args)
        return this.equals((getTableNames_args)that);
      return false;
    }

    public boolean equals(getTableNames_args that) {
      if (that == null)
        return false;

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      return builder.toHashCode();
    }

    public int compareTo(getTableNames_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getTableNames_args typedOther = (getTableNames_args)other;

      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getTableNames_args(");
      boolean first = true;

      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getTableNames_result implements TBase<getTableNames_result._Fields>, java.io.Serializable, Cloneable, Comparable<getTableNames_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("getTableNames_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.LIST, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public List<byte[]> success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new FieldValueMetaData(TType.STRING))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getTableNames_result.class, metaDataMap);
    }

    public getTableNames_result() {
    }

    public getTableNames_result(
      List<byte[]> success,
      IOError io)
    {
      this();
      this.success = success;
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getTableNames_result(getTableNames_result other) {
      if (other.isSetSuccess()) {
        List<byte[]> __this__success = new ArrayList<byte[]>();
        for (byte[] other_element : other.success) {
          __this__success.add(other_element);
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public getTableNames_result deepCopy() {
      return new getTableNames_result(this);
    }

    @Deprecated
    public getTableNames_result clone() {
      return new getTableNames_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<byte[]> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(byte[] elem) {
      if (this.success == null) {
        this.success = new ArrayList<byte[]>();
      }
      this.success.add(elem);
    }

    public List<byte[]> getSuccess() {
      return this.success;
    }

    public getTableNames_result setSuccess(List<byte[]> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public getTableNames_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<byte[]>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getTableNames_result)
        return this.equals((getTableNames_result)that);
      return false;
    }

    public boolean equals(getTableNames_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(getTableNames_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getTableNames_result typedOther = (getTableNames_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.LIST) {
                {
                  TList _list9 = iprot.readListBegin();
                  this.success = new ArrayList<byte[]>(_list9.size);
                  for (int _i10 = 0; _i10 < _list9.size; ++_i10)
                  {
                    byte[] _elem11;
                    _elem11 = iprot.readBinary();
                    this.success.add(_elem11);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRING, this.success.size()));
          for (byte[] _iter12 : this.success)
          {
            oprot.writeBinary(_iter12);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getTableNames_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getColumnDescriptors_args implements TBase<getColumnDescriptors_args._Fields>, java.io.Serializable, Cloneable, Comparable<getColumnDescriptors_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("getColumnDescriptors_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);

    /**
     * table name
     */
    public byte[] tableName;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * table name
       */
      TABLE_NAME((short)1, "tableName");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getColumnDescriptors_args.class, metaDataMap);
    }

    public getColumnDescriptors_args() {
    }

    public getColumnDescriptors_args(
      byte[] tableName)
    {
      this();
      this.tableName = tableName;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getColumnDescriptors_args(getColumnDescriptors_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
    }

    public getColumnDescriptors_args deepCopy() {
      return new getColumnDescriptors_args(this);
    }

    @Deprecated
    public getColumnDescriptors_args clone() {
      return new getColumnDescriptors_args(this);
    }

    /**
     * table name
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * table name
     */
    public getColumnDescriptors_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getColumnDescriptors_args)
        return this.equals((getColumnDescriptors_args)that);
      return false;
    }

    public boolean equals(getColumnDescriptors_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      return builder.toHashCode();
    }

    public int compareTo(getColumnDescriptors_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getColumnDescriptors_args typedOther = (getColumnDescriptors_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getColumnDescriptors_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getColumnDescriptors_result implements TBase<getColumnDescriptors_result._Fields>, java.io.Serializable, Cloneable   {
    private static final TStruct STRUCT_DESC = new TStruct("getColumnDescriptors_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.MAP, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public Map<byte[],ColumnDescriptor> success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new MapMetaData(TType.MAP, 
              new FieldValueMetaData(TType.STRING), 
              new StructMetaData(TType.STRUCT, ColumnDescriptor.class))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getColumnDescriptors_result.class, metaDataMap);
    }

    public getColumnDescriptors_result() {
    }

    public getColumnDescriptors_result(
      Map<byte[],ColumnDescriptor> success,
      IOError io)
    {
      this();
      this.success = success;
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getColumnDescriptors_result(getColumnDescriptors_result other) {
      if (other.isSetSuccess()) {
        Map<byte[],ColumnDescriptor> __this__success = new HashMap<byte[],ColumnDescriptor>();
        for (Map.Entry<byte[], ColumnDescriptor> other_element : other.success.entrySet()) {

          byte[] other_element_key = other_element.getKey();
          ColumnDescriptor other_element_value = other_element.getValue();

          byte[] __this__success_copy_key = other_element_key;

          ColumnDescriptor __this__success_copy_value = new ColumnDescriptor(other_element_value);

          __this__success.put(__this__success_copy_key, __this__success_copy_value);
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public getColumnDescriptors_result deepCopy() {
      return new getColumnDescriptors_result(this);
    }

    @Deprecated
    public getColumnDescriptors_result clone() {
      return new getColumnDescriptors_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public void putToSuccess(byte[] key, ColumnDescriptor val) {
      if (this.success == null) {
        this.success = new HashMap<byte[],ColumnDescriptor>();
      }
      this.success.put(key, val);
    }

    public Map<byte[],ColumnDescriptor> getSuccess() {
      return this.success;
    }

    public getColumnDescriptors_result setSuccess(Map<byte[],ColumnDescriptor> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public getColumnDescriptors_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((Map<byte[],ColumnDescriptor>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getColumnDescriptors_result)
        return this.equals((getColumnDescriptors_result)that);
      return false;
    }

    public boolean equals(getColumnDescriptors_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.MAP) {
                {
                  TMap _map13 = iprot.readMapBegin();
                  this.success = new HashMap<byte[],ColumnDescriptor>(2*_map13.size);
                  for (int _i14 = 0; _i14 < _map13.size; ++_i14)
                  {
                    byte[] _key15;
                    ColumnDescriptor _val16;
                    _key15 = iprot.readBinary();
                    _val16 = new ColumnDescriptor();
                    _val16.read(iprot);
                    this.success.put(_key15, _val16);
                  }
                  iprot.readMapEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeMapBegin(new TMap(TType.STRING, TType.STRUCT, this.success.size()));
          for (Map.Entry<byte[], ColumnDescriptor> _iter17 : this.success.entrySet())
          {
            oprot.writeBinary(_iter17.getKey());
            _iter17.getValue().write(oprot);
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getColumnDescriptors_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getTableRegions_args implements TBase<getTableRegions_args._Fields>, java.io.Serializable, Cloneable, Comparable<getTableRegions_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("getTableRegions_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);

    /**
     * table name
     */
    public byte[] tableName;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * table name
       */
      TABLE_NAME((short)1, "tableName");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getTableRegions_args.class, metaDataMap);
    }

    public getTableRegions_args() {
    }

    public getTableRegions_args(
      byte[] tableName)
    {
      this();
      this.tableName = tableName;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getTableRegions_args(getTableRegions_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
    }

    public getTableRegions_args deepCopy() {
      return new getTableRegions_args(this);
    }

    @Deprecated
    public getTableRegions_args clone() {
      return new getTableRegions_args(this);
    }

    /**
     * table name
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * table name
     */
    public getTableRegions_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getTableRegions_args)
        return this.equals((getTableRegions_args)that);
      return false;
    }

    public boolean equals(getTableRegions_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      return builder.toHashCode();
    }

    public int compareTo(getTableRegions_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getTableRegions_args typedOther = (getTableRegions_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getTableRegions_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getTableRegions_result implements TBase<getTableRegions_result._Fields>, java.io.Serializable, Cloneable, Comparable<getTableRegions_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("getTableRegions_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.LIST, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public List<TRegionInfo> success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, TRegionInfo.class))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getTableRegions_result.class, metaDataMap);
    }

    public getTableRegions_result() {
    }

    public getTableRegions_result(
      List<TRegionInfo> success,
      IOError io)
    {
      this();
      this.success = success;
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getTableRegions_result(getTableRegions_result other) {
      if (other.isSetSuccess()) {
        List<TRegionInfo> __this__success = new ArrayList<TRegionInfo>();
        for (TRegionInfo other_element : other.success) {
          __this__success.add(new TRegionInfo(other_element));
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public getTableRegions_result deepCopy() {
      return new getTableRegions_result(this);
    }

    @Deprecated
    public getTableRegions_result clone() {
      return new getTableRegions_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<TRegionInfo> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(TRegionInfo elem) {
      if (this.success == null) {
        this.success = new ArrayList<TRegionInfo>();
      }
      this.success.add(elem);
    }

    public List<TRegionInfo> getSuccess() {
      return this.success;
    }

    public getTableRegions_result setSuccess(List<TRegionInfo> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public getTableRegions_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<TRegionInfo>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getTableRegions_result)
        return this.equals((getTableRegions_result)that);
      return false;
    }

    public boolean equals(getTableRegions_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(getTableRegions_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getTableRegions_result typedOther = (getTableRegions_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.LIST) {
                {
                  TList _list18 = iprot.readListBegin();
                  this.success = new ArrayList<TRegionInfo>(_list18.size);
                  for (int _i19 = 0; _i19 < _list18.size; ++_i19)
                  {
                    TRegionInfo _elem20;
                    _elem20 = new TRegionInfo();
                    _elem20.read(iprot);
                    this.success.add(_elem20);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.success.size()));
          for (TRegionInfo _iter21 : this.success)
          {
            _iter21.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getTableRegions_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class createTable_args implements TBase<createTable_args._Fields>, java.io.Serializable, Cloneable, Comparable<createTable_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("createTable_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField COLUMN_FAMILIES_FIELD_DESC = new TField("columnFamilies", TType.LIST, (short)2);

    /**
     * name of table to create
     */
    public byte[] tableName;
    /**
     * list of column family descriptors
     */
    public List<ColumnDescriptor> columnFamilies;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table to create
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * list of column family descriptors
       */
      COLUMN_FAMILIES((short)2, "columnFamilies");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMN_FAMILIES, new FieldMetaData("columnFamilies", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, ColumnDescriptor.class))));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(createTable_args.class, metaDataMap);
    }

    public createTable_args() {
    }

    public createTable_args(
      byte[] tableName,
      List<ColumnDescriptor> columnFamilies)
    {
      this();
      this.tableName = tableName;
      this.columnFamilies = columnFamilies;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public createTable_args(createTable_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetColumnFamilies()) {
        List<ColumnDescriptor> __this__columnFamilies = new ArrayList<ColumnDescriptor>();
        for (ColumnDescriptor other_element : other.columnFamilies) {
          __this__columnFamilies.add(new ColumnDescriptor(other_element));
        }
        this.columnFamilies = __this__columnFamilies;
      }
    }

    public createTable_args deepCopy() {
      return new createTable_args(this);
    }

    @Deprecated
    public createTable_args clone() {
      return new createTable_args(this);
    }

    /**
     * name of table to create
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table to create
     */
    public createTable_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    public int getColumnFamiliesSize() {
      return (this.columnFamilies == null) ? 0 : this.columnFamilies.size();
    }

    public java.util.Iterator<ColumnDescriptor> getColumnFamiliesIterator() {
      return (this.columnFamilies == null) ? null : this.columnFamilies.iterator();
    }

    public void addToColumnFamilies(ColumnDescriptor elem) {
      if (this.columnFamilies == null) {
        this.columnFamilies = new ArrayList<ColumnDescriptor>();
      }
      this.columnFamilies.add(elem);
    }

    /**
     * list of column family descriptors
     */
    public List<ColumnDescriptor> getColumnFamilies() {
      return this.columnFamilies;
    }

    /**
     * list of column family descriptors
     */
    public createTable_args setColumnFamilies(List<ColumnDescriptor> columnFamilies) {
      this.columnFamilies = columnFamilies;
      return this;
    }

    public void unsetColumnFamilies() {
      this.columnFamilies = null;
    }

    /** Returns true if field columnFamilies is set (has been asigned a value) and false otherwise */
    public boolean isSetColumnFamilies() {
      return this.columnFamilies != null;
    }

    public void setColumnFamiliesIsSet(boolean value) {
      if (!value) {
        this.columnFamilies = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case COLUMN_FAMILIES:
        if (value == null) {
          unsetColumnFamilies();
        } else {
          setColumnFamilies((List<ColumnDescriptor>)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case COLUMN_FAMILIES:
        return getColumnFamilies();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case COLUMN_FAMILIES:
        return isSetColumnFamilies();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof createTable_args)
        return this.equals((createTable_args)that);
      return false;
    }

    public boolean equals(createTable_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_columnFamilies = true && this.isSetColumnFamilies();
      boolean that_present_columnFamilies = true && that.isSetColumnFamilies();
      if (this_present_columnFamilies || that_present_columnFamilies) {
        if (!(this_present_columnFamilies && that_present_columnFamilies))
          return false;
        if (!this.columnFamilies.equals(that.columnFamilies))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_columnFamilies = true && (isSetColumnFamilies());
      builder.append(present_columnFamilies);
      if (present_columnFamilies)
        builder.append(columnFamilies);

      return builder.toHashCode();
    }

    public int compareTo(createTable_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      createTable_args typedOther = (createTable_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumnFamilies()).compareTo(isSetColumnFamilies());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(columnFamilies, typedOther.columnFamilies);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMN_FAMILIES:
              if (field.type == TType.LIST) {
                {
                  TList _list22 = iprot.readListBegin();
                  this.columnFamilies = new ArrayList<ColumnDescriptor>(_list22.size);
                  for (int _i23 = 0; _i23 < _list22.size; ++_i23)
                  {
                    ColumnDescriptor _elem24;
                    _elem24 = new ColumnDescriptor();
                    _elem24.read(iprot);
                    this.columnFamilies.add(_elem24);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.columnFamilies != null) {
        oprot.writeFieldBegin(COLUMN_FAMILIES_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.columnFamilies.size()));
          for (ColumnDescriptor _iter25 : this.columnFamilies)
          {
            _iter25.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("createTable_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("columnFamilies:");
      if (this.columnFamilies == null) {
        sb.append("null");
      } else {
        sb.append(this.columnFamilies);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class createTable_result implements TBase<createTable_result._Fields>, java.io.Serializable, Cloneable, Comparable<createTable_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("createTable_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);
    private static final TField IA_FIELD_DESC = new TField("ia", TType.STRUCT, (short)2);
    private static final TField EXIST_FIELD_DESC = new TField("exist", TType.STRUCT, (short)3);

    public IOError io;
    public IllegalArgument ia;
    public AlreadyExists exist;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io"),
      IA((short)2, "ia"),
      EXIST((short)3, "exist");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
      put(_Fields.IA, new FieldMetaData("ia", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
      put(_Fields.EXIST, new FieldMetaData("exist", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(createTable_result.class, metaDataMap);
    }

    public createTable_result() {
    }

    public createTable_result(
      IOError io,
      IllegalArgument ia,
      AlreadyExists exist)
    {
      this();
      this.io = io;
      this.ia = ia;
      this.exist = exist;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public createTable_result(createTable_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
      if (other.isSetIa()) {
        this.ia = new IllegalArgument(other.ia);
      }
      if (other.isSetExist()) {
        this.exist = new AlreadyExists(other.exist);
      }
    }

    public createTable_result deepCopy() {
      return new createTable_result(this);
    }

    @Deprecated
    public createTable_result clone() {
      return new createTable_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public createTable_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public IllegalArgument getIa() {
      return this.ia;
    }

    public createTable_result setIa(IllegalArgument ia) {
      this.ia = ia;
      return this;
    }

    public void unsetIa() {
      this.ia = null;
    }

    /** Returns true if field ia is set (has been asigned a value) and false otherwise */
    public boolean isSetIa() {
      return this.ia != null;
    }

    public void setIaIsSet(boolean value) {
      if (!value) {
        this.ia = null;
      }
    }

    public AlreadyExists getExist() {
      return this.exist;
    }

    public createTable_result setExist(AlreadyExists exist) {
      this.exist = exist;
      return this;
    }

    public void unsetExist() {
      this.exist = null;
    }

    /** Returns true if field exist is set (has been asigned a value) and false otherwise */
    public boolean isSetExist() {
      return this.exist != null;
    }

    public void setExistIsSet(boolean value) {
      if (!value) {
        this.exist = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      case IA:
        if (value == null) {
          unsetIa();
        } else {
          setIa((IllegalArgument)value);
        }
        break;

      case EXIST:
        if (value == null) {
          unsetExist();
        } else {
          setExist((AlreadyExists)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      case IA:
        return getIa();

      case EXIST:
        return getExist();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      case IA:
        return isSetIa();
      case EXIST:
        return isSetExist();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof createTable_result)
        return this.equals((createTable_result)that);
      return false;
    }

    public boolean equals(createTable_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      boolean this_present_ia = true && this.isSetIa();
      boolean that_present_ia = true && that.isSetIa();
      if (this_present_ia || that_present_ia) {
        if (!(this_present_ia && that_present_ia))
          return false;
        if (!this.ia.equals(that.ia))
          return false;
      }

      boolean this_present_exist = true && this.isSetExist();
      boolean that_present_exist = true && that.isSetExist();
      if (this_present_exist || that_present_exist) {
        if (!(this_present_exist && that_present_exist))
          return false;
        if (!this.exist.equals(that.exist))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      boolean present_ia = true && (isSetIa());
      builder.append(present_ia);
      if (present_ia)
        builder.append(ia);

      boolean present_exist = true && (isSetExist());
      builder.append(present_exist);
      if (present_exist)
        builder.append(exist);

      return builder.toHashCode();
    }

    public int compareTo(createTable_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      createTable_result typedOther = (createTable_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIa()).compareTo(isSetIa());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(ia, typedOther.ia);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetExist()).compareTo(isSetExist());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(exist, typedOther.exist);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IA:
              if (field.type == TType.STRUCT) {
                this.ia = new IllegalArgument();
                this.ia.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case EXIST:
              if (field.type == TType.STRUCT) {
                this.exist = new AlreadyExists();
                this.exist.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      } else if (this.isSetIa()) {
        oprot.writeFieldBegin(IA_FIELD_DESC);
        this.ia.write(oprot);
        oprot.writeFieldEnd();
      } else if (this.isSetExist()) {
        oprot.writeFieldBegin(EXIST_FIELD_DESC);
        this.exist.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("createTable_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ia:");
      if (this.ia == null) {
        sb.append("null");
      } else {
        sb.append(this.ia);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("exist:");
      if (this.exist == null) {
        sb.append("null");
      } else {
        sb.append(this.exist);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class deleteTable_args implements TBase<deleteTable_args._Fields>, java.io.Serializable, Cloneable, Comparable<deleteTable_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("deleteTable_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);

    /**
     * name of table to delete
     */
    public byte[] tableName;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table to delete
       */
      TABLE_NAME((short)1, "tableName");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(deleteTable_args.class, metaDataMap);
    }

    public deleteTable_args() {
    }

    public deleteTable_args(
      byte[] tableName)
    {
      this();
      this.tableName = tableName;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public deleteTable_args(deleteTable_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
    }

    public deleteTable_args deepCopy() {
      return new deleteTable_args(this);
    }

    @Deprecated
    public deleteTable_args clone() {
      return new deleteTable_args(this);
    }

    /**
     * name of table to delete
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table to delete
     */
    public deleteTable_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof deleteTable_args)
        return this.equals((deleteTable_args)that);
      return false;
    }

    public boolean equals(deleteTable_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      return builder.toHashCode();
    }

    public int compareTo(deleteTable_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      deleteTable_args typedOther = (deleteTable_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("deleteTable_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class deleteTable_result implements TBase<deleteTable_result._Fields>, java.io.Serializable, Cloneable, Comparable<deleteTable_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("deleteTable_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(deleteTable_result.class, metaDataMap);
    }

    public deleteTable_result() {
    }

    public deleteTable_result(
      IOError io)
    {
      this();
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public deleteTable_result(deleteTable_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public deleteTable_result deepCopy() {
      return new deleteTable_result(this);
    }

    @Deprecated
    public deleteTable_result clone() {
      return new deleteTable_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public deleteTable_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof deleteTable_result)
        return this.equals((deleteTable_result)that);
      return false;
    }

    public boolean equals(deleteTable_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(deleteTable_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      deleteTable_result typedOther = (deleteTable_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("deleteTable_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class get_args implements TBase<get_args._Fields>, java.io.Serializable, Cloneable, Comparable<get_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("get_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField COLUMN_FIELD_DESC = new TField("column", TType.STRING, (short)3);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * row key
     */
    public byte[] row;
    /**
     * column name
     */
    public byte[] column;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * row key
       */
      ROW((short)2, "row"),
      /**
       * column name
       */
      COLUMN((short)3, "column");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMN, new FieldMetaData("column", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(get_args.class, metaDataMap);
    }

    public get_args() {
    }

    public get_args(
      byte[] tableName,
      byte[] row,
      byte[] column)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.column = column;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public get_args(get_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      if (other.isSetColumn()) {
        this.column = other.column;
      }
    }

    public get_args deepCopy() {
      return new get_args(this);
    }

    @Deprecated
    public get_args clone() {
      return new get_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public get_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * row key
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * row key
     */
    public get_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    /**
     * column name
     */
    public byte[] getColumn() {
      return this.column;
    }

    /**
     * column name
     */
    public get_args setColumn(byte[] column) {
      this.column = column;
      return this;
    }

    public void unsetColumn() {
      this.column = null;
    }

    /** Returns true if field column is set (has been asigned a value) and false otherwise */
    public boolean isSetColumn() {
      return this.column != null;
    }

    public void setColumnIsSet(boolean value) {
      if (!value) {
        this.column = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case COLUMN:
        if (value == null) {
          unsetColumn();
        } else {
          setColumn((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case COLUMN:
        return getColumn();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case COLUMN:
        return isSetColumn();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof get_args)
        return this.equals((get_args)that);
      return false;
    }

    public boolean equals(get_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_column = true && this.isSetColumn();
      boolean that_present_column = true && that.isSetColumn();
      if (this_present_column || that_present_column) {
        if (!(this_present_column && that_present_column))
          return false;
        if (!java.util.Arrays.equals(this.column, that.column))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_column = true && (isSetColumn());
      builder.append(present_column);
      if (present_column)
        builder.append(column);

      return builder.toHashCode();
    }

    public int compareTo(get_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      get_args typedOther = (get_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumn()).compareTo(isSetColumn());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(column, typedOther.column);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMN:
              if (field.type == TType.STRING) {
                this.column = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      if (this.column != null) {
        oprot.writeFieldBegin(COLUMN_FIELD_DESC);
        oprot.writeBinary(this.column);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("get_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("column:");
      if (this.column == null) {
        sb.append("null");
      } else {
        sb.append(this.column);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class get_result implements TBase<get_result._Fields>, java.io.Serializable, Cloneable, Comparable<get_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("get_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.LIST, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public List<TCell> success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, TCell.class))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(get_result.class, metaDataMap);
    }

    public get_result() {
    }

    public get_result(
      List<TCell> success,
      IOError io)
    {
      this();
      this.success = success;
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public get_result(get_result other) {
      if (other.isSetSuccess()) {
        List<TCell> __this__success = new ArrayList<TCell>();
        for (TCell other_element : other.success) {
          __this__success.add(new TCell(other_element));
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public get_result deepCopy() {
      return new get_result(this);
    }

    @Deprecated
    public get_result clone() {
      return new get_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<TCell> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(TCell elem) {
      if (this.success == null) {
        this.success = new ArrayList<TCell>();
      }
      this.success.add(elem);
    }

    public List<TCell> getSuccess() {
      return this.success;
    }

    public get_result setSuccess(List<TCell> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public get_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<TCell>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof get_result)
        return this.equals((get_result)that);
      return false;
    }

    public boolean equals(get_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(get_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      get_result typedOther = (get_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.LIST) {
                {
                  TList _list26 = iprot.readListBegin();
                  this.success = new ArrayList<TCell>(_list26.size);
                  for (int _i27 = 0; _i27 < _list26.size; ++_i27)
                  {
                    TCell _elem28;
                    _elem28 = new TCell();
                    _elem28.read(iprot);
                    this.success.add(_elem28);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.success.size()));
          for (TCell _iter29 : this.success)
          {
            _iter29.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("get_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getVer_args implements TBase<getVer_args._Fields>, java.io.Serializable, Cloneable, Comparable<getVer_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("getVer_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField COLUMN_FIELD_DESC = new TField("column", TType.STRING, (short)3);
    private static final TField NUM_VERSIONS_FIELD_DESC = new TField("numVersions", TType.I32, (short)4);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * row key
     */
    public byte[] row;
    /**
     * column name
     */
    public byte[] column;
    /**
     * number of versions to retrieve
     */
    public int numVersions;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * row key
       */
      ROW((short)2, "row"),
      /**
       * column name
       */
      COLUMN((short)3, "column"),
      /**
       * number of versions to retrieve
       */
      NUM_VERSIONS((short)4, "numVersions");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __NUMVERSIONS_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMN, new FieldMetaData("column", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.NUM_VERSIONS, new FieldMetaData("numVersions", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I32)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getVer_args.class, metaDataMap);
    }

    public getVer_args() {
    }

    public getVer_args(
      byte[] tableName,
      byte[] row,
      byte[] column,
      int numVersions)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.column = column;
      this.numVersions = numVersions;
      setNumVersionsIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getVer_args(getVer_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      if (other.isSetColumn()) {
        this.column = other.column;
      }
      this.numVersions = other.numVersions;
    }

    public getVer_args deepCopy() {
      return new getVer_args(this);
    }

    @Deprecated
    public getVer_args clone() {
      return new getVer_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public getVer_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * row key
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * row key
     */
    public getVer_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    /**
     * column name
     */
    public byte[] getColumn() {
      return this.column;
    }

    /**
     * column name
     */
    public getVer_args setColumn(byte[] column) {
      this.column = column;
      return this;
    }

    public void unsetColumn() {
      this.column = null;
    }

    /** Returns true if field column is set (has been asigned a value) and false otherwise */
    public boolean isSetColumn() {
      return this.column != null;
    }

    public void setColumnIsSet(boolean value) {
      if (!value) {
        this.column = null;
      }
    }

    /**
     * number of versions to retrieve
     */
    public int getNumVersions() {
      return this.numVersions;
    }

    /**
     * number of versions to retrieve
     */
    public getVer_args setNumVersions(int numVersions) {
      this.numVersions = numVersions;
      setNumVersionsIsSet(true);
      return this;
    }

    public void unsetNumVersions() {
      __isset_bit_vector.clear(__NUMVERSIONS_ISSET_ID);
    }

    /** Returns true if field numVersions is set (has been asigned a value) and false otherwise */
    public boolean isSetNumVersions() {
      return __isset_bit_vector.get(__NUMVERSIONS_ISSET_ID);
    }

    public void setNumVersionsIsSet(boolean value) {
      __isset_bit_vector.set(__NUMVERSIONS_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case COLUMN:
        if (value == null) {
          unsetColumn();
        } else {
          setColumn((byte[])value);
        }
        break;

      case NUM_VERSIONS:
        if (value == null) {
          unsetNumVersions();
        } else {
          setNumVersions((Integer)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case COLUMN:
        return getColumn();

      case NUM_VERSIONS:
        return new Integer(getNumVersions());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case COLUMN:
        return isSetColumn();
      case NUM_VERSIONS:
        return isSetNumVersions();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getVer_args)
        return this.equals((getVer_args)that);
      return false;
    }

    public boolean equals(getVer_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_column = true && this.isSetColumn();
      boolean that_present_column = true && that.isSetColumn();
      if (this_present_column || that_present_column) {
        if (!(this_present_column && that_present_column))
          return false;
        if (!java.util.Arrays.equals(this.column, that.column))
          return false;
      }

      boolean this_present_numVersions = true;
      boolean that_present_numVersions = true;
      if (this_present_numVersions || that_present_numVersions) {
        if (!(this_present_numVersions && that_present_numVersions))
          return false;
        if (this.numVersions != that.numVersions)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_column = true && (isSetColumn());
      builder.append(present_column);
      if (present_column)
        builder.append(column);

      boolean present_numVersions = true;
      builder.append(present_numVersions);
      if (present_numVersions)
        builder.append(numVersions);

      return builder.toHashCode();
    }

    public int compareTo(getVer_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getVer_args typedOther = (getVer_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumn()).compareTo(isSetColumn());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(column, typedOther.column);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetNumVersions()).compareTo(isSetNumVersions());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(numVersions, typedOther.numVersions);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMN:
              if (field.type == TType.STRING) {
                this.column = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case NUM_VERSIONS:
              if (field.type == TType.I32) {
                this.numVersions = iprot.readI32();
                setNumVersionsIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      if (this.column != null) {
        oprot.writeFieldBegin(COLUMN_FIELD_DESC);
        oprot.writeBinary(this.column);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(NUM_VERSIONS_FIELD_DESC);
      oprot.writeI32(this.numVersions);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getVer_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("column:");
      if (this.column == null) {
        sb.append("null");
      } else {
        sb.append(this.column);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("numVersions:");
      sb.append(this.numVersions);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getVer_result implements TBase<getVer_result._Fields>, java.io.Serializable, Cloneable, Comparable<getVer_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("getVer_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.LIST, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public List<TCell> success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, TCell.class))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getVer_result.class, metaDataMap);
    }

    public getVer_result() {
    }

    public getVer_result(
      List<TCell> success,
      IOError io)
    {
      this();
      this.success = success;
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getVer_result(getVer_result other) {
      if (other.isSetSuccess()) {
        List<TCell> __this__success = new ArrayList<TCell>();
        for (TCell other_element : other.success) {
          __this__success.add(new TCell(other_element));
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public getVer_result deepCopy() {
      return new getVer_result(this);
    }

    @Deprecated
    public getVer_result clone() {
      return new getVer_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<TCell> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(TCell elem) {
      if (this.success == null) {
        this.success = new ArrayList<TCell>();
      }
      this.success.add(elem);
    }

    public List<TCell> getSuccess() {
      return this.success;
    }

    public getVer_result setSuccess(List<TCell> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public getVer_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<TCell>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getVer_result)
        return this.equals((getVer_result)that);
      return false;
    }

    public boolean equals(getVer_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(getVer_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getVer_result typedOther = (getVer_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.LIST) {
                {
                  TList _list30 = iprot.readListBegin();
                  this.success = new ArrayList<TCell>(_list30.size);
                  for (int _i31 = 0; _i31 < _list30.size; ++_i31)
                  {
                    TCell _elem32;
                    _elem32 = new TCell();
                    _elem32.read(iprot);
                    this.success.add(_elem32);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.success.size()));
          for (TCell _iter33 : this.success)
          {
            _iter33.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getVer_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getVerTs_args implements TBase<getVerTs_args._Fields>, java.io.Serializable, Cloneable, Comparable<getVerTs_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("getVerTs_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField COLUMN_FIELD_DESC = new TField("column", TType.STRING, (short)3);
    private static final TField TIMESTAMP_FIELD_DESC = new TField("timestamp", TType.I64, (short)4);
    private static final TField NUM_VERSIONS_FIELD_DESC = new TField("numVersions", TType.I32, (short)5);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * row key
     */
    public byte[] row;
    /**
     * column name
     */
    public byte[] column;
    /**
     * timestamp
     */
    public long timestamp;
    /**
     * number of versions to retrieve
     */
    public int numVersions;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * row key
       */
      ROW((short)2, "row"),
      /**
       * column name
       */
      COLUMN((short)3, "column"),
      /**
       * timestamp
       */
      TIMESTAMP((short)4, "timestamp"),
      /**
       * number of versions to retrieve
       */
      NUM_VERSIONS((short)5, "numVersions");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __TIMESTAMP_ISSET_ID = 0;
    private static final int __NUMVERSIONS_ISSET_ID = 1;
    private BitSet __isset_bit_vector = new BitSet(2);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMN, new FieldMetaData("column", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.TIMESTAMP, new FieldMetaData("timestamp", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I64)));
      put(_Fields.NUM_VERSIONS, new FieldMetaData("numVersions", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I32)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getVerTs_args.class, metaDataMap);
    }

    public getVerTs_args() {
    }

    public getVerTs_args(
      byte[] tableName,
      byte[] row,
      byte[] column,
      long timestamp,
      int numVersions)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.column = column;
      this.timestamp = timestamp;
      setTimestampIsSet(true);
      this.numVersions = numVersions;
      setNumVersionsIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getVerTs_args(getVerTs_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      if (other.isSetColumn()) {
        this.column = other.column;
      }
      this.timestamp = other.timestamp;
      this.numVersions = other.numVersions;
    }

    public getVerTs_args deepCopy() {
      return new getVerTs_args(this);
    }

    @Deprecated
    public getVerTs_args clone() {
      return new getVerTs_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public getVerTs_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * row key
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * row key
     */
    public getVerTs_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    /**
     * column name
     */
    public byte[] getColumn() {
      return this.column;
    }

    /**
     * column name
     */
    public getVerTs_args setColumn(byte[] column) {
      this.column = column;
      return this;
    }

    public void unsetColumn() {
      this.column = null;
    }

    /** Returns true if field column is set (has been asigned a value) and false otherwise */
    public boolean isSetColumn() {
      return this.column != null;
    }

    public void setColumnIsSet(boolean value) {
      if (!value) {
        this.column = null;
      }
    }

    /**
     * timestamp
     */
    public long getTimestamp() {
      return this.timestamp;
    }

    /**
     * timestamp
     */
    public getVerTs_args setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      setTimestampIsSet(true);
      return this;
    }

    public void unsetTimestamp() {
      __isset_bit_vector.clear(__TIMESTAMP_ISSET_ID);
    }

    /** Returns true if field timestamp is set (has been asigned a value) and false otherwise */
    public boolean isSetTimestamp() {
      return __isset_bit_vector.get(__TIMESTAMP_ISSET_ID);
    }

    public void setTimestampIsSet(boolean value) {
      __isset_bit_vector.set(__TIMESTAMP_ISSET_ID, value);
    }

    /**
     * number of versions to retrieve
     */
    public int getNumVersions() {
      return this.numVersions;
    }

    /**
     * number of versions to retrieve
     */
    public getVerTs_args setNumVersions(int numVersions) {
      this.numVersions = numVersions;
      setNumVersionsIsSet(true);
      return this;
    }

    public void unsetNumVersions() {
      __isset_bit_vector.clear(__NUMVERSIONS_ISSET_ID);
    }

    /** Returns true if field numVersions is set (has been asigned a value) and false otherwise */
    public boolean isSetNumVersions() {
      return __isset_bit_vector.get(__NUMVERSIONS_ISSET_ID);
    }

    public void setNumVersionsIsSet(boolean value) {
      __isset_bit_vector.set(__NUMVERSIONS_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case COLUMN:
        if (value == null) {
          unsetColumn();
        } else {
          setColumn((byte[])value);
        }
        break;

      case TIMESTAMP:
        if (value == null) {
          unsetTimestamp();
        } else {
          setTimestamp((Long)value);
        }
        break;

      case NUM_VERSIONS:
        if (value == null) {
          unsetNumVersions();
        } else {
          setNumVersions((Integer)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case COLUMN:
        return getColumn();

      case TIMESTAMP:
        return new Long(getTimestamp());

      case NUM_VERSIONS:
        return new Integer(getNumVersions());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case COLUMN:
        return isSetColumn();
      case TIMESTAMP:
        return isSetTimestamp();
      case NUM_VERSIONS:
        return isSetNumVersions();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getVerTs_args)
        return this.equals((getVerTs_args)that);
      return false;
    }

    public boolean equals(getVerTs_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_column = true && this.isSetColumn();
      boolean that_present_column = true && that.isSetColumn();
      if (this_present_column || that_present_column) {
        if (!(this_present_column && that_present_column))
          return false;
        if (!java.util.Arrays.equals(this.column, that.column))
          return false;
      }

      boolean this_present_timestamp = true;
      boolean that_present_timestamp = true;
      if (this_present_timestamp || that_present_timestamp) {
        if (!(this_present_timestamp && that_present_timestamp))
          return false;
        if (this.timestamp != that.timestamp)
          return false;
      }

      boolean this_present_numVersions = true;
      boolean that_present_numVersions = true;
      if (this_present_numVersions || that_present_numVersions) {
        if (!(this_present_numVersions && that_present_numVersions))
          return false;
        if (this.numVersions != that.numVersions)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_column = true && (isSetColumn());
      builder.append(present_column);
      if (present_column)
        builder.append(column);

      boolean present_timestamp = true;
      builder.append(present_timestamp);
      if (present_timestamp)
        builder.append(timestamp);

      boolean present_numVersions = true;
      builder.append(present_numVersions);
      if (present_numVersions)
        builder.append(numVersions);

      return builder.toHashCode();
    }

    public int compareTo(getVerTs_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getVerTs_args typedOther = (getVerTs_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumn()).compareTo(isSetColumn());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(column, typedOther.column);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(isSetTimestamp());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(timestamp, typedOther.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetNumVersions()).compareTo(isSetNumVersions());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(numVersions, typedOther.numVersions);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMN:
              if (field.type == TType.STRING) {
                this.column = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case TIMESTAMP:
              if (field.type == TType.I64) {
                this.timestamp = iprot.readI64();
                setTimestampIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case NUM_VERSIONS:
              if (field.type == TType.I32) {
                this.numVersions = iprot.readI32();
                setNumVersionsIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      if (this.column != null) {
        oprot.writeFieldBegin(COLUMN_FIELD_DESC);
        oprot.writeBinary(this.column);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(this.timestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(NUM_VERSIONS_FIELD_DESC);
      oprot.writeI32(this.numVersions);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getVerTs_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("column:");
      if (this.column == null) {
        sb.append("null");
      } else {
        sb.append(this.column);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("timestamp:");
      sb.append(this.timestamp);
      first = false;
      if (!first) sb.append(", ");
      sb.append("numVersions:");
      sb.append(this.numVersions);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getVerTs_result implements TBase<getVerTs_result._Fields>, java.io.Serializable, Cloneable, Comparable<getVerTs_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("getVerTs_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.LIST, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public List<TCell> success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, TCell.class))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getVerTs_result.class, metaDataMap);
    }

    public getVerTs_result() {
    }

    public getVerTs_result(
      List<TCell> success,
      IOError io)
    {
      this();
      this.success = success;
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getVerTs_result(getVerTs_result other) {
      if (other.isSetSuccess()) {
        List<TCell> __this__success = new ArrayList<TCell>();
        for (TCell other_element : other.success) {
          __this__success.add(new TCell(other_element));
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public getVerTs_result deepCopy() {
      return new getVerTs_result(this);
    }

    @Deprecated
    public getVerTs_result clone() {
      return new getVerTs_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<TCell> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(TCell elem) {
      if (this.success == null) {
        this.success = new ArrayList<TCell>();
      }
      this.success.add(elem);
    }

    public List<TCell> getSuccess() {
      return this.success;
    }

    public getVerTs_result setSuccess(List<TCell> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public getVerTs_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<TCell>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getVerTs_result)
        return this.equals((getVerTs_result)that);
      return false;
    }

    public boolean equals(getVerTs_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(getVerTs_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getVerTs_result typedOther = (getVerTs_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.LIST) {
                {
                  TList _list34 = iprot.readListBegin();
                  this.success = new ArrayList<TCell>(_list34.size);
                  for (int _i35 = 0; _i35 < _list34.size; ++_i35)
                  {
                    TCell _elem36;
                    _elem36 = new TCell();
                    _elem36.read(iprot);
                    this.success.add(_elem36);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.success.size()));
          for (TCell _iter37 : this.success)
          {
            _iter37.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getVerTs_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getRow_args implements TBase<getRow_args._Fields>, java.io.Serializable, Cloneable, Comparable<getRow_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("getRow_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * row key
     */
    public byte[] row;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * row key
       */
      ROW((short)2, "row");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getRow_args.class, metaDataMap);
    }

    public getRow_args() {
    }

    public getRow_args(
      byte[] tableName,
      byte[] row)
    {
      this();
      this.tableName = tableName;
      this.row = row;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getRow_args(getRow_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
    }

    public getRow_args deepCopy() {
      return new getRow_args(this);
    }

    @Deprecated
    public getRow_args clone() {
      return new getRow_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public getRow_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * row key
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * row key
     */
    public getRow_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getRow_args)
        return this.equals((getRow_args)that);
      return false;
    }

    public boolean equals(getRow_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      return builder.toHashCode();
    }

    public int compareTo(getRow_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getRow_args typedOther = (getRow_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getRow_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getRow_result implements TBase<getRow_result._Fields>, java.io.Serializable, Cloneable   {
    private static final TStruct STRUCT_DESC = new TStruct("getRow_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.LIST, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public List<TRowResult> success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, TRowResult.class))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getRow_result.class, metaDataMap);
    }

    public getRow_result() {
    }

    public getRow_result(
      List<TRowResult> success,
      IOError io)
    {
      this();
      this.success = success;
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getRow_result(getRow_result other) {
      if (other.isSetSuccess()) {
        List<TRowResult> __this__success = new ArrayList<TRowResult>();
        for (TRowResult other_element : other.success) {
          __this__success.add(new TRowResult(other_element));
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public getRow_result deepCopy() {
      return new getRow_result(this);
    }

    @Deprecated
    public getRow_result clone() {
      return new getRow_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<TRowResult> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(TRowResult elem) {
      if (this.success == null) {
        this.success = new ArrayList<TRowResult>();
      }
      this.success.add(elem);
    }

    public List<TRowResult> getSuccess() {
      return this.success;
    }

    public getRow_result setSuccess(List<TRowResult> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public getRow_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<TRowResult>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getRow_result)
        return this.equals((getRow_result)that);
      return false;
    }

    public boolean equals(getRow_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.LIST) {
                {
                  TList _list38 = iprot.readListBegin();
                  this.success = new ArrayList<TRowResult>(_list38.size);
                  for (int _i39 = 0; _i39 < _list38.size; ++_i39)
                  {
                    TRowResult _elem40;
                    _elem40 = new TRowResult();
                    _elem40.read(iprot);
                    this.success.add(_elem40);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.success.size()));
          for (TRowResult _iter41 : this.success)
          {
            _iter41.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getRow_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getRowWithColumns_args implements TBase<getRowWithColumns_args._Fields>, java.io.Serializable, Cloneable, Comparable<getRowWithColumns_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("getRowWithColumns_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField COLUMNS_FIELD_DESC = new TField("columns", TType.LIST, (short)3);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * row key
     */
    public byte[] row;
    /**
     * List of columns to return, null for all columns
     */
    public List<byte[]> columns;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * row key
       */
      ROW((short)2, "row"),
      /**
       * List of columns to return, null for all columns
       */
      COLUMNS((short)3, "columns");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMNS, new FieldMetaData("columns", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new FieldValueMetaData(TType.STRING))));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getRowWithColumns_args.class, metaDataMap);
    }

    public getRowWithColumns_args() {
    }

    public getRowWithColumns_args(
      byte[] tableName,
      byte[] row,
      List<byte[]> columns)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.columns = columns;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getRowWithColumns_args(getRowWithColumns_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      if (other.isSetColumns()) {
        List<byte[]> __this__columns = new ArrayList<byte[]>();
        for (byte[] other_element : other.columns) {
          __this__columns.add(other_element);
        }
        this.columns = __this__columns;
      }
    }

    public getRowWithColumns_args deepCopy() {
      return new getRowWithColumns_args(this);
    }

    @Deprecated
    public getRowWithColumns_args clone() {
      return new getRowWithColumns_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public getRowWithColumns_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * row key
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * row key
     */
    public getRowWithColumns_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    public int getColumnsSize() {
      return (this.columns == null) ? 0 : this.columns.size();
    }

    public java.util.Iterator<byte[]> getColumnsIterator() {
      return (this.columns == null) ? null : this.columns.iterator();
    }

    public void addToColumns(byte[] elem) {
      if (this.columns == null) {
        this.columns = new ArrayList<byte[]>();
      }
      this.columns.add(elem);
    }

    /**
     * List of columns to return, null for all columns
     */
    public List<byte[]> getColumns() {
      return this.columns;
    }

    /**
     * List of columns to return, null for all columns
     */
    public getRowWithColumns_args setColumns(List<byte[]> columns) {
      this.columns = columns;
      return this;
    }

    public void unsetColumns() {
      this.columns = null;
    }

    /** Returns true if field columns is set (has been asigned a value) and false otherwise */
    public boolean isSetColumns() {
      return this.columns != null;
    }

    public void setColumnsIsSet(boolean value) {
      if (!value) {
        this.columns = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case COLUMNS:
        if (value == null) {
          unsetColumns();
        } else {
          setColumns((List<byte[]>)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case COLUMNS:
        return getColumns();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case COLUMNS:
        return isSetColumns();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getRowWithColumns_args)
        return this.equals((getRowWithColumns_args)that);
      return false;
    }

    public boolean equals(getRowWithColumns_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_columns = true && this.isSetColumns();
      boolean that_present_columns = true && that.isSetColumns();
      if (this_present_columns || that_present_columns) {
        if (!(this_present_columns && that_present_columns))
          return false;
        if (!this.columns.equals(that.columns))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_columns = true && (isSetColumns());
      builder.append(present_columns);
      if (present_columns)
        builder.append(columns);

      return builder.toHashCode();
    }

    public int compareTo(getRowWithColumns_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getRowWithColumns_args typedOther = (getRowWithColumns_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumns()).compareTo(isSetColumns());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(columns, typedOther.columns);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMNS:
              if (field.type == TType.LIST) {
                {
                  TList _list42 = iprot.readListBegin();
                  this.columns = new ArrayList<byte[]>(_list42.size);
                  for (int _i43 = 0; _i43 < _list42.size; ++_i43)
                  {
                    byte[] _elem44;
                    _elem44 = iprot.readBinary();
                    this.columns.add(_elem44);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      if (this.columns != null) {
        oprot.writeFieldBegin(COLUMNS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRING, this.columns.size()));
          for (byte[] _iter45 : this.columns)
          {
            oprot.writeBinary(_iter45);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getRowWithColumns_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("columns:");
      if (this.columns == null) {
        sb.append("null");
      } else {
        sb.append(this.columns);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getRowWithColumns_result implements TBase<getRowWithColumns_result._Fields>, java.io.Serializable, Cloneable   {
    private static final TStruct STRUCT_DESC = new TStruct("getRowWithColumns_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.LIST, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public List<TRowResult> success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, TRowResult.class))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getRowWithColumns_result.class, metaDataMap);
    }

    public getRowWithColumns_result() {
    }

    public getRowWithColumns_result(
      List<TRowResult> success,
      IOError io)
    {
      this();
      this.success = success;
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getRowWithColumns_result(getRowWithColumns_result other) {
      if (other.isSetSuccess()) {
        List<TRowResult> __this__success = new ArrayList<TRowResult>();
        for (TRowResult other_element : other.success) {
          __this__success.add(new TRowResult(other_element));
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public getRowWithColumns_result deepCopy() {
      return new getRowWithColumns_result(this);
    }

    @Deprecated
    public getRowWithColumns_result clone() {
      return new getRowWithColumns_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<TRowResult> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(TRowResult elem) {
      if (this.success == null) {
        this.success = new ArrayList<TRowResult>();
      }
      this.success.add(elem);
    }

    public List<TRowResult> getSuccess() {
      return this.success;
    }

    public getRowWithColumns_result setSuccess(List<TRowResult> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public getRowWithColumns_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<TRowResult>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getRowWithColumns_result)
        return this.equals((getRowWithColumns_result)that);
      return false;
    }

    public boolean equals(getRowWithColumns_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.LIST) {
                {
                  TList _list46 = iprot.readListBegin();
                  this.success = new ArrayList<TRowResult>(_list46.size);
                  for (int _i47 = 0; _i47 < _list46.size; ++_i47)
                  {
                    TRowResult _elem48;
                    _elem48 = new TRowResult();
                    _elem48.read(iprot);
                    this.success.add(_elem48);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.success.size()));
          for (TRowResult _iter49 : this.success)
          {
            _iter49.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getRowWithColumns_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getRowTs_args implements TBase<getRowTs_args._Fields>, java.io.Serializable, Cloneable, Comparable<getRowTs_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("getRowTs_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField TIMESTAMP_FIELD_DESC = new TField("timestamp", TType.I64, (short)3);

    /**
     * name of the table
     */
    public byte[] tableName;
    /**
     * row key
     */
    public byte[] row;
    /**
     * timestamp
     */
    public long timestamp;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of the table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * row key
       */
      ROW((short)2, "row"),
      /**
       * timestamp
       */
      TIMESTAMP((short)3, "timestamp");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __TIMESTAMP_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.TIMESTAMP, new FieldMetaData("timestamp", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I64)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getRowTs_args.class, metaDataMap);
    }

    public getRowTs_args() {
    }

    public getRowTs_args(
      byte[] tableName,
      byte[] row,
      long timestamp)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.timestamp = timestamp;
      setTimestampIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getRowTs_args(getRowTs_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      this.timestamp = other.timestamp;
    }

    public getRowTs_args deepCopy() {
      return new getRowTs_args(this);
    }

    @Deprecated
    public getRowTs_args clone() {
      return new getRowTs_args(this);
    }

    /**
     * name of the table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of the table
     */
    public getRowTs_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * row key
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * row key
     */
    public getRowTs_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    /**
     * timestamp
     */
    public long getTimestamp() {
      return this.timestamp;
    }

    /**
     * timestamp
     */
    public getRowTs_args setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      setTimestampIsSet(true);
      return this;
    }

    public void unsetTimestamp() {
      __isset_bit_vector.clear(__TIMESTAMP_ISSET_ID);
    }

    /** Returns true if field timestamp is set (has been asigned a value) and false otherwise */
    public boolean isSetTimestamp() {
      return __isset_bit_vector.get(__TIMESTAMP_ISSET_ID);
    }

    public void setTimestampIsSet(boolean value) {
      __isset_bit_vector.set(__TIMESTAMP_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case TIMESTAMP:
        if (value == null) {
          unsetTimestamp();
        } else {
          setTimestamp((Long)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case TIMESTAMP:
        return new Long(getTimestamp());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case TIMESTAMP:
        return isSetTimestamp();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getRowTs_args)
        return this.equals((getRowTs_args)that);
      return false;
    }

    public boolean equals(getRowTs_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_timestamp = true;
      boolean that_present_timestamp = true;
      if (this_present_timestamp || that_present_timestamp) {
        if (!(this_present_timestamp && that_present_timestamp))
          return false;
        if (this.timestamp != that.timestamp)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_timestamp = true;
      builder.append(present_timestamp);
      if (present_timestamp)
        builder.append(timestamp);

      return builder.toHashCode();
    }

    public int compareTo(getRowTs_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getRowTs_args typedOther = (getRowTs_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(isSetTimestamp());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(timestamp, typedOther.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case TIMESTAMP:
              if (field.type == TType.I64) {
                this.timestamp = iprot.readI64();
                setTimestampIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(this.timestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getRowTs_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("timestamp:");
      sb.append(this.timestamp);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getRowTs_result implements TBase<getRowTs_result._Fields>, java.io.Serializable, Cloneable   {
    private static final TStruct STRUCT_DESC = new TStruct("getRowTs_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.LIST, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public List<TRowResult> success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, TRowResult.class))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getRowTs_result.class, metaDataMap);
    }

    public getRowTs_result() {
    }

    public getRowTs_result(
      List<TRowResult> success,
      IOError io)
    {
      this();
      this.success = success;
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getRowTs_result(getRowTs_result other) {
      if (other.isSetSuccess()) {
        List<TRowResult> __this__success = new ArrayList<TRowResult>();
        for (TRowResult other_element : other.success) {
          __this__success.add(new TRowResult(other_element));
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public getRowTs_result deepCopy() {
      return new getRowTs_result(this);
    }

    @Deprecated
    public getRowTs_result clone() {
      return new getRowTs_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<TRowResult> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(TRowResult elem) {
      if (this.success == null) {
        this.success = new ArrayList<TRowResult>();
      }
      this.success.add(elem);
    }

    public List<TRowResult> getSuccess() {
      return this.success;
    }

    public getRowTs_result setSuccess(List<TRowResult> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public getRowTs_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<TRowResult>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getRowTs_result)
        return this.equals((getRowTs_result)that);
      return false;
    }

    public boolean equals(getRowTs_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.LIST) {
                {
                  TList _list50 = iprot.readListBegin();
                  this.success = new ArrayList<TRowResult>(_list50.size);
                  for (int _i51 = 0; _i51 < _list50.size; ++_i51)
                  {
                    TRowResult _elem52;
                    _elem52 = new TRowResult();
                    _elem52.read(iprot);
                    this.success.add(_elem52);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.success.size()));
          for (TRowResult _iter53 : this.success)
          {
            _iter53.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getRowTs_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getRowWithColumnsTs_args implements TBase<getRowWithColumnsTs_args._Fields>, java.io.Serializable, Cloneable, Comparable<getRowWithColumnsTs_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("getRowWithColumnsTs_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField COLUMNS_FIELD_DESC = new TField("columns", TType.LIST, (short)3);
    private static final TField TIMESTAMP_FIELD_DESC = new TField("timestamp", TType.I64, (short)4);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * row key
     */
    public byte[] row;
    /**
     * List of columns to return, null for all columns
     */
    public List<byte[]> columns;
    public long timestamp;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * row key
       */
      ROW((short)2, "row"),
      /**
       * List of columns to return, null for all columns
       */
      COLUMNS((short)3, "columns"),
      TIMESTAMP((short)4, "timestamp");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __TIMESTAMP_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMNS, new FieldMetaData("columns", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new FieldValueMetaData(TType.STRING))));
      put(_Fields.TIMESTAMP, new FieldMetaData("timestamp", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I64)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getRowWithColumnsTs_args.class, metaDataMap);
    }

    public getRowWithColumnsTs_args() {
    }

    public getRowWithColumnsTs_args(
      byte[] tableName,
      byte[] row,
      List<byte[]> columns,
      long timestamp)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.columns = columns;
      this.timestamp = timestamp;
      setTimestampIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getRowWithColumnsTs_args(getRowWithColumnsTs_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      if (other.isSetColumns()) {
        List<byte[]> __this__columns = new ArrayList<byte[]>();
        for (byte[] other_element : other.columns) {
          __this__columns.add(other_element);
        }
        this.columns = __this__columns;
      }
      this.timestamp = other.timestamp;
    }

    public getRowWithColumnsTs_args deepCopy() {
      return new getRowWithColumnsTs_args(this);
    }

    @Deprecated
    public getRowWithColumnsTs_args clone() {
      return new getRowWithColumnsTs_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public getRowWithColumnsTs_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * row key
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * row key
     */
    public getRowWithColumnsTs_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    public int getColumnsSize() {
      return (this.columns == null) ? 0 : this.columns.size();
    }

    public java.util.Iterator<byte[]> getColumnsIterator() {
      return (this.columns == null) ? null : this.columns.iterator();
    }

    public void addToColumns(byte[] elem) {
      if (this.columns == null) {
        this.columns = new ArrayList<byte[]>();
      }
      this.columns.add(elem);
    }

    /**
     * List of columns to return, null for all columns
     */
    public List<byte[]> getColumns() {
      return this.columns;
    }

    /**
     * List of columns to return, null for all columns
     */
    public getRowWithColumnsTs_args setColumns(List<byte[]> columns) {
      this.columns = columns;
      return this;
    }

    public void unsetColumns() {
      this.columns = null;
    }

    /** Returns true if field columns is set (has been asigned a value) and false otherwise */
    public boolean isSetColumns() {
      return this.columns != null;
    }

    public void setColumnsIsSet(boolean value) {
      if (!value) {
        this.columns = null;
      }
    }

    public long getTimestamp() {
      return this.timestamp;
    }

    public getRowWithColumnsTs_args setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      setTimestampIsSet(true);
      return this;
    }

    public void unsetTimestamp() {
      __isset_bit_vector.clear(__TIMESTAMP_ISSET_ID);
    }

    /** Returns true if field timestamp is set (has been asigned a value) and false otherwise */
    public boolean isSetTimestamp() {
      return __isset_bit_vector.get(__TIMESTAMP_ISSET_ID);
    }

    public void setTimestampIsSet(boolean value) {
      __isset_bit_vector.set(__TIMESTAMP_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case COLUMNS:
        if (value == null) {
          unsetColumns();
        } else {
          setColumns((List<byte[]>)value);
        }
        break;

      case TIMESTAMP:
        if (value == null) {
          unsetTimestamp();
        } else {
          setTimestamp((Long)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case COLUMNS:
        return getColumns();

      case TIMESTAMP:
        return new Long(getTimestamp());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case COLUMNS:
        return isSetColumns();
      case TIMESTAMP:
        return isSetTimestamp();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getRowWithColumnsTs_args)
        return this.equals((getRowWithColumnsTs_args)that);
      return false;
    }

    public boolean equals(getRowWithColumnsTs_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_columns = true && this.isSetColumns();
      boolean that_present_columns = true && that.isSetColumns();
      if (this_present_columns || that_present_columns) {
        if (!(this_present_columns && that_present_columns))
          return false;
        if (!this.columns.equals(that.columns))
          return false;
      }

      boolean this_present_timestamp = true;
      boolean that_present_timestamp = true;
      if (this_present_timestamp || that_present_timestamp) {
        if (!(this_present_timestamp && that_present_timestamp))
          return false;
        if (this.timestamp != that.timestamp)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_columns = true && (isSetColumns());
      builder.append(present_columns);
      if (present_columns)
        builder.append(columns);

      boolean present_timestamp = true;
      builder.append(present_timestamp);
      if (present_timestamp)
        builder.append(timestamp);

      return builder.toHashCode();
    }

    public int compareTo(getRowWithColumnsTs_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getRowWithColumnsTs_args typedOther = (getRowWithColumnsTs_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumns()).compareTo(isSetColumns());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(columns, typedOther.columns);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(isSetTimestamp());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(timestamp, typedOther.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMNS:
              if (field.type == TType.LIST) {
                {
                  TList _list54 = iprot.readListBegin();
                  this.columns = new ArrayList<byte[]>(_list54.size);
                  for (int _i55 = 0; _i55 < _list54.size; ++_i55)
                  {
                    byte[] _elem56;
                    _elem56 = iprot.readBinary();
                    this.columns.add(_elem56);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case TIMESTAMP:
              if (field.type == TType.I64) {
                this.timestamp = iprot.readI64();
                setTimestampIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      if (this.columns != null) {
        oprot.writeFieldBegin(COLUMNS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRING, this.columns.size()));
          for (byte[] _iter57 : this.columns)
          {
            oprot.writeBinary(_iter57);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(this.timestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getRowWithColumnsTs_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("columns:");
      if (this.columns == null) {
        sb.append("null");
      } else {
        sb.append(this.columns);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("timestamp:");
      sb.append(this.timestamp);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class getRowWithColumnsTs_result implements TBase<getRowWithColumnsTs_result._Fields>, java.io.Serializable, Cloneable   {
    private static final TStruct STRUCT_DESC = new TStruct("getRowWithColumnsTs_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.LIST, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public List<TRowResult> success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, TRowResult.class))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(getRowWithColumnsTs_result.class, metaDataMap);
    }

    public getRowWithColumnsTs_result() {
    }

    public getRowWithColumnsTs_result(
      List<TRowResult> success,
      IOError io)
    {
      this();
      this.success = success;
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getRowWithColumnsTs_result(getRowWithColumnsTs_result other) {
      if (other.isSetSuccess()) {
        List<TRowResult> __this__success = new ArrayList<TRowResult>();
        for (TRowResult other_element : other.success) {
          __this__success.add(new TRowResult(other_element));
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public getRowWithColumnsTs_result deepCopy() {
      return new getRowWithColumnsTs_result(this);
    }

    @Deprecated
    public getRowWithColumnsTs_result clone() {
      return new getRowWithColumnsTs_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<TRowResult> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(TRowResult elem) {
      if (this.success == null) {
        this.success = new ArrayList<TRowResult>();
      }
      this.success.add(elem);
    }

    public List<TRowResult> getSuccess() {
      return this.success;
    }

    public getRowWithColumnsTs_result setSuccess(List<TRowResult> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public getRowWithColumnsTs_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<TRowResult>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getRowWithColumnsTs_result)
        return this.equals((getRowWithColumnsTs_result)that);
      return false;
    }

    public boolean equals(getRowWithColumnsTs_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.LIST) {
                {
                  TList _list58 = iprot.readListBegin();
                  this.success = new ArrayList<TRowResult>(_list58.size);
                  for (int _i59 = 0; _i59 < _list58.size; ++_i59)
                  {
                    TRowResult _elem60;
                    _elem60 = new TRowResult();
                    _elem60.read(iprot);
                    this.success.add(_elem60);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.success.size()));
          for (TRowResult _iter61 : this.success)
          {
            _iter61.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getRowWithColumnsTs_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class mutateRow_args implements TBase<mutateRow_args._Fields>, java.io.Serializable, Cloneable, Comparable<mutateRow_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("mutateRow_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField MUTATIONS_FIELD_DESC = new TField("mutations", TType.LIST, (short)3);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * row key
     */
    public byte[] row;
    /**
     * list of mutation commands
     */
    public List<Mutation> mutations;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * row key
       */
      ROW((short)2, "row"),
      /**
       * list of mutation commands
       */
      MUTATIONS((short)3, "mutations");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.MUTATIONS, new FieldMetaData("mutations", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, Mutation.class))));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(mutateRow_args.class, metaDataMap);
    }

    public mutateRow_args() {
    }

    public mutateRow_args(
      byte[] tableName,
      byte[] row,
      List<Mutation> mutations)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.mutations = mutations;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public mutateRow_args(mutateRow_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      if (other.isSetMutations()) {
        List<Mutation> __this__mutations = new ArrayList<Mutation>();
        for (Mutation other_element : other.mutations) {
          __this__mutations.add(new Mutation(other_element));
        }
        this.mutations = __this__mutations;
      }
    }

    public mutateRow_args deepCopy() {
      return new mutateRow_args(this);
    }

    @Deprecated
    public mutateRow_args clone() {
      return new mutateRow_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public mutateRow_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * row key
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * row key
     */
    public mutateRow_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    public int getMutationsSize() {
      return (this.mutations == null) ? 0 : this.mutations.size();
    }

    public java.util.Iterator<Mutation> getMutationsIterator() {
      return (this.mutations == null) ? null : this.mutations.iterator();
    }

    public void addToMutations(Mutation elem) {
      if (this.mutations == null) {
        this.mutations = new ArrayList<Mutation>();
      }
      this.mutations.add(elem);
    }

    /**
     * list of mutation commands
     */
    public List<Mutation> getMutations() {
      return this.mutations;
    }

    /**
     * list of mutation commands
     */
    public mutateRow_args setMutations(List<Mutation> mutations) {
      this.mutations = mutations;
      return this;
    }

    public void unsetMutations() {
      this.mutations = null;
    }

    /** Returns true if field mutations is set (has been asigned a value) and false otherwise */
    public boolean isSetMutations() {
      return this.mutations != null;
    }

    public void setMutationsIsSet(boolean value) {
      if (!value) {
        this.mutations = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case MUTATIONS:
        if (value == null) {
          unsetMutations();
        } else {
          setMutations((List<Mutation>)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case MUTATIONS:
        return getMutations();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case MUTATIONS:
        return isSetMutations();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof mutateRow_args)
        return this.equals((mutateRow_args)that);
      return false;
    }

    public boolean equals(mutateRow_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_mutations = true && this.isSetMutations();
      boolean that_present_mutations = true && that.isSetMutations();
      if (this_present_mutations || that_present_mutations) {
        if (!(this_present_mutations && that_present_mutations))
          return false;
        if (!this.mutations.equals(that.mutations))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_mutations = true && (isSetMutations());
      builder.append(present_mutations);
      if (present_mutations)
        builder.append(mutations);

      return builder.toHashCode();
    }

    public int compareTo(mutateRow_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      mutateRow_args typedOther = (mutateRow_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetMutations()).compareTo(isSetMutations());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(mutations, typedOther.mutations);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case MUTATIONS:
              if (field.type == TType.LIST) {
                {
                  TList _list62 = iprot.readListBegin();
                  this.mutations = new ArrayList<Mutation>(_list62.size);
                  for (int _i63 = 0; _i63 < _list62.size; ++_i63)
                  {
                    Mutation _elem64;
                    _elem64 = new Mutation();
                    _elem64.read(iprot);
                    this.mutations.add(_elem64);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      if (this.mutations != null) {
        oprot.writeFieldBegin(MUTATIONS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.mutations.size()));
          for (Mutation _iter65 : this.mutations)
          {
            _iter65.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("mutateRow_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("mutations:");
      if (this.mutations == null) {
        sb.append("null");
      } else {
        sb.append(this.mutations);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class mutateRow_result implements TBase<mutateRow_result._Fields>, java.io.Serializable, Cloneable, Comparable<mutateRow_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("mutateRow_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);
    private static final TField IA_FIELD_DESC = new TField("ia", TType.STRUCT, (short)2);

    public IOError io;
    public IllegalArgument ia;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io"),
      IA((short)2, "ia");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
      put(_Fields.IA, new FieldMetaData("ia", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(mutateRow_result.class, metaDataMap);
    }

    public mutateRow_result() {
    }

    public mutateRow_result(
      IOError io,
      IllegalArgument ia)
    {
      this();
      this.io = io;
      this.ia = ia;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public mutateRow_result(mutateRow_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
      if (other.isSetIa()) {
        this.ia = new IllegalArgument(other.ia);
      }
    }

    public mutateRow_result deepCopy() {
      return new mutateRow_result(this);
    }

    @Deprecated
    public mutateRow_result clone() {
      return new mutateRow_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public mutateRow_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public IllegalArgument getIa() {
      return this.ia;
    }

    public mutateRow_result setIa(IllegalArgument ia) {
      this.ia = ia;
      return this;
    }

    public void unsetIa() {
      this.ia = null;
    }

    /** Returns true if field ia is set (has been asigned a value) and false otherwise */
    public boolean isSetIa() {
      return this.ia != null;
    }

    public void setIaIsSet(boolean value) {
      if (!value) {
        this.ia = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      case IA:
        if (value == null) {
          unsetIa();
        } else {
          setIa((IllegalArgument)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      case IA:
        return getIa();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      case IA:
        return isSetIa();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof mutateRow_result)
        return this.equals((mutateRow_result)that);
      return false;
    }

    public boolean equals(mutateRow_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      boolean this_present_ia = true && this.isSetIa();
      boolean that_present_ia = true && that.isSetIa();
      if (this_present_ia || that_present_ia) {
        if (!(this_present_ia && that_present_ia))
          return false;
        if (!this.ia.equals(that.ia))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      boolean present_ia = true && (isSetIa());
      builder.append(present_ia);
      if (present_ia)
        builder.append(ia);

      return builder.toHashCode();
    }

    public int compareTo(mutateRow_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      mutateRow_result typedOther = (mutateRow_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIa()).compareTo(isSetIa());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(ia, typedOther.ia);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IA:
              if (field.type == TType.STRUCT) {
                this.ia = new IllegalArgument();
                this.ia.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      } else if (this.isSetIa()) {
        oprot.writeFieldBegin(IA_FIELD_DESC);
        this.ia.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("mutateRow_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ia:");
      if (this.ia == null) {
        sb.append("null");
      } else {
        sb.append(this.ia);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class mutateRowTs_args implements TBase<mutateRowTs_args._Fields>, java.io.Serializable, Cloneable, Comparable<mutateRowTs_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("mutateRowTs_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField MUTATIONS_FIELD_DESC = new TField("mutations", TType.LIST, (short)3);
    private static final TField TIMESTAMP_FIELD_DESC = new TField("timestamp", TType.I64, (short)4);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * row key
     */
    public byte[] row;
    /**
     * list of mutation commands
     */
    public List<Mutation> mutations;
    /**
     * timestamp
     */
    public long timestamp;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * row key
       */
      ROW((short)2, "row"),
      /**
       * list of mutation commands
       */
      MUTATIONS((short)3, "mutations"),
      /**
       * timestamp
       */
      TIMESTAMP((short)4, "timestamp");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __TIMESTAMP_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.MUTATIONS, new FieldMetaData("mutations", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, Mutation.class))));
      put(_Fields.TIMESTAMP, new FieldMetaData("timestamp", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I64)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(mutateRowTs_args.class, metaDataMap);
    }

    public mutateRowTs_args() {
    }

    public mutateRowTs_args(
      byte[] tableName,
      byte[] row,
      List<Mutation> mutations,
      long timestamp)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.mutations = mutations;
      this.timestamp = timestamp;
      setTimestampIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public mutateRowTs_args(mutateRowTs_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      if (other.isSetMutations()) {
        List<Mutation> __this__mutations = new ArrayList<Mutation>();
        for (Mutation other_element : other.mutations) {
          __this__mutations.add(new Mutation(other_element));
        }
        this.mutations = __this__mutations;
      }
      this.timestamp = other.timestamp;
    }

    public mutateRowTs_args deepCopy() {
      return new mutateRowTs_args(this);
    }

    @Deprecated
    public mutateRowTs_args clone() {
      return new mutateRowTs_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public mutateRowTs_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * row key
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * row key
     */
    public mutateRowTs_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    public int getMutationsSize() {
      return (this.mutations == null) ? 0 : this.mutations.size();
    }

    public java.util.Iterator<Mutation> getMutationsIterator() {
      return (this.mutations == null) ? null : this.mutations.iterator();
    }

    public void addToMutations(Mutation elem) {
      if (this.mutations == null) {
        this.mutations = new ArrayList<Mutation>();
      }
      this.mutations.add(elem);
    }

    /**
     * list of mutation commands
     */
    public List<Mutation> getMutations() {
      return this.mutations;
    }

    /**
     * list of mutation commands
     */
    public mutateRowTs_args setMutations(List<Mutation> mutations) {
      this.mutations = mutations;
      return this;
    }

    public void unsetMutations() {
      this.mutations = null;
    }

    /** Returns true if field mutations is set (has been asigned a value) and false otherwise */
    public boolean isSetMutations() {
      return this.mutations != null;
    }

    public void setMutationsIsSet(boolean value) {
      if (!value) {
        this.mutations = null;
      }
    }

    /**
     * timestamp
     */
    public long getTimestamp() {
      return this.timestamp;
    }

    /**
     * timestamp
     */
    public mutateRowTs_args setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      setTimestampIsSet(true);
      return this;
    }

    public void unsetTimestamp() {
      __isset_bit_vector.clear(__TIMESTAMP_ISSET_ID);
    }

    /** Returns true if field timestamp is set (has been asigned a value) and false otherwise */
    public boolean isSetTimestamp() {
      return __isset_bit_vector.get(__TIMESTAMP_ISSET_ID);
    }

    public void setTimestampIsSet(boolean value) {
      __isset_bit_vector.set(__TIMESTAMP_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case MUTATIONS:
        if (value == null) {
          unsetMutations();
        } else {
          setMutations((List<Mutation>)value);
        }
        break;

      case TIMESTAMP:
        if (value == null) {
          unsetTimestamp();
        } else {
          setTimestamp((Long)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case MUTATIONS:
        return getMutations();

      case TIMESTAMP:
        return new Long(getTimestamp());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case MUTATIONS:
        return isSetMutations();
      case TIMESTAMP:
        return isSetTimestamp();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof mutateRowTs_args)
        return this.equals((mutateRowTs_args)that);
      return false;
    }

    public boolean equals(mutateRowTs_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_mutations = true && this.isSetMutations();
      boolean that_present_mutations = true && that.isSetMutations();
      if (this_present_mutations || that_present_mutations) {
        if (!(this_present_mutations && that_present_mutations))
          return false;
        if (!this.mutations.equals(that.mutations))
          return false;
      }

      boolean this_present_timestamp = true;
      boolean that_present_timestamp = true;
      if (this_present_timestamp || that_present_timestamp) {
        if (!(this_present_timestamp && that_present_timestamp))
          return false;
        if (this.timestamp != that.timestamp)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_mutations = true && (isSetMutations());
      builder.append(present_mutations);
      if (present_mutations)
        builder.append(mutations);

      boolean present_timestamp = true;
      builder.append(present_timestamp);
      if (present_timestamp)
        builder.append(timestamp);

      return builder.toHashCode();
    }

    public int compareTo(mutateRowTs_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      mutateRowTs_args typedOther = (mutateRowTs_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetMutations()).compareTo(isSetMutations());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(mutations, typedOther.mutations);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(isSetTimestamp());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(timestamp, typedOther.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case MUTATIONS:
              if (field.type == TType.LIST) {
                {
                  TList _list66 = iprot.readListBegin();
                  this.mutations = new ArrayList<Mutation>(_list66.size);
                  for (int _i67 = 0; _i67 < _list66.size; ++_i67)
                  {
                    Mutation _elem68;
                    _elem68 = new Mutation();
                    _elem68.read(iprot);
                    this.mutations.add(_elem68);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case TIMESTAMP:
              if (field.type == TType.I64) {
                this.timestamp = iprot.readI64();
                setTimestampIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      if (this.mutations != null) {
        oprot.writeFieldBegin(MUTATIONS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.mutations.size()));
          for (Mutation _iter69 : this.mutations)
          {
            _iter69.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(this.timestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("mutateRowTs_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("mutations:");
      if (this.mutations == null) {
        sb.append("null");
      } else {
        sb.append(this.mutations);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("timestamp:");
      sb.append(this.timestamp);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class mutateRowTs_result implements TBase<mutateRowTs_result._Fields>, java.io.Serializable, Cloneable, Comparable<mutateRowTs_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("mutateRowTs_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);
    private static final TField IA_FIELD_DESC = new TField("ia", TType.STRUCT, (short)2);

    public IOError io;
    public IllegalArgument ia;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io"),
      IA((short)2, "ia");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
      put(_Fields.IA, new FieldMetaData("ia", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(mutateRowTs_result.class, metaDataMap);
    }

    public mutateRowTs_result() {
    }

    public mutateRowTs_result(
      IOError io,
      IllegalArgument ia)
    {
      this();
      this.io = io;
      this.ia = ia;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public mutateRowTs_result(mutateRowTs_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
      if (other.isSetIa()) {
        this.ia = new IllegalArgument(other.ia);
      }
    }

    public mutateRowTs_result deepCopy() {
      return new mutateRowTs_result(this);
    }

    @Deprecated
    public mutateRowTs_result clone() {
      return new mutateRowTs_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public mutateRowTs_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public IllegalArgument getIa() {
      return this.ia;
    }

    public mutateRowTs_result setIa(IllegalArgument ia) {
      this.ia = ia;
      return this;
    }

    public void unsetIa() {
      this.ia = null;
    }

    /** Returns true if field ia is set (has been asigned a value) and false otherwise */
    public boolean isSetIa() {
      return this.ia != null;
    }

    public void setIaIsSet(boolean value) {
      if (!value) {
        this.ia = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      case IA:
        if (value == null) {
          unsetIa();
        } else {
          setIa((IllegalArgument)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      case IA:
        return getIa();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      case IA:
        return isSetIa();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof mutateRowTs_result)
        return this.equals((mutateRowTs_result)that);
      return false;
    }

    public boolean equals(mutateRowTs_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      boolean this_present_ia = true && this.isSetIa();
      boolean that_present_ia = true && that.isSetIa();
      if (this_present_ia || that_present_ia) {
        if (!(this_present_ia && that_present_ia))
          return false;
        if (!this.ia.equals(that.ia))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      boolean present_ia = true && (isSetIa());
      builder.append(present_ia);
      if (present_ia)
        builder.append(ia);

      return builder.toHashCode();
    }

    public int compareTo(mutateRowTs_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      mutateRowTs_result typedOther = (mutateRowTs_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIa()).compareTo(isSetIa());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(ia, typedOther.ia);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IA:
              if (field.type == TType.STRUCT) {
                this.ia = new IllegalArgument();
                this.ia.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      } else if (this.isSetIa()) {
        oprot.writeFieldBegin(IA_FIELD_DESC);
        this.ia.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("mutateRowTs_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ia:");
      if (this.ia == null) {
        sb.append("null");
      } else {
        sb.append(this.ia);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class mutateRows_args implements TBase<mutateRows_args._Fields>, java.io.Serializable, Cloneable, Comparable<mutateRows_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("mutateRows_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_BATCHES_FIELD_DESC = new TField("rowBatches", TType.LIST, (short)2);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * list of row batches
     */
    public List<BatchMutation> rowBatches;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * list of row batches
       */
      ROW_BATCHES((short)2, "rowBatches");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW_BATCHES, new FieldMetaData("rowBatches", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, BatchMutation.class))));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(mutateRows_args.class, metaDataMap);
    }

    public mutateRows_args() {
    }

    public mutateRows_args(
      byte[] tableName,
      List<BatchMutation> rowBatches)
    {
      this();
      this.tableName = tableName;
      this.rowBatches = rowBatches;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public mutateRows_args(mutateRows_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRowBatches()) {
        List<BatchMutation> __this__rowBatches = new ArrayList<BatchMutation>();
        for (BatchMutation other_element : other.rowBatches) {
          __this__rowBatches.add(new BatchMutation(other_element));
        }
        this.rowBatches = __this__rowBatches;
      }
    }

    public mutateRows_args deepCopy() {
      return new mutateRows_args(this);
    }

    @Deprecated
    public mutateRows_args clone() {
      return new mutateRows_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public mutateRows_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    public int getRowBatchesSize() {
      return (this.rowBatches == null) ? 0 : this.rowBatches.size();
    }

    public java.util.Iterator<BatchMutation> getRowBatchesIterator() {
      return (this.rowBatches == null) ? null : this.rowBatches.iterator();
    }

    public void addToRowBatches(BatchMutation elem) {
      if (this.rowBatches == null) {
        this.rowBatches = new ArrayList<BatchMutation>();
      }
      this.rowBatches.add(elem);
    }

    /**
     * list of row batches
     */
    public List<BatchMutation> getRowBatches() {
      return this.rowBatches;
    }

    /**
     * list of row batches
     */
    public mutateRows_args setRowBatches(List<BatchMutation> rowBatches) {
      this.rowBatches = rowBatches;
      return this;
    }

    public void unsetRowBatches() {
      this.rowBatches = null;
    }

    /** Returns true if field rowBatches is set (has been asigned a value) and false otherwise */
    public boolean isSetRowBatches() {
      return this.rowBatches != null;
    }

    public void setRowBatchesIsSet(boolean value) {
      if (!value) {
        this.rowBatches = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW_BATCHES:
        if (value == null) {
          unsetRowBatches();
        } else {
          setRowBatches((List<BatchMutation>)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW_BATCHES:
        return getRowBatches();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW_BATCHES:
        return isSetRowBatches();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof mutateRows_args)
        return this.equals((mutateRows_args)that);
      return false;
    }

    public boolean equals(mutateRows_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_rowBatches = true && this.isSetRowBatches();
      boolean that_present_rowBatches = true && that.isSetRowBatches();
      if (this_present_rowBatches || that_present_rowBatches) {
        if (!(this_present_rowBatches && that_present_rowBatches))
          return false;
        if (!this.rowBatches.equals(that.rowBatches))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_rowBatches = true && (isSetRowBatches());
      builder.append(present_rowBatches);
      if (present_rowBatches)
        builder.append(rowBatches);

      return builder.toHashCode();
    }

    public int compareTo(mutateRows_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      mutateRows_args typedOther = (mutateRows_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRowBatches()).compareTo(isSetRowBatches());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(rowBatches, typedOther.rowBatches);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW_BATCHES:
              if (field.type == TType.LIST) {
                {
                  TList _list70 = iprot.readListBegin();
                  this.rowBatches = new ArrayList<BatchMutation>(_list70.size);
                  for (int _i71 = 0; _i71 < _list70.size; ++_i71)
                  {
                    BatchMutation _elem72;
                    _elem72 = new BatchMutation();
                    _elem72.read(iprot);
                    this.rowBatches.add(_elem72);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.rowBatches != null) {
        oprot.writeFieldBegin(ROW_BATCHES_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.rowBatches.size()));
          for (BatchMutation _iter73 : this.rowBatches)
          {
            _iter73.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("mutateRows_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("rowBatches:");
      if (this.rowBatches == null) {
        sb.append("null");
      } else {
        sb.append(this.rowBatches);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class mutateRows_result implements TBase<mutateRows_result._Fields>, java.io.Serializable, Cloneable, Comparable<mutateRows_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("mutateRows_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);
    private static final TField IA_FIELD_DESC = new TField("ia", TType.STRUCT, (short)2);

    public IOError io;
    public IllegalArgument ia;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io"),
      IA((short)2, "ia");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
      put(_Fields.IA, new FieldMetaData("ia", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(mutateRows_result.class, metaDataMap);
    }

    public mutateRows_result() {
    }

    public mutateRows_result(
      IOError io,
      IllegalArgument ia)
    {
      this();
      this.io = io;
      this.ia = ia;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public mutateRows_result(mutateRows_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
      if (other.isSetIa()) {
        this.ia = new IllegalArgument(other.ia);
      }
    }

    public mutateRows_result deepCopy() {
      return new mutateRows_result(this);
    }

    @Deprecated
    public mutateRows_result clone() {
      return new mutateRows_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public mutateRows_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public IllegalArgument getIa() {
      return this.ia;
    }

    public mutateRows_result setIa(IllegalArgument ia) {
      this.ia = ia;
      return this;
    }

    public void unsetIa() {
      this.ia = null;
    }

    /** Returns true if field ia is set (has been asigned a value) and false otherwise */
    public boolean isSetIa() {
      return this.ia != null;
    }

    public void setIaIsSet(boolean value) {
      if (!value) {
        this.ia = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      case IA:
        if (value == null) {
          unsetIa();
        } else {
          setIa((IllegalArgument)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      case IA:
        return getIa();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      case IA:
        return isSetIa();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof mutateRows_result)
        return this.equals((mutateRows_result)that);
      return false;
    }

    public boolean equals(mutateRows_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      boolean this_present_ia = true && this.isSetIa();
      boolean that_present_ia = true && that.isSetIa();
      if (this_present_ia || that_present_ia) {
        if (!(this_present_ia && that_present_ia))
          return false;
        if (!this.ia.equals(that.ia))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      boolean present_ia = true && (isSetIa());
      builder.append(present_ia);
      if (present_ia)
        builder.append(ia);

      return builder.toHashCode();
    }

    public int compareTo(mutateRows_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      mutateRows_result typedOther = (mutateRows_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIa()).compareTo(isSetIa());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(ia, typedOther.ia);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IA:
              if (field.type == TType.STRUCT) {
                this.ia = new IllegalArgument();
                this.ia.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      } else if (this.isSetIa()) {
        oprot.writeFieldBegin(IA_FIELD_DESC);
        this.ia.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("mutateRows_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ia:");
      if (this.ia == null) {
        sb.append("null");
      } else {
        sb.append(this.ia);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class mutateRowsTs_args implements TBase<mutateRowsTs_args._Fields>, java.io.Serializable, Cloneable, Comparable<mutateRowsTs_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("mutateRowsTs_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_BATCHES_FIELD_DESC = new TField("rowBatches", TType.LIST, (short)2);
    private static final TField TIMESTAMP_FIELD_DESC = new TField("timestamp", TType.I64, (short)3);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * list of row batches
     */
    public List<BatchMutation> rowBatches;
    /**
     * timestamp
     */
    public long timestamp;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * list of row batches
       */
      ROW_BATCHES((short)2, "rowBatches"),
      /**
       * timestamp
       */
      TIMESTAMP((short)3, "timestamp");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __TIMESTAMP_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW_BATCHES, new FieldMetaData("rowBatches", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, BatchMutation.class))));
      put(_Fields.TIMESTAMP, new FieldMetaData("timestamp", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I64)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(mutateRowsTs_args.class, metaDataMap);
    }

    public mutateRowsTs_args() {
    }

    public mutateRowsTs_args(
      byte[] tableName,
      List<BatchMutation> rowBatches,
      long timestamp)
    {
      this();
      this.tableName = tableName;
      this.rowBatches = rowBatches;
      this.timestamp = timestamp;
      setTimestampIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public mutateRowsTs_args(mutateRowsTs_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRowBatches()) {
        List<BatchMutation> __this__rowBatches = new ArrayList<BatchMutation>();
        for (BatchMutation other_element : other.rowBatches) {
          __this__rowBatches.add(new BatchMutation(other_element));
        }
        this.rowBatches = __this__rowBatches;
      }
      this.timestamp = other.timestamp;
    }

    public mutateRowsTs_args deepCopy() {
      return new mutateRowsTs_args(this);
    }

    @Deprecated
    public mutateRowsTs_args clone() {
      return new mutateRowsTs_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public mutateRowsTs_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    public int getRowBatchesSize() {
      return (this.rowBatches == null) ? 0 : this.rowBatches.size();
    }

    public java.util.Iterator<BatchMutation> getRowBatchesIterator() {
      return (this.rowBatches == null) ? null : this.rowBatches.iterator();
    }

    public void addToRowBatches(BatchMutation elem) {
      if (this.rowBatches == null) {
        this.rowBatches = new ArrayList<BatchMutation>();
      }
      this.rowBatches.add(elem);
    }

    /**
     * list of row batches
     */
    public List<BatchMutation> getRowBatches() {
      return this.rowBatches;
    }

    /**
     * list of row batches
     */
    public mutateRowsTs_args setRowBatches(List<BatchMutation> rowBatches) {
      this.rowBatches = rowBatches;
      return this;
    }

    public void unsetRowBatches() {
      this.rowBatches = null;
    }

    /** Returns true if field rowBatches is set (has been asigned a value) and false otherwise */
    public boolean isSetRowBatches() {
      return this.rowBatches != null;
    }

    public void setRowBatchesIsSet(boolean value) {
      if (!value) {
        this.rowBatches = null;
      }
    }

    /**
     * timestamp
     */
    public long getTimestamp() {
      return this.timestamp;
    }

    /**
     * timestamp
     */
    public mutateRowsTs_args setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      setTimestampIsSet(true);
      return this;
    }

    public void unsetTimestamp() {
      __isset_bit_vector.clear(__TIMESTAMP_ISSET_ID);
    }

    /** Returns true if field timestamp is set (has been asigned a value) and false otherwise */
    public boolean isSetTimestamp() {
      return __isset_bit_vector.get(__TIMESTAMP_ISSET_ID);
    }

    public void setTimestampIsSet(boolean value) {
      __isset_bit_vector.set(__TIMESTAMP_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW_BATCHES:
        if (value == null) {
          unsetRowBatches();
        } else {
          setRowBatches((List<BatchMutation>)value);
        }
        break;

      case TIMESTAMP:
        if (value == null) {
          unsetTimestamp();
        } else {
          setTimestamp((Long)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW_BATCHES:
        return getRowBatches();

      case TIMESTAMP:
        return new Long(getTimestamp());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW_BATCHES:
        return isSetRowBatches();
      case TIMESTAMP:
        return isSetTimestamp();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof mutateRowsTs_args)
        return this.equals((mutateRowsTs_args)that);
      return false;
    }

    public boolean equals(mutateRowsTs_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_rowBatches = true && this.isSetRowBatches();
      boolean that_present_rowBatches = true && that.isSetRowBatches();
      if (this_present_rowBatches || that_present_rowBatches) {
        if (!(this_present_rowBatches && that_present_rowBatches))
          return false;
        if (!this.rowBatches.equals(that.rowBatches))
          return false;
      }

      boolean this_present_timestamp = true;
      boolean that_present_timestamp = true;
      if (this_present_timestamp || that_present_timestamp) {
        if (!(this_present_timestamp && that_present_timestamp))
          return false;
        if (this.timestamp != that.timestamp)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_rowBatches = true && (isSetRowBatches());
      builder.append(present_rowBatches);
      if (present_rowBatches)
        builder.append(rowBatches);

      boolean present_timestamp = true;
      builder.append(present_timestamp);
      if (present_timestamp)
        builder.append(timestamp);

      return builder.toHashCode();
    }

    public int compareTo(mutateRowsTs_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      mutateRowsTs_args typedOther = (mutateRowsTs_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRowBatches()).compareTo(isSetRowBatches());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(rowBatches, typedOther.rowBatches);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(isSetTimestamp());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(timestamp, typedOther.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW_BATCHES:
              if (field.type == TType.LIST) {
                {
                  TList _list74 = iprot.readListBegin();
                  this.rowBatches = new ArrayList<BatchMutation>(_list74.size);
                  for (int _i75 = 0; _i75 < _list74.size; ++_i75)
                  {
                    BatchMutation _elem76;
                    _elem76 = new BatchMutation();
                    _elem76.read(iprot);
                    this.rowBatches.add(_elem76);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case TIMESTAMP:
              if (field.type == TType.I64) {
                this.timestamp = iprot.readI64();
                setTimestampIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.rowBatches != null) {
        oprot.writeFieldBegin(ROW_BATCHES_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.rowBatches.size()));
          for (BatchMutation _iter77 : this.rowBatches)
          {
            _iter77.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(this.timestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("mutateRowsTs_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("rowBatches:");
      if (this.rowBatches == null) {
        sb.append("null");
      } else {
        sb.append(this.rowBatches);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("timestamp:");
      sb.append(this.timestamp);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class mutateRowsTs_result implements TBase<mutateRowsTs_result._Fields>, java.io.Serializable, Cloneable, Comparable<mutateRowsTs_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("mutateRowsTs_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);
    private static final TField IA_FIELD_DESC = new TField("ia", TType.STRUCT, (short)2);

    public IOError io;
    public IllegalArgument ia;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io"),
      IA((short)2, "ia");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
      put(_Fields.IA, new FieldMetaData("ia", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(mutateRowsTs_result.class, metaDataMap);
    }

    public mutateRowsTs_result() {
    }

    public mutateRowsTs_result(
      IOError io,
      IllegalArgument ia)
    {
      this();
      this.io = io;
      this.ia = ia;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public mutateRowsTs_result(mutateRowsTs_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
      if (other.isSetIa()) {
        this.ia = new IllegalArgument(other.ia);
      }
    }

    public mutateRowsTs_result deepCopy() {
      return new mutateRowsTs_result(this);
    }

    @Deprecated
    public mutateRowsTs_result clone() {
      return new mutateRowsTs_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public mutateRowsTs_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public IllegalArgument getIa() {
      return this.ia;
    }

    public mutateRowsTs_result setIa(IllegalArgument ia) {
      this.ia = ia;
      return this;
    }

    public void unsetIa() {
      this.ia = null;
    }

    /** Returns true if field ia is set (has been asigned a value) and false otherwise */
    public boolean isSetIa() {
      return this.ia != null;
    }

    public void setIaIsSet(boolean value) {
      if (!value) {
        this.ia = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      case IA:
        if (value == null) {
          unsetIa();
        } else {
          setIa((IllegalArgument)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      case IA:
        return getIa();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      case IA:
        return isSetIa();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof mutateRowsTs_result)
        return this.equals((mutateRowsTs_result)that);
      return false;
    }

    public boolean equals(mutateRowsTs_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      boolean this_present_ia = true && this.isSetIa();
      boolean that_present_ia = true && that.isSetIa();
      if (this_present_ia || that_present_ia) {
        if (!(this_present_ia && that_present_ia))
          return false;
        if (!this.ia.equals(that.ia))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      boolean present_ia = true && (isSetIa());
      builder.append(present_ia);
      if (present_ia)
        builder.append(ia);

      return builder.toHashCode();
    }

    public int compareTo(mutateRowsTs_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      mutateRowsTs_result typedOther = (mutateRowsTs_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIa()).compareTo(isSetIa());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(ia, typedOther.ia);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IA:
              if (field.type == TType.STRUCT) {
                this.ia = new IllegalArgument();
                this.ia.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      } else if (this.isSetIa()) {
        oprot.writeFieldBegin(IA_FIELD_DESC);
        this.ia.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("mutateRowsTs_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ia:");
      if (this.ia == null) {
        sb.append("null");
      } else {
        sb.append(this.ia);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class atomicIncrement_args implements TBase<atomicIncrement_args._Fields>, java.io.Serializable, Cloneable, Comparable<atomicIncrement_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("atomicIncrement_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField COLUMN_FIELD_DESC = new TField("column", TType.STRING, (short)3);
    private static final TField VALUE_FIELD_DESC = new TField("value", TType.I64, (short)4);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * row to increment
     */
    public byte[] row;
    /**
     * name of column
     */
    public byte[] column;
    /**
     * amount to increment by
     */
    public long value;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * row to increment
       */
      ROW((short)2, "row"),
      /**
       * name of column
       */
      COLUMN((short)3, "column"),
      /**
       * amount to increment by
       */
      VALUE((short)4, "value");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __VALUE_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMN, new FieldMetaData("column", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.VALUE, new FieldMetaData("value", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I64)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(atomicIncrement_args.class, metaDataMap);
    }

    public atomicIncrement_args() {
    }

    public atomicIncrement_args(
      byte[] tableName,
      byte[] row,
      byte[] column,
      long value)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.column = column;
      this.value = value;
      setValueIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public atomicIncrement_args(atomicIncrement_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      if (other.isSetColumn()) {
        this.column = other.column;
      }
      this.value = other.value;
    }

    public atomicIncrement_args deepCopy() {
      return new atomicIncrement_args(this);
    }

    @Deprecated
    public atomicIncrement_args clone() {
      return new atomicIncrement_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public atomicIncrement_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * row to increment
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * row to increment
     */
    public atomicIncrement_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    /**
     * name of column
     */
    public byte[] getColumn() {
      return this.column;
    }

    /**
     * name of column
     */
    public atomicIncrement_args setColumn(byte[] column) {
      this.column = column;
      return this;
    }

    public void unsetColumn() {
      this.column = null;
    }

    /** Returns true if field column is set (has been asigned a value) and false otherwise */
    public boolean isSetColumn() {
      return this.column != null;
    }

    public void setColumnIsSet(boolean value) {
      if (!value) {
        this.column = null;
      }
    }

    /**
     * amount to increment by
     */
    public long getValue() {
      return this.value;
    }

    /**
     * amount to increment by
     */
    public atomicIncrement_args setValue(long value) {
      this.value = value;
      setValueIsSet(true);
      return this;
    }

    public void unsetValue() {
      __isset_bit_vector.clear(__VALUE_ISSET_ID);
    }

    /** Returns true if field value is set (has been asigned a value) and false otherwise */
    public boolean isSetValue() {
      return __isset_bit_vector.get(__VALUE_ISSET_ID);
    }

    public void setValueIsSet(boolean value) {
      __isset_bit_vector.set(__VALUE_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case COLUMN:
        if (value == null) {
          unsetColumn();
        } else {
          setColumn((byte[])value);
        }
        break;

      case VALUE:
        if (value == null) {
          unsetValue();
        } else {
          setValue((Long)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case COLUMN:
        return getColumn();

      case VALUE:
        return new Long(getValue());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case COLUMN:
        return isSetColumn();
      case VALUE:
        return isSetValue();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof atomicIncrement_args)
        return this.equals((atomicIncrement_args)that);
      return false;
    }

    public boolean equals(atomicIncrement_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_column = true && this.isSetColumn();
      boolean that_present_column = true && that.isSetColumn();
      if (this_present_column || that_present_column) {
        if (!(this_present_column && that_present_column))
          return false;
        if (!java.util.Arrays.equals(this.column, that.column))
          return false;
      }

      boolean this_present_value = true;
      boolean that_present_value = true;
      if (this_present_value || that_present_value) {
        if (!(this_present_value && that_present_value))
          return false;
        if (this.value != that.value)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_column = true && (isSetColumn());
      builder.append(present_column);
      if (present_column)
        builder.append(column);

      boolean present_value = true;
      builder.append(present_value);
      if (present_value)
        builder.append(value);

      return builder.toHashCode();
    }

    public int compareTo(atomicIncrement_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      atomicIncrement_args typedOther = (atomicIncrement_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumn()).compareTo(isSetColumn());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(column, typedOther.column);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetValue()).compareTo(isSetValue());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(value, typedOther.value);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMN:
              if (field.type == TType.STRING) {
                this.column = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case VALUE:
              if (field.type == TType.I64) {
                this.value = iprot.readI64();
                setValueIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      if (this.column != null) {
        oprot.writeFieldBegin(COLUMN_FIELD_DESC);
        oprot.writeBinary(this.column);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(VALUE_FIELD_DESC);
      oprot.writeI64(this.value);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("atomicIncrement_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("column:");
      if (this.column == null) {
        sb.append("null");
      } else {
        sb.append(this.column);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("value:");
      sb.append(this.value);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class atomicIncrement_result implements TBase<atomicIncrement_result._Fields>, java.io.Serializable, Cloneable, Comparable<atomicIncrement_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("atomicIncrement_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.I64, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);
    private static final TField IA_FIELD_DESC = new TField("ia", TType.STRUCT, (short)2);

    public long success;
    public IOError io;
    public IllegalArgument ia;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io"),
      IA((short)2, "ia");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __SUCCESS_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I64)));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
      put(_Fields.IA, new FieldMetaData("ia", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(atomicIncrement_result.class, metaDataMap);
    }

    public atomicIncrement_result() {
    }

    public atomicIncrement_result(
      long success,
      IOError io,
      IllegalArgument ia)
    {
      this();
      this.success = success;
      setSuccessIsSet(true);
      this.io = io;
      this.ia = ia;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public atomicIncrement_result(atomicIncrement_result other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.success = other.success;
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
      if (other.isSetIa()) {
        this.ia = new IllegalArgument(other.ia);
      }
    }

    public atomicIncrement_result deepCopy() {
      return new atomicIncrement_result(this);
    }

    @Deprecated
    public atomicIncrement_result clone() {
      return new atomicIncrement_result(this);
    }

    public long getSuccess() {
      return this.success;
    }

    public atomicIncrement_result setSuccess(long success) {
      this.success = success;
      setSuccessIsSet(true);
      return this;
    }

    public void unsetSuccess() {
      __isset_bit_vector.clear(__SUCCESS_ISSET_ID);
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return __isset_bit_vector.get(__SUCCESS_ISSET_ID);
    }

    public void setSuccessIsSet(boolean value) {
      __isset_bit_vector.set(__SUCCESS_ISSET_ID, value);
    }

    public IOError getIo() {
      return this.io;
    }

    public atomicIncrement_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public IllegalArgument getIa() {
      return this.ia;
    }

    public atomicIncrement_result setIa(IllegalArgument ia) {
      this.ia = ia;
      return this;
    }

    public void unsetIa() {
      this.ia = null;
    }

    /** Returns true if field ia is set (has been asigned a value) and false otherwise */
    public boolean isSetIa() {
      return this.ia != null;
    }

    public void setIaIsSet(boolean value) {
      if (!value) {
        this.ia = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((Long)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      case IA:
        if (value == null) {
          unsetIa();
        } else {
          setIa((IllegalArgument)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return new Long(getSuccess());

      case IO:
        return getIo();

      case IA:
        return getIa();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      case IA:
        return isSetIa();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof atomicIncrement_result)
        return this.equals((atomicIncrement_result)that);
      return false;
    }

    public boolean equals(atomicIncrement_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true;
      boolean that_present_success = true;
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (this.success != that.success)
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      boolean this_present_ia = true && this.isSetIa();
      boolean that_present_ia = true && that.isSetIa();
      if (this_present_ia || that_present_ia) {
        if (!(this_present_ia && that_present_ia))
          return false;
        if (!this.ia.equals(that.ia))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true;
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      boolean present_ia = true && (isSetIa());
      builder.append(present_ia);
      if (present_ia)
        builder.append(ia);

      return builder.toHashCode();
    }

    public int compareTo(atomicIncrement_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      atomicIncrement_result typedOther = (atomicIncrement_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIa()).compareTo(isSetIa());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(ia, typedOther.ia);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.I64) {
                this.success = iprot.readI64();
                setSuccessIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IA:
              if (field.type == TType.STRUCT) {
                this.ia = new IllegalArgument();
                this.ia.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        oprot.writeI64(this.success);
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      } else if (this.isSetIa()) {
        oprot.writeFieldBegin(IA_FIELD_DESC);
        this.ia.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("atomicIncrement_result(");
      boolean first = true;

      sb.append("success:");
      sb.append(this.success);
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ia:");
      if (this.ia == null) {
        sb.append("null");
      } else {
        sb.append(this.ia);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class deleteAll_args implements TBase<deleteAll_args._Fields>, java.io.Serializable, Cloneable, Comparable<deleteAll_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("deleteAll_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField COLUMN_FIELD_DESC = new TField("column", TType.STRING, (short)3);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * Row to update
     */
    public byte[] row;
    /**
     * name of column whose value is to be deleted
     */
    public byte[] column;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * Row to update
       */
      ROW((short)2, "row"),
      /**
       * name of column whose value is to be deleted
       */
      COLUMN((short)3, "column");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMN, new FieldMetaData("column", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(deleteAll_args.class, metaDataMap);
    }

    public deleteAll_args() {
    }

    public deleteAll_args(
      byte[] tableName,
      byte[] row,
      byte[] column)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.column = column;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public deleteAll_args(deleteAll_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      if (other.isSetColumn()) {
        this.column = other.column;
      }
    }

    public deleteAll_args deepCopy() {
      return new deleteAll_args(this);
    }

    @Deprecated
    public deleteAll_args clone() {
      return new deleteAll_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public deleteAll_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * Row to update
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * Row to update
     */
    public deleteAll_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    /**
     * name of column whose value is to be deleted
     */
    public byte[] getColumn() {
      return this.column;
    }

    /**
     * name of column whose value is to be deleted
     */
    public deleteAll_args setColumn(byte[] column) {
      this.column = column;
      return this;
    }

    public void unsetColumn() {
      this.column = null;
    }

    /** Returns true if field column is set (has been asigned a value) and false otherwise */
    public boolean isSetColumn() {
      return this.column != null;
    }

    public void setColumnIsSet(boolean value) {
      if (!value) {
        this.column = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case COLUMN:
        if (value == null) {
          unsetColumn();
        } else {
          setColumn((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case COLUMN:
        return getColumn();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case COLUMN:
        return isSetColumn();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof deleteAll_args)
        return this.equals((deleteAll_args)that);
      return false;
    }

    public boolean equals(deleteAll_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_column = true && this.isSetColumn();
      boolean that_present_column = true && that.isSetColumn();
      if (this_present_column || that_present_column) {
        if (!(this_present_column && that_present_column))
          return false;
        if (!java.util.Arrays.equals(this.column, that.column))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_column = true && (isSetColumn());
      builder.append(present_column);
      if (present_column)
        builder.append(column);

      return builder.toHashCode();
    }

    public int compareTo(deleteAll_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      deleteAll_args typedOther = (deleteAll_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumn()).compareTo(isSetColumn());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(column, typedOther.column);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMN:
              if (field.type == TType.STRING) {
                this.column = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      if (this.column != null) {
        oprot.writeFieldBegin(COLUMN_FIELD_DESC);
        oprot.writeBinary(this.column);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("deleteAll_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("column:");
      if (this.column == null) {
        sb.append("null");
      } else {
        sb.append(this.column);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class deleteAll_result implements TBase<deleteAll_result._Fields>, java.io.Serializable, Cloneable, Comparable<deleteAll_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("deleteAll_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(deleteAll_result.class, metaDataMap);
    }

    public deleteAll_result() {
    }

    public deleteAll_result(
      IOError io)
    {
      this();
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public deleteAll_result(deleteAll_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public deleteAll_result deepCopy() {
      return new deleteAll_result(this);
    }

    @Deprecated
    public deleteAll_result clone() {
      return new deleteAll_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public deleteAll_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof deleteAll_result)
        return this.equals((deleteAll_result)that);
      return false;
    }

    public boolean equals(deleteAll_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(deleteAll_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      deleteAll_result typedOther = (deleteAll_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("deleteAll_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class deleteAllTs_args implements TBase<deleteAllTs_args._Fields>, java.io.Serializable, Cloneable, Comparable<deleteAllTs_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("deleteAllTs_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField COLUMN_FIELD_DESC = new TField("column", TType.STRING, (short)3);
    private static final TField TIMESTAMP_FIELD_DESC = new TField("timestamp", TType.I64, (short)4);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * Row to update
     */
    public byte[] row;
    /**
     * name of column whose value is to be deleted
     */
    public byte[] column;
    /**
     * timestamp
     */
    public long timestamp;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * Row to update
       */
      ROW((short)2, "row"),
      /**
       * name of column whose value is to be deleted
       */
      COLUMN((short)3, "column"),
      /**
       * timestamp
       */
      TIMESTAMP((short)4, "timestamp");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __TIMESTAMP_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMN, new FieldMetaData("column", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.TIMESTAMP, new FieldMetaData("timestamp", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I64)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(deleteAllTs_args.class, metaDataMap);
    }

    public deleteAllTs_args() {
    }

    public deleteAllTs_args(
      byte[] tableName,
      byte[] row,
      byte[] column,
      long timestamp)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.column = column;
      this.timestamp = timestamp;
      setTimestampIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public deleteAllTs_args(deleteAllTs_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      if (other.isSetColumn()) {
        this.column = other.column;
      }
      this.timestamp = other.timestamp;
    }

    public deleteAllTs_args deepCopy() {
      return new deleteAllTs_args(this);
    }

    @Deprecated
    public deleteAllTs_args clone() {
      return new deleteAllTs_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public deleteAllTs_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * Row to update
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * Row to update
     */
    public deleteAllTs_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    /**
     * name of column whose value is to be deleted
     */
    public byte[] getColumn() {
      return this.column;
    }

    /**
     * name of column whose value is to be deleted
     */
    public deleteAllTs_args setColumn(byte[] column) {
      this.column = column;
      return this;
    }

    public void unsetColumn() {
      this.column = null;
    }

    /** Returns true if field column is set (has been asigned a value) and false otherwise */
    public boolean isSetColumn() {
      return this.column != null;
    }

    public void setColumnIsSet(boolean value) {
      if (!value) {
        this.column = null;
      }
    }

    /**
     * timestamp
     */
    public long getTimestamp() {
      return this.timestamp;
    }

    /**
     * timestamp
     */
    public deleteAllTs_args setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      setTimestampIsSet(true);
      return this;
    }

    public void unsetTimestamp() {
      __isset_bit_vector.clear(__TIMESTAMP_ISSET_ID);
    }

    /** Returns true if field timestamp is set (has been asigned a value) and false otherwise */
    public boolean isSetTimestamp() {
      return __isset_bit_vector.get(__TIMESTAMP_ISSET_ID);
    }

    public void setTimestampIsSet(boolean value) {
      __isset_bit_vector.set(__TIMESTAMP_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case COLUMN:
        if (value == null) {
          unsetColumn();
        } else {
          setColumn((byte[])value);
        }
        break;

      case TIMESTAMP:
        if (value == null) {
          unsetTimestamp();
        } else {
          setTimestamp((Long)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case COLUMN:
        return getColumn();

      case TIMESTAMP:
        return new Long(getTimestamp());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case COLUMN:
        return isSetColumn();
      case TIMESTAMP:
        return isSetTimestamp();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof deleteAllTs_args)
        return this.equals((deleteAllTs_args)that);
      return false;
    }

    public boolean equals(deleteAllTs_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_column = true && this.isSetColumn();
      boolean that_present_column = true && that.isSetColumn();
      if (this_present_column || that_present_column) {
        if (!(this_present_column && that_present_column))
          return false;
        if (!java.util.Arrays.equals(this.column, that.column))
          return false;
      }

      boolean this_present_timestamp = true;
      boolean that_present_timestamp = true;
      if (this_present_timestamp || that_present_timestamp) {
        if (!(this_present_timestamp && that_present_timestamp))
          return false;
        if (this.timestamp != that.timestamp)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_column = true && (isSetColumn());
      builder.append(present_column);
      if (present_column)
        builder.append(column);

      boolean present_timestamp = true;
      builder.append(present_timestamp);
      if (present_timestamp)
        builder.append(timestamp);

      return builder.toHashCode();
    }

    public int compareTo(deleteAllTs_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      deleteAllTs_args typedOther = (deleteAllTs_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumn()).compareTo(isSetColumn());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(column, typedOther.column);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(isSetTimestamp());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(timestamp, typedOther.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMN:
              if (field.type == TType.STRING) {
                this.column = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case TIMESTAMP:
              if (field.type == TType.I64) {
                this.timestamp = iprot.readI64();
                setTimestampIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      if (this.column != null) {
        oprot.writeFieldBegin(COLUMN_FIELD_DESC);
        oprot.writeBinary(this.column);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(this.timestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("deleteAllTs_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("column:");
      if (this.column == null) {
        sb.append("null");
      } else {
        sb.append(this.column);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("timestamp:");
      sb.append(this.timestamp);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class deleteAllTs_result implements TBase<deleteAllTs_result._Fields>, java.io.Serializable, Cloneable, Comparable<deleteAllTs_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("deleteAllTs_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(deleteAllTs_result.class, metaDataMap);
    }

    public deleteAllTs_result() {
    }

    public deleteAllTs_result(
      IOError io)
    {
      this();
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public deleteAllTs_result(deleteAllTs_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public deleteAllTs_result deepCopy() {
      return new deleteAllTs_result(this);
    }

    @Deprecated
    public deleteAllTs_result clone() {
      return new deleteAllTs_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public deleteAllTs_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof deleteAllTs_result)
        return this.equals((deleteAllTs_result)that);
      return false;
    }

    public boolean equals(deleteAllTs_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(deleteAllTs_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      deleteAllTs_result typedOther = (deleteAllTs_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("deleteAllTs_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class deleteAllRow_args implements TBase<deleteAllRow_args._Fields>, java.io.Serializable, Cloneable, Comparable<deleteAllRow_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("deleteAllRow_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * key of the row to be completely deleted.
     */
    public byte[] row;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * key of the row to be completely deleted.
       */
      ROW((short)2, "row");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(deleteAllRow_args.class, metaDataMap);
    }

    public deleteAllRow_args() {
    }

    public deleteAllRow_args(
      byte[] tableName,
      byte[] row)
    {
      this();
      this.tableName = tableName;
      this.row = row;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public deleteAllRow_args(deleteAllRow_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
    }

    public deleteAllRow_args deepCopy() {
      return new deleteAllRow_args(this);
    }

    @Deprecated
    public deleteAllRow_args clone() {
      return new deleteAllRow_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public deleteAllRow_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * key of the row to be completely deleted.
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * key of the row to be completely deleted.
     */
    public deleteAllRow_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof deleteAllRow_args)
        return this.equals((deleteAllRow_args)that);
      return false;
    }

    public boolean equals(deleteAllRow_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      return builder.toHashCode();
    }

    public int compareTo(deleteAllRow_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      deleteAllRow_args typedOther = (deleteAllRow_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("deleteAllRow_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class deleteAllRow_result implements TBase<deleteAllRow_result._Fields>, java.io.Serializable, Cloneable, Comparable<deleteAllRow_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("deleteAllRow_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(deleteAllRow_result.class, metaDataMap);
    }

    public deleteAllRow_result() {
    }

    public deleteAllRow_result(
      IOError io)
    {
      this();
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public deleteAllRow_result(deleteAllRow_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public deleteAllRow_result deepCopy() {
      return new deleteAllRow_result(this);
    }

    @Deprecated
    public deleteAllRow_result clone() {
      return new deleteAllRow_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public deleteAllRow_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof deleteAllRow_result)
        return this.equals((deleteAllRow_result)that);
      return false;
    }

    public boolean equals(deleteAllRow_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(deleteAllRow_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      deleteAllRow_result typedOther = (deleteAllRow_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("deleteAllRow_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class deleteAllRowTs_args implements TBase<deleteAllRowTs_args._Fields>, java.io.Serializable, Cloneable, Comparable<deleteAllRowTs_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("deleteAllRowTs_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField ROW_FIELD_DESC = new TField("row", TType.STRING, (short)2);
    private static final TField TIMESTAMP_FIELD_DESC = new TField("timestamp", TType.I64, (short)3);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * key of the row to be completely deleted.
     */
    public byte[] row;
    /**
     * timestamp
     */
    public long timestamp;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * key of the row to be completely deleted.
       */
      ROW((short)2, "row"),
      /**
       * timestamp
       */
      TIMESTAMP((short)3, "timestamp");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __TIMESTAMP_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.ROW, new FieldMetaData("row", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.TIMESTAMP, new FieldMetaData("timestamp", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I64)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(deleteAllRowTs_args.class, metaDataMap);
    }

    public deleteAllRowTs_args() {
    }

    public deleteAllRowTs_args(
      byte[] tableName,
      byte[] row,
      long timestamp)
    {
      this();
      this.tableName = tableName;
      this.row = row;
      this.timestamp = timestamp;
      setTimestampIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public deleteAllRowTs_args(deleteAllRowTs_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetRow()) {
        this.row = other.row;
      }
      this.timestamp = other.timestamp;
    }

    public deleteAllRowTs_args deepCopy() {
      return new deleteAllRowTs_args(this);
    }

    @Deprecated
    public deleteAllRowTs_args clone() {
      return new deleteAllRowTs_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public deleteAllRowTs_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * key of the row to be completely deleted.
     */
    public byte[] getRow() {
      return this.row;
    }

    /**
     * key of the row to be completely deleted.
     */
    public deleteAllRowTs_args setRow(byte[] row) {
      this.row = row;
      return this;
    }

    public void unsetRow() {
      this.row = null;
    }

    /** Returns true if field row is set (has been asigned a value) and false otherwise */
    public boolean isSetRow() {
      return this.row != null;
    }

    public void setRowIsSet(boolean value) {
      if (!value) {
        this.row = null;
      }
    }

    /**
     * timestamp
     */
    public long getTimestamp() {
      return this.timestamp;
    }

    /**
     * timestamp
     */
    public deleteAllRowTs_args setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      setTimestampIsSet(true);
      return this;
    }

    public void unsetTimestamp() {
      __isset_bit_vector.clear(__TIMESTAMP_ISSET_ID);
    }

    /** Returns true if field timestamp is set (has been asigned a value) and false otherwise */
    public boolean isSetTimestamp() {
      return __isset_bit_vector.get(__TIMESTAMP_ISSET_ID);
    }

    public void setTimestampIsSet(boolean value) {
      __isset_bit_vector.set(__TIMESTAMP_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case ROW:
        if (value == null) {
          unsetRow();
        } else {
          setRow((byte[])value);
        }
        break;

      case TIMESTAMP:
        if (value == null) {
          unsetTimestamp();
        } else {
          setTimestamp((Long)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case ROW:
        return getRow();

      case TIMESTAMP:
        return new Long(getTimestamp());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case ROW:
        return isSetRow();
      case TIMESTAMP:
        return isSetTimestamp();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof deleteAllRowTs_args)
        return this.equals((deleteAllRowTs_args)that);
      return false;
    }

    public boolean equals(deleteAllRowTs_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_row = true && this.isSetRow();
      boolean that_present_row = true && that.isSetRow();
      if (this_present_row || that_present_row) {
        if (!(this_present_row && that_present_row))
          return false;
        if (!java.util.Arrays.equals(this.row, that.row))
          return false;
      }

      boolean this_present_timestamp = true;
      boolean that_present_timestamp = true;
      if (this_present_timestamp || that_present_timestamp) {
        if (!(this_present_timestamp && that_present_timestamp))
          return false;
        if (this.timestamp != that.timestamp)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_row = true && (isSetRow());
      builder.append(present_row);
      if (present_row)
        builder.append(row);

      boolean present_timestamp = true;
      builder.append(present_timestamp);
      if (present_timestamp)
        builder.append(timestamp);

      return builder.toHashCode();
    }

    public int compareTo(deleteAllRowTs_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      deleteAllRowTs_args typedOther = (deleteAllRowTs_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetRow()).compareTo(isSetRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(row, typedOther.row);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(isSetTimestamp());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(timestamp, typedOther.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case ROW:
              if (field.type == TType.STRING) {
                this.row = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case TIMESTAMP:
              if (field.type == TType.I64) {
                this.timestamp = iprot.readI64();
                setTimestampIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.row != null) {
        oprot.writeFieldBegin(ROW_FIELD_DESC);
        oprot.writeBinary(this.row);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(this.timestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("deleteAllRowTs_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("row:");
      if (this.row == null) {
        sb.append("null");
      } else {
        sb.append(this.row);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("timestamp:");
      sb.append(this.timestamp);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class deleteAllRowTs_result implements TBase<deleteAllRowTs_result._Fields>, java.io.Serializable, Cloneable, Comparable<deleteAllRowTs_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("deleteAllRowTs_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(deleteAllRowTs_result.class, metaDataMap);
    }

    public deleteAllRowTs_result() {
    }

    public deleteAllRowTs_result(
      IOError io)
    {
      this();
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public deleteAllRowTs_result(deleteAllRowTs_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public deleteAllRowTs_result deepCopy() {
      return new deleteAllRowTs_result(this);
    }

    @Deprecated
    public deleteAllRowTs_result clone() {
      return new deleteAllRowTs_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public deleteAllRowTs_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof deleteAllRowTs_result)
        return this.equals((deleteAllRowTs_result)that);
      return false;
    }

    public boolean equals(deleteAllRowTs_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(deleteAllRowTs_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      deleteAllRowTs_result typedOther = (deleteAllRowTs_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("deleteAllRowTs_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerOpen_args implements TBase<scannerOpen_args._Fields>, java.io.Serializable, Cloneable, Comparable<scannerOpen_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerOpen_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField START_ROW_FIELD_DESC = new TField("startRow", TType.STRING, (short)2);
    private static final TField COLUMNS_FIELD_DESC = new TField("columns", TType.LIST, (short)3);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public byte[] startRow;
    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public List<byte[]> columns;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * Starting row in table to scan.
       * Send "" (empty string) to start at the first row.
       */
      START_ROW((short)2, "startRow"),
      /**
       * columns to scan. If column name is a column family, all
       * columns of the specified column family are returned. It's also possible
       * to pass a regex in the column qualifier.
       */
      COLUMNS((short)3, "columns");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.START_ROW, new FieldMetaData("startRow", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMNS, new FieldMetaData("columns", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new FieldValueMetaData(TType.STRING))));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerOpen_args.class, metaDataMap);
    }

    public scannerOpen_args() {
    }

    public scannerOpen_args(
      byte[] tableName,
      byte[] startRow,
      List<byte[]> columns)
    {
      this();
      this.tableName = tableName;
      this.startRow = startRow;
      this.columns = columns;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerOpen_args(scannerOpen_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetStartRow()) {
        this.startRow = other.startRow;
      }
      if (other.isSetColumns()) {
        List<byte[]> __this__columns = new ArrayList<byte[]>();
        for (byte[] other_element : other.columns) {
          __this__columns.add(other_element);
        }
        this.columns = __this__columns;
      }
    }

    public scannerOpen_args deepCopy() {
      return new scannerOpen_args(this);
    }

    @Deprecated
    public scannerOpen_args clone() {
      return new scannerOpen_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public scannerOpen_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public byte[] getStartRow() {
      return this.startRow;
    }

    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public scannerOpen_args setStartRow(byte[] startRow) {
      this.startRow = startRow;
      return this;
    }

    public void unsetStartRow() {
      this.startRow = null;
    }

    /** Returns true if field startRow is set (has been asigned a value) and false otherwise */
    public boolean isSetStartRow() {
      return this.startRow != null;
    }

    public void setStartRowIsSet(boolean value) {
      if (!value) {
        this.startRow = null;
      }
    }

    public int getColumnsSize() {
      return (this.columns == null) ? 0 : this.columns.size();
    }

    public java.util.Iterator<byte[]> getColumnsIterator() {
      return (this.columns == null) ? null : this.columns.iterator();
    }

    public void addToColumns(byte[] elem) {
      if (this.columns == null) {
        this.columns = new ArrayList<byte[]>();
      }
      this.columns.add(elem);
    }

    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public List<byte[]> getColumns() {
      return this.columns;
    }

    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public scannerOpen_args setColumns(List<byte[]> columns) {
      this.columns = columns;
      return this;
    }

    public void unsetColumns() {
      this.columns = null;
    }

    /** Returns true if field columns is set (has been asigned a value) and false otherwise */
    public boolean isSetColumns() {
      return this.columns != null;
    }

    public void setColumnsIsSet(boolean value) {
      if (!value) {
        this.columns = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case START_ROW:
        if (value == null) {
          unsetStartRow();
        } else {
          setStartRow((byte[])value);
        }
        break;

      case COLUMNS:
        if (value == null) {
          unsetColumns();
        } else {
          setColumns((List<byte[]>)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case START_ROW:
        return getStartRow();

      case COLUMNS:
        return getColumns();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case START_ROW:
        return isSetStartRow();
      case COLUMNS:
        return isSetColumns();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerOpen_args)
        return this.equals((scannerOpen_args)that);
      return false;
    }

    public boolean equals(scannerOpen_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_startRow = true && this.isSetStartRow();
      boolean that_present_startRow = true && that.isSetStartRow();
      if (this_present_startRow || that_present_startRow) {
        if (!(this_present_startRow && that_present_startRow))
          return false;
        if (!java.util.Arrays.equals(this.startRow, that.startRow))
          return false;
      }

      boolean this_present_columns = true && this.isSetColumns();
      boolean that_present_columns = true && that.isSetColumns();
      if (this_present_columns || that_present_columns) {
        if (!(this_present_columns && that_present_columns))
          return false;
        if (!this.columns.equals(that.columns))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_startRow = true && (isSetStartRow());
      builder.append(present_startRow);
      if (present_startRow)
        builder.append(startRow);

      boolean present_columns = true && (isSetColumns());
      builder.append(present_columns);
      if (present_columns)
        builder.append(columns);

      return builder.toHashCode();
    }

    public int compareTo(scannerOpen_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerOpen_args typedOther = (scannerOpen_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetStartRow()).compareTo(isSetStartRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(startRow, typedOther.startRow);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumns()).compareTo(isSetColumns());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(columns, typedOther.columns);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case START_ROW:
              if (field.type == TType.STRING) {
                this.startRow = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMNS:
              if (field.type == TType.LIST) {
                {
                  TList _list78 = iprot.readListBegin();
                  this.columns = new ArrayList<byte[]>(_list78.size);
                  for (int _i79 = 0; _i79 < _list78.size; ++_i79)
                  {
                    byte[] _elem80;
                    _elem80 = iprot.readBinary();
                    this.columns.add(_elem80);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.startRow != null) {
        oprot.writeFieldBegin(START_ROW_FIELD_DESC);
        oprot.writeBinary(this.startRow);
        oprot.writeFieldEnd();
      }
      if (this.columns != null) {
        oprot.writeFieldBegin(COLUMNS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRING, this.columns.size()));
          for (byte[] _iter81 : this.columns)
          {
            oprot.writeBinary(_iter81);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerOpen_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("startRow:");
      if (this.startRow == null) {
        sb.append("null");
      } else {
        sb.append(this.startRow);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("columns:");
      if (this.columns == null) {
        sb.append("null");
      } else {
        sb.append(this.columns);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerOpen_result implements TBase<scannerOpen_result._Fields>, java.io.Serializable, Cloneable, Comparable<scannerOpen_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerOpen_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.I32, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public int success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __SUCCESS_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I32)));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerOpen_result.class, metaDataMap);
    }

    public scannerOpen_result() {
    }

    public scannerOpen_result(
      int success,
      IOError io)
    {
      this();
      this.success = success;
      setSuccessIsSet(true);
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerOpen_result(scannerOpen_result other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.success = other.success;
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public scannerOpen_result deepCopy() {
      return new scannerOpen_result(this);
    }

    @Deprecated
    public scannerOpen_result clone() {
      return new scannerOpen_result(this);
    }

    public int getSuccess() {
      return this.success;
    }

    public scannerOpen_result setSuccess(int success) {
      this.success = success;
      setSuccessIsSet(true);
      return this;
    }

    public void unsetSuccess() {
      __isset_bit_vector.clear(__SUCCESS_ISSET_ID);
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return __isset_bit_vector.get(__SUCCESS_ISSET_ID);
    }

    public void setSuccessIsSet(boolean value) {
      __isset_bit_vector.set(__SUCCESS_ISSET_ID, value);
    }

    public IOError getIo() {
      return this.io;
    }

    public scannerOpen_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((Integer)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return new Integer(getSuccess());

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerOpen_result)
        return this.equals((scannerOpen_result)that);
      return false;
    }

    public boolean equals(scannerOpen_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true;
      boolean that_present_success = true;
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (this.success != that.success)
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true;
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(scannerOpen_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerOpen_result typedOther = (scannerOpen_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.I32) {
                this.success = iprot.readI32();
                setSuccessIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        oprot.writeI32(this.success);
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerOpen_result(");
      boolean first = true;

      sb.append("success:");
      sb.append(this.success);
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerOpenWithStop_args implements TBase<scannerOpenWithStop_args._Fields>, java.io.Serializable, Cloneable, Comparable<scannerOpenWithStop_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerOpenWithStop_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField START_ROW_FIELD_DESC = new TField("startRow", TType.STRING, (short)2);
    private static final TField STOP_ROW_FIELD_DESC = new TField("stopRow", TType.STRING, (short)3);
    private static final TField COLUMNS_FIELD_DESC = new TField("columns", TType.LIST, (short)4);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public byte[] startRow;
    /**
     * row to stop scanning on. This row is *not* included in the
     * scanner's results
     */
    public byte[] stopRow;
    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public List<byte[]> columns;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * Starting row in table to scan.
       * Send "" (empty string) to start at the first row.
       */
      START_ROW((short)2, "startRow"),
      /**
       * row to stop scanning on. This row is *not* included in the
       * scanner's results
       */
      STOP_ROW((short)3, "stopRow"),
      /**
       * columns to scan. If column name is a column family, all
       * columns of the specified column family are returned. It's also possible
       * to pass a regex in the column qualifier.
       */
      COLUMNS((short)4, "columns");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.START_ROW, new FieldMetaData("startRow", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.STOP_ROW, new FieldMetaData("stopRow", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMNS, new FieldMetaData("columns", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new FieldValueMetaData(TType.STRING))));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerOpenWithStop_args.class, metaDataMap);
    }

    public scannerOpenWithStop_args() {
    }

    public scannerOpenWithStop_args(
      byte[] tableName,
      byte[] startRow,
      byte[] stopRow,
      List<byte[]> columns)
    {
      this();
      this.tableName = tableName;
      this.startRow = startRow;
      this.stopRow = stopRow;
      this.columns = columns;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerOpenWithStop_args(scannerOpenWithStop_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetStartRow()) {
        this.startRow = other.startRow;
      }
      if (other.isSetStopRow()) {
        this.stopRow = other.stopRow;
      }
      if (other.isSetColumns()) {
        List<byte[]> __this__columns = new ArrayList<byte[]>();
        for (byte[] other_element : other.columns) {
          __this__columns.add(other_element);
        }
        this.columns = __this__columns;
      }
    }

    public scannerOpenWithStop_args deepCopy() {
      return new scannerOpenWithStop_args(this);
    }

    @Deprecated
    public scannerOpenWithStop_args clone() {
      return new scannerOpenWithStop_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public scannerOpenWithStop_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public byte[] getStartRow() {
      return this.startRow;
    }

    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public scannerOpenWithStop_args setStartRow(byte[] startRow) {
      this.startRow = startRow;
      return this;
    }

    public void unsetStartRow() {
      this.startRow = null;
    }

    /** Returns true if field startRow is set (has been asigned a value) and false otherwise */
    public boolean isSetStartRow() {
      return this.startRow != null;
    }

    public void setStartRowIsSet(boolean value) {
      if (!value) {
        this.startRow = null;
      }
    }

    /**
     * row to stop scanning on. This row is *not* included in the
     * scanner's results
     */
    public byte[] getStopRow() {
      return this.stopRow;
    }

    /**
     * row to stop scanning on. This row is *not* included in the
     * scanner's results
     */
    public scannerOpenWithStop_args setStopRow(byte[] stopRow) {
      this.stopRow = stopRow;
      return this;
    }

    public void unsetStopRow() {
      this.stopRow = null;
    }

    /** Returns true if field stopRow is set (has been asigned a value) and false otherwise */
    public boolean isSetStopRow() {
      return this.stopRow != null;
    }

    public void setStopRowIsSet(boolean value) {
      if (!value) {
        this.stopRow = null;
      }
    }

    public int getColumnsSize() {
      return (this.columns == null) ? 0 : this.columns.size();
    }

    public java.util.Iterator<byte[]> getColumnsIterator() {
      return (this.columns == null) ? null : this.columns.iterator();
    }

    public void addToColumns(byte[] elem) {
      if (this.columns == null) {
        this.columns = new ArrayList<byte[]>();
      }
      this.columns.add(elem);
    }

    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public List<byte[]> getColumns() {
      return this.columns;
    }

    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public scannerOpenWithStop_args setColumns(List<byte[]> columns) {
      this.columns = columns;
      return this;
    }

    public void unsetColumns() {
      this.columns = null;
    }

    /** Returns true if field columns is set (has been asigned a value) and false otherwise */
    public boolean isSetColumns() {
      return this.columns != null;
    }

    public void setColumnsIsSet(boolean value) {
      if (!value) {
        this.columns = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case START_ROW:
        if (value == null) {
          unsetStartRow();
        } else {
          setStartRow((byte[])value);
        }
        break;

      case STOP_ROW:
        if (value == null) {
          unsetStopRow();
        } else {
          setStopRow((byte[])value);
        }
        break;

      case COLUMNS:
        if (value == null) {
          unsetColumns();
        } else {
          setColumns((List<byte[]>)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case START_ROW:
        return getStartRow();

      case STOP_ROW:
        return getStopRow();

      case COLUMNS:
        return getColumns();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case START_ROW:
        return isSetStartRow();
      case STOP_ROW:
        return isSetStopRow();
      case COLUMNS:
        return isSetColumns();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerOpenWithStop_args)
        return this.equals((scannerOpenWithStop_args)that);
      return false;
    }

    public boolean equals(scannerOpenWithStop_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_startRow = true && this.isSetStartRow();
      boolean that_present_startRow = true && that.isSetStartRow();
      if (this_present_startRow || that_present_startRow) {
        if (!(this_present_startRow && that_present_startRow))
          return false;
        if (!java.util.Arrays.equals(this.startRow, that.startRow))
          return false;
      }

      boolean this_present_stopRow = true && this.isSetStopRow();
      boolean that_present_stopRow = true && that.isSetStopRow();
      if (this_present_stopRow || that_present_stopRow) {
        if (!(this_present_stopRow && that_present_stopRow))
          return false;
        if (!java.util.Arrays.equals(this.stopRow, that.stopRow))
          return false;
      }

      boolean this_present_columns = true && this.isSetColumns();
      boolean that_present_columns = true && that.isSetColumns();
      if (this_present_columns || that_present_columns) {
        if (!(this_present_columns && that_present_columns))
          return false;
        if (!this.columns.equals(that.columns))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_startRow = true && (isSetStartRow());
      builder.append(present_startRow);
      if (present_startRow)
        builder.append(startRow);

      boolean present_stopRow = true && (isSetStopRow());
      builder.append(present_stopRow);
      if (present_stopRow)
        builder.append(stopRow);

      boolean present_columns = true && (isSetColumns());
      builder.append(present_columns);
      if (present_columns)
        builder.append(columns);

      return builder.toHashCode();
    }

    public int compareTo(scannerOpenWithStop_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerOpenWithStop_args typedOther = (scannerOpenWithStop_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetStartRow()).compareTo(isSetStartRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(startRow, typedOther.startRow);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetStopRow()).compareTo(isSetStopRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(stopRow, typedOther.stopRow);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumns()).compareTo(isSetColumns());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(columns, typedOther.columns);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case START_ROW:
              if (field.type == TType.STRING) {
                this.startRow = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case STOP_ROW:
              if (field.type == TType.STRING) {
                this.stopRow = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMNS:
              if (field.type == TType.LIST) {
                {
                  TList _list82 = iprot.readListBegin();
                  this.columns = new ArrayList<byte[]>(_list82.size);
                  for (int _i83 = 0; _i83 < _list82.size; ++_i83)
                  {
                    byte[] _elem84;
                    _elem84 = iprot.readBinary();
                    this.columns.add(_elem84);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.startRow != null) {
        oprot.writeFieldBegin(START_ROW_FIELD_DESC);
        oprot.writeBinary(this.startRow);
        oprot.writeFieldEnd();
      }
      if (this.stopRow != null) {
        oprot.writeFieldBegin(STOP_ROW_FIELD_DESC);
        oprot.writeBinary(this.stopRow);
        oprot.writeFieldEnd();
      }
      if (this.columns != null) {
        oprot.writeFieldBegin(COLUMNS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRING, this.columns.size()));
          for (byte[] _iter85 : this.columns)
          {
            oprot.writeBinary(_iter85);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerOpenWithStop_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("startRow:");
      if (this.startRow == null) {
        sb.append("null");
      } else {
        sb.append(this.startRow);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("stopRow:");
      if (this.stopRow == null) {
        sb.append("null");
      } else {
        sb.append(this.stopRow);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("columns:");
      if (this.columns == null) {
        sb.append("null");
      } else {
        sb.append(this.columns);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerOpenWithStop_result implements TBase<scannerOpenWithStop_result._Fields>, java.io.Serializable, Cloneable, Comparable<scannerOpenWithStop_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerOpenWithStop_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.I32, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public int success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __SUCCESS_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I32)));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerOpenWithStop_result.class, metaDataMap);
    }

    public scannerOpenWithStop_result() {
    }

    public scannerOpenWithStop_result(
      int success,
      IOError io)
    {
      this();
      this.success = success;
      setSuccessIsSet(true);
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerOpenWithStop_result(scannerOpenWithStop_result other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.success = other.success;
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public scannerOpenWithStop_result deepCopy() {
      return new scannerOpenWithStop_result(this);
    }

    @Deprecated
    public scannerOpenWithStop_result clone() {
      return new scannerOpenWithStop_result(this);
    }

    public int getSuccess() {
      return this.success;
    }

    public scannerOpenWithStop_result setSuccess(int success) {
      this.success = success;
      setSuccessIsSet(true);
      return this;
    }

    public void unsetSuccess() {
      __isset_bit_vector.clear(__SUCCESS_ISSET_ID);
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return __isset_bit_vector.get(__SUCCESS_ISSET_ID);
    }

    public void setSuccessIsSet(boolean value) {
      __isset_bit_vector.set(__SUCCESS_ISSET_ID, value);
    }

    public IOError getIo() {
      return this.io;
    }

    public scannerOpenWithStop_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((Integer)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return new Integer(getSuccess());

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerOpenWithStop_result)
        return this.equals((scannerOpenWithStop_result)that);
      return false;
    }

    public boolean equals(scannerOpenWithStop_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true;
      boolean that_present_success = true;
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (this.success != that.success)
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true;
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(scannerOpenWithStop_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerOpenWithStop_result typedOther = (scannerOpenWithStop_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.I32) {
                this.success = iprot.readI32();
                setSuccessIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        oprot.writeI32(this.success);
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerOpenWithStop_result(");
      boolean first = true;

      sb.append("success:");
      sb.append(this.success);
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerOpenWithPrefix_args implements TBase<scannerOpenWithPrefix_args._Fields>, java.io.Serializable, Cloneable, Comparable<scannerOpenWithPrefix_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerOpenWithPrefix_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField START_AND_PREFIX_FIELD_DESC = new TField("startAndPrefix", TType.STRING, (short)2);
    private static final TField COLUMNS_FIELD_DESC = new TField("columns", TType.LIST, (short)3);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * the prefix (and thus start row) of the keys you want
     */
    public byte[] startAndPrefix;
    /**
     * the columns you want returned
     */
    public List<byte[]> columns;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * the prefix (and thus start row) of the keys you want
       */
      START_AND_PREFIX((short)2, "startAndPrefix"),
      /**
       * the columns you want returned
       */
      COLUMNS((short)3, "columns");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.START_AND_PREFIX, new FieldMetaData("startAndPrefix", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMNS, new FieldMetaData("columns", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new FieldValueMetaData(TType.STRING))));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerOpenWithPrefix_args.class, metaDataMap);
    }

    public scannerOpenWithPrefix_args() {
    }

    public scannerOpenWithPrefix_args(
      byte[] tableName,
      byte[] startAndPrefix,
      List<byte[]> columns)
    {
      this();
      this.tableName = tableName;
      this.startAndPrefix = startAndPrefix;
      this.columns = columns;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerOpenWithPrefix_args(scannerOpenWithPrefix_args other) {
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetStartAndPrefix()) {
        this.startAndPrefix = other.startAndPrefix;
      }
      if (other.isSetColumns()) {
        List<byte[]> __this__columns = new ArrayList<byte[]>();
        for (byte[] other_element : other.columns) {
          __this__columns.add(other_element);
        }
        this.columns = __this__columns;
      }
    }

    public scannerOpenWithPrefix_args deepCopy() {
      return new scannerOpenWithPrefix_args(this);
    }

    @Deprecated
    public scannerOpenWithPrefix_args clone() {
      return new scannerOpenWithPrefix_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public scannerOpenWithPrefix_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * the prefix (and thus start row) of the keys you want
     */
    public byte[] getStartAndPrefix() {
      return this.startAndPrefix;
    }

    /**
     * the prefix (and thus start row) of the keys you want
     */
    public scannerOpenWithPrefix_args setStartAndPrefix(byte[] startAndPrefix) {
      this.startAndPrefix = startAndPrefix;
      return this;
    }

    public void unsetStartAndPrefix() {
      this.startAndPrefix = null;
    }

    /** Returns true if field startAndPrefix is set (has been asigned a value) and false otherwise */
    public boolean isSetStartAndPrefix() {
      return this.startAndPrefix != null;
    }

    public void setStartAndPrefixIsSet(boolean value) {
      if (!value) {
        this.startAndPrefix = null;
      }
    }

    public int getColumnsSize() {
      return (this.columns == null) ? 0 : this.columns.size();
    }

    public java.util.Iterator<byte[]> getColumnsIterator() {
      return (this.columns == null) ? null : this.columns.iterator();
    }

    public void addToColumns(byte[] elem) {
      if (this.columns == null) {
        this.columns = new ArrayList<byte[]>();
      }
      this.columns.add(elem);
    }

    /**
     * the columns you want returned
     */
    public List<byte[]> getColumns() {
      return this.columns;
    }

    /**
     * the columns you want returned
     */
    public scannerOpenWithPrefix_args setColumns(List<byte[]> columns) {
      this.columns = columns;
      return this;
    }

    public void unsetColumns() {
      this.columns = null;
    }

    /** Returns true if field columns is set (has been asigned a value) and false otherwise */
    public boolean isSetColumns() {
      return this.columns != null;
    }

    public void setColumnsIsSet(boolean value) {
      if (!value) {
        this.columns = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case START_AND_PREFIX:
        if (value == null) {
          unsetStartAndPrefix();
        } else {
          setStartAndPrefix((byte[])value);
        }
        break;

      case COLUMNS:
        if (value == null) {
          unsetColumns();
        } else {
          setColumns((List<byte[]>)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case START_AND_PREFIX:
        return getStartAndPrefix();

      case COLUMNS:
        return getColumns();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case START_AND_PREFIX:
        return isSetStartAndPrefix();
      case COLUMNS:
        return isSetColumns();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerOpenWithPrefix_args)
        return this.equals((scannerOpenWithPrefix_args)that);
      return false;
    }

    public boolean equals(scannerOpenWithPrefix_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_startAndPrefix = true && this.isSetStartAndPrefix();
      boolean that_present_startAndPrefix = true && that.isSetStartAndPrefix();
      if (this_present_startAndPrefix || that_present_startAndPrefix) {
        if (!(this_present_startAndPrefix && that_present_startAndPrefix))
          return false;
        if (!java.util.Arrays.equals(this.startAndPrefix, that.startAndPrefix))
          return false;
      }

      boolean this_present_columns = true && this.isSetColumns();
      boolean that_present_columns = true && that.isSetColumns();
      if (this_present_columns || that_present_columns) {
        if (!(this_present_columns && that_present_columns))
          return false;
        if (!this.columns.equals(that.columns))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_startAndPrefix = true && (isSetStartAndPrefix());
      builder.append(present_startAndPrefix);
      if (present_startAndPrefix)
        builder.append(startAndPrefix);

      boolean present_columns = true && (isSetColumns());
      builder.append(present_columns);
      if (present_columns)
        builder.append(columns);

      return builder.toHashCode();
    }

    public int compareTo(scannerOpenWithPrefix_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerOpenWithPrefix_args typedOther = (scannerOpenWithPrefix_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetStartAndPrefix()).compareTo(isSetStartAndPrefix());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(startAndPrefix, typedOther.startAndPrefix);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumns()).compareTo(isSetColumns());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(columns, typedOther.columns);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case START_AND_PREFIX:
              if (field.type == TType.STRING) {
                this.startAndPrefix = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMNS:
              if (field.type == TType.LIST) {
                {
                  TList _list86 = iprot.readListBegin();
                  this.columns = new ArrayList<byte[]>(_list86.size);
                  for (int _i87 = 0; _i87 < _list86.size; ++_i87)
                  {
                    byte[] _elem88;
                    _elem88 = iprot.readBinary();
                    this.columns.add(_elem88);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.startAndPrefix != null) {
        oprot.writeFieldBegin(START_AND_PREFIX_FIELD_DESC);
        oprot.writeBinary(this.startAndPrefix);
        oprot.writeFieldEnd();
      }
      if (this.columns != null) {
        oprot.writeFieldBegin(COLUMNS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRING, this.columns.size()));
          for (byte[] _iter89 : this.columns)
          {
            oprot.writeBinary(_iter89);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerOpenWithPrefix_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("startAndPrefix:");
      if (this.startAndPrefix == null) {
        sb.append("null");
      } else {
        sb.append(this.startAndPrefix);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("columns:");
      if (this.columns == null) {
        sb.append("null");
      } else {
        sb.append(this.columns);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerOpenWithPrefix_result implements TBase<scannerOpenWithPrefix_result._Fields>, java.io.Serializable, Cloneable, Comparable<scannerOpenWithPrefix_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerOpenWithPrefix_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.I32, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public int success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __SUCCESS_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I32)));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerOpenWithPrefix_result.class, metaDataMap);
    }

    public scannerOpenWithPrefix_result() {
    }

    public scannerOpenWithPrefix_result(
      int success,
      IOError io)
    {
      this();
      this.success = success;
      setSuccessIsSet(true);
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerOpenWithPrefix_result(scannerOpenWithPrefix_result other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.success = other.success;
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public scannerOpenWithPrefix_result deepCopy() {
      return new scannerOpenWithPrefix_result(this);
    }

    @Deprecated
    public scannerOpenWithPrefix_result clone() {
      return new scannerOpenWithPrefix_result(this);
    }

    public int getSuccess() {
      return this.success;
    }

    public scannerOpenWithPrefix_result setSuccess(int success) {
      this.success = success;
      setSuccessIsSet(true);
      return this;
    }

    public void unsetSuccess() {
      __isset_bit_vector.clear(__SUCCESS_ISSET_ID);
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return __isset_bit_vector.get(__SUCCESS_ISSET_ID);
    }

    public void setSuccessIsSet(boolean value) {
      __isset_bit_vector.set(__SUCCESS_ISSET_ID, value);
    }

    public IOError getIo() {
      return this.io;
    }

    public scannerOpenWithPrefix_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((Integer)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return new Integer(getSuccess());

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerOpenWithPrefix_result)
        return this.equals((scannerOpenWithPrefix_result)that);
      return false;
    }

    public boolean equals(scannerOpenWithPrefix_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true;
      boolean that_present_success = true;
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (this.success != that.success)
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true;
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(scannerOpenWithPrefix_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerOpenWithPrefix_result typedOther = (scannerOpenWithPrefix_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.I32) {
                this.success = iprot.readI32();
                setSuccessIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        oprot.writeI32(this.success);
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerOpenWithPrefix_result(");
      boolean first = true;

      sb.append("success:");
      sb.append(this.success);
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerOpenTs_args implements TBase<scannerOpenTs_args._Fields>, java.io.Serializable, Cloneable, Comparable<scannerOpenTs_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerOpenTs_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField START_ROW_FIELD_DESC = new TField("startRow", TType.STRING, (short)2);
    private static final TField COLUMNS_FIELD_DESC = new TField("columns", TType.LIST, (short)3);
    private static final TField TIMESTAMP_FIELD_DESC = new TField("timestamp", TType.I64, (short)4);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public byte[] startRow;
    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public List<byte[]> columns;
    /**
     * timestamp
     */
    public long timestamp;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * Starting row in table to scan.
       * Send "" (empty string) to start at the first row.
       */
      START_ROW((short)2, "startRow"),
      /**
       * columns to scan. If column name is a column family, all
       * columns of the specified column family are returned. It's also possible
       * to pass a regex in the column qualifier.
       */
      COLUMNS((short)3, "columns"),
      /**
       * timestamp
       */
      TIMESTAMP((short)4, "timestamp");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __TIMESTAMP_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.START_ROW, new FieldMetaData("startRow", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMNS, new FieldMetaData("columns", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new FieldValueMetaData(TType.STRING))));
      put(_Fields.TIMESTAMP, new FieldMetaData("timestamp", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I64)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerOpenTs_args.class, metaDataMap);
    }

    public scannerOpenTs_args() {
    }

    public scannerOpenTs_args(
      byte[] tableName,
      byte[] startRow,
      List<byte[]> columns,
      long timestamp)
    {
      this();
      this.tableName = tableName;
      this.startRow = startRow;
      this.columns = columns;
      this.timestamp = timestamp;
      setTimestampIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerOpenTs_args(scannerOpenTs_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetStartRow()) {
        this.startRow = other.startRow;
      }
      if (other.isSetColumns()) {
        List<byte[]> __this__columns = new ArrayList<byte[]>();
        for (byte[] other_element : other.columns) {
          __this__columns.add(other_element);
        }
        this.columns = __this__columns;
      }
      this.timestamp = other.timestamp;
    }

    public scannerOpenTs_args deepCopy() {
      return new scannerOpenTs_args(this);
    }

    @Deprecated
    public scannerOpenTs_args clone() {
      return new scannerOpenTs_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public scannerOpenTs_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public byte[] getStartRow() {
      return this.startRow;
    }

    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public scannerOpenTs_args setStartRow(byte[] startRow) {
      this.startRow = startRow;
      return this;
    }

    public void unsetStartRow() {
      this.startRow = null;
    }

    /** Returns true if field startRow is set (has been asigned a value) and false otherwise */
    public boolean isSetStartRow() {
      return this.startRow != null;
    }

    public void setStartRowIsSet(boolean value) {
      if (!value) {
        this.startRow = null;
      }
    }

    public int getColumnsSize() {
      return (this.columns == null) ? 0 : this.columns.size();
    }

    public java.util.Iterator<byte[]> getColumnsIterator() {
      return (this.columns == null) ? null : this.columns.iterator();
    }

    public void addToColumns(byte[] elem) {
      if (this.columns == null) {
        this.columns = new ArrayList<byte[]>();
      }
      this.columns.add(elem);
    }

    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public List<byte[]> getColumns() {
      return this.columns;
    }

    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public scannerOpenTs_args setColumns(List<byte[]> columns) {
      this.columns = columns;
      return this;
    }

    public void unsetColumns() {
      this.columns = null;
    }

    /** Returns true if field columns is set (has been asigned a value) and false otherwise */
    public boolean isSetColumns() {
      return this.columns != null;
    }

    public void setColumnsIsSet(boolean value) {
      if (!value) {
        this.columns = null;
      }
    }

    /**
     * timestamp
     */
    public long getTimestamp() {
      return this.timestamp;
    }

    /**
     * timestamp
     */
    public scannerOpenTs_args setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      setTimestampIsSet(true);
      return this;
    }

    public void unsetTimestamp() {
      __isset_bit_vector.clear(__TIMESTAMP_ISSET_ID);
    }

    /** Returns true if field timestamp is set (has been asigned a value) and false otherwise */
    public boolean isSetTimestamp() {
      return __isset_bit_vector.get(__TIMESTAMP_ISSET_ID);
    }

    public void setTimestampIsSet(boolean value) {
      __isset_bit_vector.set(__TIMESTAMP_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case START_ROW:
        if (value == null) {
          unsetStartRow();
        } else {
          setStartRow((byte[])value);
        }
        break;

      case COLUMNS:
        if (value == null) {
          unsetColumns();
        } else {
          setColumns((List<byte[]>)value);
        }
        break;

      case TIMESTAMP:
        if (value == null) {
          unsetTimestamp();
        } else {
          setTimestamp((Long)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case START_ROW:
        return getStartRow();

      case COLUMNS:
        return getColumns();

      case TIMESTAMP:
        return new Long(getTimestamp());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case START_ROW:
        return isSetStartRow();
      case COLUMNS:
        return isSetColumns();
      case TIMESTAMP:
        return isSetTimestamp();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerOpenTs_args)
        return this.equals((scannerOpenTs_args)that);
      return false;
    }

    public boolean equals(scannerOpenTs_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_startRow = true && this.isSetStartRow();
      boolean that_present_startRow = true && that.isSetStartRow();
      if (this_present_startRow || that_present_startRow) {
        if (!(this_present_startRow && that_present_startRow))
          return false;
        if (!java.util.Arrays.equals(this.startRow, that.startRow))
          return false;
      }

      boolean this_present_columns = true && this.isSetColumns();
      boolean that_present_columns = true && that.isSetColumns();
      if (this_present_columns || that_present_columns) {
        if (!(this_present_columns && that_present_columns))
          return false;
        if (!this.columns.equals(that.columns))
          return false;
      }

      boolean this_present_timestamp = true;
      boolean that_present_timestamp = true;
      if (this_present_timestamp || that_present_timestamp) {
        if (!(this_present_timestamp && that_present_timestamp))
          return false;
        if (this.timestamp != that.timestamp)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_startRow = true && (isSetStartRow());
      builder.append(present_startRow);
      if (present_startRow)
        builder.append(startRow);

      boolean present_columns = true && (isSetColumns());
      builder.append(present_columns);
      if (present_columns)
        builder.append(columns);

      boolean present_timestamp = true;
      builder.append(present_timestamp);
      if (present_timestamp)
        builder.append(timestamp);

      return builder.toHashCode();
    }

    public int compareTo(scannerOpenTs_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerOpenTs_args typedOther = (scannerOpenTs_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetStartRow()).compareTo(isSetStartRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(startRow, typedOther.startRow);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumns()).compareTo(isSetColumns());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(columns, typedOther.columns);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(isSetTimestamp());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(timestamp, typedOther.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case START_ROW:
              if (field.type == TType.STRING) {
                this.startRow = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMNS:
              if (field.type == TType.LIST) {
                {
                  TList _list90 = iprot.readListBegin();
                  this.columns = new ArrayList<byte[]>(_list90.size);
                  for (int _i91 = 0; _i91 < _list90.size; ++_i91)
                  {
                    byte[] _elem92;
                    _elem92 = iprot.readBinary();
                    this.columns.add(_elem92);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case TIMESTAMP:
              if (field.type == TType.I64) {
                this.timestamp = iprot.readI64();
                setTimestampIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.startRow != null) {
        oprot.writeFieldBegin(START_ROW_FIELD_DESC);
        oprot.writeBinary(this.startRow);
        oprot.writeFieldEnd();
      }
      if (this.columns != null) {
        oprot.writeFieldBegin(COLUMNS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRING, this.columns.size()));
          for (byte[] _iter93 : this.columns)
          {
            oprot.writeBinary(_iter93);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(this.timestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerOpenTs_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("startRow:");
      if (this.startRow == null) {
        sb.append("null");
      } else {
        sb.append(this.startRow);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("columns:");
      if (this.columns == null) {
        sb.append("null");
      } else {
        sb.append(this.columns);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("timestamp:");
      sb.append(this.timestamp);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerOpenTs_result implements TBase<scannerOpenTs_result._Fields>, java.io.Serializable, Cloneable, Comparable<scannerOpenTs_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerOpenTs_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.I32, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public int success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __SUCCESS_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I32)));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerOpenTs_result.class, metaDataMap);
    }

    public scannerOpenTs_result() {
    }

    public scannerOpenTs_result(
      int success,
      IOError io)
    {
      this();
      this.success = success;
      setSuccessIsSet(true);
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerOpenTs_result(scannerOpenTs_result other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.success = other.success;
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public scannerOpenTs_result deepCopy() {
      return new scannerOpenTs_result(this);
    }

    @Deprecated
    public scannerOpenTs_result clone() {
      return new scannerOpenTs_result(this);
    }

    public int getSuccess() {
      return this.success;
    }

    public scannerOpenTs_result setSuccess(int success) {
      this.success = success;
      setSuccessIsSet(true);
      return this;
    }

    public void unsetSuccess() {
      __isset_bit_vector.clear(__SUCCESS_ISSET_ID);
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return __isset_bit_vector.get(__SUCCESS_ISSET_ID);
    }

    public void setSuccessIsSet(boolean value) {
      __isset_bit_vector.set(__SUCCESS_ISSET_ID, value);
    }

    public IOError getIo() {
      return this.io;
    }

    public scannerOpenTs_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((Integer)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return new Integer(getSuccess());

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerOpenTs_result)
        return this.equals((scannerOpenTs_result)that);
      return false;
    }

    public boolean equals(scannerOpenTs_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true;
      boolean that_present_success = true;
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (this.success != that.success)
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true;
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(scannerOpenTs_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerOpenTs_result typedOther = (scannerOpenTs_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.I32) {
                this.success = iprot.readI32();
                setSuccessIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        oprot.writeI32(this.success);
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerOpenTs_result(");
      boolean first = true;

      sb.append("success:");
      sb.append(this.success);
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerOpenWithStopTs_args implements TBase<scannerOpenWithStopTs_args._Fields>, java.io.Serializable, Cloneable, Comparable<scannerOpenWithStopTs_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerOpenWithStopTs_args");

    private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", TType.STRING, (short)1);
    private static final TField START_ROW_FIELD_DESC = new TField("startRow", TType.STRING, (short)2);
    private static final TField STOP_ROW_FIELD_DESC = new TField("stopRow", TType.STRING, (short)3);
    private static final TField COLUMNS_FIELD_DESC = new TField("columns", TType.LIST, (short)4);
    private static final TField TIMESTAMP_FIELD_DESC = new TField("timestamp", TType.I64, (short)5);

    /**
     * name of table
     */
    public byte[] tableName;
    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public byte[] startRow;
    /**
     * row to stop scanning on. This row is *not* included in the
     * scanner's results
     */
    public byte[] stopRow;
    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public List<byte[]> columns;
    /**
     * timestamp
     */
    public long timestamp;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * name of table
       */
      TABLE_NAME((short)1, "tableName"),
      /**
       * Starting row in table to scan.
       * Send "" (empty string) to start at the first row.
       */
      START_ROW((short)2, "startRow"),
      /**
       * row to stop scanning on. This row is *not* included in the
       * scanner's results
       */
      STOP_ROW((short)3, "stopRow"),
      /**
       * columns to scan. If column name is a column family, all
       * columns of the specified column family are returned. It's also possible
       * to pass a regex in the column qualifier.
       */
      COLUMNS((short)4, "columns"),
      /**
       * timestamp
       */
      TIMESTAMP((short)5, "timestamp");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __TIMESTAMP_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.TABLE_NAME, new FieldMetaData("tableName", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.START_ROW, new FieldMetaData("startRow", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.STOP_ROW, new FieldMetaData("stopRow", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRING)));
      put(_Fields.COLUMNS, new FieldMetaData("columns", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new FieldValueMetaData(TType.STRING))));
      put(_Fields.TIMESTAMP, new FieldMetaData("timestamp", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I64)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerOpenWithStopTs_args.class, metaDataMap);
    }

    public scannerOpenWithStopTs_args() {
    }

    public scannerOpenWithStopTs_args(
      byte[] tableName,
      byte[] startRow,
      byte[] stopRow,
      List<byte[]> columns,
      long timestamp)
    {
      this();
      this.tableName = tableName;
      this.startRow = startRow;
      this.stopRow = stopRow;
      this.columns = columns;
      this.timestamp = timestamp;
      setTimestampIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerOpenWithStopTs_args(scannerOpenWithStopTs_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
      if (other.isSetStartRow()) {
        this.startRow = other.startRow;
      }
      if (other.isSetStopRow()) {
        this.stopRow = other.stopRow;
      }
      if (other.isSetColumns()) {
        List<byte[]> __this__columns = new ArrayList<byte[]>();
        for (byte[] other_element : other.columns) {
          __this__columns.add(other_element);
        }
        this.columns = __this__columns;
      }
      this.timestamp = other.timestamp;
    }

    public scannerOpenWithStopTs_args deepCopy() {
      return new scannerOpenWithStopTs_args(this);
    }

    @Deprecated
    public scannerOpenWithStopTs_args clone() {
      return new scannerOpenWithStopTs_args(this);
    }

    /**
     * name of table
     */
    public byte[] getTableName() {
      return this.tableName;
    }

    /**
     * name of table
     */
    public scannerOpenWithStopTs_args setTableName(byte[] tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been asigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public byte[] getStartRow() {
      return this.startRow;
    }

    /**
     * Starting row in table to scan.
     * Send "" (empty string) to start at the first row.
     */
    public scannerOpenWithStopTs_args setStartRow(byte[] startRow) {
      this.startRow = startRow;
      return this;
    }

    public void unsetStartRow() {
      this.startRow = null;
    }

    /** Returns true if field startRow is set (has been asigned a value) and false otherwise */
    public boolean isSetStartRow() {
      return this.startRow != null;
    }

    public void setStartRowIsSet(boolean value) {
      if (!value) {
        this.startRow = null;
      }
    }

    /**
     * row to stop scanning on. This row is *not* included in the
     * scanner's results
     */
    public byte[] getStopRow() {
      return this.stopRow;
    }

    /**
     * row to stop scanning on. This row is *not* included in the
     * scanner's results
     */
    public scannerOpenWithStopTs_args setStopRow(byte[] stopRow) {
      this.stopRow = stopRow;
      return this;
    }

    public void unsetStopRow() {
      this.stopRow = null;
    }

    /** Returns true if field stopRow is set (has been asigned a value) and false otherwise */
    public boolean isSetStopRow() {
      return this.stopRow != null;
    }

    public void setStopRowIsSet(boolean value) {
      if (!value) {
        this.stopRow = null;
      }
    }

    public int getColumnsSize() {
      return (this.columns == null) ? 0 : this.columns.size();
    }

    public java.util.Iterator<byte[]> getColumnsIterator() {
      return (this.columns == null) ? null : this.columns.iterator();
    }

    public void addToColumns(byte[] elem) {
      if (this.columns == null) {
        this.columns = new ArrayList<byte[]>();
      }
      this.columns.add(elem);
    }

    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public List<byte[]> getColumns() {
      return this.columns;
    }

    /**
     * columns to scan. If column name is a column family, all
     * columns of the specified column family are returned. It's also possible
     * to pass a regex in the column qualifier.
     */
    public scannerOpenWithStopTs_args setColumns(List<byte[]> columns) {
      this.columns = columns;
      return this;
    }

    public void unsetColumns() {
      this.columns = null;
    }

    /** Returns true if field columns is set (has been asigned a value) and false otherwise */
    public boolean isSetColumns() {
      return this.columns != null;
    }

    public void setColumnsIsSet(boolean value) {
      if (!value) {
        this.columns = null;
      }
    }

    /**
     * timestamp
     */
    public long getTimestamp() {
      return this.timestamp;
    }

    /**
     * timestamp
     */
    public scannerOpenWithStopTs_args setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      setTimestampIsSet(true);
      return this;
    }

    public void unsetTimestamp() {
      __isset_bit_vector.clear(__TIMESTAMP_ISSET_ID);
    }

    /** Returns true if field timestamp is set (has been asigned a value) and false otherwise */
    public boolean isSetTimestamp() {
      return __isset_bit_vector.get(__TIMESTAMP_ISSET_ID);
    }

    public void setTimestampIsSet(boolean value) {
      __isset_bit_vector.set(__TIMESTAMP_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((byte[])value);
        }
        break;

      case START_ROW:
        if (value == null) {
          unsetStartRow();
        } else {
          setStartRow((byte[])value);
        }
        break;

      case STOP_ROW:
        if (value == null) {
          unsetStopRow();
        } else {
          setStopRow((byte[])value);
        }
        break;

      case COLUMNS:
        if (value == null) {
          unsetColumns();
        } else {
          setColumns((List<byte[]>)value);
        }
        break;

      case TIMESTAMP:
        if (value == null) {
          unsetTimestamp();
        } else {
          setTimestamp((Long)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return getTableName();

      case START_ROW:
        return getStartRow();

      case STOP_ROW:
        return getStopRow();

      case COLUMNS:
        return getColumns();

      case TIMESTAMP:
        return new Long(getTimestamp());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case TABLE_NAME:
        return isSetTableName();
      case START_ROW:
        return isSetStartRow();
      case STOP_ROW:
        return isSetStopRow();
      case COLUMNS:
        return isSetColumns();
      case TIMESTAMP:
        return isSetTimestamp();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerOpenWithStopTs_args)
        return this.equals((scannerOpenWithStopTs_args)that);
      return false;
    }

    public boolean equals(scannerOpenWithStopTs_args that) {
      if (that == null)
        return false;

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!java.util.Arrays.equals(this.tableName, that.tableName))
          return false;
      }

      boolean this_present_startRow = true && this.isSetStartRow();
      boolean that_present_startRow = true && that.isSetStartRow();
      if (this_present_startRow || that_present_startRow) {
        if (!(this_present_startRow && that_present_startRow))
          return false;
        if (!java.util.Arrays.equals(this.startRow, that.startRow))
          return false;
      }

      boolean this_present_stopRow = true && this.isSetStopRow();
      boolean that_present_stopRow = true && that.isSetStopRow();
      if (this_present_stopRow || that_present_stopRow) {
        if (!(this_present_stopRow && that_present_stopRow))
          return false;
        if (!java.util.Arrays.equals(this.stopRow, that.stopRow))
          return false;
      }

      boolean this_present_columns = true && this.isSetColumns();
      boolean that_present_columns = true && that.isSetColumns();
      if (this_present_columns || that_present_columns) {
        if (!(this_present_columns && that_present_columns))
          return false;
        if (!this.columns.equals(that.columns))
          return false;
      }

      boolean this_present_timestamp = true;
      boolean that_present_timestamp = true;
      if (this_present_timestamp || that_present_timestamp) {
        if (!(this_present_timestamp && that_present_timestamp))
          return false;
        if (this.timestamp != that.timestamp)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_tableName = true && (isSetTableName());
      builder.append(present_tableName);
      if (present_tableName)
        builder.append(tableName);

      boolean present_startRow = true && (isSetStartRow());
      builder.append(present_startRow);
      if (present_startRow)
        builder.append(startRow);

      boolean present_stopRow = true && (isSetStopRow());
      builder.append(present_stopRow);
      if (present_stopRow)
        builder.append(stopRow);

      boolean present_columns = true && (isSetColumns());
      builder.append(present_columns);
      if (present_columns)
        builder.append(columns);

      boolean present_timestamp = true;
      builder.append(present_timestamp);
      if (present_timestamp)
        builder.append(timestamp);

      return builder.toHashCode();
    }

    public int compareTo(scannerOpenWithStopTs_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerOpenWithStopTs_args typedOther = (scannerOpenWithStopTs_args)other;

      lastComparison = Boolean.valueOf(isSetTableName()).compareTo(isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(tableName, typedOther.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetStartRow()).compareTo(isSetStartRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(startRow, typedOther.startRow);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetStopRow()).compareTo(isSetStopRow());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(stopRow, typedOther.stopRow);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetColumns()).compareTo(isSetColumns());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(columns, typedOther.columns);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(isSetTimestamp());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(timestamp, typedOther.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case TABLE_NAME:
              if (field.type == TType.STRING) {
                this.tableName = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case START_ROW:
              if (field.type == TType.STRING) {
                this.startRow = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case STOP_ROW:
              if (field.type == TType.STRING) {
                this.stopRow = iprot.readBinary();
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case COLUMNS:
              if (field.type == TType.LIST) {
                {
                  TList _list94 = iprot.readListBegin();
                  this.columns = new ArrayList<byte[]>(_list94.size);
                  for (int _i95 = 0; _i95 < _list94.size; ++_i95)
                  {
                    byte[] _elem96;
                    _elem96 = iprot.readBinary();
                    this.columns.add(_elem96);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case TIMESTAMP:
              if (field.type == TType.I64) {
                this.timestamp = iprot.readI64();
                setTimestampIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (this.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeBinary(this.tableName);
        oprot.writeFieldEnd();
      }
      if (this.startRow != null) {
        oprot.writeFieldBegin(START_ROW_FIELD_DESC);
        oprot.writeBinary(this.startRow);
        oprot.writeFieldEnd();
      }
      if (this.stopRow != null) {
        oprot.writeFieldBegin(STOP_ROW_FIELD_DESC);
        oprot.writeBinary(this.stopRow);
        oprot.writeFieldEnd();
      }
      if (this.columns != null) {
        oprot.writeFieldBegin(COLUMNS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRING, this.columns.size()));
          for (byte[] _iter97 : this.columns)
          {
            oprot.writeBinary(_iter97);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(this.timestamp);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerOpenWithStopTs_args(");
      boolean first = true;

      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("startRow:");
      if (this.startRow == null) {
        sb.append("null");
      } else {
        sb.append(this.startRow);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("stopRow:");
      if (this.stopRow == null) {
        sb.append("null");
      } else {
        sb.append(this.stopRow);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("columns:");
      if (this.columns == null) {
        sb.append("null");
      } else {
        sb.append(this.columns);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("timestamp:");
      sb.append(this.timestamp);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerOpenWithStopTs_result implements TBase<scannerOpenWithStopTs_result._Fields>, java.io.Serializable, Cloneable, Comparable<scannerOpenWithStopTs_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerOpenWithStopTs_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.I32, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);

    public int success;
    public IOError io;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __SUCCESS_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I32)));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerOpenWithStopTs_result.class, metaDataMap);
    }

    public scannerOpenWithStopTs_result() {
    }

    public scannerOpenWithStopTs_result(
      int success,
      IOError io)
    {
      this();
      this.success = success;
      setSuccessIsSet(true);
      this.io = io;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerOpenWithStopTs_result(scannerOpenWithStopTs_result other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.success = other.success;
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
    }

    public scannerOpenWithStopTs_result deepCopy() {
      return new scannerOpenWithStopTs_result(this);
    }

    @Deprecated
    public scannerOpenWithStopTs_result clone() {
      return new scannerOpenWithStopTs_result(this);
    }

    public int getSuccess() {
      return this.success;
    }

    public scannerOpenWithStopTs_result setSuccess(int success) {
      this.success = success;
      setSuccessIsSet(true);
      return this;
    }

    public void unsetSuccess() {
      __isset_bit_vector.clear(__SUCCESS_ISSET_ID);
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return __isset_bit_vector.get(__SUCCESS_ISSET_ID);
    }

    public void setSuccessIsSet(boolean value) {
      __isset_bit_vector.set(__SUCCESS_ISSET_ID, value);
    }

    public IOError getIo() {
      return this.io;
    }

    public scannerOpenWithStopTs_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((Integer)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return new Integer(getSuccess());

      case IO:
        return getIo();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerOpenWithStopTs_result)
        return this.equals((scannerOpenWithStopTs_result)that);
      return false;
    }

    public boolean equals(scannerOpenWithStopTs_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true;
      boolean that_present_success = true;
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (this.success != that.success)
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true;
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      return builder.toHashCode();
    }

    public int compareTo(scannerOpenWithStopTs_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerOpenWithStopTs_result typedOther = (scannerOpenWithStopTs_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.I32) {
                this.success = iprot.readI32();
                setSuccessIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        oprot.writeI32(this.success);
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerOpenWithStopTs_result(");
      boolean first = true;

      sb.append("success:");
      sb.append(this.success);
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerGet_args implements TBase<scannerGet_args._Fields>, java.io.Serializable, Cloneable, Comparable<scannerGet_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerGet_args");

    private static final TField ID_FIELD_DESC = new TField("id", TType.I32, (short)1);

    /**
     * id of a scanner returned by scannerOpen
     */
    public int id;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * id of a scanner returned by scannerOpen
       */
      ID((short)1, "id");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __ID_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.ID, new FieldMetaData("id", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I32)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerGet_args.class, metaDataMap);
    }

    public scannerGet_args() {
    }

    public scannerGet_args(
      int id)
    {
      this();
      this.id = id;
      setIdIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerGet_args(scannerGet_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.id = other.id;
    }

    public scannerGet_args deepCopy() {
      return new scannerGet_args(this);
    }

    @Deprecated
    public scannerGet_args clone() {
      return new scannerGet_args(this);
    }

    /**
     * id of a scanner returned by scannerOpen
     */
    public int getId() {
      return this.id;
    }

    /**
     * id of a scanner returned by scannerOpen
     */
    public scannerGet_args setId(int id) {
      this.id = id;
      setIdIsSet(true);
      return this;
    }

    public void unsetId() {
      __isset_bit_vector.clear(__ID_ISSET_ID);
    }

    /** Returns true if field id is set (has been asigned a value) and false otherwise */
    public boolean isSetId() {
      return __isset_bit_vector.get(__ID_ISSET_ID);
    }

    public void setIdIsSet(boolean value) {
      __isset_bit_vector.set(__ID_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case ID:
        if (value == null) {
          unsetId();
        } else {
          setId((Integer)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case ID:
        return new Integer(getId());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case ID:
        return isSetId();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerGet_args)
        return this.equals((scannerGet_args)that);
      return false;
    }

    public boolean equals(scannerGet_args that) {
      if (that == null)
        return false;

      boolean this_present_id = true;
      boolean that_present_id = true;
      if (this_present_id || that_present_id) {
        if (!(this_present_id && that_present_id))
          return false;
        if (this.id != that.id)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_id = true;
      builder.append(present_id);
      if (present_id)
        builder.append(id);

      return builder.toHashCode();
    }

    public int compareTo(scannerGet_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerGet_args typedOther = (scannerGet_args)other;

      lastComparison = Boolean.valueOf(isSetId()).compareTo(isSetId());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(id, typedOther.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case ID:
              if (field.type == TType.I32) {
                this.id = iprot.readI32();
                setIdIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(this.id);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerGet_args(");
      boolean first = true;

      sb.append("id:");
      sb.append(this.id);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerGet_result implements TBase<scannerGet_result._Fields>, java.io.Serializable, Cloneable   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerGet_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.LIST, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);
    private static final TField IA_FIELD_DESC = new TField("ia", TType.STRUCT, (short)2);

    public List<TRowResult> success;
    public IOError io;
    public IllegalArgument ia;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io"),
      IA((short)2, "ia");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, TRowResult.class))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
      put(_Fields.IA, new FieldMetaData("ia", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerGet_result.class, metaDataMap);
    }

    public scannerGet_result() {
    }

    public scannerGet_result(
      List<TRowResult> success,
      IOError io,
      IllegalArgument ia)
    {
      this();
      this.success = success;
      this.io = io;
      this.ia = ia;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerGet_result(scannerGet_result other) {
      if (other.isSetSuccess()) {
        List<TRowResult> __this__success = new ArrayList<TRowResult>();
        for (TRowResult other_element : other.success) {
          __this__success.add(new TRowResult(other_element));
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
      if (other.isSetIa()) {
        this.ia = new IllegalArgument(other.ia);
      }
    }

    public scannerGet_result deepCopy() {
      return new scannerGet_result(this);
    }

    @Deprecated
    public scannerGet_result clone() {
      return new scannerGet_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<TRowResult> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(TRowResult elem) {
      if (this.success == null) {
        this.success = new ArrayList<TRowResult>();
      }
      this.success.add(elem);
    }

    public List<TRowResult> getSuccess() {
      return this.success;
    }

    public scannerGet_result setSuccess(List<TRowResult> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public scannerGet_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public IllegalArgument getIa() {
      return this.ia;
    }

    public scannerGet_result setIa(IllegalArgument ia) {
      this.ia = ia;
      return this;
    }

    public void unsetIa() {
      this.ia = null;
    }

    /** Returns true if field ia is set (has been asigned a value) and false otherwise */
    public boolean isSetIa() {
      return this.ia != null;
    }

    public void setIaIsSet(boolean value) {
      if (!value) {
        this.ia = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<TRowResult>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      case IA:
        if (value == null) {
          unsetIa();
        } else {
          setIa((IllegalArgument)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      case IA:
        return getIa();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      case IA:
        return isSetIa();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerGet_result)
        return this.equals((scannerGet_result)that);
      return false;
    }

    public boolean equals(scannerGet_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      boolean this_present_ia = true && this.isSetIa();
      boolean that_present_ia = true && that.isSetIa();
      if (this_present_ia || that_present_ia) {
        if (!(this_present_ia && that_present_ia))
          return false;
        if (!this.ia.equals(that.ia))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      boolean present_ia = true && (isSetIa());
      builder.append(present_ia);
      if (present_ia)
        builder.append(ia);

      return builder.toHashCode();
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.LIST) {
                {
                  TList _list98 = iprot.readListBegin();
                  this.success = new ArrayList<TRowResult>(_list98.size);
                  for (int _i99 = 0; _i99 < _list98.size; ++_i99)
                  {
                    TRowResult _elem100;
                    _elem100 = new TRowResult();
                    _elem100.read(iprot);
                    this.success.add(_elem100);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IA:
              if (field.type == TType.STRUCT) {
                this.ia = new IllegalArgument();
                this.ia.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.success.size()));
          for (TRowResult _iter101 : this.success)
          {
            _iter101.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      } else if (this.isSetIa()) {
        oprot.writeFieldBegin(IA_FIELD_DESC);
        this.ia.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerGet_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ia:");
      if (this.ia == null) {
        sb.append("null");
      } else {
        sb.append(this.ia);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerGetList_args implements TBase<scannerGetList_args._Fields>, java.io.Serializable, Cloneable, Comparable<scannerGetList_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerGetList_args");

    private static final TField ID_FIELD_DESC = new TField("id", TType.I32, (short)1);
    private static final TField NB_ROWS_FIELD_DESC = new TField("nbRows", TType.I32, (short)2);

    /**
     * id of a scanner returned by scannerOpen
     */
    public int id;
    /**
     * number of results to return
     */
    public int nbRows;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * id of a scanner returned by scannerOpen
       */
      ID((short)1, "id"),
      /**
       * number of results to return
       */
      NB_ROWS((short)2, "nbRows");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __ID_ISSET_ID = 0;
    private static final int __NBROWS_ISSET_ID = 1;
    private BitSet __isset_bit_vector = new BitSet(2);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.ID, new FieldMetaData("id", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I32)));
      put(_Fields.NB_ROWS, new FieldMetaData("nbRows", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I32)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerGetList_args.class, metaDataMap);
    }

    public scannerGetList_args() {
    }

    public scannerGetList_args(
      int id,
      int nbRows)
    {
      this();
      this.id = id;
      setIdIsSet(true);
      this.nbRows = nbRows;
      setNbRowsIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerGetList_args(scannerGetList_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.id = other.id;
      this.nbRows = other.nbRows;
    }

    public scannerGetList_args deepCopy() {
      return new scannerGetList_args(this);
    }

    @Deprecated
    public scannerGetList_args clone() {
      return new scannerGetList_args(this);
    }

    /**
     * id of a scanner returned by scannerOpen
     */
    public int getId() {
      return this.id;
    }

    /**
     * id of a scanner returned by scannerOpen
     */
    public scannerGetList_args setId(int id) {
      this.id = id;
      setIdIsSet(true);
      return this;
    }

    public void unsetId() {
      __isset_bit_vector.clear(__ID_ISSET_ID);
    }

    /** Returns true if field id is set (has been asigned a value) and false otherwise */
    public boolean isSetId() {
      return __isset_bit_vector.get(__ID_ISSET_ID);
    }

    public void setIdIsSet(boolean value) {
      __isset_bit_vector.set(__ID_ISSET_ID, value);
    }

    /**
     * number of results to return
     */
    public int getNbRows() {
      return this.nbRows;
    }

    /**
     * number of results to return
     */
    public scannerGetList_args setNbRows(int nbRows) {
      this.nbRows = nbRows;
      setNbRowsIsSet(true);
      return this;
    }

    public void unsetNbRows() {
      __isset_bit_vector.clear(__NBROWS_ISSET_ID);
    }

    /** Returns true if field nbRows is set (has been asigned a value) and false otherwise */
    public boolean isSetNbRows() {
      return __isset_bit_vector.get(__NBROWS_ISSET_ID);
    }

    public void setNbRowsIsSet(boolean value) {
      __isset_bit_vector.set(__NBROWS_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case ID:
        if (value == null) {
          unsetId();
        } else {
          setId((Integer)value);
        }
        break;

      case NB_ROWS:
        if (value == null) {
          unsetNbRows();
        } else {
          setNbRows((Integer)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case ID:
        return new Integer(getId());

      case NB_ROWS:
        return new Integer(getNbRows());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case ID:
        return isSetId();
      case NB_ROWS:
        return isSetNbRows();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerGetList_args)
        return this.equals((scannerGetList_args)that);
      return false;
    }

    public boolean equals(scannerGetList_args that) {
      if (that == null)
        return false;

      boolean this_present_id = true;
      boolean that_present_id = true;
      if (this_present_id || that_present_id) {
        if (!(this_present_id && that_present_id))
          return false;
        if (this.id != that.id)
          return false;
      }

      boolean this_present_nbRows = true;
      boolean that_present_nbRows = true;
      if (this_present_nbRows || that_present_nbRows) {
        if (!(this_present_nbRows && that_present_nbRows))
          return false;
        if (this.nbRows != that.nbRows)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_id = true;
      builder.append(present_id);
      if (present_id)
        builder.append(id);

      boolean present_nbRows = true;
      builder.append(present_nbRows);
      if (present_nbRows)
        builder.append(nbRows);

      return builder.toHashCode();
    }

    public int compareTo(scannerGetList_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerGetList_args typedOther = (scannerGetList_args)other;

      lastComparison = Boolean.valueOf(isSetId()).compareTo(isSetId());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(id, typedOther.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetNbRows()).compareTo(isSetNbRows());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(nbRows, typedOther.nbRows);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case ID:
              if (field.type == TType.I32) {
                this.id = iprot.readI32();
                setIdIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case NB_ROWS:
              if (field.type == TType.I32) {
                this.nbRows = iprot.readI32();
                setNbRowsIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(this.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(NB_ROWS_FIELD_DESC);
      oprot.writeI32(this.nbRows);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerGetList_args(");
      boolean first = true;

      sb.append("id:");
      sb.append(this.id);
      first = false;
      if (!first) sb.append(", ");
      sb.append("nbRows:");
      sb.append(this.nbRows);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerGetList_result implements TBase<scannerGetList_result._Fields>, java.io.Serializable, Cloneable   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerGetList_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.LIST, (short)0);
    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);
    private static final TField IA_FIELD_DESC = new TField("ia", TType.STRUCT, (short)2);

    public List<TRowResult> success;
    public IOError io;
    public IllegalArgument ia;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IO((short)1, "io"),
      IA((short)2, "ia");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT, 
          new ListMetaData(TType.LIST, 
              new StructMetaData(TType.STRUCT, TRowResult.class))));
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
      put(_Fields.IA, new FieldMetaData("ia", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerGetList_result.class, metaDataMap);
    }

    public scannerGetList_result() {
    }

    public scannerGetList_result(
      List<TRowResult> success,
      IOError io,
      IllegalArgument ia)
    {
      this();
      this.success = success;
      this.io = io;
      this.ia = ia;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerGetList_result(scannerGetList_result other) {
      if (other.isSetSuccess()) {
        List<TRowResult> __this__success = new ArrayList<TRowResult>();
        for (TRowResult other_element : other.success) {
          __this__success.add(new TRowResult(other_element));
        }
        this.success = __this__success;
      }
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
      if (other.isSetIa()) {
        this.ia = new IllegalArgument(other.ia);
      }
    }

    public scannerGetList_result deepCopy() {
      return new scannerGetList_result(this);
    }

    @Deprecated
    public scannerGetList_result clone() {
      return new scannerGetList_result(this);
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<TRowResult> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(TRowResult elem) {
      if (this.success == null) {
        this.success = new ArrayList<TRowResult>();
      }
      this.success.add(elem);
    }

    public List<TRowResult> getSuccess() {
      return this.success;
    }

    public scannerGetList_result setSuccess(List<TRowResult> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public IOError getIo() {
      return this.io;
    }

    public scannerGetList_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public IllegalArgument getIa() {
      return this.ia;
    }

    public scannerGetList_result setIa(IllegalArgument ia) {
      this.ia = ia;
      return this;
    }

    public void unsetIa() {
      this.ia = null;
    }

    /** Returns true if field ia is set (has been asigned a value) and false otherwise */
    public boolean isSetIa() {
      return this.ia != null;
    }

    public void setIaIsSet(boolean value) {
      if (!value) {
        this.ia = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<TRowResult>)value);
        }
        break;

      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      case IA:
        if (value == null) {
          unsetIa();
        } else {
          setIa((IllegalArgument)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IO:
        return getIo();

      case IA:
        return getIa();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IO:
        return isSetIo();
      case IA:
        return isSetIa();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerGetList_result)
        return this.equals((scannerGetList_result)that);
      return false;
    }

    public boolean equals(scannerGetList_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      boolean this_present_ia = true && this.isSetIa();
      boolean that_present_ia = true && that.isSetIa();
      if (this_present_ia || that_present_ia) {
        if (!(this_present_ia && that_present_ia))
          return false;
        if (!this.ia.equals(that.ia))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_success = true && (isSetSuccess());
      builder.append(present_success);
      if (present_success)
        builder.append(success);

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      boolean present_ia = true && (isSetIa());
      builder.append(present_ia);
      if (present_ia)
        builder.append(ia);

      return builder.toHashCode();
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case SUCCESS:
              if (field.type == TType.LIST) {
                {
                  TList _list102 = iprot.readListBegin();
                  this.success = new ArrayList<TRowResult>(_list102.size);
                  for (int _i103 = 0; _i103 < _list102.size; ++_i103)
                  {
                    TRowResult _elem104;
                    _elem104 = new TRowResult();
                    _elem104.read(iprot);
                    this.success.add(_elem104);
                  }
                  iprot.readListEnd();
                }
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IA:
              if (field.type == TType.STRUCT) {
                this.ia = new IllegalArgument();
                this.ia.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new TList(TType.STRUCT, this.success.size()));
          for (TRowResult _iter105 : this.success)
          {
            _iter105.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      } else if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      } else if (this.isSetIa()) {
        oprot.writeFieldBegin(IA_FIELD_DESC);
        this.ia.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerGetList_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ia:");
      if (this.ia == null) {
        sb.append("null");
      } else {
        sb.append(this.ia);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerClose_args implements TBase<scannerClose_args._Fields>, java.io.Serializable, Cloneable, Comparable<scannerClose_args>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerClose_args");

    private static final TField ID_FIELD_DESC = new TField("id", TType.I32, (short)1);

    /**
     * id of a scanner returned by scannerOpen
     */
    public int id;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      /**
       * id of a scanner returned by scannerOpen
       */
      ID((short)1, "id");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __ID_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.ID, new FieldMetaData("id", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.I32)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerClose_args.class, metaDataMap);
    }

    public scannerClose_args() {
    }

    public scannerClose_args(
      int id)
    {
      this();
      this.id = id;
      setIdIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerClose_args(scannerClose_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.id = other.id;
    }

    public scannerClose_args deepCopy() {
      return new scannerClose_args(this);
    }

    @Deprecated
    public scannerClose_args clone() {
      return new scannerClose_args(this);
    }

    /**
     * id of a scanner returned by scannerOpen
     */
    public int getId() {
      return this.id;
    }

    /**
     * id of a scanner returned by scannerOpen
     */
    public scannerClose_args setId(int id) {
      this.id = id;
      setIdIsSet(true);
      return this;
    }

    public void unsetId() {
      __isset_bit_vector.clear(__ID_ISSET_ID);
    }

    /** Returns true if field id is set (has been asigned a value) and false otherwise */
    public boolean isSetId() {
      return __isset_bit_vector.get(__ID_ISSET_ID);
    }

    public void setIdIsSet(boolean value) {
      __isset_bit_vector.set(__ID_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case ID:
        if (value == null) {
          unsetId();
        } else {
          setId((Integer)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case ID:
        return new Integer(getId());

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case ID:
        return isSetId();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerClose_args)
        return this.equals((scannerClose_args)that);
      return false;
    }

    public boolean equals(scannerClose_args that) {
      if (that == null)
        return false;

      boolean this_present_id = true;
      boolean that_present_id = true;
      if (this_present_id || that_present_id) {
        if (!(this_present_id && that_present_id))
          return false;
        if (this.id != that.id)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_id = true;
      builder.append(present_id);
      if (present_id)
        builder.append(id);

      return builder.toHashCode();
    }

    public int compareTo(scannerClose_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerClose_args typedOther = (scannerClose_args)other;

      lastComparison = Boolean.valueOf(isSetId()).compareTo(isSetId());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(id, typedOther.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case ID:
              if (field.type == TType.I32) {
                this.id = iprot.readI32();
                setIdIsSet(true);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(this.id);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerClose_args(");
      boolean first = true;

      sb.append("id:");
      sb.append(this.id);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class scannerClose_result implements TBase<scannerClose_result._Fields>, java.io.Serializable, Cloneable, Comparable<scannerClose_result>   {
    private static final TStruct STRUCT_DESC = new TStruct("scannerClose_result");

    private static final TField IO_FIELD_DESC = new TField("io", TType.STRUCT, (short)1);
    private static final TField IA_FIELD_DESC = new TField("ia", TType.STRUCT, (short)2);

    public IOError io;
    public IllegalArgument ia;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      IO((short)1, "io"),
      IA((short)2, "ia");

      private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byId.put((int)field._thriftId, field);
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        return byId.get(fieldId);
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
      put(_Fields.IO, new FieldMetaData("io", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
      put(_Fields.IA, new FieldMetaData("ia", TFieldRequirementType.DEFAULT, 
          new FieldValueMetaData(TType.STRUCT)));
    }});

    static {
      FieldMetaData.addStructMetaDataMap(scannerClose_result.class, metaDataMap);
    }

    public scannerClose_result() {
    }

    public scannerClose_result(
      IOError io,
      IllegalArgument ia)
    {
      this();
      this.io = io;
      this.ia = ia;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public scannerClose_result(scannerClose_result other) {
      if (other.isSetIo()) {
        this.io = new IOError(other.io);
      }
      if (other.isSetIa()) {
        this.ia = new IllegalArgument(other.ia);
      }
    }

    public scannerClose_result deepCopy() {
      return new scannerClose_result(this);
    }

    @Deprecated
    public scannerClose_result clone() {
      return new scannerClose_result(this);
    }

    public IOError getIo() {
      return this.io;
    }

    public scannerClose_result setIo(IOError io) {
      this.io = io;
      return this;
    }

    public void unsetIo() {
      this.io = null;
    }

    /** Returns true if field io is set (has been asigned a value) and false otherwise */
    public boolean isSetIo() {
      return this.io != null;
    }

    public void setIoIsSet(boolean value) {
      if (!value) {
        this.io = null;
      }
    }

    public IllegalArgument getIa() {
      return this.ia;
    }

    public scannerClose_result setIa(IllegalArgument ia) {
      this.ia = ia;
      return this;
    }

    public void unsetIa() {
      this.ia = null;
    }

    /** Returns true if field ia is set (has been asigned a value) and false otherwise */
    public boolean isSetIa() {
      return this.ia != null;
    }

    public void setIaIsSet(boolean value) {
      if (!value) {
        this.ia = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case IO:
        if (value == null) {
          unsetIo();
        } else {
          setIo((IOError)value);
        }
        break;

      case IA:
        if (value == null) {
          unsetIa();
        } else {
          setIa((IllegalArgument)value);
        }
        break;

      }
    }

    public void setFieldValue(int fieldID, Object value) {
      setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case IO:
        return getIo();

      case IA:
        return getIa();

      }
      throw new IllegalStateException();
    }

    public Object getFieldValue(int fieldId) {
      return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      switch (field) {
      case IO:
        return isSetIo();
      case IA:
        return isSetIa();
      }
      throw new IllegalStateException();
    }

    public boolean isSet(int fieldID) {
      return isSet(_Fields.findByThriftIdOrThrow(fieldID));
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof scannerClose_result)
        return this.equals((scannerClose_result)that);
      return false;
    }

    public boolean equals(scannerClose_result that) {
      if (that == null)
        return false;

      boolean this_present_io = true && this.isSetIo();
      boolean that_present_io = true && that.isSetIo();
      if (this_present_io || that_present_io) {
        if (!(this_present_io && that_present_io))
          return false;
        if (!this.io.equals(that.io))
          return false;
      }

      boolean this_present_ia = true && this.isSetIa();
      boolean that_present_ia = true && that.isSetIa();
      if (this_present_ia || that_present_ia) {
        if (!(this_present_ia && that_present_ia))
          return false;
        if (!this.ia.equals(that.ia))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      HashCodeBuilder builder = new HashCodeBuilder();

      boolean present_io = true && (isSetIo());
      builder.append(present_io);
      if (present_io)
        builder.append(io);

      boolean present_ia = true && (isSetIa());
      builder.append(present_ia);
      if (present_ia)
        builder.append(ia);

      return builder.toHashCode();
    }

    public int compareTo(scannerClose_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      scannerClose_result typedOther = (scannerClose_result)other;

      lastComparison = Boolean.valueOf(isSetIo()).compareTo(isSetIo());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(io, typedOther.io);
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = Boolean.valueOf(isSetIa()).compareTo(isSetIa());
      if (lastComparison != 0) {
        return lastComparison;
      }
      lastComparison = TBaseHelper.compareTo(ia, typedOther.ia);
      if (lastComparison != 0) {
        return lastComparison;
      }
      return 0;
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) { 
          break;
        }
        _Fields fieldId = _Fields.findByThriftId(field.id);
        if (fieldId == null) {
          TProtocolUtil.skip(iprot, field.type);
        } else {
          switch (fieldId) {
            case IO:
              if (field.type == TType.STRUCT) {
                this.io = new IOError();
                this.io.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
            case IA:
              if (field.type == TType.STRUCT) {
                this.ia = new IllegalArgument();
                this.ia.read(iprot);
              } else { 
                TProtocolUtil.skip(iprot, field.type);
              }
              break;
          }
          iprot.readFieldEnd();
        }
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetIo()) {
        oprot.writeFieldBegin(IO_FIELD_DESC);
        this.io.write(oprot);
        oprot.writeFieldEnd();
      } else if (this.isSetIa()) {
        oprot.writeFieldBegin(IA_FIELD_DESC);
        this.ia.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("scannerClose_result(");
      boolean first = true;

      sb.append("io:");
      if (this.io == null) {
        sb.append("null");
      } else {
        sb.append(this.io);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ia:");
      if (this.ia == null) {
        sb.append("null");
      } else {
        sb.append(this.ia);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

}
