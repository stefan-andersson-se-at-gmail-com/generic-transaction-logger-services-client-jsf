/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erbjuder.logger.server.web.helper;

/**
 *
 * @author Stefan Andersson
 */
public class UserAgentDetail {

    private String browserName;
    private String browserVersion;
    private String browserComments;

    /**
     * Constructor.
     *
     * @param browserName the name of the browser
     * @param browserVersion the version of the browser
     * @param browserComments the operating system the browser is running on
     */
    UserAgentDetail(String browserName, String browserVersion, String browserComments) {
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.browserComments = browserComments;
    }

    public String getBrowserComments() {
        return browserComments;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

}
