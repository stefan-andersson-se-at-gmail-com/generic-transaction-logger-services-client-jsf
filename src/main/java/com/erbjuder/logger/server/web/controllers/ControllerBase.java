/* 
 * Copyright (C) 2014 erbjuder.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.erbjuder.logger.server.web.controllers;

import com.erbjuder.logger.server.common.helper.DataBase;
import com.erbjuder.logger.server.common.helper.MimeTypes;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Incomplete;
import com.erbjuder.logger.server.entity.interfaces.LogMessageData;
import com.erbjuder.logger.server.exception.InvalidXML;
import com.erbjuder.logger.server.web.helper.CommonWebUtil;
import com.erbjuder.logger.server.web.helper.PaginationHelper;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.faces.model.DataModel;

/**
 *
 * @author Stefan Andersson
 */
public abstract class ControllerBase implements Serializable {

    DataModel items = null;
    PaginationHelper pagination;

    public abstract Logger getLogger();

    public abstract String getReturnPage();

    public abstract DataModel getItems();

    public abstract PaginationHelper getPagination();

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return getReturnPage();
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return getReturnPage();
    }

    public void recreateModel() {
        items = null;
    }

    public String convertTimestampTime(Timestamp timestamp) {
        Format format = getDateFormater();
        String timeToSeconds = format.format(timestamp);
        String nanoTime = Integer.toString(timestamp.getNanos());
        return timeToSeconds + "." + nanoTime;
    }

    public String convertDateTime(Date date) {
        Format format = getDateFormater();
        String timeToSeconds = format.format(date);
        return timeToSeconds;
    }

    public SimpleDateFormat getDateFormater() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public Set<Class> getDefaultSearchableDatabases() {
        Set<Class> defaultSearchableDatabases = new HashSet<Class>();
        defaultSearchableDatabases.add(DataBase.LOGMESSAGEDATA_PARTITION_01_CLASS);
        defaultSearchableDatabases.add(DataBase.LOGMESSAGEDATA_PARTITION_02_CLASS);
        defaultSearchableDatabases.add(DataBase.LOGMESSAGEDATA_PARTITION_03_CLASS);
        defaultSearchableDatabases.add(DataBase.LOGMESSAGEDATA_PARTITION_04_CLASS);
        defaultSearchableDatabases.add(DataBase.LOGMESSAGEDATA_PARTITION_05_CLASS);
        defaultSearchableDatabases.add(DataBase.LOGMESSAGEDATA_PARTITION_06_CLASS);
        defaultSearchableDatabases.add(DataBase.LOGMESSAGEDATA_PARTITION_07_CLASS);
        defaultSearchableDatabases.add(DataBase.LOGMESSAGEDATA_PARTITION_08_CLASS);
        defaultSearchableDatabases.add(DataBase.LOGMESSAGEDATA_PARTITION_09_CLASS);
        defaultSearchableDatabases.add(DataBase.LOGMESSAGEDATA_PARTITION_10_CLASS);
        defaultSearchableDatabases.add(DataBase.LOGMESSAGEDATA_PARTITION_11_CLASS);
        return defaultSearchableDatabases;

    }

    public String decodeContent(com.erbjuder.logger.server.entity.interfaces.LogMessageData logMessageData) {
        //return logMessageData.getContent();

        // ToDo Don't use this, it's a temp solution. 
        // Use above insteed when all db-content ar valid
        String content = logMessageData.getContent();
        if (MimeTypes.BASE64.equalsIgnoreCase(logMessageData.getMimeType())) {

            try {
                content = CommonWebUtil.XmlFormatter(logMessageData.getContent());
            } catch (InvalidXML e1) {
                try {
                    // Try to fix invalid characters
                    content = CommonWebUtil.cleanInvalidXmlChars(logMessageData.getContent());
                    content = CommonWebUtil.XmlFormatter(content);
                } catch (InvalidXML e2) {
                    // Can't do mutch return argument
                    content = logMessageData.getContent();
                }
            }
        }

        return content;
    }

    public boolean hasContent(LogMessageData logMessageData) {
        return !(logMessageData instanceof LogMessageData_Incomplete);
    }

}
