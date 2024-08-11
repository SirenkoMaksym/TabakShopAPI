/*
 * created by max$
 */


package de.tabakshop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class AuthRequestDto {
    private String email;
    private String password;
}
