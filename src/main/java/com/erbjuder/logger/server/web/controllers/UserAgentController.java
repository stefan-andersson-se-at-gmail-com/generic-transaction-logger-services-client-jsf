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

import com.erbjuder.logger.server.web.helper.CommonWebUtil;
import com.erbjuder.logger.server.web.helper.JsfUtil;
import com.erbjuder.logger.server.web.helper.UserAgentParser;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Stefan Andersson
 */
@ManagedBean(name = "userAgentController")
@RequestScoped
public class UserAgentController {

    public UserAgentController() {
        try {
            String userAgentStr = JsfUtil.getRequestHeaderParameter("User-Agent");
            UserAgentParser userAgent = CommonWebUtil.getBrowserInfo(userAgentStr);

            if ("MSIE".equalsIgnoreCase(userAgent.getBrowserName()) && Float.parseFloat(userAgent.getBrowserVersion()) < 8) {
                JsfUtil.addErrorMessage("This page don't support old Internet Explorer browsers!");
                JsfUtil.addErrorMessage("Upgrade to newer version or use Firefox or Chrome!");
                JsfUtil.addErrorMessage("Your browser [ " + userAgent.getBrowserName() + " " + userAgent.getBrowserVersion() + " ]");
            }
        } catch (NumberFormatException e) {
            // Verion not a float
        }
    }

    public boolean isProcessUserAgent() {
        return true;
    }
}
