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

import com.erbjuder.logger.server.facade.interfaces.LogMessageFacade;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Stefan Andersson
 */
@ManagedBean(name = "configurationController")
@SessionScoped
public class ConfigurationController extends ConfigurationControllerBase implements Serializable {

    @EJB
    private com.erbjuder.logger.server.facade.impl.LogMessageFacadeImpl logMessageFacade;
   
    // Logger
    private static final Logger logger = Logger.getLogger(FlowController.class.getName());

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public String getReturnPage() {
        return "List";
    }

    @Override
    public LogMessageFacade getLogMessageFacade() {
        return logMessageFacade;
    }

    
}
