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

import com.erbjuder.logger.server.common.helper.DataBaseSearchController;
import com.erbjuder.logger.server.common.helper.FreeTextSearchController;
import com.erbjuder.logger.server.facade.interfaces.LogMessageFacade;
import com.erbjuder.logger.server.web.helper.PaginationHelper;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Stefan Andersson
 */
public abstract class ConfigurationControllerBase extends ControllerBase implements Serializable {

    private DataBaseSearchController dataBaseSearchController = new DataBaseSearchController();
    
    @Override
    public abstract String getReturnPage();

    @Override
    public abstract Logger getLogger();

    public abstract LogMessageFacade getLogMessageFacade();

    public ConfigurationControllerBase() {
        super();
        init();
    }

    private void init() {
        dataBaseSearchController.setTreatAsSelectedDatabases(getDefaultSearchableDatabases());
    }

    @Override
    public DataModel getItems() {

        if (items == null) { // && FacesContext.getCurrentInstance().getRenderResponse()) {
            items = getPagination().createPageDataModel();

        }
        return items;
    }

    @Override
    public PaginationHelper getPagination() {

        if (pagination == null) { // && FacesContext.getCurrentInstance().getRenderResponse()) {

            pagination = new PaginationHelper(50) {
                
                public int maxResult = 5000;
                
                @Override
                public int getItemsCount() {

                    Calendar c1 = Calendar.getInstance(); // August  16th, 2012 AD
                    c1.set(Calendar.YEAR, 0);             // August  16th,    0 AD
                    c1.set(Calendar.DAY_OF_YEAR, 1);      // January  1st,    0 AD
                    Date from = c1.getTime();             // January  1st,    1 BC (corrected)
                    Date to = new Date();

                    return getLogMessageFacade().count(
                            "",
                            "",
                            "",
                            "",
                            from.getTime(),
                            to.getTime(),
                            new FreeTextSearchController(),
                            dataBaseSearchController,
                            null,
                            maxResult);
                }

                @Override
                public DataModel createPageDataModel() {

                    Calendar c1 = Calendar.getInstance(); // August  16th, 2012 AD
                    c1.set(Calendar.YEAR, 0);             // August  16th,    0 AD
                    c1.set(Calendar.DAY_OF_YEAR, 1);      // January  1st,    0 AD
                    Date from = c1.getTime();             // January  1st,    1 BC (corrected)
                    Date to = new Date();
                    ListDataModel list = new ListDataModel(getLogMessageFacade().findRange(
                            new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()},
                            "",
                            "",
                            "",
                            "",
                            from.getTime(),
                            to.getTime(),
                            new FreeTextSearchController(),
                            dataBaseSearchController,
                            null));
                    return list;
                }
            };
        }
        return pagination;
    }

}