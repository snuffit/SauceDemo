package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Checkout {

    private String firstName;
    private String lastName;
    private String postalCode;
}
