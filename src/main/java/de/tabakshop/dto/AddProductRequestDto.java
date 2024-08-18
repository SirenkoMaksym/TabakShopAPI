/*
 * created by $
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
public class AddProductRequestDto {
    private String title;
    private int price;
    private int quantity;
    private boolean active;
    private String description;
    private String characteristics;

}
