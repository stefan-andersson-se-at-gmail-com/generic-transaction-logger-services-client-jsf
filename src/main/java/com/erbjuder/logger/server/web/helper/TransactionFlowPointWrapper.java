/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erbjuder.logger.server.web.helper;

import com.erbjuder.logger.server.entity.impl.LogMessage;
import java.io.IOException;
import java.io.Serializable;
import org.json.simple.JSONObject;

/**
 *
 * @author Stefan Andersson
 */
public class TransactionFlowPointWrapper implements Serializable {

    private String name = "";
    private LogMessage logMessage;

    public TransactionFlowPointWrapper(
            LogMessage entity,
            String name) {

        this.logMessage = entity;
        this.name = name;

    }

    public boolean isIsError() {
        return isIsPending() == false ? getLogMessage().isIsError() : false;
    }

    public boolean isIsPending() {
        return logMessage == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LogMessage getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(LogMessage logMessage) {
        this.logMessage = logMessage;
    }

    @Override
    public String toString() {
        return toJSONString();
    }

    public String toJSONString() {
        return toJSON().toString();
    }

    public String toJSONPrettyString() {
        String jsonString = "";
        try {
            JSONPrettyPrintWriter writer = new JSONPrettyPrintWriter();
            toJSON().writeJSONString(writer);
            jsonString = writer.toString();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return jsonString;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("flowPointName", name);
        json.put("loggMessageId", logMessage.getId());
        return json;
    }

}
