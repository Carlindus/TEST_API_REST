package org.symbolit.dao;

public interface IMessage {

    /**
     * Return the code HTTP of error
     * @return the code HTTP of error
     */
    int getHttpCode();

    /**
     * Return the king of message
     * @return the king of message
     */
    String getTag();

    /**
     * Return the king of message
     * @return the content of message
     */
    String getMessage();

    /**
     * Define the code HTTP of error
     */
    void setHttpCode(int httpCode);

    /**
     * Define the king of message
     */
    void setTag(String tag);

    /**
     * Define the content of message
     */
    void setMessage(String message);

}
