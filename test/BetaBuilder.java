import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class BetaBuilder {
    private String betaId;
    private String betaDescription;
    private List<Gamma> gammas = newArrayList();

    public BetaBuilder withBetaId(String betaId) {
        this.betaId = betaId;
        return this;
    }

    public BetaBuilder withBetaDescription(String betaDescription) {
        this.betaDescription = betaDescription;
        return this;
    }

    public BetaBuilder withGamma(Gamma gamma) {
        this.gammas.add(gamma);
        return this;
    }

    public Beta build() {
        return new Beta(betaId, betaDescription, gammas);
    }
}
