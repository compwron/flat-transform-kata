public class FlatAlphaBuilder {
    private String betaId;
    private String betaName;
    private String gammaId;
    private String gammaDescription;

    public FlatAlphaBuilder withBetaId(String betaId) {
        this.betaId = betaId;
        return this;
    }

    public FlatAlphaBuilder withBetaName(String betaName) {
        this.betaName = betaName;
        return this;
    }

    public FlatAlphaBuilder withGammaId(String gammaId) {
        this.gammaId = gammaId;
        return this;
    }

    public FlatAlphaBuilder withGammaDescription(String gammaDescription) {
        this.gammaDescription = gammaDescription;
        return this;
    }

    public FlatAlpha build() {
        return new FlatAlpha(betaId, betaName, gammaId, gammaDescription);
    }
}
