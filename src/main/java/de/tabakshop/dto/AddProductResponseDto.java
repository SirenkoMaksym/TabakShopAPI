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
public class AddProductResponseDto {
    private Integer id;
    private String title;
    private Integer price;
    private Integer quantity;
    private boolean active;
    private String description;
    private String characteristics;

}
