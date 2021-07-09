package com.epam.project.tag;

import com.epam.project.service.impl.SubscriptionServiceImpl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class DeleteUserButtonTag extends SimpleTagSupport {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }



    @Override
    public void doTag() throws JspException, IOException {

        if(SubscriptionServiceImpl.getInstance().findAllSubscriptionsByUser(id).size()==0){
            getJspContext().getOut().print("<form method=\"get\" action=\"deleteUser\">\n" +
                    "                                <input type=\"hidden\" name=\"id\" value="+id+" />\n" +
                    "                               <input type=\"submit\" class=\"btn btn-outline-danger m-lg-3\" value="+"<fmt:message key=\"users.delete\"/>\">  " +
                    "                            </form>");
        }

    }
}

