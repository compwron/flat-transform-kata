import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class FooHolderTest {

    @Test
    public void shouldCreateFooHolderWithFooThatHasBar(){
        FlatFoo flatFoo1 = new FlatFoo("1", "thing", "bar id 1", "bar desc 1");

        FooHolder fooHolder = new FooHolder(newArrayList(flatFoo1));
        assertThat(fooHolder.getFoos(), hasSize(1));
        assertThat(fooHolder.getFoos().get(0).getBars(), hasSize(1));
    }

    @Test
    public void shouldCreateFooHolderWithFooThatHasTwoBars(){
        FlatFoo flatFoo1 = new FlatFoo("1", "thing", "bar id 1", "bar desc 1");
        FlatFoo flatFoo2 = new FlatFoo("1", "thing", "bar id 2", "bar desc 2");

        FooHolder fooHolder = new FooHolder(newArrayList(flatFoo1, flatFoo2));
        assertThat(fooHolder.getFoos(), hasSize(1));
        assertThat(fooHolder.getFoos().get(0).getBars(), hasSize(2));
    }

    @Test
    public void shouldCreateFooHolderWithFooWithIdAndDescription(){
        FlatFoo flatFoo1 = new FlatFoo("1", "thing", "bar id 1", "bar desc 1");

        FooHolder fooHolder = new FooHolder(newArrayList(flatFoo1));
        assertThat(fooHolder.getFoos().get(0).getId(), is("1"));
        assertThat(fooHolder.getFoos().get(0).getDescription(), is("thing"));

    }
}
