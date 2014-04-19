import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


public class Alpha {
    @Getter
    private List<Beta> betas = newArrayList();

    public Alpha(List<FlatAlpha> flatAlphas) {
        List<FlatAlpha> flatAlphasWithoutInvalidAlphas = new AlphaExclusions().excludeInvalid(flatAlphas);
        Collections.sort(flatAlphasWithoutInvalidAlphas);

        if (flatAlphas.size() == 0) {
            return;
        }

        String betaId = flatAlphasWithoutInvalidAlphas.get(0).getBetaId();

        List<FlatAlpha> flatAlphasForCurrentBeta = newArrayList();
        for (FlatAlpha flatAlpha : flatAlphasWithoutInvalidAlphas) {
            if (!betaId.equals(flatAlpha.getBetaId())) {
                betas.add(new Beta(flatAlphasForCurrentBeta));
                betaId = flatAlpha.getBetaId();
                flatAlphasForCurrentBeta = newArrayList();
            }
            flatAlphasForCurrentBeta.add(flatAlpha);
        }
        betas.add(new Beta(flatAlphasForCurrentBeta));
    }

    public List<Gamma> getGammasForBeta(String betaId) {
        List<Beta> filteredBetas = newArrayList(Collections2.filter(betas, filterById(betaId)));
        return filteredBetas.size() > 0 ? filteredBetas.get(0).getGammas() : new ArrayList<Gamma>();
    }

    private Predicate<? super Beta> filterById(final String betaId) {
        return new Predicate<Beta>() {
            @Override
            public boolean apply(Beta beta) {
                return beta.getBetaId().equals(betaId);
            }
        };
    }

    public Gamma getGammaById(String gammaId) {
        for (Beta beta : betas) {
            for (Gamma gamma : beta.getGammas()) {
                if (String.valueOf(gamma.getGammaId()).equals(gammaId)) {
                    return gamma;
                }
            }
        }
        return null;
    }

    public Beta getBetaById(String betaId) {
        for (Beta beta : betas) {
            if (beta.getBetaId().equals(betaId)) {
                return beta;
            }
        }
        return null;
    }

    public Beta getBetaByGammaId(Integer gammaId) {
        for (Beta beta : betas) {
            for (Gamma gamma : beta.getGammas()) {
                if (gammaId.equals(gamma.getGammaId())) {
                    return beta;
                }
            }
        }
        return null;
    }
}