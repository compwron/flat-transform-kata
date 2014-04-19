import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class FlatAlpha implements Comparable<FlatAlpha> {
    private String betaId;
    private String betaName;
    private String gammaId;
    private String gammaName;

    @Override
    public int compareTo(FlatAlpha other) {
        return Integer.parseInt(betaId) - Integer.parseInt(other.getBetaId());
    }
}
