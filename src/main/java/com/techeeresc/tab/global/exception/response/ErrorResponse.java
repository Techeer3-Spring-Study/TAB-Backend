package com.techeeresc.tab.global.exception.response;

import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ErrorResponse {
  @Schema(description = "error code", defaultValue = "404")
  private int errorCode;

  @Schema(description = "error message", defaultValue = "NOT FOUND/BAD REQUEST/SERVER ERROR")
  private String message;

  public ErrorResponse(StatusCodes statusCodes, StatusMessage statusMessage) {
    this.errorCode = statusCodes.getStatusCode();
    this.message = statusMessage.getStatusMessage();
  }
}
