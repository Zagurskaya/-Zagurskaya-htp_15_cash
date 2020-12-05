package com.zagurskaya.cash.tag;

import com.zagurskaya.cash.entity.RateCB;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.CurrencyService;
import com.zagurskaya.cash.model.service.RateCBService;
import com.zagurskaya.cash.model.service.impl.CurrencyServiceImpl;
import com.zagurskaya.cash.model.service.impl.RateCBServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class RateTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(RateTag.class);
    private final RateCBService rateCBService = new RateCBServiceImpl();
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private String rateValue;

    @Override
    public int doStartTag() throws JspException {
        try {
            List<RateCB> lastRate = rateCBService.allLastRateCB();
            Map<String, String> currencies = currencyService.findAllToMap();
            StringBuffer buffer = new StringBuffer();
            buffer.append("<table class=\"table table-striped table-bordered table-sm\">")
                    .append("<thead><tr>");
            for (int i = 0; i < lastRate.size() / 2; i++) {
                buffer.append("<th scope=\"col\"></th><th scope=\"col\">buy</th><th scope=\"col\">sale</th>");
            }
            buffer.append("</tr></thead><tbody><tr>");
            for (int i = 0; i < lastRate.size(); i += 2) {
                buffer.append("<th > " + currencies.get(lastRate.get(i).getComing().toString()) + " </th>")
                        .append("<td>" + lastRate.get(i).getSum() + "</td>").append("<td>")
                        .append(lastRate.get(i + 1).getSum()).append("</td>");
            }
            buffer.append("</tr></tbody></table>");
            this.rateValue = buffer.toString();

            pageContext.getOut().print(this.rateValue);

        } catch (ServiceException | IOException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    public void setRateValue(String value) {
        this.rateValue = value;
    }
}