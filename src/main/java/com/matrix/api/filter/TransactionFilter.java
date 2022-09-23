package com.matrix.api.filter;

import com.matrix.api.controller.MatrixController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

@Component
public class TransactionFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(MatrixController.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        boolean error = false;
        String responseText = "Please upload a text/csv file.";

        try{
            if(!req.getRequestURL().toString().contains("swagger") && !req.getRequestURL().toString().contains("docs")) {
                Part file = req.getPart("file");

                log.info("Checking file content type: {}", file.getSubmittedFileName());

                if(!file.getContentType().equals("text/csv")){
                    error = true;
                    responseText = "\"Please upload a text/csv file.\"";
                }
            }

        } catch(Exception e){
            log.error(e.getMessage());
            error = true;
            if(e.getMessage().contains("SizeLimitExceededException"))
                responseText = "Internal server error. The request was rejected because its size exceeds the configured maximum (10485760).";
            else
                responseText = "Internal server error. The file could not be processed.";
        } finally {
            if(error){
                response.setContentLength(responseText.length());
                response.getWriter().write(responseText);
            } else chain.doFilter(request, response);
        }
    }
}