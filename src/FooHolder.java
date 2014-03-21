import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


public class FooHolder {
//    @Getter
    private List<Foo> foos;

    public FooHolder(ArrayList<FlatFoo> flatFoos) {
        this.foos = new ArrayList<Foo>();
        Bar bar = new Bar(flatFoos.)
        foos.add(new Foo());
    }

    public List<Foo> getFoos() {
        return foos;
    }
}