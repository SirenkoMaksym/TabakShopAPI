/*
 * created by max$
 */


package de.tabakshop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ErrorRegDto {
    private String message;
    private int status;
    private String timestamp;
    private String error;
    private String path;
}
