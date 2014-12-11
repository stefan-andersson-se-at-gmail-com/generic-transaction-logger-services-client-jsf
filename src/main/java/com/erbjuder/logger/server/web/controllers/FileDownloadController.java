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

import com.erbjuder.logger.server.entity.impl.LogMessage;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Incomplete;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_01;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_02;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_03;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_04;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_05;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_06;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_07;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_08;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_09;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_10;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_11;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_12;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_13;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_14;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_15;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_16;
import com.erbjuder.logger.server.entity.impl.LogMessageData_Partition_17;
import com.erbjuder.logger.server.entity.interfaces.LogMessageData;
import com.erbjuder.logger.server.facade.impl.LogMessageDataFacadeImpl;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stefan Andersson
 */
@ManagedBean(name = "fileDownloadController")
@RequestScoped
public class FileDownloadController {

    @EJB
    LogMessageDataFacadeImpl logMessageDataFacade;

    public String getDownloadLogMessageDataFileName(LogMessage logMessage, LogMessageData logMessageData) {
        String fileName = logMessage.getApplicationName() + "_data_" + logMessageData.getId();
        fileName = fileName.replaceAll(" ", "_");
        return fileName;
    }

    public void downloadLogMessageDataContent(LogMessage logMessage, LogMessageData logMessageData) throws IOException {

        if (logMessageData instanceof LogMessageData_Incomplete) {
            LogMessageData_Incomplete data = (LogMessageData_Incomplete) logMessageData;
            logMessageData = logMessageDataFacade.getLogMessageData(data);
            doFileResponse(logMessage, logMessageData);

        } else if (logMessageData instanceof LogMessageData_Partition_01) {

            LogMessageData_Partition_01 data = (LogMessageData_Partition_01) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_02) {
            LogMessageData_Partition_02 data = (LogMessageData_Partition_02) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_03) {
            LogMessageData_Partition_03 data = (LogMessageData_Partition_03) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_04) {
            LogMessageData_Partition_04 data = (LogMessageData_Partition_04) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_05) {
            LogMessageData_Partition_05 data = (LogMessageData_Partition_05) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_06) {
            LogMessageData_Partition_06 data = (LogMessageData_Partition_06) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_07) {
            LogMessageData_Partition_07 data = (LogMessageData_Partition_07) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_08) {
            LogMessageData_Partition_08 data = (LogMessageData_Partition_08) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_09) {
            LogMessageData_Partition_09 data = (LogMessageData_Partition_09) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_10) {
            LogMessageData_Partition_10 data = (LogMessageData_Partition_10) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_11) {
            LogMessageData_Partition_11 data = (LogMessageData_Partition_11) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_12) {
            LogMessageData_Partition_12 data = (LogMessageData_Partition_12) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_13) {
            LogMessageData_Partition_13 data = (LogMessageData_Partition_13) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_14) {
            LogMessageData_Partition_14 data = (LogMessageData_Partition_14) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_15) {
            LogMessageData_Partition_15 data = (LogMessageData_Partition_15) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_16) {
            LogMessageData_Partition_16 data = (LogMessageData_Partition_16) logMessageData;
            doFileResponse(logMessage, data);

        } else if (logMessageData instanceof LogMessageData_Partition_17) {
            LogMessageData_Partition_17 data = (LogMessageData_Partition_17) logMessageData;
            doFileResponse(logMessage, data);

        }
    }

    private void doFileResponse(LogMessage logMessage, LogMessageData data) throws IOException {

        HttpServletResponse response
                = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + getDownloadLogMessageDataFileName(logMessage, data) + ".txt");
        response.getOutputStream().write(data.getContent().getBytes());
        response.getOutputStream().flush();
        response.getOutputStream().close();
        FacesContext.getCurrentInstance().responseComplete();
    }

}
