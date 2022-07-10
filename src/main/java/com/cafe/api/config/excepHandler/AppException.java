package com.cafe.api.config.excepHandler;

import com.cafe.api.common.utils.statusType;

public class AppException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -7178945192162789217L;

  private statusType errorType;

  public AppException(statusType type) {
    this(type, null);
  }
  public AppException(statusType type, String customMessage) {
    super();

    errorType = type;
    if (customMessage != null) {
      errorType.message = customMessage;
    }
  }

  public AppException(statusType type, String customMessage, Throwable ex) {
    super(ex);

    errorType = type;
    if (customMessage != null) {
      errorType.message = customMessage;
    }
  }

  public statusType getErrorType() {
    return errorType;
  }
}
