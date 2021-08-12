package com.epam.project.tag;

import com.epam.project.service.impl.SubscriptionServiceImpl;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteUserButtonTag extends SimpleTagSupport {
    private Long id;
    private String locale;

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public void doTag() throws JspException, IOException {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("language", Locale.forLanguageTag(locale));

        if(SubscriptionServiceImpl.getInstance().findAllSubscriptionsByUser(id).size()==0){
            getJspContext().getOut().print("<form method=\"get\" action=\"deleteUser\">\n" +
                    "                                <input type=\"hidden\" name=\"id\" value="+id+" />\n" +
                    "                               <input type=\"submit\" class=\"btn btn-outline-danger m-lg-3\" value="+resourceBundle.getString("users.delete")+">  " +
                    "                            </form>");
        }

    }
}

