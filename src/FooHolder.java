
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;


public class FooHolder {
    //    @Getter
    private List<Foo> foos = newArrayList();

    public FooHolder(ArrayList<FlatFoo> flatFoos) {
        Map<Foo, List<Bar>> fooHash = newHashMap();
        for (FlatFoo flatFoo : flatFoos) {
            String fooId = flatFoo.getFooId();
            if (!fooHash.containsKey(fooId)) {
                Foo keyFoo = new Foo(fooId, flatFoo.getFooDescription());
                fooHash.put(keyFoo, new ArrayList<Bar>());
            }

            Bar bar = new Bar(flatFoo.getBarId(), flatFoo.getBarDescription());
            List<Bar> bars = fooHash.get(key);
            bars.add(bar);
        }

        for (Foo foo : fooHash.keySet()) {

            List<Bar> bars = fooHash.get(foo);
//            foo.addBars(bars);
            foo.getBars().addAll(bars);

            foos.add(foo);
        }
    }

    public List<Foo> getFoos() {
        return foos;
    }
}