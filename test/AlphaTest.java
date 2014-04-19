import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AlphaTest {
    @Test
    public void shouldTranslateFromFlatAlpha() {

        FlatAlpha FlatAlpha = new FlatAlphaBuilder()
                .withBetaId("1")
                .withBetaName("Beta name")
                .withGammaId("100")
                .withGammaDescription("gamma 1 description")
                .build();

        Alpha alpha = new Alpha(newArrayList(FlatAlpha));

        Beta beta = new BetaBuilder()
                .withBetaId("1")
                .withBetaDescription("Beta name")
                .withGamma(new Gamma(100, "gamma 1 description"))
                .build();

        assertThat(alpha.getBetas().size(), is(1));
        assertThat(alpha.getBetas().get(0), is(beta));
    }

    @Test
    public void shouldTranslateTwoBetasWithTwoGammasEachFromFlatAlpha() {

        FlatAlpha betaGamma1 = new FlatAlphaBuilder()
                .withBetaId("1")
                .withBetaName("Beta name 1")
                .withGammaId("100")
                .withGammaDescription("gamma 1 description")
                .build();

        FlatAlpha betaGamma2 = new FlatAlphaBuilder()
                .withBetaId("2")
                .withBetaName("Beta name 2")
                .withGammaId("200")
                .withGammaDescription("gamma 2 description")
                .build();

        Alpha alpha = new Alpha(newArrayList(betaGamma1, betaGamma2));

        Beta beta1 = new BetaBuilder()
                .withBetaId("1")
                .withBetaDescription("Beta name 1")
                .withGamma(new Gamma(100, "gamma 1 description"))
                .build();

        Beta Beta2 = new BetaBuilder()
                .withBetaId("2")
                .withBetaDescription("Beta name 2")
                .withGamma(new Gamma(200, "gamma 2 description"))
                .build();

        assertThat(alpha.getBetas().size(), is(2));

        assertThat(alpha.getBetas().get(0), is(beta1));
        assertThat(alpha.getBetas().get(0).getGammas().get(0), is(new Gamma(100, "gamma 1 description")));

        assertThat(alpha.getBetas().get(1), is(Beta2));
        assertThat(alpha.getBetas().get(1).getGammas().get(0), is(new Gamma(200, "gamma 2 description")));
    }

    @Test
    public void shouldExcludeInvalidFlatAlphas(){
        FlatAlpha withInvalidGamma = new FlatAlphaBuilder()
                .withBetaId("1")
                .withBetaName("Beta name 1")
                .withGammaId("100")
                .withGammaDescription("XXXX")
                .build();

        FlatAlpha withValidGamma = new FlatAlphaBuilder()
                .withBetaId("1")
                .withBetaName("Beta name 1")
                .withGammaId("200")
                .withGammaDescription("gamma 1 description")
                .build();

        FlatAlpha oneInvalidGamma = new FlatAlphaBuilder()
                .withBetaId("2")
                .withBetaName("Beta name 2")
                .withGammaId("300")
                .withGammaDescription("XXXX")
                .build();

        Alpha alpha = new Alpha(newArrayList(withInvalidGamma, withValidGamma, oneInvalidGamma));

        Gamma gamma = new Gamma(200, "gamma 1 description");
        Beta beta = new BetaBuilder()
                .withBetaId("1")
                .withBetaDescription("Beta name 1")
                .withGamma(gamma)
                .build();

        assertThat(alpha.getBetas().size(), is(1));

        assertThat(alpha.getBetas().get(0), is(beta));
        assertThat(alpha.getBetas().get(0).getGammas().get(0), is(gamma));
    }

    @Test
    public void shouldReturnGammasForBeta(){
        FlatAlpha flatAlphaFor31 = new FlatAlphaBuilder().withBetaId("31").withBetaName("Beta 1")
                .withGammaId("311").withGammaDescription("Gamma 311").build();

        FlatAlpha flatAlphaFor32 = new FlatAlphaBuilder().withBetaId("32").withBetaName("Beta 2")
                .withGammaId("321").withGammaDescription("Gamma 321").build();

        Alpha alpha = new Alpha(newArrayList(flatAlphaFor31, flatAlphaFor32));

        List<Gamma> gammasForBeta = alpha.getGammasForBeta("31");
        assertThat(gammasForBeta, hasSize(1));
        assertThat(gammasForBeta.get(0).getGammaId(), is(311));
    }

    @Test
    public void shouldReturnEmptyListForBetaThatDoesntExist(){
        FlatAlpha flatAlphaFor31 = new FlatAlphaBuilder().withBetaId("31").withBetaName("Beta 1")
                .withGammaId("311").withGammaDescription("Gamma 311").build();

        Alpha Alpha = new Alpha(newArrayList(flatAlphaFor31));

        List<Gamma> gammasForBeta = Alpha.getGammasForBeta("32");
        assertThat(gammasForBeta, hasSize(0));
    }

    @Test
    public void gammasInBetaShouldBeInOrderByIdEvenIfInputIsNot(){
        FlatAlpha withBeta200 = new FlatAlphaBuilder().withBetaId("1").withBetaName("Beta 1")
                .withGammaId("200").withGammaDescription("Gamma 200").build();
        FlatAlpha withBeta100 = new FlatAlphaBuilder().withBetaId("1").withBetaName("Beta 1")
                .withGammaId("100").withGammaDescription("Gamma 100").build();
        FlatAlpha withBeta300 = new FlatAlphaBuilder().withBetaId("1").withBetaName("Beta 1")
                .withGammaId("300").withGammaDescription("Gamma 300").build();

        Alpha alpha = new Alpha(newArrayList(withBeta200, withBeta100, withBeta300));

        List<Beta> betas = alpha.getBetas();
        List<Gamma> gammas = betas.get(0).getGammas();

        assertThat(gammas.get(0).getGammaId(), is(100));
        assertThat(gammas.get(1).getGammaId(), is(200));
        assertThat(gammas.get(2).getGammaId(), is(300));
    }

    @Test
    public void betasShouldBeInOrderByIdEvenIfInputIsNot(){
        FlatAlpha beta2WithGamma200 = new FlatAlphaBuilder().withBetaId("2").withBetaName("Beta 1")
                .withGammaId("200").withGammaDescription("Gamma 200").build();
        FlatAlpha beta1WithGamma100 = new FlatAlphaBuilder().withBetaId("1").withBetaName("Beta 1")
                .withGammaId("100").withGammaDescription("Gamma 100").build();
        FlatAlpha beta3WithGamma300 = new FlatAlphaBuilder().withBetaId("3").withBetaName("Beta 1")
                .withGammaId("300").withGammaDescription("Gamma 300").build();

        Alpha Alpha = new Alpha(newArrayList(beta2WithGamma200, beta1WithGamma100, beta3WithGamma300));

        List<Beta> Betas = Alpha.getBetas();

        assertThat(Betas.get(0).getBetaId(), is("1"));
        assertThat(Betas.get(1).getBetaId(), is("2"));
        assertThat(Betas.get(2).getBetaId(), is("3"));
    }

    @Test
    public void shouldGetGammaById(){
        FlatAlpha FlatAlpha1 = new FlatAlphaBuilder().withBetaId("1").withBetaName("Beta 1")
                .withGammaId("200").withGammaDescription("Gamma 200").build();
        FlatAlpha FlatAlpha2 = new FlatAlphaBuilder().withBetaId("1").withBetaName("Beta 1")
                .withGammaId("100").withGammaDescription("Gamma 100").build();

        Alpha Alpha = new Alpha(newArrayList(FlatAlpha1, FlatAlpha2));

        assertThat(Alpha.getGammaById("100").getDescription(), is("Gamma 100"));
    }

    @Test
    public void shouldReturnNullGammaWhenNoGammaWithIdIsPresent(){
        Alpha Alpha = new Alpha(new ArrayList<FlatAlpha>());

        assertThat(Alpha.getGammaById("100"), nullValue());
    }

}
