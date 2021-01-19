package com.vasanti.web.addresses.model;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.concurrent.Flow;

public class ResponseModel {

    private String requestAcknowledgementId ;

    private String responseMessage;

    private String ErrorMessage ;


    public ResponseModel(String requestAcknowledgementId, String responseMessage, String errorMessage) {
        this.requestAcknowledgementId = requestAcknowledgementId;
        this.responseMessage = responseMessage;
        ErrorMessage = errorMessage;
    }

    public ResponseModel() {
    }


    public String getRequestAcknowledgementId() {
        return requestAcknowledgementId;
    }

    public void setRequestAcknowledgementId(String requestAcknowledgementId) {
        this.requestAcknowledgementId = requestAcknowledgementId;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "requestAcknowledgementId='" + requestAcknowledgementId + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", ErrorMessage='" + ErrorMessage + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseModel that = (ResponseModel) o;
        return requestAcknowledgementId.equals(that.requestAcknowledgementId) && responseMessage.equals(that.responseMessage) && Objects.equals(ErrorMessage, that.ErrorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestAcknowledgementId, responseMessage, ErrorMessage);
    }


}
