package FLAGCamp.FindMyHome.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelTimeResponse {

    private List<Node> nodes;
    private List<String> travelModes;
    private List<String> optimisticTime;
    private List<String> pessimisticTime;
    private List<BigDecimal> fare;

}
