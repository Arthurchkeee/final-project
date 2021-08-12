package com.epam.project.tag;

import com.epam.project.entities.Status;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.ResourceBundle;

public class FilterTag extends SimpleTagSupport {
    private String status;
    private Long id;
    private String name;
    private String author;
    private Long subId;
    private String locale;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if(Status.ORDERED_SUBSCRIPTION.equals(Status.valueOf(status))||Status.ORDERED_ROOM.equals(Status.valueOf(status))){
            ResourceBundle resourceBundle=ResourceBundle.getBundle("language", Locale.forLanguageTag(locale));
            getJspContext().getOut().print("<tr>\n" +
                    "                    <td>"+id+"</td>\n" +
                    "                    <td>"+name+"</td>\n" +
                    "                    <td>"+author+"</td>\n" +
                    "                    <form method=\"post\" action=\"canceledOrder\">\n" +
                    "                        <input type=\"hidden\" name=\"book_id\" value="+id+" />\n" +
                    "                        <input type=\"hidden\" name=\"id\" value="+subId+" />\n" +
                    "                        <td id=\"action\">\n" +
                    "                            <input type=\"submit\" class=\"btn btn-outline-danger\" value="+resourceBundle.getString("myBooks.cancel")+">\n" +
                    "                        </td>\n" +
                    "                    </form>\n" +
                    "                </tr>");
        }

    }
}
