import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class AlphaExclusions {

    public List<FlatAlpha> excludeInvalid(List<FlatAlpha> flatAlphas) {
        ArrayList<FlatAlpha> merchandiseWithoutOtherBeta = newArrayList(Collections2.filter(flatAlphas, hasBetaDescriptionOther()));
        return newArrayList(Collections2.filter(merchandiseWithoutOtherBeta, hasInactiveGamma()));
    }

    private Predicate<? super FlatAlpha> hasBetaDescriptionOther() {
        return new Predicate<FlatAlpha>() {
            @Override
            public boolean apply(FlatAlpha alpha) {
                return !alpha.getBetaName().equalsIgnoreCase("OTHER");
            }
        };
    }

    private Predicate<? super FlatAlpha> hasInactiveGamma() {
        return new Predicate<FlatAlpha>() {
            @Override
            public boolean apply(FlatAlpha alpha) {
                return !alpha.getGammaName().toUpperCase().contains("XXX");
            }
        };
    }
}
