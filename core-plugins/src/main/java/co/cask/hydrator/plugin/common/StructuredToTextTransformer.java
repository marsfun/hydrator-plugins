
/*
 * Copyright © 2016 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package co.cask.hydrator.plugin.common;

import co.cask.cdap.api.data.format.StructuredRecord;
import co.cask.hydrator.common.RecordConverter;

import java.io.IOException;

/**
 * Creates Single String from StructuredRecord
 */
public class StructuredToTextTransformer extends RecordConverter<StructuredRecord, String> {

  private final String delimiter;
  private static final String FIELD_NAME = "record";

  public StructuredToTextTransformer(String delimiter) {
    this.delimiter = delimiter;
  }

  @Override
  public String transform(StructuredRecord structuredRecord,
                          co.cask.cdap.api.data.schema.Schema schema) throws IOException {
    String recordString = "";
    for (co.cask.cdap.api.data.schema.Schema.Field field : schema.getFields()) {
      Object recordField = structuredRecord.get(field.getName());
      String fieldValue = recordField == null ? "" : recordField.toString();
      recordString += fieldValue + delimiter;
    }
    return recordString.substring(0, recordString.length());
  }
}
