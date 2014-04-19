import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@ToString
public class Beta {
    private String betaId;
    private String betaName;
    private List<Gamma> gammas;

    public Beta(List<FlatAlpha> flatAlphasForCurrentBeta) {
        FlatAlpha first = flatAlphasForCurrentBeta.get(0);
        this.betaId = first.getBetaId();
        this.betaName = first.getBetaName();
        this.gammas = newArrayList();

        for (FlatAlpha flatAlpha : flatAlphasForCurrentBeta) {
            this.gammas.add(new Gamma(flatAlpha));
        }
        Collections.sort(gammas);

    }
}
