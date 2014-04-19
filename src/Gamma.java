import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Gamma implements Comparable<Gamma> {
    private Integer gammaId;
    private String description;

    public Gamma(FlatAlpha flatAlpha) {
        this.gammaId = Integer.parseInt(flatAlpha.getGammaId());
        this.description = flatAlpha.getGammaName();
    }

    @Override
    public int compareTo(Gamma other) {
        return gammaId - other.getGammaId();
    }
}
